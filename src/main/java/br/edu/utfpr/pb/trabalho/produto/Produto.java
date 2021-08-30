package br.edu.utfpr.pb.trabalho.produto;

import br.edu.utfpr.pb.trabalho.categoria.Categoria;
import br.edu.utfpr.pb.trabalho.marca.Marca;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 150, nullable = false)
	private String nome;

	@Column(length = 1024, nullable = false)
	private String descricao;

	@Column(nullable = false)
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "marca_id", referencedColumnName = "id")
	private Marca marca;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "produto")
	private List<ImagemProduto> imagemProdutos;

	private LocalDateTime dataCriacao;

	public Double getValorParcelado() {
		return valor / 12.0;
	}

	public String getImagem() {
		if (!imagemProdutos.isEmpty()) {
			return imagemProdutos.get(0).getNome();
		} else {
			return "";
		}
	}

	public Produto(Long id) {
		this.id = id;
	}

	public Produto(String nome, String descricao, Double valor, Categoria categoria, Marca marca, List<ImagemProduto> imagemProdutos) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
		this.marca = marca;
		this.imagemProdutos = imagemProdutos;
		this.dataCriacao = LocalDateTime.now();
	}
}
