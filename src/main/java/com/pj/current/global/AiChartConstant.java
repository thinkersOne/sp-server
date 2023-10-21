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

    /**
     * 通义千问
     */
    public static final String AL_APP_KEY = "sk-c79e9edac5b446a0a6a376fc38cd8555";
    public static final String AL_URL_CHART = "https://dashscope.aliyuncs.com/api/v1/services/aigc/text-generation/generation";
    public static final String AL_AUTHORIZATION = "Bearer "+ AL_APP_KEY;
    public static final String AL_MODEL = "qwen-turbo";
    public static final String AL_ROLE_0 = "user";
    public static final String AL_ROLE_1 = "system";
    /**
     *"text"表示旧版本的text
     * "message"表示兼容openai的message
     */
    public static final String AL_RESULT_FORMAT = "message";
    /**
     * 生成时，核采样方法的概率阈值。例如，取值为0.8时，仅保留累计概率之和大于等于0.8的概率分布中的token，作为随机采样的候选集。
     * 取值范围为(0,1.0)，取值越大，生成的随机性越高；取值越低，生成的随机性越低。默认值 0.8。注意，取值不要大于等于1
     */
    public static final Float AL_TOP_P = 0.8f;
    /**
     * 生成时，采样候选集的大小。例如，取值为50时，仅将单次生成中得分最高的50个token组成随机采样的候选集。
     * 取值越大，生成的随机性越高；取值越小，生成的确定性越高。注意：如果top_k的值大于100，top_k将采用默认值100
     */
    public static final Integer AL_TOP_K = 50;
    /**
     * 生成时，随机数的种子，用于控制模型生成的随机性。如果使用相同的种子，每次运行生成的结果都将相同；
     * 当需要复现模型的生成结果时，可以使用相同的种子。seed参数支持无符号64位整数类型。默认值 1234
     */
    public static final Integer AL_SEED = 1234;
    /**
     * 用于控制随机性和多样性的程度。具体来说，temperature值控制了生成文本时对每个候选词的概率分布进行平滑的程度。
     * 较高的temperature值会降低概率分布的峰值，使得更多的低概率词被选择，生成结果更加多样化；
     * 而较低的temperature值则会增强概率分布的峰值，使得高概率词更容易被选择，生成结果更加确定。
     * 取值范围： (0, 2),系统默认值1.0
     */
    public static final Float AL_TEMPERATURE = 1.0f;
    public static final Boolean AL_ENABLE_SEARCH = false;

}