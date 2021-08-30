package br.edu.utfpr.pb.trabalho.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
}
