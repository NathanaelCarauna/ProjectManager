package com.carauna.projectmanager.api.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.carauna.projectmanager.domain.model.Task;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;

class TaskControllerTest extends AbstractTest {

	@BeforeEach
	void tearUp() {
		this.setUp();
	}

	@Test
	void findAllTest() throws Exception {
		String uri = "/api/task";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task[] taskList = super.mapFromJson(content, Task[].class);
		assertTrue(taskList.length > 0);
	}

	@Test
	void findByIdTest() throws Exception {
		String uri = "/api/task/2";

		Exception exception = assertThrows(ServletException.class, () -> {
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
		});
		String expectedMessage = "Request processing failed: jakarta.persistence.EntityNotFoundException";
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void createTaskTest() throws Exception {
		String uri = "/api/task";
		Task task = new Task("title", true);

		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task taskResponse = super.mapFromJson(content, Task.class);
		assertTrue(taskResponse.getId() > 0);
	}

	@Test
	void updateTaskTest() throws Exception {
		String uri = "/api/task/2";
		Task task = new Task(2, "title2", true);

		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Task taskResponse = super.mapFromJson(content, Task.class);
		assertTrue(taskResponse.getTitle().equals("title2"));
	}

	@Test
	void deleteTaskTest() throws Exception {
		String uri = "/api/task/2";
		Task task = new Task(2, "title2", true);

		String inputJson = super.mapToJson(task);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Task taskResponse = super.mapFromJson(content, Task.class);
//		assertTrue(taskResponse.getTitle().equals("title2"));
	}

}
