package com.carauna.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carauna.projectmanager.domain.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
