package com.bauti.piazolla_gestion.repositories;

import com.bauti.piazolla_gestion.entities.Boxeador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxeadorRepository extends JpaRepository<Boxeador, Long> {
}
