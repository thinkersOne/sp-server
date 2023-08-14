package com.pj.project.aav.sp_image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：sp_image --
 * @author lizhihao
 *
 */
@Component
public class SpImageUtil {


    /** 底层 Mapper 对象 */
    public static SpImageMapper spImageMapper;
    @Autowired
    private void setSpImageMapper(SpImageMapper spImageMapper) {
        SpImageUtil.spImageMapper = spImageMapper;
    }


    /**
     * 将一个 SpImage 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G]
     */
    static void check(SpImage s) {
        AjaxError.throwByIsNull(s.id, "[主键id] 不能为空");		// 验证: 主键id
        AjaxError.throwByIsNull(s.url, "[图片完整路径地址] 不能为空");		// 验证: 图片完整路径地址
        AjaxError.throwByIsNull(s.type, "[1：注册登录2：轮播图] 不能为空");		// 验证: 1：注册登录  2：轮播图
        AjaxError.throwByIsNull(s.createTime, "[创建时间] 不能为空");		// 验证: 创建时间
        AjaxError.throwByIsNull(s.createBy, "[创建人] 不能为空");		// 验证: 创建人
        AjaxError.throwByIsNull(s.updateBy, "[更新人] 不能为空");		// 验证: 更新人
        AjaxError.throwByIsNull(s.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间
    }

    /**
     * 获取一个SpImage (方便复制代码用) [G]
     */
    static SpImage getSpImage() {
        SpImage s = new SpImage();	// 声明对象
        s.id = 0L;		// 主键id
        s.url = "";		// 图片完整路径地址
        s.type = 0;		// 1：注册登录  2：轮播图
        s.createTime = "";		// 创建时间
        s.createBy = "";		// 创建人
        s.updateBy = "";		// 更新人
        s.updateTime = "";		// 更新时间
        return s;
    }

}