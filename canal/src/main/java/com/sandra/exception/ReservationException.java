package com.sandra.exception;

public class ReservationException extends Exception {

	
	public ReservationException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
	public ReservationException(String errorMessage) {
	    super(errorMessage);
	}
	
}
