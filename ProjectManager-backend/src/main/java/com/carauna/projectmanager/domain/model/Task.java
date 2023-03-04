package com.carauna.projectmanager.domain.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
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
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isCompleted() {
		return completed;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", completed=" + completed + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(id, other.id);
	}

}
