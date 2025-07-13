package br.com.ricardo.crudcliente.controller.handlers;

import br.com.ricardo.crudcliente.dto.CustomError;
import br.com.ricardo.crudcliente.dto.ValidationError;
import br.com.ricardo.crudcliente.service.exceptions.DatabaseException;
import br.com.ricardo.crudcliente.service.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(
				Instant.now(),
				status.value(),
				e.getMessage(),
				request.getRequestURI()
		);
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> dataBase(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		CustomError err = new CustomError(
				Instant.now(),
				status.value(),
				e.getMessage(),
				request.getRequestURI()
		);
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(
				Instant.now(),
				status.value(),
				"Dados inválidos",
				request.getRequestURI());

		for (var fieldError : e.getBindingResult().getFieldErrors()) {
			err.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
}
