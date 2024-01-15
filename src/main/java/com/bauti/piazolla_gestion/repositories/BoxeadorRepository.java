package com.bauti.piazolla_gestion.repositories;

import com.bauti.piazolla_gestion.entities.Boxeador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BoxeadorRepository extends JpaRepository<Boxeador, Long> {

    List<Boxeador> findByFechaInscripcionBetween(Timestamp startOfDay, Timestamp endOfDay);
}
