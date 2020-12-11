
INSERT INTO tp.usuario
VALUES (1,'usuario1@gmail.com','contraseña1','Usuario 1',null,null,424113432,null),
(2,'usuario2@gmail.com','contraseña2','Usuario 2',null,null,40905123,null);


INSERT INTO tp.deporte 
VALUES (1,'Natacion'),
(2,'Futbol'),
(3,'Voleibol'),
(4,'Baloncesto'),
(5,'Tenis'),
(6,'Beisbol'),
(7,'Hockey'),
(8,'Rugby');

INSERT INTO tp.lugar_de_realizacion 
VALUES (1,'Monumental','sin descripcion',true,1,10),
(2,'La bombonera','sin descripcion',true,1,10),
(3,'Jose Amalfitani','sin descripcion',true,1,10),
(4,'15 de Abril','sin descripcion',true,1,10),
(5,'Brigadier Lopez','sin descripcion',true,1,10),
(6,'Estadio UTN','sin descripcion',true,2,10),
(7,'Gigante de Arroyito','sin descripcion',true,2,10),
(8,'Marcelo Bielsa','sin descripcion',true,2,10),
(9,'Gabino Sosa','sin descripcion',true,2,10),
(10,'Nuevo Monumental','sin descripcion',true,2,10);

INSERT INTO tp.deporte_lugar_de_realizacion
VALUES (1,1),(1,2),(1,6),(1,7),
(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),
(3,3),(3,4),(3,8),(3,9),
(4,5),(4,5),
(5,5),(5,5),
(6,1),(6,2),(6,6),(6,7),
(7,3),(7,4),(7,8),(7,9),
(8,3);

