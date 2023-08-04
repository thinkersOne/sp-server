-- 用户表
CREATE TABLE aav.`user` (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	name varchar(30) NOT NULL COMMENT '用户名',
	phone varchar(30) NOT NULL COMMENT '手机号',
	password varchar(50) NOT NULL COMMENT '密码',
	CONSTRAINT user_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

RENAME TABLE aav.`user` TO aav.sys_user;



