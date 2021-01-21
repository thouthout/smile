package com.smile.design.state;


/**
 * @author luweiming
 * @date 2021/1/21 上午10:22
 */
public class StartState implements State{

    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
