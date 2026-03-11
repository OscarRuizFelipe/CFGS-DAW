--Utilizando la base de datos empresa y en un archivo nombrado como ac06empleados.sql, se pide:

--Crea una tabla dashboard_dpto que muestre para cada departamento, además de su código y nombre y presupuesto anual, cuantos empleados y su gasto en salarios.
--Crea una tabla dashboard_centro que muestre para cada centro, además de su código y nombre, cuantos departamentos contiene y el presupuesto anual (entendido como la suma de los presupuestos de sus departamentos).
--Lanza el script ac06empleados.sql para que ejecute ambas operaciones (cada vez que lo hagas, debe borrar las tablas y volver a crearlas).

drop table if exists dashboard_dpto;
create table dashboard_dpto as select d.CodDep, d.NomDep, d.PreAnu, count(e.CodEmp) as NumEmpleados, sum(e.SalEmp) as GastoSalarios
from departamento d left join empleado e on d.CodDep = e.CodDep
group by d.CodDep, d.NomDep, d.PreAnu;

drop table if exists dashboard_centro;
create table dashboard_centro as select c.CodCen, c.NomCen, count(d.CodDep) as NumDepartamentos, sum(d.PreAnu) as PresupuestoAnual
from centro c left join departamento d on c.CodCen = d.CodCen
group by c.CodCen, c.NomCen; 

