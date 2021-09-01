package br.edu.utfpr.pb.trabalho.usuario;

import lombok.Data;

@Data
public class UsuarioNomeLoginTo {
	private String nome;
	private String username;

	public UsuarioNomeLoginTo(String nome, String username) {
		this.nome = nome;
		this.username = username;
	}
}
