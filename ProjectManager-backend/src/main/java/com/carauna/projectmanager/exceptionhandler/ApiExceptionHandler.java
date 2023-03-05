package com.carauna.projectmanager.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;		
		ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), LocalDateTime.now(), ex.getMessage());
		
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		var fields = new ArrayList<ErrorResponseField>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError)error).getField();
			String mensage = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			fields.add(new ErrorResponseField(name, mensage));
		}
		
		String title = "One or more fields are invalid. Correct the fields and try again";
		ApiErrorResponse errorResponse = new ApiErrorResponse(status.value(), LocalDateTime.now(), title, fields);
		
		return super.handleExceptionInternal(ex, errorResponse, headers, status, request);
	}
}
