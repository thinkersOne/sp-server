package lottery;

import com.pj.SpServerApplication;
import com.pj.project.lottery.lottery_forecast.LotteryForecastService;
import com.pj.project.lottery.lottery_forecast.LotteryForestVo;
import com.pj.utils.cache.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpServerApplication.class)
public class LotteryControllerTest {
    @Autowired
    LotteryForecastService lotteryForecastService;

    @Test
    public void testForestLotteryConfig(){
        for (int i = 2013100; i < 2023147; i++) {
            LotteryForestVo lotteryForestVo = LotteryForestVo.builder()
                    .code(i+"").type(0).orderBy(0).build();
            lotteryForecastService.lotteryConfig(lotteryForestVo);
        }
    }

    @Test
    public void testRedisUtils_ListAdd(){
        RedisUtil.forListAdd("testList","123");
        RedisUtil.forListAdd("testList","456");
    }
    @Test
    public void testRedisUtils_ListRemove(){
        RedisUtil.forListRemove("testList");
    }

}
