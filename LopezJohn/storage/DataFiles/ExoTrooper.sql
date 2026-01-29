-- Script de creación de tabla para ExoTrooper
CREATE TABLE IF NOT EXISTS ExoBot (
    IdExobot INTEGER PRIMARY KEY,
    TipoExobot TEXT NOT NULL,
    Entreno TEXT NOT NULL,
    NumeroAccion INTEGER NOT NULL
);
CREATE TABLE IF NOT EXISTS Usuario (
    Cedula TEXT PRIMARY KEY,
    Nombre TEXT NOT NULL,
    Clave TEXT NOT NULL
);