package br.edu.utfpr.pb.trabalho.usuario;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("cadastro")
	public String cadastro() {
		return "cadastro";
	}

	@GetMapping("/recuperar-senha")
	public String recuperarSenha() {
		return "recuperar-senha";
	}
}
