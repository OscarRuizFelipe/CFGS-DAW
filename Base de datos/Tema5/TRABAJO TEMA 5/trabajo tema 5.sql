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

DELIMITER $$

CREATE PROCEDURE sp_actualizar_estado_ejemplares()
BEGIN
    DECLARE v_id INT;
    DECLARE v_stock INT;
    DECLARE v_done BOOL DEFAULT FALSE;
    DECLARE v_contador INT DEFAULT 0;
    DECLARE MESSAGE_TEXT VARCHAR(300);
    DECLARE cur_ejemplares CURSOR FOR
        SELECT id_ejemplar, stock FROM ejemplar;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;

    OPEN cur_ejemplares;

    read_loop: LOOP
        FETCH cur_ejemplares INTO v_id, v_stock;
        IF v_done THEN
            LEAVE read_loop;
        END IF;

        IF v_stock = 0 THEN
            UPDATE ejemplar SET estado = 'Alquilado' WHERE id_ejemplar = v_id;
        ELSE
            UPDATE ejemplar SET estado = 'Disponible' WHERE id_ejemplar = v_id;
        END IF;

        SET v_contador = v_contador + 1;
    END LOOP;

    CLOSE cur_ejemplares;

    IF v_contador = 0 THEN
      
            SET MESSAGE_TEXT = 'No hay ejemplares para procesar.';
    END IF;

    SELECT 'Estado de ejemplares actualizado.' AS mensaje;
END$$

CREATE PROCEDURE sp_lista_clientes_alquileres()
BEGIN
    DECLARE v_id INT;
    DECLARE v_nombre VARCHAR(100);
    DECLARE v_cantidad INT;
    DECLARE v_done BOOL DEFAULT FALSE;
    DECLARE v_contador INT DEFAULT 0;
    DECLARE MESSAGE_TEXT VARCHAR(400);
    DECLARE cur_clientes CURSOR FOR
        SELECT id_cliente, nombre FROM cliente;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_done = TRUE;

    OPEN cur_clientes;

    read_clientes: LOOP
        FETCH cur_clientes INTO v_id, v_nombre;
        IF v_done THEN
            LEAVE read_clientes;
        END IF;

        SELECT COUNT(*) INTO v_cantidad
        FROM alquila
        WHERE id_cliente = v_id
          AND fecha_entrega IS NULL;

        SELECT v_id AS id_cliente, v_nombre AS cliente, v_cantidad AS alquileres_activos;
        SET v_contador = v_contador + 1;
    END LOOP;

    CLOSE cur_clientes;

    IF v_contador = 0 THEN
            SET MESSAGE_TEXT = 'No hay clientes para procesar.';
    END IF;
END$$

DELIMITER $$

CREATE OR REPLACE FUNCTION fn_alquileres_activos_por_categoria(
    p_id_categoria INT
)
RETURNS INT

BEGIN
    DECLARE v_total INT DEFAULT 0;

    SELECT COUNT(*) INTO v_total
    FROM alquila a
    JOIN ejemplar e ON a.id_ejemplar = e.id_ejemplar
    JOIN pelicula p ON e.id_pelicula = p.id_pelicula
    JOIN pertenece pe ON p.id_pelicula = pe.id_pelicula
    WHERE pe.id_categoria = p_id_categoria
      AND a.fecha_entrega IS NULL;

    RETURN v_total;
END $$

CREATE OR REPLACE FUNCTION fn_titulo_ultima_pelicula_alquilada(
    p_id_cliente INT
)
RETURNS VARCHAR(200)

BEGIN
    DECLARE v_titulo VARCHAR(200);

    SELECT p.titulo INTO v_titulo
    FROM alquila a
    JOIN ejemplar e ON a.id_ejemplar = e.id_ejemplar
    JOIN pelicula p ON e.id_pelicula = p.id_pelicula
    WHERE a.id_cliente = p_id_cliente
    ORDER BY a.fecha_recogida DESC
    LIMIT 1;

    IF v_titulo IS NULL THEN
        SET v_titulo = 'Sin alquileres';
    END IF;

    RETURN v_titulo;
END $$

DELIMITER ;

CREATE OR REPLACE TABLE dashboard_simple (
    id_dashboard INT AUTO_INCREMENT PRIMARY KEY,
    fecha_generacion DATETIME NOT NULL,
    total_clientes INT NOT NULL,
    ejemplares_disponibles INT NOT NULL
);

DELIMITER $$


CREATE OR REPLACE FUNCTION fn_total_clientes()
RETURNS INT

BEGIN
    DECLARE v_total INT;
    SELECT COUNT(*) INTO v_total FROM cliente;
    RETURN v_total;
  
END  $$


CREATE OR REPLACE PROCEDURE sp_generar_dashboard_simple()
BEGIN
    INSERT INTO dashboard_simple (fecha_generacion, total_clientes, ejemplares_disponibles)
    VALUES (
        NOW(),
        fn_total_clientes(),
        (SELECT COUNT(*) FROM ejemplar WHERE stock > 0)
    );
    SELECT 'Dashboard simple generado.' AS mensaje;
END $$

CREATE OR REPLACE PROCEDURE sp_ver_dashboard_simple()
BEGIN
    SELECT * FROM dashboard_simple ORDER BY fecha_generacion DESC;
END $$

DELIMITER ;



                        