-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 08, 2017 at 06:37 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.0.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `internshipapp`
--
CREATE DATABASE IF NOT EXISTS `internshipapp` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `internshipapp`;

-- --------------------------------------------------------

--
-- Table structure for table `application`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `application`;
CREATE TABLE `application` (
  `ID_STUDENT` varchar(50) NOT NULL,
  `ID_VACANCY` varchar(50) NOT NULL,
  `APPLY_DATE` varchar(50) NOT NULL,
  `APPLICATION_STATUS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`ID_STUDENT`, `ID_VACANCY`, `APPLY_DATE`, `APPLICATION_STATUS`) VALUES
('B03151XXXX', 'VAC20171', '2017-10-09 00:24:16', 'NEW APPLICATION'),
('STUD0000001', 'VAC20172', '2017-08-09 20:30:38', 'ACCEPTED'),
('STUD0000001', 'VAC20173', '2017-09-27 00:13:29', 'NEW APPLICATION'),
('STUD0000001', 'VAC20174', '2017-08-09 20:30:50', 'REJECTED'),
('STUD0000002', 'VAC20171', '2017-08-09 20:29:21', 'ACCEPTED'),
('STUD0000002', 'VAC20173', '2017-08-09 20:30:46', 'NEW APPLICATION');

-- --------------------------------------------------------

--
-- Table structure for table `batch`
--
-- Creation: Oct 08, 2017 at 05:19 AM
--

DROP TABLE IF EXISTS `batch`;
CREATE TABLE `batch` (
  `BATCH_ID` int(11) NOT NULL,
  `BATCH` varchar(30) NOT NULL,
  `SEM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `batch`
--

INSERT INTO `batch` (`BATCH_ID`, `BATCH`, `SEM`) VALUES
(1, '201718', '1'),
(2, '201718', '2'),
(3, '201718', '3');

-- --------------------------------------------------------

--
-- Table structure for table `committee`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `committee`;
CREATE TABLE `committee` (
  `ID_STAFF` varchar(50) NOT NULL,
  `STAFF_IC` varchar(50) NOT NULL,
  `STAFF_NAME` varchar(50) NOT NULL,
  `STAFF_EMAIL` varchar(50) NOT NULL,
  `STAFF_NO_TEL` varchar(50) NOT NULL,
  `STAFF_POSITION` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `committee`
--

INSERT INTO `committee` (`ID_STAFF`, `STAFF_IC`, `STAFF_NAME`, `STAFF_EMAIL`, `STAFF_NO_TEL`, `STAFF_POSITION`) VALUES
('COMT0000001', '123456789876', 'COMMITEE DUMMY #1', 'DUMMY@EMAIL.COM', '1234567890', 'N/A'),
('COMT0000002', '123456789854', 'COMMITEE DUMMY #2', 'DUMMY@EMAIL.COM', '1234567890', 'N/A');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE `company` (
  `ID_COMPANY` varchar(50) NOT NULL,
  `COMP_NAME` varchar(50) NOT NULL,
  `COMP_ADDRSS` varchar(50) NOT NULL,
  `COMP_POSTCODE` varchar(50) NOT NULL,
  `COMP_LOCATION` varchar(50) NOT NULL,
  `COMP_STATE` varchar(50) NOT NULL,
  `COMP_TEL` varchar(50) NOT NULL,
  `COMP_EMAIL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`ID_COMPANY`, `COMP_NAME`, `COMP_ADDRSS`, `COMP_POSTCODE`, `COMP_LOCATION`, `COMP_STATE`, `COMP_TEL`, `COMP_EMAIL`) VALUES
('COM0000001', 'COMPANY DUMMY #1', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A', 'N/A'),
('COM0000002', 'COMPANY DUMMY#2', 'DUMMY', 'DUMMY', 'DUMMY', 'DUMMY', 'DUMMY', 'DUMMY'),
('COM20171', 'hshddjdjd', 'cf c', 'cndb', 'dndn', 'ndb', 'dhdh', 'zbsh'),
('COM20172', 'alii', 'qwerr', 'cndbdhdjdjb', 'dbshs2@5(', 'ndbbdbdbdh', 'dhdhvxbx', 'zbshxhxbd'),
('COM20173', 'A', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `sequencenumber`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `sequencenumber`;
CREATE TABLE `sequencenumber` (
  `module` varchar(255) NOT NULL,
  `lastSeqNumber` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sequencenumber`
--

INSERT INTO `sequencenumber` (`module`, `lastSeqNumber`) VALUES
('company', 3),
('vacancy_number', 11);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--
-- Creation: Oct 08, 2017 at 05:21 AM
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `ID_STUDENT` varchar(50) NOT NULL,
  `IC_NO` varchar(50) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `NO_TEL` varchar(50) NOT NULL,
  `ADDRESS` text NOT NULL,
  `COURSE` varchar(30) NOT NULL,
  `STATUS` char(1) NOT NULL DEFAULT '0',
  `BATCH_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`ID_STUDENT`, `IC_NO`, `NAME`, `EMAIL`, `NO_TEL`, `ADDRESS`, `COURSE`, `STATUS`, `BATCH_ID`) VALUES
('B03151XXXX', 'xxxxxxxxxxxx', 'XXXXXXXXXXXXXXX', 'XXXXXXXXXXXXXXXX', 'XXXXXXXXXXXXX', 'XXXXXXXXXXXXXXXXXXXXX', 'XXXX', '1', 1),
('B03152VVVV', 'vvvvvvvvvvvv', 'VVVVVVVVVVVVVV', 'VVVVV@EMAIL.COM', 'VVVVVVVVV', 'VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV', 'VVVV', '0', 3),
('B03152ZZZZ', 'zzzzzzzzzzzz', 'ZZZZZZZZZZZZZZZZZZZZZ', 'ZZZZZZ@EMAIL.COM', 'ZZZZZZZZZZ', 'ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ', 'ZZZZ', '0', 2),
('D03152XXXX', 'yyyyyyyyyyyyy', 'YYYYYYYYYYYYY', 'YYYYYYYYYYYYYY', 'YYYYYYYYYYYYYYYY', 'YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY', 'YYYYYYYYYYYYYYYYYYYY', '1', 1),
('STUD0000001', '98765432123', 'DUMMY STUDENT #2', 'N/A', 'N/A', 'N/A', 'BITD', '1', 2),
('STUD0000002', '123456789012', 'DUMMY STUDENT #1', 'DUMMY N/A', 'N/A', 'N/A', 'BITS', '0', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `usersID` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usersType` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`usersID`, `username`, `password`, `usersType`) VALUES
('B03151XXXX', 'STUDX', 'abc123', 'student'),
('B03152VVVV', 'STUDV', 'abc123', 'student'),
('B03152ZZZZ', 'STUDZ', 'abc123', 'student'),
('COM0000001', 'com1', 'abc123', 'company'),
('COM0000002', 'com2', 'abc123', 'company'),
('COM20171', 'qwerty ', 'abc', 'company'),
('COM20172', 'qwerty3', 'abc', 'company'),
('COM20173', 'aa', 'aaa', 'company'),
('COMT0000001', 'comit1', 'abc123', 'committee'),
('COMT0000002', 'comit2', 'abc123', 'committee'),
('D03152XXXX', 'STUDDX', 'abc123', 'student'),
('STUD0000001', 'stud1', 'abc123', 'student'),
('STUD0000002', 'stud2', 'abc123', 'student');

-- --------------------------------------------------------

--
-- Table structure for table `vacancy`
--
-- Creation: Sep 26, 2017 at 02:52 AM
--

DROP TABLE IF EXISTS `vacancy`;
CREATE TABLE `vacancy` (
  `ID_VACANCY` varchar(50) NOT NULL,
  `ID_COMPANY` varchar(50) NOT NULL,
  `POSITION` varchar(50) NOT NULL,
  `NO_OF_VACANCY` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(50) NOT NULL,
  `VCNCY_AD_DATE` datetime NOT NULL,
  `STATUS` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `vacancy`
--

INSERT INTO `vacancy` (`ID_VACANCY`, `ID_COMPANY`, `POSITION`, `NO_OF_VACANCY`, `DESCRIPTION`, `VCNCY_AD_DATE`, `STATUS`) VALUES
('VAC20171', 'COM0000001', 'manager', '1', 'jadi manager kat kedai mamak', '2017-07-23 18:32:46', 'available'),
('VAC201711', 'COM0000001', 'PERCUBAAN', '30', 'Saja saja', '2017-09-28 01:26:28', 'available'),
('VAC20172', 'COM0000001', 'kuli', '9', 'sape2 nk kuli mai sini', '2017-07-23 18:48:22', 'available'),
('VAC20173', 'COM0000002', 'SUPERVISOR', '3', 'supervisor bos,gaji x de,sbb intern kan..x yah nk ', '2017-07-23 21:06:13', 'available'),
('VAC20174', 'COM0000001', 'HAI2', '1', 'gwhsjs', '2017-07-24 15:55:52', 'available'),
('VAC20175', 'COM0000001', 'UPDATED!!', '33', 'Ini hanyalah sekadar testing sahaja', '2017-09-27 00:07:57', 'available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`ID_STUDENT`,`ID_VACANCY`);

--
-- Indexes for table `batch`
--
ALTER TABLE `batch`
  ADD PRIMARY KEY (`BATCH_ID`);

--
-- Indexes for table `committee`
--
ALTER TABLE `committee`
  ADD PRIMARY KEY (`ID_STAFF`);

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`ID_COMPANY`);

--
-- Indexes for table `sequencenumber`
--
ALTER TABLE `sequencenumber`
  ADD PRIMARY KEY (`module`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`ID_STUDENT`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`usersID`);

--
-- Indexes for table `vacancy`
--
ALTER TABLE `vacancy`
  ADD PRIMARY KEY (`ID_VACANCY`,`ID_COMPANY`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `batch`
--
ALTER TABLE `batch`
  MODIFY `BATCH_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
