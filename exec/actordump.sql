-- MySQL dump 10.13  Distrib 8.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: actors
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarm`
--

DROP TABLE IF EXISTS `alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `type` char(3) NOT NULL DEFAULT '없음',
  `is_read` char(1) NOT NULL DEFAULT 'F',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_Alarm_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Alarm_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm`
--

LOCK TABLES `alarm` WRITE;
/*!40000 ALTER TABLE `alarm` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `recruitment_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `video_link` varchar(255) DEFAULT '',
  `content` varchar(200) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `saved_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Recruitment_TO_Apply_1` (`recruitment_id`),
  KEY `FK_Member_TO_Apply_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Apply_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_Recruitment_TO_Apply_1` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
INSERT INTO `apply` VALUES (1,1,3,'VideoLink1','Content1','2024-03-28 09:45:58','2024-03-28 09:45:58','SavedName1'),(2,2,4,'VideoLink2','Content2','2024-03-28 09:45:58','2024-03-28 09:45:58','SavedName2'),(3,3,1,'VideoLink3','Content3','2024-03-28 09:45:58','2024-03-28 09:45:58','SavedName3'),(4,4,1,'VideoLink4','Content4','2024-03-28 09:45:58','2024-03-28 09:45:58','SavedName4'),(5,5,1,'VideoLink5','Content5','2024-03-28 09:45:58','2024-03-28 09:45:58','SavedName5'),(7,10,34,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/applies/4c637d80-9b41-48ad-9d34-db699b340ca8_%EC%BA%A1%EC%B2%98.PNG','안녕하세요','2024-04-04 00:32:06','2024-04-04 00:32:06','4c637d80-9b41-48ad-9d34-db699b340ca8_캡처.PNG');
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blacklist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `warning` char(1) NOT NULL DEFAULT 'F',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_Blacklist_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Blacklist_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender` bigint NOT NULL,
  `chat_room_id` bigint NOT NULL,
  `content` varchar(100) NOT NULL,
  `is_read` char(1) NOT NULL DEFAULT 'F',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` char(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_chat_message_1` (`sender`),
  KEY `FK_chat_room_TO_chat_message_1` (`chat_room_id`),
  CONSTRAINT `FK_chat_room_TO_chat_message_1` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`),
  CONSTRAINT `FK_Member_TO_chat_message_1` FOREIGN KEY (`sender`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_room`
--

DROP TABLE IF EXISTS `chat_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_room` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_room`
--

LOCK TABLES `chat_room` WRITE;
/*!40000 ALTER TABLE `chat_room` DISABLE KEYS */;
INSERT INTO `chat_room` VALUES (1,'배신자','2024-04-03 07:39:20','2024-04-03 07:39:20'),(2,'자신 추가 테스트1','2024-04-03 08:18:34','2024-04-03 08:18:34'),(3,'본인 추가 테스트 1','2024-04-03 08:19:37','2024-04-03 08:19:37'),(4,'자신 추가 테스트2','2024-04-03 08:24:17','2024-04-03 08:24:17'),(5,'자신 추가 테스트2','2024-04-03 08:28:29','2024-04-03 08:28:29'),(6,'jh1234 테스트 1','2024-04-03 09:24:09','2024-04-03 09:24:09'),(7,'jh1234 test1','2024-04-03 11:27:12','2024-04-03 11:27:12'),(8,'jh1234 test 2','2024-04-03 11:33:21','2024-04-03 11:33:21');
/*!40000 ALTER TABLE `chat_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `montage_id` bigint NOT NULL,
  `content` varchar(100) NOT NULL,
  `reference_id` bigint DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_Comment_1` (`member_id`),
  KEY `FK_montage_TO_Comment_1` (`montage_id`),
  CONSTRAINT `FK_Member_TO_Comment_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_montage_TO_Comment_1` FOREIGN KEY (`montage_id`) REFERENCES `montage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'comment',NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(2,1,1,'cupid',1,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(3,2,1,'숨겨왔던',1,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(4,3,2,'더미데이터',NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(5,3,2,'이게 테스트야',NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(6,4,2,'hehe',3,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(7,5,3,'hehe',NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(8,4,4,'hehe',NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(9,4,4,'hehe',4,'2024-03-28 09:45:57','2024-03-28 09:45:57',0),(10,1,1,'ㅇㅎ',NULL,'2024-04-02 12:30:34','2024-04-02 12:30:34',0),(11,1,1,'ㅗㅓㅎ',NULL,'2024-04-02 12:30:48','2024-04-02 12:30:48',0),(12,1,1,'ㅘ',4,'2024-04-02 12:31:00','2024-04-02 12:31:00',0),(13,1,1,'ㅇㄴㄹㄴㅇㅇㄴ',NULL,'2024-04-02 12:31:13','2024-04-02 12:31:13',0),(14,1,1,'ㄴㅇㄹㄹㅇ',4,'2024-04-02 12:31:16','2024-04-02 12:31:16',0),(15,1,1,'ㄹㅇㄴㅇ',7,'2024-04-02 12:31:37','2024-04-02 12:31:37',0),(16,1,1,'ㄴㅇㄹㄴㅇ',NULL,'2024-04-02 12:31:50','2024-04-02 12:31:50',0),(17,1,1,'ㅇㄴㄹㄴ',NULL,'2024-04-02 12:32:54','2024-04-02 12:32:54',0),(18,1,1,'dg',NULL,'2024-04-02 12:50:10','2024-04-02 12:50:10',0),(19,1,1,'ㄴㄹㅇㅇㄴ',NULL,'2024-04-02 13:27:59','2024-04-02 13:27:59',0),(20,1,1,'ㅇㄴㄹㄴㅇ',NULL,'2024-04-02 13:33:11','2024-04-02 13:33:11',0),(21,1,1,'ㄴㅇㄹㄴ',NULL,'2024-04-02 13:33:59','2024-04-02 13:33:59',0),(22,1,1,'ㄴㅇㄹ',NULL,'2024-04-02 13:37:19','2024-04-02 13:37:19',0),(23,1,1,'ㅇㄴㄹ',NULL,'2024-04-02 13:37:23','2024-04-02 13:37:23',0),(24,1,1,'ㄴㅇㄹ',20,'2024-04-02 13:38:00','2024-04-02 13:38:00',0),(25,1,1,'ㄴㄹ',NULL,'2024-04-02 13:42:14','2024-04-02 13:42:14',0),(26,1,1,'ㅇㄴㄹ',1,'2024-04-02 13:42:20','2024-04-02 13:42:20',0),(27,1,1,'ㄴㅇㄹ',NULL,'2024-04-02 13:42:26','2024-04-02 13:42:26',0),(28,1,1,'ㅇㄴㄹ',1,'2024-04-02 13:42:31','2024-04-02 13:42:31',0),(29,1,1,'ㅇㄴㄹ',NULL,'2024-04-02 13:44:46','2024-04-02 13:44:46',0),(30,1,1,'ㄴㅇㄹㄴ',5,'2024-04-02 13:47:28','2024-04-02 13:47:28',0),(31,1,1,'ㅇㄴㄹㄹㅇㄴ',5,'2024-04-02 13:47:30','2024-04-02 13:47:30',0),(32,1,1,'ㄹㄹ',NULL,'2024-04-02 13:57:32','2024-04-02 13:57:32',0),(33,1,1,'sdfds',1,'2024-04-03 13:53:02','2024-04-03 13:53:02',0),(34,1,1,'sdfds',NULL,'2024-04-03 13:53:06','2024-04-03 13:53:06',0),(35,1,1,'sf',NULL,'2024-04-03 13:58:03','2024-04-03 13:58:03',0),(36,1,1,'dsfds',NULL,'2024-04-03 14:35:53','2024-04-03 14:35:53',0),(37,1,1,'sdffdsds',NULL,'2024-04-03 14:35:57','2024-04-03 14:35:57',0),(38,1,1,'sdfds',NULL,'2024-04-03 14:36:12','2024-04-03 14:36:12',0),(39,1,1,'sdffds',NULL,'2024-04-03 14:36:15','2024-04-03 14:36:15',0),(40,1,1,'sdffdsfds',NULL,'2024-04-03 14:36:20','2024-04-03 14:36:20',0),(41,1,1,'sddfsfsfdsfdsfdsdfsds',NULL,'2024-04-03 14:36:34','2024-04-03 14:36:34',0),(42,1,1,'sdffsdsfd',NULL,'2024-04-03 14:36:37','2024-04-03 14:36:37',0),(43,1,1,'sdffds',NULL,'2024-04-03 14:37:23','2024-04-03 14:37:23',0),(44,1,1,'dsfsd',NULL,'2024-04-03 14:37:27','2024-04-03 14:37:27',0),(45,1,1,'sdffsdsdf',NULL,'2024-04-03 14:38:20','2024-04-03 14:38:20',0),(46,1,1,'sdfsd',NULL,'2024-04-03 14:38:26','2024-04-03 14:38:26',0),(47,1,1,'dsdffds',NULL,'2024-04-03 14:43:53','2024-04-03 14:43:53',0),(48,1,1,'ㄹㄴㅇㅇㄴ',NULL,'2024-04-03 14:47:56','2024-04-03 14:47:56',0),(49,1,1,'ㄴㅇㄹ',NULL,'2024-04-03 14:48:02','2024-04-03 14:48:02',0),(50,1,1,'ㄴㅇㄹㄴㅇ',NULL,'2024-04-03 14:48:07','2024-04-03 14:48:07',0),(51,1,1,'ㄴㄹㅇㄹ',NULL,'2024-04-03 14:48:19','2024-04-03 14:48:19',0),(52,1,1,'ㄴㅇㄹㄴㅇ',7,'2024-04-03 14:52:34','2024-04-03 14:52:34',0),(53,1,1,'ㄴㅇㄹㄹㄴㅇㅇㄹㄴ',7,'2024-04-03 14:52:40','2024-04-03 14:52:40',0),(54,1,1,'ㄴㅇㄹㄹㄴㅇ',NULL,'2024-04-03 14:52:41','2024-04-03 14:52:41',0),(55,1,1,'ㄴㅇㄹㄹㄴㅇㅇ',1,'2024-04-03 14:52:47','2024-04-03 14:52:47',0),(56,1,1,'ㅇㄴㄹㄹㄴㅇ',NULL,'2024-04-03 14:52:51','2024-04-03 14:52:51',0),(57,1,1,'ㄴㅇㄹ',NULL,'2024-04-03 14:54:03','2024-04-03 14:54:03',0),(58,1,1,'ㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ',NULL,'2024-04-03 14:54:07','2024-04-03 14:54:07',0),(59,1,1,'ㄴㅇㄹㄴㅇㄴㅇㄹ',40,'2024-04-03 14:54:09','2024-04-03 14:54:09',0),(60,1,1,'ㄴㅇㄹㄴㅇㄴㄹㅇㅇㄴ',NULL,'2024-04-03 18:47:29','2024-04-03 18:47:29',0),(61,1,1,'ㄴㅇㄹㅇㄴㄴㅇㄹ',40,'2024-04-03 18:47:33','2024-04-03 18:47:33',0),(62,1,1,'ㅇㄹㄴ',NULL,'2024-04-03 18:47:44','2024-04-03 18:47:44',0),(63,1,1,'ㄴㅇㄴㅇㄹ',4,'2024-04-03 18:47:47','2024-04-03 18:47:47',0),(64,1,1,'ㅇㄴㄹㄴㅇ',10,'2024-04-03 18:49:19','2024-04-03 18:49:19',0),(65,1,1,'ㄷㄱ',7,'2024-04-03 19:19:06','2024-04-03 19:19:06',0),(66,1,1,'ㅂㅈㄷㄱ',7,'2024-04-03 19:19:10','2024-04-03 19:19:10',0),(67,1,1,'ㅂㅈㄷㄱ',7,'2024-04-03 19:19:13','2024-04-03 19:19:13',0),(68,1,1,'ㅂㄱㄷㅈ',NULL,'2024-04-03 19:19:15','2024-04-03 19:19:15',0),(69,1,1,'ㄱㄷㅈ',NULL,'2024-04-03 19:19:17','2024-04-03 19:19:17',0),(70,1,1,'ㄱㄷㅈ',NULL,'2024-04-03 19:19:18','2024-04-03 19:19:18',0),(71,1,1,'ㄱㄷㅈ',NULL,'2024-04-03 19:19:19','2024-04-03 19:19:19',0),(72,1,1,'ㄷㅈㄱ',NULL,'2024-04-03 19:19:21','2024-04-03 19:19:21',0),(73,1,1,'ㄷㅈㄱ',NULL,'2024-04-03 19:19:23','2024-04-03 19:19:23',0),(74,1,1,'ㅁㄴㅇㄹ',NULL,'2024-04-03 19:19:29','2024-04-03 19:19:29',0),(75,1,3,'ㅁㄴㅇ',NULL,'2024-04-03 19:27:38','2024-04-03 19:27:38',0),(76,1,1,'ㅁㄴㅇ',75,'2024-04-03 19:27:41','2024-04-03 19:27:41',0),(77,1,3,'ㅇㅇㅇ',75,'2024-04-03 19:28:02','2024-04-03 19:28:02',0),(78,1,3,'ㄹㅇ',NULL,'2024-04-03 19:54:08','2024-04-03 19:54:08',0),(79,1,3,'ㄹㄴㅇㄻㅁㄴㄹㅇㅁㄴㄹㄴㄹㄻ',75,'2024-04-03 19:54:13','2024-04-03 19:54:13',0),(80,1,3,'ㅊㅋ',7,'2024-04-03 19:58:28','2024-04-03 19:58:28',0),(81,1,4,'ㅓㅕㅕ',8,'2024-04-03 21:01:48','2024-04-03 21:01:48',0),(82,1,4,'ㅓㅓㅓ',NULL,'2024-04-03 21:01:51','2024-04-03 21:01:51',0),(83,1,1,'fdsfsd',1,'2024-04-03 22:04:24','2024-04-03 22:04:24',0),(84,1,2,'sdfsd',NULL,'2024-04-03 22:15:37','2024-04-03 22:15:37',0),(87,1,12,'ㅇㄹㄴㄹ',NULL,'2024-04-04 00:15:24','2024-04-04 00:15:24',0),(88,1,12,'ㄴㅇㄹㄴㄹ',87,'2024-04-04 00:15:26','2024-04-04 00:15:26',0),(89,1,12,'ㄴㅇㄹㄴㅇㄹ',87,'2024-04-04 00:15:29','2024-04-04 00:15:29',0),(90,1,1,'ㅋㅋ',NULL,'2024-04-04 00:15:30','2024-04-04 00:15:30',0),(91,1,12,'ㄴㅇㄹㄴㅇㄹㄴㅇ',NULL,'2024-04-04 00:15:33','2024-04-04 00:15:33',0),(92,1,12,'ㄴㅇㄹㄴㅇㄹㄴㅇ',NULL,'2024-04-04 00:15:36','2024-04-04 00:15:36',0),(93,1,1,'ㅇㅅㅇ',NULL,'2024-04-04 00:15:47','2024-04-04 00:15:47',0),(94,1,12,'ㄴㅇㄹㄴㅇ',87,'2024-04-04 00:16:27','2024-04-04 00:16:27',0),(95,1,12,'ㄴㄹㅇㄴ',87,'2024-04-04 00:17:12','2024-04-04 00:17:12',0),(96,1,12,'ㄴㅇㄹㄴㅇ',87,'2024-04-04 00:17:26','2024-04-04 00:17:26',0),(97,1,12,'ㄴㅇㄹㄴㅇㄹ',NULL,'2024-04-04 00:21:44','2024-04-04 00:21:44',0),(98,1,12,'ㄴㅇㄹㄴㅇㄹ',NULL,'2024-04-04 00:21:45','2024-04-04 00:21:45',0),(99,1,16,'와 너무 멋져요!',NULL,'2024-04-04 00:27:14','2024-04-04 00:27:14',0),(100,1,16,'최고에요!',99,'2024-04-04 00:27:20','2024-04-04 00:27:20',0),(101,1,16,'저도 농구 하고싶네요 !',NULL,'2024-04-04 00:49:04','2024-04-04 00:49:04',0),(102,1,15,'농구 너무 잘하시네요 !!',NULL,'2024-04-04 00:50:34','2024-04-04 00:50:34',0),(103,1,5,'댓댓',NULL,'2024-04-04 01:41:05','2024-04-04 01:41:05',0),(104,1,6,'대래대랫',NULL,'2024-04-04 01:41:13','2024-04-04 01:41:13',0),(105,1,6,'댓 걸',104,'2024-04-04 01:41:17','2024-04-04 01:41:17',0),(106,1,5,'댓댓',NULL,'2024-04-04 01:41:32','2024-04-04 01:41:32',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','init','SQL','V1__init.sql',-2094425552,'root','2024-03-28 09:45:57',821,1),(2,'2','insert dummy member','SQL','V2__insert_dummy_member.sql',-585983172,'root','2024-03-28 09:45:57',10,1),(3,'3','insert dummy other','SQL','V3__insert_dummy_other.sql',1305772626,'root','2024-03-28 09:45:57',20,1),(4,'4','followTable','SQL','V4__followTable.sql',-1077163778,'root','2024-03-28 09:45:57',69,1),(5,'5','add filename','SQL','V5__add_filename.sql',1062784785,'root','2024-03-28 09:45:57',168,1),(6,'6','alter member column','SQL','V6__alter_member_column.sql',-1885193497,'root','2024-03-28 09:45:57',52,1),(7,'7','alter report column','SQL','V7__alter_report_column.sql',-579614331,'root','2024-03-28 09:45:57',64,1),(8,'8','alter member column','SQL','V8__alter_member_column.sql',-2028266040,'root','2024-03-28 09:45:58',54,1),(9,'9','alter filename','SQL','V9__alter_filename.sql',1533057273,'root','2024-03-28 09:45:58',214,1),(10,'10','insert dummy recruit','SQL','V10__insert_dummy_recruit.sql',-1332699039,'root','2024-03-28 09:45:58',10,1),(11,'11','insert dummy member with stageName','SQL','V11__insert_dummy_member_with_stageName.sql',778345451,'user','2024-03-31 01:03:21',13,1),(12,'12','alter recruitmentTitle','SQL','V12__alter_recruitmentTitle.sql',-300736348,'user','2024-03-31 01:03:21',22,1),(13,'13','alter profile','SQL','V13__alter_profile.sql',1292365989,'root','2024-04-03 08:51:56',62,1),(14,'14','add member dummy data','SQL','V14__add_member_dummy_data.sql',-1868268746,'root','2024-04-03 09:04:44',35,1),(15,'15','add montage dummy data','SQL','V15__add_montage_dummy_data.sql',1163580745,'root','2024-04-03 18:02:30',15,1),(16,'16','insert dummy profile','SQL','V16__insert_dummy_profile.sql',451711781,'root','2024-04-03 18:24:17',10,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `following_id` bigint NOT NULL,
  `follower_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`following_id`,`follower_id`),
  KEY `FK_Member_TO_Follow_2` (`follower_id`),
  CONSTRAINT `FK_Member_TO_Follow_1` FOREIGN KEY (`following_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_Member_TO_Follow_2` FOREIGN KEY (`follower_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,2,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(1,3,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(1,4,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(1,5,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(2,1,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(2,4,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(21,5,'2024-04-03 21:03:05','2024-04-03 21:03:05'),(21,22,'2024-04-04 00:06:24','2024-04-04 00:06:24'),(34,22,'2024-04-04 00:33:06','2024-04-04 00:33:06');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_count`
--

DROP TABLE IF EXISTS `like_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_count` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `montage_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_montage_TO_Like_count_1` (`montage_id`),
  KEY `FK_Member_TO_Like_count_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Like_count_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_montage_TO_Like_count_1` FOREIGN KEY (`montage_id`) REFERENCES `montage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_count`
--

LOCK TABLES `like_count` WRITE;
/*!40000 ALTER TABLE `like_count` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_count` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `login_id` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `birth` varchar(100) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `stage_name` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `kakao_id` varchar(250) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `oauth_type` varchar(100) DEFAULT NULL,
  `saved_name` varchar(255) DEFAULT '',
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,NULL,'abcd@naver.com',NULL,'2023-03-01','F',NULL,NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',NULL,NULL,NULL,'',NULL),(2,NULL,'qwer@naver.com',NULL,'2023-02-01','M',NULL,NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',NULL,NULL,NULL,'',NULL),(3,NULL,'asdf@naver.com',NULL,'2022-02-01','F',NULL,NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',NULL,NULL,NULL,'',NULL),(4,NULL,'poiu@naver.com',NULL,'2021-02-01','F',NULL,NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',NULL,NULL,NULL,'',NULL),(5,NULL,'fjfjfj@naver.com',NULL,'2020-02-01','M',NULL,NULL,'2024-03-28 09:45:57','2024-03-28 09:45:57',NULL,NULL,NULL,'',NULL),(6,'admin602',NULL,NULL,NULL,NULL,NULL,NULL,'2024-03-28 21:06:39','2024-03-28 21:06:39',NULL,NULL,NULL,NULL,'$2a$10$T7AAe4T32/W25NXr7hN1lOxrf0w3TW8C0gP3AgfPLf3EfOlMXNrni'),(7,NULL,'aaaa@naver.com',NULL,'2024-07-06','F',NULL,'김씨','2024-03-31 01:03:21','2024-03-31 01:03:21',NULL,NULL,NULL,'',NULL),(8,NULL,'bbbb@naver.com',NULL,'2024-08-05','M',NULL,'이씨','2024-03-31 01:03:21','2024-03-31 01:03:21',NULL,NULL,NULL,'',NULL),(9,NULL,'cccc@naver.com',NULL,'2023-09-04','F',NULL,'이상해씨','2024-03-31 01:03:21','2024-03-31 01:03:21',NULL,NULL,NULL,'',NULL),(10,NULL,'dddd@naver.com',NULL,'2023-10-03','F',NULL,'김씨','2024-03-31 01:03:21','2024-03-31 01:03:21',NULL,NULL,NULL,'',NULL),(11,NULL,'eeee@naver.com',NULL,'2022-11-02','M',NULL,'최배달','2024-03-31 01:03:21','2024-03-31 01:03:21',NULL,NULL,NULL,'',NULL),(12,'kk','kkn1312@naver.com','010-5097-1234','2024-01-15',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/70e02220-cb0d-43bb-b836-ecd8559eb884_%EC%BA%A1%EC%B2%98.PNG','ksh','2024-04-02 12:04:47','2024-04-02 12:04:47',NULL,'김선혁',NULL,NULL,'$2a$10$wAwkTYLwOX6kXVebe9m1nO3L7RDVCLaDxRmO8H3qzrLFmpzDMWlVe'),(13,'cat','catisaw99@gmail.com','01090262394','1995-07-20',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/c08bec6c-a694-48dc-957a-1e08fd7792f7_xtest.PNG','히도','2024-04-03 07:18:28','2024-04-03 07:18:28',NULL,'김도희',NULL,NULL,'$2a$10$jR83.qn1y3oj0SdP2lBDjetOYeDTaa2trDCKqyN6Jf97MMON5ORiy'),(14,'dog','catisaw99@gmail.com','01090262394','1995-07-20',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/42fbe9ad-278b-46d4-b2ae-9a83fd320e9b_xtest.PNG','도리','2024-04-03 08:00:32','2024-04-03 08:00:32',NULL,'김도리',NULL,NULL,'$2a$10$mB/jRphBqUr2Ceu5Lj6dpOwVKO6T8DmqefSHRZNFEebZM.BWIln6q'),(15,'bjw','bjw@ssafy.com',NULL,'1999-01-11','M',NULL,'농구맨','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'배정우',NULL,'','1234'),(16,'bjh','bjh@ssafy.com',NULL,'1998-08-24','F',NULL,'배피티','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'배진현',NULL,'','1234'),(17,'sjh','sjh@ssafy.com',NULL,'1999-01-15','M',NULL,'송신','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'송정훈',NULL,'','1234'),(18,'psy','psy@ssafy.com',NULL,'1995-11-11','F',NULL,'싸피다니엘','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'박소영',NULL,'','1234'),(19,'ksh','ksh@ssafy.com',NULL,'1999-01-31','M',NULL,'볼링맨','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'김선혁',NULL,'','1234'),(20,'kdh','kdh@ssafy.com',NULL,'1996-07-20','F',NULL,'춤신춤왕','2024-04-03 09:04:44','2024-04-03 09:04:44',NULL,'김도희',NULL,'','1234'),(21,'1','kkn1312@naver.com','010-1234-5678','2024-02-13',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/fa78a67f-6824-42e4-bfab-87d98341b5c4_%EC%BA%A1%EC%B2%98.PNG','boot','2024-04-03 09:11:28','2024-04-03 09:11:28',NULL,'김',NULL,NULL,'$2a$10$fZk2u7d.6pNFMiJXJLM5IeHLXtualnoyVQWM.nryMjytqvR3AsGYK'),(22,'profile1','sadf@gmail.com','010-1245-5335','2024-04-01',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/4e76ddef-6b7b-47ef-ad2e-ac802603b744_%EA%B9%80%EB%AF%BC%EA%B2%BD.png','김민경','2024-04-03 22:30:54','2024-04-03 22:30:54',NULL,'김민경',NULL,NULL,'$2a$10$oTyYwYdfRWsqP24kW/si6eWiHTyVw338vFyAkKvNcDX3sPFGm3XEe'),(23,'9876','9876@naver.com','010-3074-3511','2024-04-25',NULL,'','9876','2024-04-03 22:31:26','2024-04-03 22:31:26',NULL,'9876',NULL,NULL,'$2a$10$2xe6RAyJGkk6588SbzuIQO9/nBk49I4fcYqwa6PPoJdxqhJhr08C.'),(24,'profile2','afewo@gmail.com','010-6334-5341','2024-04-02',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/6e3b8832-1141-4e5c-bc77-f182c491330c_%EA%B9%80%EB%AF%BC%ED%99%98.jpg','김민환','2024-04-03 22:35:59','2024-04-03 22:35:59',NULL,'김민환',NULL,NULL,'$2a$10$htV3z3uG6XoaVGZk.BELv.N1A7k3EFcjp0hQyBNYBko2.hwHZb2l6'),(25,'profile3','flk@gmail.com','010-2624-4753','2024-04-03',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/0b9bf48c-22d7-43e3-b46b-b66584f3d3ee_%EA%B9%80%EC%9D%B4%EC%97%B0.jpg','김이연','2024-04-03 23:04:35','2024-04-03 23:04:35',NULL,'김이연',NULL,NULL,'$2a$10$6XCTjSTNxPjz39jrt0InVOdc8n9rsNe71YoUM3DvhwNS/GQTQsTWm'),(26,'profile4','dfsa@gmail.com','010-5463-7766','2024-04-04',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/b4d5b484-f303-4b04-a065-5ab1ba05ae09_%EA%B9%80%EC%A2%85%ED%98%84.png','김종현','2024-04-03 23:28:03','2024-04-03 23:28:03',NULL,'김종현',NULL,NULL,'$2a$10$VMElBKdw93JAbD9kWiDHOeVcAKf/UKDOeQu16WUNJGuRoeGDU4H.O'),(27,'profile5','joi@gmail.com','010-3335-7543','2024-04-05',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/c1823da2-e43f-4328-838b-3db56f227795_%EA%B9%80%ED%95%B4%EC%9B%85.png','김해웅','2024-04-03 23:29:54','2024-04-03 23:29:54',NULL,'김해웅',NULL,NULL,'$2a$10$kMZTN585BYZer.svpusBCOsfhsw6B2g7s2nEDe61AqkSQQbY3Z5cC'),(28,'profile6','gdinogo@naver.com','010-2242-7775','2024-04-06',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/94a0fa9e-e637-4e7b-be13-2dc4f1c48704_%EB%82%A8%EC%A7%80%EC%88%98.png','남지수','2024-04-03 23:32:50','2024-04-03 23:32:50',NULL,'남지수',NULL,NULL,'$2a$10$CUmkU.7WSzVbKhyqrLYs8.IYekp6bn72y.FcwQpVb2npkekNvBoSq'),(29,'profile7','jnkjn@naver.com','010-8756-3653','2024-04-07',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/5f24af99-4265-4e62-a2f6-9a6c9cee3948_%EB%91%90%EC%9C%A4%EB%B9%84.jpg','두윤비','2024-04-03 23:34:21','2024-04-03 23:34:21',NULL,'두윤비',NULL,NULL,'$2a$10$iUOHYulYL.miPz5SojAli.sjZOQ1XQOGXUJZtLfaTnIw8e29eF.wO'),(30,'profile8','niu@gmail.com','010-6546-8827','2024-04-08',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/968bef0c-c319-4ef0-9a31-a0c1d9bdfe58_%EB%AF%BC%EC%B1%84%EC%95%84.jpg','민채아','2024-04-03 23:35:54','2024-04-03 23:35:54',NULL,'민채아',NULL,NULL,'$2a$10$vo8AjdNRcecsAW1zKQ0EouluhvvUA9iMklvrYDq5ZjIXaBKQp.4CW'),(31,'profile9','bttb@gmail.com','010-6346-6363','2024-04-09',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/4877a43c-ef23-43a8-bc38-8274a98ad82c_%EB%B0%95%EC%84%B1%EC%9A%B0.png','박성우','2024-04-03 23:37:58','2024-04-03 23:37:58',NULL,'박성우',NULL,NULL,'$2a$10$wyznxFA4F4yG7dxUHll0wOueUkhB7LYNxZMuUc/Vkq0LhiH28iH1q'),(32,'profile10','fffw@asdf.aaa','010-6367-8594','2024-04-10',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/16a05ff9-e4cb-47a8-b164-3bca82c349b5_%EB%B0%95%ED%98%9C%EC%98%81.png','에여엉','2024-04-03 23:40:49','2024-04-03 23:40:49',NULL,'박혜영',NULL,NULL,'$2a$10$Xw1z9qLimjgZlZpKuCCdwu0jYflqfZTgEoJIvFPhPWw7NZZ8q0klm'),(33,'profile11','ggg@naver.com','010-6362-1112','1988-04-12',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/f17d8d39-31e5-46ef-8662-45c296257319_%EC%8B%A0%EC%A7%80%ED%98%84.jpg','신지현','2024-04-03 23:47:16','2024-04-03 23:47:16',NULL,'신지현',NULL,NULL,'$2a$10$1o8aSqueUOYnAB7DSso3x.EWFl5bbuz6BamKkLOs47kHG..WTiSxm'),(34,'actor602','kkn1312@naver.com','010-1684-5212','1999-01-11',NULL,'https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/f3e0b6b5-9cbc-4c03-8983-607fad010dab_%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%281%29.jpg','배우','2024-04-04 00:28:34','2024-04-04 00:28:34',NULL,'김배우',NULL,NULL,'$2a$10$TKQhrWHQYNdRPBS7rZbZCugRzumht4xxegzVxt3YodqgZNeqGM.yG');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `montage`
--

DROP TABLE IF EXISTS `montage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `montage` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `title` varchar(50) NOT NULL,
  `link` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `saved_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_montage_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_montage_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `montage`
--

LOCK TABLES `montage` WRITE;
/*!40000 ALTER TABLE `montage` DISABLE KEYS */;
INSERT INTO `montage` VALUES (1,1,'title-test','lojskdfnkdf','2024-03-28 09:45:57','2024-03-28 09:45:57',''),(2,1,'title-tessdfsdft','lojskdddddfnkdf','2024-03-28 09:45:57','2024-03-28 09:45:57',''),(3,2,'titlesdfsdf-test','dddddlojskdfnkdf','2024-03-28 09:45:57','2024-03-28 09:45:57',''),(4,3,'iu','dddddlojskdfnkdf','2024-03-28 09:45:57','2024-03-28 09:45:57',''),(5,4,'ty','dddddlojskdfnkdf','2024-03-28 09:45:57','2024-03-28 09:45:57',''),(6,12,'[농구영상] 농구 연기입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/a53d9e90-9e0e-4c09-87a9-063c18d650ea_%EB%B0%B0%EC%A0%95%EC%9A%B0-%EB%86%8D%EA%B5%AC%EC%98%81%EC%83%81.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(7,12,'[농구영상] 농구 연기단입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/d45313fd-3cde-4afa-b485-cb99ac4eebf3_%EB%B0%B0%EC%A0%95%EC%9A%B0-%EB%86%8D%EA%B5%AC%EB%8B%A8.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(8,14,'[청춘시대] 연기입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7c9cdb8e-5427-4c4f-bc41-97591021e153_%EC%86%A1%EC%A0%95%ED%9B%88-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(9,15,'[청춘시대] 청춘시대 연기입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7c18a985-3757-47a4-ab87-b6a49445f335_%EA%B9%80%EC%84%A0%ED%98%81-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(10,17,'[청춘시대] 연기 영상입니다. 많은 관심 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/05e2adc6-6c17-4809-9a0b-27cbca09a4ea_%EB%B0%95%EC%86%8C%EC%98%81-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(11,17,'[개인 영상] 지망생 연기 영상입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7a26f307-b93a-4f7e-aeb0-734a5e792f55_%EB%B0%95%EC%86%8C%EC%98%81-%EC%A7%80%EB%A7%9D%EC%83%9D.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(12,13,'[개인] 제 키보드입니다. 많은 연락 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/ae564c52-1b38-49e6-9baf-6b8a8e49fced_%EB%B0%B0%EC%A7%84%ED%98%84-%EB%82%B4%ED%82%A4%EB%B3%B4%EB%93%9C.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(13,13,'[개인] 제 필통입니다. 잘 봐주세요','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/9654fb23-5122-4e64-a619-f6a71ce16a25_%EB%B0%B0%EC%A7%84%ED%98%84-%EB%82%B4%ED%95%84%ED%86%B5.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(14,16,'[개인] 펭돌이입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/57a9b34e-3176-464f-8edd-7df90cd1c363_%EC%9E%A0%EC%9E%90%EB%8A%94%ED%8E%AD%EA%B7%84.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(15,16,'[개인] 기프트카드입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/3189e83c-f41b-4a18-8fe0-09d894b837ea_%EC%82%AC%EB%9E%8C%EC%9D%80%EC%B1%85%EC%B1%85%EC%9D%80%EC%82%AC%EB%9E%8C.mp4','2024-04-03 09:04:44','2024-04-03 09:04:44',''),(16,11,'[농구영상] 농구 연기입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/a53d9e90-9e0e-4c09-87a9-063c18d650ea_%EB%B0%B0%EC%A0%95%EC%9A%B0-%EB%86%8D%EA%B5%AC%EC%98%81%EC%83%81.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(17,11,'[농구영상] 농구 연기단입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/d45313fd-3cde-4afa-b485-cb99ac4eebf3_%EB%B0%B0%EC%A0%95%EC%9A%B0-%EB%86%8D%EA%B5%AC%EB%8B%A8.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(18,13,'[청춘시대] 연기입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7c9cdb8e-5427-4c4f-bc41-97591021e153_%EC%86%A1%EC%A0%95%ED%9B%88-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(19,14,'[청춘시대] 청춘시대 연기입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7c18a985-3757-47a4-ab87-b6a49445f335_%EA%B9%80%EC%84%A0%ED%98%81-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(20,16,'[청춘시대] 연기 영상입니다. 많은 관심 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/05e2adc6-6c17-4809-9a0b-27cbca09a4ea_%EB%B0%95%EC%86%8C%EC%98%81-%EC%B2%AD%EC%B6%98%EC%8B%9C%EB%8C%80.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(21,16,'[개인 영상] 지망생 연기 영상입니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/7a26f307-b93a-4f7e-aeb0-734a5e792f55_%EB%B0%95%EC%86%8C%EC%98%81-%EC%A7%80%EB%A7%9D%EC%83%9D.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(22,12,'[개인] 제 키보드입니다. 많은 연락 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/ae564c52-1b38-49e6-9baf-6b8a8e49fced_%EB%B0%B0%EC%A7%84%ED%98%84-%EB%82%B4%ED%82%A4%EB%B3%B4%EB%93%9C.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(23,12,'[개인] 제 필통입니다. 잘 봐주세요','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/9654fb23-5122-4e64-a619-f6a71ce16a25_%EB%B0%B0%EC%A7%84%ED%98%84-%EB%82%B4%ED%95%84%ED%86%B5.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(24,15,'[개인] 펭돌이입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/57a9b34e-3176-464f-8edd-7df90cd1c363_%EC%9E%A0%EC%9E%90%EB%8A%94%ED%8E%AD%EA%B7%84.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30',''),(25,15,'[개인] 기프트카드입니다. 잘 부탁드립니다.','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/montages/3189e83c-f41b-4a18-8fe0-09d894b837ea_%EC%82%AC%EB%9E%8C%EC%9D%80%EC%B1%85%EC%B1%85%EC%9D%80%EC%82%AC%EB%9E%8C.mp4','2024-04-03 18:02:30','2024-04-03 18:02:30','');
/*!40000 ALTER TABLE `montage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` bigint NOT NULL,
  `receiver_id` bigint NOT NULL,
  `content` varchar(100) NOT NULL,
  `is_read` char(1) NOT NULL DEFAULT 'F',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_Note_1` (`sender_id`),
  KEY `FK_Member_TO_Note_2` (`receiver_id`),
  CONSTRAINT `FK_Member_TO_Note_1` FOREIGN KEY (`sender_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_Member_TO_Note_2` FOREIGN KEY (`receiver_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants`
--

DROP TABLE IF EXISTS `participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participants` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chat_room_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_chat_room_TO_participants_1` (`chat_room_id`),
  KEY `FK_Member_TO_participants_1` (`member_id`),
  CONSTRAINT `FK_chat_room_TO_participants_1` FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`),
  CONSTRAINT `FK_Member_TO_participants_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants`
--

LOCK TABLES `participants` WRITE;
/*!40000 ALTER TABLE `participants` DISABLE KEYS */;
INSERT INTO `participants` VALUES (1,1,7,'2024-04-03 07:39:20','2024-04-03 07:39:20'),(2,1,10,'2024-04-03 07:39:20','2024-04-03 07:39:20'),(3,3,8,'2024-04-03 08:19:37','2024-04-03 08:19:37'),(4,3,7,'2024-04-03 08:19:37','2024-04-03 08:19:37'),(5,4,8,'2024-04-03 08:24:17','2024-04-03 08:24:17'),(6,4,7,'2024-04-03 08:24:17','2024-04-03 08:24:17'),(7,5,10,'2024-04-03 08:28:30','2024-04-03 08:28:30'),(8,5,7,'2024-04-03 08:28:30','2024-04-03 08:28:30'),(10,7,8,'2024-04-03 11:27:13','2024-04-03 11:27:13'),(11,7,13,'2024-04-03 11:27:13','2024-04-03 11:27:13'),(12,8,10,'2024-04-03 11:33:21','2024-04-03 11:33:21'),(13,8,7,'2024-04-03 11:33:21','2024-04-03 11:33:21'),(14,8,8,'2024-04-03 11:33:21','2024-04-03 11:33:21'),(15,8,13,'2024-04-03 11:33:21','2024-04-03 11:33:21');
/*!40000 ALTER TABLE `participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profile` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` bigint NOT NULL,
  `content` text NOT NULL,
  `type` char(1) NOT NULL DEFAULT 'A',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `portfolio` varchar(100) DEFAULT '',
  `private_post` varchar(1) NOT NULL DEFAULT 'F',
  `saved_name` varchar(255) DEFAULT '',
  `image` varchar(255) DEFAULT '',
  `image_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_profile_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_profile_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` VALUES (2,5,'Hi guys I\'m soyoung PD 나는 소영 피디다','P','2024-03-10 01:42:08','2024-03-10 01:42:08','porfFF.pdf','F',NULL,NULL,NULL),(3,4,'Hi guys I\'m sohee 나는 소희다','A','2024-03-10 01:42:08','2024-03-10 01:42:08','soheePorfolio.pdf','F',NULL,NULL,NULL),(4,3,'Hi guys I\'m daniel 나는 다니엘이다.','A','2024-03-10 01:42:08','2024-03-10 01:42:08','daniPorfolio.pdf','F',NULL,NULL,NULL),(5,5,'Hi guys I\'m soyoung actor 나는 소영 배우다','A','2024-03-10 01:42:08','2024-03-10 01:42:08','porfFF.pdf','F',NULL,NULL,NULL),(8,22,'대배우 꿈꾸는 김민경이에요','P','2024-04-03 22:33:43','2024-04-03 22:33:43','http://fa.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/0e2e2b02-e6c2-465e-8597-28ddb107fb57_%EA%B9%80%EB%AF%BC%EA%B2%BD.png','0e2e2b02-e6c2-465e-8597-28ddb107fb57_김민경.png'),(9,24,'안녕하세요 신인배우 김민환입니다.','P','2024-04-03 22:37:06','2024-04-03 22:37:06','http://gior.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/a98af282-c1ff-4d6e-b398-9d6b18d6e7a3_%EA%B9%80%EB%AF%BC%ED%99%98.jpg','a98af282-c1ff-4d6e-b398-9d6b18d6e7a3_김민환.jpg'),(10,25,'김이연입니다.','P','2024-04-03 23:05:38','2024-04-03 23:05:38','http://yter.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/02a91533-f5f4-43aa-9594-936a45d215b3_%EA%B9%80%EC%9D%B4%EC%97%B0.jpg','02a91533-f5f4-43aa-9594-936a45d215b3_김이연.jpg'),(11,27,'신인배우 찾아요','P','2024-04-03 23:30:50','2024-04-03 23:30:50','http://awfejoi.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/0cbdfdb1-a9dc-441c-a51f-729b357e40df_%EA%B9%80%ED%95%B4%EC%9B%85.png','0cbdfdb1-a9dc-441c-a51f-729b357e40df_김해웅.png'),(12,31,'성우에요','P','2024-04-03 23:39:24','2024-04-03 23:39:24','http://awef.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/47e26e93-c7ed-451d-89a7-3f372cc07455_%EB%B0%95%EC%84%B1%EC%9A%B0.png','47e26e93-c7ed-451d-89a7-3f372cc07455_박성우.png'),(13,32,'안녕하세요 졸려요','P','2024-04-03 23:42:24','2024-04-03 23:42:24','edu.ssafy.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/a1520945-2d2f-4337-8614-e8d84b10531f_%EB%B0%95%ED%98%9C%EC%98%81.png','a1520945-2d2f-4337-8614-e8d84b10531f_박혜영.png'),(14,33,'나이가 있어요','P','2024-04-03 23:48:11','2024-04-03 23:48:11','http://bbb.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/69bb8795-429b-4662-b231-bc0caf30200a_%EC%8B%A0%EC%A7%80%ED%98%84.jpg','69bb8795-429b-4662-b231-bc0caf30200a_신지현.jpg'),(15,33,'감독 겸 배우에오','A','2024-04-03 23:49:45','2024-04-03 23:49:45','http://g3g3.com','F','','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/profiles/36a3be8f-d1cf-4d07-bfad-34178585907b_%EC%8B%A0%EC%A7%80%ED%98%84.jpg','36a3be8f-d1cf-4d07-bfad-34178585907b_신지현.jpg');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recruitment`
--

DROP TABLE IF EXISTS `recruitment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recruitment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `member_id` bigint NOT NULL,
  `category` varchar(10) NOT NULL,
  `image` varchar(255) DEFAULT '',
  `start_date` varchar(20) NOT NULL,
  `end_date` varchar(20) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `private_recruitment` varchar(1) DEFAULT 'F',
  `image_name` varchar(255) DEFAULT '',
  `file` varchar(255) DEFAULT '',
  `file_name` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_Recruitment_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Recruitment_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recruitment`
--

LOCK TABLES `recruitment` WRITE;
/*!40000 ALTER TABLE `recruitment` DISABLE KEYS */;
INSERT INTO `recruitment` VALUES (1,'Title1','Content1',1,'Category1','Image1','2024-03-26','2024-04-26','2024-03-28 09:45:58','2024-03-28 09:45:58','F','ImageName1','File1','FileName1'),(2,'Title2','Content2',2,'Category2','Image2','2024-03-27','2024-04-27','2024-03-28 09:45:58','2024-03-28 09:45:58','F','ImageName2','File2','FileName2'),(3,'Title3','Content3',3,'Category3','Image3','2024-03-28','2024-04-28','2024-03-28 09:45:58','2024-03-28 09:45:58','F','ImageName3','File3','FileName3'),(4,'Title4','Content4',4,'Category4','Image4','2024-03-29','2024-04-29','2024-03-28 09:45:58','2024-03-28 09:45:58','F','ImageName4','File4','FileName4'),(5,'Title5','Content5',5,'Category5','Image5','2024-03-30','2024-04-30','2024-03-28 09:45:58','2024-03-28 09:45:58','F','ImageName5','File5','FileName5'),(8,'[급구합니다]','집 : 4명',21,'뮤비/CF','https://s-learner-bucket.s3.ap-northeast-2.amazonaws.com/recruits/c2bf07e8-0ba9-45f3-9e33-c0fd9d867886_%EC%BA%A1%EC%B2%98.PNG','2024-04-01','2024-04-24','2024-04-03 20:15:28','2024-04-03 20:21:50','F','c2bf07e8-0ba9-45f3-9e33-c0fd9d867886_캡처.PNG','',''),(9,'외국어 채널 쇼츠 영상 제작자(영어, 일어) 모십니다! 인쇄','안녕하세요, 과학/공학 유튜브 채널 긱블에서 ( https://www.youtube.com/@geekblekr ) 해외채널을 담당해주실 외국어 가능한 PD님을 모십니다. (영어, 일본어) 저희 긱블 채널은 다년간 과학/공학을 소재를 하는 콘텐츠로 국내 시청자들에게 많은 사랑을 받아 왔습니다. 앞으로는 국내를 넘어 글로벌 시청자들에게 긱블의 콘텐츠를 소개하고 긱블 팬층을 확장하고자 합니다. 이를 위해 한국어로 제작된 긱블의 기존 콘텐츠들을 영어 및 일본어로 제작하여 글로벌 채널 전용 콘텐츠를 편집해주실 PD님을 모십니다. 해당 언어에 능통하시고, 해당 언어 문화권에 익숙한 분이시면 좋겠습니다. (현지 생활 경험 있으신 분 우대) 모집 인원 : 영어 편집자 2명 일본어 편집자 2명 업무 내용 : 기존에 발행된 긱블 롱폼 영상의 원본 소스 및 프로젝트를 제공해 드리면 해당 영상에서 해외 시청자들에게 바이럴 될만한 포인트를 잡아내 외국어 쇼츠로 제작해주시면 됩니다. (자막, 필요시 내레이션 등) 제작 주기는 주 3편 제작입니다. 작업물 예시 : https://www.youtube.com/@geekble 협업 금액 : 기본급 편당 10만원 (ex. 월 12회 -> 120만원) + 조회수 및 구독자 증가에 따른 인센티브 (ex. 월간 종합 조회수 500만 달성 시 100만원) + 외국어 채널에서 구독자가 붙고 조회수 수익이 발생하면 수익은 5:5로 쉐어해드릴 예정입니다. (ex. 조회수 수익 1000만원 발생 -> 500만원 지급) 한국에서 120만 구독자를 확보한 긱블 채널의 검증된 콘텐츠들을 활용하고, 과학/공학이라는 국경이 없는 주제를 소재로 하는 콘텐츠이기 때문에 해외에서도 분명히 성공할 수 있다고 보고 있습니다. 단순한 외주 개념이 아닌 긱블 해외 채널을 성공시키고 그 열매를 함께 나누실 파트너분을 모시고 싶습니다. [필름메이커스/(영어or일본어)/ㅇㅇㅇ(성함)] 제목으로 간단한 경력 및 포트폴리오 첨부하여 이메일 (leeminsuk@geekble.kr)지원 부탁드립니다. 감사합니다!',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:21:07','2024-04-03 21:21:07',NULL,NULL,NULL,NULL),(10,'상업영화 <비공개> 인쇄','안녕하세요. 저희는 언주역에 위치한 제작사입니다 제작부장님과 제작팀원, 회계팀원을 구하고 있습니다.  - 제작부장 : 상업 영화 경력자, 운전면허1종 보유자 -제작팀원 :상업영화경력자 운전면허1종보유자 - 회계 : 상업 영화 경력자  * 급여 : 미팅시 협의 * 성별/나이 무관 * 사무실 위치 : 언주역 * 참여 기간 : 24년 4월 ~ 24년 7월 (예정) * 모집분야 : 제작부장1인/ 회계1인 * 이메일 지원 : birdco.kr@gmail.com 이력서 및 포트폴리오(선택사항) 예) 제작부장 지원 – 000 / 회계 지원 – 000 <영화 정보> - 제 목: 비공개 -장 르: 서스펜스 블랙코미디 (호러코미디) - 감 독: 확정 (미팅 시 공개) - 주 연: 확정 (미팅 시 공개)   좋은 분들과 같이 좋은 작품으로 만나뵈었으면 좋겠습니다^^ 많은 지원 부탁드리겠습니다',1,'장편영화',NULL,'2024-03-18 11:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(11,'프로젝트 파인에서 의상팀 추가 구인합니다 인쇄','안녕하세요~ 이제 막 촬영에 들어간 작품이고, 추가 인원을 1명 더 구인하고 있습니다~ 경력있는 분으로 찾고 있고, 운전 가능하면 더 좋습니다~! 메일로 지원바랍니다, 감사합니다~',1,'장편영화',NULL,'2024-03-16 21:03','2024-04-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(12,'현재 준비중인 영화 의상팀 모집합니다. 인쇄','안녕하세요. 현재 준비중인 상업영화 <계시록> 의상팀 모집합니다.  기간은 프리프러덕션 현재~ 4월초 / 프러덕션 4월~6월 입니다.  이력서 보내주시면 검토후 개별 연락 드리겠습니다. 많은 관심 부탁드립니다. 감사합니다.',1,'장편영화',NULL,'2024-03-16 20:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(13,'상업영화 의상팀원 구인합니다. 인쇄','안녕하세요. 현재 준비중인 상업영화의 의상팀원을 모십니다.  * 프리프로덕션 : 4월 * 프로덕션(촬영기간) : 5월 초 - 7월 예정 (약 두달 예정) * 급여 : 경력에 따라 미팅시 협의 예정 * 작업실 위치 : 재택 근무 및 혜화역 근처 (4호선)  저희와 함께 하실 팀원 충원하려고 합니다. 경력에 따라 페이는 협의될 예정이며, 편하게 메일로 지원 부탁드립니다.  사진이 첨부된 이력서에 운전 가능 유/무 와 출근시작 가능한 날짜 적어서 보내주시면 감사하겠습니다.  /담당자 이메일 : kheee0326@gmail.com /담당자 연락처 : 010-9288-6561  아무때나 괜찮으니 이력서 보내주신 후, 위 연락처로 연락 남겨주시면 감사하겠습니다!  ',1,'장편영화',NULL,'2024-03-25 15:03','2024-05-01','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(14,'영화팀에서 조명팀원 구합니다 인쇄','영화팀에서 조명팀원 모집합니다  저희 팀은 영화,ott,광고,뮤비 촬영등 다양한 분야에사 활동하는 팀으로 여러 현장에서 다양한 경험을 쌓을 수 있는 팀입니다. 경력이 없으셔도 좋습니다 배우려는 의지가 있으신분, 탑차 운전 가능하신분(탑차 1톤2.5톤3.5톤) 이메일이나 연락처로 이력서 제출해주시면 됩니다 부담없이 연락주시면 순서대로 연락드리겠습니다 많은 연락 부탁드립니다~!',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(15,'영화 미술팀원 구인~! 인쇄','잘하는 미술팀원 구인  작품정보는 면접시 공개 하겠습니다.  기간 : 2024년 6월 중순까지  페이: 협의 후 (제작사 기준에 준함)  까칠하신분 정중히 사양합니다. 유쾌하신분 격하게 환영합니다.  지원은 이메일로만 받겠습니다.  포토폴리오 꼭 보내주세요. ',1,'장편영화',NULL,'2024-03-15 17:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(16,'상업 영화 [먼 훗날 우리]팀원 충원합니다. 인쇄','안녕하세요. 영화 및 드라마 소품회사 \'STUFF\' 입니다.  3월부터 준비중에 있는 상업영화 [먼 훗날 우리] 작품에 참여 가능한 팀원을 구합니다.  프리프러덕션 기간 중에는 사무실에서 데스크 작업과 시장조사, 창고를 오가는 업무가 진행될 예정이고, 프러덕션 기간 중에는 사무실, 창고, 촬영 현장을 오가는 업무가 진행될 예정입니다.  ========================================================================= [ 모집 내용 ] _ 모집인원 : 현장팀장 1명(마감) , 팀원1명-2명 (모집중) _ 모집분야 : 현장 진행, 세팅/철수 진행, 차량 운행 _ 모집 요건 : 실 운전 가능하신 분 (카니발, 스타렉스, 탑차 등)  [ 업무 기간 ] 2024.3월 중 출근일자 ~ 2024. 06월 경 _ 프리프러덕션 : 출근 시점 ~ 4월 중순 _ 프러덕션 : 2024.04월 중순 (크랭크인) ~ 2024.06월 중순 (크랭크업-현재 시점 기준) _ 촬영 환경이나 날씨에 의해 크랭크업에 영향이 있을 경우, 업무 종료 일정은 변동이 있을 수 있고, 사전에 공지하여 협의할 수 있도록 하겠습니다. [ 그 외 ] _ 사무실 위치 : 서울 마포구 성산동 (경의선 가좌역, 6호선 마포구청역 인근 입니다) _ 첨부 파일 : 이력서, 포트폴리오 ( * 포트폴리오는 선택사항 입니다) _ 이력서 내 활용 가능한 프로그램 기재 _ 실제 차량 운행이 가능해야 합니다. (면허취득일 기입 필수, 실제 운행했던 차종 기입)  =========================================================================  이메일로 이력서 발송하신 후 문자 발송 꼭~ 부탁드립니다. 인원 충원 후, 모집 종료 공지하겠습니다.  긴 글 읽어주셔서 감사합니다.',1,'장편영화',NULL,'2024-03-14 17:03','2024-03-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(17,'[모터헤드 주식회사] VFX 본부 경력직 채용 인쇄','',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(18,'저예산 상업 장편 학원 액션물 제작팀 구합니다. (마감) 인쇄','함께 즐겁게 영화를 만들 학원 액션물 저예산 상업 장편 연출팀 모집합니다. 4월 1째주 프리, 4월 4째주 크랭크인, 프로덕션까지 함께 할 팀원을 모집합니다.  4월 프리기간 - 대본리딩, 로케헌팅 등 사전 스케줄 참여 4월 4째 주 - 5월 1째 주 총 8회차 촬영 참여 이메일로 제목 맞춰 이력서와 포트폴리오 보내주시면 감사하겠습니다.  제목 [학원액션물 / 제작팀 / 이름 / 번호] 필수내용 - [이력서첨부, 1종 차량 실운전 가능 여부, 간략한 자기소개]',1,'장편영화',NULL,'2024-03-21 10:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(19,'상업영화 <비공개> 인쇄','- 제작부장 : 상업 영화 경력자, 운전면허1종 보유자  - 회계 : 상업 영화 경력자      * 급여 : 미팅시 협의  * 성별/나이 무관  * 사무실 위치 : 언주역  * 참여 기간 : 24년 4월 ~ 24년 7월 (예정)  * 모집분야 : 제작부장1인/ 회계1인  이력서 및 포트폴리오(선택사항)  예) 제작부장 지원 – 000 / 회계 지원 – 000    <영화 정보>  - 제 목: 비공개  - 감 독: 확정 (미팅 시 공개)  - 주 연: 확정 (미팅 시 공개)    현재 주연, 조연캐스팅완료/투자완료/프리진행중  면접시 작품 내용 및 페이 협의  좋은 분들과 같이 좋은 작품으로 만나뵈었으면 좋겠습니다^^  많은 지원 부탁드리겠습니다',1,'장편영화',NULL,'2024-03-14 10:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(20,'스릴러 장편상업영화 제작팀 구인합니다. 인쇄','안녕하세요. 현재 장편 상업영화 프리 진행중이며, 기본적인 상업영화 기준 로케이션 헌팅 및 제반정리 / 준비 등에 대하여 1~2작품 이상 진행해보신 분들에 한하여 구인하고 있습니다. 크랭크인은 24.05.20일 예정이며, 주 로케이션 헌팅지는 부산지역으로 진행예정에 있습니다.  유쾌하고 재밌는분들 환영합니다-!',1,'장편영화',NULL,'2024-03-14 10:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(21,'장편 상업 영화 미술 팀장,팀원 현장 진행 충원 합니다. 인쇄','함께 현장 진행할 미술 팀장,팀원을 충원 합니다. 장르는 스릴러로 현제 시대 입니다.  ▶모집인원 미술팀장 : 상업영화 미술팀장으로 1편 이상경험이 있으시거나 5~6편 이상 경력자 우대 경력 미술팀원 : 미술팀원 으로 1~3편 정도 참여한 분 (세트 디자인 및 현장 진행 유경험자 운전가능자 우대) 신입 미술팀원 : 포토 일러 스케치업 가능자 우대(스타렉스등 운전 가능한분)  ▶참여기간 프리프로덕션 : 2024년 3월~ 4월 초 프로덕션 : 2024년 4월 초 에서 6월 (아직 자세한 일정은 정해지지 않았습니다.) 40여회차 내외입니다.  ▶지원 메일 beast202401@gmail.com 으로 이력서와 포트폴리오 보내 주시면 감사하겠습니다~. (이름, 나이, 성별, 주소, 전화번호 ) - 필수 기입 포트폴리오는 본인이 작업했던거 위주로만 자유로운 형식으로 보내주시면 되고, 메일로 보내주시면 연락 드리겠습니다~. 급여는 경력에 따른 차등 지급 입니다.  함께 화이팅 하며 작업 하실 분들의 많은 참여 부탁 드립니다~.',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(22,'영화 의상팀장과 드라마팀원 구합니다 인쇄','의상팀장과 팀원을 모집합니다 이력서 메일로 보내주시면 연락드리겠습니다 자세한 사항은 면접시 의논드리겠습니다 감사합니다',1,'장편영화',NULL,'2024-03-12 16:03','2024-03-16','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(23,'상업 영화 [먼 훗날 우리]소품 팀장,팀원 충원합니다. 인쇄','안녕하세요. 영화 및 드라마 소품회사 \'STUFF\' 입니다.  3월부터 준비중에 있는 상업영화 [먼 훗날 우리] 작품에 참여 가능한 팀장님 및 팀원을 구합니다.  프리프러덕션 기간 중에는 사무실에서 데스크 작업과 시장조사, 창고를 오가는 업무가 진행될 예정이고, 프러덕션 기간 중에는 사무실, 창고, 촬영 현장을 오가는 업무가 진행될 예정입니다.  =========================================================================  [ 모집 내용 ] _ 모집인원 : 현장팀장 1명, 팀원2명 _ 모집분야 : 현장 진행, 세팅/철수 진행, 차량 운행 _ 모집 요건 : 실 운전 가능하신 분 (카니발, 스타렉스, 탑차 등)  [ 업무 기간 ] 2024.3월 중 출근일자 ~ 2024. 06월 경 _ 프리프러덕션 : 출근 시점 ~ 4월 중순 _ 프러덕션 : 2024.04월 중순 (크랭크인) ~ 2024.06월 중순 (크랭크업-현재 시점 기준) _ 촬영 환경이나 날씨에 의해 크랭크업에 영향이 있을 경우, 업무 종료 일정은 변동이 있을 수 있고, 사전에 공지하여 협의할 수 있도록 하겠습니다. [ 그 외 ] _ 사무실 위치 : 서울 마포구 성산동 (경의선 가좌역, 6호선 마포구청역 인근 입니다) _ 첨부 파일 : 이력서, 포트폴리오 ( * 포트폴리오는 선택사항 입니다) _ 이력서 내 활용 가능한 프로그램 기재 _ 실제 차량 운행이 가능해야 합니다. (면허취득일 기입 필수, 실제 운행했던 차종 기입)  =========================================================================  이메일로 이력서 발송하신 후 문자 발송 꼭~ 부탁드립니다. 인원 충원 후, 모집 종료 공지하겠습니다.  긴 글 읽어주셔서 감사합니다.',1,'장편영화',NULL,'2024-03-11 16:03','2024-03-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(24,'장편 상업 영화 미술 팀원 충원 합니다. 인쇄','함께 일할 미술팀원을 충원 합니다. 장르는 스릴러로 현제 시대 입니다.  ▶모집인원 미술팀장 : 상업영화 미술팀장으로 1편 이상경험이 있으시거나 5~6편 이상 경력자 우대 경력 미술팀원 : 미술팀원 으로 1~3편 정도 참여한 분 (세트 디자인 및 현장 진행 유경험자 운전가능자 우대) 신입 미술팀원 : 포토 일러 스케치업 가능자 우대(스타렉스등 운전 가능한분)  ▶참여기간 프리프로덕션 : 2024년 3월~ 4월 초 프로덕션 : 2024년 4월 초 에서 6월 (아직 자세한 일정은 정해지지 않았습니다.) 40여회차 내외입니다.  ▶지원 메일 beast202401@gmail.com 으로 이력서와 포트폴리오 보내 주시면 감사하겠습니다~. (이름, 나이, 성별, 주소, 전화번호 ) - 필수 기입 포트폴리오는 본인이 작업했던거 위주로만 자유로운 형식으로 보내주시면 되고, 메일로 보내주시면 연락 드리겠습니다~. 급여는 경력에 따른 차등 지급 입니다.  함께 화이팅 하며 작업 하실 분들의 많은 참여 부탁 드립니다~.',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(25,'※ 영화 제작부 및 드라마 섭외팀 및 라인피디 모집(자차우대) ※ 인쇄','다수 작품들로 현재 **제작부 신입 및 섭외팀 신입**인원을 추가로 모집하고 있습니다. ※1차 구인공고모집은 2024년 1월 종료했고, 마지막 2차 추가인원 구인공고입니다. ※ **이력서 및 경력기술서 2차추가인원모집지원은 ※2024/3/11~2024/3/14 ※(총 4일)까지만 모집받겠습니다. 참고바랍니다.** <***앞으로 향후 작품 프로젝트를 오랫동안 함께 할 참신한 신입 라인피디 및 섭외팀원을 모집합니다. 반드시 단기간 구인 및 알바 지원 채용 일체 사절입니다.***> <*한작품 이상의 경력도 기술서 첨부 및 이력서 지원바랍니다. *> 상업영화 및 OTT드라마 넷플릭스 및 상업드라마에서 제작팀원 및 장소섭외팀원을 모집하오니 많은 지원 바랍니다.  **[작품개요] 제 목 : 면접 및 미팅 때 공지** 급 여 : 협의 **현재 프로덕션 진행중 ** **지원하신 이력서 및 경력기술서 검토 후 개별연락 후 비공개 면접진행예정.  [모집인원] - 제작팀 및 섭외팀 포함 총 5인 (신입) ※ 상업영화, OTT드라마 및 상업드라마 제작 및 섭외(장소로케이션)에 관심이 있으신분 ※ 운전면허 필수 (실운전자)※ / ※제작팀 자차우대 ※ ※섭외팀 자차필수 ※ ※제작부 및 섭외팀 1종보통 우대 ※  [지원 방법] **위 이메일 주소로 이력서를 보내주시면 검토 후 면접자분에게 개별 연락드리겠습니다. 신중히 검토하시고 지원 바랍니다. 감사합니다. **  ※ 제작실장 이메일 주소 : phoo619@nate.com ※ ※',1,'장편영화',NULL,'2024-03-11 00:03','2024-03-14','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(26,'녹음팀 붐어시스던트 구합니다 인쇄','안녕하세요 녹음팀 어시스턴트 구합니다 현재 작품진행중이며 처음하시는분, 경력자분도 괜찮습니다 면허있으신 분이면 좋겠습니다  촬영중이라 전화 연락은 못받으니 문자로 연락주시면 감사하겠습니다  김은주 010-9876-1998 감사합니다',1,'장편영화',NULL,'2024-03-09 10:03','2024-03-16','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(27,'넷플릭스 US 오리지널 드라마 <XO, Kitty Season 2>에서 팀원을 구인합니다. 인쇄','안녕하세요! 파라마운트 & 넷플릭스 US 오리지널 드라마 <XO, Kitty Season 2>에서 팀원을 구인합니다. 본 프로덕션은 미국 본팀과 협업하여 진행 될 예정이라 평소 해외작품의 참여를 원하셨던 분들의 많은 관심 부탁드립니다.  [ 작품개요 ] 제목: XO, Kitty Season 2 장르: 드라마 투자: 완료 캐스팅: 주연 완료 및 국내 단역 외 진행예정  [ 근무기간 ] 2024년 3월 ~ 6월 말 (프로덕션: 2024년 4월 중 - 6월 말)  [ 근무지 ] 오피스 - 잠실 방이동 촬영지 - 서울 경기권  [ 급여 조건 ] 경력에 따른 차등 지급  [ 필수조건 ] - 상업 영화/드라마 경력있으시면서 현장진행 경험 있으신분 - 영어 가능하신 분  [ 지원방법 ] 아래 이메일 주소로 이력서 보내주시면 검토 후 연락드리겠습니다. xokitty_unit@naver.com   감사합니다.',1,'장편영화',NULL,'2024-03-08 18:03','2024-03-23','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(28,'함께 할 인재를 모집합니다. 인쇄','커넥트온 신입지원 공고 글  커넥트온 데이터팀은 상업영화, 넷플릭스를 비롯한 OTT시리즈, 예능 등 다양한 컨텐츠의 데이터매니지먼트 전문업체로서, 함께 할 뜻을 가진 인재를 모집하고 있습니다.  [공통 지원자격]  * 학력 : 무관 * 나이/성별 : 무관 * 실 운전 가능자 * 남성경우 군필자 * 해외여행 결격사유 없는자  [담당 업무]  * DIT 와 관련된 전반적인 업무 진행 / 영화, OTT, 드라마 현장 업무 (Ex. 촬영 데이터 관리, 현장싱크, 매체변환 등)  [우대사항]  * 경력: 경력자(경력 년수 무관) * 영상 관련 전공자 우대 * 촬영제작 현장 경험자 우대  [근무조건]  * 근무형태 : 원천 계약(정규직 전환가능) * 근무시간 : 현장 스케줄에 따름 (탄력적 근무) * 급여 : 회사내규에 따름 (면접 후 결정) * 근무지역 : 촬영 현장 & 사무실 (유기적 변동 가능)  [지원방법]  * 이메일접수: connecton.team@gmail.com * (자유양식) 이력서 (사진 필수, 사는 지역 기재, 운전가능여부 및 운전경력 필수 기재) / 자기소개서 또는 경력 기술서  [채용관련 문의] connecton.team@gmail.com  [회사위치] 서울특별시 강서구 마곡동',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(29,'[스튜디오 클론] DIT 정규직 사원 채용 공고 인쇄','[스튜디오 클론] DIT 정규직 사원 채용 공고   스튜디오 클론은 예능제작 전문 업체로 각종 방송 및 OTT ( Netflix, Disney +, TVING, Coupang play) 예능 프로그램들을 제작하고 있습니다.  스튜디오 클론 에서 DIT 정규직 사원을 모집하고 있습니다. 많은 관심과 지원 바랍니다.   [모집분야]  DIT (신입/경력)  [담당업무]  DIT - DIT 및 비디오어시스트 - 현장 데이터 관리 업무 - 싱크  [우대사항] - 영상 관련 전공자 - 프리미어, 다빈치리졸브 등 영상 관련 툴 작업 가능자 - Mac OS 사용 가능자  [공통자격 요건] 학력 : 무관 나이/성별 : 무관  [근무조건] 근무형태 : 정규직 근무지 : 서울 상암동 근무시간 : 업무에 따른 탄력근무 급여 : 협의  [제출서류] 이력서 및 자기소개서 제출주소 : clonstudio@naver.com  [위치] : 서울특별시 마포구 상암동 누리꿈스퀘어',1,'장편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(30,'단편영화 노인(가제) 연출 제작등 스텝 모십니다 인쇄','단편영화 노인(가제) 연출 감독님, 제작팀원,촬영감독님,등 모십니다 영화제 출품을 하고자 합니다 예산은 저예산이다보니 많은 폐이를 들이지 못해 송구합니다 약 2달에 2작품정도 꾸준히 단편영화를 제작 하려 합니다 같이 팀을 이루워서 가족같이 해주실 분 연락 주세요 메일 :57353217a@daum.net ',1,'단편영화',NULL,'2024-03-19 17:03','2024-03-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(31,'단편영화 영어자막 번역가 구합니다 인쇄','안녕하세요. 30분 분량 단편영화에 영자막 번역가를 구합니다.  영어로 번역하여 SRT 파일까지 추출해주시면 좋겠습니다.  themeathouse23@gmail.com으로 포트폴리오와 페이를 남겨 주시면 확인 후 빠른 시일 내 연락 드리겠습니다.',1,'단편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(32,'SFX 메이크업 아티스트를 구합니다 인쇄','',1,'단편영화',NULL,'2024-03-19 09:03','2024-04-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(33,'녹음기사를 구합니다 인쇄','안녕하세요. 저희는 프랑스의 영화감독 듀오입니다. 현장에서 사운드 엔지니어로 일하실 분을 찾고 있습니다! 경험이 있는 분과 가능하다면 장비(대여 가능)가 필요합니다. 우리는 당신과 함께 일하기를 기대합니다!',1,'단편영화',NULL,'2024-03-19 09:03','2024-04-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(34,'개인 단편영화에서 편집감독을 구합니다 인쇄','안녕하세요, 단편영화의 편집감독을 구인합니다.  길이: 30분 내외 장르: 드라마 주요 업무: - 1차 편집본 수령 후 편집감독 임의 편집 - 픽처락 전까지 연출과 소통하여 편집 수정 페이: 30만 원  개인 제작 단편영화로 예산이 적어 낮게 페이를 책정한 점 양해 부탁드립니다. 메일로 필모그래피, 작업 영상 및 연락처를 보내주시면 검토 후 개별 연락드리겠습니다. 협의 이후 시나리오 및 1차 편집본 발송드릴 예정입니다.  감사합니다.',1,'단편영화',NULL,'2024-03-18 18:03','2024-03-20','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(35,'단편영화 음악감독님 (믹싱, 마스터링,ADR) 맡아 주실 분 찾습니다 인쇄','안녕하세요! 단편영화 \"수선화\" 감독 윤채환 입니다. 현재 영화 제작중인 수선화의 음악감독을 맡아 주실 분을 찾고 있습니다. 현재 1차 컷 편집까지 완료된 상태이고 지금 믹싱과 폴리 ADR, 음악을 맡아 주실 분을 찾고 있습니다. 러닝타임은 25분이고 원본 파일들 다 가지고 있는데 음향상태가 최고가 아니라 ADR이 중요시하게 될 부분인 것 같습니다. 그래서 개인 스튜디오가 있으신지 아닌지 여부를 남겨주시면 감사하겠습니다. 메일로 포트폴리오와 희망페이 남겨주시면 감사하겠습니다. 아무래도 개인제작 단편이다 보니 많은 페이를 드리진 못하겠지만 최대한 협의하에 드리도록 하겠습니다!',1,'단편영화',NULL,'2024-03-18 11:03','2024-03-25','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(36,'비공개 인쇄','개인 사정상 영화 정보를 공개하기 어려운 점 양해 부탁드립니다. 20분 내외 단편영화이며 조감독 구인중입니다. 상업 혹은 장편의 경험이 있으신 분, 경력,경험상 스케줄구성, 현장 진행에 문제가 없으신 분을 모십니다. 현재 진행상황은 캐스팅, 헌팅 등 기본적인 제반들은 전부 진행이 되어있으며 현재 상황 파악 및 그 외 진행만 해주시면 됩니다. 자세한 사항들은 미팅때 설명드릴 수 있도록 하겠습니다. 다시 한번 자세한 정보를 드리지 못하는 점 양해 부탁드리며 좋은 인연, 많은 지원 부탁드립니다:)',1,'단편영화',NULL,'2024-03-17 20:03','2024-03-22','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(37,'숏폼 형식의 sns 롱테이크 좀비 코미디 영화에 분장/메이크업 감독님을 모십니다. 인쇄','안녕하세요. SNS에 업로드 되는 스마트폰 숏폼 영화 형식의 단편좀비영화의 분장을 맡아주실 감독님을 모십니다.  관심있으신 분들께서는 위 메일로 연락주시면 감사하겠습니다.(가급적 포트폴리오도 함께 보내주시면 감사하겠습니다.) 수도권에서 촬영이 진행될 예정입니다! <장소 및 3월 25일 또는 26일 중 정확한 날짜는 3월 19일 확정> <촬영시간은 총 3시간정도로 길지 않습니다. 분장시간 또한 1-2시간정도 생각하고 있습니다.>   <제목> 미정 (좀비 스릴러 코미디)  <레퍼런스>    <작업 관련> 위 사진과 같이 좀비 분장을 진행합니다만, 창백한 음영표현과 좀비 눈 렌즈 착용정도로 진행 될 예정입니다! (피 or 상처 분장 최소화)  <페이> (소요시간 1-2시간) 회차당 12만원(분장 재료비는 저희 측에서 부담합니다.)  <지원 사항> - 제목 : 메일 보내실 때, 제목 앞에 [좀비 분장 지원] 이렇게 역할 지원 구분 부탁드립니다!',1,'단편영화',NULL,'2024-03-17 18:03','2024-03-20','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(38,'20분 분량 단편영화 영어 번역/영자막 제작해주실 분을 구합니다. 인쇄','안녕하세요, 기완성된 영화가 국제영화제에 선정되어 영자막을 제작해야 하는 상황입니다.  드라마 장르입니다.  포트폴리오와 희망 페이를 적어서 알려주시면 연락드리겠습니다.',1,'단편영화',NULL,'2024-03-16 16:03','2024-03-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(39,'4월 19일 스타일리스트 1분 구인합니다 인쇄','한복 관련하여 지식이 있으신 분이면 더 좋을 것 같습니다!',1,'단편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(40,'단편영화 <인사>에서 사운드 믹싱 작업해주실 분을 구합니다 인쇄','안녕하세요. 단편영화 <인사>에서 사운드 믹싱 후반 작엄을 담당해주실 분을 찾습니다. 다빈치 리졸브로 편집을 마무리하였고, 다빈치을 아시는 분이면 좋겠습니다. 영화의 런닝타임은 16분입니다.  페이는 20-30만원대로 생각하고 있습니다.  -------------------------  [지원 방법] 이메일 지원 [메일 제목 양식] 믹싱 / 이름 [메일 내용] 1. 포트폴리오 또는 편집하신 영상 링크, 2. 희망 페이  감사합니다.',1,'단편영화',NULL,'2024-03-17 18:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(41,'(마감된 공고입니다. 감사합니다.) 단편 \"버스(가제)\" 에서 촬영감독님을 모십니다. 인쇄','안녕하세요, 단편 영화 \"버스(가제)\" 연출자 최윤수 입니다. 촬영을 해주실 촬영감독님을 모십니다. 단편영화 경험이 많으신 감독님, 혹은 경험이 많지는 않더라도 새로운 촬영으로 시도해보실 분 등 찾고 있습니다. 1. 촬영 내용 : 간단 로그라인_버스를 타고 일어나는 이야기로 현재와 과거를 넘나들며 그동안 참아왔던 슬픔과 분노, 절망, 후회등 복합적인 감정이 폭발하는 이야기.  2. 자격요건 : 카메라, 짐벌 등 장비 보유하신 분  3. 촬영 지역 및 일정 a. 제주도 로케 촬영이라 함께 동행 예정 b. 24년 4월 9-11(화수목) (자세한 이야기는 연락으로) 4. 제출 자료 a. 메일 제목은 [촬영감독_이름_연락처] 로 부탁드리며 b. 간단포트폴리오는 링크 혹은 자유 형식으로 c. E-mail: likethismc@hanmail.net/ HP: 010 7526 3356/ 연출 최윤수 로 보내주시면 감사하겠습니다:) 기타 문의 사항은 댓글/연락처를 통해 남겨주시면 빠르게 답해 드리겠습니다.',1,'단편영화',NULL,'2024-03-15 09:03','2024-03-20','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(42,'스크립터 구인합니다. 인쇄','상업 촬영현장 경험 있으신 분 아이패드 사용가능하신 분  이메일로 간단한 경력사항과 인적사항 보내주시면 연락ㄷ 리겠습니다. 많은 지원 부탁드립니다.',1,'단편영화',NULL,'2024-03-12 16:03','2024-03-16','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(43,'안녕하세요 단편영화 <천원> 에서 음악감독님을 모집합니다 인쇄','안녕하세요 단편영화 <천원> 에서 음악감독님을 모집합니다  러닝타임: 20분 내외 장르: 드라마  현재 가편집 단계이며 음악은 최소 2곡 이상 예상 중입니다.  1. 약 2분 30초 내외 겨울 느낌이 나는 서정적인 음악 레퍼런스: 김해원, 임주연 - 차가운 아침 공기 (윤희에게 OST) 김해원, 임주연 - 만월 2. 약 40초 내외 아기 강아지 샤워씬에 어울리는 밝은 음악. 레퍼런스: My Happy Song | featuring Noodle & Pals | Super Simple Songs https://www.youtube.com/watch?v=ufbOHl1mmYk  페이: 협의  lnxyz@naver.com 이메일로 포트폴리오 보내주시면 감사하겠습니다.',1,'단편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(44,'미국 샌프란시스코 배경 단편영화 <따뜻함의 바다(The Sea of Warmth)>에서 한국 촬영 부분 촬영감독님, 음향감독님을 모집합니다. 인쇄','안녕하세요! 독립영화 <따뜻함의 바다(The Sea of Warmth)>에서 촬영감독님과 음향감독님을 모집합니다!  <촬영 정보> 이 작품은 미국 샌프란시스코에서 거의 모든 부분이 촬영이 된 30분 정도 러닝타임의 작품이며, 다만 영화의 시작과 끝 씬이 서울에서 일어나는 부분이어서, 한국 부분(2씬) 정도만 참여해주실 스탭분들을 구하고 있습니다.  로케이션은 을지로 3가역과 동작대교이며, 2씬에 대사도 거의 없기 때문에 촬영이 길지 않을 것으로 예상됩니다(약 3시간 예상합니다!) 거의 와이드샷과 인서트가 대부분이고, 대사는 2줄만 있습니다!  영화 자체는 대부분 영어로 되어있으나, 한국어로 된 부분도 조금 포함이 되어있습니다.  최소한의 크루로 촬영을 할 수 있으면 좋겠어서, 촬영 혹은 음향 한가지만 가능하신 분도 좋고, 두 분야를 팀으로 운영하시는 분이어도 좋을 것 같습니다! 페이같은 경우, 최대한 소규모로 진행하려고 노력하고 있지만, 견적을 말씀해주시면 협의할 수 있으면 좋겠습니다. 학생분이나 경험을 쌓고 싶으신 분도 환영입니다!  <시놉시스> 미국 유학 시절 함께 아이를 가지게 된 준희와 지운은 아이를 다른 가족에 입양시키기로 결정을 한다. 아이를 떠나보내고 한국과 미국에서 각자의 삶을 살던 준희와 지운은, 어느 날 입양을 보냈던 아이가 세상을 떠났다는 소식을 듣고 다시 만난다. 미국으로 준희를 보러 온 지운은 준희의 달라진 삶을 보고 다양한 감정을 느낀다.  아무래도 미국에서 찍은 영화의 시작과 끝을 한국에서 찍는 것이다 보니 카메라나 렌즈가 같아야 해서, 이 점을 어떻게 렌탈해서 진행할지 줌으로 의논을 나눠보면 좋을 것 같습니다!  궁금하신 점 있으시다면 부담없이 junwookang7@gmail.com으로 연락주세요! 각본과 스토리보드, 그리고 미국 촬영본도 공유 가능합니다!',1,'단편영화',NULL,'2024-03-12 10:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(45,'딘편영화 음악감독 인쇄','현재 촬영 완료하여 편집 중인 단편영화의 음악감독님 구합니다 장르는 호러이며 4월 중순 제출기한인 국제 영화제에 출품 예정입니다 아직 편집이 완료되지 않아 확실하진 않지만 10분 내외 (12분 미만 예상) 길이의 영상입니다 함께 회의하면서 전체적으로 브금 등등의 음악을 만들어주셨으면 좋겠습니다 소규모 개인단편영화라 페이는 시세만큼 지급드리긴 어려울 것 같습니다, 따라서 시작하거나 포트폴리오 만들고 싶으신 분들께서 참여해주신다면 좋을 것 같습니다 위 메일로 경력이 없으시다면 간단하게나마 어느정도의 실력이신지 알려주시면 감사하겠습니다 그리고 원하시는 페이도 꼭 선제시해주시기 바랍니다! 연락주시면 감사하겠습니다 모두 좋은 하루 보내세요!^^',1,'단편영화',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(46,'청소년 영화 제작팀 단편영화 편집해주실 분 구합니다 인쇄','각 지역 청소년들이 모여 개인 사비로 제작한 단편 영화입니다. 저희끼리 편집을 하려고 하다가 파일이 날아가고 오류가 생겨서 급하게 편집자분을 모집해봅니다..ㅠㅠ 청소년 제작 영화이기 때문에 페이가 많이 적습니다…죄송합니다.. 컷편집이랑 립싱크만 맞춰주시면 감사할 것 같습니다 지원은 적어둔 이메일로 보내주시면 됩니다',1,'단편영화',NULL,'2024-03-11 07:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(47,'특수분장 팀원 구인합니다 인쇄','이번에 들어가는 프리퀄 단편영화 특수분장 팀원을 모집합니다  프리 : ~ 3월 31일 크랭크인 : 4월 1일 ~ 4월 10일 중 3회차  자율양식 자소서와 포트폴리오 보내주시면 확인 후 연락드리겠습니다 xodms216@naver.com 메일에 이름 / 나이 / 거주지 / 연락처 기재 부탁드립니다  이번 작품이 아니더라도 추후에 참여 가능하신분들도 연락바랍니다  주로 독립 영화, 뮤직비디오에서 특수분장과 특수소품 제작을 하고있습니다 ( *일반적인 메이크업 외주는 받지않습니다 ) 저와 함께 좋은 작업물을 만들 팀원을 모집합니다  경력 무관 작업 특성상 스케줄이 자유롭고 체력이 좋으신분 조소 혹은 미술 관련 전공 우대 서울 경기권 우대  @sfx_osal',1,'단편영화',NULL,'2024-03-10 19:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(48,'단편영화 편집 도와주실분 구합니다. 인쇄','안녕하세요,  개인이 휴대폰으로 촬영한 영상의 편집마무리를 도와주실 기사님을 찾습니다.  현재 컷 편집까지 완료된 상태입니다.  전문가가 아닌상태로 혼자 편집을 하다보니 디테일한 부분의 편집이 너무 어려워서  사운드나 디테일한 부분 편집 도와주실 기사님께서 도움 주셨으면 합니다..! (페이를 기재해 놓긴 했는데, 원하시는 페이가 있으시다면 메일로 같이 보내주셔도 됩니다!)  단편영화 총 19분 영화입니다.  메일 부탁드립니다!',1,'단편영화',NULL,'2024-03-10 11:03','2024-03-11','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(49,'미스테리/스릴러 [구슬집] __믹싱 아티스트와 타이틀/포스터 아티스트를 구합니다. 인쇄','안녕하세요.  개인 단편영화 <구슬집>의 후반작업을 함께 해 주실 두 분을 모십니다.  프로덕션은 이미 완료한 상태입니다. 편집은 다음주 중으로 마무리될 예정입니다.     *작품 로그라인: 서른 살 생일을 맞이한 진주는 연옥에서 빠져나가기 위해 계획했던 살인을 실행한다.   *작품 길이: 20분 내외   *구인 분야: 믹싱 및 폴리 1명 / 영화 타이틀, 포스터 디자이너 1명   *특이사항: (1) 3월 말일까지는 믹싱과 자막 작업이 완료되었으면 합니다. 편집 파일은 늦어도 3월 17일 전후로 드릴 수 있습니다. (2) 미스테리/공포/스릴러 등 장르물 작업 경험이 많으신 분 선호합니다. (3) 영화 포스터와 더불어 영상 타이틀을 따로 디자인 해 주실 분 찾습니다.   *페이: 이메일에 희망 급여 적어주시면 감사하겠습니다. 포트폴리오 꼭 첨부 부탁드립니다.  *이메일 주소: hanna_lemon@naver.com    ',1,'단편영화',NULL,'2024-03-09 21:03','2024-03-14','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(50,'유튜브 콘텐츠/웹드라마의 작가님을 모집합니다! 인쇄','안녕하세요.  기독교 유튜브 채널 KNOCK노크의 웹드라마 <고밍아웃> 의 시즌3 시나리오 및 구성 작가님을 모집합니다.  <고밍아웃>은 기독교/비기독교의 고민을 웹드라마로 담고, 기독교적 관점에서 솔루션을 제공하는 토크 프로그램이며, 해당 모집은 웹드라마 시나리오 작업하실 작가님 모집입니다 :)  현재 시즌 1,2까지 퍼블리싱을 마쳤으며, 시즌 3에는 \'10대\'를 주제로 제작을 진행하고자 합니다. (편수 : 총 10편 / RT : 편당 10분 이내)  ■ 모집 요강 ▷ 직무 : 작가/시나리오 및 구성 ▷ 스킬 : 기획, 콘텐츠, 시나리오, 글쓰기 (우대 : 웹 소설, 웹드라마 및 유튜브 콘텐츠 헤비 유저 청소년 및 웹드라마 작가 유경험자 혹은 지망생) ▷ 제작 : 시나리오 작업 3월 ~ 4월 / 6월 촬영 예정 ▷ 연차 : 상관없음 ▷ 페이 : 면접 시 협의  ※ 이메일로 간단한 이력서와 포트폴리오를 보내주시면 검토하여 연락드리겠습니다. 지원을 원하시는 분들은 831ga@cgnmail.net로 접수해 주시기 바랍니다.  감사합니다 :)    ',1,'웹드라마',NULL,'2024-02-02 14:02','2024-03-01','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(51,'3월말 5월초 분장팀원 모집합니다. 인쇄','분장팀 팀장•막내 구합니다❤️‍🔥 - [ ] 남여 기본 메이크업 팀장은 상처분장 가능하신 분 - [ ] 3월말~ 5월초 로케 서울경기 2달 정도 촬영예상 - [ ] 서울 경기 거주자 홍대 쪽이나 경기북부환영이요 실력도 실력이지만 진짜 이쪽 일 하고싶은 정말 열정있고 예의있는분만 연락주세요! 작업실은 일산입니다 가까우신 분 환영이예요 막내 200~ 팀장 300~ 페이는 연락 주시면 스케쥴 상황에 따라 알려드리도록 하겠습니다  ',1,'웹드라마',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(52,'웹드라마 동시녹음 \'붐맨\' 모집 인쇄','안녕하세요. 웹드라마 무림소녀 제작진입니다. 동시녹음 \'붐맨\' (음향기술자)님 모십니다. 현재 음악감독, 장비는 모두 있는 상태입니다. 이력서는 메일로 보내주세요. 감사드립니다.  chanechane@hanmail.net',1,'웹드라마',NULL,'2024-02-01 21:02','2024-02-05','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(53,'웹시트콤 헤어 메이크업 실장님 구인합니다 인쇄','웹시트콤 헤어메이크업 실장님 구인합니다.  남매 이야기의 웹 시트콤, 총 4회차 촬영 예정입니다. 현장에서 변경되는 씬에 맞게 헤어. 메이크업 진행해주시면 됩니다. 특별한 분장은 없고 상황에 맞는 내추럴하고 일반적인 헤어 메이크업이면 됩니다. 회차당 출연하시는 배우는 최소 3인에서 최대 5인 정도 예상합니다.  촬영 날짜 : 2/13,14,15,17 회차 : 4회차 촬영 장소 : 서울 내 페이 : 1회차 20만원 등장인물 : 전체 주연 남2 여1 / 조연 여3 남3 (회차당 최대 3-5인)  지원 방식 : 이메일 접수 - 첨부서류 (이력서 및 참여 작품 포트폴리오) 메일 제목은 \'[웹시트콤/헤메]_000 지원합니다\' 접수해주시면 됩니다.  많은 지원 부탁드립니다. 감사합니다.',1,'웹드라마',NULL,'2024-01-31 16:01','2024-02-09','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(54,'유튜브 콘텐츠·웹드라마 작가님을 모집합니다. 인쇄','안녕하세요 온라인콘텐츠 창작소 온라이브플러스 입니다 현재 토크형식의 유튜브 콘텐츠를 꾸준히 찍어나가고 있는데, 새로운 아이디어 기획과, 웹드라마를 함께 만들어가 주실 작가님을 모집합니다 촬영은 매주 최소 1,2회 진행되며 점차 늘려나가고 있습니다. 4대보험 적용하여 직원으로 채용하길 희망하며 정직원 근무시 바이럴 업무가 병행될 수 있습니다 많은 연락 부탁드립니다 메일은 jh.bae@onliveplus.com 으로 부탁드립니다 감사합니다 온라이브플러스 배재현 **의도에 오해가 있으신것 같아 정정하겠습니다 1. 우선 이력서와 포트폴리오만 주십시오 2. 내부 기준에 따라 면접대상자에게 연락드리고 저희가 기 진행된 콘텐츠(10분 내외 유튜브 토크)를 어레인지 해주십시오.(비용 10만원) 면접대상자 선별기준을 두기위해 참신한 아이디어를 가지고 계신 작가님들을 모시고 싶어서 새로운 콘텐츠의 간단한 시놉시스정도를 요청드리고 저희와 방향이 맞는 분을 우선 모셔서 면접하고 테스트 촬영에 대한 비용을 우선 지불하고 정직원 계약을 하려는 것이 저희 의도였는데 오해가 있는 분들이 계신것 같아 정정드립니다 현재 파일럿 형태의 막 촬영을 끝낸 상태라 편집중이고, 업로드되지 않아 콘텐츠는 공유되지 않았어요 개별 면접 대상자분들에게 안내드리겠습니다 의견 감사드리며, 텍스트로 소통하는 것에 부족한 소양을 반성하며 남겨주신 말씀 새기겠습니다. 감사합니다 온라이브플러스 배재현 드림',1,'웹드라마',NULL,'2024-01-30 17:01','2024-02-29','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(55,'[유튜브 예능 숏폼] 웹드라마 20대 남자 여자 배우 모집합니다 인쇄','3분내외 정도의 스케치코미디 배우분 모집합니다. 브랜드 홍보 영상이며 웹드라마 형식 *SNL처럼 유머스러운 내용이 있습니다. *유튜브/인스타그램/틱톡 등 SNS 업로드 예정 [촬영일시] - 일시 : 지속적으로 진행할 컨텐츠이며 인터뷰 후 촬영 1~2주전에 연락 드릴 예정입니다 [페이] 건당 20~30만원(협의) [향후 계획] 브랜드 홍보 영상으로 지속 촬영 예정 *이미지 잘 맞을시 고정캐릭터로 지속적으로 촬영 가능 [지원방법] *지원메일 : jnine2018@naver.com ※메일 제목 해당 예시처럼 기재 부탁드립니다. [유튜브 숏폼 지원자/이름/성별/나이] ex. [유튜브 숏폼 지원자/홍길동/남/20] *제출서류 : 이력서 / 포트폴리오 ㄴ웹드라마/숏폼 출연 영상 있으면 해당 UPL도 함께 첨부해주세요',1,'웹드라마',NULL,'2024-03-25 10:03','2024-04-10','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(56,'안녕하세요. OTT / 웹드라마 작가님을 모시고 있습니다. 인쇄','안녕하세요. OTT / 웹드라마 작가님을 모시고 있습니다. 전에 모집공고를 한번 올렸었는데, 유명 작품 경력이 있으신 작가님이 필요한 상황으로 바뀌어서 다시 올립니다.  ****꼭!!!! 유명한 작품 또는 TV 및 OTT 드라마 작품 경력이나 경험이 있으신 작가님만 지원 부탁드립니다(보조작가님도 경력이 많다면 ok입니다)****   신인 여자아이돌 데뷔를 앞두고, 드라마 형식의 컨텐츠를 제작하려 하는데, 작가를 구해야 하는 상황입니다.  플랫폼은 넷플릭스 등의 OTT 일 확률이 높고, 유튜브 등의 플랫폼에 업로드 될 가능성도 있습니다.  드라마의 회차는 편당 30~40분 5~6회 정도이고, 내용은 편마다 아이돌 1, 2, 3, 4, 5 의 성장과정 및 일대기 및 어떻게 만나게 되어서 팀이 되었는지 정도입니다.  러프하게 전체적인 틀정도는 연출감독님이 잡을텐데, 잡혀있는 틀은 정말 러프하다고 생각하면 좋을 것 같습니다.  작가가 연출에 관여하는 부분이 너무 많으면 조금 부담스러울 것 같고, 웬만하면 연출감독과 소통이 잘 되며 능동적인 작가님이시면 좋을 것 같습니다.  1~2월중에 미팅하고 촬영은 2~3월중에 들어가면 좋을 듯 싶습니다. 페이와 대표작 및 자유형식의 포트폴리오 아래 메일로 보내주시면 검토후 연락 드리겠습니다. 유명 작품 경력이 있으신 작가님들의 많은 지원 부탁드리고, 페이 러프하게라도 꼭 함께 기재 부탁드립니다. 감사합니다.  ****꼭!!!! 유명한 작품 또는 TV 및 OTT 드라마 작품 경력이나 경험이 있으신 작가님만 지원 부탁드립니다(보조작가님도 경력이 많다면 ok입니다)****   메일 : lhs5592@naver.com',1,'웹드라마',NULL,'2024-01-26 01:01','2024-02-29','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(57,'BL드라마 조감독,연출팀을 모집합니다. 인쇄','안녕하세요. BL드라마 연출팀(조감독/연출팀)을 모집합니다.  - 작품정보 : 미팅 시 공개  - 모집부분 : 조감독 1명, 연출팀 2명 - 근무기간 : 24.02 - 24.05 예정 (프리-촬영 종료시까지) - 급여 : 미팅시 공개 - 지원자격 : 성별무관 - 우대사항 : 1) 조감독 : 경력 2작품 이상 2) 공통 : BL 컨텐츠에 관심이 있는 분 환영합니다.  - 지원방법 : 자유양식의 이력서 메일 접수 / *이력서에 지원분야(조감독/연출팀) 기재  *이력서 및 문의사항 skql3835naver.com',1,'웹드라마',NULL,'2024-01-24 19:01','2024-02-09','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(58,'웹드라마 형식의 영상 동시녹음 감독님 구합니다 인쇄','연기자가 하는게 아니고 일반 회사원 분들이 웹드라마(SNL MZ오피스 느낌)에 도전하는 영상입니다. 1월30일 / 2월 2일 / 2월 5일 총 3일 촬영이 진행 됩니다. 시나리오는 총 5편이며 편당 2분 미만의 영상이며 출연 인원은 편당 5~7명 정도 입니다. (추가적인 엑스트라들도 있지만 사운드는 없습니다) 촬영 시간은 식사시간 포함 9시간 정도 입니다. 촬영장소는 서울 강남구 일대 입니다  3일 모두 가능하신분들 지원해주시면 좋겠습니다. 메일로 지원 바랍니다. 감사합니다!',1,'웹드라마',NULL,'2024-01-23 15:01','2024-01-23','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(59,'[마감] IT기업 사내교육영상 웹드라마 작가님을 모집합니다. 인쇄','안녕하세요, 영상 제작팀 <유니랜서>입니다.  IT기업 임직원 사내 교육을 위한 웹드라마 1편 시나리오 써주실 작가 분을 모집합니다.  [작업 내용] - 러닝타임 : 편당 8분 내외 - 작업물: 총 1편 - 톤앤 매너 : 이목을 끌 수 있는 속도감과 약간의 유머 - 특이사항: 업체에서 이야기의 큰 틀이나 대사가 대략적으로 자료 전달할 예정입니다. 각색에 가까운 작업일 것 같습니다. (디테일한 상황 대사 터치 필요) - 일정: 1월 24일 1차 자료를 작가님께 전달 예정. / 1월 25일 ~ 26일 온라인 미팅 29일까지 시나리오 1차본 클라이언트에게 전달. (일정이 많이 빠듯해서 죄송합니다. )  [참고사항] - 출연자: 2~3명 예상 [근무] 재택근무 가능하며 작업상황에 스케줄을 서로 조율하여 미팅이 가능하신 분 [모집자격] 경력, 성별, 연령 무관 기업 문화, 회사 생활에 대한 이해도가 있으신 분 [페이] 1편: 50만 원 [지원방법] - 이메일로 지원시 작업하셨던 웹드라마 <레퍼런스 링크>와 <1편 기준 희망하시는 페이> 기재 부탁드리며, 간단한 소개도 부탁드리겠습니다. - 메일 제목은 [웹드라마 작가 지원_성함]으로 작성해주시기 바랍니다.  많은 지원 부탁드립니다!',1,'웹드라마',NULL,'2024-01-23 17:01','2024-01-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(60,'학교관련물 <웹 드라마> 시나리오 작가님을 모집합니다 인쇄','안녕하세요. 울산 영상프로덕션 한그루필름입니다.  이번\'관계회복\'을 주제로 한 새로운 웹드라마 시리즈를 제작을 진행하게 되어 작가님을 모집합니다. (해당 웹드라마는 교육청유튜브 및 사내브랜드채널 등 업로드 될 예정입니다)  ■ 제작내용 · 주제: 관계회복 (교사-학생) · 편수: 2~3편 · RT: 편당 5분 이내 - 하나의 내용을 3편으로 진행되거나, 토막형식으로 각각 다른 시리즈를 담아 제작 논의중 · 제작방향: 최근 일어나는 학생과 교사간의 관계회복을 위한 내용을 담아내지만, 분위기는 전반적으로 어둡지 않게 담아내는 방향으로 제작을 하려 합니다. · 내용은 고등학교에 포커스를 맞추고 있습니다. ------------------------------------------------------------------------------- ■ 모집요강 1. 시나리오 작가 - 1명 1) 일정 - 6월말 촬영예정으로 잡고 있어, 3~5월 중 시나리오 컨택을 거치려합니다. - 현재 초기 회의를 진행 중에 있어 제작일정이 폭넓음을 사전에 전달드립니다. 2) 우대사항 - 학교물(청소년) 시나리오 유경험자 - 드라마(웹드라마) 작가 유경험자 3) 페이(협의) - 페이는 메일 접수 시, 기재 부탁드립니다.  * 지방업체이기에 시나리오 미팅에 있어 줌회의가 있을 수 있음을 사전 양해부탁드립니다. ------------------------------------------------------------------------------- ■ 지원방법 - 이메일 접수 -> 서류검토(참여작품 외) -> 최종 셀렉 후, 컨택 이메일접수 - 서류면접 1) 이력서, 포트폴리오 2) 편당 작업기간 / 수정횟수(시놉시스 제외) 3) 페이 지원하시는 분은 이메일(hangroo_cc@naver.com)로 접수해주시면 됩니다:)  감사합니다 :D',1,'웹드라마',NULL,'2024-01-23 10:01','2024-02-29','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(61,'(마감) 인쇄','마감되었습니다.  도움 주시고자 연락해주신 모든 분들께 감사의 말씀 드립니다.',1,'웹드라마',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(62,'남여 배우모집합니다. 인쇄','****** 남, 여 배우 모집합니다. ******',1,'웹드라마',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(63,'감사합니다! (지원 마감!) 짧은 드라마타이즈 인쇄','국가철도공단에서 진행한 시나리오 공모전 수상작 촬영입니다.  짧은 드라마 타이즈로 동시녹음 부탁드릴 감독님 한 분 구합니다. 장비(붐마이크)는 저희측에서 대여해 준비할 예정이며 (기종은 함께 논의할 예정 )  1회차 노원구 화랑대역 폐역 1월 28일 일요일 8시-17시 2회차 역삼역 대관 카페 + 저희 회사 사무실 (삼성역) 1월 29일 월요일 9시-17시  이렇게 진행할 예정입니다. 많은 지원 바랍니다. 감사합니다. ',1,'웹드라마',NULL,'2024-01-12 18:01','2024-01-11','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(64,'연출부 지원마감했습니다.감사합니다. 인쇄','이번 저예산 유튜브 1월 14일 부터 시작 3회차 정도 찍을것 같습니다. 페이는 총 70만원정도 보구있고요 아직 다음 스케줄이 미정이라서 이메일이나 혹 문자로 필메보고 답장 드립니다.살짝 스펙 정도 남겨주시면 되겠습니다. 예상이 적다보니 많이 못드려서 죄송합니다.  ',1,'웹드라마',NULL,'2024-01-11 18:01','2024-01-13','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(65,'숏드라마 동시녹음 감독님 구합니다. 인쇄','안녕하세요. 동시녹음 감독님을 모집합니다.  촬영 일정은 1월 27,28일 2회차 예정입니다. 서울에서 촬영 진행합니다.  이력서 및 포트폴리오는 dramashines@gmail.com 으로 제목에 [동시녹음]을 명시하여 보내주시면 감사하겠습니다.  확인 후 개별 연락 드리겠습니다. 감사합니다!',1,'웹드라마',NULL,'2024-01-11 15:01','2024-01-19','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(66,'숏폼 드라마 후반 작업 해주실 팀 또는 분 구합니다 인쇄','숏폼 드라마 후반 작업 총괄해주실 팀 또는 분 모십니다.  총 85회로, 회당 1분 30초 정도 예상 중이고 /페이는 총 페이 600 정도 생각 중입니다 어느정도 선에서 현의 가능합니다  현장 편집/편집/음악/믹싱/Di/ 예고편 까지 높은 수준을 원하는건 아니라서 총괄해서 맡아주실 분 팀 부탁드립니다  singy3@naver.com으로 (후반작업) 제목으로 작업하셨던 레퍼런스 자료 부탁드립니다',1,'웹드라마',NULL,'2024-01-09 03:01','2024-01-24','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(67,'숏폼 드라마 미술,분장,의상 해주실분 구합니다 인쇄','분장과 헤어를 같이 해주실분 1분을 구하고 소품과 의상 같이 해주실분 1분을 구합니다.  singy3@naver.com (분장헤어) 또는 (소품의상) 제목으로 작업하셨던 참고 자료같이 메일로 부탁드립니다  1월 27일에 전체회의때 분장헤어, 소품의상 체크때 한번 와주셔서 체크 하고 사진 찍는것도 같이 부탁드립니다.',1,'웹드라마',NULL,'2024-01-09 02:01','2024-01-29','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(68,'BL드라마 제작팀(제작PD/라인PD) 구인 인쇄','안녕하세요. BL드라마 제작팀(제작PD / 라인PD)을 모집합니다.  - 작품정보 : 미팅 시 공개  - 모집부문 : 제작PD, 라인PD 각 1명 - 근무기간 : 24.03. - 24.07. 예정(프리~촬영종료 시 까지) - 급여 : 협의 - 지원자격 : 성별무관 - 우대사항 : 1)공통 : 실운전가능자 2)제작PD : 경력 1작품 이상  - 지원방법 : 자유양식의 이력서 메일 접수/ *이력서에 지원분야(제작PD / 라인PD) 기재 *이력서 및 문의사항 w123ww@naver.com 자세한 내용은 미팅 시 공개 가능한 점 양해 부탁드리며, 미팅 진행 시 개별 연락 드리겠습니다. :) 많은 관심 부탁드립니다. 감사합니다.',1,'웹드라마',NULL,'2024-01-08 14:01','2024-01-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(69,'마약중독관련 짧은 웹드라마 작가님을 모십니다. 인쇄','안녕하세요.  저희는 금번에 마약중독과 관련해서 짧은 웹드라마 제작을 진행하게 된 코드브이 라고 합니다.  대학생 2명이 풀어나가는 스토리로 개략적인 스토리 라인(시놉)은 구성되어 있으며, 내용을 구체화 시켜서 시나리오를 작성해 주실 작가님을 모시고자 합니다. RT는 3분 정도 분량으로 많이 길지 않게 구성할 예정입니다. 혹시 작업하셨던 포폴이나 경력사항 있으시면 전달 부탁드립니다.  메일로 연락처 공유 해 주시면 직접 연락드려서 진행하겠습니다.  한 해의 시작은 이렇게 단발 건으로 시작하지만, 좋은 인연이 되어 많은 일 드릴 수 있도록 저희도 노력 해 보겠습니다~^^  많은 지원 부탁드립니다. 감사합니다.',1,'웹드라마',NULL,'2024-01-08 10:01','2024-01-10','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(70,'< 3월 23일 MV 현장 연출부 남자 1인 구인 합니다 / 파주 시작, 인천 종료 / 12~03시 / 18만원 + & > 인쇄','< 3월 23일 MV 현장 연출부 남자 1인 구인 합니다 / 파주 시작, 인천 종료 / 12~03시 / 18만원 + & >  * 장소 : 파주 백석리 시작, 인천 연수구 종료 (실내스팟) * 일시 : 3월 23일 12~03시 - 15시간 * 내용 : 기본 연출부 * ★ 우대사항 : 1종 보통, 스타렉스 운전 가능자  * 페이 : 12시간 기준 18만원 + 시간당 1만원씩, 15시간 이상 촬영 예정 * 필요장비 : 연출가방(없으시면 미니백 1개 지참!) * 기타 : 현장 경험이 조금 적으시더라도 그 외 다양한 경험이 많고 성실하고 근면하고 빠릿빠릿하신 분이면 좋겠습니다! 나이트 촬영 힘드시지 않으신 분, 올빼미이신 분! 말을 빨리 알아들으실 수 있는 분이면 좋겠습니다.  ※ 많은 시간을 뺐지 않습니다만, 면접을 나름 상세히 진행하는 편이라서, 잘 참여해주셨으면 좋겠습니다. 현장에서 제가 정말 열심히 할테니, 잘 따라와 주실 분이면 좋겠습니다!!  * 모집방법 : 이메일 접수 -> 유선 면접(상세히 진행) -> 최종 선발 * 지원방법 : rhdld0091@naver.com 메일 접수 * 제목내용 : 이메일 제목에[이름/나이/거주지/본인사진 1장/임하는 각오] + 포트폴리오 및 촬영현장경력(포지션 1st, 2rd, 3rd 등 상세히 부탁드립니다.)  ※ 면접 일정 및 최종 선발은 개별 통지 ※ 모든 내용은 변동될 수 있음 ',1,'뮤비/CF',NULL,'2024-03-20 10:03','2024-03-23','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(71,'광고 뮤직비디오 편집 개인 및 업체 구인합니다. 인쇄','안녕하세요.  광고 뮤직비디오 편집 업체 및 개인 구인합니다.  저희쪽에서 1차 가편집 및 색보정 진행 예정입니다.  컷 편집 및 2D그래픽 작업 가능하신 분들 지원해주시면 감사하겠습니다.  추후 진행 시 진행 프로세스는 한번 조율해서 진행하면 좋을 것 같습니다!  일정은 다소 타이트하다보니 일정에 잘 지켜주시는 분이면 좋을 것 같습니다.  메일(제목 : [편집] 이름)로 지원해주시면 감사하겠습니다  많은 지원부탁드립니다~ 감사합니다! :)',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(72,'[여자모델] 헤어드라이기 20~30대 긴생머리 여자 모델 구인 인쇄','안녕하세요. 마케팅에이전시입니다. 헤어드라이기 제품 여자 모델분을 구인합니다. 상세페이지에 들어갈 이미지 및 영상에 등장하실 긴머리 모델분입니다.  ○ 구인 모델 : 20~30 여자 모델 (긴생머리 필수) - 헤어드라이기로 기본적인 셀프스타일링 가능하신 분 ○ 촬영 일정 : 3/21 (목) 오후 1시~5시 (4시간) - 콜타임 : 15분 전 (오후 12시 45분) ○ 위치 : 서울 신도림 스튜디오 ○ 페이 : 30만원(소득세 3.3% 당사부담 신고-30만원 전액 지급) ○ 촬영 내용: - 사진/영상 촬영 모두 진행 - 접이식 헤어드라이기 사용, 시연컷 등 - 긴생머리 말리는 컷 ( 머리를 감았다가 젖은 머리를 말리는 연출, 부스스한 머리 연출이 들어갑니다.) - 마그네틱 노즐 부탁 후 스타일링하는 컷  지원 시에는 프로필/포트폴리오를 꼭 첨부해주세요. 지원 메일 : wisepicker153@gmail.com 감사합니다.',1,'뮤비/CF',NULL,'2024-03-18 14:03','2024-03-19','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(73,'3월 19일 강남 13~22시 화장품 바이럴 광고 연출팀 구인 인쇄','3월 19일 13시부터 22시까지 강남구 역삼동에서 촬영 진행 예정입니다. 화장품 촬영 구인_연출팀 1명 010 3309 3998로 연락 주시면 감사하겠습니다',1,'뮤비/CF',NULL,'2024-03-18 11:03','2024-03-19','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(74,'내일 18일 수도권내 뮤비 조명팀 1분 구합니다 인쇄','영등포쪽으로 넘어오시면 됩니다  유경험자 지원 바랍니다  어퓨처계열 사용합니다  메일로 주세요~~',1,'뮤비/CF',NULL,'2024-03-17 11:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(75,'3월 19일 스튜디오 아이돌 콘서트 VCR 영상 촬영 보조 인쇄','안녕하세요,  3월 19일 화요일 경기도 포천 근처 스튜디오에서 아이돌 콘서트 VCR 영상 촬영 보조 모집합니다.  보조 인원 : 1 명 촬영 업무 : 차량 운전 과 촬영 장비 세팅 로닌 RS2 + Raveneye 사용 가능자 자차 보유 운전 가능자 우대 진행비(유류비, 통행료 전액지원) 촬영 시간 : 10h - 22h 개인 장비 없이 몸만 오시면 됩니다 페이는 촬영끝나후 지급합니다.  메일로(imlee18@naver.com) 간단한 소개 와 경험 함께 지원 부탁드리겠습니다. 감사합니다',1,'뮤비/CF',NULL,'2024-03-16 13:03','2024-03-18','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(76,'광고 영상 프로덕션 Better-Taste에서 함께 할 인재를 찾습니다 인쇄','안녕하세요 Better-Taste 대표 고인곤 입니다.  저흰 2009년 부터 패션, 뮤직비디오 업계에서 수년간 업력을 쌓아 오다가 2018년부터는 CF 연출을 메인으로 하는 프로덕션으로 성장했습니다.  주요 클라이언트로는 삼성, LG, 현대, 샤넬, 루이비통, 펜디, 토즈, 나이키, 컨버스, 캐논, JTBC, SM 등이 있습니다.  본인의 주 업무에 성실하게 임하되 끊임없이 새로운 것을 탐구하고 공부하며 다양한 업무를 경계 없이 할 수 있는 긍정적인 마인드를 가진 분이랑 일하고 싶습니다.  그래서 저 또한 광고 연출이 주 업무 이지만 촬영 감독으로 현장에 나갈 때도 있으며 현장에서 연출을 하면서 메인 포토를 찍기도 하고 편집과 2D, 색보정을 직접 할 때도 있으며 조감독이 연출하는 건이 생기면 촬영 감독으로 현장에서 도와주기도 합니다.  앞으로 Better -Taste의 색깔을 좀 더 확실하게 표현 하고 싶어서 개성있고 감각적인 인재들을 모집하고자 합니다. 많은 지원 부탁드립니다.  www.better-taste.com    [자격요건] - 성별, 학력, 나이 제한 없음 - 고용형태 : 3개월의 수습 기간 이후 업무평가를 통해 정규직 전환 결정 - 근무지 : 서울 성동구 성수동 (뚝섬역 도보 8분) [우대 사항] - 유관업무 경력자로 영상업을 잘 이해 하고 능동적이신 분 - 영상을 사랑하시는 분 - 개성있고 감각적인걸 좋아하시는 분 - 운전 가능하신 분 - 영어 가능하신 분  [채용 부문] - 1st / 2nd 조감독 - 편집 실장, 팀장급  [제출서류 및 신청방법] - 자유 형식의 이력서 1부 (희망 급여 기재 ) - 본인이 좋아하고 영향을 받은 문화, 음악, 영화, 감독, 브랜드 등을 자유롭게 서술 해 주시기 바랍니다 - 다운로드 할 수 있는 파일이나 또는 온라인에서 확인 가능한 포트폴리오 본인이 참여한 역할이나 업무 표기 Word, PDF, Notion 등 열람 가능한 url 링크 hwp 파일은 지양합니다 [접수 및 제출] love@better-taste.com 접수 시 메일 제목에 [Better-Taste (지원 분야) 경력 / 이름] 표기해 주세요 [모집 기간 및 일정] - 1차 서류 - 2차 면접 후 - 채용 결과 통보 메일 접수 후 서류 합격자에 한해서 개별 연락 드립니다 많은 지원 부탁 드립니다   ',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(77,'[급구] 뮤직비디오 콘티작가 인쇄','안녕하세요 콘티작가님 급하게 구하고 있습니다 유튜브 콘텐츠에 올라는 4분 정도의 뮤직비디오 입니다 대외비라서 상세한 내용은 컨택후에 알려 드리겠습니다  장당 : 1만원  으로 구하고 있습니다  yobg20@naver.com 으로 작업하신 포트폴리오와 함께 보내주시면 연락 드리겠습니다  감사합니다',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(78,'3월18일(월) 성수동, 상업 아트 필름 촬영, 촬영팀 스텝 구인합니다. 인쇄','안녕하세요.  3월18일(월) 08:00 ~ 18:00 성수동 스튜디오에서 진행되는, 상업 아트필름, 촬영팀 구인합니다.  촬영팀 막내역할입니다.  감사합니다.',1,'뮤비/CF',NULL,'2024-03-15 18:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(79,'[마감] 3월 18일 월요일 촬영 / 현장 연출부 남자 3분 구합니다 인쇄','촬영일자 : 3월 18일 월요일 (1회차) 촬영시간 : 11시~17시 30분 종료 위치 : 용인시 처인구 소재 스튜디오  *서울 강남구에서 모여 연출봉고 차량 탑승해 같이 출발 예정  미술 소품 옮기는 거 위주로 진행해주시면 됩니다. 운동기구와 같이 무거운 것들이 있어 참고 부탁드립니다.  문자로 간단한 이력서 보내주세요. -------------------------------------- 지원 마감됐습니다. 감사합니다.',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(80,'니가그립다에서 같이일할 팀원구합니다 인쇄','니가그립다에서 같이 일할 팀원 구합니다.  급여는 세금포함해서 회차당30 12시간 기준으로 1시간당 일당의 10%가 오버로 책정되며 광고특성상 입금은 늦게 될수 있습니다.  지원 조건 나이 25~32세 자차보유 하고있어야합니다.  저한테 문자로 이름, 나이, 사는지역, 경력사항 보내주시면 현장 티오 모자랄때 인원보충식으로 연락드리겠습니다. 팀원으로 지원하시는 분들도 인원보충식으로 나오셔서 일하시면서 현장분위기 파악하시고 지원하시면 됩니다.',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(81,'(구인완료) 아이돌 MV 스토리보드 작가 섭외합니다. 인쇄','안녕하세요 팀 owgeded의 조연출 최대혁입니다. 여자아이돌 뮤직비디오 건으로 스토리보드작가님을 섭외합니다.  100~150컷 정도의 분량입니다! (촬영 예정일은 3월 22일에 진행됩니다)  포트폴리오, 경력기술서를 첨부하여 아래 메일로 지원 부탁드립니다!  choirio713@gmail.com 보내주시면 메일 검토 후 개별 연락으로 디테일한 내용 전달해드리겠습니다  메일 제목은 - [오위디드] 팀명(성함)으로 지원 부탁드립니다  감사합니다!',1,'뮤비/CF',NULL,'2024-03-19 17:03','2024-03-16','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(82,'상반기 신인 남자아이돌 그룹 뮤직비디오 촬영 /조명 감독 구인 인쇄','능력 출중하시고 , 재능 넘치시는 분들 지원 너무 감사합니다 . 덕분에 상황에 맞게 맞춰나갈수있는 분들 컨택이 어느정도 이루어져 연락이 닿은 팀들과 상황에 맞게 미팅을 해 나갈 예정이라 일찍 마감하게 되었습니다 , 일일히 연락 드리지 못해 죄송합니다  ',1,'뮤비/CF',NULL,'2024-03-15 19:03','2024-03-25','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(83,'신인 여자 아이돌 뮤직비디오 조감독 님 구인중입니다. 인쇄','현재 프리 프로덕션 진행되고 있는 신인 여자 아이돌 뮤직비디오의 조감독 님 구인중입니다.  촬영 날짜는 4월 11-13일 중 3회차 예정입니다. 포트폴리오와 이력서 chaerin@ambienceseoul.com 으로 발송해주시면, 면밀히 검토 후 개별 연락 드리겠습니다. 프리 프로덕션이 진행되고 있는만큼, 즉시 일정 합류 가능하신 분들을 우선적으로 연락드리겠습니다.',1,'뮤비/CF',NULL,'2024-03-14 16:03','2024-03-17','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(84,'DI 색보정 실장님 구인합니다. 인쇄','뮤직비디오 DI 색보정 실장님 구인합니다.  3분 초반대의 뮤직비디오이며, 스튜디오에서 촬영되어 샷 환경이 크게 다르지 않지만 중간 중간 컬러 라이트 구간이 삽입되어 있습니다.  시네마틱한 컬러를 지향하며 레퍼런스 또한 준비되어 있습니다.  바로 작업이 가능해야하며, 1회 혼방이 필요합니다. 정확하게 확인할 수 있는 포트폴리오 링크와 함께 메일 부탁드립니다.  감사합니다.',1,'뮤비/CF',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(85,'[광고/캠페인/홍보/바이럴/콘텐츠] 대전-충청권 내 협업 가능한 촬영 및 편집 프리랜서 감독님 모집(여성 감독님) 환영 :-) 인쇄','안녕하세요. 광고 대행사입니다.  대전, 충청지역 내 협업이 가능한 촬영 및 편집 감독님분들을 찾고 있습니다!  - 제작영상 : 광고, 캠페인,홍보영상,바이럴,콘텐츠,스케치 등 - 모집분야 : 촬영 및 편집 / 한 분야만도 가능 - 제작단가 : 과업 금액 및 퀄리티에 따라 조율 - 거주지역 : 가능하시다면 대전, 충청 지역내  위의 이메일로 직접 제작하신 포트폴리오 첨부해주시면 감사드리겠습니다. 많은 연락 부탁드립니다 ^^',1,'뮤비/CF',NULL,'2024-03-12 19:03','2024-03-15','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(86,'3월말 저예산 MV 아트팀 1팀 모셔봅니다. 인쇄','프리프로덕션은 마친 상태입니다!  제작할 것들은 없고, 1톤 탑차에 실어 렌탈. 배치. 설치정도되는 소품양정도로 보고 있습니다. 견적은 모든 부분 협의 가능합니다~~  간략히 소개해서 보내주시면 연락드리겠습니다!',1,'뮤비/CF',NULL,'2024-03-12 13:03','2024-03-23','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(87,'인디 힙합 R&B 오피셜 뮤직비디오 미술감독님 구인합니다! 인쇄','인디 힙합 R&B 오피셜 뮤직비디오 촬영 미술감독님을 구합니다! 여자배우 1명 촬영 예정,회차당 10시간 촬영 예정입니다. 예산이 많지 않지만 프로듀서랑 뮤지션, 배우 모두 이름 있으신 분이라 함께 하시면 좋은 그림 만들 수 있을거라 확신합니다. 페이는 최대한 선제시 해주시면 감사하겠습니다. 장비 및 소모품은 따로 예산 책정할 계획입니다. 뮤직비디오 기획은 아직 프리 초기 단계라 완성 되지는 않았지만 하단에 무드 보드 첨부 해놨습니다. 함께 하시게 되면 구체화된 기획안과 함께 로케이션 헌팅, 키스텝 미팅 등 진행 하며 함께 좋은 작품 만들 수 있도록 원활한 커뮤니케이션을 진행 할 예정입니다 :) 많은 지원 부탁드립니다! ',1,'뮤비/CF',NULL,'2024-03-12 00:03','2024-03-15','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(88,'인디 힙합 R&B 오피셜 뮤직비디오 조명 구인 인쇄','인디 힙합 R&B 오피셜 뮤직비디오 촬영에 조명 감독님을 모십니다.  여자배우 1명 촬영할 예정입니다. 그리고 회차당 10시간 촬영 예정입니다.  예산이 많지 않지만 프로듀서랑 뮤지션, 배우 모두 이름 있으신 분이라 함께 하시면 좋은 그림 만들 수 있을거라 확신합니다.  페이는 선제시로 같이 작성해 주시면 감사하겠습니다. 장비 및 소모품은 따로 예산 책정할 계획입니다.  메일 통해서 포트폴리오와 페이 작성하여 보내주시면 감사하겠습니다.  뮤직비디오 기획은 아직 프리 초기 단계라 완성 되지는 않았지만 하단에 무드 보드 첨부 해놨습니다.  함께 하시게 되면 구체화된 기획안과 함께 로케이션 헌팅, 키스텝 미팅 등 진행 하며 함께 좋은 작품 만들 수 있도록 원활한 커뮤니케이션을 진행 할 예정입니다 :)  많은 지원 부탁드립니다!   ',1,'뮤비/CF',NULL,'2024-03-11 23:03','2024-03-15','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(89,'힙합 뮤직비디오 여자 배우 구합니다 인쇄','안녕하세요 뮤직비디오 디렉터 필름 사무라이입니다  현재 뮤직비디오에 출연할 여자 배우를 구하고 있습니다 페이는 10만원에 촬영 시간은 3시간에서 5시간 정도 될 것 같습니다  래퍼분이 해외 분이라서 약간의 터치에 프리 하신 분 구하고 있습니다. 연락 부탁드립니다',1,'뮤비/CF',NULL,'2024-03-11 06:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(90,'[YOUTUBE] 신규 남성 패션브랜드의 유튜브 채널을 함께 오픈할 촬영&편집 크루 찾습니다! 인쇄','안녕하세요 오피스 워크웨어 패션 브랜드의 YOUTUBE채널을 함께 오픈할 촬영&편집자&PD 분을 모집합니다.  컨텐츠는 20대부터~50대에 남성 분들을 업무현장에 방문하여 업무환경 일하는 모습 등 인터뷰 컨텐츠를 제작 중에 있습니다.  지원: 경력사항을 e-mail로 보내시면 참고하여 자세한 내용과 함께 유선상으로 말씀 드리겠습니다.  감사합니다.   ',1,'유튜브/기타',NULL,'2024-04-01 13:04','2024-04-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(91,'저예산 포트폴리오 영상 제작 조명 감독님 구인합니다. 인쇄','안녕하세요. 영상 제작사 Focuspoint 입니다. 저희가 이번에 포트폴리오로 유모차 및 카시트 세탁 관련한 영상을 제작하게 되었습니다.  제품보다는 인물 위주의 형태로 영상 제작될 예정이며 등장인물은 1~2인입니다. 아래 레퍼런스 링크 첨부해드립니다.  - 조명감독님 + 조명(씨네로이드 1~2개, 300X급 1개) 저예산 포트폴리오 제작이기에 금액을 제대로 드리지 못하는 점 죄송합니다. 다만, 이제 막 스타트업한 기업이기에 도움 주시면 앞으로 더 좋은 기회로 만나뵐 수 있도록 노력하겠습니다. ※ 촬영일시 : 24년 04월 8일 ~ 9일 (중 1회차) ※ 촬영장소 : 수도권내 스튜디오 + 야외(스튜디오 인근) ※ 촬영시간 : 5~6시간 촬영 예정 ※ 촬영내용 : 인물 중심의 홍보영상 촬영 ※페이 : 1회차당 35만원 ※ 지원방법 ※ - qodudqls8@focus-point.co.kr 으로 메일 보내주세요. - 메일 제목은 [우리아이 - 조명] - 연락 가능한 연락처는 꼭 기입하여 주시기 바랍니다. - 간단한 포트폴리오 정보 주시면 좋을 것 같습니다. 많은 지원과 도움 부탁드리겠습니다. 감사합니다.',1,'유튜브/기타',NULL,'2024-04-01 11:04','2024-04-05','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(92,'[구인완료]이번 주 바로 투입 가능한 촬영 감독님(팀) 2명(메인급 1, 서브1 2인 1조) 구합니다. 수입차 브랜드 자동차 유튜브 인쇄','안녕하세요. 수입차 브랜드 유튜브 채널 운영을 맡아 촬영 팀을 섭외하고 있습니다. 기존 컨택중인 촬영팀이 사정이 생겨 급하게 섭외하게 됐습니다.ㅜ  월 촬영은 1~2건 계획돼 있으며 일당 촬영 시간은 최소 3~4시간 또는 현장 상황에 따라 좀 더 길어 질 수도 있습니다. (전시장에서 촬영하는 경우가 많을 예정이라 변수들이 종종 발생합니다.)  당장 이번 주 촬영은 콘텐츠 디자인 작업 때문에 2~3편을 촬영할 예정입니다. (기획/디렉팅은 저희쪽에서 담당하기에 촬영만 담당해주시면 됩니다.)   현재 기획중인 촬영 구성은 아래 레퍼런스 수준으로 참고하시면 될 거 같습니다.  1   2  -촬영 내용 : 토크쇼 콘셉트와 차량 리뷰 촬영 토크쇼 톤앤매너는 2번 레퍼 참고하시면 되고, 차량 리뷰는 일반적으로 생각하시는 자동차 리뷰 느낌입니다.    -우대 사항 : 자동차 브랜드 영상 촬영 경험 또는 핸드짐벌 촬영 가능하신 분/FX3 촬영 가능하신 분 평소 차량에 대한 관심과 시내/외부 촬영 노하우가 좀 있으신 분들이 수월할 거 같습니다. 촬영 장비는 기본적으로 저희 장비를 사용할 예정입니다. (보유중이신 장비 투입 요청드릴 수도 있습니다) ㄴ메인급 감독님은 간단한 짐벌 촬영도 가능하면 좋겠습니다.(레퍼런스 1번 영상 참고) ㄴ유튜브 콘텐츠다보니 극상의 촬영 퀄리티를 요하진 않습니다.  -모집 방식 : 이메일 통해 포트폴리오&보유 장비 목록 함께 지원 현 상황에선 가능하면 각개보단 2인 구성 1팀을 선호합니다만 실력&자신있는 프리랜서분들도 메일 주시면 검토하겠습니다.    *점심시간 걸쳐 촬영하는 경우엔 식사 제공해드립니다. *당연한 얘기지만 시간 엄수/매너 좋으신 분들을 선호합니다. *합이 잘 맞고 결과물 클라이언트 만족 시 촬영 외 편집 업무까지 의뢰드릴 수 있습니다.',1,'유튜브/기타',NULL,'2024-04-01 13:04','2024-04-03','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(93,'*채용*[잼실용음악학원/홍대] 영상 제작/편집 & 콘텐츠 디자인 사원 인쇄','홈페이지: http://www.jam.or.kr/main/main.php 유투브: http://www.youtube.com/@JAM_MusicAcademy 인스타그램: http://www.instagram.com/jam_music_academy/         지원서 다운로드 https://drive.google.com/drive/u/3/folders/1cQVGisGoeV2aPtTgpdz-tOdvXCGQ91Fc',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(94,'(주)에스엘알렌트 카메라 렌탈파트 정직원 채용 공고 인쇄','에스엘알렌트 렌탈파트 정직원 채용  에스엘알렌트 정규직 채용 공고 카메라 대여 문화를 선도하는 기업 (주)에스엘알렌트의 비전을 함께 공유할 수 있는 유능한 인재들을 모십니다.  □채용 인원 - 정규직 00명  □정규직 모집분야 - 렌탈파트 (장비렌탈 및 관리)  □근무시간 및 연봉 - 근무시간 : 주 5일 근무(주말포함), 오전-오후 격주 교대 근무 (주 평균 50시간) 오전조(목,금) : 07:30-17:00, 토 07:30-14:00 /★주 2회(월,화) 풀근무 (07:30-22:30) : 주 48.5시간 오후조(목,금,토) : 13:00-22:30/★주 2회(일,수) 풀근무 (07:30-22:30) : 주 51.5시간  신입 - 월급 : 입사 시 2,800,000원(6개월), 이후 3,000,000원(12개월) - 연봉 : 34,800,000원~36,000,000원(퇴직금 별도)  경력 * 경력직은 전직장 기준 110% 이상 보장  ■ 자격조건 - 학력/경력/나이 무관  ■ 우대사항 - 촬영장비 방송, 음향 관련학과 혹은 관련 업계 경험자 우대  ■ 근무지역 - 강남점 : 강남역 1번 출구 앞 - 합정점 : 합정역 3번 출구 앞  ■ 복리후생 - 주 5일 근무 - 1년에 15일 연차 사용 가능 - 장기근속자 포상, 우수사원 포상 - 업무 능력 및 성과도에 따라 조기 진급 - 경조금, 경조 휴가 - 생일자 상여금 지급 - 직원 전용 스낵바 제공 - 부서 별 회식비 지원 - 석식 제공 - 설해원, 아난티, 파라스파라 리조트 이용 - 명절선물, 유니폼 제공 - 야근 시 안전귀가 택시비 지급 - 관련 분야 전시회 및 세미나 참석 기회 제공 - 근로기준법에 맞는 근로계약서를 작성하며 근로기준법을 준수 - 법정 공휴일 제공 (대체 휴일 적용) * 에스엘알렌트 모든 장비 무료 사용가능  □제출 서류 - 이력서 - 자기소개서  □제출 방법 - E-mail 접수 : recruit@slrrent.com - 홈페이지 : http://www.SLRRENT.com - 블로그 : http://slrrent.blog.me/221349071072 - 연락처 : 1600-2470  에스엘알렌트의 꿈은 아주 큽니다 그 속에서 함께 성장하실 분이라면 누구든 환영합니다. 관심있는 분들의 많은 지원 부탁드립니다.  ✔카메라 대여 문화를 선도하는 기업 #에스엘알렌트 ✔합정100평 층고6m 대형 호리존 #에스엘알스튜디오 ✔하이엔드 촬영장비 렌탈도 역시 #에스엘알씨네  (주)에스엘알렌트 강남점 - 서울시 강남구 테헤란로 6길 9, 장원빌딩 1층 합정점 - 서울시 마포구 양화로 10길 37, 에스엘알빌딩',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(95,'사진 촬영 어시스턴트 이틀 구합니다 인쇄','안녕하세요. 단체 프로필 촬영에 어시스턴트 한분 구합니다. 페이는 각각13만원 이며, 4월 3일 11일 양일이며 시간은 7시 반 ~ 오후 7 시 까지입니다. 위치는 강남 역삼동 입니다.  처음 조명 세팅 이후 세팅 변경이 없습니다~ 가능하신 분 연락주세요  kimsehunstudio@naver.com',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(96,'신규 유튜브 채널 스케치코미디 시나리오 작가님 모집합니다. 인쇄','1. 유튜브 채널 : 4월 중으로 개설 예정  2. 장르 : 하이퍼리얼리즘 스케치코미디  3. 목적 : 자체적인 콘텐츠 파워로 채널 성장 후, 현재 협력중인 업체 제품들을 하나씩 PPL로 녹여 에피소드를 만드려고 합니다.  4. 페이 페이는 업력에 따라 조율 가능하니 편하게 제안 부탁드립니다.  유튜브에 스케치코미디폼으로 진행된다는 것만 확정된 상태입니다. 사내 콘텐츠 마케터가 전체적인 기획안을 작성할 수 있으나, 자체적으로 기획까지 가능한 작가님이면 더 좋을 것 같습니다.  게시글은 자주 확인하지 못하니 메일로 많은 지원 부탁드립니다. 감사합니다.',1,'유튜브/기타',NULL,'2024-03-30 19:03','2024-04-14','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(97,'당일 헤어 메이크업 구합니다. 인쇄','4월8일 경기 안성팜랜드에서 컬럼비아 아웃도어 의류 촬영합니다.  남성모델 1명, 여성모델 1명이며 헤어 및 메이크업 가능하신분 구합니다.  페이는 10만원(3.3%)+중식이며 9시 집결지 픽업하여 10시부터 촬영시작, 17시 촬영마감입니다.  가능하신분은 경력 및 거주지, 번호, 포토폴리오 메일로 보내주시면 감사드리겠습니다.',1,'유튜브/기타',NULL,'2024-04-03 22:04','2024-04-01','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(98,'애펙 모션그래픽 가능하신분! 인쇄','애프터이펙트 등으로 모션그래픽 만들어주실 프리랜서 아티스트 찾습니다!  첨부해드린 사진처럼 인물옆에 키와 메시지 창만 화면에 떠있고, 키패드를 눌러가며 메시지 몇글자가 입력되면 됩니다~ 미팅없이 비대면 작업 가능하세요.  -작업기간 : 3.30(토) ~ 4월초 -페 이 : 협의 (1차 수정 후 바로 입금/ 입금일 협의 가능)  <아래 내용을 메일로 보내주시면, 개별 연락드립니다> -성함, 휴대폰번호 -기존에 작업하신 영상파일 또는 링크 -메일 주소 : wkdvus@kakao.com ',1,'유튜브/기타',NULL,'2024-03-29 18:03','2024-04-08','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(99,'공간스타일리스트,미술감독님 구인합니다 인쇄','안녕하세요 공간스타일리스트,미술감독님을 구인합니다. 4월중 촬영이며 납골당(봉안당) 프리미엄 업체에 맞는 봉안함 미술잡아 주시면됩니다. 4월1일 현장로케 미팅 가능하신분 지원 부탁드립니다 빠른 회신 드리겠습니다! 위에 메일로 포폴 부탁드립니다!',1,'유튜브/기타',NULL,'2024-03-29 18:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(100,'스케치코미디를 기반으로 하는 유튜브 컨텐츠 시나리오 작가님 함께해요(신입가능) 인쇄','스케치코미디를 기반으로 여러 영상을 만들어갈 시나리오 대본 작가 멤버를 모집합니다(유튜브 컨텐츠 릴스 먹방 챌린지 등)  회의 모임장소는 구로 신도림역 근처입니다  회의는 화요일 or 수요일 저녁 7시 반 이후로 할 예정이고  촬영도 회의를 통해 정하고 평일 위주로 그리고 저녁 위주로 할 예정입니다  함께 만들어갈 멤버들의 많은 연락부탁드립니다  대본 시나리오 작가 위주의 분들 또 연기(시도)도 가능합니다 일당백으로 함께하실 분 함께 해요  들어오셔서 성함 나이 사는곳 지원분야 즐겨보는 스케치코미디채널 , 좋아하는 채널을 적어주세요  https://open.kakao.com/o/sGIWVR3e',1,'유튜브/기타',NULL,'2024-03-29 14:03','2024-04-03','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(101,'<그래픽 노블> 집필 파트너 / 스토리보드 작가 찾습니다 인쇄','안녕하세요 그래픽 노블 작업중인 작가입니다.  시나리오는 30% 진행중에 있고 집필을 같이 하실 시나리오 작가분을 구합니다  공동작업으로 시나리오 집필을 하거나 콘티 만들때 같이 협업해서 완성할 수 있는 분 찾고 있습니다 온/오프라인 미팅 협의 가능하고 일주일에 1회~2회 작업에 대해 이야기 할 수 있으면 좋을 것 같습니다.  그림은 제가 그릴 예정이고 1) 예술 전공이 아니시더라도 문학, 철학, 심리를 공부 하셨거나 2) 영화를 많이 보신분 또는 책을 읽으시는 분 3) 혹은 여러 방면에서 통찰력이 있으신분  셋중 하나이기만 하거나 그래픽노블에 관심이 있으신분과 함께 할 수 있다면 정말 좋을것 같습니다. 이메일로 이력서나 짧은 소계글 또는 이전에 작업하신 작품/ SNS 계정 링크 첨부하셔서 연락 주시면 감사하겠습니다! underdog.ground21@gmail.com  감사합니다 ',1,'유튜브/기타',NULL,'2024-03-29 23:03','2024-04-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(102,'AI 휴먼(가상인간) 실루엣 촬영에 참여하실 20~30대초 한국 남성 모델/배우 분 모집합니다. 인쇄','AI휴먼(가상인간) 실루엣 학습에 참여하실 20~30대초 한국 남성 모델/배우 분 모집합니다.  * 모집분야 : AI휴먼(가상인간)의 실루엣 학습에 사용될 실루엣 촬영 * 촬영일정 : 4월19일, 4월26일 중 평일 1일 (1인 약 6시간 예정, 시간에 따라 금액 변경) * 촬영장소 : 서울 특별시 서초구 법원로 4길 40 B1 * 특이사항 : * 가상인간 모델촬영 * 한국인 20대~30대 초 남자 1 분 * 컨셉: 훈훈한 컨셉, 캐주얼 복장이 잘 어울리는 사람 (헤어메이크업 제공) * 촬영은 약 6시간 예정 (점심 제공) * 간단한 안내 동작 및 연설하는듯한 제스쳐 * 영상 촬영에 익숙하신 분을 희망하고 있습니다  * 연락처 : funfuldavid@cnai.ai  위 메일로 프로필 보내주시면 확인 후 연락 드리겠습니다. 감사합니다!',1,'유튜브/기타',NULL,'2024-03-29 13:03','2024-04-05','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(103,'(빵빵이의 일상)느낌의 병맛 잘 뽑는 작가님 모십니다. 인쇄','안녕하세요. 유튜브를 기획하고 있는 작은 회사입니다.  이번에, 14세 이상부터 볼 수 있는, 병맛 인형극을 제작하려 합니다.  컨셉은 캐리언니같은 여성분이 나와서, 장난감(레고)로 상황극을 하는거에요.  1회당 5~10분 정도 의 분량이면 됩니다.  다른 스텝 및 배우는 다 구성이 되어있습니다. 개연성만 있다면 저세상 드립이라도 상관 없습니다.  페이는 협의 해야하지만, 편당 10~20으로 생각하고 있습니다.  많은 지원 부탁드립니다. 감사합니다.',1,'유튜브/기타',NULL,'2024-03-30 13:03','2024-04-10','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(104,'유튜브 예능,스케치,모션 편집 각 파트별 연간으로 진행해주실 편집자분 구합니다 인쇄','*연간으로 함께 할, \"실력있는 편집자\" 구합니다.  재택에서 작업해주시면 되며 건바이건으로 연락드리며 작업 예정입니다 꾸준하게 일이 있어 계속 같이하실분 구합니다  메일 보내주실때 제목에 지원파트 (모션,예능,스케치) 적어서 보내주시면 됩니다 100% 작업하신 작업물로 보내주시면 확인 후 개별 연락드리겠습니다! ',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(105,'유튜브 작가님 모십니다. 인쇄','안녕하세요?  유튜브 영상 시나리오 도움주실 작가님 모십니다.  페이는 업무 협의 후 함께 조율해서 진행하려 합니다.  매주 주 1회 한편씩 촬영할 예정입니다.  cs@factory-platform.com 으로 성함과 연락처, 이력서 보내주세요.  감사합니다!',1,'유튜브/기타',NULL,'2024-03-29 11:03','2024-04-30','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(106,'*채용*[잼실용음악학원/홍대] 영상 제작/편집 & 콘텐츠 디자인 사원 인쇄','홈페이지: http://www.jam.or.kr/main/main.php 유투브: http://www.youtube.com/@JAM_MusicAcademy 인스타그램: http://www.instagram.com/jam_music_academy/         지원서 다운로드 https://drive.google.com/drive/u/3/folders/1cQVGisGoeV2aPtTgpdz-tOdvXCGQ91Fc',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(107,'관광공사 홍보영상 스타일리스트 1분 구합니다 인쇄','안녕하세요!  4월 3일 과 4일 사용할 의상으로 하루 3명이어서 3착씩 2일 총 6벌 입니다  각나라 국적별 느낌 살려 힙한 느낌의 의상으로 구성하려고 합니다. 총 금액은 80만원입니다 (따라 안오셔도 되고 의상만 전달해주시면 됩니다!)  메일로 보내주시면 확인 후 연락드리겠습니다 감사합니다.',1,'유튜브/기타',NULL,'2024-03-28 18:03','2024-03-31','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(108,'웹예능 <회포자>에서 피디님 모집합니다! 인쇄','*웹예능 <회포자> PD 구인* https://www.youtube.com/watch?v=cVOqQKxWpxI&t=260s 기획, 촬영, 편집 같이 하실 PD님 모집합니다! 15분 분량의 웹예능 본편 편집 진행하실 수 있는 분 지원 부탁드리겠습니다  - 경력 : 유관 업무 경력 우대 (2-4년 차) - 모집 인원 : 1명 - 근무 형태 : 프리랜서 - 급여 : 면접시 협의 - 근무 회사 : 플래디 (디지털 콘텐츠 제작사) / https://pladi.tv - 근무지 : 성수동 - 지원기간 : 채용시 마감 아래의 메일로 이력서와 포트폴리오 부탁드립니다.  yb.kim@pladi.tv  * 이력서 확인 후 면접 대상자분들께 추후 개별적으로 연락드리겠습니다',1,'유튜브/기타',NULL,'2024-03-28 18:03','2024-04-07','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL),(109,'*채용*[잼실용음악학원/홍대] 영상 제작/편집 & 콘텐츠 디자인 사원 인쇄','홈페이지: http://www.jam.or.kr/main/main.php 유투브: http://www.youtube.com/@JAM_MusicAcademy 인스타그램: http://www.instagram.com/jam_music_academy/         지원서 다운로드 https://drive.google.com/drive/u/3/folders/1cQVGisGoeV2aPtTgpdz-tOdvXCGQ91Fc',1,'유튜브/기타',NULL,'상시 모집','상시 모집','2024-04-03 21:28:32','2024-04-03 21:28:32','F',NULL,NULL,NULL);
/*!40000 ALTER TABLE `recruitment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reason` varchar(255) DEFAULT NULL,
  `reporter_id` bigint NOT NULL,
  `reportee_id` bigint NOT NULL,
  `image_link` varchar(255) DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Member_TO_report_1` (`reporter_id`),
  KEY `FK_Member_TO_report_2` (`reportee_id`),
  CONSTRAINT `FK_Member_TO_report_1` FOREIGN KEY (`reporter_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_Member_TO_report_2` FOREIGN KEY (`reportee_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `recruitment_id` bigint NOT NULL,
  `member_id` bigint NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_Recruitment_TO_Wishlist_1` (`recruitment_id`),
  KEY `FK_Member_TO_Wishlist_1` (`member_id`),
  CONSTRAINT `FK_Member_TO_Wishlist_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FK_Recruitment_TO_Wishlist_1` FOREIGN KEY (`recruitment_id`) REFERENCES `recruitment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,1,3,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(2,1,2,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(3,1,5,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(4,2,1,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(5,3,5,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(6,4,2,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(7,4,1,'2024-03-28 09:45:58','2024-03-28 09:45:58'),(9,5,13,'2024-04-03 08:45:44','2024-04-03 08:45:44'),(13,5,21,'2024-04-03 20:14:44','2024-04-03 20:14:44'),(14,1,21,'2024-04-03 20:14:46','2024-04-03 20:14:46'),(15,57,21,'2024-04-03 22:58:07','2024-04-03 22:58:07'),(16,84,21,'2024-04-03 22:58:09','2024-04-03 22:58:09'),(17,31,21,'2024-04-04 00:07:18','2024-04-04 00:07:18'),(18,10,34,'2024-04-04 00:31:35','2024-04-04 00:31:35'),(19,37,34,'2024-04-04 00:31:38','2024-04-04 00:31:38');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-04  1:58:40
