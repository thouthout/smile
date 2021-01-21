package com.smile.design.state;

/**
 * @author luweiming
 * @date 2021/1/21 上午10:23
 */
public class StopState implements State {

    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}