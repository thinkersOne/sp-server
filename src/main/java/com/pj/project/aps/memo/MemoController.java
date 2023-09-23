package com.pj.project.aps.memo;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.pj.models.so.SoMap;
import com.pj.utils.sg.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller: memo -- 备忘录表
 * @author lizhihao
 */
@RestController
@RequestMapping("/Memo/")
public class MemoController {

    /** 底层 Service 对象 */
    @Autowired
    MemoService memoService;
    @Autowired
    MemoMapper memoMapper;

    /** 增 */
    @PostMapping("add")
    @SaCheckLogin
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson add(@RequestBody Memo m){
        memoService.add(m);
        return AjaxJson.getSuccessData(m);
    }

    /** 删 - 根据id列表 */
    @RequestMapping("deleteByIds")
    @SaCheckLogin
    public AjaxJson deleteByIds(@RequestBody List<Long> ids){
        int line = memoMapper.deleteByIds(Memo.TABLE_NAME, ids);
        return AjaxJson.getByLine(line);
    }

    /** 改 */
    @RequestMapping("update")
    @SaCheckLogin
    public AjaxJson update(Memo m){
        int line = memoService.update(m);
        return AjaxJson.getByLine(line);
    }

    /** 查 - 根据id */
    @RequestMapping("getById")
    @SaCheckLogin
    public AjaxJson getById(Long id){
        Memo m = memoService.getById(id);
        return AjaxJson.getSuccessData(m);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    @GetMapping("getList")
    @SaCheckLogin
    public AjaxJson getList() {
        SoMap so = SoMap.getRequestSoMap();
        so.set("userId", StpUtil.getLoginIdAsLong());
        List<Memo> list = memoService.getList(so.startPage());
        return AjaxJson.getPageData(so.getDataCount(), list);
    }

    @GetMapping("getTodayList")
    @SaCheckLogin
    public AjaxJson getTodayList() {
        SoMap so = SoMap.getRequestSoMap();
        so.set("userId", StpUtil.getLoginIdAsLong());
        List<Memo> list = memoService.getTodayList(so);
        return AjaxJson.getPageData(so.getDataCount(), list);
    }

}