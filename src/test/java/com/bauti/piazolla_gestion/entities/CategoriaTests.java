package com.bauti.piazolla_gestion.entities;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoriaTests {

    @Test
    void testCreateCategoria() {
        Categoria categoria = new Categoria();
        assertNotNull(categoria);
    }

    @Test
    void testCategoriaFields() {
        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Welter");
        categoria.setMinimo_kg(60.0);
        categoria.setMaximo_kg(70.0);

        assertEquals(1L, categoria.getId());
        assertEquals("Welter", categoria.getNombre());
        assertEquals(60.0, categoria.getMinimo_kg());
        assertEquals(70.0, categoria.getMaximo_kg());
    }

    @Test
    void testCategoriaEntrenadorRelationship() {
        Categoria categoria = new Categoria();
        Entrenador entrenador = Mockito.mock(Entrenador.class);

        categoria.setEntrenador(entrenador);
        assertEquals(entrenador, categoria.getEntrenador());
    }

    @Test
    void testCategoriaBoxeadoresRelationship() {
        Categoria categoria = new Categoria();
        Boxeador boxeador1 = new Boxeador();
        Boxeador boxeador2 = new Boxeador();
        boxeador1.setCategoria(categoria);
        boxeador2.setCategoria(categoria);

        Set<Boxeador> boxeadores = new HashSet<>();
        boxeadores.add(boxeador1);
        boxeadores.add(boxeador2);

        categoria.setBoxeadores(boxeadores);

        assertEquals(2, categoria.getBoxeadores().size());
        assertTrue(categoria.getBoxeadores().contains(boxeador1));
        assertTrue(categoria.getBoxeadores().contains(boxeador2));

        for (Boxeador boxeador : categoria.getBoxeadores()) {
            assertEquals(categoria, boxeador.getCategoria());
        }
    }
}
