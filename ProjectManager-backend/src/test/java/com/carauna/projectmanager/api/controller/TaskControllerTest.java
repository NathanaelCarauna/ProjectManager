package com.carauna.projectmanager.api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.carauna.projectmanager.domain.model.Task;
import com.carauna.projectmanager.exceptionhandler.ApiErrorResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;

class TaskControllerTest extends AbstractTest {

	String expectedResult;

	@BeforeEach
	void tearUp() {
		this.setUp();
	}

	@Test
	void findAllReturnTaskListTest() throws Exception {
		MvcResult createTaskResult = CreateTask("testFindAll", false);
		Task resultTaskEntity = getTaskEntityFromResult(createTaskResult);
		String uri = "/api/task";

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Task[] taskList = super.mapFromJson(content, Task[].class);
		deleteTask(resultTaskEntity.getId());

		assertEquals(200, status);
		assertTrue(taskList.length > 0);
	}

	@Test
	void findByIncorrectIdThrowsNotFoundExceptionTest() throws Exception {
		long id = 0;
		String uri = "/api/task/" + id;

		MvcResult findByIdResult = findById(uri);
		int status = findByIdResult.getResponse().getStatus();
		String content = findByIdResult.getResponse().getContentAsString();
		
		String expectedStatus = "{\"status\":400,";
		String expectedTimeStamp = "\"timeStamp\":";
		String expectedTitle = "\"title\":\"Task with id 0 doesn't exists\"";
		
		assertEquals(400, status);
		assertTrue(content.contains(expectedStatus));
		assertTrue(content.contains(expectedTimeStamp));
		assertTrue(content.contains(expectedTitle));
	}

	@Test
	void findByCorrectIdIsSuccessTest() throws Exception {
		expectedResult = "testFindById";
		MvcResult createTaskResult = CreateTask(expectedResult, false);
		Task resultTaskEntity = getTaskEntityFromResult(createTaskResult);
		String uri = "/api/task/" + resultTaskEntity.getId();

		MvcResult findByIdResult = findById(uri);
		int status = findByIdResult.getResponse().getStatus();
		Task taskEntityFromResult = getTaskEntityFromResult(findByIdResult);		
		deleteTask(resultTaskEntity.getId());
		
		assertEquals(200, status);
		assertEquals("testFindById", taskEntityFromResult.getTitle());
	}

	@Test
	void createTaskWithValidTitleTest() throws Exception {
		expectedResult = "CreateTest";
		MvcResult mvcResult = CreateTask(expectedResult, true);

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Task taskResponse = super.mapFromJson(content, Task.class);
		deleteTask(taskResponse.getId());

		assertEquals(200, status);
		assertEquals(expectedResult, taskResponse.getTitle());
		assertTrue(taskResponse.isCompleted());
	}

	@Test
	void createTaskWithEmptyTitleThrowsExceptionTest() throws Exception {
		String emptyStr = "";
		
		MvcResult mvcResult = CreateTask(emptyStr, true);
		int status = mvcResult.getResponse().getStatus();		
		
		assertEquals(400, status);
	}

	@Test
	void createTaskWithTSmallerThan3ThrowsExceptionTest() throws Exception {
		String emptyStr = "as";
		
		MvcResult mvcResult = CreateTask(emptyStr, true);
		int status = mvcResult.getResponse().getStatus();		
		
		assertEquals(400, status);
	}

	@Test
	void createTaskWithTitleBiggerThan100ThrowsExceptionTest() throws Exception {
		String emptyStr = "asasdasdasdasdasdasdasdasdfasdf asdf asdf sadf asdf asdf sadfasdfasdfasdfasdf asdfa sdfasdfa sdfasd fasdfasdfasdfas";
		
		MvcResult mvcResult = CreateTask(emptyStr, true);
		int status = mvcResult.getResponse().getStatus();		
		
		assertEquals(400, status);
	}

	@Test
	void updateTaskWithValidFieldsTest() throws Exception {
		expectedResult = "updateTest";
		MvcResult createTaskResult = CreateTask("asd", false);
		Task resultTaskEntity = getTaskEntityFromResult(createTaskResult);

		Task task = new Task(resultTaskEntity.getId(), expectedResult, true);
		MvcResult mvcResult = updateTask(task, resultTaskEntity.getId());

		int status = mvcResult.getResponse().getStatus();
		Task taskEntityFromResult = getTaskEntityFromResult(mvcResult);
		deleteTask(resultTaskEntity.getId());

		assertEquals(200, status);
		assertEquals(expectedResult, taskEntityFromResult.getTitle());
	}
	
	@Test
	void updateTaskWithEmptyTitleThrowsExceptionTest() throws Exception {
		String emptyStr = "";
		Task task = new Task(emptyStr, true);
		
		MvcResult mvcResult = updateTask(task, 0);
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		
		String expectedStatus = "{\"status\":400,";
		String expectedTimeStamp = "\"timeStamp\":";
		String expectedTitle = "\"title\":\"One or more fields are invalid. Correct the fields and try again\"";
		String fields = "\"fields\":[";
		
		assertEquals(400, status);
		assertTrue(content.contains(expectedStatus));
		assertTrue(content.contains(expectedTimeStamp));
		assertTrue(content.contains(expectedTitle));
		assertTrue(content.contains(fields));
	}

	@Test
	void updateTaskWithTitleSmallerThan3ThrowsExceptionTest() throws Exception {
		String emptyStr = "as";
		Task task = new Task(emptyStr, true);
		
		MvcResult mvcResult = updateTask(task, 0);
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		
		String expectedStatus = "{\"status\":400,";
		String expectedTimeStamp = "\"timeStamp\":";
		String expectedTitle = "\"title\":\"One or more fields are invalid. Correct the fields and try again\"";
		String fields = "\"fields\":[";
		
		assertEquals(400, status);
		assertTrue(content.contains(expectedStatus));
		assertTrue(content.contains(expectedTimeStamp));
		assertTrue(content.contains(expectedTitle));
		assertTrue(content.contains(fields));
	}

	@Test
	void updateTaskWithBiggerThan100ThrowsExceptionTest() throws Exception {
		String emptyStr = "asasdasdasdasdasdasdasdasdfasdf asdf asdf sadf asdf asdf sadfasdfasdfasdfasdf asdfa sdfasdfa sdfasd fasdfasdfasdfas";
		Task task = new Task(emptyStr, true);
		
		MvcResult mvcResult = updateTask(task, 0);
		int status = mvcResult.getResponse().getStatus();		
		
		assertEquals(400, status);
	}

	@Test
	void deleteTaskWithValidIdTest() throws Exception {
		MvcResult createTaskResult = CreateTask("asd", false);
		Task resultTaskEntity = getTaskEntityFromResult(createTaskResult);

		MvcResult mvcResult = deleteTask(resultTaskEntity.getId());
		int status = mvcResult.getResponse().getStatus();

		assertEquals(200, status);
	}

	@Test
	void deleteTaskWithInvalidIdThrowsNotFoundExceptionTest() throws Exception {
		MvcResult findByIdResult = deleteTask(0);
		int status = findByIdResult.getResponse().getStatus();
		String content = findByIdResult.getResponse().getContentAsString();
		
		String expectedStatus = "{\"status\":400,";
		String expectedTimeStamp = "\"timeStamp\":";
		String expectedTitle = "\"title\":\"Task with id 0 doesn't exists\"";
		
		assertEquals(400, status);
		assertTrue(content.contains(expectedStatus));
		assertTrue(content.contains(expectedTimeStamp));
		assertTrue(content.contains(expectedTitle));
	}

	private MvcResult findById(String uri) throws Exception {
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		return mvcResult;
	}

	private MvcResult updateTask(Task task, long id) throws JsonProcessingException, Exception {
		String uri = "/api/task/" + id;
		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		return mvcResult;
	}

	private MvcResult deleteTask(long id) throws Exception {
		String uri = "/api/task/" + id;
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		return mvcResult;
	}

	private Task getTaskEntityFromResult(MvcResult mvcResult)
			throws JsonParseException, JsonMappingException, IOException {
		String content = mvcResult.getResponse().getContentAsString();
		Task taskResponse = super.mapFromJson(content, Task.class);
		return taskResponse;
	}

	private MvcResult CreateTask(String title, boolean completed) throws JsonProcessingException, Exception {
		String uri = "/api/task";
		Task task = new Task(title, completed);

		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		return mvcResult;
	}

}
