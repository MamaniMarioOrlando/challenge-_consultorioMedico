package com.codemized.challenge.consultorioMedico.dto.consultorio;

import com.codemized.challenge.consultorioMedico.model.Consultorio;

public class ConsultorioMappper {
    // Convierte una entidad Consultorio a ConsultorioDto
    public static ConsultorioDto toDto(Consultorio consultorio) {
        if (consultorio == null) return null;
        return ConsultorioDto.builder()
                .id(consultorio.getId())
                .nombre(consultorio.getNombre())
                .direccion(consultorio.getDireccion())
                .telefono(consultorio.getTelefono())
                .email(consultorio.getEmail())
                .horarioAtencion(consultorio.getHorarioAtencion())
                .activo(consultorio.getActivo())
                .build();
    }

    // Convierte un ConsultorioCreateDto a una entidad Consultorio
    public static Consultorio toEntity(ConsultorioCreateDto dto) {
        if (dto == null) return null;
        return Consultorio.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .email(dto.getEmail())
                .horarioAtencion(dto.getHorarioAtencion())
                .activo(dto.getActivo())
                .build();
    }

    // Actualiza una entidad Consultorio con los datos de ConsultorioUpdateDto
    public static void updateEntityFromDto(ConsultorioUpdateDto dto, Consultorio consultorio) {
        if (dto == null || consultorio == null) return;
        if (dto.getNombre() != null && !dto.getNombre().trim().isEmpty()) {
            consultorio.setNombre(dto.getNombre());
        }
        if (dto.getDireccion() != null && !dto.getDireccion().trim().isEmpty()) {
            consultorio.setDireccion(dto.getDireccion());
        }
        if (dto.getTelefono() != null && !dto.getTelefono().trim().isEmpty()) {
            consultorio.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            consultorio.setEmail(dto.getEmail());
        }
        if (dto.getHorarioAtencion() != null && !dto.getHorarioAtencion().trim().isEmpty()) {
            consultorio.setHorarioAtencion(dto.getHorarioAtencion());
        }
        if (dto.getActivo() != null) {
            consultorio.setActivo(dto.getActivo());
        }
    }
}
