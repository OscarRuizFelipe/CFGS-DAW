
-- 1

SELECT dni,nombre from Propietarios inner join zona where nombre ='Centro';

-- 2
SELECT P.dni,Pe.nombre,Pe.apellidos,P.numero,P.calle FROM Propietarios P INNER JOIN Persona Pe ON P.dni = Pe.dni where P.numero ='20' and P.calle='Damaso';

-- 3 

SELECT metros,dni from Piso inner join Propietarios where dni='44351312';

--4 
SELECT COUNT(nombre) as personas FROM Persona where numero='14' and calle='Felipe II';

--5 

SELECT nombre,AVG(metros) as media FROM Piso inner join Zona where nombre='Latina';
    
--6   
SELECT nombre, COUNT(*) as cantidad_pisos FROM Piso inner join Zona on Piso.cp = Zona.cp GROUP BY nombre HAVING COUNT(*) > 1;

--7
SELECT Pe.nombre,Pe.apellidos FROM Persona Pe LEFT JOIN Propietarios P ON Pe.dni = P.dni WHERE Pe.calle !=P.calle;

--8                         

SELECT Pe.dni,Pe.nombre, COUNT(P.calle) as cantidad_Pisos FROM Persona Pe INNER JOIN Propietarios P ON Pe.dni = P.dni GROUP BY Pe.dni,Pe.nombre HAVING COUNT(P.calle) >= 1;


--9
SELECT Pe.nombre,Pe.apellidos,Z.nombre FROM Persona Pe INNER JOIN Propietarios P ON Pe.dni = P.dni INNER JOIN Piso Pi ON P.calle = Pi.calle AND P.numero = Pi.numero AND P.escalera = Pi.escalera AND P.planta = Pi.planta AND P.puerta = Pi.puerta INNER JOIN Zona Z ON Pi.cp = Z.cp GROUP BY Pe.dni,Pe.nombre,Pe.apellidos HAVING AVG(Pi.metros) > 70 AND COUNT(P.calle) > 1;



--10


SELECT Pe.nombre,Pe.apellidos,Z.nombre FROM Persona Pe INNER JOIN Propietarios P ON Pe.dni = P.dni INNER JOIN Piso Pi ON P.calle = Pi.calle AND P.numero = Pi.numero AND P.escalera = Pi.escalera AND P.planta = Pi.planta AND P.puerta = Pi.puerta INNER JOIN Zona Z ON Pi.cp = Z.cp GROUP BY Pe.dni,Pe.nombre,Pe.apellidos HAVING AVG(Pi.metros);