

--Combo

INSERT INTO "public".combo (descripcion, nombre, numerolavadas) 
	VALUES ('4 Lavados Xpress + Cera, ahorra S/.20.00', 'COMBO XPRESS', 4);
INSERT INTO "public".combo (descripcion, nombre, numerolavadas) 
	VALUES ('5 Lavados Xpress + Cera + Motor o Rampa, ahorra S/.45.00', 'COMBO EJECUTIVO', 5);

--Cliente

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ALBERCA MORALES', '02698781', 'albj@mail.com', 'JUAN', '968982914');


--Modelo

INSERT INTO "public".modelo (nombre) 
	VALUES ('CAMIONETA');
INSERT INTO "public".modelo (nombre) 
	VALUES ('AUTOMOVIL');


--Combo Por Modelo
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (60, 1, 2);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (80, 1, 1);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (80, 2, 2);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (100, 2, 1);

--Lavada

INSERT INTO "public".lavada (fechalavado, carroid) 
	VALUES (CURRENT_TIMESTAMP, 1);
