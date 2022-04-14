/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100419
 Source Host           : localhost:3306
 Source Schema         : devdb

 Target Server Type    : MySQL
 Target Server Version : 100419
 File Encoding         : 65001

 Date: 14/04/2022 18:17:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sign` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (91, '2022-04-04 00:22:24', 1, NULL, NULL, '注册区县', 'register_area', NULL);
INSERT INTO `sys_dict` VALUES (92, '2022-04-06 11:13:18', 1, '2022-04-06 11:13:29', 1, '监管仓状态', 'waste_disposal_state', '');
INSERT INTO `sys_dict` VALUES (93, '2022-04-13 23:10:19', 1, '2022-04-14 11:35:45', 1, '货物类别', 'goodType', '');
INSERT INTO `sys_dict` VALUES (94, '2022-04-13 23:11:10', 1, '2022-04-14 11:35:33', 1, '货物来源', 'goodSource', '');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL COMMENT 'dict_id',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `value` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '值',
  `sort` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (87, '2022-04-04 00:28:54', 1, '2022-04-04 07:27:19', 1, 91, '鼓楼区', '鼓楼区', '');
INSERT INTO `sys_dict_data` VALUES (88, '2022-04-04 00:29:41', 1, '2022-04-04 07:27:19', 1, 91, '云龙区', '云龙区', '');
INSERT INTO `sys_dict_data` VALUES (89, '2022-04-04 07:27:19', 1, NULL, NULL, 91, '铜山区', '铜山区', NULL);
INSERT INTO `sys_dict_data` VALUES (90, '2022-04-06 11:15:22', 1, NULL, NULL, 92, '正常', '正常', '1');
INSERT INTO `sys_dict_data` VALUES (91, '2022-04-06 11:15:22', 1, NULL, NULL, 92, '闭仓', '闭仓', '2');
INSERT INTO `sys_dict_data` VALUES (92, '2022-04-06 11:15:22', 1, NULL, NULL, 92, '废弃', '废弃', '3');
INSERT INTO `sys_dict_data` VALUES (93, '2022-04-13 23:10:48', 1, NULL, NULL, 93, '水产品', '水产品', NULL);
INSERT INTO `sys_dict_data` VALUES (94, '2022-04-13 23:10:48', 1, NULL, NULL, 93, '禽类', '禽类', NULL);
INSERT INTO `sys_dict_data` VALUES (95, '2022-04-13 23:10:48', 1, NULL, NULL, 93, '畜类', '畜类', NULL);
INSERT INTO `sys_dict_data` VALUES (96, '2022-04-13 23:10:48', 1, NULL, NULL, 93, '其他', '其他', NULL);
INSERT INTO `sys_dict_data` VALUES (97, '2022-04-13 23:11:39', 1, NULL, NULL, 94, '口岸流入', '口岸流入', NULL);
INSERT INTO `sys_dict_data` VALUES (98, '2022-04-13 23:11:40', 1, NULL, NULL, 94, '省际流入', '省际流入', NULL);
INSERT INTO `sys_dict_data` VALUES (99, '2022-04-13 23:11:40', 1, NULL, NULL, 94, '省内流入', '省内流入', NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (93, '2022-04-04 05:15:29', NULL, NULL, NULL, '3127295d-45a4-4f30-842b-2ad599020900', '22.png', 'D:\\file\\3127295d-45a4-4f30-842b-2ad599020900.png');
INSERT INTO `sys_file` VALUES (94, '2022-04-04 05:15:39', NULL, NULL, NULL, '3495726d-cce5-4af7-820a-063b6868565d', '22.png', 'D:\\file\\3495726d-cce5-4af7-820a-063b6868565d.png');
INSERT INTO `sys_file` VALUES (95, '2022-04-04 05:19:44', NULL, NULL, NULL, '7ede42e4-f03f-4450-aa06-bcd0d8720712.png', '22.png', 'D:\\file\\7ede42e4-f03f-4450-aa06-bcd0d8720712.png');
INSERT INTO `sys_file` VALUES (96, '2022-04-04 05:33:38', NULL, NULL, NULL, 'a220997b-16a6-43f2-8b0e-9ce06959ad89.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\a220997b-16a6-43f2-8b0e-9ce06959ad89.png');
INSERT INTO `sys_file` VALUES (97, '2022-04-04 05:36:23', NULL, NULL, NULL, 'ebc5ab68-6305-4ccd-87c4-d2da6fa48b0a.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\ebc5ab68-6305-4ccd-87c4-d2da6fa48b0a.png');
INSERT INTO `sys_file` VALUES (98, '2022-04-04 05:36:54', NULL, NULL, NULL, '0dbceedb-7887-4b2c-ac25-56258b52b63d.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\0dbceedb-7887-4b2c-ac25-56258b52b63d.png');
INSERT INTO `sys_file` VALUES (99, '2022-04-04 05:37:35', NULL, NULL, NULL, '12ef51aa-7879-4a5e-846e-bb94df2b9e44.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\12ef51aa-7879-4a5e-846e-bb94df2b9e44.png');
INSERT INTO `sys_file` VALUES (100, '2022-04-04 05:38:45', NULL, NULL, NULL, '3cf1f83d-a1b1-4a29-aff3-25e9b066ce35.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\3cf1f83d-a1b1-4a29-aff3-25e9b066ce35.png');
INSERT INTO `sys_file` VALUES (101, '2022-04-04 05:39:04', NULL, NULL, NULL, '24429de4-ff30-4250-b017-38124483a141.png', '微信截图_20220325215056.png', 'D:\\developTools\\xampp\\htdocs\\file\\24429de4-ff30-4250-b017-38124483a141.png');
INSERT INTO `sys_file` VALUES (102, '2022-04-04 05:39:07', NULL, NULL, NULL, '86e19e32-b785-4f56-9a17-4a26b4bc0fd7.jpg', '微信截图_20220326002659.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\86e19e32-b785-4f56-9a17-4a26b4bc0fd7.jpg');
INSERT INTO `sys_file` VALUES (103, '2022-04-04 05:39:39', NULL, NULL, NULL, '2b0e6562-2743-4771-ace5-89383093e11d.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\2b0e6562-2743-4771-ace5-89383093e11d.png');
INSERT INTO `sys_file` VALUES (104, '2022-04-04 05:41:29', NULL, NULL, NULL, '9743e539-624d-4505-a45a-ed0f0563e48a.png', '22.png', 'D:\\developTools\\xampp\\htdocs\\file\\9743e539-624d-4505-a45a-ed0f0563e48a.png');
INSERT INTO `sys_file` VALUES (105, '2022-04-14 18:08:34', NULL, NULL, NULL, '4d2f6358-a35c-44c6-96da-0ab3a721e949.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\4d2f6358-a35c-44c6-96da-0ab3a721e949.jpg');
INSERT INTO `sys_file` VALUES (106, '2022-04-14 18:09:28', NULL, NULL, NULL, '3de1f5f0-a23a-4df5-984a-86ce937ce889.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\3de1f5f0-a23a-4df5-984a-86ce937ce889.jpg');
INSERT INTO `sys_file` VALUES (107, '2022-04-14 18:10:23', NULL, NULL, NULL, '6cb451bd-93f5-492c-b934-3d48e7452d0d.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\6cb451bd-93f5-492c-b934-3d48e7452d0d.jpg');
INSERT INTO `sys_file` VALUES (108, '2022-04-14 18:16:24', NULL, NULL, NULL, '58f31024-9dbe-40a3-9b3b-22490d9cbe27.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\58f31024-9dbe-40a3-9b3b-22490d9cbe27.jpg');
INSERT INTO `sys_file` VALUES (109, '2022-04-14 18:16:26', NULL, NULL, NULL, 'ab3b49c2-cf49-48e9-a086-1aa1fea5d36a.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\ab3b49c2-cf49-48e9-a086-1aa1fea5d36a.jpg');
INSERT INTO `sys_file` VALUES (110, '2022-04-14 18:16:29', NULL, NULL, NULL, 'f912849a-533e-41be-80d2-32e69c996c44.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\f912849a-533e-41be-80d2-32e69c996c44.jpg');
INSERT INTO `sys_file` VALUES (111, '2022-04-14 18:16:32', NULL, NULL, NULL, '5be42595-f283-46c7-93d3-0793279530d2.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\developTools\\xampp\\htdocs\\file\\5be42595-f283-46c7-93d3-0793279530d2.jpg');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `login_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `role` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  `enable` int NULL DEFAULT NULL COMMENT '是否启用（1为禁用，否则为启用）',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属地',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `enterprise_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `enterprise_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会统一信用代码',
  `enterprise_owner` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `enterprise_license_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照注册地址',
  `enterprise_license_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2022-03-30 11:35:02', NULL, '2022-04-01 08:47:38', 1, 'a', '系统管理员333', 'ca2@!1cf@aa!23b5b#6f4@adc0560adg#6@2d!6664c5@3d4c35@df413d6*223a', 'M07KjwZBHfVWO93I', '管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (79, '2022-04-02 08:56:57', 1, '2022-04-03 15:06:19', 1, '测试', '1', 'c40a!b#cdac*0d*206cgbfaaab#6da1c64c15f*16*!f0g5#c13c0d6gd33d554b', 'at5rJapu0Phouq7a', '监管仓管理员', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (80, '2022-04-03 13:40:00', 1, '2022-04-03 15:05:42', 1, '测试2', '1', 'd#d0d0@22F3gc45c0*b5*111464613222b24!cd6cca##f3dbgcc#c2a6#!a13a3', 'kp2OBULU5jZcFUra', '监管仓管理员', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (83, '2022-04-04 05:41:43', NULL, NULL, NULL, 'a1', '2', '5d6@0*#caa5g0##gcfa!136b1ddd5d5#aF165b0@@4*33*10612c3*60006533!f', 'lFritdhcbOwvEGMq', '企业用户', NULL, '88', '13333333334', '5', '6', '1', '1', '9743e539-624d-4505-a45a-ed0f0563e48a.png');

-- ----------------------------
-- Table structure for t_appointment
-- ----------------------------
DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE `t_appointment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NOT NULL COMMENT '录入人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `filing_order` int NOT NULL COMMENT '备案单号',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上报的省（省市区）',
  `warehouse` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约监管仓',
  `warehousing_date` date NULL DEFAULT NULL COMMENT '入仓时间',
  `good_type` int NULL DEFAULT NULL COMMENT '货物类别',
  `good_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货物名称',
  `good_source` int NULL DEFAULT NULL COMMENT '货物来源',
  `source_name` int NULL DEFAULT NULL COMMENT '来源名称',
  `warehoused_prove` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出仓证明',
  `origin_place` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原产国/产地',
  `amount` int NULL DEFAULT NULL COMMENT '件数',
  `weight` int NULL DEFAULT NULL COMMENT '重量（Kg）',
  `quarantine_certificate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检疫证明',
  `customs_bill` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报关单',
  `port_inspection_certificate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '港口核酸证明',
  `port_disinfection_certificate` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '港口消杀证明',
  `driver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '承运司机',
  `car_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `driver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  `refuse_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驳回理由',
  `update_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改理由',
  `filing_date` date NULL DEFAULT NULL COMMENT '备案时间',
  `filing_state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备案状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_appointment
-- ----------------------------
INSERT INTO `t_appointment` VALUES (114, '2022-04-14 16:34:06', 1, NULL, NULL, 1, '河北省/秦皇岛市/山海关区', '110', '2022-04-14', 96, '1', 99, 54, '6', '7', 8, 9, '9444', '4', '5', '6', '7', '8', '9', NULL, NULL, '2022-04-14', '保存');

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL COMMENT '录入人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `inspection_meter` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检验计量器',
  `is_periodic_inspection` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否定期校验',
  `inspection_date` date NULL DEFAULT NULL COMMENT '校验日期',
  `is_required_save` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测废弃物是否按规定存放',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES (108, '2022-04-04 19:48:21', 1, '2022-04-04 20:56:50', 1, '22', '是', '2022-04-05', '否');
INSERT INTO `t_device` VALUES (111, '2022-04-04 20:50:17', 1, '2022-04-04 20:55:51', 1, '2', '否', '2022-04-04', '否');
INSERT INTO `t_device` VALUES (112, '2022-04-04 20:57:14', 1, '2022-04-04 20:57:50', 1, '1', '是', '2022-04-23', '否');

-- ----------------------------
-- Table structure for t_disinfectant
-- ----------------------------
DROP TABLE IF EXISTS `t_disinfectant`;
CREATE TABLE `t_disinfectant`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL COMMENT '操作人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消毒液名称',
  `ratio` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消毒液配比比例',
  `deployment_date` date NULL DEFAULT NULL COMMENT '调配时间',
  `deployment_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调配人',
  `concentration` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消毒液浓度',
  `assessment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消杀评估',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_disinfectant
-- ----------------------------
INSERT INTO `t_disinfectant` VALUES (110, '2022-04-12 16:22:36', 1, '2022-04-12 22:56:15', 1, '1', '比例比例', '2022-04-07', '2', '3', '4');

-- ----------------------------
-- Table structure for t_disinfection_record
-- ----------------------------
DROP TABLE IF EXISTS `t_disinfection_record`;
CREATE TABLE `t_disinfection_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `warehouse` int NULL DEFAULT NULL COMMENT '监管仓',
  `area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消杀区域',
  `date` date NULL DEFAULT NULL COMMENT '消杀时间',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `disinfectant_ratio` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消毒液配比',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_disinfection_record
-- ----------------------------
INSERT INTO `t_disinfection_record` VALUES (111, '2022-04-12 22:26:17', NULL, '2022-04-13 09:26:11', 1, 110, '233', '2022-04-15', '11', '比例比例');
INSERT INTO `t_disinfection_record` VALUES (112, '2022-04-12 22:55:50', 1, '2022-04-13 09:26:14', 1, 110, '1', '2022-04-12', '555', '比例比例');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '公告时间',
  `create_user` int NULL DEFAULT NULL COMMENT '公告人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `content` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (106, '2022-04-04 08:04:55', 1, NULL, NULL, '测试', '123123123');
INSERT INTO `t_notice` VALUES (107, '2022-04-04 08:34:10', 1, '2022-04-04 08:34:18', 1, '33', '55\n444');
INSERT INTO `t_notice` VALUES (108, '2022-04-04 20:50:11', 1, NULL, NULL, '1', '2');

-- ----------------------------
-- Table structure for t_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse`;
CREATE TABLE `t_warehouse`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `record_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国家平台备案号',
  `code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监管仓编码',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监管仓名称',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集中监管仓分类',
  `s` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '建筑面积（平方米）',
  `v` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '容量（千克）',
  `area` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '监管仓所在省（市、区）',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `enable_date` date NULL DEFAULT NULL COMMENT '启用日期',
  `enterprise_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营企业名称',
  `enterprise_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运营企业统一社会信用代码',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `longitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集中监管仓经度',
  `latitude` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '集中监管仓纬度',
  `state` int NULL DEFAULT NULL COMMENT '状态（正常、闭仓、废弃）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse
-- ----------------------------
INSERT INTO `t_warehouse` VALUES (110, '2022-04-12 22:56:50', 1, '2022-04-13 22:36:55', 1, '2', '4', '泉山监管仓', '1', '2', '3', '天津市/直辖区', '4', '2022-04-12', '4', '5', '11111111111', '1', '2', 90);
INSERT INTO `t_warehouse` VALUES (111, '2022-04-13 22:37:19', 1, NULL, NULL, '1', '255', '睢宁监管仓', '1', '3', '5', '北京市/直辖区', '6', '2022-04-13', '7', '8', '9', '1', '2', 90);
INSERT INTO `t_warehouse` VALUES (112, '2022-04-13 22:37:46', 1, NULL, NULL, '53', '6', '云龙监管仓', '33', '5', '4', '山西省/阳泉市/矿区', '5', '2022-04-13', '5', '6', '6', '7', '8', 91);

-- ----------------------------
-- Table structure for t_warehouse_user
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user`;
CREATE TABLE `t_warehouse_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL,
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `work_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作种类',
  `warehouse` int NULL DEFAULT NULL COMMENT '所属监管仓',
  `is_inoculate` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否接种疫苗',
  `inoculate_count` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接种次数',
  `inoculate_date` date NULL DEFAULT NULL COMMENT '接种时间',
  `option1` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option2` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option3` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option4` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option5` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option6` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option7` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option8` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option9` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `option10` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_user
-- ----------------------------
INSERT INTO `t_warehouse_user` VALUES (111, '2022-04-13 10:04:07', NULL, '2022-04-13 20:17:05', 1, '1', '女', '3', '4', '5', 110, '是', '7', '2022-04-14', '是', '是', '否', '是', '否', '是', '是', '否', '是', '是');

-- ----------------------------
-- Table structure for t_warehouse_user_check
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user_check`;
CREATE TABLE `t_warehouse_user_check`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL COMMENT '操作人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL COMMENT 't_warehouse_user的id',
  `result` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测结果',
  `date` date NULL DEFAULT NULL COMMENT '检测时间',
  `user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 115 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_user_check
-- ----------------------------
INSERT INTO `t_warehouse_user_check` VALUES (112, '2022-04-13 14:59:49', NULL, '2022-04-13 15:09:27', 1, 111, '1555', '2022-04-13', '4444');
INSERT INTO `t_warehouse_user_check` VALUES (113, '2022-04-13 15:09:06', 1, NULL, NULL, 111, '1', '2022-04-13', '555');

-- ----------------------------
-- Table structure for t_warehouse_user_emergency
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user_emergency`;
CREATE TABLE `t_warehouse_user_emergency`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL COMMENT '操作人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL COMMENT 't_warehouse_user的id',
  `date` date NULL DEFAULT NULL COMMENT '时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_user_emergency
-- ----------------------------
INSERT INTO `t_warehouse_user_emergency` VALUES (115, '2022-04-13 17:58:44', 1, '2022-04-13 17:59:31', 1, 111, '2022-04-13', '2222666');

-- ----------------------------
-- Table structure for t_warehouse_user_isolate
-- ----------------------------
DROP TABLE IF EXISTS `t_warehouse_user_isolate`;
CREATE TABLE `t_warehouse_user_isolate`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL,
  `create_user` int NULL DEFAULT NULL COMMENT '操作人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `pid` int NULL DEFAULT NULL COMMENT 't_warehouse_user的id',
  `date` date NULL DEFAULT NULL COMMENT '隔离时间',
  `place` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '隔离地点',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_warehouse_user_isolate
-- ----------------------------
INSERT INTO `t_warehouse_user_isolate` VALUES (114, '2022-04-13 17:36:33', 1, '2022-04-13 17:37:07', 1, 111, '2022-04-14', '225', '335');

-- ----------------------------
-- Table structure for t_waste_disposal
-- ----------------------------
DROP TABLE IF EXISTS `t_waste_disposal`;
CREATE TABLE `t_waste_disposal`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '录入时间',
  `create_user` int NULL DEFAULT NULL COMMENT '操作人 ',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `item_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品名称',
  `item_amount` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物品数量',
  `save_place` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存放地方',
  `is_save48hour` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否存放48小时',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_waste_disposal
-- ----------------------------
INSERT INTO `t_waste_disposal` VALUES (115, '2022-04-05 12:29:46', 1, NULL, NULL, '1', '2', '3', '是', '55');

SET FOREIGN_KEY_CHECKS = 1;
