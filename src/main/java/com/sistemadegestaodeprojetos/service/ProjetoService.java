package com.sistemadegestaodeprojetos.service;

import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    // Verifica se um projeto existe pelo ID
    public boolean isExiste(Long id) {
        return projetoRepository.existsById(id);
    }

    // Cria um novo projeto
    public Projeto criarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Atualiza um projeto existente
    public Projeto atualizarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Lista todos os projetos
    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    // Busca um projeto por ID
    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }

    // Exclui um projeto por ID
    public void excluirProjeto(Long id) {
        projetoRepository.deleteById(id);
    }
}
