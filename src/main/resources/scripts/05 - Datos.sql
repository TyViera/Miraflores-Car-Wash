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
INSERT INTO "public".combo (descripcion, nombre, numerolavadas) 
	VALUES ('SI SOLO NECESITAS UNA LAVADA, ES PERFECTO PARA TI', 'COMBO UNICO', 1);
--Cliente

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ALBERCA MORALES', '02698781', 'albj@mail.com', 'JUAN', '968982914');
 
INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ABARCA BAYONA', '02687244', 'juan_12@mail.com', 'JUAN NICOLÁS', '951289823');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('RAMÍREZ CORAL', '02680144', 'sonia@mail.com', 'SONIA', '957813472');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('CHECA PEREZ', '40016575', 'jPerez@mail.com', 'JUANA', '940016575');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('JIMENEZ BAUTISTA', '76959777', 'jPerez@mail.com', 'CARMEN', '976959777');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('VIERA ARRUNATEGUI', '55782876', 'omar_232@mail.com', 'OMAR', '955782876');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('MADRID CHERREZ', '82007620', 'madrid291@mail.com', 'ALEXANDRA', '982007620');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('CALLE RUIZ', '75006674', 'josema@mail.com', 'JOSE MARIA', '975006674');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('ELIAS GARCIA', '12904217', 'elig_999@mail.com', 'MARCO', '912904217');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('MENDOZA ZARANGO', '12414284', 'juan_men@mail.com', 'JUAN', '912414284');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('MEDINA JULCA', '20712159', 'ivan009@mail.com', 'IVAN', '920712159');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('JIMENEZ GAMBOA', '92655899', 'willbrad@mail.com', 'WILLIAM', '992655899');

INSERT INTO "public".cliente (apellidos, dni, email, nombres, telefono) 
	VALUES ('TALLEDO FLORES', '45651234', 'lenny_talledo_93@mail.com', 'LENNY', '945651234');
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
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (30, 3, 1);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (20, 3, 2);
INSERT INTO "public".combopormodelo (precio, comboid, modeloid) 
	VALUES (40, 3, 3);

--Carro
INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Acura', 'A8F115', 1, 1);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Chevrolet', 'C7Z017', 2, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Hyundai', 'F70473', 3, 3);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Toyota', 'ABG473', 4, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Hyundai', 'RTA452', 5, 1);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Toyota', 'FDL912', 6, 3);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Chevrolet', 'HASO21', 7, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Ford', 'NAC001', 8, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Mazda', 'HGT009', 9, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Audi', 'APQ012', 10, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Peugeot', 'RZA368', 11, 2);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Nissan', 'BMF819', 12, 1);

INSERT INTO "public".carro (marca, placa, clienteid, modeloid) 
	VALUES ('Volvo', 'AJY671', 13, 2);

-- Cliente Combo Por Modelo

-- Cliente 1
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-01-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-02-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-03-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-04-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-05-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-06-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-07-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid)
        VALUES ('2015-08-04', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-14', 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-14', 1, 2);

-- Cliente 2
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-01-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-02-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-03-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-03-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-04-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-05-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-06-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid)
        VALUES ('2015-07-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 2, 1);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-01', 2, 3);

-- Cliente 3
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-01-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-02-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-03-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-04-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-05-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-06-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid)
        VALUES ('2015-07-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-01', 3, 6);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-15', 3, 5);

-- Cliente 4
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-01-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-02-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-03-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-04-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-05-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-06-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid)
        VALUES ('2015-07-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-08-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-01', 4, 8);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES ('2015-09-15', 4, 8);

INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES (CURRENT_TIMESTAMP, 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES (CURRENT_TIMESTAMP, 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES (CURRENT_TIMESTAMP, 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES (CURRENT_TIMESTAMP, 1, 2);
INSERT INTO "public".clientecombopormodelo (fecharegistro, clienteid, combopormodeloid) 
	VALUES (CURRENT_TIMESTAMP, 1, 2);

--Lavada

/*INSERT INTO "public".lavada (fechalavado, carroid) 
	VALUES (CURRENT_TIMESTAMP, 1);
*/
