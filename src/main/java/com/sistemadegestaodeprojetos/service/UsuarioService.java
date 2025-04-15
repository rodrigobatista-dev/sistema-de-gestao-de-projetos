package com.sistemadegestaodeprojetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Verifica se um usuário existe pelo ID
    public boolean isExiste(Long id) {
        return usuarioRepository.existsById(id);
    }

    // Verifica se o CPF ainda não está cadastrado
    public boolean notExisteCpf(String cpf) {
        List<Usuario> lista = usuarioRepository.findByCpf(cpf);
        return lista.isEmpty(); // retorna true se o CPF não foi encontrado (ou seja, pode cadastrar)
    }

    // Cadastra um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Atualiza um usuário existente
    public Usuario atualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Retorna todos os usuários do banco
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Remove um usuário pelo ID
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Busca um usuário específico pelo ID
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
}
