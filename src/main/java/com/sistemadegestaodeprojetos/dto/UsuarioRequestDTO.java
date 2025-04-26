package com.sistemadegestaodeprojetos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO { // para entrada de dados (POST/PUT)

    @Schema(description = "Informe seu nome completo", example = "Seu nome aqui", required = true)
    @NotBlank(message = "O nome não pode ficar em branco")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "Nome deve conter apenas letras")
    private String nome;

    @Schema(description = "Informe seu email:", required = true)
    @NotBlank(message = "O email não pode ficar em branco")
    @Pattern(regexp = "^[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,}$", message = "E-mail inválido")
    private String email;

    @Schema(description = "Informe sua senha. Deve conter pelo menos 8 caracteres, incluindo letra maiúscula, minúscula, número e símbolo.", example = "Senha@123", required = true)
    @NotBlank(message = "A senha não pode ficar em branco")
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "A senha deve conter letra maiúscula, minúscula, número e símbolo")
    private String senha;

    @Schema(description = "Informe seu CPF: ", example = "000.000.000-00", required = true)
    @NotBlank(message = "O CPF não pode estar em branco")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "CPF deve estar no formato 000.000.000-00")
    private String cpf;

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

}
