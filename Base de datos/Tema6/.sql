create database prueba;
/*crea la tabla alumnado con las siguientes columnas:

id: entero sin signo (clave primaria).
nombre: cadena de 50 caracteres.
apellidos: cadena de 50 caracteres.
curso: cadena de 50 caracteres;*/

USE prueba; 
DROP TABLE IF EXISTS alumnado;
CREATE TABLE alumnado (
   id INT PRIMARY KEY,
   nombre VARCHAR(50),
   apellidos VARCHAR(50),
   curso VARCHAR(50)
);
--E inserta 5 registros con datos inventados.
INSERT INTO alumnado (id, nombre, apellidos, curso) VALUES
(1, 'Juan', 'Pérez', 'Matemáticas'),
(2, 'María', 'Gómez', 'Física'),
(3, 'Carlos', 'López', 'Química'),
(4, 'Ana', 'Martínez', 'Biología'),
(5, 'Luis', 'Sánchez', 'Historia');

/*Crea una función (crearEmail) que a partir de un nombre, apellidos y curso, genere una dirección de email y la devuelva como salida. El formato del email de salida es el siguiente:

El primer carácter del parámetro nombre (en minúsculas). Usa la función LOWER y LEFT
Los cinco primeros caracteres del parámetro apellidos (en minúsculas). Usa la función LOWER y LEFT
Un número con la longitud de los apellidos.
El carácter @.
El curso pasado como parámetro (en minúsculas).
y finalizar con .kursal.es.
Por ejemplo, si invocamos a la función con crearEmail('Alberto', 'Morales', 'BD') devolvería amoral7@bd.kursal.es.*/
DELIMITER //
CREATE FUNCTION crearEmail(nombre VARCHAR(50), apellidos VARCHAR(50), curso VARCHAR(50))
RETURNS VARCHAR(100)

BEGIN
   DECLARE email VARCHAR(100);
   SET email = CONCAT(LEFT(LOWER(nombre), 1), LEFT(LOWER(apellidos), 5), LENGTH(apellidos), '@', LOWER(curso), '.kursal.es');
   RETURN email;
END //
DELIMITER ;

--dame un select para usa la funcion
select crearEmail('Juan', 'Pérez', 'Matemáticas')


/*Añade una columna email a la tabla alumnado. */
ALTER TABLE alumnado ADD COLUMN email VARCHAR(100);

--A continuación, crea un procedimiento (ac11actualizarColumnaEmail) que permita crear un email para todo el alumnado que ya existe en la tabla alumnado, utilizando la función crearEmail.
DELIMITER //
CREATE PROCEDURE ac11actualizarColumnaEmail()
BEGIN
   UPDATE alumnado
   SET email = crearEmail(nombre, apellidos, curso);
END //
DELIMITER ;
--usa el prodedimiento para actualizar la columna email
CALL ac11actualizarColumnaEmail();