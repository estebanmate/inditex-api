package com.inditex.challenge.application.rest.exception.type;

import com.inditex.challenge.application.rest.resources.ErrorMessages;
import com.inditex.challenge.application.rest.resources.ExceptionCodes;
import java.io.Serial;
import lombok.Getter;

@Getter
public class InvalidFieldException extends BaseException {

  @Serial
  private static final long serialVersionUID = 1L;

  private final String field;

  public InvalidFieldException(String field) {
    super(ExceptionCodes.INVALID_FIELD_VALUE, ErrorMessages.INVALID_FIELD_ERROR_MESSAGE);
    this.field = field;
  }

}
