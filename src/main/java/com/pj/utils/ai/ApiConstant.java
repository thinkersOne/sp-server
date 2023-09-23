package com.pj.utils.ai;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.pj.utils.SslUtils;
import com.pj.utils.ai.baidu.Token;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiConstant {

    /**
     * ERNIE_BOT_TURBO 发起会话接口
     */
    public static String getToken(String appKey, String secretKey) throws Exception {
        String url = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + appKey + "&client_secret=" + secretKey;
        SslUtils.ignoreSsl();
        String s = HttpUtil.get(url);
        Token bean = JSONUtil.toBean(s, Token.class);
        return bean.getAccess_token();
    }


    public static void main(String[] args) throws Exception {
        String appKey = "B1dUjvawdpxVkmr47I91NgVA";
        String secretKey = "aomCBu6BZn1FfqmqxX7AhDfpv1thRHkW";
        String token = getToken(appKey, secretKey);
        System.out.println(token);

    }
}