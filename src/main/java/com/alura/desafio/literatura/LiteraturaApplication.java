package com.alura.desafio.literatura;

import com.alura.desafio.literatura.principal.Principal;
import com.alura.desafio.literatura.repository.AutoresRepository;
import com.alura.desafio.literatura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication  implements CommandLineRunner {

	@Autowired
	private LibrosRepository repository;
	@Autowired
	private AutoresRepository autoresRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,autoresRepository);
		principal.muestraElMenu();
	}
}
