package com.rc.designpattern.pattern.behavioural.state;

public enum CommandType {

    SHAPE_BACKGROUND_COLOR(0), SHAPE_COLOR(1), SHAPE_STATE(2);

    private int value;

    CommandType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}