package com.smile.design.command;

/**
 * @author luweiming
 * 创建
 * @date 2021/1/21 下午2:20
 */
public class Stock {

    private String name = "ABC";
    private int quantity = 10;

    public void buy(){
        System.out.println("Stock [ Name: "+name+", quantity: " + quantity +" ] bought");
    }
    public void sell(){
        System.out.println("Stock [ Name: "+name+", quantity: " + quantity +" ] sold");
    }
}
