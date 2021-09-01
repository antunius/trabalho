package br.edu.utfpr.pb.trabalho.email;


import java.util.Optional;

public interface EmailService {

	void send(Email email, EmailRequest request) throws Exception;

	Optional<Email> findPadrao();
}
