package com.sistemadegestaodeprojetos.mapper;

import com.sistemadegestaodeprojetos.dto.UsuarioRequestDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponseDTO toDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCpf(dto.getCpf());
        return usuario;
    }

    public static Usuario updateEntity(Usuario usuarioExistente, UsuarioRequestDTO dto) {

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setSenha(dto.getSenha());
        usuarioExistente.setCpf(dto.getCpf());
        return usuarioExistente;

    }
}
