package com.bauti.piazolla_gestion.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EntrenadorTests {

    @Test
    public void testCreateEntrenador() {
        Entrenador entrenador = new Entrenador();
        assertNotNull(entrenador);
    }

    @Test
    public void testEntrenadorFields() {
        Entrenador entrenador = new Entrenador();
        entrenador.setId(1L);
        entrenador.setNombre("Juan");
        entrenador.setApellido("Cito");

        assertEquals(1L, entrenador.getId());
        assertEquals("Juan", entrenador.getNombre());
        assertEquals("Cito", entrenador.getApellido());
    }

    @Test
    public void testEntrenadorCategoriaRelationship() {
        Entrenador entrenador = new Entrenador();
        Categoria categoria1 = new Categoria();
        categoria1.setEntrenador(entrenador);
        Categoria categoria2 = new Categoria();
        categoria2.setEntrenador(entrenador);

        Set<Categoria> categorias = new HashSet<>();
        categorias.add(categoria1);
        categorias.add(categoria2);

        entrenador.setCategorias(categorias);

        assertEquals(2, entrenador.getCategorias().size());
        assertTrue(entrenador.getCategorias().contains(categoria1));
        assertTrue(entrenador.getCategorias().contains(categoria2));

        for (Categoria categoria : entrenador.getCategorias()) {
            assertEquals(entrenador, categoria.getEntrenador());
        }
    }
}
