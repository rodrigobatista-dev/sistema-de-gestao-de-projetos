package com.sistemadegestaodeprojetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadegestaodeprojetos.model.Tarefa;

import com.sistemadegestaodeprojetos.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // Verifica se um usuário existe pelo ID
    public boolean isExiste(Long id) {
        return tarefaRepository.existsById(id);
    }

    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    // Atualiza um usuário existente
    public Tarefa atualizar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    public Optional<Tarefa> buscarTarefaPorId(Long id) {
        return tarefaRepository.findById(id);
    }

    // Remove um tarefa pelo ID
    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
