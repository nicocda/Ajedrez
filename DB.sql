CREATE DATABASE  IF NOT EXISTS `ajedrez` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ajedrez`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: ajedrez
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jugadores` (
  `dni` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (1,'nombre1','ape1'),(2,'nombre2','ape2'),(3,'nombre3','ape3'),(4,'nombre3','ape3'),(5,'nombre3','ape3'),(6,'nombre3','ape3'),(7,'nombre3','ape3'),(8,'nombre3','ape3'),(9,'nombre3','ape3'),(10,'nombre3','ape3'),(11,'nombre3','ape3'),(12,'nombre3','ape3'),(13,'nombre3','ape3'),(14,'nombre3','ape3'),(15,'nombre3','ape3'),(16,'nombre3','ape3'),(17,'nombre3','ape3');
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partida`
--

DROP TABLE IF EXISTS `partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partida` (
  `blanco` int(11) NOT NULL,
  `negro` int(11) NOT NULL,
  PRIMARY KEY (`blanco`,`negro`),
  KEY `blanco_idx` (`blanco`),
  KEY `negro_idx` (`negro`),
  CONSTRAINT `blanco` FOREIGN KEY (`blanco`) REFERENCES `jugadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `negro` FOREIGN KEY (`negro`) REFERENCES `jugadores` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partida`
--

LOCK TABLES `partida` WRITE;
/*!40000 ALTER TABLE `partida` DISABLE KEYS */;
/*!40000 ALTER TABLE `partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trebejos`
--

DROP TABLE IF EXISTS `trebejos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trebejos` (
  `tipo` char(1) NOT NULL,
  `posX` int(11) NOT NULL,
  `posY` int(11) NOT NULL,
  `color` tinyint(4) DEFAULT NULL,
  `dni1` int(11) DEFAULT NULL,
  `dni2` int(11) DEFAULT NULL,
  PRIMARY KEY (`posY`,`posX`),
  KEY `partida_idx` (`dni1`,`dni2`),
  CONSTRAINT `partida` FOREIGN KEY (`dni1`, `dni2`) REFERENCES `partida` (`blanco`, `negro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trebejos`
--

LOCK TABLES `trebejos` WRITE;
/*!40000 ALTER TABLE `trebejos` DISABLE KEYS */;
/*!40000 ALTER TABLE `trebejos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-08 18:41:21
