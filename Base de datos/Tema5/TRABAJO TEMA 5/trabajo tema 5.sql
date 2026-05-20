DELIMITER $$

CREATE TRIGGER trg_alquila_before_insert
BEFORE INSERT ON alquila
FOR EACH ROW
BEGIN
    IF NEW.fecha_entrega IS NOT NULL 
       AND NEW.fecha_entrega < NEW.fecha_recogida THEN


        SET MESSAGE_TEXT = 'La fecha de entrega no puede ser anterior a la fecha de recogida';

    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER trg_ejemplar_before_update
BEFORE UPDATE ON ejemplar
FOR EACH ROW
BEGIN
    IF NEW.stock = 0 THEN
        SET NEW.estado = 'Alquilado';
    ELSE
        SET NEW.estado = 'Disponible';
    END IF;
END$$

DELIMITER ;$

DELIMITER ;


UPDATE ejemplar
SET stock = 0   
WHERE id_ejemplar = 1;



USE videoclub;
DELIMITER $$

CREATE PROCEDURE sp_alquilar_ejemplar(
    IN p_cliente INT,
    IN p_ejemplar INT,
    IN p_fecha_recogida DATE,
    IN p_fecha_entrega DATE,
    IN p_trabajador INT
)
BEGIN 
     DECLARE error varchar(255);
    SET error = '';
    if (p_fecha_entrega < p_fecha_recogida) THEN
        SET error = 'La fecha de entrega no puede ser anterior a la fecha de recogida.';
    END IF;

    START TRANSACTION;
   

    UPDATE ejemplar
    SET stock = stock - 1
    WHERE id_ejemplar = p_ejemplar
      AND stock > 0;

    INSERT INTO alquila (id_cliente, id_ejemplar, fecha_recogida, fecha_entrega, id_trabajador)
    VALUES (p_cliente, p_ejemplar, p_fecha_recogida, p_fecha_entrega, p_trabajador);

    COMMIT;
    SELECT 'Alquiler registrado correctamente.' AS mensaje;
END$$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_devolver_ejemplar(
    IN p_cliente INT,
    IN p_ejemplar INT,
    IN p_fecha_entrega DATE
)
BEGIN
 DECLARE error varchar(255);
    DECLARE fecha_recogida DATE;
    SET error = '';
    SET fecha_recogida = (SELECT fecha_recogida FROM alquila WHERE id_cliente = p_cliente AND id_ejemplar = p_ejemplar AND fecha_entrega IS NULL);
    if (p_fecha_entrega < fecha_recogida) THEN
        SET error = 'La fecha de entrega no puede ser anterior a la fecha de recogida.';
    END IF;
    
    START TRANSACTION;
   
    UPDATE alquila
    SET fecha_entrega = p_fecha_entrega
    WHERE id_cliente = p_cliente
      AND id_ejemplar = p_ejemplar
      AND fecha_entrega IS NULL;

    UPDATE ejemplar
    SET stock = stock + 1
    WHERE id_ejemplar = p_ejemplar;

    COMMIT;

    SELECT 'Devolución registrada correctamente.' AS mensaje;
END$$

DELIMITER ;



