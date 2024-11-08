package com.inditex.challenge.application.rest.exception;

import com.inditex.challenge.application.rest.exception.type.BaseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ExceptionView {
	private final int errorCode;
	private final LocalDateTime dateTime;
	private final String description;
	private final Map<String, Object> details;

	public ExceptionView(BaseException exception) {
		this(exception.getErrorCode(), exception.getMessage());
	}

	public ExceptionView(int errorCode, String description) {
		this(errorCode, description, null);
	}

	public ExceptionView(int errorCode, String description,Map<String, Object> details) {
		this.errorCode = errorCode;
		this.dateTime = LocalDateTime.now();
		this.description = description;
		if (details == null) {
			this.details = new HashMap<>();
		} else {
			this.details = details;
		}
	}

	public void addDetails(String key, Object value) {
		details.put(key, value);
	}

}
