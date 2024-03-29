
-- lottery.lottery definition

CREATE TABLE `lottery` (
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '期号',
  `date` varchar(50) NOT NULL DEFAULT '' COMMENT '开奖日期',
  `red` varchar(20) NOT NULL DEFAULT '' COMMENT '中奖号码：红',
  `blue` varchar(20) NOT NULL DEFAULT '' COMMENT '中奖号码：蓝',
  `sales` varchar(200) NOT NULL DEFAULT '' COMMENT '总销售额',
  `one_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '一等奖注数',
  `one_type_money` varchar(10) NOT NULL DEFAULT '' COMMENT '一等奖中奖金额',
  `two_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '二等奖注数',
  `two_type_money` varchar(10) NOT NULL DEFAULT '' COMMENT '二等奖中奖金额',
  `three_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '三等奖注数',
  `three_type_money` varchar(10) NOT NULL DEFAULT '' COMMENT '三等奖中奖金额',
  `poolmoney` varchar(50) NOT NULL DEFAULT '' COMMENT '奖金池',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- lottery.current_lottery definition

ALTER TABLE lottery.lottery MODIFY COLUMN one_type_money varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' NOT NULL COMMENT '一等奖中奖金额';
ALTER TABLE lottery.lottery MODIFY COLUMN two_type_money varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' NOT NULL COMMENT '二等奖中奖金额';
ALTER TABLE lottery.lottery MODIFY COLUMN three_type_money varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' NOT NULL COMMENT '三等奖中奖金额';

-- lottery.lottery_calculate_per definition

CREATE TABLE `lottery_calculate_per` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(100) NOT NULL COMMENT '期号',
  `red` varchar(100) NOT NULL COMMENT '红色球',
  `blue` varchar(100) NOT NULL COMMENT '篮球',
  `red_odd_even_ratio` varchar(100) NOT NULL COMMENT '红球奇数/偶数比例',
  `red_partition_ratio` varchar(100) NOT NULL COMMENT '红球分区比例',
  `red_sum` int(11) NOT NULL COMMENT '红球和',
  `blue_odd_even` varchar(100) NOT NULL COMMENT '蓝球奇数/偶数',
  `blue_big_small` varchar(100) NOT NULL COMMENT '蓝球大/小',
  `blue_partition` int(11) NOT NULL COMMENT '蓝球分区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='统计计算每期双色球表';

ALTER TABLE lottery.lottery_calculate_per ADD `year` INT NULL COMMENT '年';
ALTER TABLE lottery.lottery_calculate_per ADD `month` INT NULL COMMENT '月';
ALTER TABLE lottery.lottery_calculate_per ADD week varchar(100) NULL COMMENT '星期几';
ALTER TABLE lottery.lottery_calculate_per MODIFY COLUMN week INT NOT NULL COMMENT '星期';
ALTER TABLE lottery.lottery_calculate_per MODIFY COLUMN `year` int(11) NOT NULL COMMENT '年';
ALTER TABLE lottery.lottery_calculate_per MODIFY COLUMN `month` int(11) NOT NULL COMMENT '月';
ALTER TABLE lottery.lottery_calculate_per MODIFY COLUMN week VARCHAR(10) NOT NULL COMMENT '年-第几周';
ALTER TABLE lottery.lottery_calculate_per CHANGE red_partition_ratio red_range_ratio varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '红球分区比例';
ALTER TABLE lottery.lottery_calculate_per CHANGE red_odd_even_ratio red_parity_ratio varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '红球奇数/偶数比例';
ALTER TABLE lottery.lottery_calculate_per CHANGE blue_partition blue_range int(11) NOT NULL COMMENT '蓝球分区';
ALTER TABLE lottery.lottery_calculate_per CHANGE blue_odd_even blue_parity varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '蓝球奇数/偶数';
ALTER TABLE lottery.lottery_calculate_per ADD nine_turn_09 varchar(100) NULL COMMENT '九转连环图-09';
ALTER TABLE lottery.lottery_calculate_per ADD nine_turn_17 varchar(100) NULL COMMENT '九转连环图17';
ALTER TABLE lottery.lottery_calculate_per ADD nine_turn_33 varchar(100) NULL COMMENT '九转连环图33';
ALTER TABLE lottery.lottery_calculate_per ADD max_consecutive_numbers INT DEFAULT 0 NULL COMMENT '最大连号数';
ALTER TABLE lottery.lottery_calculate_per ADD consecutive_numbers_count INT DEFAULT 0 NULL COMMENT '连号个数统计';

-- lottery.lottery_calculate_count definition
-- lottery.lottery_calculate_count definition
-- lottery.lottery_calculate_count definition

CREATE TABLE `lottery_calculate_count` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `week` int(11) DEFAULT NULL COMMENT '周',
  `codes` varchar(100) DEFAULT NULL COMMENT '期号XX1-XX0',
  `red1` int(11) DEFAULT '0' COMMENT '红球1个数',
  `red2` int(11) DEFAULT '0' COMMENT '红球2个数',
  `red3` int(11) DEFAULT '0' COMMENT '红球3个数',
  `red4` int(11) DEFAULT '0' COMMENT '红球4个数',
  `red5` int(11) DEFAULT '0' COMMENT '红球5个数',
  `red6` int(11) DEFAULT '0' COMMENT '红球6个数',
  `red7` int(11) DEFAULT '0' COMMENT '红球7个数',
  `red8` int(11) DEFAULT '0' COMMENT '红球8个数',
  `red9` int(11) DEFAULT '0' COMMENT '红球9个数',
  `red10` int(11) DEFAULT '0' COMMENT '红球10个数',
  `red11` int(11) DEFAULT '0' COMMENT '红球11个数',
  `red12` int(11) DEFAULT '0' COMMENT '红球12个数',
  `red13` int(11) DEFAULT '0' COMMENT '红球13个数',
  `red14` int(11) DEFAULT '0' COMMENT '红球14个数',
  `red15` int(11) DEFAULT '0' COMMENT '红球15个数',
  `red16` int(11) DEFAULT '0' COMMENT '红球16个数',
  `red17` int(11) DEFAULT '0' COMMENT '红球17个数',
  `red18` int(11) DEFAULT '0' COMMENT '红球18个数',
  `red19` int(11) DEFAULT '0' COMMENT '红球19个数',
  `red20` int(11) DEFAULT '0' COMMENT '红球20个数',
  `red21` int(11) DEFAULT '0' COMMENT '红球21个数',
  `red22` int(11) DEFAULT '0' COMMENT '红球22个数',
  `red23` int(11) DEFAULT '0' COMMENT '红球23个数',
  `red24` int(11) DEFAULT '0' COMMENT '红球24个数',
  `red25` int(11) DEFAULT '0' COMMENT '红球25个数',
  `red26` int(11) DEFAULT '0' COMMENT '红球26个数',
  `red27` int(11) DEFAULT '0' COMMENT '红球27个数',
  `red28` int(11) DEFAULT '0' COMMENT '红球28个数',
  `red29` int(11) DEFAULT '0' COMMENT '红球29个数',
  `red30` int(11) DEFAULT '0' COMMENT '红球30个数',
  `red31` int(11) DEFAULT '0' COMMENT '红球31个数',
  `red32` int(11) DEFAULT '0' COMMENT '红球32个数',
  `red33` int(11) DEFAULT '0' COMMENT '红球33个数',
  `blue1` int(11) DEFAULT '0' COMMENT '蓝球1个数',
  `blue2` int(11) DEFAULT '0' COMMENT '蓝球2个数',
  `blue3` int(11) DEFAULT '0' COMMENT '蓝球3个数',
  `blue4` int(11) DEFAULT '0' COMMENT '蓝球4个数',
  `blue5` int(11) DEFAULT '0' COMMENT '蓝球5个数',
  `blue6` int(11) DEFAULT '0' COMMENT '蓝球6个数',
  `blue7` int(11) DEFAULT '0' COMMENT '蓝球7个数',
  `blue8` int(11) DEFAULT '0' COMMENT '蓝球8个数',
  `blue9` int(11) DEFAULT '0' COMMENT '蓝球9个数',
  `blue10` int(11) DEFAULT '0' COMMENT '蓝球10个数',
  `blue11` int(11) DEFAULT '0' COMMENT '蓝球11个数',
  `blue12` int(11) DEFAULT '0' COMMENT '蓝球12个数',
  `blue13` int(11) DEFAULT '0' COMMENT '蓝球13个数',
  `blue14` int(11) DEFAULT '0' COMMENT '蓝球14个数',
  `blue15` int(11) DEFAULT '0' COMMENT '蓝球15个数',
  `blue16` int(11) DEFAULT '0' COMMENT '蓝球16个数',
  `cal_type` int(11) NOT NULL DEFAULT '1' COMMENT '1：按月统计  2：按年统计  3：按周统计',
  `red_parity_ratio_2_4` int(11) DEFAULT '0' COMMENT '红球奇偶2:4',
  `red_parity_ratio_4_2` int(11) DEFAULT '0' COMMENT '红球奇偶4:2',
  `red_parity_ratio_5_1` int(11) DEFAULT '0' COMMENT '红球奇偶5:1',
  `red_parity_ratio_1_5` int(11) DEFAULT '0' COMMENT '红球奇偶1:5',
  `red_parity_ratio_3_3` int(11) DEFAULT '0' COMMENT '红球奇偶3:3',
  `red_parity_ratio_6_0` int(11) DEFAULT '0' COMMENT '红球奇偶6:0',
  `red_parity_ratio_0_6` int(11) DEFAULT '0' COMMENT '红球奇偶0:6',
  `red_range_2_3_1` int(11) DEFAULT '0' COMMENT '红球区间比2_3_1',
  `red_range_2_2_2` int(11) DEFAULT '0' COMMENT '红球区间比2_2_2',
  `red_range_3_2_1` int(11) DEFAULT '0' COMMENT '红球区间比3_2_1',
  `red_range_1_2_3` int(11) DEFAULT '0' COMMENT '红球区间比1_2_3',
  `red_range_2_0_4` int(11) DEFAULT '0' COMMENT '红球区间比2_0_4',
  `red_range_2_4_0` int(11) DEFAULT '0' COMMENT '红球区间比2_4_0',
  `red_range_4_0_2` int(11) DEFAULT '0' COMMENT '红球区间比4_0_2',
  `red_range_4_2_0` int(11) DEFAULT '0' COMMENT '红球区间比4_2_0',
  `red_range_3_1_2` int(11) DEFAULT '0' COMMENT '红球区间比3_1_2',
  `red_range_2_1_3` int(11) DEFAULT '0' COMMENT '红球区间比2_1_3',
  `red_range_4_1_1` int(11) DEFAULT '0' COMMENT '红球区间比4_1_1',
  `red_range_1_1_4` int(11) DEFAULT '0' COMMENT '红球区间比1_1_4',
  `red_range_1_4_1` int(11) DEFAULT '0' COMMENT '红球区间比1_4_1',
  `blue_small` int(11) DEFAULT '0' COMMENT '蓝球个数_小',
  `blue_big` int(11) DEFAULT '0' COMMENT '蓝球个数_大',
  `blue_area_one` int(11) DEFAULT '0' COMMENT '蓝球区域_1',
  `blue_area_two` int(11) DEFAULT '0' COMMENT '蓝球区域_2',
  `blue_area_three` int(11) DEFAULT '0' COMMENT '蓝球区域_3',
  `blue_area_four` int(11) DEFAULT '0' COMMENT '蓝球区域_4',
  `blue_parity` int(11) DEFAULT '0' COMMENT '蓝球奇数个数',
  `blue_ratio` int(11) DEFAULT '0' COMMENT '蓝球偶数个数',
  `red_21_60` int(11) DEFAULT '0' COMMENT '红球和21_60',
  `red_73_78` int(11) DEFAULT '0' COMMENT '红球和73_78',
  `red_61_66` int(11) DEFAULT '0' COMMENT '红球和61_66',
  `red_103_108` int(11) DEFAULT '0' COMMENT '红球和103_108',
  `red_91_96` int(11) DEFAULT '0' COMMENT '红球和91_96',
  `red_79_84` int(11) DEFAULT '0' COMMENT '红球和79_84',
  `red_67_72` int(11) DEFAULT '0' COMMENT '红球和67_72',
  `red_109_114` int(11) DEFAULT '0' COMMENT '红球和109_114',
  `red_115_120` int(11) DEFAULT '0' COMMENT '红球和115_120',
  `red_133_138` int(11) DEFAULT '0' COMMENT '红球和133_138',
  `red_97_102` int(11) DEFAULT '0' COMMENT '红球和97_102',
  `red_139_144` int(11) DEFAULT '0' COMMENT '红球和139_144',
  `red_127_132` int(11) DEFAULT '0' COMMENT '红球和127_132',
  `red_121_126` int(11) DEFAULT '0' COMMENT '红球和121_126',
  `red_145_183` int(11) DEFAULT '0' COMMENT '红球和145_183',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3488 DEFAULT CHARSET=utf8 COMMENT='按照不同时间维度统计每个红蓝球情况';
ALTER TABLE lottery.lottery_calculate_count ADD consecutive_numbers_count_0 INT DEFAULT 0 NULL COMMENT '连号个数';
ALTER TABLE lottery.lottery_calculate_count ADD consecutive_numbers_count_1 INT DEFAULT 0 NULL COMMENT '连号个数';
ALTER TABLE lottery.lottery_calculate_count ADD consecutive_numbers_count_2 INT DEFAULT 0 NULL COMMENT '连号个数';
ALTER TABLE lottery.lottery_calculate_count ADD max_consecutive_numbers_1 INT DEFAULT 0 NULL COMMENT '最大连号数';
ALTER TABLE lottery.lottery_calculate_count ADD max_consecutive_numbers_2 INT DEFAULT 0 NULL COMMENT '最大连号数';
ALTER TABLE lottery.lottery_calculate_count ADD max_consecutive_numbers_3 INT DEFAULT 0 NULL COMMENT '最大连号数';
ALTER TABLE lottery.lottery_calculate_count ADD max_consecutive_numbers_4 INT DEFAULT 0 NULL COMMENT '最大连号数';
ALTER TABLE lottery.lottery_calculate_count ADD max_consecutive_numbers_5 INT DEFAULT 0 NULL COMMENT '最大连号数';


-- lottery.lottery_calculate_nine definition

-- lottery.lottery_calculate_nine definition
CREATE TABLE `lottery_calculate_nine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `month` int(11) DEFAULT NULL COMMENT '月份',
  `cal_type` int(11) DEFAULT NULL COMMENT '类型  1：年份  2：月份',
  `codes` varchar(100) DEFAULT NULL COMMENT '区号区间',
  `nine_turn` varchar(100) DEFAULT NULL COMMENT '九转连环分布比',
  `nine_turn_count` int(11) DEFAULT NULL COMMENT '统计个数',
  `nine_turn_type` int(11) DEFAULT NULL COMMENT '09,17,33',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38182 DEFAULT CHARSET=utf8 COMMENT='九转连环图统计表';
ALTER TABLE lottery.lottery_calculate_nine ADD nine_turn_max INT DEFAULT 0 NULL COMMENT '九转连环轴上最大个数';
ALTER TABLE lottery.lottery_calculate_nine ADD nine_turn_min INT DEFAULT 0 NULL COMMENT '九转连环轴上最小个数';


CREATE TABLE lottery.lottery_all (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	red varchar(100) NOT NULL COMMENT '红球',
	CONSTRAINT lottery_all_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='所有可能得红球组合';

CREATE TABLE lottery.lottery_select (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	red varchar(100) NOT NULL COMMENT '红球',
	CONSTRAINT lottery_select_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='经过初步筛选后的全量双色球表';

-- lottery.lottery_forecast definition
-- lottery.lottery_forecast definition
-- lottery.lottery_forecast definition

CREATE TABLE `lottery_forecast` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `red` varchar(100) NOT NULL COMMENT '预测红球',
  `red_parity_ratio` varchar(100) DEFAULT NULL COMMENT '奇偶比',
  `red_range_ratio` varchar(100) DEFAULT NULL COMMENT '分区比',
  `red_sum` varchar(100) DEFAULT NULL COMMENT '红球和值',
  `consecutive_numbers_count` int(11) DEFAULT NULL COMMENT '连号个数',
  `max_consecutive_numbers_count` int(11) DEFAULT NULL COMMENT '最大连号数',
  `nine_turn_09` varchar(100) DEFAULT NULL COMMENT '九转连环-09',
  `nine_turn_17` varchar(100) DEFAULT NULL COMMENT '九转连环-17',
  `nine_turn_33` varchar(100) DEFAULT NULL COMMENT '九转连环-33',
  `code` varchar(100) DEFAULT NULL COMMENT '预测期号',
  `forecast_red` varchar(1000) DEFAULT NULL COMMENT '预测中奖号码',
  `blue` varchar(100) DEFAULT NULL COMMENT '预测蓝球',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='号码预测';

CREATE TABLE lottery.lottery_calculate_nine_count (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	count INT DEFAULT 0 NOT NULL COMMENT '统计出现的个数',
	nine INT NOT NULL COMMENT '分组统计个数',
	`type` INT NOT NULL COMMENT '类型：9、17、33',
	location_type INT NOT NULL COMMENT '方位类型 1、2、3、4',
	CONSTRAINT lottery_calculate_nine_count_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='统计九转中四行数据每行上出现个数的统计表';

ALTER TABLE lottery.lottery_calculate_per ADD before_code_count INT DEFAULT 0 NOT NULL COMMENT '与上一期比较相同的红球数';
ALTER TABLE lottery.lottery_calculate_per ADD before_common_code_count INT DEFAULT 0 NOT NULL COMMENT '同期号上一期相同红球数';


CREATE TABLE lottery.lottery_red_proportion (
	id bigint(20) auto_increment NOT NULL COMMENT '主键id',
	code varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '期号',
	red1 decimal(10,0) DEFAULT 0 NULL COMMENT '红球1占比',
	red2 decimal(10,0) DEFAULT 0 NULL COMMENT '红球2占比',
	red3 decimal(10,0) DEFAULT 0 NULL COMMENT '红球3占比',
	red4 decimal(10,0) DEFAULT 0 NULL COMMENT '红球4占比',
	red5 decimal(10,0) DEFAULT 0 NULL COMMENT '红球5占比',
	red6 decimal(10,0) DEFAULT 0 NULL COMMENT '红球6占比',
	red7 decimal(10,0) DEFAULT 0 NULL COMMENT '红球7占比',
	red8 decimal(10,0) DEFAULT 0 NULL COMMENT '红球8占比',
	red9 decimal(10,0) DEFAULT 0 NULL COMMENT '红球9占比',
	red10 decimal(10,0) DEFAULT 0 NULL COMMENT '红球10占比',
	red11 decimal(10,0) DEFAULT 0 NULL COMMENT '红球11占比',
	red12 decimal(10,0) DEFAULT 0 NULL COMMENT '红球12占比',
	red13 decimal(10,0) DEFAULT 0 NULL COMMENT '红球13占比',
	red14 decimal(10,0) DEFAULT 0 NULL COMMENT '红球14占比',
	red15 decimal(10,0) DEFAULT 0 NULL COMMENT '红球15占比',
	red16 decimal(10,0) DEFAULT 0 NULL COMMENT '红球16占比',
	red17 decimal(10,0) DEFAULT 0 NULL COMMENT '红球17占比',
	red18 decimal(10,0) DEFAULT 0 NULL COMMENT '红球18占比',
	red19 decimal(10,0) DEFAULT 0 NULL COMMENT '红球19占比',
	red20 decimal(10,0) DEFAULT 0 NULL COMMENT '红球20占比',
	red21 decimal(10,0) DEFAULT 0 NULL COMMENT '红球21占比',
	red22 decimal(10,0) DEFAULT 0 NULL COMMENT '红球22占比',
	red23 decimal(10,0) DEFAULT 0 NULL COMMENT '红球23占比',
	red24 decimal(10,0) DEFAULT 0 NULL COMMENT '红球24占比',
	red25 decimal(10,0) DEFAULT 0 NULL COMMENT '红球25占比',
	red26 decimal(10,0) DEFAULT 0 NULL COMMENT '红球26占比',
	red27 decimal(10,0) DEFAULT 0 NULL COMMENT '红球27占比',
	red28 decimal(10,0) DEFAULT 0 NULL COMMENT '红球28占比',
	red29 decimal(10,0) DEFAULT 0 NULL COMMENT '红球29占比',
	red30 decimal(10,0) DEFAULT 0 NULL COMMENT '红球30占比',
	red31 decimal(10,0) DEFAULT 0 NULL COMMENT '红球31占比',
	red32 decimal(10,0) DEFAULT 0 NULL COMMENT '红球32占比',
	red33 decimal(10,0) DEFAULT 0 NULL COMMENT '红球33占比',
	red varchar(100) NULL COMMENT '红球号',
	CONSTRAINT `PRIMARY` PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='各红球号占比';

ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red1 decimal(10,4) DEFAULT 0 NULL COMMENT '红球1占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red2 decimal(10,4) DEFAULT 0 NULL COMMENT '红球2占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red3 decimal(10,4) DEFAULT 0 NULL COMMENT '红球3占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red4 decimal(10,4) DEFAULT 0 NULL COMMENT '红球4占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red5 decimal(10,4) DEFAULT 0 NULL COMMENT '红球5占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red6 decimal(10,4) DEFAULT 0 NULL COMMENT '红球6占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red7 decimal(10,4) DEFAULT 0 NULL COMMENT '红球7占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red8 decimal(10,4) DEFAULT 0 NULL COMMENT '红球8占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red9 decimal(10,4) DEFAULT 0 NULL COMMENT '红球9占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red10 decimal(10,4) DEFAULT 0 NULL COMMENT '红球10占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red11 decimal(10,4) DEFAULT 0 NULL COMMENT '红球11占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red12 decimal(10,4) DEFAULT 0 NULL COMMENT '红球12占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red13 decimal(10,4) DEFAULT 0 NULL COMMENT '红球13占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red14 decimal(10,4) DEFAULT 0 NULL COMMENT '红球14占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red15 decimal(10,4) DEFAULT 0 NULL COMMENT '红球15占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red16 decimal(10,4) DEFAULT 0 NULL COMMENT '红球16占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red17 decimal(10,4) DEFAULT 0 NULL COMMENT '红球17占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red18 decimal(10,4) DEFAULT 0 NULL COMMENT '红球18占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red19 decimal(10,4) DEFAULT 0 NULL COMMENT '红球19占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red20 decimal(10,4) DEFAULT 0 NULL COMMENT '红球20占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red21 decimal(10,4) DEFAULT 0 NULL COMMENT '红球21占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red22 decimal(10,4) DEFAULT 0 NULL COMMENT '红球22占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red23 decimal(10,4) DEFAULT 0 NULL COMMENT '红球23占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red24 decimal(10,4) DEFAULT 0 NULL COMMENT '红球24占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red25 decimal(10,4) DEFAULT 0 NULL COMMENT '红球25占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red26 decimal(10,4) DEFAULT 0 NULL COMMENT '红球26占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red27 decimal(10,4) DEFAULT 0 NULL COMMENT '红球27占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red28 decimal(10,4) DEFAULT 0 NULL COMMENT '红球28占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red29 decimal(10,4) DEFAULT 0 NULL COMMENT '红球29占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red30 decimal(10,4) DEFAULT 0 NULL COMMENT '红球30占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red31 decimal(10,4) DEFAULT 0 NULL COMMENT '红球31占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red32 decimal(10,4) DEFAULT 0 NULL COMMENT '红球32占比';
ALTER TABLE lottery.lottery_red_proportion MODIFY COLUMN red33 decimal(10,4) DEFAULT 0 NULL COMMENT '红球33占比';

ALTER TABLE lottery.lottery_calculate_count MODIFY COLUMN cal_type int(11) DEFAULT 1 NOT NULL COMMENT '1：按年统计  2：按月统计  3：按周统计 4：按码统计';

CREATE TABLE lottery.lottery_config (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	red_rate DECIMAL(10,2) NULL COMMENT '红球比例',
	red_parity_rate DECIMAL(10,2) NULL COMMENT '奇偶比例',
	red_range_rate DECIMAL(10,2) NULL COMMENT '区间比例',
	red_sum_rate DECIMAL(10,2) NULL COMMENT '和值比例',
	consecutive_numbers_count_rate DECIMAL(10,2) NULL COMMENT '连号个数比例',
	max_consecutive_numbers_rate DECIMAL(10,2) NULL COMMENT '最大连号数比例',
	CONSTRAINT lottery_config_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='配置表';
ALTER TABLE lottery.lottery_config ADD `type` TINYINT NULL COMMENT '1:年 2:月 3:周  4:类型';


CREATE TABLE `lottery_temp` (
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '期号',
  `date` varchar(50) NOT NULL DEFAULT '' COMMENT '开奖日期',
  `red` varchar(20) NOT NULL DEFAULT '' COMMENT '中奖号码：红',
  `blue` varchar(20) NOT NULL DEFAULT '' COMMENT '中奖号码：蓝',
  `sales` varchar(200) NOT NULL DEFAULT '' COMMENT '总销售额',
  `one_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '一等奖注数',
  `one_type_money` varchar(30) NOT NULL DEFAULT '' COMMENT '一等奖中奖金额',
  `two_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '二等奖注数',
  `two_type_money` varchar(30) NOT NULL DEFAULT '' COMMENT '二等奖中奖金额',
  `three_type_num` varchar(10) NOT NULL DEFAULT '' COMMENT '三等奖注数',
  `three_type_money` varchar(30) NOT NULL DEFAULT '' COMMENT '三等奖中奖金额',
  `poolmoney` varchar(50) NOT NULL DEFAULT '' COMMENT '奖金池',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lottery.lottery_strategy_record (
	id BIGINT auto_increment NOT NULL COMMENT '主键id',
	strategy_no varchar(100) NOT NULL COMMENT '策略编号-雪花',
	total INT DEFAULT 0 NOT NULL COMMENT '统计数据量',
	enable_contain TINYINT DEFAULT 0 NOT NULL COMMENT '是否命中',
	strategy varchar(200) NOT NULL COMMENT '策略',
	CONSTRAINT lottery_strategy_record_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='策略记录表';

ALTER TABLE lottery.lottery_strategy_record ADD code varchar(100) NOT NULL COMMENT '期号';

ALTER TABLE lottery.lottery_config ADD red_nine_turn09_rate DECIMAL(10,2) NULL COMMENT '九转09比例';
ALTER TABLE lottery.lottery_config ADD red_nine_turn17_rate DECIMAL(10,2) NULL COMMENT '九转17比例';
ALTER TABLE lottery.lottery_config ADD red_nine_turn33_rate DECIMAL(10,2) NULL COMMENT '九转33比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_rate decimal(10,2) DEFAULT 0 NULL COMMENT '红球比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_parity_rate decimal(10,2) DEFAULT 0 NULL COMMENT '奇偶比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_range_rate decimal(10,2) DEFAULT 0 NULL COMMENT '区间比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_sum_rate decimal(10,2) DEFAULT 0 NULL COMMENT '和值比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN consecutive_numbers_count_rate decimal(10,2) DEFAULT 0 NULL COMMENT '连号个数比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN max_consecutive_numbers_rate decimal(10,2) DEFAULT 0 NULL COMMENT '最大连号数比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_nine_turn09_rate decimal(10,2) DEFAULT 0 NULL COMMENT '九转09比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_nine_turn17_rate decimal(10,2) DEFAULT 0 NULL COMMENT '九转17比例';
ALTER TABLE lottery.lottery_config MODIFY COLUMN red_nine_turn33_rate decimal(10,2) DEFAULT 0 NULL COMMENT '九转33比例';

ALTER TABLE lottery.lottery_config ADD CONSTRAINT lottery_config_UN UNIQUE KEY (`type`);
