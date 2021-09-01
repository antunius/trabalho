package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.compra.dto.CompraProdutoDTO;
import br.edu.utfpr.pb.trabalho.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("compra")
public class CompraController {

	private final ProdutoService produtoService;
	private final CompraService compraService;

	public CompraController(ProdutoService produtoService, CompraService compraService) {
		this.produtoService = produtoService;
		this.compraService = compraService;
	}


	@GetMapping
	public String getCompras(Model model) {
		model.addAttribute("compras", compraService.findAll());
		return "compras";
	}

	@PostMapping("/finalizar")
	@ResponseStatus(HttpStatus.CREATED)
	public void finalizarCompra(Model model, @RequestBody List<CompraProdutoDTO> compraProdutosDTO) {
		Compra compra = new Compra(Collections.emptyList());
		compra = compraService.save(compra);
		Compra finalCompra = compra;
		var compraProdutos = compraProdutosDTO
			.stream()
			.map(CompraProdutoDTO::toModel)
			.peek(compraProduto -> compraProduto.getId().setCompra(finalCompra))
			.collect(Collectors.toList());

		compra.setCompraProdutos(compraProdutos);
		compraService.save(compra);
	}
}
