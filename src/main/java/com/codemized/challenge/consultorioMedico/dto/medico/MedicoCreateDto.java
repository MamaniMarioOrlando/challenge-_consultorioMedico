package com.codemized.challenge.consultorioMedico.dto.medico;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoCreateDto {
    @NotBlank(message = "El campo nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "El campo apellido es obligatorio")
    private String apellido;
    @NotBlank(message = "La matrícula es obligatoria")
    private String matricula;
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;
}
