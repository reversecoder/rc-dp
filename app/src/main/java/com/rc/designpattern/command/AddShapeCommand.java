package com.rc.designpattern.command;

import android.view.View;
import android.view.ViewGroup;

import com.rc.designpattern.tools.RandomManager;
import com.rc.designpattern.memento.CareTaker;
import com.rc.designpattern.memento.GenericMemento;
import com.rc.designpattern.memento.GenericOriginator;

public class AddShapeCommand implements Command {

    private ViewGroup parentView;
    private View shape;
    private String key;

    public AddShapeCommand(ViewGroup parentView, View shape) {
        this.parentView = parentView;
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(20);
    }

    @Override
    public void doIt() {
        parentView.addView(shape);
        // Memento
        GenericOriginator<View> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<View> currentMemento = (GenericMemento<View>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);
    }

    @Override
    public String whoAmI() {
        return AddShapeCommand.class.getSimpleName();
    }

    @Override
    public void undoIt() {
        // Memento
        GenericMemento mMemento = CareTaker.getInstance().get(key);
        parentView.removeView((View) mMemento.getState());
    }
}