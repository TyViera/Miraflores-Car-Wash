--Creacion de la base de datos--
CREATE ty user_lavado PASSWORD '123456';
CREATE DATABASE lavado_db WITH OWNER user_lavado;
GRANT ALL PRIVILEGES ON DATABASE lavado_db TO user_lavado;

