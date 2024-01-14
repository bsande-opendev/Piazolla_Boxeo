package com.bauti.piazolla_gestion.repositories;

import com.bauti.piazolla_gestion.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.minimo_kg <= :peso AND (c.maximo_kg >= :peso OR c.maximo_kg IS NULL)")
    Optional<Categoria> findCategoriaByPeso(@Param("peso") Double peso);

    @Query("SELECT c FROM Categoria c WHERE c.minimo_kg = (SELECT MIN(c2.minimo_kg) FROM Categoria c2)")
    Optional<Categoria> findCategoriaPesoMinimo();

    @Query("SELECT c FROM Categoria c WHERE c.maximo_kg < :peso ORDER BY c.maximo_kg DESC")
    Stream<Categoria> findCategoriasInferioresMasCercanas(Double peso);

    @Query("SELECT COUNT(b) FROM Boxeador b WHERE b.categoria = :categoria")
    Long countBoxeadoresByCategoria(@Param("categoria") Categoria categoria);

}
