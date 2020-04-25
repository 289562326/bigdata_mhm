package com.mhm.action.state;

/**
 * 状态模式
 * 策略模式很像
 * @author MHm
 * @date 2020-4-19 20:42
 */
public class StateContext {
    private IState state;

    public StateContext() {
         state = null;
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public static void main(String[] args) {
        StateContext stateContext = new StateContext();
        StartState startState = new StartState();
        startState.action(stateContext);
        startState.getState();

        StopState stopState = new StopState();
        stopState.action(stateContext);
        stopState.getState();
    }
}
