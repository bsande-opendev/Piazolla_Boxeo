# Piazolla_Boxeo

Backend en Java 11 con SpringBoot para gestionar Boxeadores, Entrenadores y Categorias del gimnasio. 
Ademas, imprime un reporte diario sobre las nuevas altas de boxeadores realizadas a las 23:59hs.

Esta aplicación utiliza una base de datos en memoria (H2)

## Requisitos

- Java 11
- Maven

## Configuración del Proyecto
Este proyecto no requiere ninguna configuración adicional.

## Ejecución
```bash
mvn spring-boot:run
```

## Uso
El contexto base de la api es **/piazolla/api** y corre en el puerto 8080

La api se encuentra documentada en http://localhost:8080/piazolla/api/swagger-ui/index.html


El script de carga de datos se encuentra en el directorio de recursos del proyecto **/resources**
Por defecto, se cargan 4 instructores cada uno asignado a 2 de las 8 categorias tradicionales del boxeo.
No se cargan boxeadores por defecto.


Si bien el reporte está seteado para imprimirse todos los días a las 23:59hs, dejé expuesto un endpoint para forzar
su impresión en **/piazolla/api/boxeadores/reporte**