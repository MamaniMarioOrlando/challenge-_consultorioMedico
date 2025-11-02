package com.codemized.challenge.consultorioMedico.repository;

import com.codemized.challenge.consultorioMedico.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
