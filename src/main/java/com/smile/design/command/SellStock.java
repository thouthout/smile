package com.smile.design.command;

/**
 * @author luweiming
 * @date 2021/1/21 下午2:22
 */
public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}