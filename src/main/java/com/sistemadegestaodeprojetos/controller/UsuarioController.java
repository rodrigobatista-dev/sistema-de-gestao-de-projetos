package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.dto.UsuarioRequestDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.UsuarioNaoEncontradoException;

import com.sistemadegestaodeprojetos.service.UsuarioService;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Criar usuário
    @PostMapping("/criarUsuario")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioRequestDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            if (usuarioService.notExisteCpf(dto.getCpf())) {
                UsuarioResponseDTO usuario = usuarioService.criarUsuario(dto);
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.badRequest().body("O CPF informado já se encontra cadastrado");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Isso vai mostrar o erro real no console
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    // Listar todos os usuários
    @GetMapping("/listar")
    public List<UsuarioResponseDTO> listar() {
        return usuarioService.listarUsuarios();
    }

    // Buscar usuário por ID
    @GetMapping("buscarpor/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuario(@PathVariable Long id) {
        try {
            UsuarioResponseDTO usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Excluir usuário por ID
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("O ID deverá ser superior a ZERO");
        }

        if (usuarioService.isExiste(id)) {
            usuarioService.excluirUsuario(id);
            return ResponseEntity.ok("Usuário removido com sucesso");
        } else {
            return ResponseEntity.badRequest().body("O ID informado não existe");
        }
    }

    // Atualizar usuário
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            UsuarioResponseDTO usuarioAtualizado = usuarioService.atualizar(id, dto); // Atualiza o usuário com DTO
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 se não encontrar o usuário
        }
    }
}

/*
 * ____ ____ _ ____ _
 * | _ \| __ )| | | _ \ ___ ___ __| |___
 * | |_) | _ \| | | | | |/ _ \/ __/ _` / __|
 * | _ <| |_) | |___ | |_| | __/ (_| (_| \__ \
 * |_| \_\____/|_____|____/ \___|\___\__,_|___/
 * 
 * Rodrigo Batista Leite | RBL Dev © 2025
 * GitHub: https://github.com/rodrigobatista-dev
 */
