package com.smile.design.strategy;

/**
 * @author luweiming
 * @date 2021/1/21 上午11:20
 */
public class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }

    @Override
    public void getTrace(Integer expressId) {
        System.out.println("获取京东物流轨迹");
    }
}
