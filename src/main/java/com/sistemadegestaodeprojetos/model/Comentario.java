package com.sistemadegestaodeprojetos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "COMENTARIO")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXTO", nullable = false)
    private String texto;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    // Relacionamento ManyToOne com a Tarefa
    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    // Relacionamento ManyToOne com a Tarefa
    @ManyToOne
    @JoinColumn(name = "TAREFA_ID", nullable = false)
    private Tarefa tarefa;

    public Comentario() {

    }

    public Comentario(Long id, String texto, LocalDateTime dataCriacao, Usuario usuario, Tarefa tarefa) {
        this.id = id;
        this.texto = texto;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        this.tarefa = tarefa;
    }

}
