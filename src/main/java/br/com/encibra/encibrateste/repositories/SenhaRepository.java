package br.com.encibra.encibrateste.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.encibra.encibrateste.domain.Senha;
import br.com.encibra.encibrateste.domain.Usuario;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Integer> {

	List<Senha> findByUsuario(Usuario usuario);
}
