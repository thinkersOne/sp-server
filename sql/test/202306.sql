-- 会员类型
ALTER TABLE `sp-dev`.sp_admin ADD user_type CHAR DEFAULT 0 NOT NULL COMMENT '会员类型  0:普通  1:会员';
ALTER TABLE `sp-dev`.sp_admin MODIFY COLUMN role_id BIGINT DEFAULT 11 NULL COMMENT '所属角色id';

-- 视频表
drop table if exists sp_vedio;
/*==============================================================*/
/* Table: sp_vedio                                              */
/*==============================================================*/
create table sp_vedio
(
   id                   bigint not null,
   url                  varchar(200),
   type                 char(2),
   status               char(2),
   create_by            varchar(50),
   create_time          varchar(100),
   update_by            varchar(50),
   update_time          varchar(100),
   primary key (id)
);
alter table sp_vedio comment '视频表';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN id bigint NOT NULL COMMENT '主键id';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN url varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '地址';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '类型 1:普通  2:会员';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN status char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '状态  1:上架  2:下架';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN create_by varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '创建人';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN create_time varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '创建时间';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN update_by varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '更新人';
ALTER TABLE `sp-dev`.sp_vedio MODIFY COLUMN update_time varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '更新时间';

-- 配置权限
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spVedio', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spVedio-list', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spVedio-add', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spVedio-info', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spVedio-update', '2023-05-29 05:50:26');

-- 商品订单
CREATE TABLE `sp-dev`.sp_product (
	id BIGINT NOT NULL COMMENT '主键id',
	name varchar(100) NOT NULL COMMENT '商品名称',
	`type` CHAR NOT NULL COMMENT '商品类别  1:包天  2:包月  3: 三个月  4: 包季度  5:包年 6:永久',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	create_by varchar(32) NOT NULL COMMENT '创建人',
	update_time DATETIME NOT NULL COMMENT '更新时间',
	update_by varchar(32) NOT NULL COMMENT '更新人'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='商品信息表';

CREATE TABLE `sp-dev`.sp_order (
	id BIGINT NOT NULL COMMENT '主键id',
	code VARCHAR(100) NOT NULL COMMENT '订单编号',
	pay_type CHAR NOT NULL COMMENT '支付方式  1：支付宝  2: 微信  3:其他',
	pay_amount DECIMAL NULL COMMENT '支付金额',
	order_source CHAR NOT NULL COMMENT '订单来源 1: 注册  2:分享',
	drawee varchar(30) NULL COMMENT '付款人',
	order_pay_time DATETIME NULL COMMENT '订单支付时间',
	order_code varchar(100) NOT NULL COMMENT '订单编号',
	create_time DATETIME NOT NULL COMMENT '创建时间',
	create_by varchar(100) NOT NULL COMMENT '创建人',
	update_time DATETIME NOT NULL COMMENT '更新时间',
	update_by varchar(100) NOT NULL COMMENT '更新人'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci
COMMENT='订单信息表';

INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct-list', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct-add', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct-info', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct-update', '2023-05-29 05:50:26');
INSERT INTO `sp-dev`.sp_role_permission
(id, role_id, permission_code, create_time)
VALUES(null, 1, 'spProduct-delete', '2023-05-29 05:50:26');

























