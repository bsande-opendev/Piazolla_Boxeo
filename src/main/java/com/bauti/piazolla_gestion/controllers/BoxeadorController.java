package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.exceptions.LimiteAlumnosExcedidoException;
import com.bauti.piazolla_gestion.services.BoxeadorService;
import com.bauti.piazolla_gestion.utils.ResponseConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/boxeadores")
public class BoxeadorController {
    @Autowired
    BoxeadorService boxeadorService;

    @GetMapping
    @Operation(summary = "Obtiene todos los boxeadores", description = "Devuelve un array con todos los boxeadores")
    public ResponseEntity getAllBoxeadores(){
        try {
            return new ResponseEntity<>(boxeadorService.getAllBoxeadores(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un boxeador", description = "Devuelve el boxeador con el id especificado")
    public ResponseEntity getBoxeadorById(@PathVariable @Parameter(name = "id", description = "id del Boxeador", example = "1") Long id){
        try {
            return new ResponseEntity<>(boxeadorService.getBoxeadorById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(ResponseConstants.BOXEADOR_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Da de alta un boxeador", description = "Crea un boxeador, lo asigna a una categoria y luego devuelve los datos")
    public ResponseEntity createBoxeador(@RequestBody Boxeador boxeador){
        try {
            return new ResponseEntity<>(boxeadorService.createBoxeador(boxeador), HttpStatus.CREATED);
        } catch (LimiteAlumnosExcedidoException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }  catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un boxeador", description = "Elimina el boxeador según el Id especificado")
    public ResponseEntity deleteBoxeadorById(@PathVariable Long id){
        try {
            boxeadorService.deleteBoxeadorById(id);
            return new ResponseEntity<>(ResponseConstants.BOXEADOR_ELIMINADO, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
