package com.sistemadegestaodeprojetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.repository.ProjetoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    // Criar um novo projeto
    public Projeto salvarProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Listar todos os projetos
    public List<Projeto> listarProjetos() {
        return projetoRepository.findAll();
    }

    // Buscar um projeto pelo ID
    public Optional<Projeto> buscarProjetoPorId(Long id) {
        return projetoRepository.findById(id);
    }

    // Excluir um projeto pelo ID
    public void excluirProjeto(Long id) {
        projetoRepository.deleteById(id);
    }
}