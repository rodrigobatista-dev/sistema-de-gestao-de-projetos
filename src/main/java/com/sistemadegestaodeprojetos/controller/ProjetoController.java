package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.dto.ProjetoRequestDTO;
import com.sistemadegestaodeprojetos.dto.ProjetoResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.ProjetoNaoEncontradaException;

import com.sistemadegestaodeprojetos.service.ProjetoService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @PostMapping("/criarprojeto")
    public ResponseEntity<?> criarProjeto(@RequestBody @Valid ProjetoRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            ProjetoResponseDTO projeto = projetoService.criarProjeto(dto);
            return ResponseEntity.ok(projeto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar projeto: " + e.getMessage());
        }
    }

    @GetMapping("/listarprojeto")
    public ResponseEntity<List<ProjetoResponseDTO>> listarProjetos() {
        return ResponseEntity.ok(projetoService.listarProjetos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarProjeto(@PathVariable Long id) {
        try {
            ProjetoResponseDTO projeto = projetoService.buscarProjetoPorId(id);
            return ResponseEntity.ok(projeto);
        } catch (ProjetoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
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
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProjeto(@PathVariable Long id, @RequestBody @Valid ProjetoRequestDTO dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listarErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listarErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listarErros);
        }

        try {
            ProjetoResponseDTO projetoAtualizar = projetoService.atualizarProjeto(id, dto);
            return ResponseEntity.ok(projetoAtualizar);

        } catch (ProjetoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}