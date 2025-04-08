package com.sistemadegestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadegestaodeprojetos.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
