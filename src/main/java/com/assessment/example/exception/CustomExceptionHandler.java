package com.assessment.example.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
	private static final String BAD_REQUEST = "BAD_REQUEST";
	
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponse> handleConstraintViolation(
                                            ConstraintViolationException exception,
                                            WebRequest request)
    {
        List<String> errorDetails = exception.getConstraintViolations()
                                    .parallelStream()
                                    .map(ConstraintViolation::getMessage)
                                    .collect(Collectors.toList());
 
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}