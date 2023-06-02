package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class InvalidInputExceptionHandler {
	public final ResponseEntity<ExceptionResponse> handleException(InvalidInputException iiex, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(new Date(), iiex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exResponse,HttpStatus.NOT_ACCEPTABLE);
	}
}
