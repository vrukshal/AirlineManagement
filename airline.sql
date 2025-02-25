-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 06, 2018 at 08:34 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airline`
--

-- --------------------------------------------------------

--
-- Table structure for table `flighttable`
--

CREATE TABLE `flighttable` (
  `Id` int(11) NOT NULL,
  `FlightName` varchar(50) NOT NULL,
  `PName` varchar(50) NOT NULL,
  `EconomyFare` double NOT NULL DEFAULT '0',
  `BusinessFare` double NOT NULL DEFAULT '0',
  `Source` varchar(50) NOT NULL,
  `Destination` varchar(50) NOT NULL,
  `DepartureTime` varchar(50) NOT NULL,
  `ArrivalTime` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flighttable`
--

INSERT INTO `flighttable` (`Id`, `FlightName`, `PName`, `EconomyFare`, `BusinessFare`, `Source`, `Destination`, `DepartureTime`, `ArrivalTime`) VALUES
(1, 'smith flight', 'smith Airlines', 400, 600, 'mumbai', 'delhi', '20:24', '21:00');

-- --------------------------------------------------------

--
-- Table structure for table `passengertable`
--

CREATE TABLE `passengertable` (
  `Id` int(11) NOT NULL,
  `Ticketid` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Passport` varchar(50) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passengertable`
--

INSERT INTO `passengertable` (`Id`, `Ticketid`, `Name`, `Passport`, `Age`, `Gender`) VALUES
(1, 1, 'arnold', '34453434', 50, 'M'),
(2, 1, 'mark', '34554334', 45, 'M'),
(3, 2, 'arnold', '34453434', 50, 'M'),
(4, 2, 'mark', '34554334', 45, 'M');

-- --------------------------------------------------------

--
-- Table structure for table `pilottable`
--

CREATE TABLE `pilottable` (
  `Id` int(11) NOT NULL,
  `PilotName` varchar(50) NOT NULL,
  `PilotContact` varchar(50) NOT NULL,
  `PilotAddress` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pilottable`
--

INSERT INTO `pilottable` (`Id`, `PilotName`, `PilotContact`, `PilotAddress`) VALUES
(1, 'john', '1234565678', 'xyz xyz xyz');

-- --------------------------------------------------------

--
-- Table structure for table `planetable`
--

CREATE TABLE `planetable` (
  `Id` int(11) NOT NULL,
  `PlaneName` varchar(50) NOT NULL,
  `Capacity` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `planetable`
--

INSERT INTO `planetable` (`Id`, `PlaneName`, `Capacity`) VALUES
(1, 'smith Airlines', '500');

-- --------------------------------------------------------

--
-- Table structure for table `tickettable`
--

CREATE TABLE `tickettable` (
  `Id` int(11) NOT NULL,
  `tDate` datetime DEFAULT NULL,
  `source` varchar(50) NOT NULL,
  `destination` varchar(50) NOT NULL,
  `FlightName` varchar(50) NOT NULL,
  `Class` varchar(50) NOT NULL,
  `Amount` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickettable`
--

INSERT INTO `tickettable` (`Id`, `tDate`, `source`, `destination`, `FlightName`, `Class`, `Amount`) VALUES
(1, '2018-09-06 12:03:43', 'mumbai', 'delhi', 'smith flight', 'Economy', 800),
(2, '2018-09-06 12:04:11', '', '', 'smith flight', 'Business', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `flighttable`
--
ALTER TABLE `flighttable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `par_ind` (`PName`);

--
-- Indexes for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `par_ind` (`Ticketid`);

--
-- Indexes for table `pilottable`
--
ALTER TABLE `pilottable`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `planetable`
--
ALTER TABLE `planetable`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `PlaneName` (`PlaneName`);

--
-- Indexes for table `tickettable`
--
ALTER TABLE `tickettable`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `flighttable`
--
ALTER TABLE `flighttable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `passengertable`
--
ALTER TABLE `passengertable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pilottable`
--
ALTER TABLE `pilottable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `planetable`
--
ALTER TABLE `planetable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tickettable`
--
ALTER TABLE `tickettable`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `flighttable`
--
ALTER TABLE `flighttable`
  ADD CONSTRAINT `fk_branchTable` FOREIGN KEY (`PName`) REFERENCES `planetable` (`PlaneName`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `passengertable`
--
ALTER TABLE `passengertable`
  ADD CONSTRAINT `fk_PassengerTable` FOREIGN KEY (`Ticketid`) REFERENCES `tickettable` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
