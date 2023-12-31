package com.pj.project.lottery.unionLotto;

import com.alibaba.fastjson.JSON;
import com.pj.current.enums.LotteryTypeEnum;
import com.pj.current.global.LotteryConstant;
import com.pj.project.lottery.unionLotto.domain.Lottery;
import com.pj.project.lottery.LotteryInterface;
import com.pj.project.lottery.LotteryParameter;
import com.pj.project.lottery.unionLotto.domain.Prizegrad;
import com.pj.project.lottery.unionLotto.domain.UnionLottoResponse;
import com.pj.project.lottery.unionLotto.utils.UnionLottoResult;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnionLotto implements LotteryInterface<LotteryParameter, UnionLottoResult> {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<UnionLottoResult> syncData(LotteryParameter param) {
        // 设置请求头的 content type
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/json, text/javascript, */*; q=0.01");
        headers.add("Content-Type","application/json");
        headers.add("Cookie","HMF_CI=dc2ac2887916254bcc063d4830650c09cd1e514fd94050020779b01de14be16616d9f613449b3cea4dc3d0afb5f23eb6db47aa1f8f6603753d1ac93d42d3c18a28; 21_vq=16");
        headers.add("Host","www.cwl.gov.cn");
        // 构建请求实体对象
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        String url = LotteryConstant.UNION_LOTTO_URL + "?name="+param.getName()+"&issueCount=&issueStart=&issueEnd=&dayStart=&dayEnd=&" +
                "pageNo="+param.getPageNo()+"&pageSize="+ param.getPageSize()+"&week=&systemType=PC";
        ResponseEntity<String> forEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        if(forEntity == null || StringUtils.isEmpty(forEntity.getBody())){
            return new ArrayList<>(1);
        }
        String body = forEntity.getBody();
        UnionLottoResponse unionLottoResponse = JSON.parseObject(body, UnionLottoResponse.class);
        if(unionLottoResponse == null || CollectionUtils.isEmpty(unionLottoResponse.getResult())){
            return new ArrayList<>(1);
        }
        List<UnionLottoResult> result = unionLottoResponse.getResult();
        return result;
    }

    public Lottery getCurrentLottery() {
        LotteryParameter parameter = LotteryParameter.builder().type(LotteryTypeEnum.UNION_LOTTO.getType())
                .pageNo(1).pageSize(1).name(LotteryTypeEnum.UNION_LOTTO.getCode()).orderBy(1).build();
        List<UnionLottoResult> unionLottoResults = syncData(parameter);
        if(CollectionUtils.isEmpty(unionLottoResults)){
            throw new RuntimeException("数据异常，请联系管理员！");
        }
        return getLottery(unionLottoResults.get(0));
    }

    public List<Lottery> getLotterys(LotteryParameter param) {
        List<UnionLottoResult> unionLottoResults = syncData(param);
        //转化数据
        List<Lottery> lotteries = convertLotterys(unionLottoResults);
        return lotteries;
    }

    private static List<Lottery> convertLotterys(List<UnionLottoResult> result) {
        List<Lottery> lotteryList = result.stream().map(v -> getLottery(v)).collect(Collectors.toList());
        return lotteryList;
    }

    @NotNull
    private static Lottery getLottery(UnionLottoResult v) {
        Lottery lottery = new Lottery();
        lottery.setBlue(v.getBlue());
        lottery.setDate(v.getDate());
        lottery.setCode(v.getCode());
        lottery.setRed(v.getRed());
        lottery.setSales(v.getSales());
        lottery.setPoolmoney(v.getPoolmoney());
        List<Prizegrad> prizegrades = v.getPrizegrades();
        prizegrades.stream().forEach(r -> {
            if (1 == r.getType()) {
                lottery.setOneTypeMoney(r.getTypemoney());
                lottery.setOneTypeNum(r.getTypenum());
            } else if (2 == r.getType()) {
                lottery.setTwoTypeMoney(r.getTypemoney());
                lottery.setTwoTypeNum(r.getTypenum());
            } else if (3 == r.getType()) {
                lottery.setThreeTypeMoney(r.getTypemoney());
                lottery.setThreeTypeNum(r.getTypenum());
            }
        });
        return lottery;
    }

}
