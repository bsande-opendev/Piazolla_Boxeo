package com.bauti.piazolla_gestion.tasks;

import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.entities.Boxeador;
import com.bauti.piazolla_gestion.services.BoxeadorService;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
@CommonsLog
public class ReporteAltasTask {

    @Autowired
    BoxeadorService boxeadorService;

    @Scheduled(cron = "59 23 * * * *")
    public void imprimirReporteAltasDiario(){

        List<BoxeadorResponse> altasDiarias = boxeadorService.obtenerAltasDiarias();

        Timestamp fechaActual = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        String fileName = "informe_altas_diarias_" + fechaActual.toLocalDateTime().toLocalDate() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Informe de Altas Diarias - " + fechaActual.toLocalDateTime().toLocalDate());
            writer.newLine();
            writer.write("-----------------------------------------");
            writer.newLine();

            for (BoxeadorResponse dto : altasDiarias) {
                writer.write("Nombre: " + dto.getNombre());
                writer.newLine();
                writer.write("Apellido: " + dto.getApellido());
                writer.newLine();
                writer.write("Peso: " + dto.getPeso_kg());
                writer.newLine();
                writer.write("Categoría: " + dto.getCategoria());
                writer.newLine();
                writer.write("Fecha de Inscripción: " + dto.getFecha_inscripcion());
                writer.newLine();
                writer.write("-----------------------------------------");
                writer.newLine();
            }
            log.info("Informe guardado en el archivo: " + fileName);
        } catch (IOException e) {
            log.error("Error en CategoriaController: " + e.getMessage(), e);
        }
    }
}


