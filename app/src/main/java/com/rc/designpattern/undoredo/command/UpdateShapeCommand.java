package com.rc.designpattern.undoredo.command;

import android.view.View;
import android.view.ViewGroup;

import com.rc.designpattern.tools.RandomManager;
import com.rc.designpattern.undoredo.memento.CareTaker;
import com.rc.designpattern.undoredo.memento.GenericMemento;
import com.rc.designpattern.undoredo.memento.GenericOriginator;

public class UpdateShapeCommand implements Command {

    private View shape;
    private String key;

    public UpdateShapeCommand(View shape) {
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(20);
    }

    @Override
    public void doIt() {
        // Memento
        GenericOriginator<View> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<View> currentMemento = (GenericMemento<View>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);
    }

    @Override
    public String whoAmI() {
        return UpdateShapeCommand.class.getSimpleName();
    }

    @Override
    public void undoIt() {
        // Memento
        GenericMemento mMemento = CareTaker.getInstance().get(key);
        ((View) mMemento.getState()).requestLayout();
    }
}