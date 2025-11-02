package com.codemized.challenge.consultorioMedico.controller;

import com.codemized.challenge.consultorioMedico.dto.medico.MedicoCreateDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoDto;
import com.codemized.challenge.consultorioMedico.dto.medico.MedicoUpdateDto;
import com.codemized.challenge.consultorioMedico.response.ApiResponse;
import com.codemized.challenge.consultorioMedico.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    public ResponseEntity<ApiResponse<MedicoDto>> create(@RequestBody MedicoCreateDto dto) {
        MedicoDto medico = medicoService.save(dto);
        ApiResponse<MedicoDto> response = ApiResponse.<MedicoDto>builder()
                .status("success")
                .message("Médico creado correctamente")
                .data(medico)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

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
