package br.com.encibra.encibrateste.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	public Usuario findById(Integer id) {
		Usuario usuario = repo.findById(id).orElse(null);
		return usuario;
	}
	
}