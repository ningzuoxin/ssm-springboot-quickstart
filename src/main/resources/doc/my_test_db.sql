/*
Navicat MySQL Data Transfer

Source Server         : localdb_root
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : my_test_db

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-03-04 14:15:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点id',
  `menu_name` varchar(128) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(128) DEFAULT '' COMMENT '菜单路径',
  `status` tinyint(3) DEFAULT '1' COMMENT '菜单状态 1-有效；0-无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12212 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '0', '菜单1', '', '1');
INSERT INTO `menu` VALUES ('11', '1', '菜单11', '', '1');
INSERT INTO `menu` VALUES ('12', '1', '菜单12', '', '1');
INSERT INTO `menu` VALUES ('13', '1', '菜单13', '', '1');
INSERT INTO `menu` VALUES ('111', '11', '菜单111', '', '1');
INSERT INTO `menu` VALUES ('121', '12', '菜单121', '', '1');
INSERT INTO `menu` VALUES ('122', '12', '菜单122', '', '1');
INSERT INTO `menu` VALUES ('1221', '122', '菜单1221', '', '1');
INSERT INTO `menu` VALUES ('1222', '122', '菜单1222', '', '1');
INSERT INTO `menu` VALUES ('12211', '1222', '菜单12211', '', '1');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('2', 'user1', 'pass1');
INSERT INTO `t_user` VALUES ('3', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('4', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('6', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('7', 'u2', 'p2');
INSERT INTO `t_user` VALUES ('8', 'u1', 'p3');
INSERT INTO `t_user` VALUES ('9', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('10', 'u1', 'p2');
INSERT INTO `t_user` VALUES ('11', 'u3', 'p3');
INSERT INTO `t_user` VALUES ('12', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('13', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('14', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('15', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('16', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('17', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('18', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('19', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('20', 'u1', 'p1');
INSERT INTO `t_user` VALUES ('21', 'u1', 'p1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL,
  `age` int(2) DEFAULT NULL,
  `deleteFlag` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三', '123456', '20', '0');
INSERT INTO `user` VALUES ('2', '李四', '654321', '19', '0');
INSERT INTO `user` VALUES ('3', '张三2', '123456', '20', '0');
