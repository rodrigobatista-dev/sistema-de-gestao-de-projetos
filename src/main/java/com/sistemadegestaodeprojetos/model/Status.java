package com.sistemadegestaodeprojetos.model;

public enum Status {
    // Status para Projetos
    EM_ANDAMENTO,
    CONCLUIDO,

    // Status para Tarefas
    ABERTO,
    EM_PROCESSO,
    CONCLUIDO_TAREFA // Renomeei para evitar confusão com o status de projeto
}