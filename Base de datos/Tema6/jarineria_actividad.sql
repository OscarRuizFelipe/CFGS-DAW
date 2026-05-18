Realice los siguientes procedimientos y funciones sobre la base de datos jardineria.
 
Función: calcular_precio_total_pedido
Descripción: Dado un código de pedido la función debe calcular la suma total del pedido. Tenga en cuenta que un pedido puede contener varios productos diferentes y varias cantidades de cada producto.
Parámetros de entrada: codigo_pedido (INT)
Parámetros de salida: El precio total del pedido (DECIMAL)  

--Función: calcular_precio_total_pedido  usa la base de datos jardineria para hacer
DELIMITER //
CREATE or replace FUNCTION calcular_precio_total_pedido(codigo_pedido INT) RETURNS DECIMAL(10,2)

BEGIN
    DECLARE precio_total DECIMAL(10,2);
    
    SELECT SUM((precio_unidad * cantidad)) INTO precio_total
    FROM detalle_pedido p
    WHERE codigo_pedido = p.codigo_pedido;
    
    RETURN precio_total;
END //
DELIMITER ; 

select calcular_precio_total_pedido(1);


Función: calcular_suma_pedidos_cliente
Descripción: Dado un código de cliente la función debe calcular la suma total de todos los pedidos realizados por el cliente. Deberá hacer uso de la función calcular_precio_total_pedido que ha desarrollado en el apartado anterior.
Parámetros de entrada: codigo_cliente (INT)
Parámetros de salida: La suma total de todos los pedidos del cliente (DECIMAL)

--Función: calcular_suma_pedidos_cliente 
DELIMITER //
CREATE or replace FUNCTION calcular_suma_pedidos_cliente(codigo_cliente INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE suma_total DECIMAL(10,2);
    
    SELECT SUM(calcular_precio_total_pedido(codigo_pedido)) INTO suma_total
    FROM pedido p
    WHERE codigo_cliente = p.codigo_cliente;
    
    RETURN suma_total;
END //
DELIMITER ;

SELECT calcular_suma_pedidos_cliente(1);

Función: calcular_suma_pagos_cliente
Descripción: Dado un código de cliente la función debe calcular la suma total de los pagos realizados por ese cliente.
Parámetros de entrada: codigo_cliente (INT)
Parámetros de salida: La suma total de todos los pagos del cliente (DECIMAL)


--Función: calcular_suma_pagos_cliente
DELIMITER //
CREATE or replace FUNCTION calcular_suma_pagos_cliente(codigo_cliente INT) RETURNS DECIMAL(10,2)
BEGIN
    DECLARE suma_pagos DECIMAL(10,2);
    
    SELECT SUM(total) INTO suma_pagos
    FROM pago p
    WHERE codigo_cliente = p.codigo_cliente;
    
    RETURN suma_pagos;
END //
DELIMITER ;

select calcular_suma_pagos_cliente(1);