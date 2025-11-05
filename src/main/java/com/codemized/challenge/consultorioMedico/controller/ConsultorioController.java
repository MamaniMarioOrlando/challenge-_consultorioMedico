package com.codemized.challenge.consultorioMedico.controller;

import com.codemized.challenge.consultorioMedico.dto.consultorio.*;
import com.codemized.challenge.consultorioMedico.dto.consultorio.AgregarUsuariosRequestDto;
import com.codemized.challenge.consultorioMedico.response.ApiResponse;
import com.codemized.challenge.consultorioMedico.service.ConsultorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
@RequiredArgsConstructor
@Tag(name = "Consultorios",
        description = "Operaciones CRUD para Consultorios")
public class ConsultorioController {
    private final ConsultorioService consultorioService;

    @Operation(summary = "Crear un consultorio",
            description = "Crea un nuevo consultorio")
    @PostMapping
    public ResponseEntity<ApiResponse<ConsultorioDto>> create(@Valid @RequestBody ConsultorioCreateDto dto) {
        ConsultorioDto created = consultorioService.save(dto);
        return ResponseEntity.status(201).body(ApiResponse.success("Consultorio creado exitosamente", created));
    }

    @Operation(summary = "Actualizar un consultorio",
            description = "Actualiza los datos de un consultorio existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioDto>> update(@PathVariable Long id, @Valid @RequestBody ConsultorioUpdateDto dto) {
        ConsultorioDto updated = consultorioService.update(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Consultorio actualizado exitosamente", updated));
    }

    @Operation(summary = "Obtener todos los consultorios",
            description = "Lista todos los consultorios activos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ConsultorioDto>>> findAll() {
        List<ConsultorioDto> consultorios = consultorioService.findAll();
        return ResponseEntity.ok(ApiResponse.success("Lista de consultorios obtenida exitosamente", consultorios));
    }

    @Operation(summary = "Obtener un consultorio por ID",
            description = "Obtiene los datos de un consultorio activo por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultorioDto>> findById(@PathVariable Long id) {
        ConsultorioDto consultorio = consultorioService.findById(id);
        return ResponseEntity.ok(ApiResponse.
                success("Consultorio encontrado", consultorio));
    }

    @Operation(summary = "Eliminar un consultorio",
            description = "Marca un consultorio como inactivo")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        consultorioService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Consultorio eliminado lógicamente", null));
    }

    @Operation(summary = "Agregar médicos a un consultorio",
            description = "Agrega una lista de médicos activos a un consultorio existente")
    @PostMapping("/medicos")
    public ResponseEntity<ApiResponse<ConsultorioDto>> agregarMedicos(@Valid @RequestBody AgregarMedicosRequestDto requestDto) {
        ConsultorioDto actualizado = consultorioService.agregarMedicos(requestDto);
        return ResponseEntity.ok(ApiResponse.success("Médicos agregados exitosamente al consultorio", actualizado));
    }

    @Operation(
            summary = "Obtener resumen de medicos de un consultorio",
            description = "Devuelve un resumen del consultorio con sus médicos activos"
    )
    @GetMapping("/{id}/resumen-medicos")
    public ResponseEntity<ApiResponse<ConsultorioResumenMedicoDto>> obtenerResumenConsultorio(@PathVariable Long id) {
        ConsultorioResumenMedicoDto resumen = consultorioService.obtenerResumenConsultorio(id);
        return ResponseEntity.ok(ApiResponse.success("Resumen del consultorio obtenido exitosamente", resumen));
    }

    @Operation(summary = "Agregar usuarios a un consultorio",
            description = "Agrega una lista de usuarios activos a un consultorio existente")
    @PostMapping("/usuarios")
    public ResponseEntity<ApiResponse<ConsultorioDto>> agregarUsuarios(@Valid @RequestBody AgregarUsuariosRequestDto requestDto) {
        ConsultorioDto actualizado = consultorioService.agregarUsuarios(requestDto);
        return ResponseEntity.ok(ApiResponse.success("Usuarios agregados exitosamente al consultorio", actualizado));
    }

    @Operation(
            summary = "Obtener resumen de usuarios de un consultorio",
            description = "Devuelve un resumen del consultorio con sus usuarios activos"
    )
    @GetMapping("/{id}/resumen-usuarios")
    public ResponseEntity<ApiResponse<ConsultorioResumenUsuarioDto>> obtenerResumenUsuariosConsultorio(@PathVariable Long id) {
        ConsultorioResumenUsuarioDto resumen = consultorioService.obtenerResumenUsuariosConsultorio(id);
        return ResponseEntity.ok(ApiResponse.success("Resumen de usuarios del consultorio obtenido exitosamente", resumen));
    }

}
