package br.com.encibra.encibrateste.resources.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.encibra.encibrateste.service.exceptions.MaxLimitedPasswordException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MaxLimitedPasswordException.class)
	public ResponseEntity<StandardError> maxLimitedPasswod(MaxLimitedPasswordException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Limite de senhas cadastradas excedido.", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
}