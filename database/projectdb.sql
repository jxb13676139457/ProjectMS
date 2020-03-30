/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : projectdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-03-30 12:52:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auditing
-- ----------------------------
DROP TABLE IF EXISTS `auditing`;
CREATE TABLE `auditing` (
  `auditingId` varchar(30) NOT NULL COMMENT '审核的项目ID',
  `projectName_tmp` varchar(50) DEFAULT NULL COMMENT '审核项目名称',
  `projectContent_tmp` varchar(255) DEFAULT NULL COMMENT '审核项目简介',
  `time_start_tmp` date DEFAULT NULL COMMENT '审核项目开始时间',
  `time_end_tmp` date DEFAULT NULL COMMENT '审核项目结束时间',
  `leader_tmp` varchar(20) DEFAULT NULL COMMENT '项目负责人',
  `phone_tmp` varchar(20) DEFAULT NULL COMMENT '联系手机号',
  `inputMoney_tmp` float(20,2) DEFAULT NULL COMMENT '项目投入资金',
  `outputMoney_tmp` float(20,2) DEFAULT NULL COMMENT '项目创收资金',
  `cost_tmp` float(20,2) DEFAULT NULL COMMENT '项目成本',
  `costDetail_tmp` varchar(300) DEFAULT NULL COMMENT '项目资金消费明细',
  `auditingStatus` int(11) NOT NULL COMMENT '审核项目状态',
  PRIMARY KEY (`auditingId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auditing
-- ----------------------------

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
  `projectId` varchar(30) NOT NULL COMMENT '文档资料所在的项目ID',
  PRIMARY KEY (`documentId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------
INSERT INTO `documents` VALUES ('7', '校园vpn使用手册.docx', '2020-02-11 00:16:07', '江鑫彪', 'E:\\IDEA\\IDEA project\\ProjectMS\\src\\main\\resources\\static\\upload\\校园vpn使用手册.docx', 'Q1_JXB');
INSERT INTO `documents` VALUES ('8', 'Java复习.docx', '2020-03-06 17:41:27', '江鑫彪', 'E:\\IDEA\\IDEA project\\ProjectMS\\src\\main\\resources\\static\\upload\\Java复习.docx', 'Q1_JXB');

-- ----------------------------
-- Table structure for execute
-- ----------------------------
DROP TABLE IF EXISTS `execute`;
CREATE TABLE `execute` (
  `executeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务处理ID',
  `userId` varchar(20) NOT NULL COMMENT '用户ID',
  `taskId` varchar(20) NOT NULL COMMENT '任务ID',
  PRIMARY KEY (`executeId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of execute
-- ----------------------------
INSERT INTO `execute` VALUES ('1', '111111111111', 'T01');
INSERT INTO `execute` VALUES ('2', '111111111111', 'T02');

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `memberId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目人员ID',
  `userId` varchar(20) NOT NULL COMMENT '用户ID',
  `projectId` varchar(30) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('1', '160202103567', 'Q1_JXB');
INSERT INTO `members` VALUES ('2', '111111111111', 'Q1_JXB');
INSERT INTO `members` VALUES ('3', '222222222222', 'Q1_JXB');
INSERT INTO `members` VALUES ('4', '333333333333', 'Q1_JXB');
INSERT INTO `members` VALUES ('5', '111111111111', 'Q3_HTW');
INSERT INTO `members` VALUES ('6', '111111111111', 'Q2_HTW');
INSERT INTO `members` VALUES ('7', '111111111111', 'Q4_HTW');
INSERT INTO `members` VALUES ('8', '111111111111', 'Q2_JXB');

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `projectId` varchar(30) NOT NULL COMMENT '项目ID',
  `projectName` varchar(50) DEFAULT NULL COMMENT '项目名称',
  `projectContent` varchar(255) DEFAULT NULL COMMENT '项目简要介绍',
  `time_start` date DEFAULT NULL COMMENT '项目开始时间',
  `time_end` date DEFAULT NULL COMMENT '项目截止时间',
  `leader` varchar(20) DEFAULT NULL COMMENT '项目负责人',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系手机号',
  `inputMoney` float(20,2) DEFAULT NULL COMMENT '项目投入资金',
  `outputMoney` float(20,2) DEFAULT NULL COMMENT '项目创收资金',
  `cost` float(20,2) DEFAULT NULL COMMENT '项目成本',
  `costDetail` varchar(255) DEFAULT NULL COMMENT '项目资金消费明细',
  `process` float(4,2) DEFAULT '0.00' COMMENT '项目进度',
  PRIMARY KEY (`projectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of projects
-- ----------------------------
INSERT INTO `projects` VALUES ('Q1_JXB', '项目一', '项目二简介', '2020-01-08', '2020-02-12', '江鑫彪', '13726213859', '50000.00', '84455.00', '45000.00', '测试操作。\r\n测试项目一', '50.48');
INSERT INTO `projects` VALUES ('Q2_HTW', '项目二', '项目二简介', '2020-01-06', '2020-02-19', '黄廷蔚', '11111111111', '80000.00', '105584.80', '61000.00', '测试项目二', '80.48');
INSERT INTO `projects` VALUES ('Q2_JXB', '项目五', '项目二简介', '2020-02-11', '2020-02-29', '江鑫彪', '13726213859', '48488.00', '58888.00', '44444.00', '测试项目二', '66.50');
INSERT INTO `projects` VALUES ('Q3_HTW', '项目三', '项目三简介', '2020-02-17', '2020-03-19', '黄廷蔚', '11111111111', '84480.00', '111111.60', '70444.00', '测试项目三', '15.50');
INSERT INTO `projects` VALUES ('Q4_HTW', '项目四', '项目四简介', '2020-02-04', '2020-02-21', '黄廷蔚', '11111111111', '555555.00', '88888.00', '45455.00', '测试项目四', '55.60');

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
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('1', 'admin', '管理员', 'all:all');
INSERT INTO `roles` VALUES ('2', '管理', '项目经理', 'demand:edit');
INSERT INTO `roles` VALUES ('3', '开发', 'Java开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('5', '测试', '软件测试工程师', 'test:edit');
INSERT INTO `roles` VALUES ('53', '开发', 'golang开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('59', '开发', 'Android开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('60', '开发', 'iOS开发工程师', 'dev:edit');
INSERT INTO `roles` VALUES ('61', '测试', '测试开发工程师', 'test:edit');
INSERT INTO `roles` VALUES ('63', '开发', 'ETL开发工程师', 'dev:edit');

-- ----------------------------
-- Table structure for tasks
-- ----------------------------
DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `taskId` varchar(20) NOT NULL COMMENT '任务ID',
  `taskName` varchar(50) DEFAULT NULL COMMENT '任务名称',
  `taskContent` varchar(255) DEFAULT NULL COMMENT '任务内容',
  `createTime` date DEFAULT NULL COMMENT '创建时间',
  `finishTime` date DEFAULT NULL COMMENT '截止完成时间',
  `taskStatus` varchar(20) NOT NULL COMMENT '任务状态',
  `taskType` varchar(20) NOT NULL COMMENT '任务类型',
  `taskLevel` varchar(10) NOT NULL COMMENT '任务优先级',
  `projectId` varchar(30) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tasks
-- ----------------------------
INSERT INTO `tasks` VALUES ('T01', '任务一啊啊啊啊啊', '开发首页界面', '2020-02-04', '2020-02-04', '正在执行', '开发任务', '低', 'Q1_JXB');
INSERT INTO `tasks` VALUES ('T02', '任务二', '开发首页添加按钮功能', '2020-02-18', '2020-02-18', '正在执行', '需求任务', '中', 'Q1_JXB');
INSERT INTO `tasks` VALUES ('T03', '任务三', '但是地点所多', '2020-02-12', '2020-02-12', '已完成', '开发任务', '高', 'Q1_JXB');
INSERT INTO `tasks` VALUES ('T04', '任务四', '是的发哦哦共', '2020-02-13', '2020-02-13', '正在执行', '开发任务', '高', 'Q3_HTW');
INSERT INTO `tasks` VALUES ('T05', '任务五', '东方佛公共', '2020-02-13', '2020-02-21', '待执行', '开发任务', '低', 'Q3_HTW');
INSERT INTO `tasks` VALUES ('T06', '任务六', '飞飞飞付付付付付', '2020-02-20', '2020-02-20', '待执行', '开发任务', '低', 'Q3_HTW');
INSERT INTO `tasks` VALUES ('T07', '任务七', '反福福福福福福福福福', '2020-02-12', '2020-02-14', '已完成', '开发任务', '高', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('T08', '任务八丰富的非方', '服服服服服服', '2020-02-18', '2020-02-18', '待执行', '开发任务', '中', 'Q3_HTW');
INSERT INTO `tasks` VALUES ('T09', '任务九', '发吧比比避风的服务', '2020-02-20', '2020-02-20', '待执行', '开发任务', '中', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('T10', '放的地方大佛', '的计算机的', '2020-02-18', '2020-02-18', '正在执行', '开发任务', '高', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('T11', '测试添加任务', 'FFFFFFFFFFFFFFFFFFFFFF', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_JXB');
INSERT INTO `tasks` VALUES ('T12', '测试添加任务', '15511111111111', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_JXB');
INSERT INTO `tasks` VALUES ('T13', '测试添加任务', '25555555', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_JXB');
INSERT INTO `tasks` VALUES ('T18', '测试添加任务', 'fdfdfdfdfd', '2020-02-22', '2020-02-24', '正在执行', '需求任务', '高', 'Q1_JXB');
INSERT INTO `tasks` VALUES ('task5', 'sdsdsdsd', 'sfffffffffffffffffff', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_JXB');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` varchar(20) NOT NULL COMMENT '用户账号ID',
  `userName` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` char(2) NOT NULL COMMENT '性别',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系手机号',
  `roleId` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('013376', 'Admin', '48b87152a010f47baf8dce027eecf03c', '男', '广东省汕头市', '13676139457', '1');
INSERT INTO `users` VALUES ('111111111111', '黄廷蔚', 'cf8c134111b3c88491b7cfbcc335f046', '男', '广东省珠海市', '13611111111', '2');
INSERT INTO `users` VALUES ('160202103567', '江鑫彪', 'd40b384472e37a461a20d76a99e4e42c', '男', '广东省汕头市', '13726213859', '2');
INSERT INTO `users` VALUES ('55555555555555555', '赵卓君', '32be2410323f98dee6aec8055578f5a3', '女', '广东省珠海市', '13644444444', '2');
SET FOREIGN_KEY_CHECKS=1;
