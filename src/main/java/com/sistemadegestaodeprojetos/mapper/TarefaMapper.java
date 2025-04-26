package com.sistemadegestaodeprojetos.mapper;

import com.sistemadegestaodeprojetos.dto.ProjetoResponseDTO;
import com.sistemadegestaodeprojetos.dto.TarefaRequestDTO;
import com.sistemadegestaodeprojetos.dto.TarefaResponseDTO;
import com.sistemadegestaodeprojetos.dto.UsuarioResponseDTO;
import com.sistemadegestaodeprojetos.model.Projeto;
import com.sistemadegestaodeprojetos.model.Tarefa;
import com.sistemadegestaodeprojetos.model.Usuario;

import java.time.LocalDateTime;

public class TarefaMapper {

    public static TarefaResponseDTO toDTO(Tarefa tarefa) {
        ProjetoResponseDTO projetoDTO = ProjetoMapper.toDTO(tarefa.getProjeto());
        UsuarioResponseDTO usuarioDTO = new UsuarioResponseDTO(
                tarefa.getUsuarioResponsavel().getId(),
                tarefa.getUsuarioResponsavel().getNome(),
                tarefa.getUsuarioResponsavel().getEmail());

        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getDataCriacao(),
                tarefa.getDataEntrega(),
                projetoDTO,
                usuarioDTO,
                tarefa.getStatus());
    }

    public static Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatus(dto.getStatus());
        tarefa.setDataCriacao(LocalDateTime.now());

        Projeto projeto = new Projeto();
        projeto.setId(dto.getProjetoId());
        tarefa.setProjeto(projeto);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId()); // usuario responsavel
        tarefa.setUsuarioResponsavel(usuario);

        tarefa.setDataEntrega(dto.getDataEntrega());

        return tarefa;
    }

    public static Tarefa updateEntity(Tarefa tarefaExistente, TarefaRequestDTO dto) {
        tarefaExistente.setTitulo(dto.getTitulo());
        tarefaExistente.setDescricao(dto.getDescricao());
        tarefaExistente.setStatus(dto.getStatus());
        tarefaExistente.setDataEntrega(dto.getDataEntrega());

        Projeto projeto = new Projeto();
        projeto.setId(dto.getProjetoId());
        tarefaExistente.setProjeto(projeto);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId()); // usuario responsavel
        tarefaExistente.setUsuarioResponsavel(usuario);

        return tarefaExistente;
    }
}
