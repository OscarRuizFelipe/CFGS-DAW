--En la base de datos empresa, crea:

--El procedimiento ac07listEmpleadosConHijos que muestre los empleados que tienen hijos.
--El procedimiento ac07contarEmpleados que muestre la cantidad de empleados.
--El procedimiento ac07updSalarioEmpleados que incremente el salario de los empleados un 10%.
--Recupera los procedimientos existentes.
--Elimina el procedimiento ac07updSalarioEmpleados.


DELIMITER $$
CREATE PROCEDURE ac07listEmpleadosConHijos()
BEGIN
    SELECT * FROM empleados WHERE id_empleado IN (SELECT DISTINCT id_empleado FROM hijos);
END $$
