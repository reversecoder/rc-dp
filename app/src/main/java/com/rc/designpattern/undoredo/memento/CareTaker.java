package com.rc.designpattern.undoredo.memento;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private List<GenericMemento> mementoList = new ArrayList<GenericMemento>();

    public void add(GenericMemento state, int currentState) {
        if (currentState == mementoList.size()) {
            mementoList.add(state);
            return;
        }

        if (currentState - 1 < mementoList.size()) {
            mementoList.set(currentState, state);
            return;
        }
    }

    public GenericMemento get(int index) {
        if (index < mementoList.size()) {
            return mementoList.get(index);
        }

        return null;
    }

    public void update() {

    }
}