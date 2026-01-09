-- database: ../DataBase/EXOBOT.sqlite
/*
|---------------------------------------------------|
|  Copyright (©) 2K25 EPN-FIS. All rights reserved  |
|  john.lopez02@epn.edu.ec                  jl        |
|---------------------------------------------------|
Autor: John Lopez
Fecha: 2.Enero.2k26
Script: Creacion de la estructura de datos para ExoBot
*/

DROP TABLE IF EXISTS Persona;

DROP TABLE IF EXISTS ExaBot;

DROP TABLE IF EXISTS Catalogo;

DROP TABLE IF EXISTS CatalogoTipo;

DROP TABLE IF EXISTS IABot;


CREATE TABLE CatalogoTipo (
    IdCatalogoTipo    INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT
   ,Nombre            VARCHAR(10)  NOT NULL UNIQUE
   ,Descripcion       VARCHAR(90)  

   ,Estado            VARCHAR(1)   NOT NULL DEFAULT ('A')
   ,FechaCreacion     DATETIME     DEFAULT (datetime('now','localtime'))
   ,FechaModifica     DATETIME 
);

CREATE TABLE Catalogo (
    IdCatalogo        INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT
   ,IdCatalogoTipo    INTEGER      NOT NULL REFERENCES CatalogoTipo(IdCatalogoTipo)
   ,Nombre            VARCHAR(10)  NOT NULL UNIQUE
   ,Descripcion       VARCHAR(90) 

   ,Estado            VARCHAR(1)   NOT NULL DEFAULT ('A')
   ,FechaCreacion     DATETIME     DEFAULT (datetime('now','localtime'))
   ,FechaModifica     DATETIME 
);

CREATE TABLE IABot (
     IdIABot          INTEGER      PRIMARY KEY AUTOINCREMENT
    ,Nombre           TEXT         NOT NULL UNIQUE
    ,Observacion      TEXT         
    ,FechaCreacion    DATETIME     DEFAULT (datetime('now','localtime'))

);

CREATE TABLE ExaBot (
     IdExaBot         INTEGER      PRIMARY KEY AUTOINCREMENT
    ,IdIABot          INTEGER      NOT NULL 
    ,Nombre           TEXT         NOT NULL 
    ,Serie            TEXT         NOT NULL 

    ,CONSTRAINT fk_IABot FOREIGN KEY (IdIABot) REFERENCES IABot(IdIABot)
);

-- CREATE TABLE PersonaTipo (
--      IdPersonaTipo     INTEGER      PRIMARY KEY AUTOINCREMENT
--     ,Descripcion       TEXT         NOT NULL UNIQUE
--     ,FechaCrea         DATETIME     DEFAULT current_timestamp
-- );

CREATE TABLE Persona (
    IdPersona                  INTEGER      PRIMARY KEY AUTOINCREMENT
   ,IdCatalogoTipoPersona      INTEGER      NOT NULL REFERENCES Catalogo(IdCatalogo)
   ,IdCatalogoSexo             INTEGER      NOT NULL REFERENCES Catalogo(IdCatalogo)
   ,IdCatalogoEstadoCivil      INTEGER      NOT NULL REFERENCES Catalogo(IdCatalogo)
   ,Cedula                     VARCHAR(10)  NOT NULL UNIQUE
   ,Nombre                     VARCHAR(50)  NOT NULL    
   ,Apellido                   VARCHAR(50)  NOT NULL

   ,Estado                     VARCHAR(1)   NOT NULL DEFAULT ('A')
   ,FechaCreacion              DATETIME     DEFAULT (datetime('now','localtime'))
   ,FechaModifica              DATETIME 
);