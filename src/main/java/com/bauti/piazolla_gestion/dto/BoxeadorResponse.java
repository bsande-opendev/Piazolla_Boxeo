package com.bauti.piazolla_gestion.dto;

import com.bauti.piazolla_gestion.entities.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class BoxeadorResponse {

    private Long id;
    private String nombre;
    private String apellido;
    private Double peso_kg;
    private String categoria;
    private Timestamp fecha_inscripcion;
}
