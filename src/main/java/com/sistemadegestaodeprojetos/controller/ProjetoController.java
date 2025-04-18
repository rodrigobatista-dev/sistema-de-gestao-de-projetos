package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.service.ProjetoService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/cadastrarprojeto")
    public ResponseEntity<?> criarProjeto(@RequestBody @Valid Projeto projeto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            projeto = projetoService.criarProjeto(projeto);
            return ResponseEntity.ok(projeto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar projeto: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Projeto>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.buscarProjetoPorId(id);
        return projeto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir projeto por ID
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirProjeto(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("O ID seve ser maior que ZERO");

        }
        if (projetoService.isExiste(id)) {
            return ResponseEntity.ok("Projeto resmovido com sucesso");

        } else {
            return ResponseEntity.badRequest().body("ID informado não existe");
        }
    }

    // Atualizar projeto
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarProjeto(@RequestBody @Valid Projeto projeto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listarErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listarErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listarErros);
        }

        try {
            if (projetoService.isExiste(projeto.getId())) {
                projeto = projetoService.atualizarProjeto(projeto);
                return ResponseEntity.ok(projeto);
            } else {
                return ResponseEntity.badRequest().body("O ID informado não se encontra cadastrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar projeto");
        }
    }
}