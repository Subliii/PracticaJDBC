-- Ejecutar UNA vez como root (Workbench o: mysql -u root -p < 00_setup_root.sql)
-- Pasos 1, 4, 5 y 6 de la Actividad 2 (los que necesitan permisos de administrador)
CREATE DATABASE IF NOT EXISTS davante;
CREATE USER IF NOT EXISTS 'DavanteUser'@'localhost' IDENTIFIED BY '1234';
-- Por si el usuario ya existía con otra contraseña:
ALTER USER 'DavanteUser'@'localhost' IDENTIFIED BY '1234';
GRANT ALL PRIVILEGES ON davante.* TO 'DavanteUser'@'localhost';
FLUSH PRIVILEGES;
