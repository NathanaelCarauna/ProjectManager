package com.carauna.projectmanager.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.carauna.projectmanager.domain.model.Task;
import com.carauna.projectmanager.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.ServletException;

class TaskServiceImplTest {

	private CrudService taskCrudService;
	List<Task> taskList;
	
	@BeforeEach
	void tearUp() {
		TaskRepository taskRepository = mock(TaskRepository.class);
		taskList = new ArrayList<>();
		Task taskListTest = new Task("listTest", true);
		Task taskId = new Task("findByIdTest", false);
		Task taskCreate = new Task("createTest", false);
		taskList.add(taskListTest);
		taskList.add(taskId);
		taskList.add(taskCreate);
		when(taskRepository.findAll()).thenReturn(taskList);
		when(taskRepository.findById(1l)).thenReturn(Optional.of(taskId));
		when(taskRepository.existsById(1l)).thenReturn(true);
		when(taskRepository.existsById(0l)).thenReturn(false);
		when(taskRepository.save(any(Task.class))).thenReturn(taskCreate);
		taskCrudService = new TaskServiceImpl(taskRepository);
	}
	
	@Test
	void findAllTest() {
		assertEquals("listTest", taskCrudService.findAll().get(0).getTitle());
	}
	
	@Test
	void findByIdTest() {
		assertEquals("findByIdTest", taskCrudService.findById(1l).getTitle());
	}
	
	@Test
	void createTest() {
		assertEquals("createTest", taskCrudService.create(new Task("createTest", false)).getTitle());
	}
	
	@Test
	void updateTest() {
		assertEquals("createTest", taskCrudService.update(1, new Task("createTest", false)).getTitle());
	}
	
	@Test
	void updateNotFoundTest() {
		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			taskCrudService.update(0, new Task("createTest", false));
		});		
	}
	
	@Test
	void deleteTest() {
		taskCrudService.deleteById(1);
	}
	
	@Test
	void deleteNotFoundTest() {
		Exception exception = assertThrows(EntityNotFoundException.class, () -> {
			taskCrudService.deleteById(0);
		});
	}

}
