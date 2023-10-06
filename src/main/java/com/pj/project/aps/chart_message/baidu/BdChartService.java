package com.pj.project.aps.chart_message.baidu;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.current.enums.PlatformEnum;
import com.pj.current.global.AiChartConstant;
import com.pj.models.so.SoMap;
import com.pj.project.aps.chart_message.ChartMessage;
import com.pj.project.aps.chart_message.ChartMessageMapper;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BdChartService {
    @Autowired
    private UserService userService;
    @Autowired
    private ChartMessageMapper chartMessageMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional(rollbackFor = Exception.class)
    public ChartMessage add(ChartMessage c){
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

            String result = "";
            // Ai 回复 模拟 测试
            String token = AiChartConstant.tokenMap.get(StpUtil.getLoginIdAsLong());
            if(StringUtils.isEmpty(token)){
                bd_chart_token();
            }
            // 查询历史对话内容 当天内容
            chartMessageList = getChartMessageList();
            result = bd_chart(chartMessageList);

            if(StringUtils.isEmpty(result)){
                throw new RuntimeException("回复异常!");
            }
            ChartMessage chartMessage = getChartMessage(c, result);
            return chartMessage;
        }catch (Exception e){
            if(chartMessageList != null && AiChartConstant.BD_ROLE_0.equals(chartMessageList.get(chartMessageList.size() - 1).getRole())){
                chartMessageMapper.deleteById(chartMessageList.get(chartMessageList.size() - 1).getId());
            }
            throw new RuntimeException("新增失败！");
        }
    }

    public String bd_chart_token(){
        String url = AiChartConstant.BD_URL_ACCESS_TOKEN + "?client_id=" + AiChartConstant.BD_APP_KEY +
                "&client_secret=" + AiChartConstant.BD_SECRET_KEY + "&grant_type=client_credentials";
        ResponseEntity<Token> forEntity = restTemplate.getForEntity(url, Token.class);
        String accessToken = forEntity.getBody().getAccess_token();
        AiChartConstant.tokenMap.put(StpUtil.getLoginIdAsLong(),accessToken);
        return accessToken;
    }
    public List<BaiduChatMessage> getChartMessageList(){
        String startOfDay = Ttime.getStartOfDay();
        SoMap soMap = new SoMap();
        soMap.set("startTime",startOfDay);
        soMap.set("plantform", PlatformEnum.WEN_XIN_YI_YAN.getCode());
        List<ChartMessage> list = chartMessageMapper.getList(soMap);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        List<BaiduChatMessage> baiduChatMessages = list.stream().map(v -> BaiduChatMessage.builder()
                .id(v.getId())
                .role(v.getRole())
                .content(v.getContent()).build()).collect(Collectors.toList());
        return baiduChatMessages;
    }
    public String bd_chart(List<BaiduChatMessage> baiduChatMessageList){
        ErnieBotTurboStreamParam param = ErnieBotTurboStreamParam.builder()
                .user_id(StpUtil.getLoginIdAsLong() + "").messages(baiduChatMessageList).build();
        String token = AiChartConstant.tokenMap.get(StpUtil.getLoginIdAsLong());
        String url = AiChartConstant.BD_URL_CHART + "?access_token=" + token;
        ErnieBotTurboResponse ernieBotTurboResponse = restTemplate.postForObject(url,param, ErnieBotTurboResponse.class);
        return ernieBotTurboResponse.getResult();
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
        chartMessage.setPlantform(c.getPlantform());
        chartMessageMapper.add(chartMessage);
        return chartMessage;
    }
}
