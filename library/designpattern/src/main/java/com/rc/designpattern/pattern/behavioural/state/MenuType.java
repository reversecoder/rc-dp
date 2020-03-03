package com.rc.designpattern.pattern.behavioural.state;

public enum MenuType {

    CIRCLE(0), RECTANGLE(1), TRIANGLE(2), UNDO(4), REDO(5);

    private int value;

    MenuType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}