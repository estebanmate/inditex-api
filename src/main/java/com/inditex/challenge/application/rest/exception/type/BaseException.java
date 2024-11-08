package com.inditex.challenge.application.rest.exception.type;

import java.io.Serial;
import lombok.Getter;

@Getter
public abstract class BaseException extends Exception {
	@Serial
	private static final long serialVersionUID = 1L;

	protected final String message;
	protected final int errorCode;

	protected BaseException(int errorCode, String message) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

}
