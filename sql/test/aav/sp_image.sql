SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sp_image
-- ----------------------------
DROP TABLE IF EXISTS `sp_image`;
CREATE TABLE `sp_image`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片完整路径地址',
  `type` tinyint NOT NULL COMMENT '1：注册登录  2：轮播图  ',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '更新人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `aav`.`sp_image`
    ADD COLUMN `description` varchar(255) NULL COMMENT '描述' AFTER `update_time`;
ALTER TABLE `aav`.`sp_image`
    ADD COLUMN `status` tinyint NOT NULL DEFAULT 1 COMMENT '1：上架  2:下架' AFTER `description`;
