package br.edu.utfpr.pb.trabalho.usuario;

import br.edu.utfpr.pb.trabalho.crud.CrudService;

public interface UsuarioService extends CrudService<Usuario, Long> {

	void recuperarSenha(String userName);

	UsuarioNomeLoginTo findUsuarioByChaveEmail(String codigo);

	boolean alterarSenhaCodigo(UsuarioSenhaTO usuarioSenhaTO);
}
