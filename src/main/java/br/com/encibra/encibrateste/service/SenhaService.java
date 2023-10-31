package br.com.encibra.encibrateste.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.repositories.SenhaRepository;

@Service
public class SenhaService {

	@Autowired
	private SenhaRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	public List<Senha> findAll() {
		return repo.findAll();
	}
	
	public Senha findById(Integer id) {
		Senha senha = repo.findById(id).orElse(null);
		return senha;
	}
	
	public Senha insert(Senha senha) {
		senha = repo.save(senha);
		return senha;
	}
	
	public Senha update(Senha newSenha) {
		Senha senha = findById(newSenha.getId());
		newSenha.setUsuario(usuarioService.findById(senha.getUsuario().getId()));
		return repo.save(newSenha);
	}

	public void delete(Integer id) {
		findById(id);
		repo.deleteById(id);
	}
	
}