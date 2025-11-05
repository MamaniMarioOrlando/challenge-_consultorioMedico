package com.codemized.challenge.consultorioMedico.exception;

public class UsuarioAlreadyExistsException extends RuntimeException {
    public UsuarioAlreadyExistsException(String email) {
        super("Ya existe un usuario con el email: " + email);
    }
}
