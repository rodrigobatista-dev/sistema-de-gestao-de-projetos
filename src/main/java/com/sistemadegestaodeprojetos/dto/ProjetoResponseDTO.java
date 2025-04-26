package com.sistemadegestaodeprojetos.dto;

import java.time.LocalDateTime;
import com.sistemadegestaodeprojetos.enums.StatusProjeto;

public class ProjetoResponseDTO {

    private Long id;
    private UsuarioResponseDTO usuarioDTO;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private StatusProjeto status;

    public ProjetoResponseDTO(Long id, UsuarioResponseDTO usuario, String titulo, String descricao,
            LocalDateTime dataCriacao, StatusProjeto status) {
        this.id = id;
        this.usuarioDTO = usuario;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioResponseDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioResponseDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
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

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

}
