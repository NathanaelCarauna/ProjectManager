package com.carauna.projectmanager.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.carauna.projectmanager.domain.model.Task;

public interface CrudController {

	List<Task> findAll();

	Task findById(long id);

	Task create(Task task);

	Task update(Task task);

	void deleteById(long id);

}