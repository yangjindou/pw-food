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

 Date: 01/04/2022 18:04:40
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
  `key` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'key',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

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
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐',
  `role` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2022-03-30 11:35:02', NULL, '2022-04-01 08:47:38', 1, 'a', '系统管理员333', 'ca2@!1cf@aa!23b5b#6f4@adc0560adg#6@2d!6664c5@3d4c35@df413d6*223a', 'M07KjwZBHfVWO93I', '管理员');
INSERT INTO `sys_user` VALUES (71, '2022-04-01 08:30:19', 1, NULL, NULL, 'ccz', 'z', 'bf!4d*!c6!!g20113310bd@Fc@02d20fa3a#3F6!b04a6c4F*3*2@1#b@g@d5a2!', '2MZPwz2DehNBzVgt', '监管仓管理员');

SET FOREIGN_KEY_CHECKS = 1;
