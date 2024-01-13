package com.bauti.piazolla_gestion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.*;
import java.util.Set;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double minimo_kg;
    private Double maximo_kg;
    @ManyToMany(mappedBy = "categorias")
    Set<Entrenador> entrenadores;
    @OneToMany(mappedBy = "categoria")
    Set<Boxeador> boxeadores;
}
