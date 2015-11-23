/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.21-log : Database - loan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`loan` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `loan`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '后台用户id',
  `pin` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '后台用户账号',
  `type` tinyint(4) DEFAULT NULL COMMENT '后台用户类型',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '后台用户名称',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `state` tinyint(4) DEFAULT NULL COMMENT '用户状态',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `administrator` */

insert  into `administrator`(`id`,`pin`,`type`,`name`,`password`,`state`,`remark`,`created`,`modified`) values (1,'admin',1,'二强','15963f7374310e680dea3ae818851332',1,NULL,'2015-09-17 14:54:55','2015-09-17 14:58:36');

/*Table structure for table `express` */

DROP TABLE IF EXISTS `express`;

CREATE TABLE `express` (
  `id` bigint(20) NOT NULL COMMENT '物流公司id',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物流公司名称',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `express` */

/*Table structure for table `loan_apply` */

DROP TABLE IF EXISTS `loan_apply`;

CREATE TABLE `loan_apply` (
  `id` bigint(20) NOT NULL COMMENT '贷款申请id',
  `oveseas_company_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '境外公司账号',
  `credit_company_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '信保公司账号',
  `amount` bigint(20) DEFAULT NULL COMMENT '申请贷款金额',
  `state` tinyint(4) DEFAULT NULL COMMENT '审核结果',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `loan_apply` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` bigint(20) NOT NULL COMMENT '订单id',
  `overs_company_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '境外公司账号',
  `facilitator_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '服务商账号',
  `receiver` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人姓名',
  `address` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '收货地址',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人电话',
  `remark` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '收货人留言',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `express_id` bigint(20) DEFAULT NULL COMMENT '物流公司id',
  `express_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流公司名称',
  `express_order_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `state` tinyint(4) DEFAULT NULL COMMENT '下单状态',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `order` */

/*Table structure for table `overseas_company` */

DROP TABLE IF EXISTS `overseas_company`;

CREATE TABLE `overseas_company` (
  `id` bigint(20) NOT NULL COMMENT '境外公司id',
  `pin` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '境外公司账号',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '登录密码',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '名称',
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `district` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '区域',
  `repertory` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '仓库',
  `repertory_address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '仓库地址',
  `linkman` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `amazon_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '亚马逊账号',
  `ebay_account` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'ebay账号',
  `remark` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `register_time` datetime DEFAULT NULL COMMENT '注册时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `overseas_company` */

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` bigint(20) NOT NULL COMMENT '供应商id',
  `pin` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '登录名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '供应商名称',
  `address` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '供应商地址',
  `district` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '区域',
  `linkman` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '联系人',
  `mobile` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `state` tinyint(4) DEFAULT NULL COMMENT '状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `supplier` */

/*Table structure for table `ware` */

DROP TABLE IF EXISTS `ware`;

CREATE TABLE `ware` (
  `id` bigint(20) NOT NULL COMMENT '商品id',
  `img` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `supplier_id` bigint(20) DEFAULT NULL COMMENT '境外公司账号',
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名称',
  `sku` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'sku',
  `unit` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '条码',
  `min_order_num` bigint(20) DEFAULT NULL COMMENT '海关编码',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `presell_price` int(11) DEFAULT NULL COMMENT '预售价格',
  `state` tinyint(4) DEFAULT NULL COMMENT '商品状态',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ware` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
