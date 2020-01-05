/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : projectdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-01-05 23:42:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for documents
-- ----------------------------
DROP TABLE IF EXISTS `documents`;
CREATE TABLE `documents` (
  `documentId` int(11) NOT NULL AUTO_INCREMENT COMMENT '文档ID',
  `documentName` varchar(100) DEFAULT NULL COMMENT '文档名',
  `uploadTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '文件上传时间',
  `author` varchar(50) DEFAULT NULL COMMENT '文档作者',
  `savePath` varchar(255) DEFAULT NULL COMMENT '文档存储路径',
  PRIMARY KEY (`documentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------

-- ----------------------------
-- Table structure for execute
-- ----------------------------
DROP TABLE IF EXISTS `execute`;
CREATE TABLE `execute` (
  `executeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务处理ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `taskId` int(11) NOT NULL COMMENT '任务ID',
  PRIMARY KEY (`executeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of execute
-- ----------------------------

-- ----------------------------
-- Table structure for funds
-- ----------------------------
DROP TABLE IF EXISTS `funds`;
CREATE TABLE `funds` (
  `fundId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目资金ID',
  `inputMoney` float DEFAULT NULL COMMENT '项目投入资金',
  `outputMoney` float DEFAULT NULL COMMENT '项目创收资金',
  `cost` float(10,2) DEFAULT NULL COMMENT '项目实际成本',
  `costDetail` varchar(100) DEFAULT NULL COMMENT '项目资金消费明细',
  PRIMARY KEY (`fundId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of funds
-- ----------------------------

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `memberId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目人员ID',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `projectId` int(11) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `projectId` int(11) NOT NULL COMMENT '项目ID',
  `projectName` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `projectContent` varchar(255) DEFAULT NULL COMMENT '项目简要介绍',
  `time_start` date DEFAULT NULL COMMENT '项目开始时间',
  `time_end` date DEFAULT NULL COMMENT '项目截止时间',
  `documentId` int(11) NOT NULL COMMENT '项目内文档资料ID',
  `fundId` int(11) NOT NULL COMMENT '项目内资金ID',
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of projects
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `roleType` varchar(50) DEFAULT '' COMMENT '角色类型',
  `roleName` varchar(50) DEFAULT '' COMMENT '角色名',
  `rolePower` varchar(50) DEFAULT '' COMMENT '角色权限',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'admin', '管理员', 'all:all');
INSERT INTO `roles` VALUES ('2', '管理', '项目经理', 'demand:edit');
INSERT INTO `roles` VALUES ('3', '开发', 'Java开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('4', '开发', 'golang开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('5', '测试', '软件测试工程师', 'test:edit');
INSERT INTO `roles` VALUES ('6', '开发', 'ETL开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('7', '开发', '前端开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('39', '开发', 'PHP开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('40', '测试', '测试开发工程师', 'test:edit');

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `taskId` int(11) NOT NULL COMMENT '任务ID',
  `taskName` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `taskContent` varchar(255) DEFAULT NULL COMMENT '任务内容',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `finishTime` date DEFAULT NULL COMMENT '截止完成时间',
  `taskStatus` varchar(20) NOT NULL COMMENT '任务状态',
  `taskType` varchar(20) NOT NULL COMMENT '任务类型',
  `taskLevel` varchar(10) NOT NULL COMMENT '任务优先级',
  `schedule` decimal(4,2) DEFAULT NULL COMMENT '项目总进度',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tasks
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` varchar(20) NOT NULL COMMENT '用户账号ID',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(30) DEFAULT NULL COMMENT '密码',
  `sex` char(2) NOT NULL COMMENT '性别',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系手机号',
  `roleId` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('111111111111', '小郑', '123456', '男', '广东省汕头市', '11111111111', '5');
INSERT INTO `users` VALUES ('160202103567', '小江', '123456', '男', '广东省汕头市', '13726213859', '1');
INSERT INTO `users` VALUES ('222222222222', '小李', '123456', '女', '广东省深圳市', '22222222222', '4');
INSERT INTO `users` VALUES ('333333333333', '小黄', '123456', '男', '广东省珠海市', '33333333333', '7');
SET FOREIGN_KEY_CHECKS=1;
