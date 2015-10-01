CREATE DATABASE  IF NOT EXISTS `ajedrez` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ajedrez`;
-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ajedrez
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
INSERT INTO `jugadores` VALUES (1,'Nico','Giorda'),(2,'Leo','Peretto'),(3,'Martu','Guereta'),(4,'Facu','Alvarez');
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
INSERT INTO `partida` VALUES (1,2);
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
  `dni1` int(11) NOT NULL DEFAULT '1',
  `dni2` int(11) NOT NULL DEFAULT '2',
  PRIMARY KEY (`posY`,`posX`,`dni2`,`dni1`),
  KEY `partida_idx` (`dni1`,`dni2`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trebejos`
--

LOCK TABLES `trebejos` WRITE;
/*!40000 ALTER TABLE `trebejos` DISABLE KEYS */;
INSERT INTO `trebejos` VALUES ('T',0,0,1,1,2),('C',1,0,1,1,2),('A',2,0,1,1,2),('K',3,0,1,1,2),('Q',4,0,1,1,2),('A',5,0,1,1,2),('C',6,0,1,1,2),('T',7,0,1,1,2),('P',0,1,1,1,2),('P',1,1,1,1,2),('P',2,1,1,1,2),('P',3,1,1,1,2),('P',4,1,1,1,2),('P',5,1,1,1,2),('P',6,1,1,1,2),('P',7,1,1,1,2),('P',0,6,0,1,2),('P',1,6,0,1,2),('P',2,6,0,1,2),('P',3,6,0,1,2),('P',4,6,0,1,2),('P',5,6,0,1,2),('P',6,6,0,1,2),('P',7,6,0,1,2),('T',0,7,0,1,2),('C',1,7,0,1,2),('A',2,7,0,1,2),('Q',3,7,0,1,2),('K',4,7,0,1,2),('A',5,7,0,1,2),('C',6,7,0,1,2),('T',7,7,0,1,2);
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

-- Dump completed on 2015-10-01 19:50:05
