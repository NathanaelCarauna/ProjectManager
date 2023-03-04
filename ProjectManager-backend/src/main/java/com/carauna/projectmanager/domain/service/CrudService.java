package com.carauna.projectmanager.domain.service;

import java.util.List;

import com.carauna.projectmanager.domain.model.Task;

public interface CrudService {

	List<Task> findAll();

	Task findById(long id);

	Task save(Task task);

	void deleteById(long id);

}