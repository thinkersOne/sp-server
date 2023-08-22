-- 用户表
CREATE TABLE aav.`user` (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	name varchar(30) NOT NULL COMMENT '用户名',
	phone varchar(30) NOT NULL COMMENT '手机号',
	password varchar(50) NOT NULL COMMENT '密码',
	CONSTRAINT user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

RENAME TABLE aav.`user` TO aav.sys_user;

-- 视频表
-- `sp-dev`.sp_vedio definition

CREATE TABLE `sp_vedio` (
  `id` bigint NOT NULL COMMENT '主键id',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Mp4地址',
  `type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '类型 1:普通  2:会员',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '状态  1:上架  2:下架',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新时间',
  `img_path` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='视频表';

-- 版本管理表
CREATE TABLE aav.app_version (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	androidUrl varchar(100) NOT NULL COMMENT '安卓下载地址',
	iosUrl varchar(100) NOT NULL COMMENT 'ios下载地址',
	isForceUpdate varchar(10) NOT NULL COMMENT '是否强制更新   1：是  2：否',
	version varchar(100) NOT NULL COMMENT '版本号',
	versionCode varchar(100) NOT NULL COMMENT '版本code',
	CONSTRAINT app_version_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='app版本管理表';






