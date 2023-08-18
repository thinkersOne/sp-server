package com.pj.project.aps.version_info;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.pj.utils.sg.*;
import com.pj.current.satoken.StpUserUtil;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: version_info -- 版本更新表
 * @author lizhihao
 */
@RestController
@RequestMapping("/VersionInfo/")
public class VersionInfoController {

    /** 底层 Service 对象 */
    @Autowired
    VersionInfoService versionInfoService;

    /** 增 */
    @PostMapping("add")
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson add(@RequestBody VersionInfo v){
        versionInfoService.add(v);
        return AjaxJson.getSuccessData(v);
    }

    /** 删 */
    @RequestMapping("delete")
    @SaCheckPermission(VersionInfo.PERMISSION_CODE)
    public AjaxJson delete(Long id){
        int line = versionInfoService.delete(id);
        return AjaxJson.getByLine(line);
    }

    /** 改 */
    @RequestMapping("update")
    @SaCheckPermission(VersionInfo.PERMISSION_CODE)
    public AjaxJson update(VersionInfo v){
        int line = versionInfoService.update(v);
        return AjaxJson.getByLine(line);
    }

    /** 查 - 根据id */
    @RequestMapping("getById")
    public AjaxJson getById(Long id){
        VersionInfo v = versionInfoService.getById(id);
        return AjaxJson.getSuccessData(v);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    @RequestMapping("getList")
    public AjaxJson getList() {
        return AjaxJson.getSuccess();
    }

    @GetMapping("getCurrentVersion")
    public AjaxJson getCurrentVersion(){
        return AjaxJson.getSuccessData(versionInfoService.getCurrentVersion());
    }

}