package com.sistemadegestaodeprojetos;

import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.model.Status;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.sistemadegestaodeprojetos.model")
public class SistemadegestaodeprojetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemadegestaodeprojetosApplication.class, args);
	}

	@Bean
	public CommandLineRunner inserirProjetos(ProjetoRepository repository) {
		return (args) -> {
			Projeto p1 = new Projeto();
			p1.setTitulo("Meu Projeto1");
			p1.setNome("Projeto Alpha");
			p1.setDescricao("Primeiro projeto do sistema");
			p1.setStatus(Status.ABERTO);
			repository.save(p1);
			System.out.println("Projeto salvo: " + p1.getId() + " - " + p1.getNome());

			Projeto p2 = new Projeto();
			p2.setTitulo("Meu Projeto2");
			p2.setNome("Projeto Beta");
			p2.setDescricao("Segundo projeto do sistema");
			p2.setStatus(Status.EM_ANDAMENTO);
			repository.save(p2);
			System.out.println("Projeto salvo: " + p2.getId() + " - " + p2.getNome());
		};
	}

}
