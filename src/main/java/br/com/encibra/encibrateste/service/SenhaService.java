package br.com.encibra.encibrateste.service;

import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.domain.dto.SenhaNewDTO;
import br.com.encibra.encibrateste.domain.dto.SenhaUpdateDTO;
import br.com.encibra.encibrateste.repositories.SenhaRepository;
import br.com.encibra.encibrateste.service.exceptions.MaxLimitedPasswordException;
import br.com.encibra.encibrateste.service.exceptions.ObjectNotFoundException;

@Service
public class SenhaService {

	@Autowired
	private SenhaRepository repo;
	
	@Autowired
    private BasicTextEncryptor textEncryptor;
	
	
	public List<Senha> findAll() {
		List<Senha> senhas = repo.findAll();
		for (Senha senha : senhas) {
			senha.setValor(descriptografar(senha.getValor()));
		}
		return senhas;
	}
	
	public Senha findById(Integer id) {
		Usuario user = UserService.authenticated();
		UserService.userIsAuthenticated(user);
		
		Senha senha = repo.findById(id).orElse(null);
		if(senha.getUsuario().getId() != user.getId()) senha = null;
		if (senha == null) throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Senha.class.getName());
		senha.setValor(descriptografar(senha.getValor()));
		return senha;
	}
	
	public List<Senha> findByUsuario() {
		Usuario user = UserService.authenticated();
		UserService.userIsAuthenticated(user);
		
		List<Senha> senhas = repo.findByUsuario(user);
		for (Senha senha : senhas) {
			senha.setValor(descriptografar(senha.getValor()));
		}
		return senhas;
	}
	
	public Senha insert(Senha senha) {
		
		if(senha.getUsuario()!=null) this.verificaLimiteSenhas(senha.getUsuario());
		senha.setId(null);
		senha.setValor(criptografar(senha.getValor()));
		senha = repo.save(senha);
		return senha;
	}
	
	public Senha update(Senha newSenha) {
		Senha senha = findById(newSenha.getId());
		senha.setDescricao(newSenha.getDescricao());
		senha.setTags(newSenha.getTags());
		senha.setValor(criptografar(newSenha.getValor()));
		return repo.save(senha);
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
    	Usuario user = UserService.authenticated();
		UserService.userIsAuthenticated(user);
		Senha senha = new Senha(null, senhaDto.getDescricao(), senhaDto.getTags(), senhaDto.getValor(), user); 
		
		return senha;
	}
    
	public Senha fromDTO(SenhaUpdateDTO senhaDto) {
		Senha senha = new Senha(null, senhaDto.getDescricao(), senhaDto.getTags(), senhaDto.getValor(), null); 
		
		return senha;
	}
	
	private void verificaLimiteSenhas(Usuario usuario) {
		if(usuario.getSenhas().size()>19) {
			throw new MaxLimitedPasswordException("A quantidade máxima que o usuário pode cadastrar é de 20 senhas. " + usuario.getId()
			+ ", Tipo: " + Senha.class.getName());
		}
	}
	
}