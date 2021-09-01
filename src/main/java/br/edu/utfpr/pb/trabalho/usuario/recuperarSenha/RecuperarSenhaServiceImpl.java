package br.edu.utfpr.pb.trabalho.usuario.recuperarSenha;


import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecuperarSenhaServiceImpl extends CrudServiceImpl<RecuperarSenha, Long> implements RecuperarSenhaService {

    @Autowired private RecuperarSenhaData data;

    @Override
    public Optional<RecuperarSenha> findByChave(String chave) {
        return data.findByChave(chave);
    }

    @Override
    public Optional<RecuperarSenha> findByChaveEmail(String chave) {
        return data.findByChaveEmail(chave);
    }

    @Override
    protected JpaRepository<RecuperarSenha, Long> getRepository() {
        return data;
    }
}
