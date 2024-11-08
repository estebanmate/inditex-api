package com.inditex.challenge.application.rest.exception.type;

import com.inditex.challenge.application.rest.resources.ErrorMessages;
import com.inditex.challenge.application.rest.resources.ExceptionCodes;
import lombok.Getter;

import java.io.Serial;

@Getter
public class MissingFieldException extends BaseException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final String field;

	public MissingFieldException(String field) {
		super(ExceptionCodes.MISSING_FIELD, ErrorMessages.MISSING_FIELD_ERROR_MESSAGE);
		this.field = field;
	}

}
