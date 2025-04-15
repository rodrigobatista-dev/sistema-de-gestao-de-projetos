package com.sistemadegestaodeprojetos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.service.UsuarioService;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // Criar usuário
    @PostMapping("/criarUsuario")
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            if (usuarioService.notExisteCpf(usuario.cpf)) {
                usuario = usuarioService.criarUsuario(usuario);
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
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody @Valid Usuario usuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> listaDeErros = new ArrayList<>();
            for (ObjectError erro : bindingResult.getAllErrors()) {
                listaDeErros.add(erro.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(listaDeErros);
        }

        try {
            if (usuarioService.isExiste(usuario.id)) {
                usuario = usuarioService.atualizar(usuario);
                return ResponseEntity.ok(usuario.id);
            } else {
                return ResponseEntity.badRequest().body("O ID informado não se encontra cadastrado");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Não foi possível atualizar o usuário");
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
