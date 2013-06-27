-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 27, 2013 at 12:07 PM
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
  `empId` int(11) DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  PRIMARY KEY (`contactListId`),
  KEY `empId` (`empId`),
  KEY `contactId` (`contactId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `contactlist`
--

INSERT INTO `contactlist` (`contactListId`, `empId`, `contactId`) VALUES
(1, 1, 3),
(2, 1, 4),
(4, 1, 6),
(5, 1, 7),
(6, 1, 8),
(7, 1, 9);

-- --------------------------------------------------------

--
-- Table structure for table `contactrelation`
--

CREATE TABLE IF NOT EXISTS `contactrelation` (
  `contactRelationId` int(11) NOT NULL AUTO_INCREMENT,
  `contactListId` int(11) DEFAULT NULL,
  `nickName` varchar(30) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `notes` varchar(300) DEFAULT NULL,
  `personalMsg` varchar(300) DEFAULT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`contactRelationId`),
  KEY `contactListId` (`contactListId`)
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
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `email` varchar(78) DEFAULT NULL,
  `phoneNo` varchar(20) DEFAULT NULL,
  `companyName` varchar(100) DEFAULT NULL,
  `companyLoc` varchar(50) DEFAULT NULL,
  `designation` varchar(120) DEFAULT NULL,
  `linkedInUrl` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`contactId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`contactId`, `firstName`, `lastName`, `email`, `phoneNo`, `companyName`, `companyLoc`, `designation`, `linkedInUrl`) VALUES
(1, 'jay', 'patel', 'darpan_jay@yahoo.in', '', 'compassites', 'mumbai', 'developer', NULL),
(2, 'disha', 'shah', 'disha.shah@live.com', '9913324541', 'compassites', 'pune', 'developer', NULL),
(3, 'satyam', 'singh', 'satyam.mgs@gmail.com', '(091)-8783764287', 'cognizant', 'pune', 'Developer', ''),
(4, 'sachin', 'khumbhojkar', 'sachin@gmail.com', '(111)-1111111111', 'cognizant', 'pune', 'BA', ''),
(6, 'walter', 'Solanki', 'waltery2w@gmail.com', '(091)-8783764287', 'cognizant', 'pune', 'CEO', ''),
(7, 'jai', 'patel', 'jai@gmail.com', '(901)-7234817230', 'cognizant', 'mumbai', 'CEO', ''),
(8, 'nagesh', 'nagraj', 'nagesh.nagraj@gmail.com', '(097)-8239469178', 'compassites', 'pune', 'BA', ''),
(9, 'Banish', 'Singh', 'invinciblemanish@gmail.com', '', 'Compassites', 'Pune', 'Navro', '');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `empId` int(11) NOT NULL AUTO_INCREMENT,
  `empEmailId` varchar(78) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `Type` int(11) NOT NULL,
  PRIMARY KEY (`empId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`empId`, `empEmailId`, `password`, `firstName`, `lastName`, `Type`) VALUES
(1, 'jay.butani@compassitesinc.com', 'jay', '', '', 0),
(3, 'disha.shah@compassitesinc.com', 'disha', '', '', 0),
(5, 'krupal.solanki@compassitesinc.com', '202cb962ac59075b964b07152d234b70', '', '', 0),
(6, 'satyam.joshi@compassitesinc.com', 'f2cdafc6b1adf94892b17f355bd9110', 'Satyam', 'Sehgal', 0);

-- --------------------------------------------------------

--
-- Table structure for table `interestbridge`
--

CREATE TABLE IF NOT EXISTS `interestbridge` (
  `IB_id` int(11) NOT NULL AUTO_INCREMENT,
  `interestId` int(11) DEFAULT NULL,
  `contactId` int(11) DEFAULT NULL,
  PRIMARY KEY (`IB_id`),
  KEY `interestId` (`interestId`),
  KEY `contactId` (`contactId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `interestbridge`
--

INSERT INTO `interestbridge` (`IB_id`, `interestId`, `contactId`) VALUES
(4, 4, 6),
(5, 5, 6),
(6, 4, 7),
(7, 6, 7),
(8, 7, 7);

-- --------------------------------------------------------

--
-- Table structure for table `interests`
--

CREATE TABLE IF NOT EXISTS `interests` (
  `interestId` int(11) NOT NULL AUTO_INCREMENT,
  `interestName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`interestId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `interests`
--

INSERT INTO `interests` (`interestId`, `interestName`) VALUES
(4, 'java'),
(5, 'mava'),
(6, 'sql'),
(7, 'python');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contactlist`
--
ALTER TABLE `contactlist`
  ADD CONSTRAINT `contactlist_ibfk_3` FOREIGN KEY (`empId`) REFERENCES `employee` (`empId`),
  ADD CONSTRAINT `contactlist_ibfk_4` FOREIGN KEY (`contactId`) REFERENCES `contacts` (`contactId`);

--
-- Constraints for table `contactrelation`
--
ALTER TABLE `contactrelation`
  ADD CONSTRAINT `contactrelation_ibfk_1` FOREIGN KEY (`contactListId`) REFERENCES `contactlist` (`contactListId`);

--
-- Constraints for table `interestbridge`
--
ALTER TABLE `interestbridge`
  ADD CONSTRAINT `interestbridge_ibfk_1` FOREIGN KEY (`interestId`) REFERENCES `interests` (`interestId`),
  ADD CONSTRAINT `interestbridge_ibfk_2` FOREIGN KEY (`contactId`) REFERENCES `contacts` (`contactId`);
