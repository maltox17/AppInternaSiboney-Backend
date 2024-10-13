package com.proyecto.appInternaSiboney;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppInternaSiboneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppInternaSiboneyApplication.class, args);
    }

    /**
     * Bean que inserta dos registros en la tabla CentroTrabajo cuando la aplicación se inicia.
     * Esto es útil para hacer pruebas rápidas desde Postman.
     *
     * @param centroTrabajoRepository Repositorio de CentroTrabajo
     * @return CommandLineRunner para ejecutar el código al inicio
     */
    @Bean
    CommandLineRunner initDatabase(CentroTrabajoRepository centroTrabajoRepository) {
        return args -> {
            // Insertar dos registros en la tabla CentroTrabajo
            centroTrabajoRepository.save(new CentroTrabajo(null, "Cafetería Central", "Calle Principal 123"));
            centroTrabajoRepository.save(new CentroTrabajo(null, "Cafetería Norte", "Avenida Norte 456"));

            System.out.println("Datos de prueba insertados en CentroTrabajo");
        };
    }
}