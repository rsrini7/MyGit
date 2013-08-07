/*
SQLyog Ultimate v9.20 
MySQL - 5.5.19 : Database - vaswindb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vaswindb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `vaswindb`;

/*Table structure for table `dirapp` */

DROP TABLE IF EXISTS `dirapp`;

CREATE TABLE `dirapp` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `entity_ids` varchar(10) DEFAULT NULL,
  `acronym` varchar(50) DEFAULT NULL,
  `aka1` varchar(50) DEFAULT NULL,
  `aka2` varchar(50) DEFAULT NULL,
  `aka3` varchar(50) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `helpGroup` varchar(10) DEFAULT NULL,
  `version` varchar(10) DEFAULT NULL,
  `date_updated` datetime DEFAULT NULL,
  `vendor` varchar(10) DEFAULT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  `display` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `dirapp` */

insert  into `dirapp`(`id`,`name`,`description`,`entity_ids`,`acronym`,`aka1`,`aka2`,`aka3`,`status`,`helpGroup`,`version`,`date_updated`,`vendor`,`approved`,`display`) values (1,'srini','description','entity_ids','acronym','aka1','aka2','aka3',1,'helpGroup','version','2012-06-10 10:32:28','edfs',1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
