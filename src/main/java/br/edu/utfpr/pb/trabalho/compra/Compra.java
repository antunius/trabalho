package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.fornecedor.Fornecedor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nota_fiscal", nullable = false)
	private String notaFiscal;

	@Column(nullable = false)
	private LocalDate data;

	@Column(name = "observacoes", length = 2048)
	private String observacoes;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id.compra")
	private List<CompraProduto> compraProdutos;

	public double getValorTotal() {
		return compraProdutos.stream()
			.map(cp -> cp.getValor() * cp.getQuantidade())
			.mapToDouble(Double::doubleValue).sum();
	}

	public Compra(List<CompraProduto> compraProdutos) {
		this.notaFiscal = UUID.randomUUID().toString();
		this.data = LocalDate.now();
		this.observacoes = "";

		this.compraProdutos = compraProdutos;
	}
}
