package com.codemized.challenge.consultorioMedico.dto.consultorio;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioCreateDto {
    @NotBlank(message = "El campo nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "El campo teléfono es obligatorio")
    private String telefono;

    @NotBlank(message = "El campo email es obligatorio")
    private String email;

    @NotBlank(message = "El campo horario de atención es obligatorio")
    private String horarioAtencion;

    @NotNull(message = "El estado activo es obligatorio")
    private Boolean activo;
}
