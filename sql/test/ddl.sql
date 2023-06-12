-- `sp-dev`.category definition

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='密码分类表';


-- `sp-dev`.password definition

CREATE TABLE `password` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '所属用户ID',
  `category_id` bigint NOT NULL COMMENT '密码分类ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='密码表';


-- `sp-dev`.ser_article definition

CREATE TABLE `ser_article` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `title` varchar(150) DEFAULT NULL COMMENT '文章标题 [t j=like, fast-update]',
  `content` longtext COMMENT '文章内容 [f]',
  `type_id` bigint DEFAULT NULL COMMENT '所属分类 [num]',
  `goods_id` bigint DEFAULT NULL COMMENT '推荐商品 [num click=ser_goods.id]',
  `eff_time` datetime DEFAULT NULL COMMENT '有效日期 [date]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `status` int DEFAULT '2' COMMENT '所属状态(1=正常,2=禁用) [j switch]',
  `is_eff` int DEFAULT '1' COMMENT '是否有效 [lc-del yes=1, no=0]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='文章表 \r\n[table icon=el-icon-document-remove]\r\n[fk-s js=(type_id=sys_type.id), show=name.所属分类, drop]\r\n[fk-s js=(goods_id=ser_goods.id), show=name.商品名称, click]';


-- `sp-dev`.ser_goods definition

CREATE TABLE `ser_goods` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称 [text j=like]',
  `avatar` varchar(512) DEFAULT NULL COMMENT '商品头像 [img]',
  `image_list` varchar(2048) DEFAULT NULL COMMENT '轮播图片 [img-list]',
  `content` text COMMENT '图文介绍 [f]',
  `money` int DEFAULT '0' COMMENT '商品价格 [num]',
  `type_id` bigint DEFAULT NULL COMMENT '所属分类 [num]',
  `stock_count` int DEFAULT '0' COMMENT '剩余库存 [num]',
  `status` int DEFAULT '1' COMMENT '商品状态 (1=上架,2=下架) [j]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期 [date-update]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品表\r\n[table icon=el-icon-apple]\r\n[fk-s js=(type_id=sys_type.id), show=name.所属分类, drop]\r\n';


-- `sp-dev`.sp_admin definition

CREATE TABLE `sp_admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id，--主键、自增',
  `name` varchar(100) NOT NULL COMMENT 'admin名称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `pw` varchar(50) DEFAULT NULL COMMENT '明文密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `role_id` int DEFAULT '11' COMMENT '所属角色id',
  `status` int DEFAULT '1' COMMENT '账号状态(1=正常, 2=禁用)',
  `create_by_aid` bigint DEFAULT '-1' COMMENT '创建自哪个管理员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '上次登陆IP',
  `login_count` int DEFAULT '0' COMMENT '登陆次数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='系统管理员表';


-- `sp-dev`.sp_admin_login definition

CREATE TABLE `sp_admin_login` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `acc_id` bigint NOT NULL COMMENT '管理员账号id',
  `acc_token` varchar(300) DEFAULT NULL COMMENT '本次登录Token',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登陆IP',
  `address` varchar(127) DEFAULT NULL COMMENT '登录地点',
  `device` varchar(127) DEFAULT NULL COMMENT '客户端设备标识',
  `system` varchar(127) DEFAULT NULL COMMENT '客户端系统标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1047 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='管理员登录日志表';


-- `sp-dev`.sp_apilog definition

CREATE TABLE `sp_apilog` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '请求id',
  `req_ip` varchar(100) DEFAULT NULL COMMENT '客户端ip',
  `req_api` varchar(512) DEFAULT NULL COMMENT '请求api',
  `req_parame` text COMMENT '请求参数',
  `req_type` varchar(50) DEFAULT NULL COMMENT '请求类型（GET、POST...）',
  `req_token` varchar(50) DEFAULT NULL COMMENT '请求token',
  `req_header` text COMMENT '请求header',
  `res_code` varchar(50) DEFAULT NULL COMMENT '返回-状态码',
  `res_msg` text COMMENT '返回-信息描述',
  `res_string` text COMMENT '返回-整个信息字符串形式',
  `user_id` bigint DEFAULT NULL COMMENT 'user_id',
  `admin_id` bigint DEFAULT NULL COMMENT 'admin_id',
  `start_time` datetime(3) DEFAULT NULL COMMENT '请求开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '请求结束时间',
  `cost_time` bigint DEFAULT NULL COMMENT '花费时间，单位ms',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1664916448776163329 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='api请求记录表';


-- `sp-dev`.sp_cfg definition

CREATE TABLE `sp_cfg` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `cfg_name` varchar(50) NOT NULL COMMENT '配置名',
  `cfg_value` text COMMENT '配置值',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `cfg_name` (`cfg_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='配置信息表';


-- `sp-dev`.sp_role definition

CREATE TABLE `sp_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id，--主键、自增',
  `name` varchar(20) NOT NULL COMMENT '角色名称, 唯一约束',
  `info` varchar(200) DEFAULT NULL COMMENT '角色详细描述',
  `is_lock` int NOT NULL DEFAULT '1' COMMENT '是否锁定(1=是,2=否), 锁定之后不可随意删除, 防止用户误操作',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='系统角色表';


-- `sp-dev`.sp_role_permission definition

CREATE TABLE `sp_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `role_id` bigint DEFAULT NULL COMMENT '角色ID ',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '菜单项ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='角色权限中间表';


-- `sp-dev`.sys_dept definition

CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(50) NOT NULL COMMENT '部门名称 [text]',
  `intro` varchar(512) NOT NULL COMMENT '部门介绍 [textarea]',
  `parent_id` bigint DEFAULT NULL COMMENT '父部门id [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='部门表 [tree fkey=parent_id][table icon=el-icon-crop]';


-- `sp-dev`.sys_notice definition

CREATE TABLE `sys_notice` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `title` varchar(50) DEFAULT NULL COMMENT '公告标题 [text]',
  `content` longtext COMMENT '公告内容 [textarea]',
  `img` varchar(2048) DEFAULT '' COMMENT '单个图片 [img]',
  `audio` varchar(2048) DEFAULT '' COMMENT '单个音频 [audio]',
  `video` varchar(2048) DEFAULT '' COMMENT '单个视频 [video]',
  `img_list` varchar(2048) DEFAULT '' COMMENT '图片集合 [img-list]',
  `audio_list` varchar(2048) DEFAULT '' COMMENT '音频列表 [audio-list]',
  `video_list` varchar(2048) DEFAULT '' COMMENT '视频列表 [video-list]',
  `img_video_list` varchar(2048) DEFAULT '' COMMENT '图视结合 [img-video-list]',
  `is_show` int DEFAULT '1' COMMENT '是否显示 (1=是, 2=否)[j]',
  `is_lock` int DEFAULT '2' COMMENT '是否锁定 (1=是, 2=否)[j]',
  `see_count` int DEFAULT '0' COMMENT '点击数量 [num not-add]',
  `sort` bigint DEFAULT '0' COMMENT '排序索引 [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期 [date-update]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='公告表 [table icon=el-icon-bell]';


-- `sp-dev`.sys_type definition

CREATE TABLE `sys_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(50) NOT NULL COMMENT '分类名字 [text]',
  `icon` varchar(512) NOT NULL COMMENT '分类图标 [img]',
  `sort` int DEFAULT '1' COMMENT '排序索引 [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `NAME` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1005 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='分类表 [table icon=el-icon-eleme] [fk-count js=ser_goods.type_id.商品数量]';


-- `sp-dev`.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-----------



