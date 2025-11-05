package com.codemized.challenge.consultorioMedico.dto.consultorio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultorioResumenUsuarioDto {
    private Long id;
    private String nombre;
    private List<String> nombresUsuarios;
}
