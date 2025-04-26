package com.sistemadegestaodeprojetos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemadegestaodeprojetos.dto.UsuarioRequestDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.exceptionerros.UsuarioNaoEncontradoException;
import com.sistemadegestaodeprojetos.mapper.UsuarioMapper;
import com.sistemadegestaodeprojetos.model.Usuario;
import com.sistemadegestaodeprojetos.repository.UsuarioRepository;

import java.util.List;

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

    // Cadastra um novo usuário usando o DTO
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(salvo);
    }

    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario usuarioexistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não encontrado."));

        usuarioexistente.setNome(dto.getNome());
        usuarioexistente.setEmail(dto.getEmail());

        Usuario usuarioAtualizar = usuarioRepository.save(usuarioexistente);

        return UsuarioMapper.toDTO(usuarioAtualizar);

    }

    // Método utilitário para buscar ou lançar exceção
    public Usuario buscarOuLancarExcecao(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com ID " + id + " não foi encontrado."));
    }

    // Retorna todos os usuários do banco
    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll().stream()
                .map(UsuarioMapper::toDTO).toList();
    }

    // Remove um usuário pelo ID
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    // Buscar usuário por ID e retornar um DTO
    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        return UsuarioMapper.toDTO(buscarOuLancarExcecao(id));
    }

}