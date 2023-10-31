package br.com.encibra.encibrateste;

import java.util.Arrays;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.Usuario;
import br.com.encibra.encibrateste.domain.enums.UserRole;
import br.com.encibra.encibrateste.repositories.SenhaRepository;
import br.com.encibra.encibrateste.repositories.UsuarioRepository;

@SpringBootApplication
public class ExamencibraApplication implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private SenhaRepository senhaRepository;
	
	@Value("${jasypt.encryptor.password}")
    private String secret;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamencibraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String encryptedPassword = new BCryptPasswordEncoder().encode("1234567");
		Usuario usuario = new Usuario(null, "Pessoa A", encryptedPassword, UserRole.ADMIN);
		Usuario usuario2 = new Usuario(null, "Pessoa B", encryptedPassword, UserRole.ADMIN);
		usuarioRepository.saveAll(Arrays.asList(usuario,usuario2));
		
		
		
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(secret);
		Senha senha = new Senha(null, "Descrição A", "Tags A, Tags B, Tags C", encryptor.encrypt("1234567"), usuario);
		Senha senha2 = new Senha(null, "Descrição B", "Tags A, Tags B, Tags C", encryptor.encrypt("1234567"), usuario2);
		senhaRepository.saveAll(Arrays.asList(senha, senha2));		
	}

}
