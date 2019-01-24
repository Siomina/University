package com.smarterama.semina.university.dao;

@SuppressWarnings("serial")
public class DAOException extends Exception {

	public DAOException() {
		super();
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message) {
		super(message);
	}
}
