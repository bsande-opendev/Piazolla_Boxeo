package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.entities.Entrenador;
import com.bauti.piazolla_gestion.exceptions.LimiteAlumnosExcedidoException;
import com.bauti.piazolla_gestion.repositories.BoxeadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoxeadorService {
    @Autowired
    private BoxeadorRepository boxeadorRepository;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private EntrenadorService entrenadorService;

    public BoxeadorResponse createBoxeador(Boxeador boxeador){
        boxeador.setCategoria(categoriaService.getCategoriaByPeso(boxeador.getPeso_kg()));
        boxeador.setFechaInscripcion(Timestamp.valueOf(LocalDateTime.now()));
        //Si el entrenador tiene disponibilidad, lo asigno. Si no tiene disponibilidad, tiro exception.
        if (entrenadorService.verificarDisponibilidadByEntrenador(boxeador.getCategoria().getEntrenador())){
            return getBoxeadorResponseDto(boxeadorRepository.save(boxeador));
        } else {
            throw new LimiteAlumnosExcedidoException();
        }
    }

    public BoxeadorResponse getBoxeadorResponseDto(Boxeador boxeador){
        BoxeadorResponse boxeadorResponse = new BoxeadorResponse();

        boxeadorResponse.setId(boxeador.getId());
        boxeadorResponse.setNombre(boxeador.getNombre());
        boxeadorResponse.setApellido(boxeador.getApellido());
        boxeadorResponse.setPeso_kg(boxeador.getPeso_kg());
        boxeadorResponse.setCategoria(boxeador.getCategoria().getNombre());
        boxeadorResponse.setFecha_inscripcion(boxeador.getFechaInscripcion());

        return boxeadorResponse;
    }

    public List<BoxeadorResponse> getBoxeadorResponseDto(List<Boxeador> boxeadorList){
        List<BoxeadorResponse> lstToReturn = new ArrayList<BoxeadorResponse>();
        boxeadorList.forEach(boxeador -> {
            BoxeadorResponse response = new BoxeadorResponse();

            response.setId(boxeador.getId());
            response.setNombre(boxeador.getNombre());
            response.setApellido(boxeador.getApellido());
            response.setPeso_kg(boxeador.getPeso_kg());
            response.setCategoria(boxeador.getCategoria().getNombre());
            response.setFecha_inscripcion(boxeador.getFechaInscripcion());

            lstToReturn.add(response);
        });

        return lstToReturn;
    }

    public List<BoxeadorResponse> obtenerAltasDiarias() {
        Timestamp fechaActual = Timestamp.valueOf(LocalDate.now().atStartOfDay());

        return getBoxeadorResponseDto(boxeadorRepository.findByFechaInscripcionBetween(
                fechaActual,
                Timestamp.valueOf(fechaActual.toLocalDateTime().toLocalDate().plusDays(1).atStartOfDay())
        ));


    }
    public void deleteBoxeador(Boxeador boxeador){
        boxeadorRepository.delete(boxeador);
    }

    public void deleteBoxeadorById(Long id){
        boxeadorRepository.deleteById(id);
    }

    public BoxeadorResponse getBoxeadorById(Long id){
        return getBoxeadorResponseDto(boxeadorRepository.findById(id).orElseThrow());
    }

    public List<BoxeadorResponse> getAllBoxeadores(){
        return getBoxeadorResponseDto(boxeadorRepository.findAll());
    }

}
