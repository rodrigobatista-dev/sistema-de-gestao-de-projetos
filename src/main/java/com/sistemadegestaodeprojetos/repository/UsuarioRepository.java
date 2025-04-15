package com.sistemadegestaodeprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadegestaodeprojetos.model.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCpf(String cpf);
}
