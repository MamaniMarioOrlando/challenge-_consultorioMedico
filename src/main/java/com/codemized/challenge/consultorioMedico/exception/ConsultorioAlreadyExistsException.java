package com.codemized.challenge.consultorioMedico.exception;

public class ConsultorioAlreadyExistsException extends RuntimeException {
    public ConsultorioAlreadyExistsException(String campo, String valor) {
        super("Ya existe un consultorio con " + campo + ": " + valor);
    }
}
