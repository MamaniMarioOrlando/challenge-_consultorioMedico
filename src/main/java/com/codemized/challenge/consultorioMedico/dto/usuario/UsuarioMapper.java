package com.codemized.challenge.consultorioMedico.dto.usuario;

import com.codemized.challenge.consultorioMedico.model.Usuario;

public class UsuarioMapper {
    // Convierte una entidad Usuario a UsuarioDto
    public static UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .activo(usuario.getActivo())
                .build();
    }

    // Convierte un UsuarioCreateDto a una entidad Usuario
    public static Usuario toEntity(UsuarioCreateDto dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .activo(true)
                .build();
    }

    // Actualiza una entidad Usuario con los datos de UsuarioUpdateDto
    public static void updateEntityFromDto(UsuarioUpdateDto dto, Usuario usuario) {
        if (dto == null || usuario == null) return;
        if (dto.getNombre() != null && !dto.getNombre().trim().isEmpty()) {
            usuario.setNombre(dto.getNombre());
        }
        if (dto.getApellido() != null && !dto.getApellido().trim().isEmpty()) {
            usuario.setApellido(dto.getApellido());
        }
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            usuario.setEmail(dto.getEmail());
        }
        if (dto.getActivo() != null) {
            usuario.setActivo(dto.getActivo());
        }
    }
}
