package com.sistemadegestaodeprojetos.mapper;

import com.sistemadegestaodeprojetos.dto.ProjetoRequestDTO;
import com.sistemadegestaodeprojetos.dto.ProjetoResponseDTO;

import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.model.Projeto;

import com.sistemadegestaodeprojetos.model.Usuario;

public class ProjetoMapper {

    public static ProjetoResponseDTO toDTO(Projeto projeto) {

        Usuario usuario = projeto.getUsuario();

        // converte o Usuario (entidade) para UsuarioResponseDTO para não mostrar dados
        // como senha e cpf
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());

        return new ProjetoResponseDTO(
                projeto.getId(),
                usuarioDTO,
                projeto.getTitulo(),
                projeto.getDescricao(),
                projeto.getDataCriacao(),
                projeto.getStatus());

    }

    public static Projeto toEntity(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        Usuario usuario = new Usuario();

        usuario.setId(dto.getUsuarioId());
        projeto.setUsuario(usuario);
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setStatus(dto.getStatus());
        return projeto;
    }

    public static Projeto updateEntity(Projeto projetoExistente, ProjetoRequestDTO dto) {

        projetoExistente.setTitulo(dto.getTitulo());
        projetoExistente.setDescricao(dto.getDescricao());
        projetoExistente.setStatus(dto.getStatus());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        projetoExistente.setUsuario(usuario);

        return projetoExistente;
    }

}
