package com.sistemadegestaodeprojetos.model;

import java.time.LocalDateTime;

import com.sistemadegestaodeprojetos.enums.StatusProjeto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "PROJETO")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "responsavel")
    private Usuario usuario;

    @NotBlank(message = "O título não pode estar em branco")
    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição não pode estar em branco")
    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    private void prePersist() {
        if (dataCriacao == null) {
            dataCriacao = LocalDateTime.now();
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private StatusProjeto status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

// O uso de columnDefinition = "varchar(100)" não é necessário porque:
// 1. O JPA já define automaticamente String como VARCHAR(255).
// 2. Se quisermos limitar o tamanho, usamos length = 100, que é mais portável.
// 3. columnDefinition pode causar problemas ao mudar de banco de dados (MySQL,
// PostgreSQL, etc.)
// 4. Só devemos usá-lo quando realmente precisarmos de um tipo específico, como
// "TEXT" ou "BOOLEAN DEFAULT false".
