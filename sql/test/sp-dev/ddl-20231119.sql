
ALTER TABLE `sp-dev`.sp_admin MODIFY COLUMN user_type char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '2' NOT NULL COMMENT '会员类型  0:普通  1:会员 2：试用会员';


