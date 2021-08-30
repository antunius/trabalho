package br.edu.utfpr.pb.trabalho.produto;

import br.edu.utfpr.pb.trabalho.crud.CrudService;

import java.util.Set;

public interface ProdutoService extends CrudService<Produto, Long> {
	Set<Produto> buscarUltimosProdutosAdicionados();
}
