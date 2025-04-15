package com.sistemadegestaodeprojetos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode ser em branco")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s.,!?()-]{3,100}$", message = "O título deve conter apenas letras, números e sinais permitidos, entre 3 e 100 caracteres")
    @Column(name = "TITULO", length = 100, nullable = false)
    private String titulo;

    @NotBlank(message = "A descrição não pode ser em branco")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s.,!?()-]{5,100}$", message = "A descrição deve conter apenas letras, números e sinais permitidos, entre 5 e 100 caracteres")
    @Column(name = "DESCRICAO", length = 100, nullable = false)
    private String descricao;

    @Column(name = "DATA_CRIACAO", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ENTREGA", nullable = false)
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
    private Status status;

}