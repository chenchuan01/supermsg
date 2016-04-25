/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50154
Source Host           : localhost:3306
Source Database       : websupermsg

Target Server Type    : MYSQL
Target Server Version : 50154
File Encoding         : 65001

Date: 2016-04-26 06:42:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_config`;
CREATE TABLE `tb_config` (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delflag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `key` varchar(20) DEFAULT NULL COMMENT '配置索引',
  `value` varchar(1000) DEFAULT NULL COMMENT '配置值',
  `remarks` varchar(100) NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_config
-- ----------------------------
INSERT INTO `tb_config` VALUES ('1', '0', 'PAGESIZE', '999', '页面容量');
INSERT INTO `tb_config` VALUES ('2', '0', 'AUTH_CONF', '0:sys;1:admin;2:front;', '权限配置');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delflag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `name` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `roles` varchar(20) DEFAULT '3' COMMENT '角色',
  `friends_id` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '0', 'CFAIC', 'gust', '202CB962AC59075B964B07152D234B70', '2', '6;8;10;9;7;4;2;5;');
INSERT INTO `tb_user` VALUES ('2', '0', '风中的司机', 'qq', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('3', '0', '扯把子不来', 'ww', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('4', '0', 'goKeng', 'gkeng', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('5', '0', '走不动各跑', 'catalina', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('6', '0', '我是约德尔人', 'yuedeer', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('7', '0', 'kevien', 'kevien', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('8', '0', 'VicentFG', 'vicentgobugo', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('9', '0', '浮的不是云', 'mayun', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('10', '0', '东风游丝', 'fengzheng', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('11', '0', '焦大说的什么', 'oo', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('12', '0', '你的队友', 'friend', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('13', '0', '说点什么好', 'rt', '202CB962AC59075B964B07152D234B70', '2', null);
INSERT INTO `tb_user` VALUES ('14', '0', 'killingSpring', 'manykills', '202CB962AC59075B964B07152D234B70', '2', null);
