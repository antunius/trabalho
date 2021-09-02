package br.edu.utfpr.pb.trabalho.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsuarioDTO {
	@NotNull
	@NotEmpty
	private String nome;
	@NotNull
	@NotEmpty
	private String email;
	@NotNull
	@NotEmpty
	private String senha;

	public Usuario toModel() {
		return new Usuario(null, nome, email, senha, Collections.emptySet());
	}
}
