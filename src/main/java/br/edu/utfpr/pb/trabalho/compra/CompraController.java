package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.fornecedor.FornecedorService;
import br.edu.utfpr.pb.trabalho.produto.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("compra")
public class CompraController {

    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;
    private final CompraService compraService;

    public CompraController(ProdutoService produtoService, FornecedorService fornecedorService, CompraService compraService) {
        this.produtoService = produtoService;
        this.fornecedorService = fornecedorService;
        this.compraService = compraService;
    }


    @GetMapping
    public String list(Model model) {
        model.addAttribute("compras", compraService.findAll());
        return "compra/list";
    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("compra", new Compra());
        carregarCombos(model);
        return "compra/form";
    }

    private void carregarCombos(Model model) {
        model.addAttribute("fornecedores", fornecedorService.findAll());
        model.addAttribute("produtos", produtoService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Compra compra,
                                  BindingResult result,
                                  Model model) {
        try {
            if (result.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            compra.getCompraProdutos().forEach(cp -> cp.getId().setCompra(compra));
            compra.setData(LocalDate.now());
            compraService.save(compra);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("compra", compraService.findOne(id));
        carregarCombos(model);
        return "compra/form";
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            compraService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
