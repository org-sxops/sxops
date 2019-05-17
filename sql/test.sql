
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `lf_car_info`;
CREATE TABLE `lf_car_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) CHARACTER SET utf8 NOT NULL,
  `car_brand` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '车辆品牌',
  `car_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆名称',
  `car_area_coding` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '车辆所属地区编码',
  `car_colour` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '车辆颜色',
  `car_year` date DEFAULT NULL COMMENT '年份',
  `car_license_plate` varchar(10) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '车牌',
  `number_of_passengers` int(3) DEFAULT NULL COMMENT '核载人数',
  `driver_license_photo_url` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '驾驶证照片',
  `driving_license_photo_url` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '行驶证照片',
  `car_appearance_photo_url` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '车辆外观照片',
  `enable` int(2) DEFAULT '1' COMMENT '是否启用',
  `owned_user_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '车辆所属用户',
  `create_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='车辆信息表';


DROP TABLE IF EXISTS `lf_journey_client`;
CREATE TABLE `lf_journey_client` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `massage_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务UUID',
  `user_info_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户UUID',
  `originating_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '始发地',
  `objective_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `es_departure_time` datetime DEFAULT NULL COMMENT '预计出发时间',
  `es_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `describe` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简短描述',
  `total_number` int(2) DEFAULT NULL COMMENT '总人数',
  `is_top` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否置顶',
  `top_time` bigint(11) DEFAULT NULL COMMENT '置顶时长',
  `top_expiration_date` datetime DEFAULT NULL COMMENT '置顶过期时间',
  `enable` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否启用',
  `last_refresh_time` datetime DEFAULT NULL COMMENT '最后刷新时间',
  `top_weight` decimal(18,2) DEFAULT '0.00' COMMENT '置顶权重',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='临汾地区_出行_客户表';


DROP TABLE IF EXISTS `lf_journey_server`;
CREATE TABLE `lf_journey_server` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `massage_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '任务UUID',
  `user_info_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户UUID',
  `originating_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '始发地',
  `objective_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `surplus_number` int(3) DEFAULT NULL COMMENT '可乘人数',
  `number_of_passengers` int(3) DEFAULT NULL COMMENT '总载人数',
  `describe` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简短描述',
  `es_departure_time` datetime DEFAULT NULL COMMENT '预计出发时间',
  `es_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `driving_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '预计行驶时间',
  `is_top` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否置顶',
  `top_time` bigint(11) DEFAULT NULL COMMENT '置顶时长',
  `top_expiration_date` datetime DEFAULT NULL COMMENT '置顶过期时间',
  `enable` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '是否启用',
  `last_refresh_time` datetime DEFAULT NULL COMMENT '最后刷新时间',
  `top_weight` decimal(18,2) DEFAULT '0.00' COMMENT '置顶权重',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='临汾地区_出行_服务表';


DROP TABLE IF EXISTS `lf_user_associated`;
CREATE TABLE `lf_user_associated` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_uuid` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '用户uuid',
  `other_uuid` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '其他属性UUID',
  `other_type` int(20) NOT NULL COMMENT '其他属性类型 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户和其他业务关联表';


DROP TABLE IF EXISTS `lf_user_info`;
CREATE TABLE `lf_user_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户唯一UUID',
  `user_name` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '用户名称',
  `password` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '密码',
  `sex` varchar(2) CHARACTER SET utf8 DEFAULT '' COMMENT '性别',
  `identity_cards` varchar(25) CHARACTER SET utf8 DEFAULT '' COMMENT '身份证号',
  `phone` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `avatarUrl` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '头像URL',
  `area_coding` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '所在区域编码',
  `address` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '详细地址',
  `user_type` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '用户类型',
  `enabled` varchar(10) CHARACTER SET utf8 DEFAULT '1' COMMENT '是否启用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `updata_time` datetime NOT NULL COMMENT '更新时间',
  `create_source` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '创建来源系统',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

DROP TABLE IF EXISTS `lf_operate_log`;
CREATE TABLE `lf_operate_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `operate_desc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `operator_code` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `operator_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `operate_ip` varchar(50) CHARACTER SET utf8  DEFAULT NULL,
  `request` text CHARACTER SET utf8 ,
  `source_system` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

DROP TABLE IF EXISTS `lf_exception_log`;
CREATE TABLE `lf_exception_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uri` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT 'URL',
  `operator_code` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '操作人id',
  `operator` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '操作人',
  `operator_ip` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '操作人ip',
  `host_name` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '主机',
  `brower_message` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '浏览器信息',
  `exception_type` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '日志类型',
  `description` text CHARACTER SET utf8  COMMENT '错误描述' ,
  `detail` text CHARACTER SET utf8  COMMENT '错误详细' ,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='异常信息记录表';


