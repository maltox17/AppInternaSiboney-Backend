CREATE DATABASE IF NOT EXISTS siboney;

USE siboney;

-- Tabla CentroTrabajo
CREATE TABLE IF NOT EXISTS centro_trabajo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL
);

-- Tabla Empleado
CREATE TABLE IF NOT EXISTS empleado (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    username VARCHAR(150) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL,
    telefono INT NOT NULL,
    horas_contrato INT NOT NULL,
    rol ENUM('CAMARERO', 'JEFE', 'ENCARGADO', 'COCINERO') NOT NULL,
    encargado_id BIGINT,
    FOREIGN KEY (encargado_id) REFERENCES empleado(id)
);

-- Tabla Vacaciones
CREATE TABLE IF NOT EXISTS vacaciones (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    estado ENUM('APROBADA', 'RECHAZADA', 'PENDIENTE') NOT NULL,
    empleado_id BIGINT,
    FOREIGN KEY (empleado_id) REFERENCES empleado(id)
);

-- Tabla Horario
CREATE TABLE IF NOT EXISTS horario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME NOT NULL,
    turno ENUM('MAÑANA', 'TARDE') NOT NULL,
    empleado_id BIGINT,
    centro_id BIGINT,
    FOREIGN KEY (empleado_id) REFERENCES empleado(id),
    FOREIGN KEY (centro_id) REFERENCES centro_trabajo(id)
);

-- Tabla HorariosEstablecidos
CREATE TABLE IF NOT EXISTS horarios_establecidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    dia_semana TINYINT,
    hora_entrada TIME NOT NULL,
    hora_salida TIME NOT NULL,
    turno ENUM('MAÑANA', 'TARDE') NOT NULL,
    empleado_id BIGINT,
    centro_id BIGINT,
    FOREIGN KEY (empleado_id) REFERENCES empleado(id),
    FOREIGN KEY (centro_id) REFERENCES centro_trabajo(id)
);

-- Tabla EmpleadoCentro
CREATE TABLE IF NOT EXISTS empleados_centro (
    empleado_id BIGINT NOT NULL,
    centro_id BIGINT NOT NULL,
    es_encargado BOOLEAN NOT NULL,
    PRIMARY KEY (empleado_id, centro_id),
    FOREIGN KEY (empleado_id) REFERENCES empleado(id),
    FOREIGN KEY (centro_id) REFERENCES centro_trabajo(id)
);

-- Creación de la tabla 'horas_extras_deuda'
CREATE TABLE IF NOT EXISTS horas_extras_deuda(
    id BIGINT AUTO_INCREMENT PRIMARY KEY, 
    horas_extras INT NOT NULL,             
    horas_deuda INT NOT NULL,              
    empleado_id BIGINT,                  
    CONSTRAINT FK_Empleado_HorasExtrasDeuda FOREIGN KEY (empleado_id) 
    REFERENCES empleado(id)
    ON DELETE SET NULL  
);
