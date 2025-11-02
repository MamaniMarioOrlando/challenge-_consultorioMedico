package com.codemized.challenge.consultorioMedico.service.impl;

import com.codemized.challenge.consultorioMedico.dto.usuario.*;
import com.codemized.challenge.consultorioMedico.exception.UsuarioAlreadyExistsException;
import com.codemized.challenge.consultorioMedico.exception.UsuarioNotFoundException;
import com.codemized.challenge.consultorioMedico.model.Usuario;
import com.codemized.challenge.consultorioMedico.repository.UsuarioRepository;
import com.codemized.challenge.consultorioMedico.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDto save(UsuarioCreateDto usuarioCreateDto) {
        if (usuarioRepository.findByEmail(usuarioCreateDto.getEmail()).isPresent()) {
            throw new UsuarioAlreadyExistsException(usuarioCreateDto.getEmail());
        }
        Usuario usuario = UsuarioMapper.toEntity(usuarioCreateDto);
        Usuario saved = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(saved);
    }

    @Override
    public UsuarioDto update(Long id, UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuario = usuarioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        UsuarioMapper.updateEntityFromDto(usuarioUpdateDto, usuario);
        Usuario updated = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(updated);
    }

    @Override
    public UsuarioDto findById(Long id) {
        Usuario usuario = usuarioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        return UsuarioMapper.toDto(usuario);
    }

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAllByActivoTrue().stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }
}
