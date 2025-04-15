package com.sistemadegestaodeprojetos.model;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/* import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; */
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@Entity(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras")
    @NotBlank(message = "O nome nao pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "NOME", unique = false, nullable = false)
    public String nome;

    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$", message = "E-mail inválido")
    @NotBlank(message = "O email não pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "EMAIL", unique = true, nullable = false)
    public String email;

    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "A senha deve conter letra maiúscula, minúscula, número e símbolo")
    @NotBlank(message = "A senha não pode ficar em branco")
    @Column(columnDefinition = "varchar(100)", name = "SENHA", unique = true, nullable = false)
    public String senha;

    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    @NotBlank(message = "O CPF não pode estar em branco")
    @Column(name = "cpf", columnDefinition = "varchar(14)", nullable = false, unique = true)
    public String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    public List<Projeto> projetos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioResponsavel", cascade = CascadeType.ALL)
    public List<Tarefa> tarefas;

    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "role_id") // nome da coluna FK no banco
     * private Role role;
     */

}
