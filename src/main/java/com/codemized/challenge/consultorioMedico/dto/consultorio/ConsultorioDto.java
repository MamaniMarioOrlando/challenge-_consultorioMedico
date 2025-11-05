package com.codemized.challenge.consultorioMedico.dto.consultorio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private String horarioAtencion;
    private Boolean activo;
}
