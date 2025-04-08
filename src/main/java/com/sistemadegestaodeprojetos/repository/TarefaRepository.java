package com.sistemadegestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadegestaodeprojetos.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}