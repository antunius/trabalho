package br.edu.utfpr.pb.trabalho.produto;

import br.edu.utfpr.pb.trabalho.categoria.CategoriaService;
import br.edu.utfpr.pb.trabalho.marca.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Controller
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private MarcaService marcaService;

	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("produtos", produtoService.findAll());
		return "produto/list";
	}
	
	@GetMapping("/{id}")
	public String pegarProduto(Model model, @PathVariable String id) {
		model.addAttribute("produto", produtoService.findOne(Long.parseLong(id)));
		return "interna";
	}

	private void carregarCombos(Model model) {
		model.addAttribute("categorias", categoriaService.findAll());
		model.addAttribute("marcas", marcaService.findAll());
	}

	@DeleteMapping("{id}") // /produto/25
	public String delete(@PathVariable Long id,
						 RedirectAttributes attributes) {
		try {
			produtoService.delete(id);
			attributes.addFlashAttribute("sucesso", 
					"Registro removido com sucesso!");
		} catch (Exception e) {
			attributes.addFlashAttribute("erro", 
					"Falha ao remover o registro!");
		}
		return "redirect:/produto";
	}


	@PostMapping
	public String save(@Valid Produto entity,
					  BindingResult result,
					  Model model,
					  @RequestParam("anexo") MultipartFile anexo,
					  @RequestParam("anexos") MultipartFile[] anexos,
					  HttpServletRequest request,
					  RedirectAttributes attributes) {

		if ( result.hasErrors() ) {
			model.addAttribute("produto", entity);
			return "produto/form";
		}

		produtoService.save(entity);
		return "redirect:/produto";
	}

}
