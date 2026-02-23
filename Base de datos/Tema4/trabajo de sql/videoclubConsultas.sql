--Lista el titulo de las películas cuyo stock  es 0.

SELECT p.titulo FROM pelicula p JOIN ejemplar e ON p.id_pelicula = e.id_pelicula WHERE e.stock = 0




--Muestra el nombre del cliente y su email de los clientes que actualmente tienen un alquiler activo.

SELECT c.nombre, c.email FROM cliente c JOIN alquila a ON c.id_cliente = a.id_cliente WHERE a.fecha_entrega IS NULL


-- Muestra pelicula, stock y director solo para las películas cuyo titulo contiene la palabra 'El'

SELECT p.titulo,e.stock,d.nombre
FROM pelicula p JOIN director d ON p.director = d.id_director
JOIN ejemplar e ON p.id_pelicula = e.id_pelicula
WHERE p.titulo LIKE 'El%';



--media del salario de los trabajadores
SELECT AVG(salario) AS salario_medio FROM trabajador;  



--mostrar titulo de la peli, nombre directo y ejempleares dispobibles ordenado de mayor a menor

SELECT p.titulo AS pelicula,d.nombre AS director,SUM(e.stock) AS ejemplares_disponibles FROM pelicula p
INNER JOIN director d ON p.director = d.id_director INNER JOIN ejemplar e ON p.id_pelicula = e.id_pelicula
GROUP BY p.id_pelicula, p.titulo, d.nombre ORDER BY ejemplares_disponibles DESC;

--la media de stock de los ejemplares que hay por cada peli que contaga con la palabra 'El' en el titulo

SELECT AVG(e.stock) AS media_stock FROM pelicula p LEFT JOIN ejemplar e   ON p.id_pelicula = e.id_pelicula WHERE p.titulo LIKE '%El%';

--Mostrar todos los ejemplares y el título de la película a la que pertenecen, incluyendo aquellas películas que no tengan ejemplares registrados.

SELECT e.id_ejemplar, e.estado, e.stock, p.titulo FROM ejemplar e RIGHT JOIN pelicula p ON e.id_pelicula = p.id_pelicula;


--Mostrar el título de las películas cuyo stock total de ejemplares sea mayor que la media de stock de todos los ejemplares.

SELECT p.titulo FROM pelicula p
WHERE (SELECT SUM(e.stock) FROM ejemplar e WHERE e.id_pelicula = p.id_pelicula) > (  SELECT AVG(stock) FROM ejemplar);

--nombres de los clientes que han alquilado alguna película.

SELECT DISTINCT c.nombre FROM cliente c JOIN alquila a ON c.id_cliente = a.id_cliente;

--Mostrar los nombres de los trabajadores cuyo salario sea mayor que el salario promedio de todos los trabajadores.

SELECT nombre FROM trabajador WHERE salario > (SELECT AVG(salario)FROM trabajador);



--insertar datos peliculas 

INSERT INTO pelicula (titulo, año_lanzamiento, genero, clasificacion, id_proveedor, director)
SELECT 'Interstellar', 2014, 'Ciencia Ficción', 'PG-13', id_proveedor, id_director FROM proveedor 
JOIN director ON director.id_director = 1 WHERE proveedor.id_proveedor = 1;

--Subir el salario de todos los trabajadores que ganan menos que el promedio


UPDATE trabajador SET salario = 2600 WHERE salario < (SELECT AVG(salario) FROM trabajador);

-- Borrar clientes que nunca han alquilado
DELETE FROM cliente WHERE id_cliente NOT IN (SELECT DISTINCT id_cliente FROM alquila);


-- Vista que muestra el nombre del cliente y cuántos alquileres ha hecho
CREATE VIEW vista_alquileres_clientes AS SELECT c.nombre AS cliente, COUNT(a.id_ejemplar) AS total_alquileres 
FROM cliente c LEFT JOIN alquila a ON c.id_cliente = a.id_cliente GROUP BY c.id_cliente;

-- Mostrar todos los clientes y su total de alquileres
SELECT * FROM vista_alquileres_clientes;