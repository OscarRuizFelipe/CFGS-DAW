
-- 01. Nombre y genero de todos los videojuegos "Multiplataforma"
SELECT nombre, genero
FROM Videojuegos
WHERE plataforma = 'Multiplataforma';

-- 02. Cantidad de videojuegos de "Acción"
SELECT COUNT(*) AS cantidad_accion
FROM Videojuegos
WHERE genero = 'Acción';

-- 03. Nombre de las distintas plataformas que existen para los videojuegos (sin repetir)
SELECT DISTINCT plataforma
FROM Videojuegos
ORDER BY plataforma;

-- 04. Nombre y fecha_lanzamiento de los videojuegos entre 2017-05-05 y 2019-05-05
SELECT nombre, fecha_lanzamiento
FROM Videojuegos
WHERE fecha_lanzamiento BETWEEN '2017-05-05' AND '2019-05-05';

-- 05. Nombre y email de los jugadores cuyo apellido acaba con la letra "a"
SELECT nombre, email
FROM Jugadores
WHERE RIGHT(SUBSTRING_INDEX(nombre, ' ', -1), 1) = 'a';

-- 06. Duración (minutos) de la partida más larga y nombre del videojuego en el que se jugó
SELECT p.duracion_minutos AS duracion_min, v.nombre AS videojuego
FROM Partidas p
JOIN Videojuegos v ON p.videojuego_id = v.id
WHERE p.duracion_minutos = (SELECT MAX(duracion_minutos) FROM Partidas);

-- 07. Duración promedio de las partidas jugadas (en minutos)
SELECT AVG(duracion_minutos) AS duracion_promedio_minutos
FROM Partidas;

-- 08. Nombre del jugador, videojuego jugado y puntuación del jugador con menor puntuación registrado
SELECT j.nombre AS jugador, v.nombre AS videojuego, jp.puntuacion
FROM Jugadores_Partidas jp
JOIN Jugadores j ON jp.jugador_id = j.id
JOIN Partidas p ON jp.partida_id = p.id
JOIN Videojuegos v ON p.videojuego_id = v.id
ORDER BY jp.puntuacion ASC
LIMIT 1;

-- 09. Puntuación total obtenida en todos los videojuegos de "PlayStation 4"
SELECT COALESCE(SUM(jp.puntuacion), 0) AS puntuacion_total
FROM Jugadores_Partidas jp
JOIN Partidas p ON jp.partida_id = p.id
JOIN Videojuegos v ON p.videojuego_id = v.id
WHERE v.plataforma = 'PlayStation 4';

-- 10. Nombre de plataforma y número de partidas totales jugadas en cada una de ellas
SELECT v.plataforma AS plataforma, COUNT(*) AS partidas_totales
FROM Partidas p
JOIN Videojuegos v ON p.videojuego_id = v.id
GROUP BY v.plataforma
ORDER BY partidas_totales DESC;