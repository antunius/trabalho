package br.edu.utfpr.pb.trabalho.fornecedor;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class FornecedorServiceImpl extends CrudServiceImpl<Fornecedor, Long> implements FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	protected JpaRepository<Fornecedor, Long> getRepository() {
		return fornecedorRepository;
	}

}
