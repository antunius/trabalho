package br.edu.utfpr.pb.trabalho.produto;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long> implements ProdutoService {

	private final ProdutoRepository produtoRepository;

	public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	protected JpaRepository<Produto, Long> getRepository() {
		return produtoRepository;
	}

	@Override
	public Set<Produto> buscarUltimosProdutosAdicionados() {
		return produtoRepository.buscarUltimosProdutosAdicionados();
	}
}
