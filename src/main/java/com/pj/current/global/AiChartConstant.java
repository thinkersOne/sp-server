package com.pj.current.global;

import java.util.HashMap;
import java.util.Map;

public class AiChartConstant {
    /**
     * 保存所有用户的请求token 记录
     */
    public static Map<Long,String> tokenMap = new HashMap<>(100);

    /**
     * 文心一言
     */
    public static final String BD_APP_KEY = "B1dUjvawdpxVkmr47I91NgVA";
    public static final String BD_SECRET_KEY = "aomCBu6BZn1FfqmqxX7AhDfpv1thRHkW";
    public static final String BD_URL_ACCESS_TOKEN = "https://aip.baidubce.com/oauth/2.0/token";
    public static final String BD_URL_CHART = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/eb-instant";
    public static final String BD_ROLE_0 = "user";
    public static final String BD_ROLE_1 = "assistant";



}