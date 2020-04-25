package com.mhm.action.state;

/**
 * @author MHm
 * @date 2020-4-19 20:41
 */
public class StartState implements IState{
    @Override
    public void action(StateContext stateContext) {
        System.out.println("设置start状态");
        stateContext.setState(this);
    }
    public String getState(){
        return "start";
    }
}
