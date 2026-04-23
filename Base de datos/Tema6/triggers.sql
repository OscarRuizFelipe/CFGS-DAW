DELIMITER $$

CREATE TRIGGER triggerCrearEmailBeforeInsert
BEFORE INSERT ON estudiantes
FOR EACH ROW
BEGIN
    IF NEW.email IS NULL OR NEW.email = '' THEN
        SET NEW.email = crearEmail(NEW.nombre, NEW.apellidos);
    END IF;
END$$

DELIMITER ;



DELIMITER $$

CREATE TRIGGER triggerGuardarEmailAfterUpdate
AFTER UPDATE ON alumnado
FOR EACH ROW
BEGIN
    IF OLD.email != NEW.email THEN
        INSERT INTO logCambiosEmail (idAlumno, fechaHora, oldEmail, newEmail)
        VALUES (OLD.id, NOW(), OLD.email, NEW.email);
    END IF;
END$$

DELIMITER ;


CREATE TABLE logCambiosEmail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT NOT NULL,
    fechaHora DATETIME NOT NULL,
    oldEmail VARCHAR(255),
    newEmail VARCHAR(255)
);



DELIMITER $$

CREATE TRIGGER ac1104triggerGuardarAlumnosAfterDelete
AFTER DELETE ON estudiantes
FOR EACH ROW
BEGIN
    INSERT INTO logAlumnosEliminados (
        idAlumno,
        fechaHora,
        nombre,
        apellido,
        email
    ) VALUES (
        OLD.id,
        NOW(),
        OLD.nombre,
        OLD.apellidos,
        OLD.email
    );
END$$

DELIMITER ;



CREATE TABLE logAlumnosEliminados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idAlumno INT NOT NULL,
    fechaHora DATETIME NOT NULL,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    email VARCHAR(255)
);
