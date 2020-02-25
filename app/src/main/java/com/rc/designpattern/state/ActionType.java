package com.rc.designpattern.state;

public enum ActionType {

    CIRCLE(0), RECTANGLE(1), TRIANGLE(2), COMPOSITE(3), UNDO(4), REDO(5);

    private int value;

    ActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}