package com.inditex.challenge.application.rest.exception.type;

import com.inditex.challenge.application.rest.resources.ErrorMessages;
import com.inditex.challenge.application.rest.resources.ExceptionCodes;
import lombok.Getter;

import java.io.Serial;

@Getter
public class DataNotFoundException extends BaseException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final String description;

	public DataNotFoundException(String description) {
		super(ExceptionCodes.DATA_NOT_FOUND, ErrorMessages.NOT_FOUND_ERROR_MESSAGE);
		this.description = description;
	}

}
