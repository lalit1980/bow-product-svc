package com.boozeonwheel.product.api.file;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;



public class ErrorResponse {

    private final HttpStatus status=null;
    private LocalDateTime timestamp=null;
    private String message;
    private String debugMessage;

    public ErrorResponse(HttpStatus status, Throwable ex) {
        this(status, LocalDateTime.now(), "Unexpected error", ex.getLocalizedMessage());
    }

    public ErrorResponse(HttpStatus status, String message, Throwable ex) {
        this(status, LocalDateTime.now(), message, ex.getLocalizedMessage());
    }

	public ErrorResponse(HttpStatus status2, LocalDateTime now, String message2, String localizedMessage) {
		super();
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public HttpStatus getStatus() {
		return status;
	}

	

}