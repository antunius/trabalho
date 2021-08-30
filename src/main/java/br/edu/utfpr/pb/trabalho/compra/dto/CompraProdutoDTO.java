package br.edu.utfpr.pb.trabalho.compra.dto;

import br.edu.utfpr.pb.trabalho.compra.CompraProduto;
import br.edu.utfpr.pb.trabalho.compra.CompraProdutoPK;
import br.edu.utfpr.pb.trabalho.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

class ProdutoDTO {
	public Long id;
	public String nome;
	public String valor;
	public String img;

	public Produto toModel() {
		return new Produto(id);
	}
}

@AllArgsConstructor
@NoArgsConstructor
public class CompraProdutoDTO {
	public ProdutoDTO produto;
	public int quantidade;
	public Double valor;

	public CompraProduto toModel() {
		return new CompraProduto(new CompraProdutoPK(null, produto.toModel()), quantidade, valor);
	}
}


