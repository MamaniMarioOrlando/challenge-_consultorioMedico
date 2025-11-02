package com.codemized.challenge.consultorioMedico.controller;

import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioCreateDto;
import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioDto;
import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioUpdateDto;
import com.codemized.challenge.consultorioMedico.response.ApiResponse;
import com.codemized.challenge.consultorioMedico.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Crear un nuevo usuario", description = "Crea un usuario y lo retorna")
    @PostMapping
    public ResponseEntity<ApiResponse<UsuarioDto>> create(@Valid @RequestBody UsuarioCreateDto usuarioCreateDto) {
        UsuarioDto usuarioDto = usuarioService.save(usuarioCreateDto);
        return ResponseEntity.status(201).body(ApiResponse.<UsuarioDto>builder()
                .status("success")
                .message("Usuario creado exitosamente")
                .data(usuarioDto)
                .build());
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los datos de un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDto>> update(@PathVariable Long id, @RequestBody UsuarioUpdateDto usuarioUpdateDto) {
        UsuarioDto usuarioDto = usuarioService.update(id, usuarioUpdateDto);
        return ResponseEntity.ok(ApiResponse.<UsuarioDto>builder()
                .status("success")
                .message("Usuario actualizado exitosamente")
                .data(usuarioDto)
                .build());
    }

    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario por su identificador")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioDto>> findById(@PathVariable Long id) {
        UsuarioDto usuarioDto = usuarioService.findById(id);
        return ResponseEntity.ok(ApiResponse.<UsuarioDto>builder()
                .status("success")
                .message("Usuario encontrado")
                .data(usuarioDto)
                .build());
    }

    @Operation(summary = "Listar todos los usuarios", description = "Obtiene una lista de todos los usuarios")
    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioDto>>> findAll() {
        List<UsuarioDto> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(ApiResponse.<List<UsuarioDto>>builder()
                .status("success")
                .message("Lista de usuarios obtenida")
                .data(usuarios)
                .build());
    }

    @Operation(summary = "Eliminar usuario (borrado lógico)", description = "Marca un usuario como inactivo")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.status(204).body(ApiResponse.<Void>builder()
                .status("success")
                .message("Usuario eliminado ")
                .build());
    }
}

