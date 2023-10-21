package com.pj.project.lottery;

import java.util.List;

public interface LotteryInterface<T,R> {
    public List<R> syncData(T t);
}
