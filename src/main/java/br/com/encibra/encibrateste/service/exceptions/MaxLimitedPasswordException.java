package br.com.encibra.encibrateste.service.exceptions;

public class MaxLimitedPasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MaxLimitedPasswordException(String msg) {
		super(msg);
	}
	
	public MaxLimitedPasswordException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

