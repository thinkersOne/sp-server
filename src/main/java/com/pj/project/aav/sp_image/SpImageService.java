package com.pj.project.aav.sp_image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: sp_image --
 * @author lizhihao
 */
@Service
public class SpImageService {

    /** 底层 Mapper 对象 */
    @Autowired
    SpImageMapper spImageMapper;

    /** 增 */
    int add(SpImage s){
        return spImageMapper.add(s);
    }

    /** 删 */
    int delete(Long id){
        return spImageMapper.delete(id);
    }

    /** 改 */
    int update(SpImage s){
        return spImageMapper.update(s);
    }

    /** 查 */
    SpImage getById(Long id){
        return spImageMapper.getById(id);
    }


}