package com.sandra.exception;

public class EquipementUnavailableException extends Exception {

	
	public EquipementUnavailableException(String errorMessage, Throwable err) {
	    super(errorMessage, err);
	}
	
	public EquipementUnavailableException(String errorMessage) {
	    super(errorMessage);
	}
	
}
