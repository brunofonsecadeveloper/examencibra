package br.com.encibra.encibrateste.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.domain.dto.AuthenticationDTO;
import br.com.encibra.encibrateste.security.JWTUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtil jwtUtil;
	
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
}