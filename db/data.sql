-- Insertar centros de trabajo
INSERT INTO centro_trabajo (nombre, direccion) VALUES ('Ferrol', 'Calle Ferrol 14');
INSERT INTO centro_trabajo (nombre, direccion) VALUES ('Rua', 'Rua nueva 24');
INSERT INTO centro_trabajo (nombre, direccion) VALUES ('Corte Ingles', 'Ramon y cajal 20');

-- Insertar empleados
INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Hernan Romero', 'hernan@example.com', 'hernan@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 123456789, 40, 'ENCARGADO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Francisco Rodriguez', 'pancho@example.com', 'pancho@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 987654321, 30, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Pablo Fernandez', 'nacho@example.com', 'nacho@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 111222333, 25, 'JEFE');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Beatriz Varela', 'bea@example.com', 'bea@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'ENCARGADO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Jhon', 'jhon@example.com', 'jhon@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Anxo', 'anxo@example.com', 'anxo@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'ENCARGADO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Valentin', 'valen@example.com', 'valen@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Samy', 'samy@example.com', 'samy@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Carmen', 'carmen@example.com', 'carmen@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('annabel', 'annabel@example.com', 'annabel@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('belen', 'belen@example.com', 'belen@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Ana Isa', 'ana@example.com', 'ana@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Leydis', 'leydis@example.com', 'leydis@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Alba', 'alba@example.com', 'alba@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Hugo Romero', 'hugo@example.com', 'hugo@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'ENCARGADO');

INSERT INTO empleado (nombre, email, username, clave, telefono, horas_contrato, rol)
VALUES ('Leslie Romero', 'leslie@example.com', 'leslie@example.com', '$2a$10$sFjUblsZ4N85KTXPrLfdFOmA5x2E/9GbNSatSnbLRrnb2CbDHgdzC', 555666777, 35, 'CAMARERO');


-- Insertar vacaciones
INSERT INTO vacaciones (fecha_inicio, fecha_fin, estado, empleado_id) VALUES ('2024-07-01', '2024-07-15', 'PENDIENTE', 1);
INSERT INTO vacaciones (fecha_inicio, fecha_fin, estado, empleado_id) VALUES ('2024-08-01', '2024-08-10', 'APROBADA', 2);
INSERT INTO vacaciones (fecha_inicio, fecha_fin, estado, empleado_id) VALUES ('2024-09-01', '2024-09-20', 'RECHAZADA', 3);
INSERT INTO vacaciones (fecha_inicio, fecha_fin, estado, empleado_id) VALUES ('2023-12-01', '2024-12-15', 'PENDIENTE', 4);

-- Insertar horarios
INSERT INTO horario (fecha, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES ('2024-07-05', '09:00:00', '17:00:00', 'MAÑANA', 1, 1);

INSERT INTO horario (fecha, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES ('2024-07-06', '09:00:00', '17:00:00', 'MAÑANA', 2, 2);

INSERT INTO horario (fecha, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES ('2024-07-07', '14:00:00', '22:00:00', 'TARDE', 3, 1);

INSERT INTO horario (fecha, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES ('2024-07-08', '14:00:00', '22:00:00', 'TARDE', 4, 2);



-- Insertar horarios establecidos
INSERT INTO horarios_establecidos (dia_semana, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES (1, '08:00:00', '16:00:00', 'MAÑANA', 1, 1);

INSERT INTO horarios_establecidos (dia_semana, hora_entrada, hora_salida, turno, empleado_id, centro_id)
VALUES (2, '08:00:00', '16:00:00', 'TARDE', 2, 2);

-- Insertar asignación de empleados a centros
INSERT INTO empleados_centro (empleado_id, centro_id, es_encargado) VALUES (1, 1, TRUE);
INSERT INTO empleados_centro (empleado_id, centro_id, es_encargado) VALUES (2, 2, FALSE);
INSERT INTO empleados_centro (empleado_id, centro_id, es_encargado) VALUES (3, 1, TRUE);
INSERT INTO empleados_centro (empleado_id, centro_id, es_encargado) VALUES (4, 2, FALSE);
