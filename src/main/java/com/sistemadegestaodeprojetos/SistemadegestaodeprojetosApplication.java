package com.sistemadegestaodeprojetos;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.sistemadegestaodeprojetos.dto.ProjetoRequestDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioRequestDTO;
import com.sistemadegestaodeprojetos.enums.StatusProjeto;
import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;
import com.sistemadegestaodeprojetos.repository.UsuarioRepository;

@SpringBootApplication
@EntityScan(basePackages = "com.sistemadegestaodeprojetos.model")
public class SistemadegestaodeprojetosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemadegestaodeprojetosApplication.class, args);
	}

	// @Primary
	// @Bean
	public CommandLineRunner inserindoUsuarios(UsuarioRepository repositorioUsuario,
			ProjetoRepository projetoRepositorio) {
		return (args) -> {
			for (int i = 1; i <= 5; i++) {
				UsuarioRequestDTO dto = new UsuarioRequestDTO();
				dto.setNome("Rodrigo " + i);
				dto.setEmail("rodrigo" + i + "@rodrigo.com");
				dto.setSenha("Senha@123" + i);
				dto.setCpf("11111111100" + i);

				Usuario usuario = new Usuario();
				usuario.setNome(dto.getNome());
				usuario.setEmail(dto.getEmail());
				usuario.setSenha(dto.getSenha());
				usuario.setCpf(dto.getCpf());

				usuario = repositorioUsuario.save(usuario);
				System.out.println("Usuário salvo com ID: " + usuario.getId());
			}

			for (int i = 1; i <= 5; i++) {
				ProjetoRequestDTO dto = new ProjetoRequestDTO();
				dto.setTitulo("Projeto " + i);
				dto.setDescricao("descricao do projeto" + i);
				dto.setStatus(StatusProjeto.EM_ANDAMENTO);
				dto.setUsuarioId((long) i);

				Optional<Usuario> usuarioOpt = repositorioUsuario.findById(dto.getUsuarioId());
				if (usuarioOpt.isPresent()) {
					Projeto projeto = new Projeto();
					projeto.setTitulo(dto.getTitulo());
					projeto.setDescricao(dto.getDescricao());
					projeto.setStatus(dto.getStatus());
					projeto.setUsuario(usuarioOpt.get());

					projetoRepositorio.save(projeto);
					System.out.println("Projeto salvo com sucesso para usuário ID: " + dto.getUsuarioId());

				} else {
					System.out.println("Usuário com ID " + dto.getUsuarioId() + " não encontrado");
				}

			}

		};
	}

}
