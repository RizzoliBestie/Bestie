-- --------------------------------------------------------
-- Host:                         localhost
-- Versione server:              8.0.27 - MySQL Community Server - GPL
-- S.O. server:                  Linux
-- HeidiSQL Versione:            11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database Bestie
CREATE DATABASE IF NOT EXISTS `Bestie` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `Bestie`;

-- Dump della struttura di tabella Bestie.Pet
CREATE TABLE IF NOT EXISTS `Pet` (
  `id_pet` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_race` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_pet`),
  KEY `id_user_PET` (`id_user`),
  KEY `id_race_PET` (`id_race`),
  CONSTRAINT `id_race_PET` FOREIGN KEY (`id_race`) REFERENCES `Race` (`id_race`),
  CONSTRAINT `id_user_PET` FOREIGN KEY (`id_user`) REFERENCES `User` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.Pet: ~0 rows (circa)
/*!40000 ALTER TABLE `Pet` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pet` ENABLE KEYS */;

-- Dump della struttura di tabella Bestie.Race
CREATE TABLE IF NOT EXISTS `Race` (
  `id_race` int NOT NULL AUTO_INCREMENT,
  `id_specie` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `information` varchar(50) NOT NULL,
  `size` varchar(50) NOT NULL,
  `fur_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id_race`),
  KEY `id_specie_RACE` (`id_specie`),
  CONSTRAINT `id_specie_RACE` FOREIGN KEY (`id_specie`) REFERENCES `Specie` (`id_specie`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.Race: ~0 rows (circa)
/*!40000 ALTER TABLE `Race` DISABLE KEYS */;
/*!40000 ALTER TABLE `Race` ENABLE KEYS */;

-- Dump della struttura di tabella Bestie.Specie
CREATE TABLE IF NOT EXISTS `Specie` (
  `id_specie` int NOT NULL AUTO_INCREMENT,
  `common_name` varchar(50) NOT NULL,
  `scientific_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_specie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.Specie: ~0 rows (circa)
/*!40000 ALTER TABLE `Specie` DISABLE KEYS */;
/*!40000 ALTER TABLE `Specie` ENABLE KEYS */;

-- Dump della struttura di tabella Bestie.User
CREATE TABLE IF NOT EXISTS `User` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.User: ~2 rows (circa)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
REPLACE INTO `User` (`id_user`, `username`, `email`, `password`, `phone_number`) VALUES
	(1, 'mike73', 'mike73@', '123456', '1234567890'),
	(2, 'mike73', 'mike@', '098765', '1234567890'),
	(3, 'luca', 'luca@', 'volpe!', '1234567890'),
	(4, 'lorenzo', 'lorenzo@', 'giuseppesimone69', '3330987658');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
