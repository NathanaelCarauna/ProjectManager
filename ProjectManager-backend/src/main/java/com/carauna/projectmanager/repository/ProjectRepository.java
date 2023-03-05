package com.carauna.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carauna.projectmanager.domain.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
