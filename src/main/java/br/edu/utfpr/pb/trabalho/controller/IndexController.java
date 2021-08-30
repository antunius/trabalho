package br.edu.utfpr.pb.trabalho.controller;

import br.edu.utfpr.pb.trabalho.produto.ProdutoService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	private final ProdutoService produtoService;

	public IndexController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("produtos", produtoService.buscarUltimosProdutosAdicionados());
		return "index";
	}

}
