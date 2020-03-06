/*
Navicat MySQL Data Transfer

Source Server         : localdb_root
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : my_test_db

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-03-06 15:14:20
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张三1', '123456', '1', '0');
INSERT INTO `user` VALUES ('2', '李四2', '123456', '2', '0');
INSERT INTO `user` VALUES ('3', '张三3', '123456', '3', '0');
INSERT INTO `user` VALUES ('4', '张三4', '123456', '4', '0');
INSERT INTO `user` VALUES ('5', '李四5', '123456', '5', '0');
INSERT INTO `user` VALUES ('6', '张三6', '123456', '6', '0');
INSERT INTO `user` VALUES ('7', '张三7', '123456', '7', '0');
INSERT INTO `user` VALUES ('8', '李四8', '123456', '8', '0');
INSERT INTO `user` VALUES ('9', '张三9', '123456', '9', '0');
INSERT INTO `user` VALUES ('10', '张三10', '123456', '10', '0');
INSERT INTO `user` VALUES ('11', '李四11', '123456', '11', '0');
INSERT INTO `user` VALUES ('12', '张三12', '123456', '12', '0');
INSERT INTO `user` VALUES ('13', '张三13', '123456', '13', '0');
INSERT INTO `user` VALUES ('14', '李四14', '123456', '14', '0');
INSERT INTO `user` VALUES ('15', '张三15', '123456', '15', '0');
INSERT INTO `user` VALUES ('16', '张三16', '123456', '16', '0');
INSERT INTO `user` VALUES ('17', '李四17', '123456', '17', '0');
INSERT INTO `user` VALUES ('18', '张三18', '123456', '18', '0');
INSERT INTO `user` VALUES ('19', '张三19', '123456', '19', '0');
INSERT INTO `user` VALUES ('20', '李四20', '123456', '20', '0');
INSERT INTO `user` VALUES ('21', '张三21', '123456', '21', '0');
INSERT INTO `user` VALUES ('22', '张三22', '123456', '22', '0');
INSERT INTO `user` VALUES ('23', '李四23', '123456', '23', '0');
INSERT INTO `user` VALUES ('24', '张三24', '123456', '24', '0');
