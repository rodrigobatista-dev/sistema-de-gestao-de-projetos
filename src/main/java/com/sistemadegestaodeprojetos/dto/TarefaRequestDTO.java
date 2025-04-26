package com.sistemadegestaodeprojetos.dto;

import java.time.LocalDate;

import com.sistemadegestaodeprojetos.enums.StatusTarefa;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TarefaRequestDTO {

    @Schema(description = "ID do usuário responsável pela tarefa. Exemplo: 5", example = "1", required = true)
    @NotNull(message = "O ID do usuário responsável é obrigatorio.")
    private Long usuarioId;

    @Schema(description = "ID do projeto ao qual esta tarefa pertence. Exemplo: 1", example = "1", required = true)
    @NotNull(message = "O ID do projeto é obrigatorio.")
    private Long projetoId;

    @Schema(description = "Informe o titulo da Tarefa:", required = true)
    @NotBlank(message = "O título não pode ser em branco")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s.,!?()-]{3,100}$", message = "O título deve conter apenas letras, números e sinais permitidos, entre 3 e 100 caracteres")
    private String titulo;

    @Schema(description = "Informe a descrição detalhada da tarefa.", required = true)
    @NotBlank(message = "A descrição não pode ser em branco")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\s.,!?()-]{5,255}$", message = "A descrição deve conter apenas letras, números e sinais permitidos, entre 5 e 255 caracteres")
    private String descricao;

    @Schema(description = "Data de entrega da tarefa. Opcional. Formato: yyyy-MM-dd", example = "2025-04-30")
    private LocalDate dataEntrega;

    @Schema(description = "Status do tarefa. Valores: EM_ANDAMENTO, CONCLUIDO, CANCELADO")
    @NotNull(message = "O STATUS é obrigatorio.")
    private StatusTarefa status;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(Long projetoId) {
        this.projetoId = projetoId;
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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

}
