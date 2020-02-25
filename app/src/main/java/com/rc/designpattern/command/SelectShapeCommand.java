package com.rc.designpattern.command;

import android.util.Log;
import android.view.View;

import com.rc.designpattern.composite.Shape;
import com.rc.designpattern.memento.CareTaker;
import com.rc.designpattern.memento.GenericMemento;
import com.rc.designpattern.memento.GenericOriginator;
import com.rc.designpattern.state.ShapeState;
import com.rc.designpattern.tools.RandomManager;

public class SelectShapeCommand implements Command {

    private View shape;
    private String key;

    public SelectShapeCommand(View shape) {
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(30);
        Log.d(SelectShapeCommand.class.getSimpleName(), "key: " + key);
    }

    @Override
    public void doIt() {
        ((Shape) this.shape).setShapeState(ShapeState.SELECTED);
        // Memento
        GenericOriginator<View> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<View> currentMemento = (GenericMemento<View>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);
    }

    @Override
    public String whoAmI() {
        return SelectShapeCommand.class.getSimpleName() + "\n(" + key + ")";
    }

    @Override
    public void undoIt() {
        // Memento
        GenericMemento mMemento = CareTaker.getInstance().get(key);
        Shape mShape = (Shape) mMemento.getState();
        mShape.setShapeState(ShapeState.UNSELECTED);
    }
}