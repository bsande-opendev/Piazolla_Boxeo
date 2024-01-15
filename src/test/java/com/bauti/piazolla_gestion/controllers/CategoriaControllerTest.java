package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.dto.CategoriaResponse;
import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.services.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest
class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    @Test
    void getAllCategorias() {
        when(categoriaService.getAllCategorias()).thenReturn(Collections.emptyList());

        ResponseEntity response = categoriaController.getAllCategorias();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaService, times(1)).getAllCategorias();
    }

    @Test
    void getAllCategoriasError(){
        doThrow(new NoSuchElementException()).when(categoriaService).getAllCategorias();

        ResponseEntity response = categoriaController.getAllCategorias();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getCategoriaById() {
        Long categoryId = 1L;

        when(categoriaService.getCategoriaById(categoryId)).thenReturn(Mockito.mock(CategoriaResponse.class));

        ResponseEntity response = categoriaController.getCategoriaById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaService, times(1)).getCategoriaById(categoryId);
    }

    @Test
    void getCategoriaByIdDevuelveNotFound() {
        Long categoriaId = 1L;
        when(categoriaService.getCategoriaById(categoriaId)).thenThrow(new NoSuchElementException());

        ResponseEntity response = categoriaController.getCategoriaById(categoriaId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoriaService, times(1)).getCategoriaById(categoriaId);
    }

    @Test
    void getCategoriaByIdError(){
        doThrow(new EntityNotFoundException()).when(categoriaService).getCategoriaById(any());

        ResponseEntity response = categoriaController.getCategoriaById(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void createCategoria() {
        Categoria categoria = new Categoria();
        when(categoriaService.createOrUpdateCategoria(any())).thenReturn(categoria);

        ResponseEntity response = categoriaController.createCategoria(categoria);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(categoriaService, times(1)).createOrUpdateCategoria(categoria);
    }

    @Test
    void updateCategoria() {
        Categoria categoria = mock(Categoria.class);
        when(categoriaService.createOrUpdateCategoria(any())).thenReturn(categoria);

        ResponseEntity response = categoriaController.updateCategoria(categoria);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaService, times(1)).createOrUpdateCategoria(categoria);
    }

    @Test
    void updateCategoriaError(){
        Categoria categoria = new Categoria();
        doThrow(new EntityNotFoundException()).when(categoriaService).createOrUpdateCategoria(any());

        ResponseEntity response = categoriaController.updateCategoria(categoria);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteCategoriaById() {
        Long categoryId = 1L;


        ResponseEntity response = categoriaController.deleteCategoriaById(categoryId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(categoriaService, times(1)).deleteCategoria(categoryId);
    }

    @Test
    void deleteCategoriaByIdError(){
        doThrow(new NoSuchElementException()).when(categoriaService).deleteCategoria(any());

        ResponseEntity response = categoriaController.deleteCategoriaById(1L);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
