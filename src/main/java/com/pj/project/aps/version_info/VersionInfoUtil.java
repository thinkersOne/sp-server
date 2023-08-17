package com.pj.project.aps.version_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pj.utils.sg.*;

/**
 * 工具类：version_info -- 版本更新表
 * @author lizhihao
 *
 */
@Component
public class VersionInfoUtil {


    /** 底层 Mapper 对象 */
    public static VersionInfoMapper versionInfoMapper;
    @Autowired
    private void setVersionInfoMapper(VersionInfoMapper versionInfoMapper) {
        VersionInfoUtil.versionInfoMapper = versionInfoMapper;
    }


    /**
     * 将一个 VersionInfo 对象进行进行数据完整性校验 (方便add/update等接口数据校验) [G]
     */
    static void check(VersionInfo v) {
        AjaxError.throwByIsNull(v.id, "[主键id] 不能为空");		// 验证: 主键id
        AjaxError.throwByIsNull(v.version, "[当前版本号] 不能为空");		// 验证: 当前版本号
        AjaxError.throwByIsNull(v.url, "[安装程序下载地址] 不能为空");		// 验证: 安装程序下载地址
        AjaxError.throwByIsNull(v.description, "[版本更新内容说明] 不能为空");		// 验证: 版本更新内容说明
        AjaxError.throwByIsNull(v.createBy, "[创建人] 不能为空");		// 验证: 创建人
        AjaxError.throwByIsNull(v.createTime, "[创建时间] 不能为空");		// 验证: 创建时间
        AjaxError.throwByIsNull(v.updateBy, "[更新人] 不能为空");		// 验证: 更新人
        AjaxError.throwByIsNull(v.updateTime, "[更新时间] 不能为空");		// 验证: 更新时间
    }

    /**
     * 获取一个VersionInfo (方便复制代码用) [G]
     */
    static VersionInfo getVersionInfo() {
        VersionInfo v = new VersionInfo();	// 声明对象
        v.id = 0L;		// 主键id
        v.version = "";		// 当前版本号
        v.url = "";		// 安装程序下载地址
        v.description = "";		// 版本更新内容说明
        v.createBy = "";		// 创建人
        v.createTime = "";		// 创建时间
        v.updateBy = "";		// 更新人
        v.updateTime = "";		// 更新时间
        return v;
    }

}