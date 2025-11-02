package com.codemized.challenge.consultorioMedico.exception;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(Long id) {
        super("Usuario no encontrado con id: " + id);
    }
    public UsuarioNotFoundException(String email) {
        super("Usuario no encontrado con email: " + email);
    }
}
