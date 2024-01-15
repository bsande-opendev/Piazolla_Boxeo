package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.controllers.BoxeadorController;
import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.exceptions.LimiteAlumnosExcedidoException;
import com.bauti.piazolla_gestion.services.BoxeadorService;
import com.bauti.piazolla_gestion.tasks.ReporteAltasTask;
import com.bauti.piazolla_gestion.utils.ResponseConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BoxeadorControllerTest {

    @InjectMocks
    private BoxeadorController boxeadorController;

    @Mock
    private BoxeadorService boxeadorService;

    @Mock
    private ReporteAltasTask reporteAltasTask;

    @Test
    void testGetAllBoxeadores() {
        when(boxeadorService.getAllBoxeadores()).thenReturn(Collections.emptyList());

        ResponseEntity responseEntity = boxeadorController.getAllBoxeadores();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.emptyList(), responseEntity.getBody());
    }
    @Test
    void testGetAllBoxeadoresFail(){
        when(boxeadorService.getAllBoxeadores()).thenThrow(NoSuchElementException.class);

        ResponseEntity responseEntity = boxeadorController.getAllBoxeadores();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetBoxeadorById() {
        Long id = 1L;
        BoxeadorResponse boxeadorResponse = new BoxeadorResponse();
        when(boxeadorService.getBoxeadorById(id)).thenReturn(boxeadorResponse);

        ResponseEntity responseEntity = boxeadorController.getBoxeadorById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boxeadorResponse, responseEntity.getBody());
    }

    @Test
    void testGetBoxeadorByIdNotFound() {
        Long id = 1L;
        when(boxeadorService.getBoxeadorById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity responseEntity = boxeadorController.getBoxeadorById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(ResponseConstants.BOXEADOR_NOT_FOUND, responseEntity.getBody());
    }
    @Test
    void testGetBoxeadorByIdError() {
        Long id = 1L;
        when(boxeadorService.getBoxeadorById(id)).thenThrow(EntityNotFoundException.class);

        ResponseEntity responseEntity = boxeadorController.getBoxeadorById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }


    @Test
    void testCreateBoxeador() {
        Boxeador boxeador = new Boxeador();
        when(boxeadorService.createBoxeador(any())).thenReturn(new BoxeadorResponse());

        ResponseEntity responseEntity = boxeadorController.createBoxeador(boxeador);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void testCreateBoxeadorLimiteAlumnosExcedido() {
        Boxeador boxeador = new Boxeador();
        doThrow(new LimiteAlumnosExcedidoException()).when(boxeadorService).createBoxeador(any());

        ResponseEntity responseEntity = boxeadorController.createBoxeador(boxeador);

        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
        assertEquals(ResponseConstants.LIMITE_BOXEADORES_EXCEDIDO, responseEntity.getBody());
    }
    @Test
    void testCreateBoxeadorError(){
        Boxeador boxeador = new Boxeador();
        doThrow(new EntityNotFoundException()).when(boxeadorService).createBoxeador(any());

        ResponseEntity responseEntity = boxeadorController.createBoxeador(boxeador);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteBoxeadorById() {
        Long id = 1L;

        ResponseEntity responseEntity = boxeadorController.deleteBoxeadorById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(ResponseConstants.BOXEADOR_ELIMINADO, responseEntity.getBody());

        verify(boxeadorService).deleteBoxeadorById(id);
    }

    @Test
    void testDeleteBoxeadorByIdError(){
        Long id = 1L;

        doThrow(new EntityNotFoundException()).when(boxeadorService).deleteBoxeadorById(any());

        ResponseEntity responseEntity = boxeadorController.deleteBoxeadorById(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
    @Test
    void testImprimir() {
        ResponseEntity responseEntity = boxeadorController.imprimir();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(reporteAltasTask).imprimirReporteAltasDiario();
    }
}
