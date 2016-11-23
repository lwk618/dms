-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2016 at 04:05 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dms`
--
CREATE DATABASE `dms` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dms`;
-- --------------------------------------------------------

--
-- Table structure for table `aircraft`
--

DROP TABLE IF EXISTS `aircraft`;
CREATE TABLE IF NOT EXISTS `aircraft` (
  `aircraftId` int(10) NOT NULL,
  `airlineId` int(10) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`aircraftId`),
  KEY `FKAircraft800507` (`airlineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `aircraft`:
--   `airlineId`
--       `airline` -> `airlineId`
--

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

DROP TABLE IF EXISTS `airline`;
CREATE TABLE IF NOT EXISTS `airline` (
  `airlineId` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gateId` char(2) DEFAULT NULL,
  PRIMARY KEY (`airlineId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `departureslot`
--

DROP TABLE IF EXISTS `departureslot`;
CREATE TABLE IF NOT EXISTS `departureslot` (
  `slotId` int(10) NOT NULL AUTO_INCREMENT,
  `scheduledPushbackTime` timestamp NULL DEFAULT NULL,
  `requiredPushbackTime` timestamp NULL DEFAULT NULL,
  `actualPushbackTime` timestamp NULL DEFAULT NULL,
  `gateId` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `aircraftId` int(10) NOT NULL,
  PRIMARY KEY (`slotId`),
  KEY `FKDeparture 381542` (`aircraftId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE `departureslot`:
--   `aircraftId`
--       `aircraft` -> `aircraftId`
--

-- --------------------------------------------------------

--
-- Table structure for table `exchangeapplication`
--

DROP TABLE IF EXISTS `exchangeapplication`;
CREATE TABLE IF NOT EXISTS `exchangeapplication` (
  `exchangeId` varchar(10) NOT NULL,
  `type` varchar(1) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `userId` int(10) NOT NULL,
  `fromDsId` int(10) NOT NULL,
  `toDsId` int(10) NOT NULL,
  PRIMARY KEY (`exchangeId`),
  KEY `FKExchange A184445` (`userId`),
  KEY `FKExchange A264376` (`fromDsId`),
  KEY `FKExchange A308785` (`toDsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE `exchangeapplication`:
--   `toDsId`
--       `departureslot` -> `slotId`
--   `userId`
--       `user` -> `userId`
--   `fromDsId`
--       `departureslot` -> `slotId`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(20) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `airlineId` int(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `lastLogin` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FKUser641891` (`airlineId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- RELATIONS FOR TABLE `user`:
--   `airlineId`
--       `airline` -> `airlineId`
--

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `loginId`, `password`, `type`, `airlineId`, `status`, `lastLogin`) VALUES
(1, 'rampcontrol', 'rampcontrol', 'r', NULL, 'Active', '2016-11-20 16:00:00');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `aircraft`
--
ALTER TABLE `aircraft`
  ADD CONSTRAINT `FKAircraft800507` FOREIGN KEY (`airlineId`) REFERENCES `airline` (`airlineId`);

--
-- Constraints for table `departureslot`
--
ALTER TABLE `departureslot`
  ADD CONSTRAINT `FKDeparture 381542` FOREIGN KEY (`aircraftId`) REFERENCES `aircraft` (`aircraftId`);

--
-- Constraints for table `exchangeapplication`
--
ALTER TABLE `exchangeapplication`
  ADD CONSTRAINT `FKExchange A308785` FOREIGN KEY (`toDsId`) REFERENCES `departureslot` (`slotId`),
  ADD CONSTRAINT `FKExchange A184445` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FKExchange A264376` FOREIGN KEY (`fromDsId`) REFERENCES `departureslot` (`slotId`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKUser641891` FOREIGN KEY (`airlineId`) REFERENCES `airline` (`airlineId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
