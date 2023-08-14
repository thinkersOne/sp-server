package com.pj.project.aav.sp_image;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper: sp_image --
 * @author lizhihao
 */

@Mapper
@Repository
public interface SpImageMapper {

    /**
     * 增
     * @param s 实体对象
     * @return 受影响行数
     */
    int add(SpImage s);

    /**
     * 删
     * @param id 要删除的数据id
     * @return 受影响行数
     */
    int delete(Long id);

    /**
     * 改
     * @param s 实体对象
     * @return 受影响行数
     */
    int update(SpImage s);

    /**
     * 查 - 根据id
     * @param id 要查询的数据id
     * @return 实体对象
     */
    SpImage getById(Long id);

    List<String> getImgUrl(int type);

}
