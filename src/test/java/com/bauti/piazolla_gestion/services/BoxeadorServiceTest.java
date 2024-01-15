package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.entities.Entrenador;
import com.bauti.piazolla_gestion.exceptions.LimiteAlumnosExcedidoException;
import com.bauti.piazolla_gestion.repositories.BoxeadorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BoxeadorServiceTest {

    @Mock
    private BoxeadorRepository boxeadorRepository;

    @Mock
    private CategoriaService categoriaService;

    @Mock
    private EntrenadorService entrenadorService;

    @InjectMocks
    private BoxeadorService boxeadorService;


    @Test
    void testCrearBoxeadorEntrenadorDisponible() {
        Boxeador boxeador = mock(Boxeador.class);

        when(boxeador.getCategoria()).thenReturn(mock(Categoria.class));
        when(boxeador.getCategoria().getEntrenador()).thenReturn(mock(Entrenador.class));
        when(categoriaService.getCategoriaByPeso(anyDouble())).thenReturn(mock(Categoria.class));
        when(entrenadorService.verificarDisponibilidadByEntrenador(any(Entrenador.class))).thenReturn(true);
        when(boxeadorRepository.save(any(Boxeador.class))).thenReturn(boxeador);

        BoxeadorResponse result = boxeadorService.createBoxeador(boxeador);
        assertNotNull(result);
    }

    @Test
    void testCrearBoxeadorLimiteEntrenadorExcedidoThrowsCustomException() {
        Boxeador boxeador = mock(Boxeador.class);
        when(boxeador.getCategoria()).thenReturn(mock(Categoria.class));
        when(boxeador.getCategoria().getEntrenador()).thenReturn(mock(Entrenador.class));
        when(categoriaService.getCategoriaByPeso(anyDouble())).thenReturn(new Categoria());
        when(entrenadorService.verificarDisponibilidadByEntrenador(any(Entrenador.class))).thenReturn(false);

        assertThrows(LimiteAlumnosExcedidoException.class, () -> boxeadorService.createBoxeador(boxeador));
    }

    @Test
    void testGetAllBoxeadores() {
        List<Boxeador> boxeadorList = new ArrayList<>();
        Boxeador boxeador1 = mock(Boxeador.class);
        Boxeador boxeador2 = mock(Boxeador.class);
        Boxeador boxeador3 = mock(Boxeador.class);

        Categoria categoriaMock = mock(Categoria.class);

        when(boxeador1.getCategoria()).thenReturn(categoriaMock);
        when(boxeador2.getCategoria()).thenReturn(categoriaMock);
        when(boxeador3.getCategoria()).thenReturn(categoriaMock);

        boxeadorList.add(boxeador1);
        boxeadorList.add(boxeador2);
        boxeadorList.add(boxeador3);

        when(boxeadorRepository.findAll()).thenReturn(boxeadorList);
        List<BoxeadorResponse> result = boxeadorService.getAllBoxeadores();

        assertNotNull(result);
    }
}