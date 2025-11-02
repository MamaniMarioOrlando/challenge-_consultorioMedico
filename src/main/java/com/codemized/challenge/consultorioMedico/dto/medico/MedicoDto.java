package com.codemized.challenge.consultorioMedico.dto.medico;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class MedicoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private LocalDate fechaNacimiento;
    private Boolean activo;
}
