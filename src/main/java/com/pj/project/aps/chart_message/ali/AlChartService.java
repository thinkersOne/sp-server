package com.pj.project.aps.chart_message.ali;

import cn.dev33.satoken.stp.StpUtil;
import com.pj.current.enums.PlatformEnum;
import com.pj.current.global.AiChartConstant;
import com.pj.models.so.SoMap;
import com.pj.project.aps.chart_message.ChartMessage;
import com.pj.project.aps.chart_message.ChartMessageMapper;
import com.pj.project.aps.user.User;
import com.pj.project.aps.user.UserService;
import com.pj.utils.Ttime;
import com.pj.utils.ai.ali.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AlChartService {
    @Autowired
    private UserService userService;
    @Autowired
    private ChartMessageMapper chartMessageMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional(rollbackFor = Exception.class)
    public ChartMessage add(ChartMessage c){
        List<ALiMessage> aLiResultMessageList = null;
        try{
            //用户 sender
            c.setUserId(StpUtil.getLoginIdAsLong());
            if(!StringUtils.hasText(c.getSender())){
                User u = userService.getById(StpUtil.getLoginIdAsLong());
                c.setSender(u.getUsername());
            }
            c.setRole(AiChartConstant.AL_ROLE_0);
            chartMessageMapper.add(c);

            String result = "";
            // 查询历史对话内容 当天内容
            ALiInput aLiInput = new ALiInput();
            aLiResultMessageList = getALiResultMessageList();
            aLiInput.setPrompt(c.getContent());
            final boolean[] singleHistory = {false};
            final boolean[] doubleHistory = {false};
            List<ALiHistory> aLiHistoryList = new ArrayList<>(20);
            if(!CollectionUtils.isEmpty(aLiResultMessageList) && aLiResultMessageList.size() > 1){
                List<ALiMessage> finalALiResultMessageList = aLiResultMessageList;
                Stream.iterate(0, i->i+1).limit(aLiResultMessageList.size()-1).forEach(i->{
                    if(i == finalALiResultMessageList.size()-1){
                        return;
                    }
                    if(!singleHistory[0] && AiChartConstant.AL_ROLE_0.equals(finalALiResultMessageList.get(i).getRole())){
                        ALiHistory aLiHistory = new ALiHistory();
                        aLiHistory.setUser(finalALiResultMessageList.get(i).getContent());
                        singleHistory[0] = true;
                        doubleHistory[0] = false;
                        aLiHistoryList.add(aLiHistory);
                    }
                    if(!doubleHistory[0] &&  AiChartConstant.AL_ROLE_1.equals(finalALiResultMessageList.get(i).getRole())){
                        ALiHistory aLiHistory = aLiHistoryList.get(aLiHistoryList.size()-1);
                        aLiHistory.setBot(finalALiResultMessageList.get(i).getContent());
                        doubleHistory[0] = true;
                        singleHistory[0] = false;
                    }
                });
            }
            aLiInput.setHistory(aLiHistoryList);
//            aLiInput.setMessages(aLiResultMessageList);
            result = al_chart(aLiInput);

            if(StringUtils.isEmpty(result)){
                throw new RuntimeException("回复异常!");
            }
            ChartMessage chartMessage = getChartMessage(c, result);
            return chartMessage;
        }catch (Exception e){
            if(aLiResultMessageList != null && AiChartConstant.BD_ROLE_0.equals(aLiResultMessageList.get(aLiResultMessageList.size() - 1).getRole())){
                chartMessageMapper.deleteById(aLiResultMessageList.get(aLiResultMessageList.size() - 1).getId());
            }
            throw new RuntimeException("新增失败:"+ e.getMessage());
        }
    }

    private ChartMessage getChartMessage(ChartMessage c, String result) {
        ChartMessage chartMessage = new ChartMessage();
        chartMessage.setSender(AiChartConstant.AL_ROLE_1);
        chartMessage.setRole(AiChartConstant.AL_ROLE_1);
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

    public List<ALiMessage> getALiResultMessageList(){
        String startOfDay = Ttime.getStartOfDay();
        SoMap soMap = new SoMap();
        soMap.set("startTime",startOfDay);
        soMap.set("plantform", PlatformEnum.TONG_YI_QIAN_WEN.getCode());
        List<ChartMessage> list = chartMessageMapper.getList(soMap);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>(1);
        }
        List<ALiMessage> aLiMessages = list.stream().map(v -> ALiMessage.builder()
                .id(v.getId())
                .role(v.getRole())
                .content(v.getContent()).build()).collect(Collectors.toList());
        return aLiMessages;
    }

    public String al_chart(ALiInput aliInput){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", AiChartConstant.AL_AUTHORIZATION);
//        headers.add("X-DashScope-SSE","enable");
//        headers.add("Accept","text/event-stream");
        ALiChartBody param = ALiChartBody.builder().model(AiChartConstant.AL_MODEL)
                .input(aliInput)
                .parameters(ALiParameter.builder().top_k(AiChartConstant.AL_TOP_K)
                        .top_p(AiChartConstant.AL_TOP_P).seed(AiChartConstant.AL_SEED).build()).build();
        HttpEntity<ALiChartBody> request = new HttpEntity<>(param,headers);
        String url = AiChartConstant.AL_URL_CHART;
        ALiChartResponse aLiChartResponse = restTemplate.postForObject(url,request, ALiChartResponse.class);
        ALiOutput output = aLiChartResponse.getOutput();
        if(output == null || output.getText() == null){
            return null;
        }
        return output.getText();
    }


}
