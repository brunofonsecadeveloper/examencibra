package br.com.encibra.encibrateste.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.encibra.encibrateste.domain.Usuario;

public class UserService {
	
	public static Usuario authenticated() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}