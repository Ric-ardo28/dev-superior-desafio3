package br.com.ricardo.crudcliente.service.exceptions;

public class DatabaseException extends RuntimeException {
	public DatabaseException(String message) {
		super(message);
	}
}
