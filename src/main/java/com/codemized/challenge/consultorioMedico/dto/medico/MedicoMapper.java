package com.codemized.challenge.consultorioMedico.dto.medico;

import com.codemized.challenge.consultorioMedico.model.Medico;

public class MedicoMapper {
    public static MedicoDto toDto(Medico medico) {
        if (medico == null) return null;
        return MedicoDto.builder()
                .id(medico.getId())
                .nombre(medico.getNombre())
                .apellido(medico.getApellido())
                .matricula(medico.getMatricula())
                .fechaNacimiento(medico.getFechaNacimiento())
                .activo(medico.getActivo())
                .build();
    }

    public static Medico toEntity(MedicoCreateDto dto) {
        if (dto == null) return null;
        return Medico.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .matricula(dto.getMatricula())
                .fechaNacimiento(dto.getFechaNacimiento())
                .activo(true)
                .build();
    }

    public static void updateEntityFromDto(MedicoUpdateDto dto, Medico medico) {
        if (dto == null || medico == null) return;
        if (dto.getNombre() != null) medico.setNombre(dto.getNombre());
        if (dto.getApellido() != null) medico.setApellido(dto.getApellido());
        if (dto.getMatricula() != null) medico.setMatricula(dto.getMatricula());
        if (dto.getFechaNacimiento() != null) medico.setFechaNacimiento(dto.getFechaNacimiento());
        if (dto.getActivo() != null) medico.setActivo(dto.getActivo());
    }
}

