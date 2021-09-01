package br.edu.utfpr.pb.trabalho.usuario.recuperarSenha;


import br.edu.utfpr.pb.trabalho.crud.CrudService;

import java.util.Optional;

public interface RecuperarSenhaService extends CrudService<RecuperarSenha, Long> {

    Optional<RecuperarSenha> findByChave(String chave);

    Optional<RecuperarSenha> findByChaveEmail(String chave);
}
