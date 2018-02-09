-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: crmCarRepairShop
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

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
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `idCustomer` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `surname` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT 'this one',
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`idCustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'Michal','Korno','1966-05-23'),(10,'Olga','Bujak','1965-06-16'),(11,'Piotr','Malinowski','1990-07-28'),(12,'Teresa','Zdobylak','1987-09-23'),(14,'Kamil','Kowalski','1956-12-24'),(31,'Karolina','Kot','2003-03-02'),(32,'Katarzyna','Pawlik',NULL),(33,'Weronika','Malinowska',NULL),(34,'Gosia','Wata',NULL),(35,'Ziemowit','Lumpeks',NULL);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Employee` (
  `idEmployee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `phone` char(11) NOT NULL,
  `note` varchar(45) DEFAULT NULL,
  `manHour` decimal(6,2) NOT NULL,
  `street` varchar(255) NOT NULL,
  `postalCode` char(6) NOT NULL,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`idEmployee`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'Mirek','Latynos','987-693-523','mechanik',40.00,'Lokalna 34/2','36-598','Warszawa'),(3,'Kuba','Wodziak','568-425-859','mechanik',40.50,'Kwiatowa 23/12','36-254','Warszawa'),(4,'Wiesław','Wołoszański','721-256-369','mechanik',50.60,'Kolejowa 100/12','20-025','Warszawa'),(5,'Krzysztof','Blaszkiewicz','421-256-369','mechanik',45.00,'Basztowa 23/7666','30-632','Warszawa'),(8,'Zenobiusz','Misiak','569-235-695','szef',100.00,'Kocia 34/12','89-569','Warszawa'),(9,'Iga','Naczelna','569-569-963','mechanik',70.00,'Dzbanek 34/12','95-896','Warszawa'),(10,'Igor','Malanowski','236-236-589','mechanik',80.00,'Tkacza 45','45-896','Warszawa');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Order` (
  `idOrder` int(11) NOT NULL AUTO_INCREMENT,
  `acceptanceForRepairDate` date NOT NULL,
  `plannedRepairDate` date DEFAULT NULL,
  `startRepairDate` date DEFAULT NULL,
  `servingEmployeeId` int(11) NOT NULL,
  `problemDescription` mediumtext,
  `repairDescription` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `repairedVehicleId` int(11) NOT NULL,
  `costForCustomer` decimal(8,2) DEFAULT NULL,
  `costOfParts` decimal(8,2) DEFAULT NULL,
  `manHourCost` decimal(6,2) DEFAULT NULL,
  `manHourQuantity` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`idOrder`),
  KEY `fk_Order_Employee_idx` (`servingEmployeeId`),
  KEY `fk_Order_Vehicle_idx` (`repairedVehicleId`),
  CONSTRAINT `fk_Order_Employee` FOREIGN KEY (`servingEmployeeId`) REFERENCES `Employee` (`idEmployee`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Order_Vehicle` FOREIGN KEY (`repairedVehicleId`) REFERENCES `Vehicle` (`idVehicle`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
INSERT INTO `Order` VALUES (34,'2016-01-01',NULL,'2016-10-01',1,'Nie działają hamulce',NULL,'Przyjęty',1,NULL,NULL,NULL,NULL),(35,'2016-02-01',NULL,'2016-11-01',3,'Problem z silnikiem',NULL,'Przyjęty',27,NULL,NULL,NULL,NULL),(36,'2016-03-01',NULL,'2016-12-01',4,'Nie działa pedał gazu',NULL,'Przyjęty',28,NULL,NULL,NULL,NULL),(37,'2016-04-01',NULL,'2017-01-01',5,'Wymiana oleju',NULL,'Zatwierdzony koszt naprawy',30,NULL,NULL,NULL,NULL),(38,'2016-05-01',NULL,'2017-02-01',8,'Nie działa klimatyzacja',NULL,'Zatwierdzony koszt naprawy',32,NULL,NULL,NULL,NULL),(39,'2016-06-01',NULL,'2017-03-01',9,'Zepsuta chłodnica',NULL,'Zatwierdzony koszt naprawy',33,NULL,NULL,NULL,NULL),(40,'2016-07-01',NULL,'2017-04-01',10,'Wymiana opon',NULL,'W naprawie',34,NULL,NULL,NULL,NULL),(41,'2016-08-01',NULL,'2017-05-01',1,'Nie chce odpalić',NULL,'W naprawie',35,NULL,NULL,NULL,NULL),(42,'2016-09-01',NULL,'2017-06-01',3,'Po przekroczeniu pewnych obrotów zwalnia',NULL,'W naprawie',36,NULL,NULL,NULL,NULL),(43,'2016-10-01',NULL,'2017-07-01',4,'Nie działają hamulce',NULL,'W naprawie',37,NULL,NULL,NULL,NULL),(44,'2016-11-01',NULL,'2017-08-01',5,'Wymiana oleju',NULL,'Gotowy do odbioru',38,NULL,NULL,NULL,NULL),(45,'2016-12-01',NULL,'2017-09-01',8,'Probelm z silnikiem',NULL,'Gotowy do odbioru',39,NULL,NULL,NULL,NULL),(46,'2017-01-01',NULL,'2017-10-01',9,'Nie można odpalić',NULL,'Gotowy do odbioru',40,NULL,NULL,NULL,NULL),(47,'2017-02-01',NULL,'2017-11-01',10,'Dziwnie chodzi - przegląd',NULL,'Rezygnacja',41,NULL,NULL,NULL,NULL),(48,'2017-03-01',NULL,'2017-12-01',1,'Nie chce odpalić',NULL,'Rezygnacja',42,NULL,NULL,NULL,NULL),(49,'2017-04-01',NULL,'2018-01-01',3,'Zepsuta chłodnica',NULL,'W naprawie',1,NULL,NULL,NULL,NULL),(50,'2017-05-01',NULL,'2018-01-01',3,'Coś się zepsuło.',NULL,'W naprawie',27,NULL,NULL,NULL,NULL),(51,'2018-01-02',NULL,'2018-01-03',10,'Wymiana opon',NULL,'W naprawie',40,0.00,0.00,0.00,0.00);
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Vehicle`
--

DROP TABLE IF EXISTS `Vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Vehicle` (
  `idVehicle` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) NOT NULL,
  `mark` varchar(255) NOT NULL,
  `productionYear` int(4) NOT NULL,
  `registrationNr` char(7) NOT NULL,
  `nextTechnicalReviewDate` date DEFAULT NULL,
  `customerId` int(11) NOT NULL,
  PRIMARY KEY (`idVehicle`),
  KEY `fk_Vehicle_1_idx` (`customerId`),
  CONSTRAINT `fk_Vehicle_Customer` FOREIGN KEY (`customerId`) REFERENCES `Customer` (`idCustomer`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Vehicle`
--

LOCK TABLES `Vehicle` WRITE;
/*!40000 ALTER TABLE `Vehicle` DISABLE KEYS */;
INSERT INTO `Vehicle` VALUES (1,'SERIA 8 (E31)','BMW',2015,'RT59876','2018-06-02',1),(27,'DOUBLE SIX','JAGUAR',2016,'RWA5698','2018-11-19',12),(28,'IBIZA III','SEAT',2015,'IKJ6578','2018-05-06',11),(30,'INKA (6K9)','SEAT',2013,'RT65874','2018-02-03',14),(32,'GHIBLI','MASERATI',2017,'UT52369','2018-08-02',10),(33,'GRANCABRIO','MASERATI',2016,'WER5237','2018-09-02',11),(34,'GRANTURISMO','MASERATI',2012,'WE12365','2018-10-02',12),(35,'LEVANTE','MASERATI',2013,'TG23156','2018-11-02',14),(36,'QUATROPORTE','MASERATI',2014,'PO12345','2018-12-02',31),(37,'JEEP CJ-2A','JEEP',2015,'QW45697','2018-08-12',32),(38,'WILLYS WAGON','JEEP',2016,'DEATH66','2018-09-20',33),(39,'JEEP CJ-3A','JEEP',2017,'EW12369','2018-10-20',34),(40,'JEEP GLADIATOR','JEEP',2013,'QW23456','2018-09-20',31),(41,'JEEP CHEROKEE (XJ)','JEEP',2014,'VBDT678','2018-09-20',32),(42,'JEEP WRANGLER','JEEP',2017,'PL67TY5','2018-09-20',33);
/*!40000 ALTER TABLE `Vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-08 12:27:35
