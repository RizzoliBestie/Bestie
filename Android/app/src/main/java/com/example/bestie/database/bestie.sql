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
  `weight` double DEFAULT NULL,
  `fur_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` tinyint DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `lastMeal` date DEFAULT NULL,
  `lastWalk` date DEFAULT NULL,
  `sterilized` tinyint NOT NULL DEFAULT '0',
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
  `information` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `size` varchar(50) NOT NULL,
  `fur_type` varchar(50) NOT NULL,
  `url` varchar(50) NOT NULL,
  PRIMARY KEY (`id_race`),
  KEY `id_specie_RACE` (`id_specie`),
  CONSTRAINT `id_specie_RACE` FOREIGN KEY (`id_specie`) REFERENCES `Specie` (`id_specie`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.Race: ~6 rows (circa)
/*!40000 ALTER TABLE `Race` DISABLE KEYS */;
REPLACE INTO `Race` (`id_race`, `id_specie`, `name`, `information`, `size`, `fur_type`, `url`) VALUES
	(1, 1, 'golden_retriver', 'not the tipical princess', 'XL', 'long', 'https://golden-retriver.it'),
	(2, 1, 'askyyyyy', 'He likes churros', 'XL', 'Long', 'https://www.asky.com'),
	(3, 2, 'european', 'he doesn\'t like run too much', 'S', 'short', 'https://www.yyy.zzz'),
	(4, 4, 'fennec', 'Big ears', 'M', 'short', 'https://www.fennec.com'),
	(5, 3, 'cricetus cricetus', 'when you sleep, he watches you', 'XXS', 'short', 'https://www.watchyou.it'),
	(6, 5, 'kunekune', 'like a dog, but bigger', 'XXL', 'medium', 'https://kunekune.big-dog.en');
/*!40000 ALTER TABLE `Race` ENABLE KEYS */;

-- Dump della struttura di tabella Bestie.Specie
CREATE TABLE IF NOT EXISTS `Specie` (
  `id_specie` int NOT NULL AUTO_INCREMENT,
  `common_name` varchar(50) NOT NULL,
  `scientific_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id_specie`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.Specie: ~5 rows (circa)
/*!40000 ALTER TABLE `Specie` DISABLE KEYS */;
REPLACE INTO `Specie` (`id_specie`, `common_name`, `scientific_name`) VALUES
	(1, 'dog', 'canis_familiaris'),
	(2, 'cat', 'felis catus'),
	(3, 'hamster', 'cricetinae'),
	(4, 'fox', 'vulpes vulpes'),
	(5, 'pig', 'sus');
/*!40000 ALTER TABLE `Specie` ENABLE KEYS */;

-- Dump della struttura di tabella Bestie.User
CREATE TABLE IF NOT EXISTS `User` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dump dei dati della tabella Bestie.User: ~8 rows (circa)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
REPLACE INTO `User` (`id_user`, `username`, `email`, `password`, `phone_number`) VALUES
	(1, 'mike73', 'mike73@', '123456', '1234567890'),
	(2, 'piero_sasso', 'piero@gmail.it', 'foggianet77', 'null'),
	(3, 'paolita', 'paolita@libero.it', 'papa0l1t4', '3337776660'),
	(4, 'lorenzo', 'lorenzo@', 'giuseppesimone69', '3330987658'),
	(5, 'ricky', 'ricky@', '101010', '0372729999'),
	(6, 'pigduck', 'pigduck@yahoo.com', '124536sffg', '0981234756'),
	(7, 'indomabile', 'indo@gmail.com', 'indoModena98', '8465288711'),
	(8, 'kaytetsu', 'kay.tetsu@gmail.com', 'bolognaTortello777', NULL);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
