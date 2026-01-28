-- Script de creación de tablas para ExoTrooper
-- Tabla de Exobots (Existente)
CREATE TABLE IF NOT EXISTS ExoBot (
    IdExobot INTEGER PRIMARY KEY,
    TipoExobot TEXT NOT NULL,
    Entreno TEXT NOT NULL,
    NumeroAccion INTEGER NOT NULL
);
-- Tabla de Usuarios (Nueva para Login)
CREATE TABLE IF NOT EXISTS Usuario (
    Cedula TEXT PRIMARY KEY,
    Nombre TEXT NOT NULL,
    Pin TEXT NOT NULL
);
-- Tabla opcional para logs de IA (Ya que se mencionaron todos los datos)
CREATE TABLE IF NOT EXISTS IALog (
    IdLog INTEGER PRIMARY KEY AUTOINCREMENT,
    TipoArma TEXT,
    Accion TEXT,
    Fecha TEXT
);
-- Insertar Usuario predeterminado solicitado (Si no existe)
INSERT
    OR IGNORE INTO Usuario (Cedula, Nombre, Pin)
VALUES ('1751330158', 'LOPEZ JOHN', '321');
INSERT
    OR IGNORE INTO Usuario (Cedula, Nombre, Pin)
VALUES ('patmic', 'Usuario Default', '123');