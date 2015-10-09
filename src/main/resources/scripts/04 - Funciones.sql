--Tipos de datos--
--------------------------------------------------------------------------------
CREATE TYPE combo_reporte AS(
    id          BIGINT,
    nombre      VARCHAR,
    cantidad    NUMERIC
);
--------------------------------------------------------------------------------
CREATE TYPE cliente_reporte AS(
    id          BIGINT,
    nombres      VARCHAR,
    apellidos    VARCHAR,
    cantidad    NUMERIC
);
--------------------------------------------------------------------------------
CREATE TYPE venta_reporte AS(
    id          BIGINT,
    cantidad    NUMERIC
);
CREATE TYPE credito AS(
    id BIGINT,
    nombres VARCHAR,
    apellidos VARCHAR,
    dni VARCHAR,
    lavadas Integer,
    modeloCarro VARCHAR
);
--------------------------------------------------------------------------------
--------------------------------------------------------------------------------
-- Funciones--
--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION ventas_intervalo(f_min DATE,f_max DATE) RETURNS REAL
AS $BODY$
DECLARE
    suma REAL;
BEGIN
    suma := 0;
    SELECT SUM(c.precio) AS suma INTO suma
        FROM clientecombopormodelo AS cl
        INNER JOIN combopormodelo AS c ON c.id=cl.combopormodeloid
        WHERE CAST(cl.fecharegistro AS DATE) > f_min 
            AND CAST(cl.fecharegistro AS DATE) <= f_max;
    RETURN suma;
END;
$BODY$ LANGUAGE plpgsql;

--------------------------------------------------------------------------------
-- Reportes -- 
-- Dinero recaudado por cada Combo --
CREATE OR REPLACE FUNCTION ventas_combos() RETURNS SETOF combo_reporte
AS $BODY$
BEGIN
    RETURN QUERY SELECT c.id,c.nombre, CAST(SUM(cpm.precio) AS NUMERIC)
            FROM combo c
            INNER JOIN combopormodelo cpm ON cpm.comboid=c.id
            INNER JOIN clientecombopormodelo cli ON cpm.id=cli.combopormodeloid
            GROUP BY c.id;
    RETURN QUERY SELECT c.id,c.nombre, CAST (0.0 AS NUMERIC)
            FROM combo c
            WHERE c.id NOT IN (
                SELECT cpm.comboid FROM combopormodelo cpm
                INNER JOIN clientecombopormodelo cli ON cpm.id=cli.combopormodeloid
            );
END;
$BODY$ LANGUAGE plpgsql;

-- Dinero recaudado por cada Cliente --
CREATE OR REPLACE FUNCTION ventas_clientes() RETURNS SETOF cliente_reporte
AS $BODY$
BEGIN
    RETURN QUERY SELECT c.id,c.nombres,c.apellidos, CAST (SUM(cpm.precio) AS NUMERIC)
            FROM cliente c
            INNER JOIN clientecombopormodelo cli ON c.id=cli.clienteid
            INNER JOIN combopormodelo cpm ON cli.combopormodeloid=cpm.id
            GROUP BY c.id;
    RETURN QUERY SELECT c.id,c.nombres,c.apellidos, CAST (0.00 AS NUMERIC)
            FROM cliente c
            WHERE c.id NOT IN (
                SELECT ccpm.clienteid FROM clientecombopormodelo ccpm
            )
            ;
END;
$BODY$ LANGUAGE plpgsql;

-- Dinero recaudado por cada Cliente Este mes--
-- select * from ventas_clientes_mes();
CREATE OR REPLACE FUNCTION ventas_clientes_mes() RETURNS SETOF cliente_reporte
AS $BODY$
DECLARE
    f_min DATE;
    f_max DATE;
BEGIN
    f_min := CAST( date_trunc('month',current_date) AS DATE);
    f_max := CAST( date_trunc('month', 
            (CAST(current_date AS DATE) + CAST('1 month' AS INTERVAL))) AS DATE);
    RETURN QUERY SELECT c.id,c.nombres,c.apellidos, CAST (SUM(cpm.precio) AS NUMERIC) 
            FROM cliente c
            INNER JOIN clientecombopormodelo cli ON c.id=cli.clienteid
            INNER JOIN combopormodelo cpm ON cli.combopormodeloid=cpm.id
            WHERE CAST(cli.fecharegistro AS DATE) >= f_min 
                    AND CAST(cli.fecharegistro AS DATE) < f_max
            GROUP BY c.id;
    RETURN QUERY SELECT c.id,c.nombres,c.apellidos, CAST (0.00 AS NUMERIC)
            FROM cliente c
            WHERE c.id NOT IN (
                SELECT ccpm.clienteid FROM clientecombopormodelo ccpm
            ) OR c.id NOT IN(
                SELECT ccpm.clienteid FROM clientecombopormodelo ccpm
                WHERE CAST(ccpm.fecharegistro AS DATE) >= f_min 
                    AND CAST(ccpm.fecharegistro AS DATE) < f_max
            )
            ;
END;
$BODY$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION ventas_cliente_intervalo
            (idCliente BIGINT, f_min DATE, f_max DATE) RETURNS NUMERIC
AS $BODY$
DECLARE
    suma NUMERIC;
BEGIN
    SELECT SUM(c.precio) AS suma INTO suma
    FROM clientecombopormodelo AS cl
    INNER JOIN combopormodelo AS c ON c.id=cl.combopormodeloid
    WHERE CAST(cl.fecharegistro AS DATE) > f_min 
        AND CAST(cl.fecharegistro AS DATE) <= f_max
        AND cl.clienteid=idCliente;
    RETURN suma;
END;
$BODY$ LANGUAGE plpgsql;

-- select * from ventas_cliente_por_mes(1);
CREATE OR REPLACE FUNCTION ventas_cliente_por_mes(idCliente BIGINT) RETURNS SETOF cliente_reporte
AS $BODY$
DECLARE
    i INTEGER;
    numero INTEGER;
    fechaActual DATE;
    fecha DATE;
    f_min DATE;
    f_max DATE;
    total NUMERIC;
BEGIN
    i := 0;
    numero := 10;
    SELECT CURRENT_DATE INTO fechaActual;
    LOOP
        IF i >= numero THEN
            EXIT;
        END IF;
        SELECT CAST(fechaActual AS DATE) - CAST('1 month' AS INTERVAL) INTO fecha;
        f_min := CAST( date_trunc('month',fecha) AS DATE);
        f_max := CAST( date_trunc('month', 
            (CAST(fechaActual AS DATE) + CAST('1 month' AS INTERVAL))) AS DATE);

        SELECT * INTO total from ventas_cliente_intervalo(idCliente,f_min,f_max);
        IF total IS NULL THEN
            total := 0;
        END IF;
        RETURN QUERY SELECT c.id, c.nombres, c.apellidos, total FROM cliente AS c WHERE c.id = idCliente;
        fechaActual := fecha;
        i := i+1;
    END LOOP;
    RETURN;
END;
$BODY$ LANGUAGE plpgsql;

-- Dinero recaudado en las ultimas n semanas --
CREATE OR REPLACE FUNCTION ventas_porSemana(numero INTEGER) RETURNS SETOF REAL
AS $BODY$
DECLARE
    valores combopormodelo%ROWTYPE;
    i INTEGER;
    fechaActual DATE;
    fecha DATE;
    total REAL;
BEGIN
    i := 0;
    SELECT CURRENT_DATE INTO fechaActual;
    LOOP
        IF i >= numero THEN
            EXIT;
        END IF;
        SELECT CAST(fechaActual AS DATE) - CAST('7 days' AS INTERVAL) INTO fecha;
        SELECT * INTO total from ventas_intervalo(fecha,fechaActual);
        IF total IS NULL THEN
            total := 0;
        END IF;
        RETURN NEXT total;
        fechaActual := fecha;
        i := i+1;
    END LOOP;

    RETURN;
END;
$BODY$ LANGUAGE plpgsql;

-- Dinero recaudado en los ultimas n meses --
CREATE OR REPLACE FUNCTION ventas_porMes(numero INTEGER) RETURNS SETOF REAL
AS $BODY$
DECLARE
    valores combopormodelo%ROWTYPE;
    i INTEGER;
    fechaActual DATE;
    fecha DATE;
    total REAL;
BEGIN
    i := 0;
    SELECT CURRENT_DATE INTO fechaActual;
    LOOP
        IF i >= numero THEN
            EXIT;
        END IF;
        SELECT CAST(fechaActual AS DATE) - CAST('1 month' AS INTERVAL) INTO fecha;
        SELECT * INTO total from ventas_intervalo(fecha,fechaActual);
        IF total IS NULL THEN
            total := 0;
        END IF;        
        RETURN NEXT total;
        fechaActual := fecha;
        i := i+1;
    END LOOP;

    RETURN;
END;
$BODY$ LANGUAGE plpgsql;

-- Dinero recaudado en los ultimos n dias --
CREATE OR REPLACE FUNCTION ventas_porDia(numero INTEGER) RETURNS SETOF REAL
AS $BODY$
DECLARE
    valores combopormodelo%ROWTYPE;
    i INTEGER;
    fechaActual DATE;
    fecha DATE;
    total REAL;
BEGIN
    i := 0;
    SELECT CURRENT_DATE INTO fechaActual;
    LOOP
        IF i >= numero THEN
            EXIT;
        END IF;
        SELECT CAST(fechaActual AS DATE) - CAST('1 day' AS INTERVAL) INTO fecha;
        SELECT * INTO total from ventas_intervalo(fecha,fechaActual);
        IF total IS NULL THEN
            total := 0;
        END IF;        
        RETURN NEXT total;
        fechaActual := fecha;
        i := i+1;
    END LOOP;

    RETURN;
END;
$BODY$ LANGUAGE plpgsql;

--
-- select * from credito_cliente(2);
CREATE OR REPLACE FUNCTION credito_cliente(idCliente BIGINT) RETURNS SETOF credito
AS $BODY$
DECLARE
BEGIN
    RETURN QUERY SELECT c.id, c.nombres, c.apellidos, c.dni, ld.numerolavadas, m.nombre
    FROM lavadadisponible ld
    INNER JOIN cliente c ON c.id = ld.clienteid
    INNER JOIN modelo m ON m.id = ld.modeloid
    WHERE ld.clienteid = idCliente;
END;
$BODY$ LANGUAGE plpgsql;