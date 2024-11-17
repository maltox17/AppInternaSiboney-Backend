DELIMITER $$

CREATE PROCEDURE calcular_horas_extras_deuda_procedure()
BEGIN
    -- Registrar horas extras para empleados que trabajaron más de su contrato
    INSERT INTO horas_extras_deuda (horas_extras, horas_deuda, empleado_id)
    SELECT 
        GREATEST(SUM(TIMESTAMPDIFF(HOUR, h.hora_entrada, h.hora_salida)) - e.horas_contrato, 0) AS horas_extras,
        0 AS horas_deuda,
        e.id AS empleado_id
    FROM 
        empleado e
    JOIN 
        horario h ON e.id = h.empleado_id
    WHERE 
        WEEK(h.fecha, 1) = WEEK(CURDATE(), 1)  -- Semana ISO actual
        AND YEAR(h.fecha) = YEAR(CURDATE())   -- Año actual
    GROUP BY 
        e.id
    HAVING 
        horas_extras > 0;

    -- Registrar horas en deuda para empleados que trabajaron menos de su contrato
    INSERT INTO horas_extras_deuda (horas_extras, horas_deuda, empleado_id)
    SELECT 
        0 AS horas_extras,
        GREATEST(e.horas_contrato - SUM(TIMESTAMPDIFF(HOUR, h.hora_entrada, h.hora_salida)), 0) AS horas_deuda,
        e.id AS empleado_id
    FROM 
        empleado e
    JOIN 
        horario h ON e.id = h.empleado_id
    WHERE 
        WEEK(h.fecha, 1) = WEEK(CURDATE(), 1)  -- Semana ISO actual
        AND YEAR(h.fecha) = YEAR(CURDATE())   -- Año actual
    GROUP BY 
        e.id
    HAVING 
        horas_deuda > 0;

    -- Registrar horas en deuda para empleados que no tienen horarios registrados
    INSERT INTO horas_extras_deuda (horas_extras, horas_deuda, empleado_id)
    SELECT 
        0 AS horas_extras,
        e.horas_contrato AS horas_deuda,
        e.id AS empleado_id
    FROM 
        empleado e
    WHERE 
        NOT EXISTS (
            SELECT 1
            FROM horario h
            WHERE e.id = h.empleado_id
            AND WEEK(h.fecha, 1) = WEEK(CURDATE(), 1)
            AND YEAR(h.fecha) = YEAR(CURDATE())
        );
END$$

DELIMITER ;