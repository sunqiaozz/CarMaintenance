/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50559
 Source Host           : localhost:3306
 Source Schema         : car_maintenance

 Target Server Type    : MySQL
 Target Server Version : 50559
 File Encoding         : 65001

 Date: 27/03/2022 16:00:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car`  (
  `car_id` int(11) NOT NULL,
  `car_identity` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汽车牌照',
  `car_brand` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汽车品牌',
  `car_color` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '汽车颜色',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '汽车主人',
  PRIMARY KEY (`car_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of car
-- ----------------------------

-- ----------------------------
-- Table structure for car_new
-- ----------------------------
DROP TABLE IF EXISTS `car_new`;
CREATE TABLE `car_new`  (
  `new_id` int(11) NOT NULL AUTO_INCREMENT,
  `new_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `new_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `new_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`new_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of car_new
-- ----------------------------
INSERT INTO `car_new` VALUES (1, '使用防冻液应该注意以下事项：', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。\r\n燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-01-20 20:38:34');
INSERT INTO `car_new` VALUES (2, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-10 20:38:55');
INSERT INTO `car_new` VALUES (3, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:18:02');
INSERT INTO `car_new` VALUES (4, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:08');
INSERT INTO `car_new` VALUES (5, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:14');
INSERT INTO `car_new` VALUES (6, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:24');
INSERT INTO `car_new` VALUES (7, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:26');
INSERT INTO `car_new` VALUES (8, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:27');
INSERT INTO `car_new` VALUES (9, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:28');
INSERT INTO `car_new` VALUES (10, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:29');
INSERT INTO `car_new` VALUES (11, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:31');
INSERT INTO `car_new` VALUES (12, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:32');
INSERT INTO `car_new` VALUES (13, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:33');
INSERT INTO `car_new` VALUES (14, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:34');
INSERT INTO `car_new` VALUES (15, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:35');
INSERT INTO `car_new` VALUES (16, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:36');
INSERT INTO `car_new` VALUES (17, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:38');
INSERT INTO `car_new` VALUES (18, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:39');
INSERT INTO `car_new` VALUES (19, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:40');
INSERT INTO `car_new` VALUES (20, '发动机积碳清洗剂的作用，积碳是如何形成的', '发动机积碳清洗剂是专门用于清洗发动机积碳的产品。其成分是高分子表面活性剂、辅助添加剂和石油馏分。性能优异，具有润湿、渗透、乳化、剥离、增溶等多种清洗功能，能快速溶解各种成分复杂的燃烧室碳化物。燃油添加剂清洗：添加剂清洗是将清洗剂直接注入燃油箱，并与燃油混合。车辆起动后，清洗剂将与燃油一起进入燃烧室。当清洁剂进入燃烧室时，它会在一定程度上清洁发动机内部的积碳。', '2022-03-24 22:21:42');

-- ----------------------------
-- Table structure for car_order
-- ----------------------------
DROP TABLE IF EXISTS `car_order`;
CREATE TABLE `car_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `package_id` int(11) NULL DEFAULT NULL COMMENT '套餐id',
  `order_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '未支付' COMMENT '订单状态：付款付款情况',
  `order_progress` int(20) NULL DEFAULT 0 COMMENT '订单进度：百分比',
  `order_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of car_order
-- ----------------------------
INSERT INTO `car_order` VALUES (1, 1, 1, '未支付', 0, '2022-06-19 21:11:14');
INSERT INTO `car_order` VALUES (2, 1, 2, '已支付', 36, '2022-12-19 21:11:23');
INSERT INTO `car_order` VALUES (3, 11, 9, '已支付', 58, '2022-04-01 21:11:25');
INSERT INTO `car_order` VALUES (4, 2, 4, '已支付', 100, '2022-03-19 21:11:26');
INSERT INTO `car_order` VALUES (5, 3, 5, '已支付', 100, '2022-03-19 21:11:28');
INSERT INTO `car_order` VALUES (6, 4, 6, '已支付', 90, '2022-07-01 21:11:29');
INSERT INTO `car_order` VALUES (10, 12, 12, '已支付', 73, '2022-10-01 10:35:06');
INSERT INTO `car_order` VALUES (13, 5, 16, '未支付', 0, '2022-12-21 11:39:13');
INSERT INTO `car_order` VALUES (14, 11, 12, '已支付', 36, '2022-09-21 11:39:13');
INSERT INTO `car_order` VALUES (15, 12, 4, '已支付', 58, '2022-09-21 11:39:13');
INSERT INTO `car_order` VALUES (16, 13, 9, '已支付', 100, '2022-03-21 11:39:13');
INSERT INTO `car_order` VALUES (18, 15, 10, '已支付', 90, '2022-03-21 11:39:13');
INSERT INTO `car_order` VALUES (19, 16, 11, '已支付', 73, '2022-11-01 11:39:13');
INSERT INTO `car_order` VALUES (20, 17, 6, '已支付', 48, '2022-03-22 14:01:48');
INSERT INTO `car_order` VALUES (21, 17, 6, '已支付', 32, '2022-09-22 14:02:13');
INSERT INTO `car_order` VALUES (22, 18, 6, '已支付', 27, '2022-11-22 14:07:56');
INSERT INTO `car_order` VALUES (23, 19, 7, '已支付', 16, '2022-03-22 14:07:56');
INSERT INTO `car_order` VALUES (24, 20, 7, '未支付', 0, '2022-09-22 14:11:00');
INSERT INTO `car_order` VALUES (25, 21, 7, '已支付', 69, '2022-12-22 14:11:00');
INSERT INTO `car_order` VALUES (26, 22, 8, '已支付', 72, '2022-10-22 14:11:00');
INSERT INTO `car_order` VALUES (27, 23, 8, '已支付', 36, '2022-03-22 14:12:04');
INSERT INTO `car_order` VALUES (28, 1, 12, '未支付', 0, '2021-11-23 12:06:19');

-- ----------------------------
-- Table structure for manage
-- ----------------------------
DROP TABLE IF EXISTS `manage`;
CREATE TABLE `manage`  (
  `manage_id` int(11) NOT NULL AUTO_INCREMENT,
  `manage_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理人员用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理人员密码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`manage_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of manage
-- ----------------------------
INSERT INTO `manage` VALUES (1, 'admin', '1234', '1757810778@qq.com', '18866932628', 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG');

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `package_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '套餐id',
  `package_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐名',
  `package_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '套餐内容',
  `package_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '套餐类型',
  `package_price` double NULL DEFAULT NULL COMMENT '套餐价格',
  `package_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`package_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of package
-- ----------------------------
INSERT INTO `package` VALUES (1, '抛光打蜡', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '车漆保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (2, '传统封釉', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '车漆保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (3, '车身镀膜', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '车漆保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (4, '镀晶镀瓷', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '车漆保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (5, '红外线漆面硬化', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '车漆保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (6, '空调清洗', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '空调保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (7, '润滑油养护', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '底盘保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (8, '防冻液养护', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '冬季养护', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (9, '机油养护', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (10, '内饰养护保养', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (11, '汽车精品', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (12, '汽车防护', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (13, '漆面处理', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (14, '内饰美容', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (15, '车身养护美容', '汽车保养是很有学问的，只要汽车是在4S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 6667, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `package` VALUES (16, '防爆隔热膜', '汽车保养是很有学问的，只要汽车是在1231232131234S店长期保养的，所有定期保养的项目都会准时电话通知你的。当然自己平时做做保养也并不很难，关键是要勤，勤劳能致富，勤劳能让你省钱。平时的保养分车的外部保养，发动机保养，轮胎保养，蓄电池保养，空调保养五项。1、外部保养就是整部车身外面，洗车打蜡都是保养的一部份，主要还是多留意车漆表面有没明显的括痕和碰伤。2、发动机保养，勤观察检查机油、刹车油、防冻液的使用状况，发动机身的清洁卫生勤清理。3、轮胎保养，检查轮胎的气压，检查是不有括痕和鼓包，清理胎纹内的夹杂物，有条件时15000公里做轮胎换位调整。4、蓄电池保养，检查电极接线头是否干燥清洁，有绿色氧化物及时用开水清洗。5、空调保养，经常检查清洗空调滤芯。 ', '日常保养', 666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` bigint(20) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件MD5，去重复文件上传',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '假删除',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (2, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (3, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (4, 'node配置.txt', 'txt', 0, 'http://localhost:8090/file/79448d0288e942f3bd45a7254903121f.txt', '7e23a42721d112b089815d431d88e6ac', 0, 1);
INSERT INTO `sys_file` VALUES (5, '解决端口占用.txt', 'txt', 0, 'http://localhost:8090/file/6ec97bc3cbf84bd5bedda3d531669c3b.txt', '4ed969cc6733ab30275d8b717d43f946', 0, 1);
INSERT INTO `sys_file` VALUES (6, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (7, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (8, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (9, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (10, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (11, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (12, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (13, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (14, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (15, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (16, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (17, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (18, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (19, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (20, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (21, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (22, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 0, 1);
INSERT INTO `sys_file` VALUES (23, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 1, 1);
INSERT INTO `sys_file` VALUES (24, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 1, 1);
INSERT INTO `sys_file` VALUES (25, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (26, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (27, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 1, 1);
INSERT INTO `sys_file` VALUES (28, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (29, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 1, 1);
INSERT INTO `sys_file` VALUES (30, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (31, '00F7EB67-4F87-4B91-8D41-746628F23647(20220319-153.JPG', 'JPG', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 1, 1);
INSERT INTO `sys_file` VALUES (32, '77.jpg', 'jpg', 231, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg', '64600ee9e33060ac419588673b2d3466', 1, 1);
INSERT INTO `sys_file` VALUES (33, 'a证件照.jpg', 'jpg', 83, 'http://localhost:8090/file/964a3004ee2b4dc983a2a151e515bfaf.JPG', 'c3fbb1ac545a3bffb3f9ce6dbda10e59', 0, 1);
INSERT INTO `sys_file` VALUES (34, '3_屈学涛-java后端-西安.pdf', 'pdf', 173, 'http://localhost:8090/file/4169c887e7ed42eb8ef04bb4a3d5ea31.pdf', '89e35efdbc658016078d1f9a4824e6bf', 0, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1',
  `account` double NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'user', '1234', '张三', '男', '18866932628', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (2, 'user2', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (3, 'user3', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (4, 'user4', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (5, 'user5', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (6, 'user6', '123', '李四', '男', '18888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (7, 'user7', '123', '李四', '女', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (11, 'zhangsan', '123', '张三', '女', '123213', '1757810778@qq.com', '1', 6666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (12, 'user11', '123', '张三', '男', '18866932628', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (13, 'user21', '123', '李四123', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (14, 'user31', '123', '李四qwe', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (15, 'user41', '123', '李四qweq', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (16, 'user51', '123', '李四asdda', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (17, 'user61', '123', '李四asda', '男', '18888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (18, 'user71', '123', '李四123', '女', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (19, 'zhangsan1', '123', '张三222', '女', '123213', '1757810778@qq.com', '1', 9000000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (20, 'user111', '123', '张三', '男', '18866932628', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (21, '111', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (22, 'user311', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (23, 'user42', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (24, 'user52', '123', '李四', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (25, 'user62', '123', '李四', '男', '18888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (26, 'user74', '123', '李四', '女', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (27, 'zhangsan5', '123', '张三', '女', '123213', '1757810778@qq.com', '1', 6666, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (28, 'user12', '123', '张三', '男', '18866932628', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (29, 'user25', '123', '李四123', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (30, 'user34', '123', '李四qwe', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (31, 'user46', '123', '李四qweq', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (32, 'user58', '123', '李四asdda', '男', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (34, 'user79', '123', '李四123', '女', '18888888888', '1757810778@qq.com', '1', 10000, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');
INSERT INTO `user` VALUES (37, 'qiao1', 'qiao1', '侨侨', '女', '18866932628', '1757810778@qq.com', '1', NULL, 'http://localhost:8090/file/2fcb2950929f4fb9b60f4ca92ba983b7.jpg');

SET FOREIGN_KEY_CHECKS = 1;
