-- aav.app_version definition

CREATE TABLE `app_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `androidUrl` varchar(100) NOT NULL COMMENT '安卓下载地址',
  `iosUrl` varchar(100) NOT NULL COMMENT 'ios下载地址',
  `isForceUpdate` varchar(10) NOT NULL COMMENT '是否强制更新   1：是  2：否',
  `version` varchar(100) NOT NULL COMMENT '版本号',
  `versionCode` varchar(100) NOT NULL COMMENT '版本code',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='app版本管理表';


-- aav.sp_cfg definition

CREATE TABLE `sp_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `cfg_name` varchar(50) NOT NULL COMMENT '配置名',
  `cfg_value` text COMMENT '配置值',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `cfg_name` (`cfg_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='配置信息表';


-- aav.sp_image definition

CREATE TABLE `sp_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `url` varchar(255) NOT NULL COMMENT '图片完整路径地址',
  `type` tinyint(4) NOT NULL COMMENT '1：注册登录  2：轮播图  ',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) NOT NULL COMMENT '创建人',
  `update_by` varchar(50) NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1：上架  2:下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;


-- aav.sp_vedio definition

CREATE TABLE `sp_vedio` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `url` varchar(200) DEFAULT NULL COMMENT 'Mp4地址',
  `type` char(2) DEFAULT NULL COMMENT '类型 1:普通  2:会员',
  `status` char(2) DEFAULT NULL COMMENT '状态  1:上架  2:下架',
  `create_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(100) DEFAULT NULL COMMENT '更新时间',
  `img_path` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='视频表';


-- aav.sys_user definition

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(30) NOT NULL COMMENT '用户名',
  `phone` varchar(30) NOT NULL COMMENT '手机号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;