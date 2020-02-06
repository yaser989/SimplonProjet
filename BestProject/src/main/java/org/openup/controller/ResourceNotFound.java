package org.openup.controller;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFound extends RuntimeException{
	
	public ResourceNotFound(String msg) {
		super(msg);
	}
	public ResourceNotFound(String msg, Throwable t) {
		super(msg, t);
	}

}
