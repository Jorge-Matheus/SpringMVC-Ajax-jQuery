package com.mballem.curso.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoSecurityApplication {

	public static void main(String[] args) {
		//System.out.println(new BCryptPasswordEncoder().encode("123456"));
		SpringApplication.run(DemoSecurityApplication.class, args);
	}
	
	//@Autowired
	//JavaMailSender sender;
	
	//@Autowired
	//EmailService emailService;

	//@Override
	//public void run(String... args) throws Exception {
		//emailService.enviarPedidoDeConfirmacaoDeCadastro("jacob.jacobjazz@gmail.com", "9852pol");
	//}
	

}
