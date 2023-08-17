package com.pj.project.aps.version_info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service: version_info -- 版本更新表
 * @author lizhihao
 */
@Service
public class VersionInfoService {

    /** 底层 Mapper 对象 */
    @Autowired
    VersionInfoMapper versionInfoMapper;

    /** 增 */
    int add(VersionInfo v){
        return versionInfoMapper.add(v);
    }

    /** 删 */
    int delete(Long id){
        return versionInfoMapper.delete(id);
    }

    /** 改 */
    int update(VersionInfo v){
        return versionInfoMapper.update(v);
    }

    /** 查 */
    VersionInfo getById(Long id){
        return versionInfoMapper.getById(id);
    }

    VersionInfo getCurrentVersion(){
        return versionInfoMapper.getCurrentVersion();
    }

}