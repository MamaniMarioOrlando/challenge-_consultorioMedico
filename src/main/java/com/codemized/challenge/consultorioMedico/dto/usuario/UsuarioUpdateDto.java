package com.codemized.challenge.consultorioMedico.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioUpdateDto {
    private String nombre;
    private String apellido;
    private String email;
    private Boolean activo;
}
