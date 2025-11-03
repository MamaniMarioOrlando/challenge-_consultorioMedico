package com.codemized.challenge.consultorioMedico.exception;

public class ConsultorioNotFoundException extends RuntimeException{
    public ConsultorioNotFoundException(Long id) {
        super("Consultorio no encontrado con id: " + id);
    }
}
