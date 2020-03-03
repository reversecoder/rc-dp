package com.rc.designpattern.pattern.behavioural.state;

public enum CommandType {

    SHAPE_X(0), SHAPE_Y(1), SHAPE_RADIUS(2), SHAPE_WIDTH(3), SHAPE_HEIGHT(4), SHAPE_BACKGROUND_COLOR(5), SHAPE_COLOR(6), SHAPE_STATE(7);

    private int value;

    CommandType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}