package br.com.encibra.encibrateste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.encibra.encibrateste.domain.Senha;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Integer> {

}
