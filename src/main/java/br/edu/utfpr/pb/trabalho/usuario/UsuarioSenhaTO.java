package br.edu.utfpr.pb.trabalho.usuario;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioSenhaTO implements Serializable {

	private String codigo;
	private String novaSenha;

	public UsuarioSenhaTO(String codigo, String novaSenha) {
		this.codigo = codigo;
		this.novaSenha = novaSenha;
	}
}
