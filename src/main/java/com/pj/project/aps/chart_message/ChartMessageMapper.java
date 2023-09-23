package com.pj.project.aps.chart_message;

import com.pj.models.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: chart_message -- AI聊天对话记录信息表
 * @author lizhihao
 */

@Mapper
@Repository
public interface ChartMessageMapper {

    /**
     * 增
     * @param c 实体对象
     * @return 受影响行数
     */
    int add(ChartMessage c);

    /**
     * 删
     * @param id 要删除的数据id
     * @return 受影响行数
     */
    int deleteById(Long id);

    int deleteByIds(@Param("tableName")String tableName,
                    @Param("ids")List<?> ids);
    /**
     * 改
     * @param c 实体对象
     * @return 受影响行数
     */
    int update(ChartMessage c);

    /**
     * 查 - 根据id
     * @param id 要查询的数据id
     * @return 实体对象
     */
    ChartMessage getById(Long id);

    /**
     * 查集合 - 根据条件（参数为空时代表忽略指定条件）
     * @param so 参数集合
     * @return 数据列表
     */
    List<ChartMessage> getList(SoMap so);

    List<ChartMessage> getListAll();
}