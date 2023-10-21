package com.pj.project.lottery.unionLotto.utils;

import java.util.Random;

public class RandomUtils {
    private static final int COUNT = 3;
    private static final int RANDOM_INDEX = 6;

    public static int randomIndex(int count){
        return new Random().nextInt(count);
    }

    /**
     * 每一行随机取的个数  最大为3
     * @return
     */
    public static int randomFour(){
        return new Random().nextInt(COUNT);
    }

    /**
     * 随机下标
     * @return
     */
    public static int getRandomIndex(){
        return new Random().nextInt(RANDOM_INDEX);
    }


    public static void main(String[] args) {
        System.out.println(randomIndex(5));
    }

}
