package com.sistemadegestaodeprojetos.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sistemadegestaodeprojetos.enums.StatusTarefa;

public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDate dataEntrega;
    private ProjetoResponseDTO projeto;
    private UsuarioResponseDTO usuarioResponsavel;
    private StatusTarefa status;

    public TarefaResponseDTO(Long id, String titulo, String descricao, LocalDateTime dataCriacao, LocalDate dataEntrega,
            ProjetoResponseDTO projeto, UsuarioResponseDTO usuarioResponsavel, StatusTarefa status) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataEntrega = dataEntrega;
        this.projeto = projeto;
        this.usuarioResponsavel = usuarioResponsavel;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public ProjetoResponseDTO getProjeto() {
        return projeto;
    }

    public void setProjeto(ProjetoResponseDTO projeto) {
        this.projeto = projeto;
    }

    public UsuarioResponseDTO getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(UsuarioResponseDTO usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

}
