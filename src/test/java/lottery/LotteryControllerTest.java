package lottery;

import com.pj.SpServerApplication;
import com.pj.project.lottery.lottery_forecast.LotteryForecastService;
import com.pj.project.lottery.lottery_forecast.LotteryForestVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpServerApplication.class)
public class LotteryControllerTest {
    @Autowired
    LotteryForecastService lotteryForecastService;

    @Test
    public void testForestLotteryConfig(){
        LotteryForestVo lotteryForestVo = LotteryForestVo.builder()
                .code("2023149").type(0).orderBy(0).build();
        lotteryForecastService.lotteryConfig(lotteryForestVo);
    }


}
