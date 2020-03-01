package com.rc.designpattern.pattern.behavioural.state;

public enum ShapeCommandType {

    SHAPE_BACKGROUND_COLOR(0), SHAPE_COLOR(1), SHAPE_STATE(2);

    private int value;

    ShapeCommandType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}