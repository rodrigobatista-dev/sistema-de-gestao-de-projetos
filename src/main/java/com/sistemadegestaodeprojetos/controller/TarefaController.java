package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.dto.TarefaRequestDTO;
import com.sistemadegestaodeprojetos.dto.TarefaResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.TarefaNaoEncontradaException;
import com.sistemadegestaodeprojetos.exceptionerros.UsuarioNaoEncontradoException;
import com.sistemadegestaodeprojetos.service.TarefaService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // Criar nova tarefa
    @PostMapping("criartarefa")
    public ResponseEntity<?> criarTarefa(@RequestBody @Valid TarefaRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> erros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                erros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(erros); // Vai te mostrar os erros do DTO
        }

        try {
            TarefaResponseDTO tarefa = tarefaService.criarTarefa(dto);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar tarefa: " + e.getMessage());
        }
    }

    @GetMapping
    public List<TarefaResponseDTO> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/busacarpor/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarTarefa(@PathVariable Long id) {
        try {
            TarefaResponseDTO tarefa = tarefaService.buscarTarefaPorId(id);
            return ResponseEntity.ok(tarefa);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // excluir tarefa por ID
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirTarefa(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("O ID deverá ser superior a ZERO");
        }

        if (tarefaService.isExiste(id)) {
            tarefaService.excluirTarefa(id);
            return ResponseEntity.ok("Tarefa removida com sucesso");
        } else {
            return ResponseEntity.badRequest().body("ID informado não existe");
        }
    }

    // Atualizar tarefa
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarTarefa(@PathVariable Long id, @RequestBody @Valid TarefaRequestDTO dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            TarefaResponseDTO tarefaAtualizar = tarefaService.atualizar(id, dto);
            return ResponseEntity.ok(tarefaAtualizar);
        } catch (TarefaNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
