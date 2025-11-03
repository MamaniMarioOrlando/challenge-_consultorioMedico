package com.codemized.challenge.consultorioMedico.dto.consultorio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioUpdateDto {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String horarioAtencion;
    private Boolean activo;
}
