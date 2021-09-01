package br.edu.utfpr.pb.trabalho.usuario;

import br.edu.utfpr.pb.trabalho.crud.CrudServiceImpl;
import br.edu.utfpr.pb.trabalho.email.Email;
import br.edu.utfpr.pb.trabalho.email.EmailRequest;
import br.edu.utfpr.pb.trabalho.email.EmailService;
import br.edu.utfpr.pb.trabalho.usuario.recuperarSenha.RecuperarSenha;
import br.edu.utfpr.pb.trabalho.usuario.recuperarSenha.RecuperarSenhaService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl<Usuario, Long>
	implements UsuarioService, UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	private final PermissaoRepository permissaoRepository;
	private final RecuperarSenhaService recuperarSenhaService;

	private final EmailService emailService;

	private final Configuration freemarkerConfiguration;

	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PermissaoRepository permissaoRepository, RecuperarSenhaService recuperarSenhaService, EmailService emailService, Configuration freemarkerConfiguration) {
		this.usuarioRepository = usuarioRepository;
		this.permissaoRepository = permissaoRepository;
		this.recuperarSenhaService = recuperarSenhaService;
		this.emailService = emailService;
		this.freemarkerConfiguration = freemarkerConfiguration;
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
				if (entity.getId() == null) {
					throw new RuntimeException("Já possui usuario com este username");
				}
			});
		permissaoRepository.findByNome("ROLE_USER")
			.ifPresent(permissao -> entity.setPermissoes(Set.of(permissao)));

		return super.save(entity);
	}

	private void recuperarSenha(Usuario usuario) {
		Optional<Email> padrao = emailService.findPadrao();

		if (padrao.isPresent()) {
			try {
				RecuperarSenha recuperarSenha = new RecuperarSenha(usuario);
				recuperarSenhaService.save(recuperarSenha);

				Map<String, Object> params = new HashMap<>();
				params.put("nome", usuario.getNome());
				params.put("codigo", recuperarSenha.getChave());

				Template template = freemarkerConfiguration.getTemplate("alterar-senha.ftl");
				String email = FreeMarkerTemplateUtils.processTemplateIntoString(template, params);

				EmailRequest emailRequest = new EmailRequest();
				emailRequest.setConteudo(email);
				emailRequest.setPara(usuario.getUsername());
				emailRequest.setTitulo("Redefinir senha");

				emailService.send(padrao.get(), emailRequest);

			} catch (Exception e) {
				throw new RuntimeException("Erro ao enviar e-mail");
			}
		} else {
			throw new RuntimeException("Precisa cadastrar uma configuração de e-mail padrão para enviar e-mail ao usuário");
		}
	}

	@Override
	@Transactional
	public void recuperarSenha(String username) {
		usuarioRepository.findByUsername(username).ifPresent(this::recuperarSenha);
	}

	@Override
	public UsuarioNomeLoginTo findUsuarioByChaveEmail(String codigo) {
		Optional<RecuperarSenha> optRecuperarSenha = recuperarSenhaService.findByChaveEmail(codigo);

		return optRecuperarSenha
			.map(RecuperarSenha::getUsuario)
			.map(u -> new UsuarioNomeLoginTo(u.getNome(), u.getUsername()))
			.orElse(null);
	}

	@Override
	public boolean alterarSenhaCodigo(UsuarioSenhaTO usuarioSenhaTO) {

		final var optional = recuperarSenhaService.findByChave(usuarioSenhaTO.getCodigo());

		if (optional.isPresent()) {
			var recuperarSenha = optional.get();
			if (recuperarSenha.getChave().equals(usuarioSenhaTO.getCodigo()) && LocalDateTime.now().isBefore(recuperarSenha.getValidade())) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

				Usuario usuario = findOne(recuperarSenha.getUsuario().getId());
				if (usuario != null) {
					usuario.setPassword(passwordEncoder.encode(usuarioSenhaTO.getNovaSenha()));
					usuarioRepository.save(usuario);
					return true;
				}
			}
		}
		return false;
	}
}
