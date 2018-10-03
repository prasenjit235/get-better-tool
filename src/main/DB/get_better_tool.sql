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
  `iConversationID` varchar(32),
  `dtCReatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `iYear` int(10) NOT NULL,
  `iMonth` int(10) NOT NULL,
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
  `iConversationID` varchar(32),
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
  `iConversationID` varchar(32) NOT NULL,
  `tConversation` tinytext NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `iCreatedBy` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

-- Table structure for table `tbl_Employee`--
CREATE TABLE IF NOT EXISTS `tbl_Employee` (
  `iEmpID` int(11) NOT NULL AUTO_INCREMENT,
  `cEmpName` char(50) NOT NULL,
  `cEmpMailId` char(60) NOT NULL,
  `iDesignationID` int(11) NOT NULL, 
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dtJoiningDate` datetime DEFAULT NULL,
  `iRMID` int(11) NOT NULL DEFAULT 2,
  `iStatus` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iEmpID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `tbl_Team` (
  `iTeamID` int(11) NOT NULL AUTO_INCREMENT,
  `cTeamName` char(50) NOT NULL,
  `dtCreatedOn` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `iStatus`  tinyint(2) NOT NULL DEFAULT '1' COMMENT '1- Active 2-Inactive',
  PRIMARY KEY (`iTeamID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;



CREATE TABLE IF NOT EXISTS `tbl_TeamMembers` (
  `iSlNo` int(11) NOT NULL AUTO_INCREMENT,
  `iTeamID` int(11) NOT NULL,
  `iEmpID` int(11) NOT NULL, 
  PRIMARY KEY (`iSlNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `tbl_Employee_Token` (
 `iEmpID` int(11) NOT NULL,
 `cPassword` char(100) NOT NULL,
 `cToken` char(100),
 FOREIGN KEY (`iEmpID`) REFERENCES tbl_Employee(iEmpID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert into tbl_Designations(iDesignationID, cDesignation) values(1, 'Software Engineer'),(2,'Senior Software Engineer'),(3,'Technical Analyst'),(4,'Technical Lead'),(5,'Project Manager'),(6,'Software Achitect'),(7,'Chief Architect'),(8,'Chief Executive Officer');
insert into tbl_Employee(iEmpID,cEmpName,cEmpMailId,iDesignationID,dtJoiningDate,iRMID) values(1002,'Ranjith Ayinala','ranjith.ayinala@techmojo.in',8,NOW(),1002),(1003,'Prasenjit Das','prasenjit@techmojo.in',4,NOW(),1002),(1004,'Lokesh Matturti','lokesh.matturti@techmojo.in',2,NOW(),1003),(1005,'Mallikharjun Janapareddi','mj@techmojo.in',3,NOW(),1003),(1006,'Tausief Shaikh','tausief.shaikh@techmojo.in',4,NOW(),1002),(1007,'Chandrakanth Nelge','chandrakanth.nelge@techmojo.in',3,NOW(),1006),(1008,'Harish S','harish.s@techmojo.in',2,NOW(),1006);
insert into tbl_Team(iTeamID,cTeamName) values(1,'Openbet SportsBet'),(2,'Dalberry'),(3,'ROKA'),(4,'Riverplay');
insert into tbl_TeamMembers(iTeamID,iEmpID) values(1,1003),(1,1004),(1,1005),(2,1006),(2,1007),(2,1008); 
insert into tbl_Employee_Token(iEmpID,cPassword) values(1002,'$2a$10$wFyOSJqNumfDCREK5SmcMOv9o.87Iagk/8tQ1SxnnXw6YjMBFeuPK'),(1003,'$2a$10$wFyOSJqNumfDCREK5SmcMOv9o.87Iagk/8tQ1SxnnXw6YjMBFeuPK'),(1004,'$2a$10$wFyOSJqNumfDCREK5SmcMOv9o.87Iagk/8tQ1SxnnXw6YjMBFeuPK'),(1005,'$2a$10$wFyOSJqNumfDCREK5SmcMOv9o.87Iagk/8tQ1SxnnXw6YjMBFeuPK'),(1006,'$2a$10$wFyOSJqNumfDCREK5SmcMOv9o.87Iagk/8tQ1SxnnXw6YjMBFeuPK');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
