package br.edu.utfpr.pb.trabalho.usuario;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long>
	implements UsuarioService, UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	private final PermissaoRepository permissaoRepository;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository) {
		this.usuarioRepository = usuarioRepository;
		this.permissaoRepository = permissaoRepository;
	}

	@Override
	protected JpaRepository<Usuario, Long> getRepository() {
		return usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}

	@Override
	public Usuario save(Usuario entity) {
		usuarioRepository
			.findByUsername(entity.getUsername())
			.ifPresent(usuario -> {
				throw new RuntimeException("Já possui usuario com este username");
			});
		permissaoRepository.findByNome("ROLE_USER")
			.ifPresent(permissao -> {
				entity.setPermissoes(Set.of(permissao));
			});

		return super.save(entity);
	}
}
