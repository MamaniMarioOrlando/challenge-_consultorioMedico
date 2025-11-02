package com.codemized.challenge.consultorioMedico.repository;

import com.codemized.challenge.consultorioMedico.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    // Métodos personalizados si los necesitas
}
