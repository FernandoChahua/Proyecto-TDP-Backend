package com.softtech.tdp.controller;




import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.softtech.tdp.dto.ErrorDTO;
import com.softtech.tdp.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ResourceNotFoundException.class,NoSuchElementException.class,ConstraintViolationException.class})
	public final ResponseEntity<ErrorDTO> handleException(Exception ex,WebRequest request) throws Exception{
		HttpStatus status = HttpStatus.NOT_FOUND;
		HttpHeaders headers = new HttpHeaders();
		String statusCode = "000";
		
		if(ex instanceof ResourceNotFoundException || ex instanceof NoSuchElementException) {
			status = HttpStatus.NOT_FOUND;
			statusCode = "404";
		}
		if(ex instanceof ConstraintViolationException) {
			status = HttpStatus.BAD_REQUEST;
			statusCode = "400";
			
		}
		return new ResponseEntity<>(ErrorDTO.builder()
				.code(statusCode)
				.message(ex.getMessage())
				.build(),headers,status);
		
		
	}
}
