package com.sistemadegestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadegestaodeprojetos.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
