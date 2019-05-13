 

SET FOREIGN_KEY_CHECKS=0;

 
DROP TABLE IF EXISTS `l_journey_client`;
CREATE TABLE `l_journey_client` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_info_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户UUID',
  `originating_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '始发地',
  `objective_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `es_departure_time` datetime DEFAULT NULL COMMENT '预计出发时间',
  `es_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `describe` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简短描述',
  `task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任务UUID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='临汾地区_出行_客户表';

 
DROP TABLE IF EXISTS `l_journey_server`;
CREATE TABLE `l_journey_server` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_info_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户UUID',
  `originating_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '始发地',
  `objective_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '目的地',
  `es_departure_time` datetime DEFAULT NULL COMMENT '预计出发时间',
  `es_arrival_time` datetime DEFAULT NULL COMMENT '预计到达时间',
  `driving_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '预计行驶时间',
  `describe` varchar(512) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简短描述',
  `task_uuid` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '任务UUID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='临汾地区_出行_服务表';

 
DROP TABLE IF EXISTS `l_task_config`;
CREATE TABLE `l_task_config` (
  `task_uuid` varchar(40) NOT NULL COMMENT '任务ID',
  `task_type` int(2) DEFAULT '0' COMMENT '任务类型',
  `task_weight` decimal(14,2) DEFAULT NULL COMMENT '任务权重',
  `task_pay_amount` decimal(14,2) DEFAULT NULL COMMENT '支付金额',
  `task_start_time` datetime DEFAULT NULL COMMENT '任务开始时间',
  `task_end_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `tsk_time` int(11) DEFAULT '120' COMMENT '任务时长(分钟为单位)',
  `task_status` int(11) DEFAULT '0' COMMENT '任务状态:0,未激活 1.已激活.2已过期',
  `task_evaluation` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '任务评价/建议',
  PRIMARY KEY (`task_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务管理';

 
DROP TABLE IF EXISTS `l_user_info`;
CREATE TABLE `l_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uuid` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'UUID',
  `user_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
  `phone_num` int(11) DEFAULT '0' COMMENT '手机号',
  `sex` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `address` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '详细地址',
  `area_coding` int(11) DEFAULT '0' COMMENT '区域编码',
  `identity_cards` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号码',
  `car_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '车牌号',
  `business_license_num` varchar(40) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营业执照编码',
  `customer_type` int(2) DEFAULT NULL COMMENT '用户类型',
  `email` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `avatarUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像URL',
  `status` int(2) DEFAULT '0' COMMENT '状态: 1.启用 2.停用 3.暂时锁定',
  `is_detele` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';
