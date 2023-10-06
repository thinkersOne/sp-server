package com.pj.project.aps.chart_message;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.ListUtil;
import com.pj.current.enums.PlatformEnum;
import com.pj.current.global.AiChartConstant;
import com.pj.models.so.SoMap;
import com.pj.project.aps.chart_message.ali.AlChartService;
import com.pj.project.aps.chart_message.baidu.BdChartService;
import com.pj.project.aps.user.User;
import com.pj.project.aps.user.UserService;
import com.pj.utils.Ttime;
import com.pj.utils.ai.ali.*;
import com.pj.utils.ai.baidu.BaiduChatMessage;
import com.pj.utils.ai.baidu.ErnieBotTurboResponse;
import com.pj.utils.ai.baidu.ErnieBotTurboStreamParam;
import com.pj.utils.ai.baidu.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
    BdChartService bdChartService;
    @Autowired
    AlChartService alChartService;
    /** 增 */
    @Transactional(rollbackFor = Exception.class)
    ChartMessage add(ChartMessage c){
        ChartMessage chartMessage = null;
        try{
            if(c.getPlantform() == null || PlatformEnum.WEN_XIN_YI_YAN.getCode() == c.getPlantform()){
                chartMessage = bdChartService.add(c);
            }else if(PlatformEnum.TONG_YI_QIAN_WEN.getCode() == c.getPlantform()){
                chartMessage = alChartService.add(c);
            }
            return chartMessage;
        }catch (Exception e){
            throw new RuntimeException("新增失败:"+e.getMessage());
        }
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

}