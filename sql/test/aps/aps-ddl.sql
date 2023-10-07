-- aps.category definition

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='密码分类表';


-- aps.chart_message definition

CREATE TABLE `chart_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT 'userId',
  `content` text NOT NULL COMMENT '对话',
  `sender` varchar(100) NOT NULL COMMENT '发送者',
  `create_by` varchar(100) NOT NULL COMMENT 'createBy',
  `create_time` datetime NOT NULL COMMENT 'createTime',
  `update_by` varchar(100) NOT NULL COMMENT 'updateBy',
  `update_time` datetime NOT NULL COMMENT 'updateTime',
  `role` varchar(50) NOT NULL,
  `plantform` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1:文心一言   2:通义千问',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8mb4 COMMENT='智能聊天信息表';


-- aps.memo definition

CREATE TABLE `memo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `content` varchar(255) NOT NULL COMMENT '每项任务/备忘录',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='备忘录表';


-- aps.password definition

CREATE TABLE `password` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '所属用户ID',
  `category_id` bigint(20) NOT NULL COMMENT '密码分类ID',
  `title` varchar(100) DEFAULT NULL COMMENT '密码标题',
  `account` varchar(100) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `url` varchar(200) DEFAULT NULL COMMENT '登录地址',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='密码表';


-- aps.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10014 DEFAULT CHARSET=utf8 COMMENT='用户表';


-- aps.version_info definition

CREATE TABLE `version_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `version` varchar(50) NOT NULL COMMENT '当前版本号',
  `url` varchar(255) NOT NULL COMMENT '安装程序下载地址',
  `description` varchar(500) NOT NULL COMMENT '版本更新内容说明',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='版本更新表';