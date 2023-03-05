package com.carauna.projectmanager.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectTest {
	
	long expectedNumber = 0;
	boolean expectedBool = true;
	String expectedString = "test";
	Project project;

	@BeforeEach
	void tearUp() {
		project = new Project();
		project.setDescription(expectedString);
		project.setId(expectedNumber);
		project.setName(expectedString);
		ArrayList<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task());
		project.setTasks(tasks);
		project.setCreationDateTime(LocalDateTime.now());
		project.setLastModifiedDateTime(LocalDateTime.now());
	}

	
	@Test
	void GetNametest() {
		assertEquals(expectedString, project.getName());
	}
	
	@Test
	void GetDescriptiontest() {
		assertEquals(expectedString, project.getDescription());
	}
	
	@Test
	void GetIdtest() {
		assertEquals(expectedNumber, project.getId());
	}
	
	@Test
	void GetHashtest() {
		expectedNumber = 31;
		assertEquals(expectedNumber,project.hashCode());
	}
	
	@Test
	void toStringtest() {
		expectedString = "Project [name=test, tasks=[Task [id=0, title=null, completed=false]], description=test, getId()=0";
		assertTrue(project.toString().contains(expectedString));
	}
	
	@Test
	void equalsSameIdsTest() {
		Project project2 = new Project();
		project2.setId(0);
		assertTrue(project.equals(project2));
	}
	
	@Test
	void equalsDiferentIdsTest() {
		Project project2 = new Project();
		project2.setId(1);
		assertFalse(project.equals(project2));
	}
	
	@Test
	void equalsNullTest() {
		assertFalse(project.equals(null));
	}
	
	@Test
	void equalsSameObjectTest() {
		assertTrue(project.equals(project));
	}
	
	@Test
	void equalsOtherClassTest() {
		assertFalse(project.equals(new Object()));
	}

}
