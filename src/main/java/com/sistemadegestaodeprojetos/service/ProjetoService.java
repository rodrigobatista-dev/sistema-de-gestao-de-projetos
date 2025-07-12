package com.sistemadegestaodeprojetos.service;

import com.sistemadegestaodeprojetos.dto.ProjetoRequestDTO;
import com.sistemadegestaodeprojetos.dto.ProjetoResponseDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.ProjetoNaoEncontradaException;
import com.sistemadegestaodeprojetos.exceptionerros.UsuarioNaoEncontradoException;
import com.sistemadegestaodeprojetos.mapper.ProjetoMapper;
import com.sistemadegestaodeprojetos.mapper.UsuarioMapper;
import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;
import com.sistemadegestaodeprojetos.repository.UsuarioRepository; // Adicionado para o UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Declaração do UsuarioRepository

    // Verifica se um projeto existe pelo ID
    public boolean isExiste(Long id) {
        return projetoRepository.existsById(id);
    }

    // Cria um novo projeto
    public ProjetoResponseDTO criarProjeto(ProjetoRequestDTO dto) {
        // Buscando o usuário pelo ID passado no DTO
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()) // <--- Alterado para usar o UsuarioRepository
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuário com ID " + dto.getUsuarioId() + " não encontrado"));

        // Convertendo o DTO para a entidade Projeto
        Projeto projeto = ProjetoMapper.toEntity(dto);

        // Associando o usuário ao projeto
        projeto.setUsuario(usuario);

        // Salvando o projeto
        Projeto salvo = projetoRepository.save(projeto);

        // Retornando a resposta com os dados do projeto criado
        return ProjetoMapper.toDTO(salvo);
    }

    public ProjetoResponseDTO atualizarProjeto(Long id, ProjetoRequestDTO dto) {
        Projeto projetoExistente = buscarOuLancarExcecao(id);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        projetoExistente.setTitulo(dto.getTitulo());
        projetoExistente.setDescricao(dto.getDescricao());
        projetoExistente.setStatus(dto.getStatus());
        projetoExistente.setUsuario(usuario);

        Projeto atualizadoSalvar = projetoRepository.save(projetoExistente);

        UsuarioResponseDTO usuarioDTO = UsuarioMapper.toDTO(atualizadoSalvar.getUsuario());

        return new ProjetoResponseDTO(
                atualizadoSalvar.getId(),
                usuarioDTO,
                atualizadoSalvar.getTitulo(),
                atualizadoSalvar.getDescricao(),
                atualizadoSalvar.getDataCriacao(),
                atualizadoSalvar.getStatus());
    }

    // Método utilitário para buscar ou lançar exceção caso o projeto não exista
    public Projeto buscarOuLancarExcecao(Long id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new ProjetoNaoEncontradaException("Projeto com ID " + id + " não encontrado."));
    }

    // Lista todos os projetos
    public List<ProjetoResponseDTO> listarProjetos() {
        return projetoRepository.findAll().stream()
                .map(ProjetoMapper::toDTO).toList();
    }

    // Busca um projeto por ID
    public ProjetoResponseDTO buscarProjetoPorId(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new ProjetoNaoEncontradaException("Projeto com ID " + id + " não encontrado"));

        return ProjetoMapper.toDTO(projeto); // Convertendo a entidade Projeto para DTO
    }

    // Exclui um projeto por ID
    public void excluirProjeto(Long id) {
        projetoRepository.deleteById(id);
    }
}
