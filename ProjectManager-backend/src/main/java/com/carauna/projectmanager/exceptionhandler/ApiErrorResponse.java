package com.carauna.projectmanager.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ApiErrorResponse {
	private int status;
	private LocalDateTime timeStamp;
	private String title;
	private List<ErrorResponseField> fields;

	public ApiErrorResponse() {
	}
	
	public ApiErrorResponse(int status, LocalDateTime timeStamp, String title) {
		super();
		this.status = status;
		this.timeStamp = timeStamp;
		this.title = title;
	}

	public ApiErrorResponse(int status, LocalDateTime timeStamp, String title, List<ErrorResponseField> fields) {
		super();
		this.status = status;
		this.timeStamp = timeStamp;
		this.title = title;
		this.fields = fields;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ErrorResponseField> getFields() {
		return fields;
	}

	public void setFields(List<ErrorResponseField> fields) {
		this.fields = fields;
	}
}
