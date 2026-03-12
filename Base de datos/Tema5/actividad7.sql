--En la base de datos empresa, crea:

--El procedimiento ac07listEmpleadosConHijos que muestre los empleados que tienen hijos.
--El procedimiento ac07contarEmpleados que muestre la cantidad de empleados.
--El procedimiento ac07updSalarioEmpleados que incremente el salario de los empleados un 10%.
--Recupera los procedimientos existentes.
--Elimina el procedimiento ac07updSalarioEmpleados.


DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07listEmpleadosConHijos()
BEGIN
    SELECT * FROM empleado WHERE numHi > 0;
END $$
DELIMITER ;

CALL ac07listEmpleadosConHijos();

DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07contarEmpleados()
BEGIN
    SELECT COUNT(*) FROM empleado;
END $$
DELIMITER ;

CALL ac07contarEmpleados();



CALL ac07updSalarioEmpleados

DELIMITER $$
CREATE OR REPLACE PROCEDURE ac07updSalarioEmpleados()
BEGIN
    UPDATE empleado SET salEMP = salEMP * 1.1;
END $$
DELIMITER ;