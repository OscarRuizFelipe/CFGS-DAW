/*En la base de datos empresa, crea:

El procedimiento ac08listDepartamentos que liste los departamentos de un determinado centro.
El procedimiento ac08listDepartamentosPlus que liste los departamentos de un centro, y en el caso de recibir como argumento un valor nulo, devuelva todos los departamentos.
El procedimiento ac08updSalarioEmpleadosParam que incremente el salario de los empleados una determinada cantidad a partir de un parámetro de entrada.
El procedimiento ac08contarEmpleados que devuelva la cantidad de empleados en un parámetro de salida.

El procedimiento ac08contarEmpleadosDpto que devuelva la cantidad de empleados de un determinado departamento (introducido vía un parámetro de entrada) en un parámetro de salida.

El procedimiento ac08sueldosSet que devuelva el sueldo menor, el mayor y el promedio de todos los empleados (usando SET).
El procedimiento ac08sueldosSelectInto que devuelva el sueldo menor, el mayor y el promedio de todos los empleados (usando SELECT INTO).*/




DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08listDepartamentos(IN CodCen1 VARCHAR(10))
BEGIN
    SELECT * FROM departamento WHERE codcen = CodCen1;
END $$
DELIMITER ;
 
 CALL ac08listDepartamentos('DIGE');



DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08listDepartamentosPlus(IN CodCen2 VARCHAR(10))
BEGIN
  
    IF CodCen2 IS NULL THEN
        SELECT * FROM departamento;
    ELSE 
        SELECT * FROM departamento WHERE codcen = CodCen2;
    END IF;
END $$
DELIMITER ;

CALL ac08listDepartamentosPlus(null);



DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08updSalarioEmpleadosParam(IN incremento DECIMAL(10,2))
BEGIN
    UPDATE empleado SET salEMP = salEMP + incremento;
END $$
DELIMITER ;     

CALL ac08updSalarioEmpleadosParam(500.00);


DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08contarEmpleados(OUT cantidadEmpleados int)
BEGIN
     SET cantidadEmpleados = (SELECT COUNT(*) FROM empleado);       
END $$
DELIMITER ;

CALL ac08contarEmpleados(@cantidadEmpleados);
SELECT @cantidadEmpleados;




DELIMITER $$
CREATE OR REPLACE PROCEDURE  ac08contarEmpleadosDpto (iN CodDepar VARCHAR(10), OUT cantidadEmpleados int)
BEGIN
     SET cantidadEmpleados = (SELECT COUNT(*) FROM empleado WHERE codDep = CodDepar);       
END $$
DELIMITER ;

CALL ac08contarEmpleadosDpto('PROZS', @cantidadEmpleados);
SELECT @cantidadEmpleados;





DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08sueldosSet(OUT sueldoMenor DECIMAL(10,2), OUT sueldoMayor DECIMAL(10,2), OUT sueldoPromedio DECIMAL(10,2))
BEGIN
     SET sueldoMenor =(SELECT MIN(salEMP) FROM empleado);
     SET sueldoMayor =(SELECT MAX(salEMP) FROM empleado);
     SET sueldoPromedio =(SELECT AVG(salEMP) FROM empleado);
     
END $$
DELIMITER ;

CALL ac08sueldosSet(@sueldoMenor, @sueldoMayor, @sueldoPromedio);
SELECT @sueldoMenor, @sueldoMayor, @sueldoPromedio;



DELIMITER $$
CREATE OR REPLACE PROCEDURE ac08sueldosSet(OUT sueldoMenor DECIMAL(10,2), OUT sueldoMayor DECIMAL(10,2), OUT sueldoPromedio DECIMAL(10,2))
BEGIN
     SELECT MIN(salEMP) INTO sueldoMenor FROM empleado;
     SELECT MAX(salEMP) INTO sueldoMayor FROM empleado;
     SELECT AVG(salEMP) INTO sueldoPromedio FROM empleado;
     
END $$
DELIMITER ;


CALL ac08sueldosSet(@sueldoMenor, @sueldoMayor, @sueldoPromedio);
SELECT @sueldoMenor, @sueldoMayor, @sueldoPromedio;
