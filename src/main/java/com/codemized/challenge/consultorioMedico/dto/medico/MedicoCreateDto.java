package com.codemized.challenge.consultorioMedico.dto.medico;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoCreateDto {
    private String nombre;
    private String apellido;
    private String matricula;
    private LocalDate fechaNacimiento;
}

