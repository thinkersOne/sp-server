SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sp_cfg
-- ----------------------------
DROP TABLE IF EXISTS `sp_cfg`;
CREATE TABLE `sp_cfg`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `cfg_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '配置名',
  `cfg_value` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '配置值',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `cfg_name`(`cfg_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '配置信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sp_cfg
-- ----------------------------
INSERT INTO `sp_cfg` VALUES (3, 'app_cfg', '{\"logoUrl\":\"http://sa-admin.dev33.cn/sa-frame/admin-logo.png\",\"appName\":\"Sa-Plus 后台管理\",\"appVersionNo\":\"v1.27.0\",\"appVersionLog\":\"更新于2022-2-21\"}', '应用配置信息，对外公开');
INSERT INTO `sp_cfg` VALUES (4, 'server_cfg', '{\"reserveInfo\":\"预留信息\",\"userDefaultAvatar\":\"https://oss.dev33.cn/sa-plus/in-file/avatar1.jpg,https://oss.dev33.cn/sa-plus/in-file/avatar2.png\"}', '服务器私有配置');

SET FOREIGN_KEY_CHECKS = 1;