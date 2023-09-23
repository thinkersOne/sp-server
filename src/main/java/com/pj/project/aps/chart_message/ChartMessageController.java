package com.pj.project.aps.chart_message;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.pj.current.global.AiChartConstant;
import com.pj.models.so.SoMap;
import com.pj.utils.ai.baidu.BaiduChatMessage;
import com.pj.utils.sg.AjaxJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Controller: chart_message -- AI聊天对话记录信息表
 * @author lizhihao
 */
@RestController
@RequestMapping("/ChartMessage/")
public class ChartMessageController {

    /** 底层 Service 对象 */
    @Autowired
    ChartMessageService chartMessageService;
    /** 增 */
    @PostMapping("add")
    @SaCheckLogin
    @Transactional(rollbackFor = Exception.class)
    public AjaxJson add(@RequestBody ChartMessage c){
        ChartMessage chartMessage = chartMessageService.add(c);
        return AjaxJson.getSuccessData(chartMessage);
    }

    /** 删 */
    @RequestMapping("delete")
    @SaCheckPermission(ChartMessage.PERMISSION_CODE)
    public AjaxJson delete(Long id){
        int line = chartMessageService.delete(id);
        return AjaxJson.getByLine(line);
    }

    /** 删 - 根据id列表 */
    @PostMapping("deleteByIds")
    @SaCheckLogin
    public AjaxJson deleteByIds(@RequestBody List<Long> ids){
        int line = chartMessageService.deleteByIds(ids);
        return AjaxJson.getByLine(line);
    }

    /** 改 */
    @RequestMapping("update")
    @SaCheckPermission(ChartMessage.PERMISSION_CODE)
    public AjaxJson update(ChartMessage c){
        int line = chartMessageService.update(c);
        return AjaxJson.getByLine(line);
    }

    /** 查 - 根据id */
    @RequestMapping("getById")
    public AjaxJson getById(Long id){
        ChartMessage c = chartMessageService.getById(id);
        return AjaxJson.getSuccessData(c);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    @GetMapping("getList")
    @SaCheckLogin
    public AjaxJson getList() {
        SoMap so = SoMap.getRequestSoMap();
        so.set("userId",StpUtil.getLoginIdAsLong());
        List<ChartMessage> list = chartMessageService.getList(so);
        return AjaxJson.getPageData(so.getDataCount(), list);
    }

    @GetMapping("bd/getAccess_token")
    @SaCheckLogin
    public AjaxJson getAccess_token() {
        return AjaxJson.getSuccessData(chartMessageService.bd_chart_token());
    }

    @GetMapping("bd/getResult")
    @SaCheckLogin
    public AjaxJson getResult() {
        List< BaiduChatMessage > baiduChatMessageList = new ArrayList<>(10);
        BaiduChatMessage baiduChatMessage = BaiduChatMessage.builder().role(AiChartConstant.BD_ROLE_0)
                .content("周末这里的天气怎么样？").build();
        BaiduChatMessage baiduChatMessage1 = BaiduChatMessage.builder().role(AiChartConstant.BD_ROLE_1)
                .content("抱歉，作为一个人工智能语言模型，我无法获得实时天气信息。您可以通过当地的天气预报网站或应用程序获取具体的天气预报信息。").build();
        BaiduChatMessage baiduChatMessage2 = BaiduChatMessage.builder().role(AiChartConstant.BD_ROLE_0)
                .content("今天天气怎么样呢？").build();
        baiduChatMessageList.add(baiduChatMessage);
        baiduChatMessageList.add(baiduChatMessage1);
        baiduChatMessageList.add(baiduChatMessage2);
        AiChartConstant.tokenMap.put(StpUtil.getLoginIdAsLong(),"24.b83bded9c570d34a62d170563a2d3075.2592000.1697769687.282335-39658647");
        return AjaxJson.getSuccessData(chartMessageService.bd_chart(baiduChatMessageList));
    }

    @GetMapping("testGetChartMessageList")
    @SaCheckLogin
    public AjaxJson testGetChartMessageList() {
        return AjaxJson.getSuccessData(chartMessageService.getChartMessageList());
    }

}