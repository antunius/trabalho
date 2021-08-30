package br.edu.utfpr.pb.trabalho;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrabalhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
	    return new LayoutDialect();
	}
}
