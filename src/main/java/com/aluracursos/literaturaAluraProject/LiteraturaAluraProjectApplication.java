package com.aluracursos.literaturaAluraProject;

import com.aluracursos.literaturaAluraProject.principal.Principal;
import com.aluracursos.literaturaAluraProject.repository.DatosLibrosRepository;
import com.aluracursos.literaturaAluraProject.repository.DatosAutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaAluraProjectApplication implements CommandLineRunner {

	@Autowired
	private DatosLibrosRepository librosRepository;

	@Autowired
	private DatosAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaAluraProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(librosRepository, autorRepository);
		principal.muestraElMenu();
	}
}
