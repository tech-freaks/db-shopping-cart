-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.27 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for tfcom_articles
CREATE DATABASE IF NOT EXISTS `tfcom_articles` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tfcom_articles`;


-- Dumping structure for table tfcom_articles.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_price` decimal(10,2) DEFAULT '0.00',
  `shipping_price` decimal(10,2) DEFAULT '0.00',
  `tax_price` decimal(10,2) DEFAULT '0.00',
  `total_price` decimal(10,2) DEFAULT '0.00',
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table tfcom_articles.cartitem
CREATE TABLE IF NOT EXISTS `cartitem` (
  `cartitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) NOT NULL DEFAULT '0',
  `product_id` int(11) NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL DEFAULT '0',
  `unitprice` decimal(6,2) NOT NULL DEFAULT '0.00',
  `totalprice` decimal(6,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`cartitem_id`),
  KEY `FK_cartitem_cart` (`cart_id`),
  KEY `FK_cartitem_product` (`product_id`),
  CONSTRAINT `FK_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FK_cartitem_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table tfcom_articles.product
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `partnumber` varchar(50) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `thumbnail_url` varchar(240) DEFAULT NULL,
  `buyable` char(1) NOT NULL DEFAULT 'Y',
  `unitprice` decimal(8,2) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
