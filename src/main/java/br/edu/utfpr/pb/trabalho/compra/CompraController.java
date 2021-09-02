package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.compra.dto.CompraProdutoDTO;
import br.edu.utfpr.pb.trabalho.produto.ProdutoService;
import br.edu.utfpr.pb.trabalho.usuario.Usuario;
import br.edu.utfpr.pb.trabalho.usuario.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("compra")
public class CompraController {

	private final CompraService compraService;

	public CompraController( CompraService compraService) {

		this.compraService = compraService;
	}


	@GetMapping
	public String getCompras(Model model,@AuthenticationPrincipal Usuario usuario) {

		model.addAttribute("compras", compraService.comprasbyUsuario(usuario.getId()));
		return "compras";
	}

	@PostMapping("/finalizar")
	@ResponseStatus(HttpStatus.CREATED)
	public void finalizarCompra(Model model,
	                            @RequestBody List<CompraProdutoDTO> compraProdutosDTO,
	                            @AuthenticationPrincipal Usuario usuario) {
		Compra compra = new Compra(Collections.emptyList());
		compra = compraService.save(compra);
		Compra finalCompra = compra;
		var compraProdutos = compraProdutosDTO
			.stream()
			.map(CompraProdutoDTO::toModel)
			.peek(compraProduto -> compraProduto.getId().setCompra(finalCompra))
			.collect(Collectors.toList());

		compra.setCompraProdutos(compraProdutos);
		compra.setUsuarioId(usuario.getId());
		compraService.save(compra);
	}
}
