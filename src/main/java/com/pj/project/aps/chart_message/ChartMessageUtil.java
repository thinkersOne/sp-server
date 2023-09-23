package com.pj.project.aps.chart_message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：chart_message -- AI聊天对话记录信息表
 * @author lizhihao
 *
 */
@Component
public class ChartMessageUtil {


    /** 底层 Mapper 对象 */
    public static ChartMessageMapper chartMessageMapper;
    @Autowired
    private void setChartMessageMapper(ChartMessageMapper chartMessageMapper) {
        ChartMessageUtil.chartMessageMapper = chartMessageMapper;
    }


    /**
     * 将一个 ChartMessage 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G]
     */
    static void check(ChartMessage c) {
        AjaxError.throwByIsNull(c.id, "[主键id] 不能为空");		// 验证: 主键id
        AjaxError.throwByIsNull(c.userId, "[用户id] 不能为空");		// 验证: 用户id
        AjaxError.throwByIsNull(c.content, "[回复信息] 不能为空");		// 验证: 回复信息
        AjaxError.throwByIsNull(c.sender, "[发送人] 不能为空");		// 验证: 发送人
        AjaxError.throwByIsNull(c.createBy, "[创建人] 不能为空");		// 验证: 创建人
        AjaxError.throwByIsNull(c.createTime, "[创建时间] 不能为空");		// 验证: 创建时间
        AjaxError.throwByIsNull(c.updateBy, "[更新人] 不能为空");		// 验证: 更新人
        AjaxError.throwByIsNull(c.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间
    }

    /**
     * 获取一个ChartMessage (方便复制代码用) [G]
     */
    static ChartMessage getChartMessage() {
        ChartMessage c = new ChartMessage();	// 声明对象
        c.id = 0L;		// 主键id
        c.userId = 0L;		// 用户id
        c.content = "";		// 回复信息
        c.sender = "";		// 发送人
        c.createBy = "";		// 创建人
        c.createTime = "";		// 创建时间
        c.updateBy = "";		// 更新人
        c.updateTime = "";		// 更新时间
        return c;
    }

}