-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for shoes
CREATE DATABASE IF NOT EXISTS `shoes` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shoes`;

-- Dumping structure for table shoes.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table shoes.admin: ~0 rows (approximately)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `username`, `password`) VALUES
	(1, 'marko', '123');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table shoes.categories
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table shoes.categories: ~3 rows (approximately)
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` (`id`, `name`) VALUES
	(1, 'Male Shoes'),
	(2, 'Female Shoes'),
	(3, 'Sport Shoes'),
	(4, 'SAFETY SHOES');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;

-- Dumping structure for table shoes.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userdata` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `products` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ordertime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table shoes.orders: ~2 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `userdata`, `products`, `ordertime`) VALUES
	(4, ' Marka cara 123', '[{"name":NIKE HYPERVENOM PHANTOM 3 ,"q":3}]', '2018-08-07 19:47:32'),
	(5, ' marko', '[{"name":TS KOPACKE PREDATOR 18.1 FG,"q":2}]', '2018-08-13 18:11:06');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table shoes.shoes
CREATE TABLE IF NOT EXISTS `shoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL DEFAULT '0',
  `category` int(11) NOT NULL DEFAULT '0',
  `size` int(11) DEFAULT '0',
  `name` varchar(250) COLLATE utf8_unicode_ci DEFAULT '0',
  `photo` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table shoes.shoes: ~4 rows (approximately)
/*!40000 ALTER TABLE `shoes` DISABLE KEYS */;
INSERT INTO `shoes` (`id`, `price`, `category`, `size`, `name`, `photo`) VALUES
	(1, 12200, 1, 42, 'Nike Air Max 2017', 'image4.jpg'),
	(2, 22000, 3, 43, 'NIKE HYPERVENOM PHANTOM 3 ', 'image2.jpg'),
	(4, 8400, 2, 39, 'PATIKE REEBOK KING GLOBAL', 'image1.jpg'),
	(6, 26399, 3, 40, 'TS KOPACKE PREDATOR 18.1 FG', 'Webp.net-resizeimage.jpg');
/*!40000 ALTER TABLE `shoes` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
