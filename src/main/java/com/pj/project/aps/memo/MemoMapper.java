package com.pj.project.aps.memo;

import com.pj.models.so.SoMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: memo -- 备忘录表
 * @author lizhihao
 */

@Mapper
@Repository
public interface MemoMapper {

    /**
     * 增
     * @param m 实体对象
     * @return 受影响行数
     */
    int add(Memo m);

    /**
     * 删
     * @param id 要删除的数据id
     * @return 受影响行数
     */
    int delete(Long id);

    /**
     * 改
     * @param m 实体对象
     * @return 受影响行数
     */
    int update(Memo m);

    /**
     * 查 - 根据id
     * @param id 要查询的数据id
     * @return 实体对象
     */
    Memo getById(Long id);
    public int deleteByIds(
            @Param("tableName")String tableName,
            @Param("ids")List<?> ids
    );
    /**
     * 查集合 - 根据条件（参数为空时代表忽略指定条件）
     * @param so 参数集合
     * @return 数据列表
     */
    List<Memo> getList(SoMap so);


}