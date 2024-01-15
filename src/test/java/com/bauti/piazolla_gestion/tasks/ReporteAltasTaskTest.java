package com.bauti.piazolla_gestion.tasks;

import com.bauti.piazolla_gestion.dto.BoxeadorResponse;
import com.bauti.piazolla_gestion.services.BoxeadorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReporteAltasTaskTest {

    @Mock
    private BoxeadorService boxeadorService;
    @InjectMocks
    private ReporteAltasTask reporteAltasTask;

    @Test
    void testImprimirReporteAltasDiario() throws IOException {

        Timestamp ahora = Timestamp.valueOf(LocalDateTime.now());


        BoxeadorResponse response1 = new BoxeadorResponse(1L, "Juan", "Cito", 80.0, "Welter", ahora);
        BoxeadorResponse response2 = new BoxeadorResponse(2L, "Juana", "Luisa", 65.5, "Mosca", ahora);
        List<BoxeadorResponse> mockAltasDiarias = Arrays.asList(response1, response2);

        when(boxeadorService.obtenerAltasDiarias()).thenReturn(mockAltasDiarias);
        reporteAltasTask.imprimirReporteAltasDiario();

        Timestamp fechaActual = Timestamp.valueOf(LocalDate.now().atStartOfDay());
        String expectedFileName = "informe_altas_diarias_" + fechaActual.toLocalDateTime().toLocalDate() + ".txt";
        Path filePath = Path.of(expectedFileName);

        assertTrue(Files.exists(filePath));

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            assertEquals("Informe de Altas Diarias - " + fechaActual.toLocalDateTime().toLocalDate(), reader.readLine());
            assertEquals("-----------------------------------------", reader.readLine());

            for (BoxeadorResponse mockResponse : mockAltasDiarias) {
                assertEquals("Nombre: " + mockResponse.getNombre(), reader.readLine());
                assertEquals("Apellido: " + mockResponse.getApellido(), reader.readLine());
                assertEquals("Peso: " + mockResponse.getPeso_kg(), reader.readLine());
                assertEquals("Categoría: " + mockResponse.getCategoria(), reader.readLine());
                assertEquals("Fecha de Inscripción: " + mockResponse.getFecha_inscripcion(), reader.readLine());
                assertEquals("-----------------------------------------", reader.readLine());
            }
        }

        Files.delete(filePath);
    }

}
