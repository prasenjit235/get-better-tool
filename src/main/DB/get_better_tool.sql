-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 26, 2018 at 11:28 PM
-- Server version: 5.5.61-0ubuntu0.14.04.1
-- PHP Version: 5.5.9-1ubuntu4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `get_better_tool`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Appraisals`
--

CREATE TABLE IF NOT EXISTS `tbl_Appraisals` (
  `iAppraisalID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `iFrom` int(11) NOT NULL,
  `iTo` int(11) NOT NULL,
  `iConversationID` int(11) NOT NULL,
  `dtCReatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `iStatus` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iAppraisalID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Appraisal_Details`
--

CREATE TABLE IF NOT EXISTS `tbl_Appraisal_Details` (
  `iSno` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `iAppraisalID` int(11) NOT NULL,
  `iWeigtageID` int(11) NOT NULL,
  `iScore` int(11) NOT NULL,
  `iConversationID` int(11) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`iSno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Categories`
--

CREATE TABLE IF NOT EXISTS `tbl_Categories` (
  `iCategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `cCategoryName` char(100) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iCategoryID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tbl_Categories`
--

INSERT INTO `tbl_Categories` (`iCategoryID`, `cCategoryName`, `dtCreatedOn`, `dtUpdatedOn`, `iStatus`) VALUES
(1, 'Company Level', '2018-09-26 08:56:46', NULL, 1),
(2, 'Team Level', '2018-09-26 08:56:46', NULL, 1),
(3, 'Individual Level', '2018-09-26 08:56:58', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Conversations`
--

CREATE TABLE IF NOT EXISTS `tbl_Conversations` (
  `iConversationID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tConversation` tinytext NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`iConversationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Designations`
--

CREATE TABLE IF NOT EXISTS `tbl_Designations` (
  `iDesignationID` int(11) NOT NULL AUTO_INCREMENT,
  `cDesignation` char(50) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iDesignationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Parameters`
--

CREATE TABLE IF NOT EXISTS `tbl_Parameters` (
  `iParameterID` int(11) NOT NULL AUTO_INCREMENT,
  `cParameterName` char(50) NOT NULL,
  `iCategoryID` int(11) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iParameterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Projects`
--

CREATE TABLE IF NOT EXISTS `tbl_Projects` (
  `iProjectID` int(11) NOT NULL AUTO_INCREMENT,
  `cProjectName` char(50) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iProjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Roles`
--

CREATE TABLE IF NOT EXISTS `tbl_Roles` (
  `iRoleID` int(11) NOT NULL AUTO_INCREMENT,
  `cRole` char(20) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_Weigtages`
--

CREATE TABLE IF NOT EXISTS `tbl_Weigtages` (
  `iWeightageID` int(11) NOT NULL AUTO_INCREMENT,
  `iParameterID` int(11) NOT NULL,
  `iDesignationID` int(11) NOT NULL,
  `iScore` int(11) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtUpdatedOn` datetime DEFAULT NULL,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iWeightageID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
