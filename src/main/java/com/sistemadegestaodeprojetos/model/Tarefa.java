package com.sistemadegestaodeprojetos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sistemadegestaodeprojetos.enums.StatusTarefa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ENTREGA")
    private LocalDate dataEntrega;

    // Relacionamento ManyToOne: muitas tarefas podem ser associadas a um projeto
    @ManyToOne
    @JoinColumn(name = "PROJETO_ID", nullable = false) // Nome da coluna de chave estrangeira
    private Projeto projeto;

    // Relacionamento ManyToOne: muitas tarefas podem ser associadas a um usuário
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false) // Nome da coluna de chave estrangeira
    private Usuario usuarioResponsavel;

    @Enumerated(EnumType.STRING) // Usando @Enumerated para salvar como string
    @Column(name = "STATUS", nullable = false)
    private StatusTarefa status;

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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

}