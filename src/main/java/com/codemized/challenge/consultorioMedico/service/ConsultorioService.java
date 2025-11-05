package com.codemized.challenge.consultorioMedico.service;

import com.codemized.challenge.consultorioMedico.dto.consultorio.*;
import com.codemized.challenge.consultorioMedico.dto.consultorio.AgregarUsuariosRequestDto;

import java.util.List;

public interface ConsultorioService {
    ConsultorioDto save(ConsultorioCreateDto consultorioCreateDto);
    ConsultorioDto update(Long id, ConsultorioUpdateDto consultorioUpdateDto);
    List<ConsultorioDto> findAll();
    ConsultorioDto findById(Long id);
    void deleteById(Long id);
    ConsultorioDto agregarMedicos(AgregarMedicosRequestDto requestDto);
    ConsultorioResumenMedicoDto obtenerResumenConsultorio(Long consultorioId);
    ConsultorioDto agregarUsuarios(AgregarUsuariosRequestDto requestDto);
    ConsultorioResumenUsuarioDto obtenerResumenUsuariosConsultorio(Long consultorioId);
}
