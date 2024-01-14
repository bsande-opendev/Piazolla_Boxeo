package com.bauti.piazolla_gestion.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
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
@ApiModel
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double minimo_kg;
    private Double maximo_kg;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    @JsonBackReference
    private Entrenador entrenador;

    @OneToMany(mappedBy = "categoria")
    @JsonManagedReference
    private Set<Boxeador> boxeadores;
}
