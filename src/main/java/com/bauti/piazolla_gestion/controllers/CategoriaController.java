package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.services.CategoriaService;
import com.bauti.piazolla_gestion.utils.ResponseConstants;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("/categorias")
@Controller
@CommonsLog
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    @Operation(summary = "Obtiene todas las categorias", description = "Devuelve un array que contiene todas las categorias del gimnasio")
    public ResponseEntity getAllCategorias(){
        try {
            return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
        } catch (Exception e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una categoria", description = "Devuelve la categoría con el id especificado")
    public ResponseEntity getCategoriaById(@PathVariable Long id){
        try {
            return new ResponseEntity(categoriaService.getCategoriaById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity<>(ResponseConstants.CATEGORIA_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Crea una categoria", description = "Crea una categoría y luego la devuelve")
    public ResponseEntity createCategoria(@RequestBody Categoria categoria){
        try {
            return new ResponseEntity(categoriaService.createOrUpdateCategoria(categoria), HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    @Operation(summary = "Modifica una categoria", description = "Modifica una categoría y luego la devuelve")
    public ResponseEntity updateCategoria(@RequestBody Categoria categoria){
        try {
            return new ResponseEntity(categoriaService.createOrUpdateCategoria(categoria), HttpStatus.OK);
        } catch (Exception e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una categoria", description = "Elimina la categoría con el id especificado")
    public ResponseEntity deleteCategoriaById(@PathVariable Long id){
        try {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            log.error("Error en CategoriaController: " + e.getMessage(), e);
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
