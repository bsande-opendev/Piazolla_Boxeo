package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.dto.CategoriaResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.entities.Entrenador;
import com.bauti.piazolla_gestion.repositories.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void testGetAlumnosByCategoriaReturns8(){
        Categoria categoria = Mockito.mock(Categoria.class);
        Mockito.when(categoriaRepository.countBoxeadoresByCategoria(categoria)).thenReturn(8L);


        assertEquals(8L, categoriaService.getAlumnosByCategoria(categoria));

    }

    @Test
    void testGetCategoriaByPesoDebajoMinimo(){
        Double peso = 30.0;

        Categoria categoriaMinima = new Categoria();
        categoriaMinima.setMinimo_kg(60.0);


        Mockito.when(categoriaRepository.findCategoriaPesoMinimo()).thenReturn(Optional.of(categoriaMinima));

        Categoria resultado = categoriaService.getCategoriaByPeso(peso);

        assertEquals(categoriaMinima, resultado);
    }

    @Test
    void testGetCategoriaByPesoSobreMaximo() {
        Double peso = 113.45;

        Categoria categoriaMaxima = new Categoria();
        categoriaMaxima.setMinimo_kg(90.1);
        categoriaMaxima.setMaximo_kg(null);
        Mockito.when(categoriaRepository.findCategoriaByPeso(peso)).thenReturn(Optional.of(categoriaMaxima));

        Categoria categoriaMinima = new Categoria();
        categoriaMinima.setMinimo_kg(60.0);
        Mockito.when(categoriaRepository.findCategoriaPesoMinimo()).thenReturn(Optional.of(categoriaMinima));

        Categoria resultado = categoriaService.getCategoriaByPeso(peso);

        assertEquals(categoriaMaxima, resultado);
    }

    @Test
    void testGetCategoriaByPesoInferiorMasCercana() {
        Double peso = 57.2;

        Categoria categoriaMinima = new Categoria();
        categoriaMinima.setMinimo_kg(48.23);
        categoriaMinima.setMaximo_kg(56.8);

        Mockito.when(categoriaRepository.findCategoriaPesoMinimo()).thenReturn(Optional.of(categoriaMinima));
        Mockito.when(categoriaRepository.findCategoriaByPeso(peso)).thenReturn(Optional.empty());

        Categoria categoriaMaxima = new Categoria();
        categoriaMaxima.setMinimo_kg(58.1);
        categoriaMaxima.setMaximo_kg(67.23);

        Mockito.when(categoriaRepository.findCategoriasInferioresMasCercanas(peso)).thenReturn(Stream.of(categoriaMinima));

        Categoria resultado = categoriaService.getCategoriaByPeso(peso);

        assertEquals(categoriaMinima, resultado);

    }

    @Test
    void testCreateOrUpdateCategoria() {
        Categoria inputCategoria = Mockito.mock(Categoria.class);
        Mockito.when(categoriaRepository.save(inputCategoria)).thenReturn(inputCategoria);

        Categoria result = categoriaService.createOrUpdateCategoria(inputCategoria);

        assertEquals(inputCategoria, result);
        Mockito.verify(categoriaRepository, Mockito.times(1)).save(inputCategoria);
    }

    @Test
    public void testGetCategoriaResponse() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Plumin");
        categoria.setMinimo_kg(20.0);
        categoria.setMaximo_kg(30.0);

        Set<Boxeador> boxeadores = new HashSet<>();
        boxeadores.add(Mockito.mock(Boxeador.class));
        boxeadores.add(Mockito.mock(Boxeador.class));
        categoria.setBoxeadores(boxeadores);


        Entrenador entrenador = Mockito.mock(Entrenador.class);
        categoria.setEntrenador(entrenador);

        String nombre = entrenador.getNombre() + " " + entrenador.getApellido();

        CategoriaResponse categoriaResponse = categoriaService.getCategoriaResponse(categoria);

        assertEquals(categoria.getId(), categoriaResponse.getId());
        assertEquals(categoria.getNombre(), categoriaResponse.getNombre());
        assertEquals(categoria.getMinimo_kg(), categoriaResponse.getMinimo_kg());
        assertEquals(categoria.getMaximo_kg(), categoriaResponse.getMaximo_kg());
        assertEquals(categoria.getBoxeadores(), categoriaResponse.getBoxeadores());
        assertEquals(nombre, categoriaResponse.getEntrenador());

    }

    @Test
    public void testGetCategoriaResponseLst() {
        List<Categoria> categoriaList = new ArrayList<>();
        Categoria cat1 = Mockito.mock(Categoria.class);
        Categoria cat2 = Mockito.mock(Categoria.class);
        Entrenador entrenador1 = Mockito.mock(Entrenador.class);

        Mockito.when(entrenador1.getNombre()).thenReturn("Juan");
        Mockito.when(entrenador1.getApellido()).thenReturn("Luis");
        Mockito.when(cat1.getEntrenador()).thenReturn(entrenador1);

        Entrenador entrenador2 = Mockito.mock(Entrenador.class);
        Mockito.when(entrenador2.getNombre()).thenReturn("Juana");
        Mockito.when(entrenador2.getApellido()).thenReturn("Luisa");

        Mockito.when(cat2.getEntrenador()).thenReturn(entrenador2);

        categoriaList.add(cat1);
        categoriaList.add(cat2);

        List<CategoriaResponse> categoriaResponseList = categoriaService.getCategoriaResponseLst(categoriaList);


        assertEquals(2, categoriaResponseList.size());

        CategoriaResponse response1 = categoriaResponseList.get(0);
        assertEquals(cat1.getId(), response1.getId());
        assertEquals(cat1.getNombre(), response1.getNombre());
        assertEquals(cat1.getMinimo_kg(), response1.getMinimo_kg());
        assertEquals(cat1.getMaximo_kg(), response1.getMaximo_kg());

        CategoriaResponse response2 = categoriaResponseList.get(1);
        assertEquals(cat2.getId(), response2.getId());
        assertEquals(cat2.getNombre(), response2.getNombre());
        assertEquals(cat2.getMinimo_kg(), response2.getMinimo_kg());
        assertEquals(cat2.getMaximo_kg(), response2.getMaximo_kg());
    }
    @Test
    public void testGetCategoriaById() {

        Categoria categoria = new Categoria();
        categoria.setId(1L);
        categoria.setNombre("Plumin");
        categoria.setMinimo_kg(26.8);
        categoria.setMaximo_kg(33.2);

        Entrenador entrenador = new Entrenador();
        entrenador.setNombre("Juan");
        entrenador.setApellido("Cito");
        categoria.setEntrenador(entrenador);

        Mockito.when(categoriaRepository.findById(1L)).thenReturn(java.util.Optional.of(categoria));

        CategoriaResponse categoriaResponse = categoriaService.getCategoriaById(1L);

        assertEquals(categoria.getId(), categoriaResponse.getId());
        assertEquals(categoria.getNombre(), categoriaResponse.getNombre());
        assertEquals(categoria.getMinimo_kg(), categoriaResponse.getMinimo_kg());
        assertEquals(categoria.getMaximo_kg(), categoriaResponse.getMaximo_kg());
        assertEquals(entrenador.getNombre() + " " + entrenador.getApellido(), categoriaResponse.getEntrenador());
    }

}
