-- `sp-dev`.ser_article definition

CREATE TABLE `ser_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `title` varchar(150) DEFAULT NULL COMMENT '文章标题 [t j=like, fast-update]',
  `content` longtext COMMENT '文章内容 [f]',
  `type_id` bigint(20) DEFAULT NULL COMMENT '所属分类 [num]',
  `goods_id` bigint(20) DEFAULT NULL COMMENT '推荐商品 [num click=ser_goods.id]',
  `eff_time` datetime DEFAULT NULL COMMENT '有效日期 [date]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `status` int(11) DEFAULT '2' COMMENT '所属状态(1=正常,2=禁用) [j switch]',
  `is_eff` int(11) DEFAULT '1' COMMENT '是否有效 [lc-del yes=1, no=0]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='文章表 \r\n[table icon=el-icon-document-remove]\r\n[fk-s js=(type_id=sys_type.id), show=name.所属分类, drop]\r\n[fk-s js=(goods_id=ser_goods.id), show=name.商品名称, click]';


-- `sp-dev`.ser_goods definition

CREATE TABLE `ser_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(200) DEFAULT NULL COMMENT '商品名称 [text j=like]',
  `avatar` varchar(512) DEFAULT NULL COMMENT '商品头像 [img]',
  `image_list` varchar(2048) DEFAULT NULL COMMENT '轮播图片 [img-list]',
  `content` text COMMENT '图文介绍 [f]',
  `money` int(11) DEFAULT '0' COMMENT '商品价格 [num]',
  `type_id` bigint(20) DEFAULT NULL COMMENT '所属分类 [num]',
  `stock_count` int(11) DEFAULT '0' COMMENT '剩余库存 [num]',
  `status` int(11) DEFAULT '1' COMMENT '商品状态 (1=上架,2=下架) [j]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期 [date-update]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品表\r\n[table icon=el-icon-apple]\r\n[fk-s js=(type_id=sys_type.id), show=name.所属分类, drop]\r\n';


-- `sp-dev`.sp_admin definition

CREATE TABLE `sp_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，--主键、自增',
  `name` varchar(100) NOT NULL COMMENT 'admin名称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像地址',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `pw` varchar(50) DEFAULT NULL COMMENT '明文密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `role_id` bigint(20) DEFAULT '11' COMMENT '所属角色id',
  `status` int(11) DEFAULT '1' COMMENT '账号状态(1=正常, 2=禁用)',
  `create_by_aid` bigint(20) DEFAULT '-1' COMMENT '创建自哪个管理员',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '上次登陆时间',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '上次登陆IP',
  `login_count` int(11) DEFAULT '0' COMMENT '登陆次数',
  `user_type` char(1) NOT NULL DEFAULT '0' COMMENT '会员类型  0:普通  1:会员',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10016 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统管理员表';


-- `sp-dev`.sp_admin_login definition

CREATE TABLE `sp_admin_login` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `acc_id` bigint(20) NOT NULL COMMENT '管理员账号id',
  `acc_token` varchar(300) DEFAULT NULL COMMENT '本次登录Token',
  `login_ip` varchar(50) DEFAULT NULL COMMENT '登陆IP',
  `address` varchar(127) DEFAULT NULL COMMENT '登录地点',
  `device` varchar(127) DEFAULT NULL COMMENT '客户端设备标识',
  `system` varchar(127) DEFAULT NULL COMMENT '客户端系统标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1125 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员登录日志表';


-- `sp-dev`.sp_apilog definition

CREATE TABLE `sp_apilog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '请求id',
  `req_ip` varchar(100) DEFAULT NULL COMMENT '客户端ip',
  `req_api` varchar(512) DEFAULT NULL COMMENT '请求api',
  `req_parame` text COMMENT '请求参数',
  `req_type` varchar(50) DEFAULT NULL COMMENT '请求类型（GET、POST...）',
  `req_token` varchar(50) DEFAULT NULL COMMENT '请求token',
  `req_header` text COMMENT '请求header',
  `res_code` varchar(50) DEFAULT NULL COMMENT '返回-状态码',
  `res_msg` text COMMENT '返回-信息描述',
  `res_string` text COMMENT '返回-整个信息字符串形式',
  `user_id` bigint(20) DEFAULT NULL COMMENT 'user_id',
  `admin_id` bigint(20) DEFAULT NULL COMMENT 'admin_id',
  `start_time` datetime(3) DEFAULT NULL COMMENT '请求开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '请求结束时间',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '花费时间，单位ms',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1710327538334699521 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='api请求记录表';


-- `sp-dev`.sp_cfg definition

CREATE TABLE `sp_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `cfg_name` varchar(50) NOT NULL COMMENT '配置名',
  `cfg_value` text COMMENT '配置值',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `cfg_name` (`cfg_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='配置信息表';


-- `sp-dev`.sp_order definition

CREATE TABLE `sp_order` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `code` varchar(100) NOT NULL COMMENT '订单编号',
  `pay_type` char(1) NOT NULL COMMENT '支付方式  1：支付宝  2: 微信  3:其他',
  `pay_amount` decimal(10,0) DEFAULT NULL COMMENT '支付金额',
  `order_source` char(1) NOT NULL COMMENT '订单来源 1: 注册  2:分享',
  `drawee` varchar(30) DEFAULT NULL COMMENT '付款人',
  `order_pay_time` datetime DEFAULT NULL COMMENT '订单支付时间',
  `order_code` varchar(100) NOT NULL COMMENT '订单编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(100) NOT NULL COMMENT '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';


-- `sp-dev`.sp_product definition

CREATE TABLE `sp_product` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `type` char(1) NOT NULL COMMENT '商品类别  1:包天  2:包月  3: 三个月  4: 包季度  5:包年 6:永久',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL COMMENT '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息表';


-- `sp-dev`.sp_role definition

CREATE TABLE `sp_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id，--主键、自增',
  `name` varchar(20) NOT NULL COMMENT '角色名称, 唯一约束',
  `info` varchar(200) DEFAULT NULL COMMENT '角色详细描述',
  `is_lock` int(11) NOT NULL DEFAULT '1' COMMENT '是否锁定(1=是,2=否), 锁定之后不可随意删除, 防止用户误操作',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统角色表';


-- `sp-dev`.sp_role_permission definition

CREATE TABLE `sp_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID ',
  `permission_code` varchar(50) DEFAULT NULL COMMENT '菜单项ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=546 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色权限中间表';


-- `sp-dev`.sp_semic_child_dictionary definition

CREATE TABLE `sp_semic_child_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `semic_dictionary_id` bigint(20) NOT NULL COMMENT '关联类型表id',
  `abbreviation` varchar(50) NOT NULL COMMENT '英文简写',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新人',
  `update_by` varchar(100) NOT NULL COMMENT '更新人',
  `full_name` varchar(100) NOT NULL COMMENT '英文全称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='半导体词典表的子集信息';


-- `sp-dev`.sp_semic_dictionary definition

CREATE TABLE `sp_semic_dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `semic_type_id` bigint(20) NOT NULL COMMENT '关联类型表id',
  `full_name` varchar(100) NOT NULL COMMENT '英文全称',
  `abbreviation` varchar(50) NOT NULL COMMENT '英文简写',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新人',
  `update_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='半导体词典表';


-- `sp-dev`.sp_semic_type definition

CREATE TABLE `sp_semic_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` int(11) NOT NULL COMMENT '类型  如 1：系统 2：其他，用户可以自定义',
  `name` varchar(100) NOT NULL COMMENT '类型说明',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(100) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='类型表';


-- `sp-dev`.sp_vedio definition

CREATE TABLE `sp_vedio` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `url` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '地址',
  `type` char(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '类型 1:普通  2:会员',
  `status` char(2) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态  1:上架  2:下架',
  `create_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建人',
  `create_time` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人',
  `update_time` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频表';


-- `sp-dev`.sys_dept definition

CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(50) NOT NULL COMMENT '部门名称 [text]',
  `intro` varchar(512) NOT NULL COMMENT '部门介绍 [textarea]',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父部门id [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='部门表 [tree fkey=parent_id][table icon=el-icon-crop]';


-- `sp-dev`.sys_notice definition

CREATE TABLE `sys_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `title` varchar(50) DEFAULT NULL COMMENT '公告标题 [text]',
  `content` longtext COMMENT '公告内容 [textarea]',
  `img` varchar(2048) DEFAULT '' COMMENT '单个图片 [img]',
  `audio` varchar(2048) DEFAULT '' COMMENT '单个音频 [audio]',
  `video` varchar(2048) DEFAULT '' COMMENT '单个视频 [video]',
  `img_list` varchar(2048) DEFAULT '' COMMENT '图片集合 [img-list]',
  `audio_list` varchar(2048) DEFAULT '' COMMENT '音频列表 [audio-list]',
  `video_list` varchar(2048) DEFAULT '' COMMENT '视频列表 [video-list]',
  `img_video_list` varchar(2048) DEFAULT '' COMMENT '图视结合 [img-video-list]',
  `is_show` int(11) DEFAULT '1' COMMENT '是否显示 (1=是, 2=否)[j]',
  `is_lock` int(11) DEFAULT '2' COMMENT '是否锁定 (1=是, 2=否)[j]',
  `see_count` int(11) DEFAULT '0' COMMENT '点击数量 [num not-add]',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序索引 [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  `update_time` datetime DEFAULT NULL COMMENT '修改日期 [date-update]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='公告表 [table icon=el-icon-bell]';


-- `sp-dev`.sys_type definition

CREATE TABLE `sys_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id [num no-add]',
  `name` varchar(50) NOT NULL COMMENT '分类名字 [text]',
  `icon` varchar(512) NOT NULL COMMENT '分类图标 [img]',
  `sort` int(11) DEFAULT '1' COMMENT '排序索引 [num]',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期 [date-create]',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `NAME` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1009 DEFAULT CHARSET=gbk ROW_FORMAT=COMPACT COMMENT='分类表 [table icon=el-icon-eleme] [fk-count js=ser_goods.type_id.商品数量]';

ALTER TABLE `sp-dev`.sp_apilog MODIFY COLUMN res_msg LONGTEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '返回-信息描述';




