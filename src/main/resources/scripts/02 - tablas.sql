
--- SE RECOMIENDA DEJAR QUE HIBERNATE GENERE LAS TABLAS ---

CREATE TABLE public.Modelo (
                id SERIAL NOT NULL ,
                nombre VARCHAR NOT NULL,
                CONSTRAINT modelo_pk PRIMARY KEY (id)
);

CREATE TABLE public.Combo (
                id SERIAL NOT NULL ,
                numeroLavadas SMALLINT NOT NULL,
                nombre VARCHAR NOT NULL,
                descripcion VARCHAR NOT NULL,
                CONSTRAINT combo_pk PRIMARY KEY (id)
);


CREATE TABLE public.ComboPorModelo (
                id SERIAL NOT NULL ,
                modeloId BIGINT NOT NULL,
                comboId BIGINT NOT NULL,
                precio NUMERIC(10,2) NOT NULL,
                CONSTRAINT combopormodelo_pk PRIMARY KEY (id)
);


CREATE TABLE public.Usuario (
                id SERIAL NOT NULL ,
                username VARCHAR(30) NOT NULL,
                password CHAR(32) NOT NULL,
                enabled BOOLEAN NOT NULL,
                nombreCompleto VARCHAR NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (id)
);

CREATE TABLE public.UsuarioRol (
                id SERIAL NOT NULL ,
                authority VARCHAR(50) NOT NULL,
                usuarioid BIGINT NOT NULL,
                CONSTRAINT usuariorol_pk PRIMARY KEY (id)
);


CREATE TABLE public.Cliente (
                id SERIAL NOT NULL ,
                nombres VARCHAR NOT NULL,
                apellidos VARCHAR NOT NULL,
                dni CHAR(8) NOT NULL,
                telefono VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                CONSTRAINT cliente_pk PRIMARY KEY (id)
);


CREATE TABLE public.ClienteComboPorModelo (
                id SERIAL NOT NULL ,
                clienteId BIGINT NOT NULL,
                comboPorModeloId BIGINT NOT NULL,
                fechaRegistro TIMESTAMP NOT NULL,
                CONSTRAINT clientecombopormodelo_pk PRIMARY KEY (id)
);


CREATE TABLE public.LavadaDisponible (
                id SERIAL NOT NULL ,
                clienteId BIGINT NOT NULL,
                modeloId BIGINT NOT NULL,
                numeroLavadas SMALLINT NOT NULL,
                CONSTRAINT lavadadisponible_pk PRIMARY KEY (id)
);


CREATE TABLE public.Carro (
                id SERIAL NOT NULL ,
                placa VARCHAR NOT NULL,
                modeloId BIGINT NOT NULL,
                marca VARCHAR NOT NULL,
                clienteId BIGINT NOT NULL,
                CONSTRAINT carro_pk PRIMARY KEY (id)
);


CREATE TABLE public.Lavada (
                id SERIAL NOT NULL ,
                carroId BIGINT NOT NULL,
                estado CHAR(3) NOT NULL,
                fechaLavado TIMESTAMP NOT NULL,
                CONSTRAINT lavada_pk PRIMARY KEY (id)
);


CREATE TABLE public.ObjetoCustodia (
                id SERIAL NOT NULL ,
                nombre VARCHAR(80) NOT NULL,
                cantidad INTEGER NOT NULL,
                lavadaId BIGINT NOT NULL,
                CONSTRAINT objetocustodia_pk PRIMARY KEY (id)
);


ALTER TABLE public.Carro ADD CONSTRAINT modelo_carro_fk
FOREIGN KEY (modeloId)
REFERENCES public.Modelo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.LavadaDisponible ADD CONSTRAINT modelo_lavadadisponible_fk
FOREIGN KEY (modeloId)
REFERENCES public.Modelo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ComboPorModelo ADD CONSTRAINT modelo_combopormodelo_fk
FOREIGN KEY (modeloId)
REFERENCES public.Modelo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ComboPorModelo ADD CONSTRAINT combo_combopormodelo_fk
FOREIGN KEY (comboId)
REFERENCES public.Combo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ClienteComboPorModelo ADD CONSTRAINT combopormodelo_clientecombopormodelo_fk
FOREIGN KEY (comboPorModeloId)
REFERENCES public.ComboPorModelo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.UsuarioRol ADD CONSTRAINT usuario_usuariorol_fk
FOREIGN KEY (usuarioid)
REFERENCES public.Usuario (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Carro ADD CONSTRAINT cliente_carro_fk
FOREIGN KEY (clienteId)
REFERENCES public.Cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.LavadaDisponible ADD CONSTRAINT cliente_lavadadisponible_fk
FOREIGN KEY (clienteId)
REFERENCES public.Cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ClienteComboPorModelo ADD CONSTRAINT cliente_clientecombopormodelo_fk
FOREIGN KEY (clienteId)
REFERENCES public.Cliente (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Lavada ADD CONSTRAINT carro_lavada_fk
FOREIGN KEY (carroId)
REFERENCES public.Carro (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ObjetoCustodia ADD CONSTRAINT lavada_objetocustodia_fk
FOREIGN KEY (lavadaId)
REFERENCES public.Lavada (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;