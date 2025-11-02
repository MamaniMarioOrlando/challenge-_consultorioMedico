package com.codemized.challenge.consultorioMedico.exception;

public class MedicoNotFoundException extends RuntimeException{
    public MedicoNotFoundException(Long id) {
        super("Medico no encontrado con id: " + id);
    }
}
