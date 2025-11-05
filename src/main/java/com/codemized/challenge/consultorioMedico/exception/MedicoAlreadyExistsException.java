package com.codemized.challenge.consultorioMedico.exception;

public class MedicoAlreadyExistsException extends RuntimeException{
    public MedicoAlreadyExistsException(String matricula) {
        super("Ya existe un médico con la matrícula: " + matricula);
    }
}
