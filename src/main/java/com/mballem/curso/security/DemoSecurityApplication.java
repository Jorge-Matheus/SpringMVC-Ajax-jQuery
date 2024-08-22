package com.mballem.curso.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.mballem.curso.security.service.EmailService;

@SpringBootApplication
public class DemoSecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(DemoSecurityApplication.class, args);
	}
	
	@Autowired
	JavaMailSender sender;
	
	@Autowired
	EmailService emailService;

	@Override
	public void run(String... args) throws Exception {
		emailService.enviarPedidoDeConfirmacaoDeCadastro("jacob.jacobjazz@gmail.com", "9852pol");
	}
	

}
