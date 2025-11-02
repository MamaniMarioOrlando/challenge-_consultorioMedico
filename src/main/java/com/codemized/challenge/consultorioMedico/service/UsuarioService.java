package com.codemized.challenge.consultorioMedico.service;

import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioCreateDto;
import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioDto;
import com.codemized.challenge.consultorioMedico.dto.usuario.UsuarioUpdateDto;

import java.util.List;

public interface UsuarioService {
    UsuarioDto save(UsuarioCreateDto usuarioCreateDto);
    UsuarioDto update(Long id, UsuarioUpdateDto usuarioUpdateDto);
    UsuarioDto findById(Long id);
    List<UsuarioDto> findAll();
    void deleteById(Long id);
}
