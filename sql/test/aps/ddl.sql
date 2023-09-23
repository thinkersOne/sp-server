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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='密码分类表';


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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='密码表';


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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='用户表';

-- 版本更新
CREATE TABLE `version_info` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `version` varchar(50) NOT NULL COMMENT '当前版本号',
    `url` varchar(255) NOT NULL COMMENT '安装程序下载地址',
    `description` varchar(500) NOT NULL COMMENT '版本更新内容说明',
    `create_by` varchar(32) NOT NULL COMMENT '创建人',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_by` varchar(32) DEFAULT NULL COMMENT '更新人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT='版本更新表';
CREATE TABLE aps.chart_message (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	user_id BIGINT NOT NULL COMMENT 'userId',
	content varchar(100) NOT NULL COMMENT '对话',
	sender varchar(100) NOT NULL COMMENT '发送者',
	create_by varchar(100) NOT NULL COMMENT 'createBy',
	create_time DATETIME NOT NULL COMMENT 'createTime',
	update_by varchar(100) NOT NULL COMMENT 'updateBy',
	update_time DATETIME NOT NULL COMMENT 'updateTime',
	CONSTRAINT chart_message_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci
COMMENT='智能聊天信息表';

ALTER TABLE aps.chart_message ADD `role` varchar(50) NOT NULL;
ALTER TABLE aps.chart_message ADD plantform TINYINT DEFAULT 1 NOT NULL COMMENT '1:文心一言   2:通义千问';

ALTER TABLE aps.chart_message MODIFY COLUMN content TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '对话';

