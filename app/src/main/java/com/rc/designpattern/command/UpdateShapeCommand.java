package com.rc.designpattern.command;

import android.view.View;

import com.rc.designpattern.tools.RandomManager;
import com.rc.designpattern.memento.CareTaker;
import com.rc.designpattern.memento.GenericMemento;
import com.rc.designpattern.memento.GenericOriginator;

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