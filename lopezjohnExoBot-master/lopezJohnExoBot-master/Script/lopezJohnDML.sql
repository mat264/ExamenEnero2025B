-- database: ../DataBase/EXOBOT.sqlite
/*
|---------------------------------------------------|
|  Copyright (©) 2K25 EPN-FIS. All rights reserved  |
|  john.lopez02@epn.edu.ec                  jl        |
|---------------------------------------------------|
Autor: John Lopez
Fecha: 2.Enero.2k26
Script: Insertando catalogo
*/

INSERT INTO CatalogoTipo
   ( Nombre              ,Descripcion ) VALUES
   ('Tipo Persona'       ,'tipos sol, meca,...')
  ,('Sexo'               ,'mas, feme,...')
  ,('Estado Civil'       ,'sol, viu, cad,...')
  ,('Raza'               ,'Negro, blanco,...');


INSERT INTO Catalogo
  (IdCatalogoTipo   ,Nombre            ,Descripcion ) VALUES

  (  1              ,'Soldado'         ,'tipos de personas del ejercito')    --1
 ,(  1              ,'Mecanico'        ,'tipos de personas del ejercito')    --2
 ,(  1              ,'Experto Ing.'    ,'tipos de personas del ejercito')    --3
 ,(  1              ,'Experto Esp.'    ,'tipos de personas del ejercito')    --4

 ,(  2              ,'Masculino'       ,'tipos de sexualidad')               --5
 ,(  2              ,'Femenino'        ,'tipos de sexualidad')               --6
 ,(  2              ,'Hibrido'         ,'tipos de sexualidad')               --7
 ,(  2              ,'Asexual'         ,'tipos de sexualidad')               --8

 ,(  3              ,'Soltero'         ,'tipos de estado civil ecuatoriano') --9
 ,(  3              ,'Casado'          ,'tipos de estado civil ecuatoriano') --10
 ,(  3              ,'Divorciado'      ,'tipos de estado civil ecuatoriano') --11
 ,(  3              ,'Viudo'           ,'tipos de estado civil ecuatoriano') --12

 ,(  4              ,'Negro'           ,'tipos de etnia')
 ,(  4              ,'Blanco'          ,'tipos de etnia')
 ,(  4              ,'Mestizo'         ,'tipos de etnia')
 ,(  4              ,'Indigena'        ,'tipos de etnia');

INSERT INTO IABot  (Nombre      ,Observacion) 
           VALUES  ("IA-RUSO"  ,"Inteligencia Artificial");

INSERT INTO ExaBot 
  ( IdIABot     ,Nombre      ,Serie     ) VALUES 
  ( 1           ,"exabot1"   ,"Serie E1")
 ,( 1           ,"exabot2"   ,"Serie E2");

INSERT INTO Persona 
  ( IdCatalogoTipoPersona   ,IdCatalogoSexo     ,IdCatalogoEstadoCivil   ,Cedula      ,Nombre        ,Apellido) VALUES
  (  1                      , 5                 , 9                      ,'11111'     ,'Kevin'       ,'Putin' )
 ,(  2                      , 5                 , 10                     ,'22222'     ,'Alejando'    ,'Putin' ) 
 ,(  3                      , 5                 , 11                     ,'33333'     ,'Carlos'      ,'Putin' )
 ,(  4                      , 5                 , 12                     ,'44444'     ,'Pedro'       ,'Putin' )
 ,(  1                      , 6                 , 9                      ,'01010'     ,'Maria'       ,'Putin' )
 ,(  2                      , 6                 , 10                     ,'02020'     ,'Ana'         ,'Putin' )
 ,(  3                      , 6                 , 11                     ,'03030'     ,'Luisa'       ,'Putin' )
 ,(  4                      , 6                 , 12                     ,'04040'     ,'Sofia'       ,'Putin' );


SELECT IdCatalogo
,IdCatalogoTipo
,Nombre        
,Descripcion   
,Estado        
,FechaCreacion 
,FechaModifica 
FROM Catalogo
WHERE Estado = 'A'
AND IdCatalogoTipo = 2;


SELECT COUNT(*) TotalReg
FROM  Catalogo           
WHERE Estado = 'A'      
AND   IdCatalogoTipo = 2;