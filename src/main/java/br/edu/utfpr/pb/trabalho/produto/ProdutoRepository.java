package br.edu.utfpr.pb.trabalho.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query(value = "select * from produto p order by p.data_criacao  LIMIT 4", nativeQuery = true)
	Set<Produto> buscarUltimosProdutosAdicionados();
}












