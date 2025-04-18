package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.model.Tarefa;
import com.sistemadegestaodeprojetos.service.TarefaService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    // Criar nova tarefa
    @PostMapping("criartarefa")
    public ResponseEntity<?> criarTarefa(@RequestBody @Valid Tarefa tarefa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> erros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                erros.add(erro.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(erros);
        }

        try {
            tarefa = tarefaService.criarTarefa(tarefa);
            return ResponseEntity.ok(tarefa);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar tarefa: " + e.getMessage());
        }

    }

    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    // Buscar tarefa por ID
    @GetMapping("/busacarpor/{id}")
    public ResponseEntity<Tarefa> buscarTarefa(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarTarefaPorId(id);
        return tarefa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
    @PutMapping("/atulizar")
    public ResponseEntity<?> atualizarTarefa(@RequestBody @Valid Tarefa tarefa, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());

            }
            return ResponseEntity.badRequest().body(listaDeErros);

        }

        try {
            if (tarefaService.isExiste(tarefa.getId())) {
                tarefa = tarefaService.atualizar(tarefa);
                return ResponseEntity.ok(tarefa);
            } else {
                return ResponseEntity.badRequest().body("O ID informado não se encontra cadastrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar tarefa");
        }
    }
}
