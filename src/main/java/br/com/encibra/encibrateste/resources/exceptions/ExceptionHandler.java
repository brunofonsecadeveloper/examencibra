package br.com.encibra.encibrateste.resources.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ExceptionHandler {
	
	protected String recebeMessage(ConstraintViolationException e) {
		
		String msg = "";
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
	        String mensagemInterpolada = violation.getMessage();
	        msg  = mensagemInterpolada;
	    }
		
		return msg;
	}
	
	protected String recebePath(ConstraintViolationException e) {
		
		String path = "";
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
	        path = violation.getPropertyPath().toString();
	    }
		
		return path;
	}

}
