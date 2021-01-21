package com.smile.design.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luweiming
 * @date 2021/1/21 下午2:22
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    private Order order;

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }

    public void inverk(Order order){
        this.order = order;
    }

    public void execute(){
        order.execute();
    }
}