package com.sistemadegestaodeprojetos.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadegestaodeprojetos.dto.TarefaRequestDTO;
import com.sistemadegestaodeprojetos.dto.TarefaResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.TarefaNaoEncontradaException;

import com.sistemadegestaodeprojetos.mapper.TarefaMapper;
import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.model.Tarefa;
import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;
import com.sistemadegestaodeprojetos.repository.TarefaRepository;
import com.sistemadegestaodeprojetos.repository.UsuarioRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    // Verifica se um usuário existe pelo ID
    public boolean isExiste(Long id) {
        return tarefaRepository.existsById(id);
    }

    // criar nova tarefa
    public TarefaResponseDTO criarTarefa(TarefaRequestDTO dto) {

        // Busca os dados relacionados
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Tarefa tarefa = TarefaMapper.toEntity(dto);

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setUsuarioResponsavel(usuario);
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setDataEntrega(LocalDate.now().plusDays(7));
        tarefa.setProjeto(projeto);

        Tarefa salvo = tarefaRepository.save(tarefa);

        return TarefaMapper.toDTO(salvo);
    }

    // Atualiza um tarefa existente
    public TarefaResponseDTO atualizar(Long id, TarefaRequestDTO dto) {
        Tarefa tarefaExistente = buscarOuLancarExcecao(id);

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        tarefaExistente.setTitulo(dto.getTitulo());
        tarefaExistente.setDescricao(dto.getDescricao());
        tarefaExistente.setStatus(dto.getStatus());
        tarefaExistente.setDataEntrega(dto.getDataEntrega());
        tarefaExistente.setUsuarioResponsavel(usuario);
        tarefaExistente.setProjeto(projeto);

        Tarefa atualizadoSalvar = tarefaRepository.save(tarefaExistente);

        return TarefaMapper.toDTO(atualizadoSalvar);
    }

    // Método utilitário para buscar ou lançar exceção
    public Tarefa buscarOuLancarExcecao(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa com ID " + id + " não foi encontrado."));
    }

    // listar tarefas
    public List<TarefaResponseDTO> listarTarefas() {
        return tarefaRepository.findAll().stream()
                .map(TarefaMapper::toDTO).toList();
    }

    // Buscar tarefa
    public TarefaResponseDTO buscarTarefaPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada"));

        return TarefaMapper.toDTO(tarefa);
    }

    // Remove um tarefa pelo ID
    public void excluirTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNaoEncontradaException("Tarefa com ID " + id + " não encontrada");
        }
        tarefaRepository.deleteById(id);
    }

}
