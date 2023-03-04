package com.carauna.projectmanager.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carauna.projectmanager.domain.model.Task;
import com.carauna.projectmanager.domain.service.CrudService;
import com.carauna.projectmanager.domain.service.TaskServiceImpl;

@RestController
@RequestMapping("/api/task")
public class TaskController implements CrudController {

	private CrudService taskService;
	protected static final Logger parentLogger = LogManager.getLogger();	
	private Logger logger = parentLogger;
	
	
	
	@Autowired
	public TaskController(TaskServiceImpl taskService) {
		this.taskService = taskService;
	}

	@Override
	@GetMapping
	public List<Task> findAll(){
		logger.info("FindAll tasks called");
		return taskService.findAll();
	}
	
	@Override
	@GetMapping("/{id}")
	public Task findById(@PathVariable long id) {
		logger.info(String.format("Find task by id called. Searching for id %s", id));
		return taskService.findById(id);
	}
	
	@Override
	@PostMapping
	public Task create(@RequestBody Task task) {
		logger.info(String.format("Create new task called. Task: %s", task));
		return taskService.create(task);
	}
	
	@Override
	@PutMapping("/{id}")
	public Task update(@PathVariable long id, @RequestBody Task task) {
		logger.info(String.format("Update task called. Task: %s", task));
		return taskService.update(id, task);
	}
	
	@Override
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable long id) {
		logger.info(String.format("Delete task by id called. Id: %s", id));
		taskService.deleteById(id);
	}
}
