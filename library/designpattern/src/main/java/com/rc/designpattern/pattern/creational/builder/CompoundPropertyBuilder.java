package com.rc.designpattern.pattern.creational.builder;

import com.rc.designpattern.pattern.structural.bridge.CompoundProperty;
import com.rc.designpattern.pattern.structural.composite.Shape;

public class CompoundPropertyBuilder {
    private Shape[] components;

    public CompoundPropertyBuilder setComponents(Shape... components) {
        this.components = components;
        return this;
    }

    public CompoundProperty createCompoundProperty() {
        return new CompoundProperty(components);
    }
}