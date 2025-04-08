package com.sistemadegestaodeprojetos.model;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Data
@Entity(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome nao pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "NOME", unique = false, nullable = false)
    private String nome;

    @NotBlank(message = "O email não pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "EMAIL", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "A senha não pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "SENHA", unique = true, nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "role_id") // nome da coluna FK no banco
    private Role role;

}
