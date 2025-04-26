package com.sistemadegestaodeprojetos.model;

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

@Entity(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", columnDefinition = "varchar(100)", nullable = false)
    private String nome;

    @Column(name = "EMAIL", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String email;

    @Column(name = "SENHA", columnDefinition = "varchar(100)", unique = true, nullable = false)
    private String senha;

    @Column(name = "CPF", columnDefinition = "varchar(14)", unique = true, nullable = false)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    @JsonIgnore
    @OneToMany(mappedBy = "usuarioResponsavel", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    /*
     * @ManyToOne
     * 
     * @JoinColumn(name = "role_id") // nome da coluna FK no banco
     * private Role role;
     */

}
