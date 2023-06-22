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
