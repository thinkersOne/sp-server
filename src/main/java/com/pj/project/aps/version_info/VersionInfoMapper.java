package com.pj.project.aps.version_info;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Mapper: version_info -- 版本更新表
 * @author lizhihao
 */

@Mapper
@Repository
public interface VersionInfoMapper {

    /**
     * 增
     * @param v 实体对象
     * @return 受影响行数
     */
    int add(VersionInfo v);

    /**
     * 删
     * @param id 要删除的数据id
     * @return 受影响行数
     */
    int delete(Long id);

    /**
     * 改
     * @param v 实体对象
     * @return 受影响行数
     */
    int update(VersionInfo v);

    /**
     * 查 - 根据id
     * @param id 要查询的数据id
     * @return 实体对象
     */
    VersionInfo getById(Long id);

    VersionInfo getCurrentVersion();

}