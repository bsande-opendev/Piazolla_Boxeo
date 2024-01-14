package com.bauti.piazolla_gestion.controllers;

import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.exceptions.LimiteAlumnosExcedidoException;
import com.bauti.piazolla_gestion.services.BoxeadorService;
import com.bauti.piazolla_gestion.utils.ResponseConstants;
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
    public ResponseEntity getAllBoxeadores(){
        try {
            return new ResponseEntity<>(boxeadorService.getAllBoxeadores(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getBoxeadorById(@PathVariable Long id){
        try {
            return new ResponseEntity<>(boxeadorService.getBoxeadorById(id), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(ResponseConstants.BOXEADOR_NOT_FOUND, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
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
    public ResponseEntity deleteBoxeadorById(@PathVariable Long id){
        try {
            boxeadorService.deleteBoxeadorById(id);
            return new ResponseEntity<>(ResponseConstants.BOXEADOR_ELIMINADO, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(ResponseConstants.SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
