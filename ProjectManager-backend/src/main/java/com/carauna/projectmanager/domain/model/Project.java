package com.carauna.projectmanager.domain.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Project extends AbstractEntity {

	private String name;

	@OneToMany
	private List<Task> tasks;
	private String description;

	public Project() {
	}

	public Project(String name, List<Task> tasks, String description) {
		super();
		this.name = name;
		this.tasks = tasks;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", tasks=" + tasks + ", description=" + description + ", getId()=" + getId()
				+ ", getCreationDateTime()=" + getCreationDateTime() + ", getLastModifiedDateTime()="
				+ getLastModifiedDateTime() + "]";
	}

}
