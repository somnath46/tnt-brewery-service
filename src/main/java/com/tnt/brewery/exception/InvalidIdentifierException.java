package com.tnt.brewery.exception;

public class InvalidIdentifierException extends RuntimeException {

	private static final long serialVersionUID = -3309915321407468637L;
	private final String message;

	public InvalidIdentifierException(Object id) {
		this.message = "Invalid identifier:" + id;
	}

	public InvalidIdentifierException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
