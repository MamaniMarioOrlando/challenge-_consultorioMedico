package com.codemized.challenge.consultorioMedico.service;

import com.codemized.challenge.consultorioMedico.dto.medico.MedicoDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoCreateDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoUpdateDto;
import java.util.List;

public interface MedicoService {
    MedicoDto save(MedicoCreateDto medicoCreateDto);
    List<MedicoDto> findAll();
    MedicoDto findById(Long id);
    MedicoDto update(Long id, MedicoUpdateDto medicoUpdateDto);
    void deleteById(Long id);
}
