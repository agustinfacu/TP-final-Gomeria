-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Gomeria
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Gomeria` ;

-- -----------------------------------------------------
-- Schema Gomeria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Gomeria` DEFAULT CHARACTER SET utf8 ;
USE `Gomeria` ;

-- -----------------------------------------------------
-- Table `vehiculo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehiculo` ;

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `id_vehiculo` INT NOT NULL AUTO_INCREMENT,
  `color` VARCHAR(10) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `kilometraje` DECIMAL(7,0) NOT NULL,
  `numero_de_chasis` VARCHAR(17) NOT NULL,
  `numero_de_motor` VARCHAR(20) NOT NULL,
  `patente` VARCHAR(8) NOT NULL,
  PRIMARY KEY (`id_vehiculo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auto` ;

CREATE TABLE IF NOT EXISTS `auto` (
  `id_auto` INT NOT NULL AUTO_INCREMENT,
  `cantidad_de_puerta` SMALLINT NOT NULL,
  `cantidad_de_rueda` SMALLINT NOT NULL,
  `id_vehiculo` INT NOT NULL,
  PRIMARY KEY (`id_auto`),
  CONSTRAINT `fk_auto_vehiculo1`
    FOREIGN KEY (`id_vehiculo`)
	REFERENCES `vehiculo` (`id_vehiculo`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moto` ;

CREATE TABLE IF NOT EXISTS `moto` (
  `id_table1` INT NOT NULL AUTO_INCREMENT,
  `cilindrada` VARCHAR(6) NOT NULL,
  `cantidad_de_rueda` SMALLINT NOT NULL,
  `id_vehiculo` INT NOT NULL,
  PRIMARY KEY (`id_table1`),
  CONSTRAINT `fk_moto_vehiculo1`
    FOREIGN KEY (`id_vehiculo`)
    REFERENCES `vehiculo` (`id_vehiculo`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `auto_de_prueba`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `auto_de_prueba` ;

CREATE TABLE IF NOT EXISTS `auto_de_prueba` (
  `id_auto_de_prueba` INT NOT NULL AUTO_INCREMENT,
  `cantidad_de_puerta` SMALLINT NOT NULL,
  `cantidad_de_rueda` SMALLINT NOT NULL,
  `id_vehiculo` INT NOT NULL,
  PRIMARY KEY (`id_auto_de_prueba`),
  CONSTRAINT `fk_auto_de_prueba_vehiculo1`
    FOREIGN KEY (`id_vehiculo`)
    REFERENCES `vehiculo` (`id_vehiculo`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `moto_de_prueba`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `moto_de_prueba` ;

CREATE TABLE IF NOT EXISTS `moto_de_prueba` (
  `id_moto_de_prueba` INT NOT NULL AUTO_INCREMENT,
  `cilindrada` VARCHAR(6) NOT NULL,
  `cantidad_de_rueda` SMALLINT NOT NULL,
  `id_vehiculo` INT NOT NULL,
  PRIMARY KEY (`id_moto_de_prueba`),
  CONSTRAINT `fk_moto_de_prueba_vehiculo1`
    FOREIGN KEY (`id_vehiculo`)
    REFERENCES `vehiculo` (`id_vehiculo`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- -----------------------------------------------------
-- INSERTS
-- -----------------------------------------------------
-- crear 100 vehiculos para pruebas
INSERT INTO `vehiculo` (`color`, `marca`, `kilometraje`, `numero_de_chasis`, `numero_de_motor`, `patente`)
VALUES

('Verde', 'Mazda', 60000, '1HGBH41JXMN109191', '4A3AA46G1YE123461', 'PQR1234'),
('Amarillo', 'Hyundai', 70000, '1HGBH41JXMN109192', '4A3AA46G1YE123462', 'STU5678'),
('Naranja', 'Kia', 80000, '1HGBH41JXMN109193', '4A3AA46G1YE123463', 'VWX9012'),
('Violeta', 'Volkswagen', 90000, '1HGBH41JXMN109194', '4A3AA46G1YE123464', 'YZA3456'),
('Marrón', 'Subaru', 100000, '1HGBH41JXMN109195', '4A3AA46G1YE123465', 'BCD7890'),
('Rojo', 'Toyota', 110000, '1HGBH41JXMN109196', '4A3AA46G1YE123466', 'CDE1234'),
('Azul', 'Ford', 120000, '1HGBH41JXMN109197', '4A3AA46G1YE123467', 'EFG5678'),
('Negro', 'Chevrolet', 130000, '1HGBH41JXMN109198', '4A3AA46G1YE123468', 'HIJ9012'),
('Blanco', 'Honda', 140000, '1HGBH41JXMN109199', '4A3AA46G1YE123469', 'JKL3456'),
('Gris', 'Nissan', 150000, '1HGBH41JXMN109200', '4A3AA46G1YE123470', 'MNO7890'),
('Verde', 'Mazda', 160000, '1HGBH41JXMN109201', '4A3AA46G1YE123471', 'PQR1234'),
('Amarillo', 'Hyundai', 170000, '1HGBH41JXMN109202', '4A3AA46G1YE123472', 'STU5678'),
('Naranja', 'Kia', 180000, '1HGBH41JXMN109203', '4A3AA46G1YE123473', 'VWX9012'),
('Violeta', 'Volkswagen', 190000, '1HGBH41JXMN109204', '4A3AA46G1YE123474', 'YZA3456'),
('Marrón', 'Subaru', 200000, '1HGBH41JXMN109205', '4A3AA46G1YE123475', 'BCD7890'),
('Rojo', 'Toyota', 210000, '1HGBH41JXMN109206', '4A3AA46G1YE123476', 'CDE1234'),
('Azul', 'Ford', 220000, '1HGBH41JXMN109207', '4A3AA46G1YE123477', 'EFG5678'),
('Negro', 'Chevrolet', 230000, '1HGBH41JXMN109208', '4A3AA46G1YE123478', 'HIJ9012'),
('Blanco', 'Honda', 240000, '1HGBH41JXMN109209', '4A3AA46G1YE123479', 'JKL3456'),
('Gris', 'Nissan', 250000, '1HGBH41JXMN109210', '4A3AA46G1YE123480', 'MNO7890'),
('Verde', 'Mazda', 260000, '1HGBH41JXMN109211', '4A3AA46G1YE123481', 'PQR1234'),
('Amarillo', 'Hyundai', 270000, '1HGBH41JXMN109212', '4A3AA46G1YE123482', 'STU5678'),
('Naranja', 'Kia', 280000, '1HGBH41JXMN109213', '4A3AA46G1YE123483', 'VWX9012'),
('Violeta', 'Volkswagen', 290000, '1HGBH41JXMN109214', '4A3AA46G1YE123484', 'YZA3456'),
('Marrón', 'Subaru', 300000, '1HGBH41JXMN109215', '4A3AA46G1YE123485', 'BCD7890'),
('Rojo', 'Toyota', 310000, '1HGBH41JXMN109216', '4A3AA46G1YE123486', 'CDE1234'),
('Azul', 'Ford', 320000, '1HGBH41JXMN109217', '4A3AA46G1YE123487', 'EFG5678'),
('Negro', 'Chevrolet', 330000, '1HGBH41JXMN109218', '4A3AA46G1YE123488', 'HIJ9012'),
('Blanco', 'Honda', 340000, '1HGBH41JXMN109219', '4A3AA46G1YE123489', 'JKL3456'),
('Gris', 'Nissan', 350000, '1HGBH41JXMN109220', '4A3AA46G1YE123490', 'MNO7890'),
('Verde', 'Mazda', 360000, '1HGBH41JXMN109221', '4A3AA46G1YE123491', 'PQR1234'),
('Amarillo', 'Hyundai', 370000, '1HGBH41JXMN109222', '4A3AA46G1YE123492', 'STU5678'),
('Naranja', 'Kia', 380000, '1HGBH41JXMN109223', '4A3AA46G1YE123493', 'VWX9012'),
('Violeta', 'Volkswagen', 390000, '1HGBH41JXMN109224', '4A3AA46G1YE123494', 'YZA3456'),
('Marrón', 'Subaru', 400000, '1HGBH41JXMN109225', '4A3AA46G1YE123495', 'BCD7890'),
('Rojo', 'Toyota', 410000, '1HGBH41JXMN109226', '4A3AA46G1YE123496', 'CDE1234'),
('Azul', 'Ford', 420000, '1HGBH41JXMN109227', '4A3AA46G1YE123497', 'EFG5678'),
('Negro', 'Chevrolet', 430000, '1HGBH41JXMN109228', '4A3AA46G1YE123498', 'HIJ9012'),
('Blanco', 'Honda', 440000, '1HGBH41JXMN109229', '4A3AA46G1YE123499', 'JKL3456'),
('Gris', 'Nissan', 450000, '1HGBH41JXMN109230', '4A3AA46G1YE123500', 'MNO7890'),
('Verde', 'Mazda', 460000, '1HGBH41JXMN109231', '4A3AA46G1YE123501', 'PQR1234'),
('Amarillo', 'Hyundai', 470000, '1HGBH41JXMN109232', '4A3AA46G1YE123502', 'STU5678'),
('Naranja', 'Kia', 480000, '1HGBH41JXMN109233', '4A3AA46G1YE123503', 'VWX9012'),
('Violeta', 'Volkswagen', 490000, '1HGBH41JXMN109234', '4A3AA46G1YE123504', 'YZA3456'),
('Marrón', 'Subaru', 500000, '1HGBH41JXMN109235', '4A3AA46G1YE123505', 'BCD7890'),
-- 50 motos
('Rojo', 'Yamaha', 10000, '1HGBH41JXMN109286', '4A3AA46G1YE123556', 'XYZ1234'),
('Azul', 'Honda', 20000, '1HGBH41JXMN109287', '4A3AA46G1YE123557', 'ABC5678'),
('Negro', 'Kawasaki', 30000, '1HGBH41JXMN109288', '4A3AA46G1YE123558', 'DEF9012'),
('Blanco', 'Suzuki', 40000, '1HGBH41JXMN109289', '4A3AA46G1YE123559', 'GHI3456'),
('Gris', 'Ducati', 50000, '1HGBH41JXMN109290', '4A3AA46G1YE123560', 'JKL7890'),
('Verde', 'BMW', 60000, '1HGBH41JXMN109291', '4A3AA46G1YE123561', 'MNO1234'),
('Amarillo', 'Aprilia', 70000, '1HGBH41JXMN109292', '4A3AA46G1YE123562', 'PQR5678'),
('Naranja', 'Triumph', 80000, '1HGBH41JXMN109293', '4A3AA46G1YE123563', 'STU9012'),
('Violeta', 'Harley-Davidson', 90000, '1HGBH41JXMN109294', '4A3AA46G1YE123564', 'VWX3456'),
('Marrón', 'KTM', 100000, '1HGBH41JXMN109295', '4A3AA46G1YE123565', 'YZA7890'),
('Rojo', 'Yamaha', 110000, '1HGBH41JXMN109296', '4A3AA46G1YE123566', 'BCD1234'),
('Azul', 'Honda', 120000, '1HGBH41JXMN109297', '4A3AA46G1YE123567', 'CDE5678'),
('Negro', 'Kawasaki', 130000, '1HGBH41JXMN109298', '4A3AA46G1YE123568', 'EFG9012'),
('Blanco', 'Suzuki', 140000, '1HGBH41JXMN109299', '4A3AA46G1YE123569', 'GHI3456'),
('Gris', 'Ducati', 150000, '1HGBH41JXMN109300', '4A3AA46G1YE123570', 'JKL7890'),
('Verde', 'BMW', 160000, '1HGBH41JXMN109301', '4A3AA46G1YE123571', 'MNO1234'),
('Amarillo', 'Aprilia', 170000, '1HGBH41JXMN109302', '4A3AA46G1YE123572', 'PQR5678'),
('Naranja', 'Triumph', 180000, '1HGBH41JXMN109303', '4A3AA46G1YE123573', 'STU9012'),
('Violeta', 'Harley-Davidson', 190000, '1HGBH41JXMN109304', '4A3AA46G1YE123574', 'VWX3456'),
('Marrón', 'KTM', 200000, '1HGBH41JXMN109305', '4A3AA46G1YE123575', 'YZA7890'),
('Rojo', 'Yamaha', 210000, '1HGBH41JXMN109306', '4A3AA46G1YE123576', 'BCD1234'),
('Azul', 'Honda', 220000, '1HGBH41JXMN109307', '4A3AA46G1YE123577', 'CDE5678'),
('Negro', 'Kawasaki', 230000, '1HGBH41JXMN109308', '4A3AA46G1YE123578', 'EFG9012'),
('Blanco', 'Suzuki', 240000, '1HGBH41JXMN109309', '4A3AA46G1YE123579', 'GHI3456'),
('Gris', 'Ducati', 250000, '1HGBH41JXMN109310', '4A3AA46G1YE123580', 'JKL7890'),
('Verde', 'BMW', 260000, '1HGBH41JXMN109311', '4A3AA46G1YE123581', 'MNO1234'),
('Amarillo', 'Aprilia', 270000, '1HGBH41JXMN109312', '4A3AA46G1YE123582', 'PQR5678'),
('Naranja', 'Triumph', 280000, '1HGBH41JXMN109313', '4A3AA46G1YE123583', 'STU9012'),
('Violeta', 'Harley-Davidson', 290000, '1HGBH41JXMN109314', '4A3AA46G1YE123584', 'VWX3456'),
('Marrón', 'KTM', 300000, '1HGBH41JXMN109315', '4A3AA46G1YE123585', 'YZA7890'),
('Rojo', 'Yamaha', 310000, '1HGBH41JXMN109316', '4A3AA46G1YE123586', 'BCD1234'),
('Azul', 'Honda', 320000, '1HGBH41JXMN109317', '4A3AA46G1YE123587', 'CDE5678'),
('Negro', 'Kawasaki', 330000, '1HGBH41JXMN109318', '4A3AA46G1YE123588', 'EFG9012'),
('Blanco', 'Suzuki', 340000, '1HGBH41JXMN109319', '4A3AA46G1YE123589', 'GHI3456'),
('Gris', 'Ducati', 350000, '1HGBH41JXMN109320', '4A3AA46G1YE123590', 'JKL7890'),
('Verde', 'BMW', 360000, '1HGBH41JXMN109321', '4A3AA46G1YE123591', 'MNO1234'),
('Amarillo', 'Aprilia', 370000, '1HGBH41JXMN109322', '4A3AA46G1YE123592', 'PQR5678'),
('Naranja', 'Triumph', 380000, '1HGBH41JXMN109323', '4A3AA46G1YE123593', 'STU9012'),
('Violeta', 'Harley-Davidson', 390000, '1HGBH41JXMN109324', '4A3AA46G1YE123594', 'VWX3456'),
('Marrón', 'KTM', 400000, '1HGBH41JXMN109325', '4A3AA46G1YE123595', 'YZA7890'),
('Rojo', 'Yamaha', 410000, '1HGBH41JXMN109326', '4A3AA46G1YE123596', 'BCD1234'),
('Azul', 'Honda', 420000, '1HGBH41JXMN109327', '4A3AA46G1YE123597', 'CDE5678'),
('Negro', 'Kawasaki', 430000, '1HGBH41JXMN109328', '4A3AA46G1YE123598', 'EFG9012'),
('Blanco', 'Suzuki', 440000, '1HGBH41JXMN109329', '4A3AA46G1YE123599', 'GHI3456'),
('Gris', 'Ducati', 450000, '1HGBH41JXMN109330', '4A3AA46G1YE123600', 'JKL7890'),
('Verde', 'BMW', 460000, '1HGBH41JXMN109331', '4A3AA46G1YE123601', 'MNO1234'),
('Amarillo', 'Aprilia', 470000, '1HGBH41JXMN109332', '4A3AA46G1YE123602', 'PQR5678'),
('Naranja', 'Triumph', 480000, '1HGBH41JXMN109333', '4A3AA46G1YE123603', 'STU9012'),
('Violeta', 'Harley-Davidson', 490000, '1HGBH41JXMN109334', '4A3AA46G1YE123604', 'VWX3456'),
('Marrón', 'KTM', 500000, '1HGBH41JXMN109335', '4A3AA46G1YE123605', 'YZA7890'),
('Negro', 'Kawasaki', 130000, '1HGBH41JXMN109298', '4A3AA46G1YE123568', 'EFG9012'),
('Blanco', 'Suzuki', 140000, '1HGBH41JXMN109299', '4A3AA46G1YE123569', 'GHI3456'),
('Gris', 'Ducati', 150000, '1HGBH41JXMN109300', '4A3AA46G1YE123570', 'JKL7890'),
('Verde', 'BMW', 160000, '1HGBH41JXMN109301', '4A3AA46G1YE123571', 'MNO1234'),
('Amarillo', 'Aprilia', 170000, '1HGBH41JXMN109302', '4A3AA46G1YE123572', 'PQR5678');
-- Insertar 50 autos de prueba
INSERT INTO `auto_de_prueba` (`cantidad_de_puerta`, `cantidad_de_rueda`, `id_vehiculo`)
VALUES
(4, 4, 1),
(4, 4, 2),
(4, 4, 3),
(3, 4, 4),
(4, 4, 5),
(4, 4, 6),
(5, 4, 7),
(4, 4, 8),
(4, 4, 9),
(4, 4, 10),
(5, 4, 11),
(5, 4, 12),
(4, 4, 13),
(4, 4, 14),
(3, 4, 15),
(4, 4, 16),
(4, 4, 17),
(4, 4, 18),
(4, 4, 19),
(4, 4, 20),
(3, 4, 21),
(4, 4, 22),
(4, 4, 23),
(4, 4, 24),
(4, 4, 25),
(4, 4, 26),
(4, 4, 27),
(4, 4, 28),
(5, 4, 29),
(4, 4, 30),
(5, 4, 31),
(4, 4, 32),
(4, 4, 33),
(4, 4, 34),
(4, 4, 35),
(4, 4, 36),
(4, 4, 37),
(5, 4, 38),
(4, 4, 39),
(4, 4, 40),
(4, 4, 41),
(5, 4, 42),
(5, 4, 43),
(5, 4, 44),
(4, 4, 45),
(4, 4, 46),
(4, 4, 47),
(5, 4, 48),
(5, 4, 49),
(4, 4, 50);
-- Insertar 50 motos de prueba
INSERT INTO `moto_de_prueba` (`cilindrada`, `cantidad_de_rueda`, `id_vehiculo`)
VALUES
('125cc', 2, 51),  -- Honda CBR125R
('150cc', 2, 52),  -- Yamaha YZF-R15
('200cc', 2, 53),  -- KTM Duke 200
('250cc', 2, 54),  -- Honda CBR250R
('300cc', 2, 55),  -- Kawasaki Ninja 300
('350cc', 2, 56),  -- KTM RC 390
('400cc', 2, 57),  -- Yamaha YZF-R3
('500cc', 2, 58),  -- Kawasaki Z500
('600cc', 2, 59),  -- Honda CBR600RR
('650cc', 2, 60),  -- Kawasaki Ninja 650
('700cc', 2, 61),  -- Yamaha MT-07
('750cc', 2, 62),  -- Suzuki GSX750
('800cc', 2, 63),  -- BMW F800R
('850cc', 2, 64),  -- BMW F850GS
('900cc', 2, 65),  -- Honda CB900F
('1000cc', 2, 66), -- Suzuki GSX-R1000
('1100cc', 2, 67), -- Honda CB1100
('1200cc', 2, 68), -- Harley-Davidson Sportster 1200
('1250cc', 2, 69), -- BMW R1250GS
('125cc', 2, 70),  -- Honda Grom
('150cc', 2, 71),  -- Bajaj Pulsar 150
('200cc', 2, 72),  -- TVS Apache RTR 200
('250cc', 2, 73),  -- KTM 250 Duke
('300cc', 2, 74),  -- Honda CB300R
('350cc', 2, 75),  -- Royal Enfield Meteor 350
('400cc', 2, 76),  -- Kawasaki Ninja 400
('500cc', 2, 77),  -- Royal Enfield Interceptor 650
('600cc', 2, 78),  -- Yamaha YZF-R6
('650cc', 2, 79),  -- Suzuki V-Strom 650
('700cc', 2, 80),  -- Honda NC750X
('750cc', 2, 81),  -- Triumph Tiger 750
('800cc', 2, 82),  -- Ducati Monster 821
('850cc', 2, 83),  -- Honda CB850
('900cc', 2, 84),  -- Yamaha XSR900
('1000cc', 2, 85), -- Kawasaki Z1000
('1100cc', 2, 86), -- Suzuki Bandit 1200
('1200cc', 2, 87), -- Harley-Davidson Roadster 1200
('1250cc', 2, 88), -- Indian FTR 1200
('125cc', 2, 89),  -- Suzuki GSX125
('150cc', 2, 90),  -- Honda CB150R
('200cc', 2, 91),  -- Benelli 202S
('250cc', 2, 92),  -- Hyosung GT250R
('300cc', 2, 93),  -- CF Moto 300NK
('350cc', 2, 94),  -- Royal Enfield Classic 350
('400cc', 2, 95),  -- KTM 390 Duke
('500cc', 2, 96),  -- Honda Rebel 500
('600cc', 2, 97),  -- Kawasaki ZX-6R
('650cc', 2, 98),  -- BMW F650GS
('700cc', 2, 99),  -- Yamaha Ténéré 700
('750cc', 2, 100); -- Suzuki GSX750F
SELECT id_vehiculo FROM vehiculo;