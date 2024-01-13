package com.bauti.piazolla_gestion.repositories;

import com.bauti.piazolla_gestion.entities.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}
