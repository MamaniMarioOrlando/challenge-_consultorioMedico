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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Médico creado correctamente", medico));
    }

    @Operation(summary = "Listar todos los médicos", description = "Devuelve una lista de todos los médicos activos.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<MedicoDto>>> findAll() {
        List<MedicoDto> medicos = medicoService.findAll();
        return ResponseEntity.ok(ApiResponse.success("Listado de médicos", medicos));
    }

    @Operation(summary = "Buscar médico por ID", description = "Devuelve los datos de un médico según su ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoDto>> findById(@PathVariable Long id) {
        MedicoDto medico = medicoService.findById(id);
        return ResponseEntity.ok(ApiResponse.success("Médico encontrado", medico));
    }

    @Operation(summary = "Actualizar médico", description = "Actualiza los datos de un médico existente por su ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicoDto>> update(@PathVariable Long id, @RequestBody MedicoUpdateDto dto) {
        MedicoDto medico = medicoService.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Médico actualizado correctamente", medico));
    }

    @Operation(summary = "Eliminar médico", description = "Elimina lógicamente un médico por su ID (no se borra físicamente de la base de datos).")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        medicoService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Médico eliminado correctamente (eliminación lógica)", null));
    }
}
