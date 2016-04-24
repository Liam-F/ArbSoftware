-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 21, 2016 at 03:32 AM
-- Server version: 5.5.45-cll-lve
-- PHP Version: 5.4.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ArbitrageTradingSite`
--

-- --------------------------------------------------------

--
-- Table structure for table `ArbitrageTrades`
--

CREATE TABLE IF NOT EXISTS `ArbitrageTrades` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profit` double NOT NULL,
  `arbPercentage` double NOT NULL,
  `playerOne` varchar(50) NOT NULL,
  `playerOneBet` double NOT NULL,
  `playerOneSite` varchar(20) NOT NULL,
  `playerOneOdds` double NOT NULL,
  `playerTwo` varchar(50) NOT NULL,
  `playerTwoBet` double NOT NULL,
  `playerTwoSite` varchar(20) NOT NULL,
  `playerTwoOdds` double NOT NULL,
  `sport` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1110 ;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE IF NOT EXISTS `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `email`, `password`) VALUES
(7, 'shane.clarke93@gmail.com', 'theboss'),
(9, 'abc@g.com', 'ssssssss'),
(10, 'c@d.c', 'ssssssss'),
(11, 's@a.com', 'aaaaaaaa'),
(12, 'shanec@g.com', 'aaaaaaaa'),
(13, 'a@s.c', 'aaaaaaaa'),
(14, 's@d.c', 'aaaaaaaa'),
(15, 'abc@gmail.com', 'aaaaaaaa');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
