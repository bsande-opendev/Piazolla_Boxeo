package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.entities.Entrenador;
import com.bauti.piazolla_gestion.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntrenadorService {
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    @Autowired
    private CategoriaService categoriaService;

    public boolean verificarDisponibilidadByEntrenador(Entrenador entrenador) {
        long alumnos = entrenador.getCategorias().stream()
                .mapToLong(categoria -> categoriaService.getAlumnosByCategoria(categoria))
                .sum();

        return alumnos < 5;
    }

    public Entrenador createEntrenador(Entrenador entrenador){
        return entrenadorRepository.save(entrenador);
    }

    public void deleteEntrenador(Entrenador entrenador){
        entrenadorRepository.delete(entrenador);
    }

}
