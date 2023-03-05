package com.carauna.projectmanager.domain.model;

import java.time.OffsetDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private OffsetDateTime creationDateTime;
	private OffsetDateTime lastModifiedDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public OffsetDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(OffsetDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public OffsetDateTime getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(OffsetDateTime lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

}
