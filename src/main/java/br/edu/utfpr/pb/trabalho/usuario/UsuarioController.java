package br.edu.utfpr.pb.trabalho.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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

	@GetMapping("nova-senha")
	public String novaSenha() {
		return "nova-senha";
	}

	@PostMapping("/recuperar-senha")
	public String recuperarSenha(Model model, @RequestParam String username) {
		usuarioService.recuperarSenha(username);
		return "redirect:nova-senha";
	}

	@PostMapping("/redefinir-senha")
	public String redefinir(@RequestParam String codigo, @RequestParam String senha) {
		usuarioService.alterarSenhaCodigo(new UsuarioSenhaTO(codigo, senha));
		return "redirect:/login";
	}
}
