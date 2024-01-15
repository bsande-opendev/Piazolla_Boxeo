package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.entities.Entrenador;
import com.bauti.piazolla_gestion.repositories.EntrenadorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EntrenadorServiceTest {

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private EntrenadorService entrenadorService;

    @Test
    void testVerificarDisponibilidadByEntrenadorCon5Asignados(){
        Entrenador entrenador = new Entrenador();
        Set<Categoria> categorias = new HashSet<>();
        Categoria categoria1 = new Categoria();
        Categoria categoria2 = new Categoria();
        categorias.add(categoria1);
        categorias.add(categoria2);
        entrenador.setCategorias(categorias);

        //Devuelvo 3 y 2 boxeadores y debería de darme false
        Mockito.when(categoriaService.getAlumnosByCategoria(categoria1)).thenReturn(3L);
        Mockito.when(categoriaService.getAlumnosByCategoria(categoria2)).thenReturn(2L);

        assertFalse(entrenadorService.verificarDisponibilidadByEntrenador(entrenador));
    }

    @Test
    void testVerificarDisponibilidadByEntrenadorCon4Asignados(){
        Entrenador entrenador = new Entrenador();
        Set<Categoria> categorias = new HashSet<>();
        Categoria categoria1 = new Categoria();
        Categoria categoria2 = new Categoria();
        categorias.add(categoria1);
        categorias.add(categoria2);
        entrenador.setCategorias(categorias);

        //Devuelvo 3 y 2 boxeadores y debería de darme false
        Mockito.when(categoriaService.getAlumnosByCategoria(categoria1)).thenReturn(2L);
        Mockito.when(categoriaService.getAlumnosByCategoria(categoria2)).thenReturn(2L);

        assertTrue(entrenadorService.verificarDisponibilidadByEntrenador(entrenador));
    }
}
