package com.codemized.challenge.consultorioMedico.dto.consultorio;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgregarMedicosRequestDto {
    @NotNull(message = "El id del consultorio no puede ser nulo")
    private Long consultorioId;

    @NotEmpty(message = "La lista de médicos no puede estar vacía")
    private List<Long> medicoIds;
}
