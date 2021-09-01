package br.edu.utfpr.pb.trabalho.email;


import br.edu.utfpr.pb.trabalho.usuario.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

@Service
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);



	@Override
	public void send(Email email, EmailRequest request) throws Exception {

		email.loadLazy();

		JavaMailSender javaMailSender = getJavaMailSender(email);
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		if (StringUtils.isNotBlank(email.getRemetente())) {
			helper.setFrom(email.getUsername(), email.getRemetente());
		} else {
			helper.setFrom(email.getUsername());
		}

		if (StringUtils.isNotBlank(request.getPara())) {
			helper.setBcc(request.getPara());
		} else {
			helper.setBcc(request.getParaList().toArray(new String[0]));
		}

		helper.setSubject(request.getTitulo());

		helper.setText(request.getConteudo(), true);

		if (request.getFile() != null) {
			helper.addAttachment(request.getFileName(), new ByteArrayResource(request.getFile()));
		}
		logger.info("Iniciando envio de email: {}. Destino: {}", email.getUsername(), message.getAllRecipients());
		javaMailSender.send(message);
		logger.info("Email enviado com sucesso.");
	}

	@Override
	public Optional<Email> findPadrao() {
		var email = new Email(1L,
			"smtp.gmail.com",
			587,
			"nimitz0601@gmail.com",
			System.getenv("password"),
			"nimitz0601@gmail.com",
			Set.of("mail.smtp.ssl.trust=smtp.gmail.com", "mail.smtp.starttls.enable=true", "mail.smtp.auth=true"),
			true);
		return Optional.of(email);
	}

	private JavaMailSender getJavaMailSender(Email email) {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(email.getHost());
		mailSender.setPort(email.getPort());

		mailSender.setUsername(email.getUsername());
		mailSender.setPassword(email.password());
		mailSender.setDefaultEncoding("UTF-8");

		Properties props = mailSender.getJavaMailProperties();

		email.getProperties().forEach(prop -> {
			String[] split = prop.split("=");
			props.put(split[0], split[1]);
		});

		return mailSender;
	}

}
