package com.tnt.brewery.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tnt.brewery.exception.InvalidIdentifierException;
import com.tnt.brewery.web.error.ErrorDto;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<ErrorDto>> handleConstraintViolation(ConstraintViolationException e) {
		logger.debug("ConstraintViolation error occurred: ");
		List<ErrorDto> errorDtos = new ArrayList<>();
		e.getConstraintViolations().forEach(c -> errorDtos.add(ErrorDto.builder().path(c.getPropertyPath().toString())
				.message(c.getMessage()).invalidValue(c.getInvalidValue().toString()).build()));
		return new ResponseEntity<>(errorDtos, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.debug("validation error occurred: ");
		List<ErrorDto> errorDtos = new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(c -> errorDtos.add(ErrorDto.builder().path(c.getField())
				.message(c.getDefaultMessage()).invalidValue(String.valueOf(c.getRejectedValue())).build()));
		return new ResponseEntity<>(errorDtos, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidIdentifierException.class)
	public ResponseEntity<ErrorDto> handleInvalidIdentifierException(InvalidIdentifierException e) {
		logger.error("error occurred: ", e);
		ErrorDto errorDto = ErrorDto.builder().message(e.getMessage()).build();
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDto> handleException(Exception e) {
		logger.error("error occurred: ", e);
		ErrorDto errorDto = ErrorDto.builder()
				.message(e.getMessage() != null ? e.getMessage() : "Internal server error!!").build();
		return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	}

}
