DROP DATABASE IF EXISTS Inmobiliaria;
CREATE DATABASE Inmobiliaria;
USE Inmobiliaria;

--
-- 1. CREACIÓN DE TABLAS (DDL)
-- 

-- Tabla de Zonas
CREATE TABLE Zona (cp INT PRIMARY KEY,nombre VARCHAR(100) NOT NULL);

-- Tabla de Pisos (Clave primaria compuesta por ubicación física)
CREATE TABLE Piso (calle VARCHAR(100),
    numero INT,
    escalera VARCHAR(10),
    planta VARCHAR(10),
    puerta VARCHAR(10),
    metros DECIMAL(10,2),
    cp INT,
    PRIMARY KEY (calle, numero, escalera, planta, puerta),
    FOREIGN KEY (cp) REFERENCES Zona(cp)
);

-- Tabla de Personas
CREATE TABLE Persona (dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni_cabeza VARCHAR(9), 
    calle VARCHAR(100),
    numero INT,
    escalera VARCHAR(10),
    planta VARCHAR(10),
    puerta VARCHAR(10),
    FOREIGN KEY (calle, numero, escalera, planta, puerta) REFERENCES Piso(calle, numero, escalera, planta, puerta)
);

-- Tabla de Propietarios (Relación persona con piso)
CREATE TABLE Propietarios (dni VARCHAR(9),
    calle VARCHAR(100),
    numero INT,
    escalera VARCHAR(10),
    planta VARCHAR(10),
    puerta VARCHAR(10),
    fecha_compra DATE,
    PRIMARY KEY (dni, calle, numero, escalera, planta, puerta),
    FOREIGN KEY (dni) REFERENCES Persona(dni),
    FOREIGN KEY (calle, numero, escalera, planta, puerta) REFERENCES Piso(calle, numero, escalera, planta, puerta)
);

-- 
-- 2. INSERCIÓN DE DATOS (DML)
-- 
INSERT INTO Zona VALUES (28001, 'Centro'), (28005, 'Latina'), (28002, 'Retiro');
INSERT INTO Zona (cp, nombre) VALUES 
(28010, 'Chamberí'), 
(28045, 'Arganzuela');

INSERT INTO Piso VALUES 
('Damaso', 20, 'A', '2', '1', 75.0, 28001),
('Felipe II', 14, '1', '0', 'B', 45.0, 28002),
('Latina', 5, 'B', '3', 'C', 90.0, 28005),
('Damaso', 20, 'B', '1', '1', 55.0, 28001);

INSERT INTO Piso VALUES 
-- En Chamberí (Zonas que superarán los 70m2 de media)
('Almagro', 10, 'Dcha', '4', 'A', 150.0, 28010),
('Zurbano', 15, '1', '2', 'B', 110.0, 28010),
-- En Arganzuela (Zonas con media más baja)
('Delicias', 40, 'A', '1', '1', 45.0, 28045),
('Canarias', 12, 'B', '2', 'C', 50.0, 28045),
-- Más pisos en Centro para validar los primeros ejercicios
('Mayor', 1, 'Ext', '3', '1', 85.0, 28001);
INSERT INTO Piso (calle, numero, escalera, planta, puerta, metros, cp) VALUES 
('Paseo de la Castellana', 50, 'A', '1', 'A', 200.0, 28010),
('Calle Serrano', 100, 'B', 'Pral', '1', 200.0, 28010);



INSERT INTO Persona VALUES 
('44351312', 'Juan', 'Perez', NULL, 'Latina', 5, 'B', '3', 'C'),
('12345678', 'Ana', 'Gomez', NULL, 'Damaso', 20, 'A', '2', '1'),
('87654321', 'Luis', 'Sanz', '12345678', 'Damaso', 20, 'A', '2', '1');
INSERT INTO Persona (dni, nombre, apellidos, dni_cabeza, calle, numero, escalera, planta, puerta) VALUES 
('11111111', 'Beatriz', 'Luna', NULL, 'Almagro', 10, 'Dcha', '4', 'A'),
('22222222', 'Carlos', 'Villa', NULL, 'Delicias', 40, 'A', '1', '1'),
('33333333', 'Diego', 'Reina', '11111111', 'Almagro', 10, 'Dcha', '4', 'A');
INSERT INTO Persona (dni, nombre, apellidos) VALUES 
('99999999', 'Roberto', 'Dueñas');

INSERT INTO propietarios VALUES 
('44351312', 'Latina', 5, 'B', '3', 'C', '2020-01-15'),
('12345678', 'Damaso', 20, 'A', '2', '1', '2019-05-20');
INSERT INTO propietarios (dni, calle, numero, escalera, planta, puerta, fecha_compra) VALUES 
-- Beatriz Luna posee 2 propiedades (una en zona cara/grande)
('11111111', 'Almagro', 10, 'Dcha', '4', 'A', '2022-05-10'),
('11111111', 'Mayor', 1, 'Ext', '3', '1', '2023-11-15'),
-- Carlos Villa solo posee 1 propiedad en zona pequeña
('22222222', 'Delicias', 40, 'A', '1', '1', '2024-01-20'),
-- Luis Sanz (del script anterior) compra una segunda propiedad
('87654321', 'Zurbano', 15, '1', '2', 'B', '2025-02-01');
INSERT INTO propietarios (dni, calle, numero, escalera, planta, puerta, fecha_compra) VALUES 
('12345678', 'Paseo de la Castellana', 50, 'A', '1', 'A', '2026-03-01'),
('99999999', 'Calle Serrano', 100, 'B', 'Pral', '1', '2026-03-05');
