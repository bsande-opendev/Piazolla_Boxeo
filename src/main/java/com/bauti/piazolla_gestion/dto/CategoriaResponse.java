package com.bauti.piazolla_gestion.dto;

import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.entities.Entrenador;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaResponse {
    private Long id;
    private String nombre;
    private Double minimo_kg;
    private Double maximo_kg;
    private Set<Boxeador> boxeadores;
    private String entrenador;

}
