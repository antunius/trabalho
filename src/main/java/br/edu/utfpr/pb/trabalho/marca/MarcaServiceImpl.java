package br.edu.utfpr.pb.trabalho.marca;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl extends CrudServiceImpl<Marca, Long> implements MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	protected JpaRepository<Marca, Long> getRepository() {
		return marcaRepository;
	}

}
