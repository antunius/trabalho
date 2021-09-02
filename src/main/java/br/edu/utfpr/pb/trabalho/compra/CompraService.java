package br.edu.utfpr.pb.trabalho.compra;

import br.edu.utfpr.pb.trabalho.crud.CrudService;

import java.util.Set;

public interface CompraService extends CrudService<Compra, Long> {

	Set<Compra> comprasbyUsuario(Long id);
}
