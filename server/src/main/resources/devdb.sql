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

 Date: 18/04/2022 18:17:16
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
) ENGINE = InnoDB AUTO_INCREMENT = 173 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `sys_file` VALUES (112, '2022-04-14 21:33:30', NULL, NULL, NULL, '6ab7b9df-3d04-4c9c-b70e-600cea3eb56e.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\6ab7b9df-3d04-4c9c-b70e-600cea3eb56e.jpeg');
INSERT INTO `sys_file` VALUES (113, '2022-04-14 21:59:22', NULL, NULL, NULL, '22253c84-8081-408f-8cb8-d1acaa260e15.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\22253c84-8081-408f-8cb8-d1acaa260e15.jpeg');
INSERT INTO `sys_file` VALUES (114, '2022-04-14 21:59:38', NULL, NULL, NULL, '3401e331-1b84-485d-87cd-698caa590f4e.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\3401e331-1b84-485d-87cd-698caa590f4e.jpeg');
INSERT INTO `sys_file` VALUES (115, '2022-04-14 22:27:03', NULL, NULL, NULL, 'ecde3c3b-422a-4e91-9251-833ce1240a00.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\ecde3c3b-422a-4e91-9251-833ce1240a00.jpeg');
INSERT INTO `sys_file` VALUES (116, '2022-04-14 22:30:14', NULL, NULL, NULL, '34ea44ce-a865-488b-8f7e-11a383cbad27.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\34ea44ce-a865-488b-8f7e-11a383cbad27.jpeg');
INSERT INTO `sys_file` VALUES (117, '2022-04-14 22:31:41', NULL, NULL, NULL, '69aa548e-4dd6-4839-b215-d1cb9585ce65.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\69aa548e-4dd6-4839-b215-d1cb9585ce65.jpeg');
INSERT INTO `sys_file` VALUES (118, '2022-04-14 22:31:46', NULL, NULL, NULL, '812540cd-a577-42bf-87bc-c08df4bcbd89.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\812540cd-a577-42bf-87bc-c08df4bcbd89.jpeg');
INSERT INTO `sys_file` VALUES (119, '2022-04-14 22:32:37', NULL, NULL, NULL, 'f3e8af48-566b-4210-9ace-22caae7fbddd.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\f3e8af48-566b-4210-9ace-22caae7fbddd.jpeg');
INSERT INTO `sys_file` VALUES (120, '2022-04-14 22:35:16', NULL, NULL, NULL, '638e58b9-24ef-4582-a820-5a71021366ac.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\638e58b9-24ef-4582-a820-5a71021366ac.jpeg');
INSERT INTO `sys_file` VALUES (121, '2022-04-14 22:35:19', NULL, NULL, NULL, '90b28c52-7fed-4001-b3c5-3cc355baffd0.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\90b28c52-7fed-4001-b3c5-3cc355baffd0.jpeg');
INSERT INTO `sys_file` VALUES (122, '2022-04-14 22:35:30', NULL, NULL, NULL, '2ce521a0-61ea-492c-8a51-5df3d861d5e1.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\2ce521a0-61ea-492c-8a51-5df3d861d5e1.jpeg');
INSERT INTO `sys_file` VALUES (123, '2022-04-14 22:35:32', NULL, NULL, NULL, '790f8d55-bfa5-41d8-90ad-359662f2b908.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\790f8d55-bfa5-41d8-90ad-359662f2b908.jpeg');
INSERT INTO `sys_file` VALUES (124, '2022-04-14 22:40:44', NULL, NULL, NULL, 'd3139588-42f1-4299-8ef0-3a0f7f4de34b.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\d3139588-42f1-4299-8ef0-3a0f7f4de34b.jpeg');
INSERT INTO `sys_file` VALUES (125, '2022-04-14 23:07:54', NULL, NULL, NULL, 'e187248a-71eb-47b9-abb7-fd83285d3a30.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\e187248a-71eb-47b9-abb7-fd83285d3a30.jpeg');
INSERT INTO `sys_file` VALUES (126, '2022-04-14 23:10:02', NULL, NULL, NULL, 'abdf8a3c-4ce2-4097-bc34-c1a5dd554a42.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\abdf8a3c-4ce2-4097-bc34-c1a5dd554a42.jpeg');
INSERT INTO `sys_file` VALUES (127, '2022-04-14 23:10:34', NULL, NULL, NULL, 'ea9505c3-7448-4102-bbee-c300220599fa.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\ea9505c3-7448-4102-bbee-c300220599fa.jpeg');
INSERT INTO `sys_file` VALUES (128, '2022-04-14 23:11:28', NULL, NULL, NULL, '20461b40-b1c6-49c8-957c-4f7ac42ad701.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\20461b40-b1c6-49c8-957c-4f7ac42ad701.jpeg');
INSERT INTO `sys_file` VALUES (129, '2022-04-14 23:11:32', NULL, NULL, NULL, 'ed9fd655-b59e-423d-a1aa-eb8d2b3dfcb8.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\ed9fd655-b59e-423d-a1aa-eb8d2b3dfcb8.jpeg');
INSERT INTO `sys_file` VALUES (130, '2022-04-14 23:11:42', NULL, NULL, NULL, '7b9a9073-2dde-4742-a705-d891393a7f10.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\7b9a9073-2dde-4742-a705-d891393a7f10.jpeg');
INSERT INTO `sys_file` VALUES (131, '2022-04-14 23:55:31', NULL, NULL, NULL, '34ff53c2-1202-43f4-a86a-28bda02e24b2.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\34ff53c2-1202-43f4-a86a-28bda02e24b2.jpeg');
INSERT INTO `sys_file` VALUES (132, '2022-04-14 23:55:44', NULL, NULL, NULL, 'c61eddf7-f08f-4bfe-914e-2128cb69fa94.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\c61eddf7-f08f-4bfe-914e-2128cb69fa94.jpeg');
INSERT INTO `sys_file` VALUES (133, '2022-04-14 23:55:51', NULL, NULL, NULL, 'aebbb36f-5ab3-41e6-9809-1f7fc1296bec.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\aebbb36f-5ab3-41e6-9809-1f7fc1296bec.jpeg');
INSERT INTO `sys_file` VALUES (134, '2022-04-14 23:56:01', NULL, NULL, NULL, '18fe033e-e0b7-4408-ae94-688feb8d14b5.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\18fe033e-e0b7-4408-ae94-688feb8d14b5.jpeg');
INSERT INTO `sys_file` VALUES (135, '2022-04-15 00:04:55', NULL, NULL, NULL, '103e8a60-e0b4-4819-93c7-2e7dad4e2d9e.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\103e8a60-e0b4-4819-93c7-2e7dad4e2d9e.jpeg');
INSERT INTO `sys_file` VALUES (136, '2022-04-15 00:05:01', NULL, NULL, NULL, '173a2567-a165-4f93-8e06-2736189db0af.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\173a2567-a165-4f93-8e06-2736189db0af.jpeg');
INSERT INTO `sys_file` VALUES (137, '2022-04-15 00:05:05', NULL, NULL, NULL, '44fffc0c-c5ec-49f1-8175-448559c56169.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\44fffc0c-c5ec-49f1-8175-448559c56169.jpeg');
INSERT INTO `sys_file` VALUES (138, '2022-04-15 00:05:44', NULL, NULL, NULL, 'fb6a975e-cb7d-49dc-9fc1-a069a61a6a43.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\fb6a975e-cb7d-49dc-9fc1-a069a61a6a43.jpeg');
INSERT INTO `sys_file` VALUES (139, '2022-04-15 00:05:47', NULL, NULL, NULL, '3839f177-8c4b-4af3-911d-558024a88eed.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\3839f177-8c4b-4af3-911d-558024a88eed.jpeg');
INSERT INTO `sys_file` VALUES (140, '2022-04-15 00:05:50', NULL, NULL, NULL, 'd5f44d9e-7626-4b85-8a51-4d1c8bc2dc59.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\d5f44d9e-7626-4b85-8a51-4d1c8bc2dc59.jpeg');
INSERT INTO `sys_file` VALUES (141, '2022-04-15 00:05:52', NULL, NULL, NULL, '28a2e3d3-db0a-4a3b-9918-441dbd103a67.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\28a2e3d3-db0a-4a3b-9918-441dbd103a67.jpeg');
INSERT INTO `sys_file` VALUES (142, '2022-04-16 18:48:36', NULL, NULL, NULL, '096d80c6-b23a-444b-ad5d-26b5afa155bd.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\096d80c6-b23a-444b-ad5d-26b5afa155bd.jpeg');
INSERT INTO `sys_file` VALUES (143, '2022-04-16 18:48:39', NULL, NULL, NULL, '876af501-ce58-461d-a8df-61903d7ea560.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\876af501-ce58-461d-a8df-61903d7ea560.jpeg');
INSERT INTO `sys_file` VALUES (144, '2022-04-16 18:48:42', NULL, NULL, NULL, '4f54bf06-e5bf-4e16-89e8-508f2697353d.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\4f54bf06-e5bf-4e16-89e8-508f2697353d.jpeg');
INSERT INTO `sys_file` VALUES (145, '2022-04-16 18:48:45', NULL, NULL, NULL, '07f789c0-51b5-4d87-8904-2640aabf779e.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\07f789c0-51b5-4d87-8904-2640aabf779e.jpeg');
INSERT INTO `sys_file` VALUES (146, '2022-04-17 09:08:49', NULL, NULL, NULL, 'bb75d236-417f-4c03-8c45-f324bf67aa2c.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\bb75d236-417f-4c03-8c45-f324bf67aa2c.jpeg');
INSERT INTO `sys_file` VALUES (147, '2022-04-17 09:08:52', NULL, NULL, NULL, '1b5a8c6f-b498-404b-80e8-03c0b73b7eac.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\1b5a8c6f-b498-404b-80e8-03c0b73b7eac.jpeg');
INSERT INTO `sys_file` VALUES (148, '2022-04-17 09:08:55', NULL, NULL, NULL, 'de9d1e4b-af63-49bc-ba81-3423eb4c7a67.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\de9d1e4b-af63-49bc-ba81-3423eb4c7a67.jpeg');
INSERT INTO `sys_file` VALUES (149, '2022-04-17 09:08:57', NULL, NULL, NULL, '8366cd00-17fd-4e5e-9c7f-409b37fd5c3d.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\8366cd00-17fd-4e5e-9c7f-409b37fd5c3d.jpeg');
INSERT INTO `sys_file` VALUES (150, '2022-04-17 09:09:01', NULL, NULL, NULL, 'd0e2aecf-307f-4b89-917f-13fc953198c6.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\d0e2aecf-307f-4b89-917f-13fc953198c6.jpeg');
INSERT INTO `sys_file` VALUES (151, '2022-04-17 09:09:03', NULL, NULL, NULL, '57e84cbd-73ab-46fd-963c-4511f610c079.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\57e84cbd-73ab-46fd-963c-4511f610c079.jpeg');
INSERT INTO `sys_file` VALUES (152, '2022-04-17 09:09:06', NULL, NULL, NULL, '4fecbda0-4d63-4573-9deb-4581ccdd640b.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\4fecbda0-4d63-4573-9deb-4581ccdd640b.jpeg');
INSERT INTO `sys_file` VALUES (153, '2022-04-17 09:09:09', NULL, NULL, NULL, '9e992846-1141-401a-9d02-02f505f54327.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\9e992846-1141-401a-9d02-02f505f54327.jpeg');
INSERT INTO `sys_file` VALUES (154, '2022-04-17 09:09:12', NULL, NULL, NULL, 'a4ad7534-eea6-49e2-9430-182ab7857b59.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\a4ad7534-eea6-49e2-9430-182ab7857b59.jpeg');
INSERT INTO `sys_file` VALUES (155, '2022-04-17 17:13:00', NULL, NULL, NULL, '46d58ec0-324c-491d-b340-06a2d61b1e81.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\46d58ec0-324c-491d-b340-06a2d61b1e81.jpeg');
INSERT INTO `sys_file` VALUES (156, '2022-04-17 17:13:02', NULL, NULL, NULL, 'fc1c8f9f-5637-473e-a5f9-18759fb44c30.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\fc1c8f9f-5637-473e-a5f9-18759fb44c30.jpeg');
INSERT INTO `sys_file` VALUES (157, '2022-04-17 17:13:05', NULL, NULL, NULL, 'f3ce0ec6-066a-46cb-910b-59672312c4c8.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\f3ce0ec6-066a-46cb-910b-59672312c4c8.jpeg');
INSERT INTO `sys_file` VALUES (158, '2022-04-17 17:13:07', NULL, NULL, NULL, '8007a14e-9ce0-4ffa-a54b-6aaa0e51595f.jpeg', '动漫头像2.jpeg', 'D:\\developTools\\xampp\\htdocs\\file\\8007a14e-9ce0-4ffa-a54b-6aaa0e51595f.jpeg');
INSERT INTO `sys_file` VALUES (159, '2022-04-18 10:01:35', NULL, NULL, NULL, '0f18d188-186d-4808-9fb8-ebb6a460a3cc.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\0f18d188-186d-4808-9fb8-ebb6a460a3cc.jpg');
INSERT INTO `sys_file` VALUES (160, '2022-04-18 10:05:39', NULL, NULL, NULL, 'b1084b82-70ad-41d0-aba1-23cf0eeaf17a.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\b1084b82-70ad-41d0-aba1-23cf0eeaf17a.jpg');
INSERT INTO `sys_file` VALUES (161, '2022-04-18 11:23:22', NULL, NULL, NULL, '0227c2f9-3e4c-4c5c-ba3d-908178c4c2ba.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\0227c2f9-3e4c-4c5c-ba3d-908178c4c2ba.jpg');
INSERT INTO `sys_file` VALUES (162, '2022-04-18 11:23:46', NULL, NULL, NULL, 'aaf5e536-b632-46d8-8d68-b96c7df208d6.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\aaf5e536-b632-46d8-8d68-b96c7df208d6.jpg');
INSERT INTO `sys_file` VALUES (163, '2022-04-18 11:23:49', NULL, NULL, NULL, '81aec315-6edf-40ff-9139-7f0b342cbf41.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\81aec315-6edf-40ff-9139-7f0b342cbf41.jpg');
INSERT INTO `sys_file` VALUES (164, '2022-04-18 11:24:04', NULL, NULL, NULL, '624f9e11-4663-4e97-8550-07f2aa4c352a.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\624f9e11-4663-4e97-8550-07f2aa4c352a.jpg');
INSERT INTO `sys_file` VALUES (165, '2022-04-18 11:24:43', NULL, NULL, NULL, '9cdc8157-bc3b-413d-a257-f32be9a9d874.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\9cdc8157-bc3b-413d-a257-f32be9a9d874.jpg');
INSERT INTO `sys_file` VALUES (166, '2022-04-18 11:25:48', NULL, NULL, NULL, 'fec94d21-94a3-4bf0-b957-81ca099eb56c.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\fec94d21-94a3-4bf0-b957-81ca099eb56c.jpg');
INSERT INTO `sys_file` VALUES (167, '2022-04-18 11:26:08', NULL, NULL, NULL, '5255324d-afd5-4c75-bf0f-2eaaec5ab9b1.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\5255324d-afd5-4c75-bf0f-2eaaec5ab9b1.jpg');
INSERT INTO `sys_file` VALUES (168, '2022-04-18 11:27:20', NULL, NULL, NULL, '25d47be4-1dcc-40ee-8004-9bd46e25a6dc.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\25d47be4-1dcc-40ee-8004-9bd46e25a6dc.jpg');
INSERT INTO `sys_file` VALUES (169, '2022-04-18 11:27:54', NULL, NULL, NULL, '231463cc-5d25-43bc-820f-cd5934cb2540.jpg', 'Dingtalk_20220325131555.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\231463cc-5d25-43bc-820f-cd5934cb2540.jpg');
INSERT INTO `sys_file` VALUES (170, '2022-04-18 11:29:15', NULL, NULL, NULL, '2769db38-8a95-4714-ae35-5a0b0bbfb249.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\2769db38-8a95-4714-ae35-5a0b0bbfb249.jpg');
INSERT INTO `sys_file` VALUES (171, '2022-04-18 11:34:30', NULL, NULL, NULL, 'b5793ec7-ba32-45f6-9ca1-5657ccde4469.jpg', 'Dingtalk_20220315165516.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\b5793ec7-ba32-45f6-9ca1-5657ccde4469.jpg');
INSERT INTO `sys_file` VALUES (172, '2022-04-18 11:49:33', NULL, NULL, NULL, '81669892-5b25-4282-bbbe-d066daf7ecd4.jpg', 'nxKevMUrALf9c2957dade7b1649bfe13229fea9acb47.jpg', 'D:\\devTools\\xampp\\htdocs\\file\\81669892-5b25-4282-bbbe-d066daf7ecd4.jpg');

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
  `disabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否禁用',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属地',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `enterprise_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `enterprise_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '社会统一信用代码',
  `enterprise_owner` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人',
  `enterprise_license_address` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照注册地址',
  `enterprise_license_img` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照照片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 94 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2022-03-30 11:35:02', NULL, '2022-04-01 08:47:38', 1, 'a', '系统管理员', 'ca2@!1cf@aa!23b5b#6f4@adc0560adg#6@2d!6664c5@3d4c35@df413d6*223a', 'M07KjwZBHfVWO93I', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '2022-03-30 11:35:02', NULL, '2022-04-01 08:47:38', 1, 'aa', '系统管理员', 'ca2@!1cf@aa!23b5b#6f4@adc0560adg#6@2d!6664c5@3d4c35@df413d6*223a', 'M07KjwZBHfVWO93I', '系统管理员', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (79, '2022-04-02 08:56:57', 1, '2022-04-18 13:16:49', 79, 'b', '监管仓管理员', '31d*cd!d6f30cb4d!1@12643ac531*4d6f1ccdc0#6#aaF5b0@4023cg5a@3c30f', 'VMvGsYedmGaMaLtL', '监管仓管理员', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (80, '2022-04-03 13:40:00', 1, '2022-04-18 18:10:08', 1, '测试2', '1', 'd#d0d0@22F3gc45c0*b5*111464613222b24!cd6cca##f3dbgcc#c2a6#!a13a3', 'kp2OBULU5jZcFUra', '监管仓管理员', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (83, '2022-04-18 11:34:48', NULL, '2022-04-18 18:10:28', 1, 'c', '企业用户', '2*5Fd0!1bcbF3d@!#g*1df1ad!4*d!@61F@#aa3#5F3@616d43#1#fbga4a6b!d2', 'RhXFexHHDY1G3viL', '企业用户', 1, '89', '11111111113', '4', '5', '6', '22', '[\"b5793ec7-ba32-45f6-9ca1-5657ccde4469.jpg\"]');
INSERT INTO `sys_user` VALUES (87, '2022-04-18 11:34:48', NULL, NULL, NULL, 'c', '企业用户', '2*5Fd0!1bcbF3d@!#g*1df1ad!4*d!@61F@#aa3#5F3@616d43#1#fbga4a6b!d2', 'RhXFexHHDY1G3viL', '企业用户', NULL, '89', '11111111113', '4', '5', '6', '22', '[\"b5793ec7-ba32-45f6-9ca1-5657ccde4469.jpg\"]');
INSERT INTO `sys_user` VALUES (88, '2022-04-18 11:34:48', NULL, NULL, NULL, 'c', '企业用户', '2*5Fd0!1bcbF3d@!#g*1df1ad!4*d!@61F@#aa3#5F3@616d43#1#fbga4a6b!d2', 'RhXFexHHDY1G3viL', '企业用户', NULL, '89', '11111111113', '4', '5', '6', '22', '[\"b5793ec7-ba32-45f6-9ca1-5657ccde4469.jpg\"]');
INSERT INTO `sys_user` VALUES (93, '2022-04-18 18:01:04', 1, NULL, NULL, 'zc', '1', '4!6*53bc@a616*4c02a43@cf2!434bb*3#b54aad1c0#10a3#*b#3a02b@12#d1F', 'A6TtSd3hIdZa0qZo', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_appointment
-- ----------------------------
DROP TABLE IF EXISTS `t_appointment`;
CREATE TABLE `t_appointment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime NOT NULL COMMENT '备案时间',
  `create_user` int NOT NULL COMMENT '录入人',
  `update_date` datetime NULL DEFAULT NULL,
  `update_user` int NULL DEFAULT NULL,
  `filing_order` int NOT NULL COMMENT '备案单号',
  `area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上报的省（省市区）',
  `warehouse` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约监管仓',
  `good_type` int NULL DEFAULT NULL COMMENT '货物类别',
  `good_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '货物名称',
  `good_source` int NULL DEFAULT NULL COMMENT '货物来源',
  `source_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源名称',
  `warehoused_prove` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出仓证明',
  `origin_place` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原产国/产地',
  `amount` int NULL DEFAULT NULL COMMENT '件数',
  `weight` int NULL DEFAULT NULL COMMENT '重量（Kg）',
  `quarantine_certificate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检疫证明',
  `customs_bill` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报关单',
  `port_inspection_certificate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '港口核酸证明',
  `port_disinfection_certificate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '港口消杀证明',
  `driver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '承运司机',
  `car_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车牌号',
  `driver_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '司机电话',
  `refuse_reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '驳回理由',
  `update_reason` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改理由',
  `filing_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备案状态',
  `apply_update_count` int NULL DEFAULT NULL COMMENT '申请修改次数',
  `emergency` tinyint(1) NULL DEFAULT NULL COMMENT '应急',
  `sampling_good_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采样货物名称',
  `sampling_amount` int NULL DEFAULT NULL COMMENT '采样数量',
  `sampling_date` datetime NULL DEFAULT NULL COMMENT '采样时间',
  `sampling_remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采样备注',
  `sampling_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '采样检测人',
  `uninstall_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卸货人员',
  `uninstall_date` datetime NULL DEFAULT NULL COMMENT '卸货时间',
  `warehousing_date` datetime NULL DEFAULT NULL COMMENT '入仓时间',
  `warehousing_weight_poultry` double(11, 2) NULL DEFAULT NULL COMMENT '入仓禽类（Kg）',
  `warehousing_weight_livestock` double(11, 2) NULL DEFAULT NULL COMMENT '入仓畜类（Kg）',
  `warehousing_weight_aquatic` double(11, 2) NULL DEFAULT NULL COMMENT '入仓水产品（Kg）',
  `warehousing_weight_other` double(11, 2) NULL DEFAULT NULL COMMENT '入仓其他（Kg）',
  `warehoused_date` datetime NULL DEFAULT NULL COMMENT '出仓时间',
  `warehoused_weight_poultry` double(11, 2) NULL DEFAULT NULL COMMENT '出仓禽类（Kg）',
  `warehoused_weight_livestock` double(11, 2) NULL DEFAULT NULL COMMENT '出仓禽类（Kg）',
  `warehoused_weight_aquatic` double(11, 2) NULL DEFAULT NULL COMMENT '出仓水产品（Kg）',
  `warehoused_weight_other` double(11, 2) NULL DEFAULT NULL COMMENT '出仓其他（Kg）',
  `warehoused_disinfection_certificate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出仓消杀证明图片',
  `warehoused_inspection_certificate` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出仓核酸检测图片',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 118 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_appointment
-- ----------------------------
INSERT INTO `t_appointment` VALUES (114, '2022-04-14 16:34:06', 83, '2022-04-17 17:41:58', 79, 1, '河北省/秦皇岛市/山海关区', '110', 96, '1', 99, '54', '6', '7', 8, 9, '18fe033e-e0b7-4408-ae94-688feb8d14b5.jpeg,fb6a975e-cb7d-49dc-9fc1-a069a61a6a43.jpeg', '3839f177-8c4b-4af3-911d-558024a88eed.jpeg', 'd5f44d9e-7626-4b85-8a51-4d1c8bc2dc59.jpeg', '28a2e3d3-db0a-4a3b-9918-441dbd103a67.jpeg', '7', '8', '9', '', '', '审核通过', 3, 1, 'c', 1, '2022-04-17 00:00:00', 'ccz', 'bb', '55aa5a5', '2022-04-16 00:00:00', '2022-04-17 14:17:03', 20.00, 1.00, 223.00, 6.00, '2022-04-17 15:29:14', 5.00, 6.10, 7.00, 8.00, NULL, NULL);
INSERT INTO `t_appointment` VALUES (115, '2022-04-16 18:59:11', 83, '2022-04-17 13:14:26', 83, 2, '天津市/直辖区/南开区', '111', 96, '32', 99, 'g', 'b', 'a', 2, 1, '096d80c6-b23a-444b-ad5d-26b5afa155bd.jpeg', '876af501-ce58-461d-a8df-61903d7ea560.jpeg', '4f54bf06-e5bf-4e16-89e8-508f2697353d.jpeg', '07f789c0-51b5-4d87-8904-2640aabf779e.jpeg', 'g', 'c', '1', '', NULL, '待审核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-04-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_appointment` VALUES (116, '2022-04-17 09:09:15', 83, '2022-04-17 15:45:50', 79, 3, '天津市/直辖区/河北区', '110', 93, '1', 97, '2', 'vv', '3', 5, 4, 'bb75d236-417f-4c03-8c45-f324bf67aa2c.jpeg,d0e2aecf-307f-4b89-917f-13fc953198c6.jpeg,57e84cbd-73ab-46fd-963c-4511f610c079.jpeg,4fecbda0-4d63-4573-9deb-4581ccdd640b.jpeg,9e992846-1141-401a-9d02-02f505f54327.jpeg,a4ad7534-eea6-49e2-9430-182ab7857b59.jpeg', '1b5a8c6f-b498-404b-80e8-03c0b73b7eac.jpeg', 'de9d1e4b-af63-49bc-ba81-3423eb4c7a67.jpeg', '8366cd00-17fd-4e5e-9c7f-409b37fd5c3d.jpeg', '6', '7', '888', '', NULL, '待审核', NULL, NULL, 'a', 1, '2022-04-17 13:45:04', '', '2', 'cbbbb', '2022-04-17 13:47:14', '2022-04-17 15:45:50', 5.10, 2.30, 44.40, 555.00, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_appointment` VALUES (117, '2022-04-17 17:13:09', 83, NULL, NULL, 4, '北京市', '111', 96, '22', 99, '4', '5', '6', 7, 8, '46d58ec0-324c-491d-b340-06a2d61b1e81.jpeg', 'fc1c8f9f-5637-473e-a5f9-18759fb44c30.jpeg', 'f3ce0ec6-066a-46cb-910b-59672312c4c8.jpeg', '8007a14e-9ce0-4ffa-a54b-6aaa0e51595f.jpeg', '9', '11', '11', '', NULL, '待审核', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `t_device` VALUES (108, '2022-04-04 19:48:21', 79, '2022-04-16 14:34:14', 79, '22', '是', '2022-04-05', '否');
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
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_disinfectant
-- ----------------------------
INSERT INTO `t_disinfectant` VALUES (110, '2022-04-12 16:22:36', 1, '2022-04-12 22:56:15', 1, '1', '比例比例', '2022-04-07', '2', '3', '4');
INSERT INTO `t_disinfectant` VALUES (111, '2022-04-16 14:35:06', 79, NULL, NULL, 'c', 'v', '2022-04-16', '1', '2', '3');

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
INSERT INTO `t_warehouse` VALUES (110, '2022-04-12 22:56:50', 79, '2022-04-13 22:36:55', 1, '2', '4', '泉山监管仓', '1', '2', '3', '天津市/直辖区', '4', '2022-04-12', '4', '5', '11111111111', '1', '2', 90);
INSERT INTO `t_warehouse` VALUES (111, '2022-04-13 22:37:19', 1, NULL, NULL, '1', '255', '睢宁监管仓', '1', '3', '5', '北京市/直辖区', '6', '2022-04-13', '7', '8', '9', '1', '2', 90);
INSERT INTO `t_warehouse` VALUES (112, '2022-04-13 22:37:46', 79, NULL, NULL, '53', '6', '云龙监管仓', '33', '5', '4', '山西省/阳泉市/矿区', '5', '2022-04-13', '5', '6', '6', '7', '8', 91);

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
) ENGINE = InnoDB AUTO_INCREMENT = 117 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_waste_disposal
-- ----------------------------
INSERT INTO `t_waste_disposal` VALUES (115, '2022-04-05 12:29:46', 1, NULL, NULL, '1', '2', '3', '是', '55');
INSERT INTO `t_waste_disposal` VALUES (116, '2022-04-16 14:34:40', 79, NULL, NULL, '2', '3', '4', '否', '55');

SET FOREIGN_KEY_CHECKS = 1;
