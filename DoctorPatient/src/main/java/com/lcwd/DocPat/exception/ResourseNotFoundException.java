package com.lcwd.DocPat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourseNotFoundException extends RuntimeException {

	public ResourseNotFoundException(String msg) {
		super(msg);
	}
}
