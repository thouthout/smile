package com.smile.design.strategy;

/**
 * @author luweiming
 * @date 2021/1/21 上午11:21
 */
public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }

    public Object getTraceStrategy(Integer expressId){
        strategy.getTrace(expressId);
        return null;
    }
}
