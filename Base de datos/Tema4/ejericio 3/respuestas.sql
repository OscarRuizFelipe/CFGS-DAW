-- ============================================================
-- BASE DE DATOS: ayuntamiento
-- EJERCICIOS DE CONSULTAS SQL
-- ============================================================

-- 01. Nombre y email de todos los usuarios del departamento "Recursos Humanos"
SELECT nombre, email 
FROM usuario 
WHERE departamento = 'Recursos Humanos';

-- 02. Lista de tickets que no tienen técnico asignado y prioridad es "Alta"
SELECT idTicket, descripcion, prioridad, categoria, estado 
FROM ticket 
WHERE email_tecnico IS NULL AND prioridad = 'Alta';

-- 03. Lista de tickets que incluyen en su descripción la palabra "servi"
SELECT idTicket, descripcion, categoria, prioridad 
FROM ticket 
WHERE LOWER(descripcion) LIKE '%servi%';

-- 04. Número total de tickets resueltos
SELECT COUNT(*) AS total_tickets_resueltos 
FROM ticket 
WHERE estado = 'Resuelto';

-- 05. Número de usuarios que tiene cada departamento
SELECT departamento, COUNT(*) AS numero_usuarios 
FROM usuario 
GROUP BY departamento 
ORDER BY departamento;

-- 06. Nombre y email de técnicos y administradores cuyo apellido acabe en "ez", ordenados ascendente
SELECT email, nombre, 'Técnico' AS tipo FROM tecnico WHERE nombre LIKE '%ez'
UNION
SELECT email, nombre, 'Administrador' AS tipo FROM administrador WHERE nombre LIKE '%ez'
ORDER BY nombre ASC;

-- 07. Lista de tickets "Pendiente" ordenados por fecha de creación descendente
SELECT idTicket, descripcion, categoria, prioridad, fecha_creacion 
FROM ticket 
WHERE estado = 'Pendiente' 
ORDER BY fecha_creacion DESC;

-- 08. Número de tickets registrados por cada departamento
SELECT u.departamento, COUNT(t.idTicket) AS numero_tickets 
FROM usuario u 
LEFT JOIN ticket t ON u.email = t.email_usuario 
GROUP BY u.departamento 
ORDER BY numero_tickets DESC;

-- 09. Lista de tickets resueltos en los últimos 5 días
SELECT DISTINCT t.idTicket, t.descripcion, t.categoria, h.fecha AS fecha_resolucion
FROM ticket t 
JOIN historial_ticket h ON t.idTicket = h.idTicket 
WHERE h.estado = 'Resuelto' 
  AND h.fecha >= DATE_SUB((SELECT MAX(fecha) FROM historial_ticket), INTERVAL 5 DAY)
ORDER BY h.fecha DESC;

-- 10. Mostrar la cantidad de tickets "En progreso", "Resuelto" y "Pendiente"
SELECT estado, COUNT(*) AS cantidad 
FROM ticket 
GROUP BY estado;

-- 11. Lista de tickets "Pendiente" con el nombre del usuario que lo ha creado
SELECT t.idTicket, t.descripcion, t.categoria, t.prioridad, u.nombre AS usuario 
FROM ticket t 
JOIN usuario u ON t.email_usuario = u.email 
WHERE t.estado = 'Pendiente' 
ORDER BY t.idTicket;

-- 12. Lista de tickets "En progreso" con nombre del usuario y nombre del técnico
SELECT t.idTicket, t.descripcion, t.categoria, u.nombre AS usuario, tec.nombre AS tecnico 
FROM ticket t 
JOIN usuario u ON t.email_usuario = u.email 
LEFT JOIN tecnico tec ON t.email_tecnico = tec.email 
WHERE t.estado = 'En progreso' 
ORDER BY t.idTicket;

-- 13. Lista de tickets "Resuelto" de categorías que empiezan por "S" o por "A"
SELECT idTicket, descripcion, categoria, prioridad 
FROM ticket 
WHERE estado = 'Resuelto' AND (categoria LIKE 'S%' OR categoria LIKE 'A%')
ORDER BY categoria;

-- 14. Nombre de los 3 técnicos que más tickets han resuelto
SELECT tec.nombre, COUNT(t.idTicket) AS tickets_resueltos 
FROM tecnico tec 
LEFT JOIN ticket t ON tec.email = t.email_tecnico AND t.estado = 'Resuelto' 
GROUP BY tec.email, tec.nombre 
ORDER BY tickets_resueltos DESC 
LIMIT 3;

-- 15. Nombre e email de técnicos que no han resuelto ningún ticket
SELECT tec.nombre, tec.email 
FROM tecnico tec 
WHERE tec.email NOT IN (
    SELECT DISTINCT email_tecnico 
    FROM ticket 
    WHERE estado = 'Resuelto' AND email_tecnico IS NOT NULL
)
ORDER BY tec.nombre;

-- 16. Día del mes de febrero que más tickets se crearon
SELECT DAY(fecha_creacion) AS dia, COUNT(*) AS cantidad_tickets 
FROM ticket 
WHERE MONTH(fecha_creacion) = 2 AND YEAR(fecha_creacion) = 2025
GROUP BY DAY(fecha_creacion) 
ORDER BY cantidad_tickets DESC 
LIMIT 1;

-- 17. Nombre, email y número de tickets creados de los 5 usuarios que más tickets han creado
SELECT u.nombre, u.email, COUNT(t.idTicket) AS numero_tickets 
FROM usuario u 
LEFT JOIN ticket t ON u.email = t.email_usuario 
GROUP BY u.email, u.nombre 
ORDER BY numero_tickets DESC 
LIMIT 5;

-- 18. Descripción, fecha y estado de todos los tickets del técnico que ha resuelto menos tickets (al menos uno)
SELECT t.descripcion, t.fecha_creacion, t.estado 
FROM ticket t 
WHERE t.email_tecnico = (
    SELECT email_tecnico
    FROM ticket
    WHERE estado = 'Resuelto' AND email_tecnico IS NOT NULL
    GROUP BY email_tecnico
    ORDER BY COUNT(*) ASC
    LIMIT 1
)
ORDER BY t.fecha_creacion;

-- 19. Mostrar el último ticket registrado
SELECT idTicket, descripcion, categoria, prioridad, estado, fecha_creacion 
FROM ticket 
ORDER BY fecha_creacion DESC, idTicket DESC 
LIMIT 1;

-- 20. Mostrar todos los estados por los que ha pasado el ticket con idTicket 5
SELECT ht.idTicket, ht.estado, ht.fecha, t.descripcion, tec.nombre AS tecnico, u.nombre AS usuario 
FROM historial_ticket ht 
JOIN ticket t ON ht.idTicket = t.idTicket 
LEFT JOIN tecnico tec ON t.email_tecnico = tec.email 
JOIN usuario u ON t.email_usuario = u.email 
WHERE ht.idTicket = 5 
ORDER BY ht.fecha ASC;
