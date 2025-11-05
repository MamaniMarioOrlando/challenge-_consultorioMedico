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
public class ConsultorioResumenDto {
    private Long id;
    private String nombre;
    private List<String> nombresMedicos;
}
