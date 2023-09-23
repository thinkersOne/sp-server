package com.pj.project.aps.memo;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.models.so.SoMap;
import com.pj.utils.Ttime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service: memo -- 备忘录表
 * @author lizhihao
 */
@Service
public class MemoService {

    /** 底层 Mapper 对象 */
    @Autowired
    MemoMapper memoMapper;

    /** 增 */
    int add(Memo m){
        m.setUserId(StpUtil.getLoginIdAsLong());
        return memoMapper.add(m);
    }

    /** 删 */
    int delete(Long id){
        return memoMapper.delete(id);
    }

    /** 改 */
    int update(Memo m){
        return memoMapper.update(m);
    }

    /** 查 */
    Memo getById(Long id){
        return memoMapper.getById(id);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    public List<Memo> getList(SoMap so) {
        return memoMapper.getList(so);
    }

    public List<Memo> getTodayList(SoMap so) {
        String startOfDay = Ttime.getStartOfDay();
        so.set("startTime",startOfDay);
        return memoMapper.getList(so);
    }


}