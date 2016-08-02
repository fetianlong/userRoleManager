/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.254
Source Server Version : 50624
Source Host           : 192.168.1.254:3306
Source Database       : yuediweb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-07-09 20:20:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `State` int(11) NOT NULL COMMENT '1-正常、2-冻结',
  `Pwd` varchar(500) DEFAULT NULL,
  `IsDeleteFlag` tinyint(1) NOT NULL DEFAULT '0',
  `CreaterId` bigint(20) DEFAULT NULL,
  `CreaterName` varchar(50) DEFAULT NULL,
  `CreateDateTime` datetime DEFAULT NULL,
  `RoleCategory` varchar(50) DEFAULT NULL,
  `Franchisees_ID` bigint(20) DEFAULT NULL,
  `UserType` int(11) NOT NULL DEFAULT '0' COMMENT '1-系统用户，0-普通用户',
  `salt` varchar(50) DEFAULT NULL,
  `maces` varchar(100) DEFAULT NULL COMMENT '登录用户MAC地址，若是机器有多个网卡使用英文分号（;）将多个网卡地址分开',
  `RealName` varchar(50) DEFAULT NULL,
  `Birthdate` varchar(50) DEFAULT NULL,
  `IDNumber` varchar(50) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '1:男，0:女',
  `telephone` varchar(50) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=753 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', '', '1', '40614ff932ebee047092febbe7650559fcfd5ebe', '0', '1', 'admin', '2013-10-27 00:00:00', 'ROLE_ADMIN', '3', '1', 'b5a30448255b3e6b', 'F0-DE-F1-07-9F-80', null, null, null, null, null, null);
