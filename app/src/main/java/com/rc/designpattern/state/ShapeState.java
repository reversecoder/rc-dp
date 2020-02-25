package com.rc.designpattern.state;

public enum ShapeState {

    UNSELECTED(0), SELECTED(1);

    private int value = 0;

    ShapeState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}