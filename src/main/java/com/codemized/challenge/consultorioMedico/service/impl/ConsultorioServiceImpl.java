package com.codemized.challenge.consultorioMedico.service.impl;

import com.codemized.challenge.consultorioMedico.dto.consultorio.*;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioAlreadyExistsException;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioNotFoundException;
import com.codemized.challenge.consultorioMedico.exception.ConsultorioInactivoException;
import com.codemized.challenge.consultorioMedico.model.Consultorio;
import com.codemized.challenge.consultorioMedico.model.Medico;
import com.codemized.challenge.consultorioMedico.repository.ConsultorioRepository;
import com.codemized.challenge.consultorioMedico.repository.MedicoRepository;
import com.codemized.challenge.consultorioMedico.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Collections;
import java.util.Set;
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

    @Override
    public ConsultorioDto agregarMedicos(AgregarMedicosRequestDto requestDto) {
        Consultorio consultorio = consultorioRepository.findById(requestDto.getConsultorioId())
                .orElseThrow(() -> new ConsultorioNotFoundException(requestDto.getConsultorioId()));
        if (!consultorio.getActivo()) {
            throw new ConsultorioInactivoException(requestDto.getConsultorioId());
        }
        List<Long> medicoIds = requestDto.getMedicoIds();
        if (medicoIds == null || medicoIds.isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs de médicos no puede estar vacía");
        }
        List<Medico> medicos = medicoRepository.findAllById(medicoIds);
        if (medicos.isEmpty() || medicos.size() != medicoIds.size()) {
            throw new IllegalArgumentException("Uno o más médicos no existen");
        }
        // Filtrar solo médicos activos
        List<Medico> medicosActivos = medicos.stream()
                .filter(Medico::getActivo)
                .toList();
        if (medicosActivos.isEmpty()) {
            throw new IllegalArgumentException("Todos los médicos seleccionados están inactivos");
        }

        // Inicializar la colección LAZY y crear una nueva colección para evitar ConcurrentModificationException
        Hibernate.initialize(consultorio.getMedicos());
        Set<Medico> medicosActuales = consultorio.getMedicos();

        // Crear un nuevo HashSet con los médicos existentes
        Set<Medico> nuevosMedicos = new HashSet<>();
        if (medicosActuales != null) {
            nuevosMedicos.addAll(medicosActuales);
        }

        // Agregar los nuevos médicos activos
        nuevosMedicos.addAll(medicosActivos);

        // Establecer la nueva colección
        consultorio.setMedicos(nuevosMedicos);

        // Sincronizar la relación bidireccional en los médicos
        for (Medico medico : medicosActivos) {
            Hibernate.initialize(medico.getConsultorios());
            Set<Consultorio> consultoriosMedico = medico.getConsultorios();
            if (consultoriosMedico == null) {
                consultoriosMedico = new HashSet<>();
                medico.setConsultorios(consultoriosMedico);
            }
            consultoriosMedico.add(consultorio);
        }

        Consultorio actualizado = consultorioRepository.save(consultorio);
        return ConsultorioMappper.toDto(actualizado);
    }

    @Override
    public ConsultorioResumenDto obtenerResumenConsultorio(Long consultorioId) {
        Consultorio consultorio = consultorioRepository.findById(consultorioId)
                .filter(Consultorio::getActivo)
                .orElseThrow(() -> new ConsultorioNotFoundException(consultorioId));
        List<String> nombresMedicos = consultorio.getMedicos() == null ? Collections.emptyList() :
                consultorio.getMedicos().stream()
                        .filter(Medico::getActivo)
                        .map(medico -> medico.getNombre() + " " + medico.getApellido())
                        .toList();
        return ConsultorioResumenDto.builder()
                .id(consultorio.getId())
                .nombre(consultorio.getNombre())
                .nombresMedicos(nombresMedicos)
                .build();
    }
}
