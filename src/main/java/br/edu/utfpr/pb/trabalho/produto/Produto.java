package br.edu.utfpr.pb.trabalho.produto;

import br.edu.utfpr.pb.trabalho.categoria.Categoria;
import br.edu.utfpr.pb.trabalho.marca.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
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

	@Column(name = "imagem", length = 10)
	private String imagem;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "produto")
	private List<ImagemProduto> imagemProdutos;

	@Lob
	@Column(name = "imagemb64")
	private byte[] imagemB64;

	@Column(name = "extensao_imagemb64", length = 10)
	private String extensaoImagemb64;

	public String getImagemB64() {
		return (this.imagemB64 != null ? new String(this.imagemB64) : null);
	}

	public Double getValorParcelado() {
		return valor / 12.0;
	}

}
