package com.codemized.challenge.consultorioMedico.repository;

import com.codemized.challenge.consultorioMedico.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
