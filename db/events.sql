DELIMITER $$

CREATE EVENT calcular_horas_extras_deuda
ON SCHEDULE EVERY 1 WEEK
STARTS '2024-11-23 23:59:59'
DO
BEGIN
    CALL calcular_horas_extras_deuda_procedure();
END$$

DELIMITER ;