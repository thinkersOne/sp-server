-- 查询历史红球重复情况   未出现重复情况
SELECT red, COUNT(red) AS count,max(code),max(blue) FROM lottery GROUP BY red HAVING COUNT(red) > 1;

-- 红球奇偶比、分区、和值 规律
-- ------------- 按年
select year,red_parity_ratio_2_4,red_parity_ratio_4_2,red_parity_ratio_5_1,red_parity_ratio_1_5,
red_parity_ratio_3_3,red_parity_ratio_6_0,red_parity_ratio_0_6 
from lottery_calculate_count where cal_type = 1 order by `year` DESC;
-- -------------- 按年 总平均值
select AVG(red_parity_ratio_2_4) as red_parity_ratio_2_4,AVG(red_parity_ratio_4_2) as red_parity_ratio_4_2,
AVG(red_parity_ratio_5_1) as red_parity_ratio_5_1,AVG(red_parity_ratio_1_5) as red_parity_ratio_1_5,
AVG(red_parity_ratio_3_3) as red_parity_ratio_3_3,AVG(red_parity_ratio_6_0) as red_parity_ratio_6_0,
AVG(red_parity_ratio_0_6) as red_parity_ratio_0_6,AVG(red_range_2_3_1) as red_range_2_3_1,AVG(red_range_2_2_2) as red_range_2_2_2,
AVG(red_range_3_2_1) as red_range_3_2_1,AVG(red_range_1_2_3) as red_range_1_2_3,AVG(red_range_2_0_4) as red_range_2_0_4,
AVG(red_range_2_4_0) as red_range_2_4_0,AVG(red_range_4_0_2) as red_range_4_0_2,AVG(red_range_4_2_0) as red_range_4_2_0,
AVG(red_range_3_1_2) as red_range_3_1_2,AVG(red_range_2_1_3) as red_range_2_1_3,AVG(red_range_4_1_1) as red_range_4_1_1,
AVG(red_range_1_1_4) as red_range_1_1_4,AVG(red_range_1_4_1) as red_range_1_4_1,avg(red_21_60) as red_21_60,
AVG(red_73_78) as red_73_78,AVG(red_61_66) as red_61_66,AVG(red_103_108) as red_103_108,avg(red_91_96) as red_91_96,
AVG(red_79_84) as red_79_84,AVG(red_67_72) as red_67_72,AVG(red_109_114) as red_109_114,AVG(red_115_120) as red_115_120,
AVG(red_133_138) as red_133_138,AVG(red_97_102) as red_97_102,AVG(red_139_144) as red_139_144,
avg(red_127_132) as red_127_132,avg(red_121_126) as red_121_126,AVG(red_145_183) as red_145_183 
from lottery_calculate_count where cal_type = 1;
-- ------------- 按月
select year,month,red_parity_ratio_2_4,red_parity_ratio_4_2,red_parity_ratio_5_1,red_parity_ratio_1_5,
red_parity_ratio_3_3,red_parity_ratio_6_0,red_parity_ratio_0_6 from lottery_calculate_count 
where cal_type = 2 order by `year` DESC,month desc;
select year,AVG(red_parity_ratio_2_4) as red_parity_ratio_2_4,AVG(red_parity_ratio_4_2) as red_parity_ratio_4_2,
AVG(red_parity_ratio_5_1) as red_parity_ratio_5_1,AVG(red_parity_ratio_1_5) as red_parity_ratio_1_5,
AVG(red_parity_ratio_3_3) as red_parity_ratio_3_3,AVG(red_parity_ratio_6_0) as red_parity_ratio_6_0,
AVG(red_parity_ratio_0_6) as red_parity_ratio_0_6,AVG(red_range_2_3_1) as red_range_2_3_1,AVG(red_range_2_2_2) as red_range_2_2_2,
AVG(red_range_3_2_1) as red_range_3_2_1,AVG(red_range_1_2_3) as red_range_1_2_3,AVG(red_range_2_0_4) as red_range_2_0_4,
AVG(red_range_2_4_0) as red_range_2_4_0,AVG(red_range_4_0_2) as red_range_4_0_2,AVG(red_range_4_2_0) as red_range_4_2_0,
AVG(red_range_3_1_2) as red_range_3_1_2,AVG(red_range_2_1_3) as red_range_2_1_3,AVG(red_range_4_1_1) as red_range_4_1_1,
AVG(red_range_1_1_4) as red_range_1_1_4,AVG(red_range_1_4_1) as red_range_1_4_1,avg(red_21_60) as red_21_60,
AVG(red_73_78) as red_73_78,AVG(red_61_66) as red_61_66,AVG(red_103_108) as red_103_108,avg(red_91_96) as red_91_96,
AVG(red_79_84) as red_79_84,AVG(red_67_72) as red_67_72,AVG(red_109_114) as red_109_114,AVG(red_115_120) as red_115_120,
AVG(red_133_138) as red_133_138,AVG(red_97_102) as red_97_102,AVG(red_139_144) as red_139_144,
avg(red_127_132) as red_127_132,avg(red_121_126) as red_121_126,AVG(red_145_183) as red_145_183 
from lottery_calculate_count where cal_type = 2 group by year order by `year` DESC;
-- ------------- 按期号
select codes,red_parity_ratio_2_4,red_parity_ratio_4_2,red_parity_ratio_5_1,red_parity_ratio_1_5,
red_parity_ratio_3_3,red_parity_ratio_6_0,red_parity_ratio_0_6 from lottery_calculate_count 
where cal_type = 4 order by codes desc;



-- 根据年份查询
select * from lottery_calculate_count where cal_type = 2 order by year desc,month desc,week desc,codes desc;
-- 根据月份查询
select * from lottery_calculate_count where cal_type = 1 order by year desc,month desc,week desc,codes desc;
-- 根据周查询
select * from lottery_calculate_count where cal_type = 3 order by year desc,month desc,week desc,codes desc;
-- 根据编号查询
select * from lottery_calculate_count where cal_type = 4 order by year desc,month desc,week desc,codes desc;

select DISTINCT nine_turn_09  from lottery_calculate_per;
select DISTINCT nine_turn_09  from lottery_calculate_per where nine_turn_09 like '1_3_2_0%';

select DISTINCT nine_turn_17  from lottery_calculate_per;
select DISTINCT nine_turn_33  from lottery_calculate_per;
-- 根据九转莲花图查询
select year,month,count(nine_turn_09),nine_turn_09  from lottery_calculate_per 
group by year,month,nine_turn_09 order by year desc,month desc;
	-- 九转规律
select count(*) as count,nine,09 as type,1 as location_type from (
select SUBSTR(nine_turn_09,1,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,09 as type,2 as location_type from (
select SUBSTR(nine_turn_09,3,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,09 as type,3 as location_type from (
select SUBSTR(nine_turn_09,5,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,09 as type,4 as location_type from (
select SUBSTR(nine_turn_09,7,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,17 as type,1 as location_type from (
select SUBSTR(nine_turn_17,1,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,17 as type,2 as location_type from (
select SUBSTR(nine_turn_17,3,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,17 as type,3 as location_type from (
select SUBSTR(nine_turn_17,5,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,17 as type,4 as location_type from (
select SUBSTR(nine_turn_17,7,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,33 as type,1 as location_type from (
select SUBSTR(nine_turn_33,1,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,33 as type,2 as location_type from (
select SUBSTR(nine_turn_33,3,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,33 as type,3 as location_type from (
select SUBSTR(nine_turn_33,5,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine
union
select count(*) as count,nine,33 as type,4 as location_type from (
select SUBSTR(nine_turn_33,7,1) as nine,code from lottery_calculate_per order by code DESC 
) a group by nine;

-- 根据九转莲花图查询
select year,month,count(nine_turn_17),nine_turn_17  from lottery_calculate_per 
group by year,month,nine_turn_17 order by year desc,month desc;
-- 根据九转莲花图查询
select year,month,count(nine_turn_33),nine_turn_33  from lottery_calculate_per 
group by year,month,nine_turn_33 order by year desc,month desc;


-- 计算总数
select (7*13*15);-- 1365    195
	-- 2:4、2:3:1、73~78
73 <= a + b + c + d + e + f <= 78

SET GLOBAL max_allowed_packet=87108864;
select * from lottery_calculate_per;
select count(*) from lottery_all;-- 1107568
select count(*) from lottery; -- 1625
-- 查询最大连号数情况
select max(max_consecutive_numbers) as max_consecutive_numbers_max,min(max_consecutive_numbers) as max_consecutive_numbers_min,
MAX(consecutive_numbers_count) as consecutive_numbers_count_max,min(consecutive_numbers_count) as consecutive_numbers_count_min,
max(red_sum) as red_sum_max,min(red_sum) as red_sum_min
from lottery_calculate_per;
select * from lottery_calculate_per where max_consecutive_numbers = 5;
select * from lottery_calculate_per where max_consecutive_numbers = 4;
select max(red_sum),min(red_sum) from lottery_calculate_per;-- 29,172
select * from lottery_calculate_per where red_sum = 29 or red_sum = 172;
select * from lottery_calculate_per order by red_sum desc;

select count(*) from lottery_select; -- 1104284
select * from lottery_select where red  = '03,08,13,24,27,29';

select * from lottery_calculate_nine where cal_type = 1;

select nine_turn_type,max(nine_turn_max) as nine_turn_max,min(nine_turn_min) as nine_turn_min 
from lottery_calculate_nine group by nine_turn_type;

select * from lottery_select where red  = '02,12,16,25,30,31';

select avg(red1),AVG(red2),AVG(red3),AVG(red4),AVG(red5),AVG(red6),AVG(red7),AVG(red8),AVG(red9),AVG(red10),  
avg(red11),AVG(red12),AVG(red13),AVG(red14),AVG(red15),AVG(red16),AVG(red17),AVG(red18),AVG(red19),AVG(red20),
avg(red21),AVG(red22),AVG(red23),AVG(red24),AVG(red25),AVG(red26),AVG(red27),AVG(red28),AVG(red29),AVG(red30),
avg(red31),AVG(red32),AVG(red33)
from (select year,red1,red2,red3,red4,red5,red6,red7,red8,red9,red10,  
red11,red12,red13,red14,red15,red16,red17,red18,red19,red20,
red21,red22,red23,red24,red25,red26,red27,red28,red29,red30,
red31,red32,red33
from lottery_calculate_count where cal_type = 1) a;

select * from lottery_calculate_per order by code  DESC limit 1;

-- 查询未在全量中的期号
select * from lottery where code not in (
select code from lottery_select
);-- 0
select count(*) from lottery_all;-- 4430272
select count(*) from lottery_select;-- 3312789 -> 4415040/4417052

select DISTINCT nine,type from lottery_calculate_nine_count where type = 9;

select min(nine) as `min`,max(nine) as `max`,type,location_type from lottery_calculate_nine_count
group by type,location_type;

select nine_turn_type,max(nine_turn_max) as nine_turn_max,min(nine_turn_min) as nine_turn_min
		from lottery_calculate_nine group by nine_turn_type;


select DISTINCT before_code_count from lottery_calculate_per;
select * from lottery_calculate_per where before_code_count = 5;
select DISTINCT before_common_code_count from lottery_calculate_per;	

select before_code_count,count(*) from lottery_calculate_per group by before_code_count;
select before_common_code_count,count(*) from lottery_calculate_per group by before_common_code_count;

# 统计
select * from lottery_calculate_count where cal_type = 2;

select avg(red1) red1,avg(red2) red2,avg(red3) red3,avg(red4) red4,avg(red5) red5,avg(red6) red6,avg(red7) red7,avg(red8) red8 
,avg(red9) red9,avg(red10) red10,avg(red11) red11,avg(red12) red12,avg(red13) red13,avg(red14) red14,avg(red15) red15,avg(red16) red16
,avg(red17) red17,avg(red18) red18,avg(red19) red19,avg(red20) red20,avg(red21) red21,avg(red22) red22,avg(red23) red23,avg(red24) red24
,avg(red25) red25,avg(red26) red26,avg(red27) red27,avg(red28) red28,avg(red29) red29,avg(red30) red30,avg(red31) red31,avg(red32) red32
,avg(red33) red33
from (select * from lottery_red_proportion
where code <= '2023140') a;

-- 按照年统计 出现次数超过35的：26
-- 上一个月出现了 6次及以上：22

-- 按照年统计 0:6出现了3次，2:2:2出现了31次，红 1:1:4出现了9次，97_102出现了21次 连号数2 出现了76次
-- 按照月统计 近期 3:3出现的频率很高，均在4次及以上 3:1:2近期出现了频率也很高
-- 按照周统计 3:3近一周出现了2次
-- 按照期号统计 3、5出现次数较高，连号个数0出现次数为7

select year,nine_turn_type,nine_turn,max(nine_turn_count) 
from lottery_calculate_nine 
where cal_type = 1
group by year,nine_turn_type,nine_turn order by year desc
;





















