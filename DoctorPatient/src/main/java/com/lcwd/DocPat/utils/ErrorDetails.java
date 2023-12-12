package com.lcwd.DocPat.utils;

import org.springframework.http.HttpStatus;

import lombok.Builder;

@Builder
public class ErrorDetails {
	 private String message;
	    private boolean success;
	    private HttpStatus status;
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public HttpStatus getStatus() {
			return status;
		}
		public void setStatus(HttpStatus status) {
			this.status = status;
		}
	    
	    
}
