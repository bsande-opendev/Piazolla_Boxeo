package com.bauti.piazolla_gestion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LimiteAlumnosExcedidoException extends RuntimeException {
    public LimiteAlumnosExcedidoException() {
        super("El entrenador ya tiene más de 5 alumnos asignados.");
    }
}
