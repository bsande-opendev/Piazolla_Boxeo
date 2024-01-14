package com.bauti.piazolla_gestion.services;

import com.bauti.piazolla_gestion.dto.CategoriaResponse;
import com.bauti.piazolla_gestion.entities.Categoria;
import com.bauti.piazolla_gestion.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Long getAlumnosByCategoria(Categoria categoria){
        return categoriaRepository.countBoxeadoresByCategoria(categoria);
    }


    @Transactional
    public Categoria getCategoriaByPeso(Double peso){

        //Si el peso es menor que el minimo de la menor categoria, se le asigna esa categoria
        Categoria categoriaMinima = categoriaRepository.findCategoriaPesoMinimo().orElseThrow();
        if(peso < categoriaMinima.getMinimo_kg()) return categoriaMinima;

        //Busco la categoría que corresponde, si no existe devuelvo la inferior mas cercana
        return categoriaRepository.findCategoriaByPeso(peso).orElseGet(() -> categoriaRepository.findCategoriasInferioresMasCercanas(peso).findFirst().orElseThrow());
    }

    public Categoria createOrUpdateCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id){
        categoriaRepository.deleteById(id);
    }

    public List<CategoriaResponse> getAllCategorias(){
        return getCategoriaResponseLst(categoriaRepository.findAll());
    }

    public CategoriaResponse getCategoriaById(Long id){
        return getCategoriaResponse(categoriaRepository.findById(id).orElseThrow());
    }

    public CategoriaResponse getCategoriaResponse(Categoria categoria){
        CategoriaResponse categoriaResponse = new CategoriaResponse();

        categoriaResponse.setId(categoria.getId());
        categoriaResponse.setNombre(categoria.getNombre());
        categoriaResponse.setMinimo_kg(categoria.getMinimo_kg());
        categoriaResponse.setMaximo_kg(categoriaResponse.getMaximo_kg());
        categoriaResponse.setBoxeadores(categoria.getBoxeadores());
        categoriaResponse.setEntrenador(categoria.getEntrenador().getNombre()+" "+categoria.getEntrenador().getApellido());

        return categoriaResponse;
    }

    public List<CategoriaResponse> getCategoriaResponseLst(List<Categoria> categoria){
        List<CategoriaResponse> lstToReturn = new ArrayList<>();

        categoria.forEach(cat ->{
            lstToReturn.add(getCategoriaResponse(cat));
        });

        return lstToReturn;
    }
}
