package com.sistemadegestaodeprojetos.dto;

public class UsuarioResponseDTO { // para saída de dados (GET)

    private Long id;
    private String nome;
    private String email;
    private String cpf;


    // contrutores
    public UsuarioResponseDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


}


/*
 * ____ ____ _ ____ _
 * | _ \| __ )| | | _ \ ___ ___ __| |___
 * | |_) | _ \| | | | | |/ _ \/ __/ _` / __|
 * | _ <| |_) | |___ | |_| | __/ (_| (_| \__ \
 * |_| \_\____/|_____|____/ \___|\___\__,_|___/
 * 
 * Rodrigo Batista Leite | RBL Dev © 2025
 * GitHub: https://github.com/rodrigobatista-dev
 */
