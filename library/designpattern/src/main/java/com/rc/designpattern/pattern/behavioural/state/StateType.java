package com.rc.designpattern.pattern.behavioural.state;

public enum StateType {

    UNSELECTED(0), SELECTED(1);

    private int value = 0;

    StateType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}