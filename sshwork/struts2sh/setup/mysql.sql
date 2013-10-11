SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for app_config
-- ----------------------------
DROP TABLE IF EXISTS `app_config`;
CREATE TABLE `app_config` (
  `id` int(11) NOT NULL auto_increment COMMENT '编号',
  `app_title` varchar(200) character set utf8 NOT NULL COMMENT '应用标题',
  `badwords` text character set utf8 COMMENT '感敏字词',
  `affiche_title` varchar(200) character set utf8 default NULL COMMENT '公告标题',
  `affiche_content` text character set utf8 COMMENT '公告内容',
  `copyright` varchar(255) character set utf8 default NULL COMMENT '权版信息',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of app_config
-- ----------------------------
INSERT INTO `app_config` VALUES ('1', 'BeanSoft S2SH系统', '刘跑跑\r\n达赖喇嘛\r\n萨科奇\r\n江泽民\r\n江\\\\*\\\\*泽\\\\*\\\\*民\r\n江 泽 民\r\nNND\r\n我日\r\nfuck\r\nbitch', '系统测试版上线!', '系统测试版于2009年5月1日上线, 欢迎广大用户参与并提出宝贵建议!<br>\r\n信息反馈: <b>beansoft@126.com</b>', '版权所有 (c) 2009 BeanSoft@126.com，保留所有权利。');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `NAME` varchar(200) NOT NULL COMMENT '商品名',
  `amount` int(11) NOT NULL COMMENT '库存',
  `audit_date` date default NULL COMMENT '审核日期',
  `audited` tinyint(1) default NULL COMMENT '已审核',
  `auditor` bigint(20) default NULL COMMENT '审核人编号',
  `catalog` varchar(200) default NULL COMMENT '分类',
  `description` varchar(200) default NULL COMMENT '商品描述信息',
  `photo` varchar(200) default NULL COMMENT '商品照片',
  `price` double default NULL COMMENT '定价',
  `rate` double default NULL COMMENT '分成率, 小数',
  `rebate` double default NULL COMMENT '打折, 小数',
  `totalSold` int(11) default NULL COMMENT '总销量',
  `add_date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '添加日期',
  `vendor_id` bigint(20) default NULL COMMENT '供应商',
  PRIMARY KEY  (`ID`),
  KEY `auditor` (`auditor`),
  KEY `vendor_id` (`vendor_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`auditor`) REFERENCES `scm_user` (`ID`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'DVD', '10', '2008-07-02', '1', '8', '电子书', 'DVD学习光盘', '/upload/1215007014921.jpg', '50', '0.5', '0.9', '30', '2008-07-02 17:47:24', '3');
INSERT INTO `product` VALUES ('3', 'Java学习光盘', '0', null, '1', null, '硬件', '小巧的笔记本', '/upload/1215015506129.gif', '50', '0.5', '1', '8', '2008-07-03 00:18:24', '3');
INSERT INTO `product` VALUES ('4', 'Windows XP 中文版安装光盘', '95', null, '1', null, '操作系统', '主流的桌面操作系统.', '/upload/1215089424018.jpg', '1000', '0.5', '0.8', '5', '2008-07-03 20:50:20', '5');

-- ----------------------------
-- Table structure for scm_order
-- ----------------------------
DROP TABLE IF EXISTS `scm_order`;
CREATE TABLE `scm_order` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `add_date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '添加日期',
  `cost` double default NULL COMMENT '总金额',
  `customer_id` bigint(20) default NULL COMMENT '客户编号',
  `status` int(11) default NULL COMMENT '状态{ 0 - 未支付, 1 - 已支付} ',
  PRIMARY KEY  (`ID`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `scm_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `scm_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of scm_order
-- ----------------------------
INSERT INTO `scm_order` VALUES ('5', '2008-07-04 15:32:33', '140', '8', '0');
INSERT INTO `scm_order` VALUES ('6', '2008-07-04 15:33:10', '1600', '8', '0');
INSERT INTO `scm_order` VALUES ('7', '2008-07-04 16:06:46', '800', '8', '0');
INSERT INTO `scm_order` VALUES ('8', '2008-07-06 17:16:34', '1790', '8', '0');
INSERT INTO `scm_order` VALUES ('9', '2008-07-06 17:17:04', '90', '8', '0');
INSERT INTO `scm_order` VALUES ('10', '2008-07-09 17:39:51', '100', '8', '0');
INSERT INTO `scm_order` VALUES ('11', '2008-09-07 15:26:15', '150', '14', '0');

-- ----------------------------
-- Table structure for scm_order_item
-- ----------------------------
DROP TABLE IF EXISTS `scm_order_item`;
CREATE TABLE `scm_order_item` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `order_id` bigint(20) default NULL COMMENT '订单号',
  `product_id` bigint(20) default NULL COMMENT '产品号',
  `add_date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '添加日期',
  `amount` int(11) default NULL COMMENT '商品个数',
  `price` double default NULL COMMENT '销售时定价',
  `rebate` double default NULL COMMENT '销售时打折',
  `rate` double default NULL COMMENT '销售时分成率',
  `deduct` double default NULL COMMENT '销售时分成金额',
  PRIMARY KEY  (`ID`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `scm_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `scm_order` (`ID`),
  CONSTRAINT `scm_order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of scm_order_item
-- ----------------------------
INSERT INTO `scm_order_item` VALUES ('5', '5', '3', '2008-07-04 15:28:02', '1', '50', '1', '0.5', '25');
INSERT INTO `scm_order_item` VALUES ('6', '5', '1', '2008-07-04 15:27:55', '2', '50', '0.9', '0.5', '45');
INSERT INTO `scm_order_item` VALUES ('7', '6', '4', '2008-07-04 15:33:04', '2', '1000', '0.8', '0.5', '800');
INSERT INTO `scm_order_item` VALUES ('8', '7', '4', '2008-07-04 15:39:53', '1', '1000', '0.8', '0.5', '400');
INSERT INTO `scm_order_item` VALUES ('9', '8', '1', '2008-07-06 17:15:50', '2', '50', '0.9', '0.5', '45');
INSERT INTO `scm_order_item` VALUES ('10', '8', '4', '2008-07-06 17:15:56', '2', '1000', '0.8', '0.5', '800');
INSERT INTO `scm_order_item` VALUES ('11', '8', '3', '2008-07-06 17:15:54', '2', '50', '1', '0.5', '50');
INSERT INTO `scm_order_item` VALUES ('12', '9', '1', '2008-07-06 17:16:52', '2', '50', '0.9', '0.5', '45');
INSERT INTO `scm_order_item` VALUES ('13', '10', '3', '2008-07-09 17:39:22', '2', '50', '1', '0.5', '50');
INSERT INTO `scm_order_item` VALUES ('14', '11', '3', '2008-09-03 23:03:14', '3', '50', '1', '0.5', '75');

-- ----------------------------
-- Table structure for scm_resource
-- ----------------------------
DROP TABLE IF EXISTS `scm_resource`;
CREATE TABLE `scm_resource` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `role_id` int(11) default NULL COMMENT '角色编号',
  `uri` varchar(200) default NULL COMMENT '资源地址',
  `add_date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '添加日期',
  `note` varchar(200) default NULL COMMENT '说明',
  PRIMARY KEY  (`ID`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `scm_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `scm_role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of scm_resource
-- ----------------------------
INSERT INTO `scm_resource` VALUES ('2', '-1', '/error.jsp', '2008-07-16 11:27:56', '错误页面');
INSERT INTO `scm_resource` VALUES ('3', '0', '/error.jsp', '2008-07-16 11:28:45', '错误页面');
INSERT INTO `scm_resource` VALUES ('4', '0', '/welcome.jsp', '2008-07-16 15:15:17', '欢迎页面, 需要登录');
INSERT INTO `scm_resource` VALUES ('5', '1', '/welcome.jsp', '2008-07-16 15:37:35', '欢迎页面, 需要登录');
INSERT INTO `scm_resource` VALUES ('6', '2', '/welcome.jsp', '2008-07-16 15:37:34', '欢迎页面, 需要登录');
INSERT INTO `scm_resource` VALUES ('7', '0', '/cart/ajaxAddProduct.action', '2008-07-18 22:26:15', '购物车添加商品');
INSERT INTO `scm_resource` VALUES ('8', '4', '/admin/*', '2008-08-30 15:49:39', '管理员页面限制');
INSERT INTO `scm_resource` VALUES ('9', '3', '/welcome.jsp', '2008-08-30 17:26:40', null);
INSERT INTO `scm_resource` VALUES ('10', '4', '/welcome.jsp', '2008-08-30 17:26:48', null);

-- ----------------------------
-- Table structure for scm_role
-- ----------------------------
DROP TABLE IF EXISTS `scm_role`;
CREATE TABLE `scm_role` (
  `ID` int(11) NOT NULL COMMENT '编号',
  `role_name` varchar(200) default NULL COMMENT '角色名',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of scm_role
-- ----------------------------
INSERT INTO `scm_role` VALUES ('-1', '游客');
INSERT INTO `scm_role` VALUES ('0', '普通');
INSERT INTO `scm_role` VALUES ('1', '供应商');
INSERT INTO `scm_role` VALUES ('2', '操作员');
INSERT INTO `scm_role` VALUES ('3', '财务经理');
INSERT INTO `scm_role` VALUES ('4', '系统管理员');
INSERT INTO `scm_role` VALUES ('5', '新角色');

-- ----------------------------
-- Table structure for scm_user
-- ----------------------------
DROP TABLE IF EXISTS `scm_user`;
CREATE TABLE `scm_user` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `NAME` varchar(200) NOT NULL COMMENT '用户名',
  `real_Name` varchar(200) NOT NULL COMMENT '真实姓名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `address` varchar(200) NOT NULL COMMENT '详细地址',
  `post_code` varchar(200) NOT NULL COMMENT '邮政编码',
  `home_phone` varchar(200) default NULL COMMENT '家庭电话',
  `gender` int(11) NOT NULL COMMENT '性别',
  `cell_phone` varchar(200) default NULL COMMENT '移动电话',
  `office_phone` varchar(200) default NULL COMMENT '办公电话',
  `birthday` date NOT NULL COMMENT '生日',
  `login_count` int(11) default NULL COMMENT '登录次数',
  `buy_count` int(11) default NULL COMMENT '购物总数',
  `pay_num` double default NULL COMMENT '购物总支出',
  `email` varchar(200) default NULL COMMENT '电子邮件',
  `website` varchar(200) default NULL COMMENT '网站地址',
  `im` varchar(200) default NULL COMMENT '即时通信工具(QQ/MSN等)',
  `user_type` int(11) NOT NULL COMMENT '用户类型, 0 - 普通, 1 - 供应商, 2 - 操作员, 3 - 财务经理, 4 - 管理员',
  `note` varchar(200) default NULL COMMENT '个人说明',
  `photo` varchar(200) default NULL COMMENT '头像地址',
  `reg_date` datetime default NULL COMMENT '注册日期',
  `tempcode` varchar(200) default NULL COMMENT '临时验证码(当前未使用)',
  `active` bit(1) default '\0' COMMENT '是否激活',
  PRIMARY KEY  (`ID`),
  UNIQUE KEY `NAME` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of scm_user
-- ----------------------------
INSERT INTO `scm_user` VALUES ('8', 'beansoft', '刘长炯', 'E10ADC3949BA59ABBE56E057F20F883E', '北京海淀2', '100000', '12345678', '0', '13812345678', '56784321', '1980-01-01', '118', '0', '0', 'beansoft@beansoft.cn', 'http://beansoft.blogjava.net', '不用QQ', '0', 'SCM系统作者', '/upload/1214906778125.jpg', '2008-09-02 12:43:13', null, '');
INSERT INTO `scm_user` VALUES ('9', 'hp1234', 'HP客户代表', 'E10ADC3949BA59ABBE56E057F20F883E', 'HP中国大道', '100000', '12345678', '0', '13812345678', '56784321', '1980-01-01', '23', '0', '0', 'hp@beansoft.cn', 'http://beansoft.blogjava.net', '不用QQ', '1', 'HP 代表', '/upload/1214906778125.jpg', '2008-09-03 12:43:16', null, '');
INSERT INTO `scm_user` VALUES ('10', 'operator', '网上交易员', 'E10ADC3949BA59ABBE56E057F20F883E', '北京海淀', '100000', '12345678', '0', '13812345678', '56784321', '1980-01-01', '5', null, null, 'operator@beansoft.cn', 'http://beansoft.blogjava.net', '不用QQ', '2', 'SCM操作员', '/upload/1215408627846.png', '2008-09-04 12:43:18', null, '');
INSERT INTO `scm_user` VALUES ('16', 'admin', '', 'E10ADC3949BA59ABBE56E057F20F883E', '', '', null, '0', null, null, '2008-08-30', '58', null, null, 'admin@beansoft.cn', null, null, '4', 'admin', null, '2008-09-01 12:43:25', null, '');

-- ----------------------------
-- Table structure for vendor
-- ----------------------------
DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `ID` bigint(20) NOT NULL auto_increment COMMENT '编号',
  `NAME` varchar(200) NOT NULL COMMENT '公司名',
  `address` varchar(200) NOT NULL COMMENT '详细地址',
  `audit_date` date default NULL COMMENT '审核日期',
  `audited` tinyint(1) NOT NULL COMMENT '是否审核',
  `auditor` bigint(20) default NULL COMMENT '审核人编号',
  `catalog` varchar(200) default NULL COMMENT '分类',
  `note` varchar(200) default NULL COMMENT '公司简介',
  `photo` varchar(200) default NULL COMMENT '公司标志图',
  `reg_date` timestamp NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP COMMENT '注册日期',
  `user_id` bigint(20) default NULL COMMENT '所有者编号',
  `website` varchar(200) default NULL COMMENT '网站地址',
  PRIMARY KEY  (`ID`),
  KEY `auditor` (`auditor`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `vendor_ibfk_1` FOREIGN KEY (`auditor`) REFERENCES `scm_user` (`ID`),
  CONSTRAINT `vendor_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `scm_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of vendor
-- ----------------------------
INSERT INTO `vendor` VALUES ('3', 'HP', '北京海淀', null, '1', null, 'IT', '提供硬件和笔记本等产品', '/upload/1214896192814.gif', '2008-07-01 15:09:49', '8', 'http://www.hp.com');
INSERT INTO `vendor` VALUES ('5', 'Microsoft', '美国', null, '1', null, '软件', '提供Office,Windows等软件产品', '/upload/1215001613854.jpg', '2008-07-02 21:32:54', '9', 'http://www.microsoft.com');
