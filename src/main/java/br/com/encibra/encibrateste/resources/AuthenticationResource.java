package br.com.encibra.encibrateste.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.domain.dto.AuthenticationDTO;
import br.com.encibra.encibrateste.domain.dto.RegisterDTO;
import br.com.encibra.encibrateste.repositories.UsuarioRepository;
import br.com.encibra.encibrateste.security.JWTUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;
	
	 @Autowired
	 private UsuarioRepository repository;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data) {

		HttpHeaders headers = new HttpHeaders();

		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token = jwtUtil.generateToken((Usuario) auth.getPrincipal());
		headers.add("Authorization", "Bearer " + token);

		String responseBody = "";

		return new ResponseEntity<>(responseBody, headers, HttpStatus.OK);

	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data){
       if(this.repository.findByNome(data.login()) != null) return ResponseEntity.badRequest().build();

       String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
       Usuario newUser = new Usuario(null, data.login(), encryptedPassword, data.role());

       newUser = this.repository.save(newUser);

       HttpHeaders headers = new HttpHeaders();
       String responseBody = "";
       return new ResponseEntity<>(responseBody, headers, HttpStatus.CREATED);
   }
}