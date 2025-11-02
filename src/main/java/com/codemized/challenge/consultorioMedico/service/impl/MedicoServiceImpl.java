package com.codemized.challenge.consultorioMedico.service.impl;

import com.codemized.challenge.consultorioMedico.dto.medico.MedicoCreateDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoMapper;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoUpdateDto;
import com.codemized.challenge.consultorioMedico.exception.MedicoAlreadyExistsException;
import com.codemized.challenge.consultorioMedico.exception.MedicoNotFoundException;
import com.codemized.challenge.consultorioMedico.model.Medico;
import com.codemized.challenge.consultorioMedico.repository.MedicoRepository;
import com.codemized.challenge.consultorioMedico.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MedicoServiceImpl implements MedicoService {
    private final MedicoRepository medicoRepository;

    @Override
    public MedicoDto save(MedicoCreateDto medicoCreateDto) {
        if (medicoRepository.findAll().stream().anyMatch(m -> m.getMatricula().equals(medicoCreateDto.getMatricula()))) {
            throw new MedicoAlreadyExistsException(medicoCreateDto.getMatricula());
        }
        Medico medico = MedicoMapper.toEntity(medicoCreateDto);
        Medico saved = medicoRepository.save(medico);
        return MedicoMapper.toDto(saved);
    }

    @Override
    public List<MedicoDto> findAll() {
        return medicoRepository.findAll().stream()
                .map(MedicoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicoDto findById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
        return MedicoMapper.toDto(medico);
    }

    @Override
    public MedicoDto update(Long id, MedicoUpdateDto medicoUpdateDto) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
        if (medicoUpdateDto.getMatricula() != null &&
            medicoRepository.findAll().stream().anyMatch(m -> m.getMatricula().equals(medicoUpdateDto.getMatricula()) && !m.getId().equals(id))) {
            throw new MedicoAlreadyExistsException(medicoUpdateDto.getMatricula());
        }
        MedicoMapper.updateEntityFromDto(medicoUpdateDto, medico);
        Medico updated = medicoRepository.save(medico);
        return MedicoMapper.toDto(updated);
    }

    @Override
    public void deleteById(Long id) {
        Medico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new MedicoNotFoundException(id));
        medico.setActivo(false); // Eliminación lógica
        medicoRepository.save(medico);
    }
}
