package com.smile.design.state;

/**
 * @author luweiming
 * @date 2021/1/21 上午10:22
 */
public class Context {

    private State state;

    public Context(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState(){
        return state;
    }

}
