package com.bauti.piazolla_gestion.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Boxeador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ApiModelProperty(required = true, example = "Diego")
    private String nombre;
    @ApiModelProperty(required = true, example = "Gutierrez")
    private String apellido;
    @ApiModelProperty(required = true, example = "74.23")
    private Double peso_kg;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonBackReference
    @ApiModelProperty(hidden = true)
    private Categoria categoria;

    @ApiModelProperty(hidden = true)
    @Column(name = "fecha_inscripcion")
    private Timestamp fechaInscripcion;
}
