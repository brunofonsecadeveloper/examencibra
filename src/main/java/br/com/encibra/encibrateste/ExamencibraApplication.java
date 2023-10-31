package br.com.encibra.encibrateste;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.Usuario;
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
		Usuario usuario = new Usuario(null, "Pessoa A");
		usuarioRepository.save(usuario);
		
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(secret);
		Senha senha = new Senha(null, "Descrição A", "Tags A, Tags B, Tags C", encryptor.encrypt("123456"), usuario);
		senhaRepository.save(senha);		
	}

}
