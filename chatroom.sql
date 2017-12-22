/*
Navicat MySQL Data Transfer

Source Server         : Amberllo
Source Server Version : 50710
Source Host           : localhost:3306
Source Database       : chatroom

Target Server Type    : MYSQL
Target Server Version : 50710
File Encoding         : 65001

Date: 2017-12-22 21:57:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_group
-- ----------------------------
DROP TABLE IF EXISTS `t_group`;
CREATE TABLE `t_group` (
  `groupname` varchar(255) DEFAULT NULL,
  `groupid` varchar(255) NOT NULL,
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group
-- ----------------------------
INSERT INTO `t_group` VALUES ('工程1部', '2b52e366-253c-4d28-b68f-f8add84cc8be', '00000000001');
INSERT INTO `t_group` VALUES ('工程2部', 'ae86add9-cab0-4e17-b1e1-99c26b8d54ee', '00000000002');
INSERT INTO `t_group` VALUES ('工程3部', '063798ca-4e95-4088-bd36-088435437d03', '00000000003');
INSERT INTO `t_group` VALUES ('工程4部', '26f1bb5b-4f1a-4896-8e34-37b430f82903', '00000000004');

-- ----------------------------
-- Table structure for t_group_ref_user
-- ----------------------------
DROP TABLE IF EXISTS `t_group_ref_user`;
CREATE TABLE `t_group_ref_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` varchar(100) NOT NULL,
  `userid` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_group_ref_user
-- ----------------------------
INSERT INTO `t_group_ref_user` VALUES ('1', '2b52e366-253c-4d28-b68f-f8add84cc8be', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b');
INSERT INTO `t_group_ref_user` VALUES ('2', '2b52e366-253c-4d28-b68f-f8add84cc8be', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e');
INSERT INTO `t_group_ref_user` VALUES ('3', '2b52e366-253c-4d28-b68f-f8add84cc8be', '8d12022e-8412-4c28-8c7e-f50a54ab07a9');
INSERT INTO `t_group_ref_user` VALUES ('4', '2b52e366-253c-4d28-b68f-f8add84cc8be', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327');
INSERT INTO `t_group_ref_user` VALUES ('5', 'ae86add9-cab0-4e17-b1e1-99c26b8d54ee', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e');
INSERT INTO `t_group_ref_user` VALUES ('6', 'ae86add9-cab0-4e17-b1e1-99c26b8d54ee', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327');
INSERT INTO `t_group_ref_user` VALUES ('7', 'ae86add9-cab0-4e17-b1e1-99c26b8d54ee', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b');
INSERT INTO `t_group_ref_user` VALUES ('8', 'ae86add9-cab0-4e17-b1e1-99c26b8d54ee', '049c0869-0716-4c84-8905-448aa5956371');
INSERT INTO `t_group_ref_user` VALUES ('9', '063798ca-4e95-4088-bd36-088435437d03', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b');
INSERT INTO `t_group_ref_user` VALUES ('10', '063798ca-4e95-4088-bd36-088435437d03', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e');
INSERT INTO `t_group_ref_user` VALUES ('11', '063798ca-4e95-4088-bd36-088435437d03', 'c6311f65-4903-47f7-9596-d4b8da2e7897');
INSERT INTO `t_group_ref_user` VALUES ('12', '063798ca-4e95-4088-bd36-088435437d03', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327');
INSERT INTO `t_group_ref_user` VALUES ('13', '063798ca-4e95-4088-bd36-088435437d03', '049c0869-0716-4c84-8905-448aa5956371');
INSERT INTO `t_group_ref_user` VALUES ('14', '063798ca-4e95-4088-bd36-088435437d03', '04a32256-d0a1-4ca7-83f8-f133ffabab1a');
INSERT INTO `t_group_ref_user` VALUES ('15', '063798ca-4e95-4088-bd36-088435437d03', '8d12022e-8412-4c28-8c7e-f50a54ab07a9');
INSERT INTO `t_group_ref_user` VALUES ('16', '063798ca-4e95-4088-bd36-088435437d03', '40cc998f-9b57-4bff-a1de-23c3de0cc785');
INSERT INTO `t_group_ref_user` VALUES ('17', '26f1bb5b-4f1a-4896-8e34-37b430f82903', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b');
INSERT INTO `t_group_ref_user` VALUES ('18', '26f1bb5b-4f1a-4896-8e34-37b430f82903', '40cc998f-9b57-4bff-a1de-23c3de0cc785');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `msgid` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `sender` varchar(100) DEFAULT NULL,
  `receiver` varchar(100) DEFAULT NULL,
  `sendtime` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`msgid`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES ('00000000001', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', '2017-11-19 12:07:13', '消息内容1');
INSERT INTO `t_message` VALUES ('00000000002', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', '2017-11-19 12:07:14', '消息内容2');
INSERT INTO `t_message` VALUES ('00000000003', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-11-19 12:07:15', '消息内容3');
INSERT INTO `t_message` VALUES ('00000000004', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', '2017-11-19 12:07:16', '消息内容4');
INSERT INTO `t_message` VALUES ('00000000005', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-11-19 12:07:17', '消息内容5');
INSERT INTO `t_message` VALUES ('00000000006', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 14:42:26', '123');
INSERT INTO `t_message` VALUES ('00000000007', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 14:43:18', '1234');
INSERT INTO `t_message` VALUES ('00000000008', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 14:43:42', '555');
INSERT INTO `t_message` VALUES ('00000000009', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '04a32256-d0a1-4ca7-83f8-f133ffabab1a', '2017-12-02 14:44:39', '124');
INSERT INTO `t_message` VALUES ('00000000010', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 15:20:03', '4141');
INSERT INTO `t_message` VALUES ('00000000011', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 15:20:43', '1414151515');
INSERT INTO `t_message` VALUES ('00000000012', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:21:34', '151515151');
INSERT INTO `t_message` VALUES ('00000000013', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '2017-12-02 15:27:45', '55555');
INSERT INTO `t_message` VALUES ('00000000014', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'c6311f65-4903-47f7-9596-d4b8da2e7897', '2017-12-02 15:30:14', '51');
INSERT INTO `t_message` VALUES ('00000000015', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'c6311f65-4903-47f7-9596-d4b8da2e7897', '2017-12-02 15:30:46', '516');
INSERT INTO `t_message` VALUES ('00000000016', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:33:31', '51666');
INSERT INTO `t_message` VALUES ('00000000017', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:37:52', 'aaa');
INSERT INTO `t_message` VALUES ('00000000018', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:38:33', 'ggaga');
INSERT INTO `t_message` VALUES ('00000000019', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:39:09', '5151');
INSERT INTO `t_message` VALUES ('00000000020', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:39:54', '6166666');
INSERT INTO `t_message` VALUES ('00000000021', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:40:21', '6161');
INSERT INTO `t_message` VALUES ('00000000022', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 15:40:28', 'HAHA');
INSERT INTO `t_message` VALUES ('00000000023', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:40:35', 'OH YEAR');
INSERT INTO `t_message` VALUES ('00000000024', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 15:40:45', 'NICE TO MEET YOU!');
INSERT INTO `t_message` VALUES ('00000000025', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:40:51', 'GOOD ');
INSERT INTO `t_message` VALUES ('00000000026', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 15:41:01', '61');
INSERT INTO `t_message` VALUES ('00000000027', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 15:42:52', '15');
INSERT INTO `t_message` VALUES ('00000000028', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 16:07:19', 'windows send');
INSERT INTO `t_message` VALUES ('00000000029', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 16:12:16', 'mac send');
INSERT INTO `t_message` VALUES ('00000000030', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 16:07:52', 'hi');
INSERT INTO `t_message` VALUES ('00000000031', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 16:12:37', 'im Amberllo');
INSERT INTO `t_message` VALUES ('00000000032', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 16:08:19', 'you good boy!');
INSERT INTO `t_message` VALUES ('00000000033', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 16:13:03', 'thanks');
INSERT INTO `t_message` VALUES ('00000000034', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 16:13:12', 'test space');
INSERT INTO `t_message` VALUES ('00000000035', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-02 16:09:33', '51');
INSERT INTO `t_message` VALUES ('00000000036', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-02 16:14:11', 'rwqrq');
INSERT INTO `t_message` VALUES ('00000000037', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:14:43', 'hihi');
INSERT INTO `t_message` VALUES ('00000000038', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:15:30', 'qrq');
INSERT INTO `t_message` VALUES ('00000000039', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:15:50', '51');
INSERT INTO `t_message` VALUES ('00000000040', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:15:55', 'tqtq');
INSERT INTO `t_message` VALUES ('00000000041', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:15:59', ' ');
INSERT INTO `t_message` VALUES ('00000000042', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:16:07', '    ');
INSERT INTO `t_message` VALUES ('00000000043', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '2017-12-02 16:16:16', 'twst 41');
INSERT INTO `t_message` VALUES ('00000000044', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:29:32', '14141');
INSERT INTO `t_message` VALUES ('00000000045', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:29:58', '41');
INSERT INTO `t_message` VALUES ('00000000046', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:30:59', '51');
INSERT INTO `t_message` VALUES ('00000000047', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:32:02', '51');
INSERT INTO `t_message` VALUES ('00000000048', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:35:33', '6666');
INSERT INTO `t_message` VALUES ('00000000049', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-03 01:35:38', '1261616');
INSERT INTO `t_message` VALUES ('00000000050', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '26f1bb5b-4f1a-4896-8e34-37b430f82903', '2017-12-03 01:45:18', '41');
INSERT INTO `t_message` VALUES ('00000000051', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '2017-12-22 20:21:36', '123');
INSERT INTO `t_message` VALUES ('00000000052', '40cc998f-9b57-4bff-a1de-23c3de0cc785', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '2017-12-22 20:21:41', '567867946');
INSERT INTO `t_message` VALUES ('00000000053', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-22 20:22:16', '12351325');
INSERT INTO `t_message` VALUES ('00000000054', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-22 20:22:21', '694679679');
INSERT INTO `t_message` VALUES ('00000000055', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '063798ca-4e95-4088-bd36-088435437d03', '2017-12-22 20:26:59', '626');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userid` varchar(100) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `username` varchar(100) NOT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0000000001', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '张三', '123', 'zhangsan');
INSERT INTO `t_user` VALUES ('0000000002', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '李四', '123', 'lisi');
INSERT INTO `t_user` VALUES ('0000000003', 'c6311f65-4903-47f7-9596-d4b8da2e7897', '王明', '123', 'wangming');
INSERT INTO `t_user` VALUES ('0000000004', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '张六', '123', 'zhangliu');
INSERT INTO `t_user` VALUES ('0000000005', '049c0869-0716-4c84-8905-448aa5956371', '李明', '123', 'liming');
INSERT INTO `t_user` VALUES ('0000000006', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '陈丽', '123', 'chenli');
INSERT INTO `t_user` VALUES ('0000000007', '04a32256-d0a1-4ca7-83f8-f133ffabab1a', '海涛', '123', 'haitao');
INSERT INTO `t_user` VALUES ('0000000011', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', '韩信', '123', 'hanxin');

-- ----------------------------
-- Table structure for t_user_state
-- ----------------------------
DROP TABLE IF EXISTS `t_user_state`;
CREATE TABLE `t_user_state` (
  `id` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `state` int(2) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_state
-- ----------------------------
INSERT INTO `t_user_state` VALUES ('00000000001', '40cc998f-9b57-4bff-a1de-23c3de0cc785', '01');
INSERT INTO `t_user_state` VALUES ('00000000002', 'd146aa7d-e863-40b7-8daa-c8d7eddfa327', '00');
INSERT INTO `t_user_state` VALUES ('00000000003', 'c6311f65-4903-47f7-9596-d4b8da2e7897', '00');
INSERT INTO `t_user_state` VALUES ('00000000004', 'abb6ba94-2b65-4f38-8c96-2c82a6d0514e', '00');
INSERT INTO `t_user_state` VALUES ('00000000005', '049c0869-0716-4c84-8905-448aa5956371', '00');
INSERT INTO `t_user_state` VALUES ('00000000006', '8d12022e-8412-4c28-8c7e-f50a54ab07a9', '00');
INSERT INTO `t_user_state` VALUES ('00000000007', '04a32256-d0a1-4ca7-83f8-f133ffabab1a', '00');
INSERT INTO `t_user_state` VALUES ('00000000008', 'c2a7db10-65e8-4a5b-8671-d04fa29eb77b', '00');
