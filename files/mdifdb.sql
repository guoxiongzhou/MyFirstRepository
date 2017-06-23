/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : mdifdb

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-06-23 23:10:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'normal', '操作员');
INSERT INTO `sys_role` VALUES ('2', 'manager', '项目管理员');
INSERT INTO `sys_role` VALUES ('3', 'admin', '主任管理员');

-- ----------------------------
-- Table structure for t_hospital
-- ----------------------------
DROP TABLE IF EXISTS `t_hospital`;
CREATE TABLE `t_hospital` (
  `hospital_id` char(255) COLLATE utf8_bin NOT NULL,
  `hospital_name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_hospital
-- ----------------------------

-- ----------------------------
-- Table structure for t_project
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `project_id` char(36) COLLATE utf8_bin NOT NULL,
  `name` varchar(1000) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_project
-- ----------------------------

-- ----------------------------
-- Table structure for t_template
-- ----------------------------
DROP TABLE IF EXISTS `t_template`;
CREATE TABLE `t_template` (
  `template_id` char(36) COLLATE utf8_bin NOT NULL,
  `name` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `project_id` char(36) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`template_id`),
  KEY `index_project` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_template
-- ----------------------------

-- ----------------------------
-- Table structure for t_upload
-- ----------------------------
DROP TABLE IF EXISTS `t_upload`;
CREATE TABLE `t_upload` (
  `upload_id` char(36) COLLATE utf8_bin NOT NULL,
  `user_id` char(36) COLLATE utf8_bin NOT NULL,
  `file_id` char(36) COLLATE utf8_bin NOT NULL,
  `file_name` varchar(1000) COLLATE utf8_bin NOT NULL,
  `project_id` char(36) COLLATE utf8_bin NOT NULL,
  `month` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`upload_id`),
  KEY `index_userId` (`user_id`),
  KEY `index_projectId` (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_upload
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` char(36) COLLATE utf8_bin NOT NULL COMMENT 'uuid',
  `user_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(40) COLLATE utf8_bin NOT NULL,
  `age` int(4) DEFAULT NULL,
  `role` int(4) DEFAULT NULL COMMENT '角色:0,省中心总管理员；1，省中心项目管理员；2，各区域管理员。',
  `project_id` char(36) COLLATE utf8_bin DEFAULT NULL COMMENT '所属项目',
  `email` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `region` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '区域',
  `hospital` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '医院名称',
  `department` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '科室名称',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1b712fdb-581d-11e7-bbc8-206a8a04fe7c', 'czy_001', '123456', '35', '1', '', '', '', '', '', '');
INSERT INTO `t_user` VALUES ('20f45136-581d-11e7-bbc8-206a8a04fe7c', 'czy_002', '123456', '35', '1', '', '', '', '', '', '');
INSERT INTO `t_user` VALUES ('3ec70b37-581d-11e7-bbc8-206a8a04fe7c', 'admin_001', '123456', '35', '3', '', '', '', '', '', '');
INSERT INTO `t_user` VALUES ('48447c64-581d-11e7-bbc8-206a8a04fe7c', 'project_001', '123456', '35', '2', '', '', '', '', '', '');
