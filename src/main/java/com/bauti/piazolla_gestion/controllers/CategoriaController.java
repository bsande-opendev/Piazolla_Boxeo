package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.services.CategoriaService;
import com.bauti.piazolla_gestion.utils.ResponseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("/categorias")
@Controller
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity getAllCategorias(){
        try {
            return new ResponseEntity<>(categoriaService.getAllCategorias(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoriaById(@PathVariable Long id){
        try {
            return new ResponseEntity(categoriaService.getCategoriaById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(ResponseConstants.CATEGORIA_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity createCategoria(@RequestBody Categoria categoria){
        try {
            return new ResponseEntity(categoriaService.createOrUpdateCategoria(categoria), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping
    public ResponseEntity updateCategoria(@RequestBody Categoria categoria){
        try {
            return new ResponseEntity(categoriaService.createOrUpdateCategoria(categoria), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategoriaById(@PathVariable Long id){
        try {
            categoriaService.deleteCategoria(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
