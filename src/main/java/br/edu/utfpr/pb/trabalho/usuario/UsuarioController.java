package br.edu.utfpr.pb.trabalho.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioController {


	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/adicionar/")
	@ResponseStatus(HttpStatus.CREATED)
	public void adicionarUsuario(Model model, @RequestBody UsuarioDTO usuarioDTO) {
		System.out.println(usuarioDTO);
		usuarioService.save(usuarioDTO.toModel());
	}
}
