/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2017-05-15 15:04:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dailylog
-- ----------------------------
DROP TABLE IF EXISTS `dailylog`;
CREATE TABLE `dailylog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dailylog
-- ----------------------------
INSERT INTO `dailylog` VALUES ('1', '内容1');
INSERT INTO `dailylog` VALUES ('2', '内容2');
INSERT INTO `dailylog` VALUES ('3', '今天天气下雨');
