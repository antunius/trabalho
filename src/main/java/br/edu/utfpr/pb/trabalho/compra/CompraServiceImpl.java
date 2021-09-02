package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CompraServiceImpl extends CrudServiceImpl<Compra, Long> implements CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Override
	protected JpaRepository<Compra, Long> getRepository() {
		return compraRepository;
	}

	@Override
	public Set<Compra> comprasbyUsuario(Long id) {
		return compraRepository.findCompraByUsuarioId(id);
	}
}
