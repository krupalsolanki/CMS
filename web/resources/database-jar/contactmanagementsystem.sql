-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 03, 2013 at 05:03 AM
-- Server version: 5.1.68
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `contactmanagementsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `contactlist`
--

CREATE TABLE IF NOT EXISTS `contactlist` (
  `contactListId` int(11) NOT NULL AUTO_INCREMENT,
  `myId` int(11) DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  PRIMARY KEY (`contactListId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `contactlist`
--


-- --------------------------------------------------------

--
-- Table structure for table `contactrelation`
--

CREATE TABLE IF NOT EXISTS `contactrelation` (
  `contactRelationId` int(11) NOT NULL AUTO_INCREMENT,
  `contactlistId` int(11) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `notes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`contactRelationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `contactrelation`
--


-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE IF NOT EXISTS `contacts` (
  `contactId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) DEFAULT NULL,
  `email` varchar(78) DEFAULT NULL,
  `phoneNo` varchar(20) NOT NULL,
  `companyName` varchar(100) DEFAULT NULL,
  `companyLoc` varchar(50) DEFAULT NULL,
  `designation` varchar(120) DEFAULT NULL,
  `linkedInUrl` varchar(256) NOT NULL,
  `notes` char(255) NOT NULL,
  `addedBy` char(30) DEFAULT NULL,
  PRIMARY KEY (`contactId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=114 ;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`contactId`, `firstName`, `lastName`, `email`, `phoneNo`, `companyName`, `companyLoc`, `designation`, `linkedInUrl`, `notes`, `addedBy`) VALUES
(109, 'Walter', 'Coutinho', 'waltery2w@gmail.com', '(091)-9860633725', 'compassites', 'Pune', 'CEO', '', '', 'Jay Patel'),
(110, 'Satyam', 'Singh', 'satyam.mgs@gmail.com', '(091)-9860633725', 'compassites', 'Pune', 'ID card Collector', '', '', 'Jay Patel'),
(111, 'Krupal', 'Solanki', 'krupalsolanki@live.com', '(123)-1231231223', 'compassites', 'Pune', 'CEO', '', '', 'Aapka Pyaara Satyam'),
(112, 'Jay', 'Patel', 'darpan_jay@yahoo.in', '(911)-7521346543', 'compassites', 'Pune', 'ID card Collector', '', '', 'Krupal'),
(113, 'ruchika', 'sharma', 'ruchika.geca@gmail.com', '(091)-2547896314', 'compassites', 'banglore', 'QA', 'http://in.linkedin.com/pub/jay-butani/60/926/695', 'mad girl..', 'Jay Patel');

-- --------------------------------------------------------

--
-- Table structure for table `interestbridge`
--

CREATE TABLE IF NOT EXISTS `interestbridge` (
  `interestId` int(11) DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  `IB_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IB_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `interestbridge`
--


-- --------------------------------------------------------

--
-- Table structure for table `interests`
--

CREATE TABLE IF NOT EXISTS `interests` (
  `interestId` int(11) NOT NULL AUTO_INCREMENT,
  `interestName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`interestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `interests`
--

