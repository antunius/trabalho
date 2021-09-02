package br.edu.utfpr.pb.trabalho.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	Set<Compra> findCompraByUsuarioId(Long id);
}
