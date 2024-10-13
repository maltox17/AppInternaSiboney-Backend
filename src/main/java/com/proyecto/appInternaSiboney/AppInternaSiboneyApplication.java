package com.proyecto.appInternaSiboney;

import com.proyecto.appInternaSiboney.entity.CentroTrabajo;
import com.proyecto.appInternaSiboney.entity.Empleado;
import com.proyecto.appInternaSiboney.entity.Rol;
import com.proyecto.appInternaSiboney.repository.CentroTrabajoRepository;
import com.proyecto.appInternaSiboney.repository.EmpleadoRepository;
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
     * Bean que inserta dos registros en la tabla CentroTrabajo y cuatro empleados con diferentes roles
     * cuando la aplicación se inicia. Esto es útil para hacer pruebas rápidas desde Postman.
     *
     * @param centroTrabajoRepository Repositorio de CentroTrabajo
     * @param empleadoRepository Repositorio de Empleado
     * @return CommandLineRunner para ejecutar el código al inicio
     */
    @Bean
    CommandLineRunner initDatabase(CentroTrabajoRepository centroTrabajoRepository, EmpleadoRepository empleadoRepository) {
        return args -> {
            // Insertar dos registros en la tabla CentroTrabajo
            CentroTrabajo cafeteriaCentral = new CentroTrabajo(null, "Cafetería Central", "Calle Principal 123");
            CentroTrabajo cafeteriaNorte = new CentroTrabajo(null, "Cafetería Norte", "Avenida Norte 456");
            centroTrabajoRepository.save(cafeteriaCentral);
            centroTrabajoRepository.save(cafeteriaNorte);

            // Insertar cuatro empleados con diferentes roles
            Empleado empleado1 = new Empleado(null, "Juan Pérez", "juan@example.com", "password1", 123456789, 40, Rol.ENCARGADO, null, null, null, null, null);
            Empleado empleado2 = new Empleado(null, "María García", "maria@example.com", "password2", 987654321, 30, Rol.CAMARERO, null, null, null, null, null);
            Empleado empleado3 = new Empleado(null, "Carlos López", "carlos@example.com", "password3", 111222333, 25, Rol.JEFE, null, null, null, null, null);
            Empleado empleado4 = new Empleado(null, "Ana Martínez", "ana@example.com", "password4", 555666777, 35, Rol.ENCARGADO, null, null, null, null, null);

            // Guardar los empleados en la base de datos
            empleadoRepository.save(empleado1);
            empleadoRepository.save(empleado2);
            empleadoRepository.save(empleado3);
            empleadoRepository.save(empleado4);

            System.out.println("Datos de prueba insertados en CentroTrabajo y Empleado");
        };
    }
}
