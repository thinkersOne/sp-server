package com.pj.project.aps.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：memo -- 备忘录表
 * @author lizhihao
 *
 */
@Component
public class MemoUtil {


    /** 底层 Mapper 对象 */
    public static MemoMapper memoMapper;
    @Autowired
    private void setMemoMapper(MemoMapper memoMapper) {
        MemoUtil.memoMapper = memoMapper;
    }


    /**
     * 将一个 Memo 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G]
     */
    static void check(Memo m) {
        AjaxError.throwByIsNull(m.id, "[主键id] 不能为空");		// 验证: 主键id
        AjaxError.throwByIsNull(m.content, "[每项任务/备忘录] 不能为空");		// 验证: 每项任务/备忘录
        AjaxError.throwByIsNull(m.userId, "[用户id] 不能为空");		// 验证: 用户id
        AjaxError.throwByIsNull(m.createBy, "[创建人] 不能为空");		// 验证: 创建人
        AjaxError.throwByIsNull(m.createTime, "[创建时间] 不能为空");		// 验证: 创建时间
        AjaxError.throwByIsNull(m.updateBy, "[更新人] 不能为空");		// 验证: 更新人
        AjaxError.throwByIsNull(m.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间
    }

    /**
     * 获取一个Memo (方便复制代码用) [G]
     */
    static Memo getMemo() {
        Memo m = new Memo();	// 声明对象
        m.id = 0L;		// 主键id
        m.content = "";		// 每项任务/备忘录
        m.userId = 0L;		// 用户id
        m.createBy = "";		// 创建人
        m.createTime = "";		// 创建时间
        m.updateBy = "";		// 更新人
        m.updateTime = "";		// 更新时间
        return m;
    }





}