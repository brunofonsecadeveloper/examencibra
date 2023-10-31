package br.com.encibra.encibrateste.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.service.exceptions.ObjectNotFoundException;

public class UserService {
	
	public static Usuario authenticated() {
		try {
			return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	public static void userIsAuthenticated(Usuario user) {
		if(user == null) {
			System.out.println("Criar exceção");
			throw new ObjectNotFoundException("Usuário autenticado não encontrado.");
		}		
	}
}