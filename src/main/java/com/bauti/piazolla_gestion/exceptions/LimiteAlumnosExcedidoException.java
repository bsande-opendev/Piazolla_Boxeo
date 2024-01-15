package com.bauti.piazolla_gestion.exceptions;

import com.bauti.piazolla_gestion.utils.ResponseConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class LimiteAlumnosExcedidoException extends RuntimeException {
    public LimiteAlumnosExcedidoException() {
        super(ResponseConstants.LIMITE_BOXEADORES_EXCEDIDO);
    }
}
