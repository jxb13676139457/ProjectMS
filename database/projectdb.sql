/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : projectdb

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2020-04-19 23:00:30
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of documents
-- ----------------------------
INSERT INTO `documents` VALUES ('22', '联合培养项目学生出国（境）学习协议书.docx', '2020-04-15 00:53:00', '赵卓君', 'E:\\IDEA\\IDEA project\\ProjectMS\\src\\main\\resources\\static\\upload\\联合培养项目学生出国（境）学习协议书.docx', 'InternationalMS');
INSERT INTO `documents` VALUES ('23', '项目开发说明.txt', '2020-04-15 02:32:58', '赵卓君', 'E:\\IDEA\\IDEA project\\ProjectMS\\src\\main\\resources\\static\\upload\\项目开发说明.txt', 'InternationalMS');

-- ----------------------------
-- Table structure for execute
-- ----------------------------
DROP TABLE IF EXISTS `execute`;
CREATE TABLE `execute` (
  `executeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '任务处理ID',
  `userId` varchar(20) NOT NULL COMMENT '用户ID',
  `taskId` varchar(20) NOT NULL COMMENT '任务ID',
  PRIMARY KEY (`executeId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of execute
-- ----------------------------
INSERT INTO `execute` VALUES ('1', '160202103567', 'ZZJ_001');
INSERT INTO `execute` VALUES ('2', '02067', 'ZZJ_002');
INSERT INTO `execute` VALUES ('3', '02067', 'ZZJ_003');

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members` (
  `memberId` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目人员ID',
  `userId` varchar(20) NOT NULL COMMENT '用户ID',
  `projectId` varchar(30) NOT NULL COMMENT '项目ID',
  PRIMARY KEY (`memberId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES ('1', '02067', 'InternationalMS');
INSERT INTO `members` VALUES ('2', '160202103567', 'InternationalMS');
INSERT INTO `members` VALUES ('11', '160202103630', 'InternationalMS');
INSERT INTO `members` VALUES ('14', '160202103570', 'InternationalMS');
INSERT INTO `members` VALUES ('17', '02067', 'ProjectMS');
INSERT INTO `members` VALUES ('18', '02067', 'LibraryMS');

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
INSERT INTO `projects` VALUES ('InternationalMS', '国际交流管理系统项目2', '近年来，随着学院与国际化接轨，通过与国外院校发展合作共同建设，成立了国际交流办公室，负责对国外院校合作还有国际班学生的日常管理工作。然而，大部分工作都是通过纸质记录和excel电子存档数据存储安全性不足，excel电子版本时常被覆盖，纸质存档还存在遗失的情况。所以，我们开发这个系统的意义在于很好地解决这种情况的出现，方便国际交流办公室老师的日常办公和提高数据存储的安全性。', '2019-04-01', '2019-05-10', '赵卓君', '13676139457', '50000.00', '0.00', '0.00', '无', '0.00');
INSERT INTO `projects` VALUES ('LibraryMS', '图书馆管理系统', '在高校中，图书馆都是不可或缺的，学校教书育人，书本就是最为重要的一环，而图书馆就管理着学校的许许多多课外书，是学生们自习的好去处，而学生的借还书问题也需要统一管理，于是有了这个课题', '2020-03-15', '2020-05-30', '赵卓君', '13676139457', '51422.00', '74512.00', '45025.00', '租用服务器：4500/年\r\n项目人工成本：16000/月', '0.00');
INSERT INTO `projects` VALUES ('ProjectMS', '基于Springboot的项目管理系统', '本系统的设计目标是立足于中小型科技公司的内部项目管理协作方面的实际需要，面向公司内项目的所有成员，建立一个使用便捷、可靠的项目管理系统，从而更方便地\r\n对项目进度进行把控以及对项目的研发过程进行管理。', '2020-01-05', '2020-04-14', '赵卓君', '13676139457', null, null, null, '', '0.00');

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
INSERT INTO `tasks` VALUES ('T15', 'DSD', 'DDDDDDDD', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('T18', '测试添加任务', 'fdfdfdfdfd', '2020-02-22', '2020-02-24', '正在执行', '需求任务', '高', 'Q1_JXB');
INSERT INTO `tasks` VALUES ('T19', '商店系统前台界面的优化', '在第一版本的任务需求中，时间急迫而项目又需要有一个直观的效果图来，于是采用敏捷开发方法，快速开发一个商店系统前台界面，以此作为原型图，现在项目开会经过领导决定，对原先的第一版本进行界面优化', '2020-02-22', '2020-02-26', '正在执行', '开发任务', '高', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('task5', 'sdsdsdsd', 'sfffffffffffffffffff', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_JXB');
INSERT INTO `tasks` VALUES ('task6', 'ddddddddddd', 'fffffffffffffffff', '2020-02-22', '2020-02-24', '待执行', '需求任务', '高', 'Q2_HTW');
INSERT INTO `tasks` VALUES ('ZZJ_001', '开发新闻中心板块', '可以让用户登录进系统后查看已发布的各类新闻信息，这些数据都统一存放在一个新闻中心板块', '2020-02-22', '2020-02-24', '正在执行', '开发任务', '高', 'InternationalMS');
INSERT INTO `tasks` VALUES ('ZZJ_002', '开发通知公告板块', '用户登录进国际交流管理系统后，点击菜单进入通知公告板块，可浏览相关公告通知', '2020-02-22', '2020-02-26', '待执行', '需求任务', '中', 'InternationalMS');
INSERT INTO `tasks` VALUES ('ZZJ_003', '开发后台管理员管理学生模块', '开发一个只属于后台管理员登录的国际交流管理系统管理端，后台管理员可以在后台维护学生信息，分为3个子菜单，维护国际班学生信息，维护交换生信息，维护留学生信息', '2020-02-24', '2020-02-28', '待执行', '需求任务', '中', 'InternationalMS');

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
INSERT INTO `users` VALUES ('02067', '赵卓君', 'f3f0ef18f5d660524a181c4f96cbdd59', '女', '广东省珠海市', '13676139457', '2');
INSERT INTO `users` VALUES ('160202103564', '郑泽楷', 'b43746afb575e5b0d16e2134046e968d', '男', '广东省汕头市', '13676139457', '5');
INSERT INTO `users` VALUES ('160202103567', '江鑫彪', 'd40b384472e37a461a20d76a99e4e42c', '男', '广东省汕头市', '13726213859', '3');
INSERT INTO `users` VALUES ('160202103570', '廖捷昕', '71f010649b36549e43a95369432bd37a', '男', '广东省汕头市', '13676139459', '3');
INSERT INTO `users` VALUES ('160202103571', '郑健', 'c13ee4a4f767798886e151e1ce7600ac', '男', '广东省汕头市', '13676139457', '53');
INSERT INTO `users` VALUES ('160202103572', '黄静云', '5b6f30b9477d872fcae59c503ca2fd4f', '女', '广东省深圳市', '13676139457', '61');
INSERT INTO `users` VALUES ('160202103630', '梁俊彬', '3d0314bc33068a3a800ad34d000ef166', '男', '广东省肇庆市', '13676139457', '3');
INSERT INTO `users` VALUES ('160202103712', '刘泽庆', '5b8715c3dea689749108b6e0b144f828', '男', '广东省云浮市', '13676139457', '3');
INSERT INTO `users` VALUES ('160502102082', '黄廷蔚', 'ece5c170820ff3b76451f4e344babba8', '男', '广东省珠海市', '13676139457', '3');
INSERT INTO `users` VALUES ('163701101411', '邓成鑫', '0197d669d2cb5470ba355d40aa89ef95', '男', '广东省深圳市', '13676139457', '3');
SET FOREIGN_KEY_CHECKS=1;
