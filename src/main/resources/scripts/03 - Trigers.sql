--------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION compra_combo_insert() RETURNS TRIGGER 
AS $BODY$
DECLARE
    id_modelo BIGINT;
    id_cliente BIGINT;
    n_lavadas INTEGER;
    
BEGIN
    id_cliente := NEW.clienteid;
    
    SELECT cpm.modeloid INTO id_modelo 
        FROM combopormodelo AS cpm
        WHERE cpm.id = NEW.combopormodeloid;
    
    SELECT c.numerolavadas INTO n_lavadas 
        FROM combopormodelo AS cpm
        INNER JOIN combo AS c ON cpm.comboid = c.id
        WHERE cpm.id = NEW.combopormodeloid;

    
    IF(EXISTS(SELECT * FROM lavadadisponible AS ld
        WHERE ld.clienteid=id_cliente AND ld.modeloid=id_modelo)) THEN
        UPDATE lavadadisponible SET numerolavadas = numerolavadas + n_lavadas;
    ELSE
        INSERT INTO lavadadisponible (clienteid, modeloid, numerolavadas)
            VALUES (id_cliente, id_modelo, n_lavadas);
    END IF;
    
    RETURN NEW;
  
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER compra_combo
    BEFORE INSERT ON "public".clientecombopormodelo
    FOR EACH ROW EXECUTE PROCEDURE compra_combo_insert();


--------------------------------------------------------------------------------  
CREATE OR REPLACE FUNCTION lavada_insert() RETURNS TRIGGER 
AS $BODY$
DECLARE
    id_modelo BIGINT;
    id_cliente BIGINT;
    id_lavada BIGINT;
    n_lavadas INTEGER;
BEGIN
    SELECT c.modeloid, c.clienteid INTO id_modelo, id_cliente
        FROM Carro AS c
        WHERE c.id=NEW.carroid;
    IF(EXISTS(SELECT * FROM lavadadisponible 
        WHERE clienteid = id_cliente AND modeloid = id_modelo)) THEN

        SELECT id, numerolavadas INTO id_lavada, n_lavadas
            FROM lavadadisponible 
            WHERE clienteid = id_cliente AND modeloid = id_modelo;
        IF(n_lavadas > 0) THEN
            UPDATE lavadadisponible SET numerolavadas = numerolavadas - 1
                WHERE clienteid = id_cliente AND modeloid = id_modelo;
        ELSE
            RAISE EXCEPTION 'No hay credito suficiente';
        END IF;
    ELSE
        RAISE EXCEPTION 'No hay credito suficiente';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER lavada_tg
    BEFORE INSERT ON "public".lavada
    FOR EACH ROW EXECUTE PROCEDURE lavada_insert();

