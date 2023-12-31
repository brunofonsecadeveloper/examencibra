package br.com.encibra.encibrateste.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.dto.SenhaNewDTO;
import br.com.encibra.encibrateste.domain.dto.SenhaUpdateDTO;
import br.com.encibra.encibrateste.service.SenhaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/senhas")
public class SenhaResource {
	
	@Autowired
	private SenhaService service;
	
	@GetMapping
	public ResponseEntity<List<Senha>> findAll() {
		List<Senha> list = service.findByUsuario();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Senha> find(@PathVariable Integer id) {
		Senha obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody SenhaNewDTO senhaNewDto) {
		
		Senha senha = service.fromNewDTO(senhaNewDto);
		senha = service.insert(senha);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(senha.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody SenhaUpdateDTO senhaUpdateDto, @PathVariable Integer id) {
		Senha senha = service.fromDTO(senhaUpdateDto);
		senha.setId(id);
		senha = service.update(senha);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
