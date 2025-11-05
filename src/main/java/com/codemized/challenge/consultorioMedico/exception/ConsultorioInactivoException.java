package com.codemized.challenge.consultorioMedico.exception;

public class ConsultorioInactivoException extends RuntimeException{
    public ConsultorioInactivoException(Long id) {
        super("El consultorio con id " + id + " está inactivo.");
    }
}
