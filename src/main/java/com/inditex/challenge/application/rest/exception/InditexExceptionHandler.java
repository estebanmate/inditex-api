package com.inditex.challenge.application.rest.exception;

import com.inditex.challenge.application.rest.exception.type.DataNotFoundException;
import com.inditex.challenge.application.rest.exception.type.InvalidFieldException;
import com.inditex.challenge.application.rest.exception.type.MissingFieldException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InditexExceptionHandler extends ResponseEntityExceptionHandler {

  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ExceptionHandler(DataNotFoundException.class)
  @ResponseBody
  public ExceptionView handlerError(DataNotFoundException exception) {
    final ExceptionView view = new ExceptionView(exception);
    view.addDetails("description", exception.getDescription());
    return view;
  }


  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MissingFieldException.class)
  @ResponseBody
  public ExceptionView handlerError(MissingFieldException exception) {
    final ExceptionView view = new ExceptionView(exception);
    view.addDetails("field", exception.getField());
    return view;
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(InvalidFieldException.class)
  @ResponseBody
  public ExceptionView handlerError(InvalidFieldException exception) {
    final ExceptionView view = new ExceptionView(exception);
    view.addDetails("field", exception.getField());
    return view;
  }

}
