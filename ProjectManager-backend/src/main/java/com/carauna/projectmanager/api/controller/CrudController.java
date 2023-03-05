package com.carauna.projectmanager.api.controller;

import java.util.List;

import com.carauna.projectmanager.domain.model.Task;

public interface CrudController {

	List<Task> findAll();

	Task findById(long id);

	Task create(Task task);

	Task update(long id, Task task);

	void deleteById(long id);

}