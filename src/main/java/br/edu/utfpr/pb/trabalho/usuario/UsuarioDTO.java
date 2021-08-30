package br.edu.utfpr.pb.trabalho.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioDTO {
	private String nome;
	private String email;
	private String senha;

	public Usuario toModel() {
		return new Usuario(null, nome, email, senha, Collections.emptySet());
	}
}
