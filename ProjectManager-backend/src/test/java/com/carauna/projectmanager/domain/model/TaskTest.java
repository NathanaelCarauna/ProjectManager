package com.carauna.projectmanager.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {

	long expectedNumber = 0;
	boolean expectedBool = true;
	String expectedString = "test";
	Task task;

	@BeforeEach
	void tearUp() {
		task = new Task(expectedString, expectedBool);
	}

	@Test
	void DefaultConstructortest() {
		assertNotNull(new Task());
	}
	
	@Test
	void GetTitletest() {
		assertEquals(expectedString, task.getTitle());
	}
	
	@Test
	void GetCompletedtest() {
		assertEquals(expectedBool, task.isCompleted());
	}
	
	@Test
	void GetIdtest() {
		assertEquals(expectedNumber, task.getId());
	}
	
	@Test
	void GetHashtest() {
		expectedNumber = 31;
		assertEquals(expectedNumber,task.hashCode());
	}
	
	@Test
	void toStringtest() {
		expectedString = "Task [id=0, title=test, completed=true]";
		assertEquals(expectedString, task.toString());
	}
	
	@Test
	void equalsSameIdsTest() {
		Task task2 = new Task(0, "test2", false);
		assertTrue(task.equals(task2));
	}
	
	@Test
	void equalsDiferentIdsTest() {
		Task task2 = new Task(1, "test2", false);
		assertFalse(task.equals(task2));
	}
	
	@Test
	void equalsNullTest() {
		assertFalse(task.equals(null));
	}
	
	@Test
	void equalsSameObjectTest() {
		assertTrue(task.equals(task));
	}
	
	@Test
	void equalsOtherClassTest() {
		assertFalse(task.equals(new Object()));
	}

}
