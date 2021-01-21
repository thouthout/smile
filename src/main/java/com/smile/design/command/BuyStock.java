package com.smile.design.command;

/**
 * @author luweiming
 * @date 2021/1/21 下午2:22
 */
public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}
