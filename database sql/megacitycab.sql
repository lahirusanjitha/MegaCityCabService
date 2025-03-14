-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2025 at 07:38 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `megacitycab`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminId` int(11) NOT NULL,
  `userName` varchar(244) NOT NULL,
  `password` varchar(244) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminId`, `userName`, `password`) VALUES
(1, 'admin', '$2a$12$2ilySywEj3prz29gZ21s8uZ0ooJDFv.JBNSpL9UdHYu944hx6Qo02');

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billId` int(11) NOT NULL,
  `bookingId` int(11) DEFAULT NULL,
  `baseFare` double DEFAULT NULL,
  `distance` double DEFAULT NULL,
  `ratePerKm` double DEFAULT NULL,
  `duration` double DEFAULT NULL,
  `ratePerMinute` double DEFAULT NULL,
  `additionalCharges` double DEFAULT NULL,
  `taxRate` double DEFAULT NULL,
  `totalFare` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billId`, `bookingId`, `baseFare`, `distance`, `ratePerKm`, `duration`, `ratePerMinute`, `additionalCharges`, `taxRate`, `totalFare`) VALUES
(7, 3, 100, 47, 32, 4, 2, 4, 1, 1632.16);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingId` int(11) NOT NULL,
  `customerId` int(11) DEFAULT NULL,
  `pickupLocation` varchar(255) DEFAULT NULL,
  `dropoffLocation` varchar(255) DEFAULT NULL,
  `bookingDate` datetime DEFAULT NULL,
  `status` enum('Pending','Completed','Pending','Cancelled') DEFAULT 'Pending',
  `driverId` int(11) DEFAULT NULL,
  `vehicleId` int(11) DEFAULT NULL,
  `totalFare` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingId`, `customerId`, `pickupLocation`, `dropoffLocation`, `bookingDate`, `status`, `driverId`, `vehicleId`, `totalFare`) VALUES
(2, 3, 'gampaha', 'colombo', '2025-03-07 10:47:56', 'Completed', 2, 2, NULL),
(3, 3, 'ballapana', 'divulaptiya', '2025-03-14 08:27:00', 'Completed', 2, 2, NULL),
(4, 3, 'gampaha', 'minuwangoda', '2025-03-14 10:15:00', 'Pending', 2, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `userName` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `nic` varchar(45) NOT NULL,
  `tel` varchar(13) NOT NULL,
  `password` varchar(244) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `name`, `userName`, `address`, `nic`, `tel`, `password`) VALUES
(1, 'John Doe', 'johndoe123', '123 Main Street, Colombo', '987654321V', '0771234567', 'securepassword'),
(2, 'John logy', 'johndoe345', '123 Main Street, nemgobo', '987654345V', '0771534567', '$2a$12$0r5EkmD4mHXhAlVP8Cl1Lu5pnShPmH2/Zv2iWiQ.aCVluCWTOoOt6'),
(3, 'lahiru', 'lahiru2000', '123 Main Street, ballapana', '200026504518', '0781922408', '$2a$12$2ilySywEj3prz29gZ21s8uZ0ooJDFv.JBNSpL9UdHYu944hx6Qo02'),
(4, 'shehan', 'shehan98', '52 ballapana assanawatta', '19985786412v', '0781922408', '$2a$12$lCE9unMBjib8EJ9nJATM.OM0Aeembf/WEdu4CgHBKNIstoYqvyT4C'),
(5, 'ushan', 'ushan98', '52 ballapana assanawatta', '19985786412v', '0781922408', '$2a$12$WuRn.M0Lt/jv636FyRPqm.Xk.nYEX2NUxW4kE7tdLogShGCrkupja'),
(7, 'dulanjaya', 'dulanajaya21', '37 hapuwalana maradagahamulla', '978564285v', '0782369854', '$2a$12$QiC.WInWUJj65/GvVPppA.5oCakvMBUUzD01w.msizKND3MVNA8VW');

-- --------------------------------------------------------

--
-- Table structure for table `driver`
--

CREATE TABLE `driver` (
  `driverId` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `licenseNumber` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `status` enum('Available','On Duty','Inactive') DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `driver`
--

INSERT INTO `driver` (`driverId`, `name`, `licenseNumber`, `phone`, `status`) VALUES
(2, 'lahiru sanjitha', 'ABC13345', '0732345678', 'Available'),
(3, 'shehan', '7856954', '0781922408', 'Available');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicleId` int(11) NOT NULL,
  `plateNumber` varchar(50) DEFAULT NULL,
  `model` varchar(100) DEFAULT NULL,
  `type` enum('Car','Van','SUV','Truck') DEFAULT NULL,
  `status` enum('Available','In Service','Booked') DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`vehicleId`, `plateNumber`, `model`, `type`, `status`) VALUES
(2, '4566', 'toyota', 'Car', 'Available'),
(3, 'PF-5869', 'Toyota KDH', 'Van', 'Available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billId`),
  ADD KEY `bookingId` (`bookingId`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingId`),
  ADD KEY `customerId` (`customerId`),
  ADD KEY `driverId` (`driverId`),
  ADD KEY `vehicleId` (`vehicleId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customerId`);

--
-- Indexes for table `driver`
--
ALTER TABLE `driver`
  ADD PRIMARY KEY (`driverId`),
  ADD UNIQUE KEY `licenseNumber` (`licenseNumber`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicleId`),
  ADD UNIQUE KEY `plateNumber` (`plateNumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `billId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `driver`
--
ALTER TABLE `driver`
  MODIFY `driverId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicleId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`bookingId`) REFERENCES `booking` (`bookingId`);

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`driverId`) REFERENCES `driver` (`driverId`),
  ADD CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`vehicleId`) REFERENCES `vehicle` (`vehicleId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
