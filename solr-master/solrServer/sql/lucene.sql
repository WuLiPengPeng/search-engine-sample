/*
Navicat MySQL Data Transfer

Source Server         : WLP阿里云
Source Server Version : 50642
Source Host           : 120.77.35.19:3306
Source Database       : lucene

Target Server Type    : MYSQL
Target Server Version : 50642
File Encoding         : 65001

Date: 2019-06-20 16:54:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `pid` varchar(255) NOT NULL COMMENT '产品id',
  `name` varchar(255) DEFAULT NULL COMMENT '产品名称',
  `catalog_name` varchar(255) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL COMMENT '价格',
  `description` varchar(255) DEFAULT NULL COMMENT '产品描述',
  `picture` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', '大帽子', '杂货', '11.00', '美丽，杂货', '1.jpg');
INSERT INTO `products` VALUES ('10', '银帽子', '金属', '100.00', '美丽，金属', '10.jpg');
INSERT INTO `products` VALUES ('2', '小帽子', '杂货', '8.00', '漂亮，杂货', '2.jpg');
INSERT INTO `products` VALUES ('3', '蓝帽子', '彩色', '80.00', '漂亮，彩色', '3.jpg');
INSERT INTO `products` VALUES ('4', '红帽子', '彩色', '75.00', '难看，彩色', '4.jpg');
INSERT INTO `products` VALUES ('5', '橙帽子', '彩色', '70.00', '难看，彩色', '5.jpg');
INSERT INTO `products` VALUES ('6', '塑料帽子', '常见', '35.00', '可爱，常见', '6.jpg');
INSERT INTO `products` VALUES ('7', '铁帽子', '金属', '50.00', '可爱，金属', '7.jpg');
INSERT INTO `products` VALUES ('8', '布帽子', '常见', '20.00', '俊俏，常见', '8.jpg');
INSERT INTO `products` VALUES ('9', '金帽子', '金属', '200.00', '俊俏，金属', '9.jpg');
