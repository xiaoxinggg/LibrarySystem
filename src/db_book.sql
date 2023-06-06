/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.28 : Database - db_book
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_book` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;

USE `db_book`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` char(10) DEFAULT NULL,
  `password` char(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `admin` */

insert  into `admin`(`id`,`userName`,`password`) values (1,'hhh','1234567'),(2,'hhh','654832'),(3,'hhh','963852'),(4,'ppp','123456'),(5,'567','567');

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `isbn` int DEFAULT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `num` int DEFAULT NULL,
  `classify` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isbn` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `book` */

insert  into `book`(`id`,`bookName`,`isbn`,`author`,`price`,`num`,`classify`) values (4,'红楼梦',321654,'曹雪芹',55.3,28,4),(5,'西游记',123456,'吴承恩',33.6,20,6),(6,'水浒传',33645,'施耐庵',43.2,15,5),(15,'离散数学',45465151,'培',16,21,1),(17,'大学英语3',334455,'李荫华',48,20,3),(21,'大学英语4',987654,'李华',56,20,9);

/*Table structure for table `bookrecord` */

DROP TABLE IF EXISTS `bookrecord`;

CREATE TABLE `bookrecord` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `bookName` char(25) DEFAULT NULL,
  `borrower` char(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `bookrecord` */

insert  into `bookrecord`(`id`,`bookName`,`borrower`,`date`) values (44,'高等数学','dll','2022-12-02 19:31:30'),(46,'离散数学','1234567','2023-01-03 16:14:55'),(47,'高等数学','11111','2023-01-03 16:16:42'),(48,'离散数学','11111','2023-01-03 16:16:47'),(50,'离散数学','888888','2022-12-06 16:21:26'),(54,'红楼梦','6543210','2023-01-04 14:07:41'),(55,'高等数学','6543210','2023-01-04 14:07:46'),(58,'红楼梦','123','2023-01-06 16:57:12');

/*Table structure for table `borrowrecord` */

DROP TABLE IF EXISTS `borrowrecord`;

CREATE TABLE `borrowrecord` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookName` char(25) DEFAULT NULL,
  `borrower` char(25) DEFAULT NULL,
  `borrowTime` date DEFAULT NULL,
  `returnTime` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `borrowrecord` */

insert  into `borrowrecord`(`id`,`bookName`,`borrower`,`borrowTime`,`returnTime`) values (1,'西游记','dll','2022-12-09',NULL),(2,'红楼梦','haha','2022-12-09',NULL),(3,'离散数学','dll','2022-12-09','2022-12-09'),(4,'红楼梦','kkk','2022-12-09','2022-12-10'),(5,'西游记','kkk','2022-12-09','2022-12-10'),(6,'水浒传','kkk','2022-12-09','2022-12-10'),(7,'红楼梦','kkk','2022-12-10','2022-12-10'),(8,'西游记','kkk','2022-12-10','2022-12-10'),(9,'离散数学','kkk','2022-12-10','2022-12-10'),(10,'水浒传','kkk','2022-12-10','2022-12-10'),(11,'水浒传','kkk','2022-12-10','2022-12-10'),(12,'西游记','kkk','2022-12-10','2022-12-10'),(13,'红楼梦','kkk','2022-12-10','2022-12-10'),(14,'高等数学','dll','2022-12-28',NULL),(15,'红楼梦','aaabb','2022-12-29','2022-12-29'),(17,'离散数学','1234567','2023-01-03',NULL),(18,'高等数学','11111','2023-01-03',NULL),(19,'离散数学','11111','2023-01-03',NULL),(20,'高等数学','888888','2023-01-03','2023-01-03'),(21,'离散数学','888888','2023-01-03',NULL),(22,'高等数学','123','2023-01-03','2023-01-03'),(23,'离散数学','123','2023-01-03','2023-01-04'),(24,'大学英语3','123','2023-01-04','2023-01-04'),(25,'红楼梦','6543210','2023-01-04',NULL),(26,'高等数学','6543210','2023-01-04',NULL),(27,'红楼梦','888666','2023-01-04','2023-01-04'),(28,'大学英语3','888666','2023-01-04','2023-01-04'),(29,'红楼梦','123','2023-01-08',NULL),(30,'红楼梦','999999','2023-06-02','2023-06-02');

/*Table structure for table `normaluser` */

DROP TABLE IF EXISTS `normaluser`;

CREATE TABLE `normaluser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` char(25) DEFAULT NULL,
  `balance` double DEFAULT '10',
  `theorySum` int DEFAULT '0',
  `realSum` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `normaluser` */

insert  into `normaluser`(`id`,`userName`,`password`,`balance`,`theorySum`,`realSum`) values (1,'dll','123456',110,NULL,NULL),(2,'8888888','777456',10,NULL,NULL),(3,'aaa','123654',10,20,NULL),(4,'bala','123456',110,30,NULL),(5,'haha','123456',20,10,NULL),(6,'abc','',10,20,NULL),(7,'kkk','123456',20,10,NULL),(8,'','',10,10,NULL),(9,'aaabb','123123',-2,10,NULL),(11,'1234567','9999999',10,10,NULL),(12,'11111','11111',10,10,NULL),(13,'888888','888888',110,10,NULL),(14,'123','123',29.4,10,NULL),(15,'654321','654',10,20,NULL),(16,'6543210','6543210',10,10,NULL),(17,'888666','888666',116.9,10,NULL),(18,NULL,NULL,10,0,NULL),(19,NULL,NULL,10,0,NULL),(20,NULL,NULL,10,0,NULL),(21,'999999','999999',10,10,NULL);

/* 触发器:删除书籍的时候也会删除借书记录表里面的相关信息 */
DELIMITER //
CREATE TRIGGER delete_related_info
    AFTER DELETE
    ON book
    FOR EACH ROW
BEGIN
    DELETE FROM bookrecord WHERE bookrecord.bookName = OLD.bookName;
    DELETE FROM borrowrecord WHERE borrowrecord.bookName = OLD.bookName;
END//
DELIMITER ;

/*触发器: 删除user信息的时候也删除借书记录*/
DELIMITER //
CREATE TRIGGER delete_related_user
    AFTER DELETE
    ON normaluser
    FOR EACH ROW
BEGIN
    DELETE FROM bookrecord WHERE bookrecord.borrower = OLD.userName;
    DELETE FROM borrowrecord WHERE borrowrecord.bookName = OLD.userName;
END//
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
