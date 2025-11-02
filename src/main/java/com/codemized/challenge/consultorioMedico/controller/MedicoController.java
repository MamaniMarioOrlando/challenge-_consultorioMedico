package com.codemized.challenge.consultorioMedico.controller;

import com.codemized.challenge.consultorioMedico.dto.medico.MedicoCreateDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoUpdateDto;
import com.codemized.challenge.consultorioMedico.response.ApiResponse;
import com.codemized.challenge.consultorioMedico.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    @Operation(summary = "Crear un nuevo médico", description = "Crea un médico y devuelve el objeto creado.")
    @PostMapping
    public ResponseEntity<ApiResponse<MedicoDto>> create(@Valid @RequestBody MedicoCreateDto dto) {
        MedicoDto medico = medicoService.save(dto);
        ApiResponse<MedicoDto> response = ApiResponse.<MedicoDto>builder()
                .status("success")
                .message("Médico creado correctamente")
                .data(medico)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Listar todos los médicos", description = "Devuelve una lista de todos los médicos activos.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicoDto>>> findAll() {
        List<MedicoDto> medicos = medicoService.findAll();
        ApiResponse<List<MedicoDto>> response = ApiResponse.<List<MedicoDto>>builder()
                .status("success")
                .message("Listado de médicos")
                .data(medicos)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Buscar médico por ID", description = "Devuelve los datos de un médico según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoDto>> findById(@PathVariable Long id) {
        MedicoDto medico = medicoService.findById(id);
        ApiResponse<MedicoDto> response = ApiResponse.<MedicoDto>builder()
                .status("success")
                .message("Médico encontrado")
                .data(medico)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Actualizar médico", description = "Actualiza los datos de un médico existente por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoDto>> update(@PathVariable Long id, @RequestBody MedicoUpdateDto dto) {
        MedicoDto medico = medicoService.update(id, dto);
        ApiResponse<MedicoDto> response = ApiResponse.<MedicoDto>builder()
                .status("success")
                .message("Médico actualizado correctamente")
                .data(medico)
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Eliminar médico", description = "Elimina lógicamente un médico por su ID (no se borra físicamente de la base de datos).")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        medicoService.deleteById(id);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status("success")
                .message("Médico eliminado correctamente (eliminación lógica)")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
