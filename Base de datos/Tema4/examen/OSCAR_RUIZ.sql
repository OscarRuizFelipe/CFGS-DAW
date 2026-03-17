
--a)
SELECT d.PROFESOR, d.DEPARTAMENTO FROM D 
LEFT JOIN I  ON d.PROFESOR = i.PROFESOR WHERE i.PROFESOR IS NULL;

--b) 
SELECT d.PROFESOR, d.DEPARTAMENTO
FROM D  JOIN I  ON d.PROFESOR = i.PROFESOR
GROUP BY d.PROFESOR, d.DEPARTAMENTO HAVING COUNT(DISTINCT i.MODULO) = 2;

--c)
SELECT d.DEPARTAMENTO, COUNT(DISTINCT e.CICLO) as num_ciclos
FROM D  JOIN I  ON d.PROFESOR = i.PROFESOR
JOIN E  on i.MODULO = e.MODULO
GROUP BY d.DEPARTAMENTO HAVING COUNT(DISTINCT e.CICLO) > 1;

--d)

select  m.ALUMNO
FROM M 
GROUP BY m.ALUMNO HAVING MIN(m.NOTA) >= 5.00;

--e) 
 

--f)
select e.Modulo FROM e  WHERE e.CICLO='DAW';




-- Muestra los nombres de los profesores que imparten al menos un módulo.

SELECT D.PROFESOR, COUNT(I.MODULO) AS numMODULOS
FROM D
JOIN I ON D.PROFESOR = I.PROFESOR
GROUP BY D.PROFESOR
HAVING COUNT(I.MODULO) >= 1;


--  Muestra todos los módulos que aún no tienen un profesor asignado.

SELECT MODULO ,PROFESOR FROM I WHERE PROFESOR is NULL;

--  Muestra los alumnos que han aprobado todos los módulos que cursan (nota ≥ 5).

select ALUMNO FROM M GROUP by ALUMNO HAVING MIN(NOTA) >= 5;
 

 --  Muestra cada alumno con su nota media en todos los módulos que cursa.

select ALUMNO,AVG(NOTA) AS MEDIA FROM M GROUP BY ALUMNO;

-- Muestra cada profesor y cuántos módulos imparte.
select PROFESOR, COUNT(MODULO) AS TOTAL FROM I GROUP BY PROFESOR;

-- Muestra una lista de alumnos, los módulos que cursan y el profesor que lo imparte.
select m.ALUMNO,m.MODULO,i.PROFESOR FROM M INNER JOIN I ON m.MODULO = i.MODULO;

-- Muestra los alumnos que han aprobado más de la mitad de los módulos que cursan.
SELECT ALUMNO
FROM M
GROUP BY ALUMNO
HAVING SUM(NOTA >= 5) > COUNT(*) / 2; 