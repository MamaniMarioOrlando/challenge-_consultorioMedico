package com.codemized.challenge.consultorioMedico.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioCreateDto {
    @NotBlank(message = "El campo nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El campo apellido no puede estar vacío")
    private String apellido;
    @NotBlank(message = "El campo email no puede estar vacío")
    private String email;
}
