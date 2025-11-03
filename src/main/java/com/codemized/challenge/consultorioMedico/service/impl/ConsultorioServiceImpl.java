package com.codemized.challenge.consultorioMedico.service.impl;

import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioCreateDto;
import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioDto;
import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioMappper;
import com.codemized.challenge.consultorioMedico.dto.consultorio.ConsultorioUpdateDto;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioAlreadyExistsException;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioNotFoundException;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioInactivoException;
import com.codemized.challenge.consultorioMedico.model.Consultorio;
import com.codemized.challenge.consultorioMedico.repository.ConsultorioRepository;
import com.codemized.challenge.consultorioMedico.repository.MedicoRepository;
import com.codemized.challenge.consultorioMedico.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsultorioServiceImpl implements ConsultorioService {
    private final ConsultorioRepository consultorioRepository;
    private final MedicoRepository medicoRepository;

    @Override
    public ConsultorioDto save(ConsultorioCreateDto consultorioCreateDto) {

        if (consultorioRepository.findAll().stream().anyMatch(c -> c.getEmail().equalsIgnoreCase(consultorioCreateDto.getEmail()))) {
            throw new ConsultorioAlreadyExistsException("email", consultorioCreateDto.getEmail());
        }

        if (consultorioRepository.findAll().stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(consultorioCreateDto.getNombre()))) {
            throw new ConsultorioAlreadyExistsException("nombre", consultorioCreateDto.getNombre());
        }
        Consultorio consultorio = ConsultorioMappper.toEntity(consultorioCreateDto);
        Consultorio saved = consultorioRepository.save(consultorio);
        return ConsultorioMappper.toDto(saved);
    }

    @Override
    public ConsultorioDto update(Long id, ConsultorioUpdateDto consultorioUpdateDto) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new ConsultorioNotFoundException(id));
        if (!consultorio.getActivo()) {
            throw new ConsultorioInactivoException(id);
        }
        ConsultorioMappper.updateEntityFromDto(consultorioUpdateDto, consultorio);
        Consultorio updated = consultorioRepository.save(consultorio);
        return ConsultorioMappper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConsultorioDto> findAll() {
        return consultorioRepository.findAllByActivoTrue().stream()
                .map(ConsultorioMappper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ConsultorioDto findById(Long id) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .filter(Consultorio::getActivo)
                .orElseThrow(() -> new ConsultorioNotFoundException(id));
        return ConsultorioMappper.toDto(consultorio);
    }

    @Override
    public void deleteById(Long id) {
        Consultorio consultorio = consultorioRepository.findById(id)
                .orElseThrow(() -> new ConsultorioNotFoundException(id));
        if (!consultorio.getActivo()) {
            throw new ConsultorioInactivoException(id);
        }
        consultorio.setActivo(false);
        consultorioRepository.save(consultorio);
    }
}
