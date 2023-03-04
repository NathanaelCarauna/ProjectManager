package com.carauna.projectmanager.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carauna.projectmanager.domain.model.Task;
import com.carauna.projectmanager.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskServiceImpl implements CrudService {

	private TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	@Override
	public Task findById(long id) {
		return taskRepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}
	
	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}
	
	@Override
	public void deleteById(long id) {
		taskRepository.deleteById(id);
	}
}
