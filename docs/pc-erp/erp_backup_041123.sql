-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: pc-erp
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL COMMENT '活动发起人ID',
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '活动名称',
  `gmt_start` datetime DEFAULT NULL COMMENT '活动开始时间',
  `gmt_end` datetime DEFAULT NULL COMMENT '活动结束时间',
  `money_budget` decimal(12,2) DEFAULT NULL COMMENT '人均预算',
  `money_final_coast` decimal(12,2) DEFAULT NULL COMMENT '最终消费（总）',
  `status_public` tinyint(4) DEFAULT '1' COMMENT '公开状态：\n1：public\n0：private',
  `content` text COMMENT '活动内容描述',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `pic_small` varchar(200) DEFAULT '' COMMENT '小照片\n36*36',
  `pic_normal` varchar(200) DEFAULT '' COMMENT '普通大小\n96*96',
  `pic_larger` varchar(200) DEFAULT '' COMMENT '较大\n128*128',
  `pic_banner` varchar(200) DEFAULT '' COMMENT 'Banner',
  `location` varchar(100) DEFAULT NULL COMMENT '活动地点',
  `location_mapx` varchar(100) DEFAULT NULL COMMENT '活动地点，地图信息',
  `location_mapy` varchar(100) DEFAULT NULL COMMENT '活动地点，地图信息',
  `clear_status` varchar(3) DEFAULT NULL COMMENT '完成结算标记\nY：已结算，活动结束\nN：未结算，未完成（默认）',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_gmt_start` (`gmt_start`),
  KEY `idx_gmt_end` (`gmt_end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动信息表，活动由发起人发起，所有费用核算结束后正式结束';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events_item`
--

DROP TABLE IF EXISTS `events_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events_item` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `events_id` int(20) NOT NULL,
  `name` varchar(200) NOT NULL DEFAULT '' COMMENT '活动项目名称',
  `money_final_coast` decimal(12,2) DEFAULT NULL COMMENT '项目消费情况（总）',
  `gmt_happen` datetime DEFAULT NULL COMMENT '活动项目发生时间',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_events_id` (`events_id`),
  KEY `idx_gmt_happen` (`gmt_happen`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='详细活动项目（一个活动可能由多个项目组成）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events_item`
--

LOCK TABLES `events_item` WRITE;
/*!40000 ALTER TABLE `events_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `events_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events_item_coast`
--

DROP TABLE IF EXISTS `events_item_coast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events_item_coast` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL DEFAULT '0',
  `events_id` int(20) NOT NULL DEFAULT '0',
  `events_item_id` int(20) NOT NULL DEFAULT '0',
  `coast` decimal(12,2) DEFAULT NULL COMMENT '每位成员在项目中的花费，默认AA',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录不同参与者在活动项目上的花费（默认可按照平均计算，但由发起人可按实际情况调整）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events_item_coast`
--

LOCK TABLES `events_item_coast` WRITE;
/*!40000 ALTER TABLE `events_item_coast` DISABLE KEYS */;
/*!40000 ALTER TABLE `events_item_coast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events_join`
--

DROP TABLE IF EXISTS `events_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events_join` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL DEFAULT '0',
  `events_id` int(20) NOT NULL DEFAULT '0',
  `money_prepaid` decimal(12,2) DEFAULT NULL COMMENT '参与活动时预支付费用\n活动费用可能会超出',
  `yes_or_no` tinyint(4) DEFAULT '0' COMMENT '参与情况（YES OR NO）\n-1：No\n0：MAYBE\n1：YES',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `clear_status` varchar(3) DEFAULT NULL COMMENT '完成结算标记\nY：已结算，活动结束\nN：未结算，未完成（默认）',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_events_id` (`events_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动组织参与情况';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events_join`
--

LOCK TABLES `events_join` WRITE;
/*!40000 ALTER TABLE `events_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `events_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL DEFAULT '' COMMENT '产品编码（商家定义）',
  `name` varchar(250) NOT NULL DEFAULT '',
  `category_code` varchar(45) NOT NULL DEFAULT '',
  `remark` varchar(250) DEFAULT '' COMMENT '产品描述',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `cid` int(20) NOT NULL,
  `uid_created` int(20) NOT NULL COMMENT '创建信息的用户',
  `uid_modified` int(20) NOT NULL COMMENT '最后编辑的用户',
  `status_life` varchar(45) NOT NULL DEFAULT '' COMMENT '产品生命周期状态\nDRAFT：草稿\nSALING：在售（默认）\nSHELVES：下架',
  PRIMARY KEY (`id`),
  KEY `idx_code` (`code`),
  KEY `idx_category_code` (`category_code`),
  KEY `idx_cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='产品基本信息，通用属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'5202','红包款5202','HB','for test','2014-10-27 20:13:40','2014-10-27 20:13:40',1,1,1,'SAILING'),(3,'5203','红包款5203','HB','for test','2014-10-27 20:13:40','2014-10-27 20:13:40',1,1,1,'SAILING'),(4,'5204','红包款5204','HB','for test','2014-10-27 20:13:40','2014-10-27 20:13:40',1,1,1,'SAILING'),(5,'5205','红包款5205','HB','for test','2014-10-27 20:13:40','2014-10-27 20:13:40',1,1,1,'SAILING'),(6,'5206','红包款5206','HB','for test','2014-10-27 20:13:41','2014-10-27 20:13:41',1,1,1,'SAILING'),(7,'5207','红包款5207','HB','for test','2014-10-27 20:13:41','2014-10-27 20:13:41',1,1,1,'SAILING'),(8,'5208','红包款5208','HB','for test','2014-10-27 20:13:41','2014-10-27 20:13:41',1,1,1,'SAILING'),(9,'5209','红包款5209','HB','for test','2014-10-27 20:13:41','2014-10-27 20:13:41',1,1,1,'SAILING'),(10,'5210','红包款5210','HB','for test','2014-10-27 20:13:41','2014-10-27 20:13:41',1,1,1,'SAILING'),(11,'5211','红包款5211','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(12,'5212','红包款5212','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(13,'5213','红包款5213','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(14,'5214','红包款5214','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(15,'5215','红包款5215','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(16,'5216','红包款5216','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(17,'5217','红包款5217','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(18,'5218','红包款5218','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(19,'5219','红包款5219','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(20,'5220','红包款5220','HB','for test','2014-10-27 20:13:42','2014-10-27 20:13:42',1,1,1,'SAILING'),(21,'5221','红包款5221','HB','for test','2014-10-27 20:13:44','2014-10-27 20:13:44',1,1,1,'SAILING'),(22,'产品编码','产品名称','HB','this is from DB update','2014-11-06 21:25:31','2014-11-08 23:22:53',1,1,1,'SALING');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_define`
--

DROP TABLE IF EXISTS `product_define`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_define` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) NOT NULL DEFAULT '0',
  `details` longtext,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='产品动态属性';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_define`
--

LOCK TABLES `product_define` WRITE;
/*!40000 ALTER TABLE `product_define` DISABLE KEYS */;
INSERT INTO `product_define` VALUES (1,22,'{\"c0\":\"文本标签U\",\"c1\":{\"dk\":\"0\",\"dv\":\"普通纸\"},\"c2\":[{\"dk\":\"1\",\"dv\":\"普通纸22\"}],\"c3\":\"文本域\",\"c4\":{\"dk\":\"1\",\"dv\":\"普通纸22\"},\"c5\":{\"dk\":\"0\",\"dv\":\"普通纸\"}}','2014-11-06 21:25:33','2014-11-08 23:22:53');
/*!40000 ALTER TABLE `product_define` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_price`
--

DROP TABLE IF EXISTS `product_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_price` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) NOT NULL DEFAULT '0',
  `price_code` varchar(45) NOT NULL DEFAULT '' COMMENT '价格类型，用于提供给不同层面的用户',
  `price_unit_code` varchar(45) NOT NULL DEFAULT '' COMMENT '单位',
  `price_value` decimal(12,2) DEFAULT NULL,
  `gmt_expired` datetime DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `remark` varchar(250) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品价格描述';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_price`
--

LOCK TABLES `product_price` WRITE;
/*!40000 ALTER TABLE `product_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_company`
--

DROP TABLE IF EXISTS `sys_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_company` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL DEFAULT '',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='登录用户所在组织';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_company`
--

LOCK TABLES `sys_company` WRITE;
/*!40000 ALTER TABLE `sys_company` DISABLE KEYS */;
INSERT INTO `sys_company` VALUES (1,'YYSoft','2014-10-18 07:05:10','2014-10-18 07:05:10'),(2,'红包喜帖制造厂','2014-10-18 07:05:58','2014-10-18 07:05:58');
/*!40000 ALTER TABLE `sys_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_config`
--

DROP TABLE IF EXISTS `sys_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_config` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type_config` varchar(45) NOT NULL COMMENT '参数类型',
  `key` varchar(45) NOT NULL,
  `value` varchar(45) DEFAULT NULL,
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='参数配置信息（v1.0.0暂不使用）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_config`
--

LOCK TABLES `sys_config` WRITE;
/*!40000 ALTER TABLE `sys_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` varchar(45) DEFAULT NULL COMMENT '用户ID：\n0：表示系统\nn：表示用户',
  `operator` varchar(45) DEFAULT NULL COMMENT '操作者：\nSystem：系统\n用户姓名',
  `gmt_log` varchar(45) DEFAULT NULL COMMENT '日志记录时间（JAVA中的时间）',
  `log_level` varchar(45) DEFAULT NULL COMMENT '日志等级：',
  `log_code` varchar(45) DEFAULT NULL COMMENT '日志代号',
  `log` varchar(45) DEFAULT NULL,
  `gmt_created` varchar(45) DEFAULT NULL,
  `gmt_modified` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志，记录各种行为（V1.0.0暂不使用）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL DEFAULT '0' COMMENT '用户ID，用户唯一标识，等于注册时accoun对应的ID',
  `classify` varchar(4) NOT NULL DEFAULT '' COMMENT '账户类型：\nA：普通账户\nM：手机号码\nE：Email\n?：待增加类型',
  `account` varchar(36) NOT NULL DEFAULT '' COMMENT '账户内容，可以是普通账户，邮箱账户，电话账户等\n账户组成：账户类型#账户\n例：\nM#13666624372\nE#mays@caiban.net\nA#mays',
  `password` varchar(32) NOT NULL DEFAULT '',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  `salt` varchar(32) DEFAULT '' COMMENT '盐',
  `cid` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_UNIQUE` (`account`),
  KEY `idx_uid` (`uid`),
  KEY `idx_password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,1,'A','A#八月下沙','e9148c5cda8d869dc2942ecc93ca9fec','2014-10-16 20:51:41','2014-10-16 20:51:42','rg40sk9gc2ruma4vniiti0rj4tw9lhhy',1),(2,2,'A','A#天堂下着沙','600238aa6175bb7ed5a30e52c8e1face','2014-10-18 19:46:53','2014-10-18 19:46:53','hcgj28uat171ix8jooii6p3oa9sy6jkv',1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-23 15:55:29
