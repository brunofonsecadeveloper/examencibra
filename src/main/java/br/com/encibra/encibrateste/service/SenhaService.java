package br.com.encibra.encibrateste.service;

import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.domain.dto.SenhaNewDTO;
import br.com.encibra.encibrateste.repositories.SenhaRepository;

@Service
public class SenhaService {

	@Autowired
	private SenhaRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private BasicTextEncryptor textEncryptor;
	
	
	public List<Senha> findAll() {
		List<Senha> senhas = repo.findAll();
		for (Senha senha : senhas) {
			senha.setValor(descriptografar(senha.getValor()));
		}
		return repo.findAll();
	}
	
	public Senha findById(Integer id) {
		Senha senha = repo.findById(id).orElse(null);
		return senha;
	}
	
	public Senha insert(Senha senha) {
		senha.setId(null);
		senha.setValor(criptografar(senha.getValor()));
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
	
   public String criptografar(String texto) {
        return textEncryptor.encrypt(texto);
    }
	
    public String descriptografar(String textoCriptografado) {
        return textEncryptor.decrypt(textoCriptografado);
    }
    
    public Senha fromNewDTO(SenhaNewDTO senhaDto) {
		Usuario usuario = usuarioService.findById(senhaDto.getIdUsuario());
		Senha senha = new Senha(null, senhaDto.getDescricao(), senhaDto.getTags(), senhaDto.getValor(), usuario); 
		
		return senha;
	}
	
}