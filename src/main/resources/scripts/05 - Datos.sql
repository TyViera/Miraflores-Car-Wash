--Usuario
INSERT INTO "public".usuario (enabled, nombrecompleto, password, username)
        VALUES(TRUE, 'Kevin Mendoza', '81dc9bdb52d04dc20036dbd8313ed055', 'kevin1');

--Rol
INSERT INTO "public".usuariorol (authority, usuarioid)
        VALUES('ROLE_USER', 1);

--Combo

INSERT INTO "public".combo (descripcion, nombre, numerolavadas) 
	VALUES ('4 Lavados Xpress + Cera, ahorra S/.20.00', 'COMBO XPRESS', 4);
INSERT INTO "public".combo (descripcion, nombre, numerolavadas) 
	VALUES ('5 Lavados Xpress + Cera + Motor o Rampa, ahorra S/.45.00', 'COMBO EJECUTIVO', 5);

--Cliente

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ALBERCA MORALES', '02698781', 'albj@mail.com', 'JUAN', '968982914');
 
INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ABARCA BAYONA', '02687244', 'juan_12@mail.com', 'JUAN NICOLÁS', '951289823');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('RAMÍREZ CORAL', '02680144', 'sonia@mail.com', 'SONIA', '957813472');

--Modelo

INSERT INTO "public".modelo (nombre) 
	VALUES ('CAMIONETA');
INSERT INTO "public".modelo (nombre) 
	VALUES ('AUTOMOVIL');
INSERT INTO "public".modelo (nombre) 
	VALUES ('MINIVAN');


--Combo Por Modelo
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (60, 1, 2);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (80, 1, 1);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (120, 1, 3);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (80, 2, 2);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (100, 2, 1);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (150, 2, 3);

--Carro
INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Acura', 'A8F115', 1, 1);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Chevrolet', 'C7Z017', 2, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Hyundai', 'F70473', 3, 3);
--Lavada

/*INSERT INTO "public".lavada (fechalavado, carroid) 
	VALUES (CURRENT_TIMESTAMP, 1);
*/
