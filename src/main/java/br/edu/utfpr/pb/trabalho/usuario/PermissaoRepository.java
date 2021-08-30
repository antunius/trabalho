package br.edu.utfpr.pb.trabalho.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissaoRepository extends JpaRepository<Permissao, Integer> {
	Optional<Permissao> findByNome(String s);
}
