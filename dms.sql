-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2016 at 06:25 PM
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

CREATE TABLE IF NOT EXISTS `aircraft` (
  `aircraftId` int(10) NOT NULL AUTO_INCREMENT,
  `airlineId` int(10) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`aircraftId`),
  KEY `FKAircraft800507` (`airlineId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `aircraft`
--

INSERT INTO `aircraft` (`aircraftId`, `airlineId`, `code`) VALUES
(1, 1, 'A350'),
(2, 2, 'B4567');

-- --------------------------------------------------------

--
-- Table structure for table `airline`
--

CREATE TABLE IF NOT EXISTS `airline` (
  `airlineId` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `gateId` char(2) DEFAULT NULL,
  PRIMARY KEY (`airlineId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `airline`
--

INSERT INTO `airline` (`airlineId`, `name`, `gateId`) VALUES
(1, 'Hong Kong Airline', 'A1'),
(2, 'China Airline', 'B3');

-- --------------------------------------------------------

--
-- Table structure for table `departureslot`
--

CREATE TABLE IF NOT EXISTS `departureslot` (
  `slotId` int(10) NOT NULL AUTO_INCREMENT,
  `scheduledPushbackTime` timestamp NULL DEFAULT NULL,
  `requiredPushbackTime` timestamp NULL DEFAULT NULL,
  `actualPushbackTime` timestamp NULL DEFAULT NULL,
  `gateId` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `aircraftId` int(10) DEFAULT NULL,
  PRIMARY KEY (`slotId`),
  KEY `FKDeparture 381542` (`aircraftId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=82 ;

--
-- Dumping data for table `departureslot`
--

INSERT INTO `departureslot` (`slotId`, `scheduledPushbackTime`, `requiredPushbackTime`, `actualPushbackTime`, `gateId`, `status`, `aircraftId`) VALUES
(1, '2016-12-02 16:01:30', NULL, NULL, 'A1', 'cancel', 1),
(2, '2016-12-02 16:03:00', NULL, NULL, 'B3', 'pending', 2),
(3, '2016-12-02 16:04:30', '2016-11-26 16:04:32', '2016-11-26 16:04:41', 'A1', 'departure', 1),
(4, '2016-12-02 16:06:00', NULL, NULL, 'B3', 'pending', 2),
(5, '2016-12-02 16:07:30', NULL, NULL, NULL, 'available', NULL),
(6, '2016-12-02 16:09:00', NULL, NULL, NULL, 'available', NULL),
(7, '2016-12-02 16:10:30', NULL, NULL, NULL, 'available', NULL),
(8, '2016-12-02 16:12:00', NULL, NULL, NULL, 'available', NULL),
(9, '2016-12-02 16:13:30', NULL, NULL, 'B3', 'pending', 2),
(10, '2016-12-02 16:15:00', NULL, NULL, NULL, 'available', NULL),
(11, '2016-12-02 16:16:30', NULL, NULL, NULL, 'available', NULL),
(12, '2016-12-02 16:18:00', NULL, NULL, NULL, 'available', NULL),
(13, '2016-12-02 16:19:30', NULL, NULL, NULL, 'available', NULL),
(14, '2016-12-02 16:21:00', NULL, NULL, NULL, 'available', NULL),
(15, '2016-12-02 16:22:30', NULL, NULL, NULL, 'available', NULL),
(16, '2016-12-02 16:24:00', NULL, NULL, NULL, 'available', NULL),
(17, '2016-12-02 16:25:30', NULL, NULL, 'B3', 'pending', 2),
(18, '2016-12-02 16:27:00', NULL, NULL, NULL, 'available', NULL),
(19, '2016-12-02 16:28:30', NULL, NULL, NULL, 'available', NULL),
(20, '2016-12-02 16:30:00', NULL, NULL, 'A1', 'pending', 1),
(21, '2016-12-02 16:31:30', NULL, NULL, NULL, 'available', NULL),
(22, '2016-12-02 16:33:00', NULL, NULL, NULL, 'available', NULL),
(23, '2016-12-02 16:34:30', NULL, NULL, NULL, 'available', NULL),
(24, '2016-12-02 16:36:00', NULL, NULL, NULL, 'available', NULL),
(25, '2016-12-02 16:37:30', NULL, NULL, NULL, 'available', NULL),
(26, '2016-12-02 16:39:00', NULL, NULL, NULL, 'available', NULL),
(27, '2016-12-02 16:40:30', NULL, NULL, NULL, 'available', NULL),
(28, '2016-12-02 16:42:00', NULL, NULL, NULL, 'available', NULL),
(29, '2016-12-02 16:43:30', NULL, NULL, NULL, 'available', NULL),
(30, '2016-12-02 16:45:00', NULL, NULL, NULL, 'available', NULL),
(31, '2016-12-02 16:46:30', NULL, NULL, NULL, 'available', NULL),
(32, '2016-12-02 16:48:00', NULL, NULL, NULL, 'available', NULL),
(33, '2016-12-02 16:49:30', NULL, NULL, NULL, 'available', NULL),
(34, '2016-12-02 16:51:00', NULL, NULL, NULL, 'available', NULL),
(35, '2016-12-02 16:52:30', NULL, NULL, NULL, 'available', NULL),
(36, '2016-12-02 16:54:00', NULL, NULL, NULL, 'available', NULL),
(37, '2016-12-02 16:55:30', NULL, NULL, NULL, 'available', NULL),
(38, '2016-12-02 16:57:00', NULL, NULL, NULL, 'available', NULL),
(39, '2016-12-02 16:58:30', NULL, NULL, NULL, 'available', NULL),
(40, '2016-12-02 17:00:00', NULL, NULL, NULL, 'available', NULL),
(41, '2016-12-02 17:01:30', NULL, NULL, NULL, 'available', NULL),
(42, '2016-12-02 17:03:00', NULL, NULL, NULL, 'available', NULL),
(43, '2016-12-02 17:04:30', NULL, NULL, 'A1', 'pending', 1),
(44, '2016-12-02 17:06:00', NULL, NULL, NULL, 'available', NULL),
(45, '2016-12-02 17:07:30', NULL, NULL, NULL, 'available', NULL),
(46, '2016-12-02 17:09:00', NULL, NULL, NULL, 'available', NULL),
(47, '2016-12-02 17:10:30', NULL, NULL, NULL, 'available', NULL),
(48, '2016-12-02 17:12:00', NULL, NULL, NULL, 'available', NULL),
(49, '2016-12-02 17:13:30', NULL, NULL, NULL, 'available', NULL),
(50, '2016-12-02 17:15:00', NULL, NULL, NULL, 'available', NULL),
(51, '2016-12-02 17:16:30', NULL, NULL, NULL, 'available', NULL),
(52, '2016-12-02 17:18:00', NULL, NULL, NULL, 'available', NULL),
(53, '2016-12-02 17:19:30', NULL, NULL, NULL, 'available', NULL),
(54, '2016-12-02 17:21:00', NULL, NULL, NULL, 'available', NULL),
(55, '2016-12-02 17:22:30', NULL, NULL, NULL, 'available', NULL),
(56, '2016-12-02 17:24:00', NULL, NULL, NULL, 'available', NULL),
(57, '2016-12-02 17:25:30', NULL, NULL, NULL, 'available', NULL),
(58, '2016-12-02 17:27:00', NULL, NULL, 'B3', 'pending', 2),
(59, '2016-12-02 17:28:30', NULL, NULL, NULL, 'available', NULL),
(60, '2016-12-02 17:30:00', NULL, NULL, NULL, 'available', NULL),
(61, '2016-12-02 17:31:30', NULL, NULL, NULL, 'available', NULL),
(62, '2016-12-02 17:33:00', NULL, NULL, NULL, 'available', NULL),
(63, '2016-12-02 17:34:30', NULL, NULL, NULL, 'available', NULL),
(64, '2016-12-02 17:36:00', NULL, NULL, NULL, 'available', NULL),
(65, '2016-12-02 17:37:30', NULL, NULL, NULL, 'available', NULL),
(66, '2016-12-02 17:39:00', NULL, NULL, NULL, 'available', NULL),
(67, '2016-12-02 17:40:30', NULL, NULL, NULL, 'available', NULL),
(68, '2016-12-02 17:42:00', NULL, NULL, NULL, 'available', NULL),
(69, '2016-12-02 17:43:30', NULL, NULL, NULL, 'available', NULL),
(70, '2016-12-02 17:45:00', NULL, NULL, NULL, 'available', NULL),
(71, '2016-12-02 17:46:30', NULL, NULL, NULL, 'available', NULL),
(72, '2016-12-02 17:48:00', NULL, NULL, NULL, 'available', NULL),
(73, '2016-12-02 17:49:30', NULL, NULL, NULL, 'available', NULL),
(74, '2016-12-02 17:51:00', NULL, NULL, NULL, 'available', NULL),
(75, '2016-12-02 17:52:30', NULL, NULL, NULL, 'available', NULL),
(76, '2016-12-02 17:54:00', NULL, NULL, NULL, 'available', NULL),
(77, '2016-12-02 17:55:30', NULL, NULL, NULL, 'available', NULL),
(78, '2016-12-02 17:57:00', NULL, NULL, NULL, 'available', NULL),
(79, '2016-12-02 17:58:30', NULL, NULL, NULL, 'available', NULL),
(80, '2016-12-02 18:00:00', NULL, NULL, NULL, 'available', NULL),
(81, '2016-12-02 16:03:00', NULL, NULL, 'A1', 'cancel', 1);

-- --------------------------------------------------------

--
-- Table structure for table `exchangeapplication`
--

CREATE TABLE IF NOT EXISTS `exchangeapplication` (
  `exchangeId` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(1) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `userId` int(10) NOT NULL,
  `fromDsId` int(10) NOT NULL,
  `toDsId` int(10) NOT NULL,
  PRIMARY KEY (`exchangeId`),
  KEY `FKExchange A184445` (`userId`),
  KEY `FKExchange A264376` (`fromDsId`),
  KEY `FKExchange A308785` (`toDsId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `exchangeapplication`
--

INSERT INTO `exchangeapplication` (`exchangeId`, `type`, `status`, `userId`, `fromDsId`, `toDsId`) VALUES
(8, 'd', 'accepted', 4, 18, 19),
(9, 'd', 'accepted', 4, 19, 20),
(10, 'e', 'cancel', 4, 9, 5),
(11, 'd', 'cancel', 4, 2, 5),
(12, 'd', 'pending', 4, 4, 5),
(13, 'e', 'pending', 5, 43, 5),
(14, NULL, 'rejected', 5, 58, 59),
(15, 'e', 'accepted', 5, 58, 2),
(16, 'e', 'accepted', 5, 58, 20),
(17, 'd', 'accepted', 5, 2, 58),
(18, 'e', 'accepted', 5, 58, 20);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `loginId`, `password`, `type`, `airlineId`, `status`, `lastLogin`) VALUES
(1, 'rampcontrol', 'rampcontrol', 'r', NULL, 'Active', '2016-11-20 16:00:00'),
(3, 'airline', 'airline', 'a', 1, 'Active', NULL),
(4, 'stationmanager', 'stationmanager', 's', 2, 'Active', '2016-11-30 17:03:58'),
(5, 'stationmanager1', 'stationmanager1', 's', 1, 'Active', '2016-11-30 16:20:27');

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
  ADD CONSTRAINT `FKExchange A184445` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  ADD CONSTRAINT `FKExchange A264376` FOREIGN KEY (`fromDsId`) REFERENCES `departureslot` (`slotId`),
  ADD CONSTRAINT `FKExchange A308785` FOREIGN KEY (`toDsId`) REFERENCES `departureslot` (`slotId`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`airlineId`) REFERENCES `airline` (`airlineId`) ON DELETE SET NULL;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
