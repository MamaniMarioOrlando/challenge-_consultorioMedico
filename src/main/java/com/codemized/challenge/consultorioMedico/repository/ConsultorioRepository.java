package com.codemized.challenge.consultorioMedico.repository;

import com.codemized.challenge.consultorioMedico.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    List<Consultorio> findAllByActivoTrue();
}
