package com.bauti.piazolla_gestion.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
class BoxeadorTests {

    @Test
    void testCreateBoxeador(){
        Boxeador boxeador = new Boxeador();
        assertNotNull(boxeador);
    }

    @Test
    void testBoxeadorFields(){
        Boxeador boxeador = new Boxeador();
        Categoria categoria = Mockito.mock(Categoria.class);
        Timestamp ahora = Timestamp.valueOf(LocalDateTime.now());

        boxeador.setId(1L);
        boxeador.setNombre("Juan");
        boxeador.setApellido("Cito");
        boxeador.setPeso_kg(65.7);
        boxeador.setCategoria(categoria);
        boxeador.setFechaInscripcion(ahora);

        assertEquals(1L, boxeador.getId());
        assertEquals("Juan", boxeador.getNombre());
        assertEquals("Cito", boxeador.getApellido());
        assertEquals(65.7, boxeador.getPeso_kg());
        assertEquals(categoria, boxeador.getCategoria());
        assertEquals(ahora, boxeador.getFechaInscripcion());
    }

}
