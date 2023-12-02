CREATE USER 'linker'@'localhost' IDENTIFIED BY 'linker';
CREATE USER 'linker'@'%' IDENTIFIED BY 'linker';

GRANT ALL PRIVILEGES ON *.* TO 'linker'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'linker'@'%';
--
CREATE DATABASE linker DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
