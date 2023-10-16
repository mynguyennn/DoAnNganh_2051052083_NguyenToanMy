-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: hospitaldb
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chi_tiet_dv`
--

DROP TABLE IF EXISTS `chi_tiet_dv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_dv` (
  `id_chitietDV` int NOT NULL AUTO_INCREMENT,
  `id_pdk` int DEFAULT NULL,
  `id_dv` int DEFAULT NULL,
  PRIMARY KEY (`id_chitietDV`),
  KEY `id_DV_idx` (`id_dv`),
  KEY `id_pdk_idx` (`id_pdk`),
  CONSTRAINT `id_DV` FOREIGN KEY (`id_dv`) REFERENCES `dich_vu` (`id_dv`),
  CONSTRAINT `id_pdk` FOREIGN KEY (`id_pdk`) REFERENCES `phieu_dang_ky` (`id_phieudk`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_dv`
--

LOCK TABLES `chi_tiet_dv` WRITE;
/*!40000 ALTER TABLE `chi_tiet_dv` DISABLE KEYS */;
INSERT INTO `chi_tiet_dv` VALUES (90,193,7),(91,194,2),(92,194,1);
/*!40000 ALTER TABLE `chi_tiet_dv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_thoi_gian_truc`
--

DROP TABLE IF EXISTS `chi_tiet_thoi_gian_truc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_thoi_gian_truc` (
  `id_chi_tiet_tg_truc` int NOT NULL AUTO_INCREMENT,
  `id_tk` int DEFAULT NULL,
  `id_tg_truc` int DEFAULT NULL,
  `ngay_dky_truc` date DEFAULT NULL,
  `trang_thai_truc` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_chi_tiet_tg_truc`),
  KEY `id_tg_truc_idx` (`id_tg_truc`),
  KEY `taikhoan_idx` (`id_tk`),
  CONSTRAINT `id_tg_truc` FOREIGN KEY (`id_tg_truc`) REFERENCES `thoi_gian_truc` (`id_tgTruc`),
  CONSTRAINT `taikhoan` FOREIGN KEY (`id_tk`) REFERENCES `tai_khoan` (`id_tk`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_thoi_gian_truc`
--

LOCK TABLES `chi_tiet_thoi_gian_truc` WRITE;
/*!40000 ALTER TABLE `chi_tiet_thoi_gian_truc` DISABLE KEYS */;
INSERT INTO `chi_tiet_thoi_gian_truc` VALUES (208,215,3,'2023-10-17',0),(209,215,3,'2023-10-19',0),(210,215,2,'2023-10-23',0);
/*!40000 ALTER TABLE `chi_tiet_thoi_gian_truc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chi_tiet_thuoc`
--

DROP TABLE IF EXISTS `chi_tiet_thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chi_tiet_thuoc` (
  `id_chitiet_thuoc` int NOT NULL AUTO_INCREMENT,
  `id_thuoc` int DEFAULT NULL,
  `id_phieukham` int DEFAULT NULL,
  `so_luong_sd` int DEFAULT NULL,
  `hdsd` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_chitiet_thuoc`),
  KEY `ChiTietThuoc_Thuoc_idx` (`id_thuoc`),
  KEY `ChiTietThuoc_PhieuKhamBenh_idx` (`id_phieukham`),
  CONSTRAINT `ChiTietThuoc_PhieuKhamBenh` FOREIGN KEY (`id_phieukham`) REFERENCES `phieu_kham_benh` (`id_phieukham`),
  CONSTRAINT `ChiTietThuoc_Thuoc` FOREIGN KEY (`id_thuoc`) REFERENCES `thuoc` (`id_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chi_tiet_thuoc`
--

LOCK TABLES `chi_tiet_thuoc` WRITE;
/*!40000 ALTER TABLE `chi_tiet_thuoc` DISABLE KEYS */;
INSERT INTO `chi_tiet_thuoc` VALUES (1,1,NULL,3,'Ngày 1 viên'),(134,36,471,5,'Mỗi ngày 3 viên '),(135,32,472,5,'Ngày 3 lần '),(136,47,472,2,'Mõi ngày 1 viên ');
/*!40000 ALTER TABLE `chi_tiet_thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danh_gia_bs`
--

DROP TABLE IF EXISTS `danh_gia_bs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danh_gia_bs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `danh_gia` int DEFAULT NULL,
  `binh_luan` varchar(200) DEFAULT NULL,
  `id_bs` int DEFAULT NULL,
  `id_bn` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bs_idx` (`id_bs`),
  KEY `id_tk_bn_idx` (`id_bn`),
  CONSTRAINT `id_tk` FOREIGN KEY (`id_bs`) REFERENCES `tai_khoan` (`id_tk`),
  CONSTRAINT `id_tk_bn` FOREIGN KEY (`id_bn`) REFERENCES `tai_khoan` (`id_tk`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danh_gia_bs`
--

LOCK TABLES `danh_gia_bs` WRITE;
/*!40000 ALTER TABLE `danh_gia_bs` DISABLE KEYS */;
INSERT INTO `danh_gia_bs` VALUES (1,2,'Tận tình',214,216),(5,2,'Bác sĩ thân thiện ',214,217),(7,4,'Bác sĩ chăm sóc tốt ',215,217),(8,3,'Dịch vụ tốt nhân viên thân thiện ',214,216),(9,3,'Có tâm ',214,216),(10,1,'Nhân viên nhiệt tình ',214,216),(11,4,'Tận tậm với bệnh nhân',214,216);
/*!40000 ALTER TABLE `danh_gia_bs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dich_vu`
--

DROP TABLE IF EXISTS `dich_vu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dich_vu` (
  `id_dv` int NOT NULL AUTO_INCREMENT,
  `ten_dv` varchar(45) DEFAULT NULL,
  `gia_dv` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id_dv`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dich_vu`
--

LOCK TABLES `dich_vu` WRITE;
/*!40000 ALTER TABLE `dich_vu` DISABLE KEYS */;
INSERT INTO `dich_vu` VALUES (1,'Xét Nghiệm',2000000),(2,'Khám Tổng Quát',1000000),(3,'Răng Hàm Mặt',5000000),(4,'Xương Khớp',3000000),(7,'Tim',1500000);
/*!40000 ALTER TABLE `dich_vu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donvi_thuoc`
--

DROP TABLE IF EXISTS `donvi_thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donvi_thuoc` (
  `id_donVi` int NOT NULL AUTO_INCREMENT,
  `ten_don_vi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_donVi`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donvi_thuoc`
--

LOCK TABLES `donvi_thuoc` WRITE;
/*!40000 ALTER TABLE `donvi_thuoc` DISABLE KEYS */;
INSERT INTO `donvi_thuoc` VALUES (1,'Viên'),(2,'Vĩ'),(3,'Chai'),(4,'Lọ'),(5,'Tuýp');
/*!40000 ALTER TABLE `donvi_thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoa_don`
--

DROP TABLE IF EXISTS `hoa_don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don` (
  `id_hoadon` int NOT NULL AUTO_INCREMENT,
  `ngay_thanh_toan` datetime DEFAULT NULL,
  `tien_thuoc` decimal(10,0) DEFAULT NULL,
  `tien_dv` decimal(10,0) DEFAULT NULL,
  `tien_kham` int DEFAULT NULL,
  `id_phieudky` int DEFAULT NULL,
  PRIMARY KEY (`id_hoadon`),
  KEY `HoaDon_PhieuDKy_idx` (`id_phieudky`),
  KEY `tien_kham_idx` (`tien_kham`),
  CONSTRAINT `HoaDon_PhieuDKy` FOREIGN KEY (`id_phieudky`) REFERENCES `phieu_dang_ky` (`id_phieudk`),
  CONSTRAINT `tien_kham` FOREIGN KEY (`tien_kham`) REFERENCES `tien_kham` (`id_tienKham`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don`
--

LOCK TABLES `hoa_don` WRITE;
/*!40000 ALTER TABLE `hoa_don` DISABLE KEYS */;
INSERT INTO `hoa_don` VALUES (39,NULL,500000,1500000,1,193),(41,'2023-08-10 17:10:00',200000,150000,1,193),(42,'2023-09-09 15:10:00',500000,100000,1,194),(43,'2023-08-07 13:14:00',200000,150000,1,194);
/*!40000 ALTER TABLE `hoa_don` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loai_thuoc`
--

DROP TABLE IF EXISTS `loai_thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loai_thuoc` (
  `idloai_thuoc` int NOT NULL AUTO_INCREMENT,
  `ten_loai_thuoc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idloai_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loai_thuoc`
--

LOCK TABLES `loai_thuoc` WRITE;
/*!40000 ALTER TABLE `loai_thuoc` DISABLE KEYS */;
INSERT INTO `loai_thuoc` VALUES (2,' Giảm đau'),(3,' Dị ứng'),(4,'Tim mạch'),(5,'Da liễu'),(6,'Điều trị mắt'),(7,'Tai, mũi, họng');
/*!40000 ALTER TABLE `loai_thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieu_dang_ky`
--

DROP TABLE IF EXISTS `phieu_dang_ky`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieu_dang_ky` (
  `id_phieudk` int NOT NULL AUTO_INCREMENT,
  `id_bs` int DEFAULT NULL,
  `id_yt` int DEFAULT NULL,
  `id_bn` int DEFAULT NULL,
  `trangThai_dky` tinyint DEFAULT NULL,
  `chon_ngaykham` date DEFAULT NULL,
  `thoi_gian_taophieu` datetime DEFAULT NULL,
  `thoi_gian_kham` varchar(45) DEFAULT NULL,
  `id_pk` int DEFAULT NULL,
  `tu_choi` tinyint DEFAULT NULL,
  PRIMARY KEY (`id_phieudk`),
  KEY `id_pk_idx` (`id_pk`),
  KEY `id_bs_idx` (`id_bs`),
  KEY `id_yt_idx` (`id_yt`),
  KEY `id_bn_idx` (`id_bn`),
  CONSTRAINT `id_bn` FOREIGN KEY (`id_bn`) REFERENCES `tai_khoan` (`id_tk`),
  CONSTRAINT `id_bs` FOREIGN KEY (`id_bs`) REFERENCES `tai_khoan` (`id_tk`) ON DELETE SET NULL,
  CONSTRAINT `id_pk` FOREIGN KEY (`id_pk`) REFERENCES `phieu_kham_benh` (`id_phieukham`),
  CONSTRAINT `id_yt` FOREIGN KEY (`id_yt`) REFERENCES `tai_khoan` (`id_tk`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_dang_ky`
--

LOCK TABLES `phieu_dang_ky` WRITE;
/*!40000 ALTER TABLE `phieu_dang_ky` DISABLE KEYS */;
INSERT INTO `phieu_dang_ky` VALUES (193,214,213,216,1,'2023-10-10','2023-09-10 19:52:17','Trưa',471,NULL),(194,215,213,216,1,'2023-10-07','2023-09-07 20:21:29','Chiều',472,NULL),(200,215,213,216,1,'2023-10-19','2023-10-11 22:28:53','Chiều',NULL,NULL),(203,NULL,213,217,1,'2023-10-18','2023-10-15 19:46:12','Chiều',NULL,NULL);
/*!40000 ALTER TABLE `phieu_dang_ky` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phieu_kham_benh`
--

DROP TABLE IF EXISTS `phieu_kham_benh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phieu_kham_benh` (
  `id_phieukham` int NOT NULL AUTO_INCREMENT,
  `trieu_chung` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ket_luan` varchar(50) DEFAULT NULL,
  `ngay_kham_benh` date DEFAULT NULL,
  PRIMARY KEY (`id_phieukham`)
) ENGINE=InnoDB AUTO_INCREMENT=473 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phieu_kham_benh`
--

LOCK TABLES `phieu_kham_benh` WRITE;
/*!40000 ALTER TABLE `phieu_kham_benh` DISABLE KEYS */;
INSERT INTO `phieu_kham_benh` VALUES (1,'ho','ho nang','2023-09-06'),(2,'cảm','cảm nắng','2023-09-05'),(471,'Đau tim ','Đau tim nhẹ ','2023-09-10'),(472,'Đau đầu','Đau nữa đầu ','2023-09-10');
/*!40000 ALTER TABLE `phieu_kham_benh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `id_tk` int NOT NULL AUTO_INCREMENT,
  `ho_ten` varchar(45) DEFAULT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` varchar(45) DEFAULT NULL,
  `dia_chi` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  `tai_khoan` varchar(45) DEFAULT NULL,
  `mat_khau` varchar(1000) DEFAULT NULL,
  `avt` varchar(1000) DEFAULT NULL,
  `id_role` int DEFAULT NULL,
  PRIMARY KEY (`id_tk`),
  UNIQUE KEY `tai_khoan_UNIQUE` (`tai_khoan`),
  KEY `id_role_idx` (`id_role`),
  CONSTRAINT `id_role` FOREIGN KEY (`id_role`) REFERENCES `user_role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=222 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES (120,'admin','1993-01-01','Nam','Hồ Chí Minh','hmh20172018@gmail.com','0336334143','admin','$2a$10$4rVL8GVgPPhSicloXCSrE.eYsDSiUWnlIMYf6bBnsQrWk9Cl8DEea','https://res.cloudinary.com/diyeuzxqt/image/upload/v1691683103/kneho0d5yklvcdk3noko.png',1),(213,'Phan Thi Yen Vi','2002-07-18','Nữ','Go Vap','205105','0123456789','yta','$2a$10$yrCkeB2xxvteZ7HNu4AC8engM5X4rZv82KGoLVz5foWcwtv/HIKfS','https://res.cloudinary.com/diyeuzxqt/image/upload/v1694348297/x2q5khjn9yz3bklaliot.png',3),(214,'Nguyen Thi Thanh','2002-06-04','Nữ','Hoc Mon ','2051052046hoang@ou.edu.vn','0123456789','bacsi','$2a$10$59GratTIYC/yUd9Q9GhUW.u4qWTNqnWrUnKGAeBnDpxNA1dqQbzgS','https://res.cloudinary.com/diyeuzxqt/image/upload/v1694348482/t0bam9dftxhwti6s77he.png',2),(215,'Nguyen Thanh Thuyen','2002-05-22','Nam','Go Vap','2051052083my@ou.edu.vn','0123456789','bacsi1','$2a$10$txEV.ldcaL3RmYw7dGrszO04hEN2xIVfz0X5SIQQHwX0c6zQlw.vO','https://res.cloudinary.com/diyeuzxqt/image/upload/v1694350003/p6dalslbq7mazzugnfzj.png',2),(216,'Nguyễn Toàn Mỹ ','2002-11-02','Nữ','Go Vap','2051052083my@ou.edu.vn','0123456789','benhnhan','$2a$10$wHFKUITVYNmeKONo3CfG0.ccF0UBk04QGqcKIRa893aTfri.kyiq6','https://res.cloudinary.com/diyeuzxqt/image/upload/v1694350248/cacfzmxorinnkbxxedk7.jpg',4),(217,'Nguyễn Thị Ngọc Yến','2002-06-05','Nữ','Go Vap','2051052083my@ou.edu.vn','0123456789','benhnhan1','$2a$10$AqMCD440IOR1Z.1vYHXj2O9qjY.PS6.2WHLwoYW8MWCkf1HgkLt0W','https://res.cloudinary.com/diyeuzxqt/image/upload/v1694350304/pgpnej2ink6sm1pzbhma.jpg',4);
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thoi_gian_truc`
--

DROP TABLE IF EXISTS `thoi_gian_truc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thoi_gian_truc` (
  `id_tgTruc` int NOT NULL AUTO_INCREMENT,
  `buoi_truc` varchar(45) DEFAULT NULL,
  `bat_dau` time DEFAULT NULL,
  `ket_thuc` time DEFAULT NULL,
  PRIMARY KEY (`id_tgTruc`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thoi_gian_truc`
--

LOCK TABLES `thoi_gian_truc` WRITE;
/*!40000 ALTER TABLE `thoi_gian_truc` DISABLE KEYS */;
INSERT INTO `thoi_gian_truc` VALUES (1,'Sáng','08:00:00','12:00:00'),(2,'Trưa','12:00:00','18:00:00'),(3,'Chiều','19:00:00','23:59:00');
/*!40000 ALTER TABLE `thoi_gian_truc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thuoc`
--

DROP TABLE IF EXISTS `thuoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thuoc` (
  `id_thuoc` int NOT NULL AUTO_INCREMENT,
  `ten_thuoc` varchar(50) DEFAULT NULL,
  `xuat_xu` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `gia_thuoc` decimal(10,0) DEFAULT NULL,
  `don_vi` int DEFAULT NULL,
  `so_luong` int DEFAULT NULL,
  `loai_thuoc` int DEFAULT NULL,
  PRIMARY KEY (`id_thuoc`),
  KEY `don_vi_idx` (`don_vi`),
  KEY `loai_thuoc_idx` (`loai_thuoc`),
  CONSTRAINT `don_vi` FOREIGN KEY (`don_vi`) REFERENCES `donvi_thuoc` (`id_donVi`),
  CONSTRAINT `loai_thuoc` FOREIGN KEY (`loai_thuoc`) REFERENCES `loai_thuoc` (`idloai_thuoc`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thuoc`
--

LOCK TABLES `thuoc` WRITE;
/*!40000 ALTER TABLE `thuoc` DISABLE KEYS */;
INSERT INTO `thuoc` VALUES (1,'vitaminA','việt nam',25000,1,48,6),(30,'Vitamin C','Việt nam ',25000,2,50,7),(31,'Paracetamol','Mỹ ',50000,2,50,2),(32,'Tramadol','Canada',25000,4,45,2),(33,'	Cephalexin','Việt nam ',50000,1,50,3),(34,'Amoxicillin','Mỹ ',50000,5,50,3),(35,'	Carvedilol','Việt nam ',25000,3,50,4),(36,'Digoxin','Nhật bản ',100000,4,45,4),(37,'Omega 3','Nhật bản ',50000,4,50,6),(38,'Vitamin D','Việt nam ',30000,3,50,6),(39,'	Pennicillin','Trung Quốc',25000,1,50,5),(40,'	Digoxin','Canada',50000,5,50,4),(41,'	Fluticasone','Hàn quốc',60000,4,50,2),(42,'	Fexofenadine','Mỹ ',100000,2,50,7),(43,'	Cetirizine','Nhật bản ',50000,3,50,7),(44,'	Ticagrelor','Canada',25000,3,50,4),(45,'Dexamethasone','Canada',25000,3,50,3),(46,'Prednisolone','Nhật bản ',100000,5,50,3),(47,'Benzylpenicillin','Nhật bản ',30000,2,58,2),(48,'Ciprofloxacin','Nhật bản ',50000,5,50,5),(49,'Amphotericin B','Canada',50000,5,50,5),(50,'Gentamicin','Mỹ ',50000,4,50,7),(51,'Doxycycline','Việt nam ',50000,3,10,6);
/*!40000 ALTER TABLE `thuoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tien_kham`
--

DROP TABLE IF EXISTS `tien_kham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tien_kham` (
  `id_tienKham` int NOT NULL AUTO_INCREMENT,
  `tien_kham` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id_tienKham`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tien_kham`
--

LOCK TABLES `tien_kham` WRITE;
/*!40000 ALTER TABLE `tien_kham` DISABLE KEYS */;
INSERT INTO `tien_kham` VALUES (1,100000);
/*!40000 ALTER TABLE `tien_kham` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id_role` int NOT NULL AUTO_INCREMENT,
  `chuc_vu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_BACSI'),(3,'ROLE_YTA'),(4,'ROLE_BENHNHAN');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-16 12:42:54
