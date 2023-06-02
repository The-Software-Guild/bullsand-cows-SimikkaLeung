package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class NotFoundExceptionHandler {
	public final ResponseEntity<ExceptionResponse> handleException(NotFoundException nfex, WebRequest request) {
		ExceptionResponse exResponse = new ExceptionResponse(new Date(), nfex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ExceptionResponse>(exResponse,HttpStatus.NOT_FOUND);
	}
}
