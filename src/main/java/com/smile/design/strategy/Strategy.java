package com.smile.design.strategy;

/**
 * @author luweiming
 * @date 2021/1/21 上午11:19
 */
public interface Strategy {
    int doOperation(int num1, int num2);

    void getTrace(Integer expressId);
}
