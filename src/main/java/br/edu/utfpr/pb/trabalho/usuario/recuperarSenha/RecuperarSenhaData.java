package br.edu.utfpr.pb.trabalho.usuario.recuperarSenha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecuperarSenhaData extends JpaRepository<RecuperarSenha, Long> {

    Optional<RecuperarSenha> findByChave(String chave);

    Optional<RecuperarSenha> findByChaveEmail(String chave);
}
