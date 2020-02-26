package com.rc.shapemanager.pattern.behavioral.state;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class UnselectedState implements State {

    @Override
    public void performAction(StateManager stateManager) {
        stateManager.setState(this);
    }
}