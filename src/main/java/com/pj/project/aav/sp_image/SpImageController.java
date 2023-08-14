package com.pj.project.aav.sp_image;

import cn.dev33.satoken.annotation.SaCheckLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.pj.utils.sg.*;
import cn.dev33.satoken.annotation.SaCheckPermission;


/**
 * Controller: sp_image --
 * @author lizhihao
 */
@RestController
@RequestMapping("/SpImage/")
public class SpImageController {

    /** 底层 Service 对象 */
    @Autowired
    SpImageService spImageService;
    @Autowired
    SpImageMapper spImageMapper;

    /** 增 */
    @PostMapping("add")
    @SaCheckLogin
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson add(@RequestBody SpImage s){
        Long id = Long.valueOf(spImageService.add(s)) ;
        s = spImageService.getById(id);
        return AjaxJson.getSuccessData(s);
    }

    /** 删 */
    @RequestMapping("delete")
    @SaCheckPermission(SpImage.PERMISSION_CODE)
    public AjaxJson delete(Long id){
        int line = spImageService.delete(id);
        return AjaxJson.getByLine(line);
    }

    /** 改 */
    @RequestMapping("update")
    @SaCheckPermission(SpImage.PERMISSION_CODE)
    public AjaxJson update(SpImage s){
        int line = spImageService.update(s);
        return AjaxJson.getByLine(line);
    }

    /** 查 - 根据id */
    @RequestMapping("getById")
    public AjaxJson getById(Long id){
        SpImage s = spImageService.getById(id);
        return AjaxJson.getSuccessData(s);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    @GetMapping("getList")
    @SaCheckLogin
    public AjaxJson getList(@Param("type") int type) {
        return AjaxJson.getSuccessData(spImageMapper.getImgUrl(type));
    }


}
