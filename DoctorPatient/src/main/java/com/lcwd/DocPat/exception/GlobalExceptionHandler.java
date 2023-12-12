package com.lcwd.DocPat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lcwd.DocPat.utils.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlerResourceNotFoundException(ResourseNotFoundException ex) {
		ErrorDetails error = new ErrorDetails();
		error.setMessage(ex.getMessage());
		error.setStatus(HttpStatus.NOT_FOUND);
		error.setSuccess(true);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
