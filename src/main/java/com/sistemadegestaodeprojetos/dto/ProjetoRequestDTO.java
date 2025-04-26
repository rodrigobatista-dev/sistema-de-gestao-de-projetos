package com.sistemadegestaodeprojetos.dto;

import com.sistemadegestaodeprojetos.enums.StatusProjeto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProjetoRequestDTO {

    @Schema(description = "ID do usuário responsável pela tarefa. Exemplo: 5", example = "1", required = true)
    @NotNull(message = "O ID do usuário responsável é obrigatorio.")
    private Long usuarioId;

    @Schema(description = "Informe o titulo do Projeto:", required = true)
    @Pattern(regexp = "^[\\p{L}0-9 .,!?\"'()\\-]+$", message = "A descrição contém caracteres inválidos.")
    @Size(min = 5, max = 100, message = "O título deve ter entre 5 e 100 caracteres.")
    @NotBlank(message = "O titulo não pode ser em branco.")
    private String titulo;

    @Schema(description = "Informe a descrição detalhada do Projeto.", required = true)
    @Pattern(regexp = "^[\\p{L}0-9 .,!?\"'()\\-]+$", message = "A descrição contém caracteres inválidos.")
    @Size(min = 10, max = 200, message = "A descrição deve ter entre 10 e 200 caracteres.")
    @NotBlank(message = "A descrição não pode ser em branco.")
    private String descricao;

    @Schema(description = "Status do projeto. Valores: EM_ANDAMENTO, CONCLUIDO, CANCELADO")
    @NotNull(message = "O STATUS é obrigatorio.")
    private StatusProjeto status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

}
