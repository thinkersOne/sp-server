package com.pj.project.aps.chart_message;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.current.global.AiChartConstant;
import com.pj.models.so.SoMap;
import com.pj.project.aps.user.User;
import com.pj.project.aps.user.UserService;
import com.pj.utils.Ttime;
import com.pj.utils.ai.baidu.BaiduChatMessage;
import com.pj.utils.ai.baidu.ErnieBotTurboResponse;
import com.pj.utils.ai.baidu.ErnieBotTurboStreamParam;
import com.pj.utils.ai.baidu.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service: chart_message -- AI聊天对话记录信息表
 * @author lizhihao
 */
@Service
public class ChartMessageService {

    /** 底层 Mapper 对象 */
    @Autowired
    ChartMessageMapper chartMessageMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserService userService;
    /** 增 */
    @Transactional(rollbackFor = Exception.class)
    ChartMessage add(ChartMessage c){
        List<BaiduChatMessage> chartMessageList = null;
        try{
            //用户 sender
            c.setUserId(StpUtil.getLoginIdAsLong());
            if(!StringUtils.hasText(c.getSender())){
                User u = userService.getById(StpUtil.getLoginIdAsLong());
                c.setSender(u.getUsername());
            }
            c.setRole(AiChartConstant.BD_ROLE_0);
            chartMessageMapper.add(c);

            // Ai 回复 模拟 测试
            String token = AiChartConstant.tokenMap.get(StpUtil.getLoginIdAsLong());
            if(StringUtils.isEmpty(token)){
                bd_chart_token();
            }
            // 查询历史对话内容 当天内容
            chartMessageList = getChartMessageList();
            String result = bd_chart(chartMessageList);
            if(StringUtils.isEmpty(result)){
                throw new RuntimeException("回复异常!");
            }
            ChartMessage chartMessage = getChartMessage(c, result);
            return chartMessage;
        }catch (Exception e){
            if(chartMessageList != null && AiChartConstant.BD_ROLE_0.equals(chartMessageList.get(chartMessageList.size() - 1).getRole())){
                delete(chartMessageList.get(chartMessageList.size() - 1).getId());
            }
            throw new RuntimeException("新增失败！");
        }
    }

    private ChartMessage getChartMessage(ChartMessage c, String result) {
        ChartMessage chartMessage = new ChartMessage();
        chartMessage.setSender(AiChartConstant.BD_ROLE_1);
        chartMessage.setRole(AiChartConstant.BD_ROLE_1);
        chartMessage.setContent(result);
        chartMessage.setUserId(StpUtil.getLoginIdAsLong());
        chartMessage.setCreateBy(c.getCreateBy());
        chartMessage.setUpdateBy(c.getUpdateBy());
        chartMessage.setCreateTime(c.getCreateTime());
        chartMessage.setUpdateTime(c.getUpdateTime());
        chartMessageMapper.add(chartMessage);
        return chartMessage;
    }

    /** 删 */
    int delete(Long id){
        return chartMessageMapper.deleteById(id);
    }

    public int deleteByIds(List<Long> ids){
        return chartMessageMapper.deleteByIds(ChartMessage.TABLE_NAME,ids);
    }

    /** 改 */
    int update(ChartMessage c){
        return chartMessageMapper.update(c);
    }

    /** 查 */
    ChartMessage getById(Long id){
        return chartMessageMapper.getById(id);
    }

    /** 查集合 - 根据条件（参数为空时代表忽略指定条件） */
    public List<ChartMessage> getList(SoMap so) {
        return chartMessageMapper.getList(so);
    }

    public String bd_chart_token(){
        String url = AiChartConstant.BD_URL_ACCESS_TOKEN + "?client_id=" + AiChartConstant.BD_APP_KEY +
                "&client_secret=" + AiChartConstant.BD_SECRET_KEY + "&grant_type=client_credentials";
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(url, Token.class);
        String accessToken = forEntity.getBody().getAccess_token();
        AiChartConstant.tokenMap.put(StpUtil.getLoginIdAsLong(),accessToken);
        return accessToken;
    }

    public String bd_chart(List<BaiduChatMessage> baiduChatMessageList){
        ErnieBotTurboStreamParam param = ErnieBotTurboStreamParam.builder()
                .user_id(StpUtil.getLoginIdAsLong() + "").messages(baiduChatMessageList).build();
        String token = AiChartConstant.tokenMap.get(StpUtil.getLoginIdAsLong());
        String url = AiChartConstant.BD_URL_CHART + "?access_token=" + token;
        ErnieBotTurboResponse ernieBotTurboResponse = restTemplate.postForObject(url,param, ErnieBotTurboResponse.class);
        return ernieBotTurboResponse.getResult();
    }

    public List<BaiduChatMessage> getChartMessageList(){
        String startOfDay = Ttime.getStartOfDay();
        SoMap soMap = new SoMap();
        soMap.set("startTime",startOfDay);
        List<ChartMessage> list = getList(soMap);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        List<BaiduChatMessage> baiduChatMessages = list.stream().map(v -> BaiduChatMessage.builder()
                .id(v.getId())
                .role(v.getRole())
                .content(v.getContent()).build()).collect(Collectors.toList());
        return baiduChatMessages;
    }

}