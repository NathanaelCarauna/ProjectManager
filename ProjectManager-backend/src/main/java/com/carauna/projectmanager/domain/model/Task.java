package com.carauna.projectmanager.domain.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task extends AbstractEntity {

	@NotBlank
	@Size(max = 100, min = 3)
	private String title;

	private boolean completed;

	public Task() {
	}

	public Task(String title, boolean completed) {
		super();
		this.title = title;
		this.completed = completed;
	}

	public Task(long id, String title, boolean completed) {
		this.setId(id);
		this.title = title;
		this.completed = completed;
	}

	public String getTitle() {
		return title;
	}

	public boolean isCompleted() {
		return completed;
	}

	@Override
	public String toString() {
		return "Task [id=" + getId() + ", title=" + title + ", completed=" + completed + "]";
	}

}
