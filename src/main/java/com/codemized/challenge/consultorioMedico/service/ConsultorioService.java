package com.codemized.challenge.consultorioMedico.service;

import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioCreateDto;
import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioDto;
import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioUpdateDto;

import java.util.List;

public interface ConsultorioService {
    ConsultorioDto save(ConsultorioCreateDto consultorioCreateDto);
    ConsultorioDto update(Long id, ConsultorioUpdateDto consultorioUpdateDto);
    List<ConsultorioDto> findAll();
    ConsultorioDto findById(Long id);
    void deleteById(Long id);
}
