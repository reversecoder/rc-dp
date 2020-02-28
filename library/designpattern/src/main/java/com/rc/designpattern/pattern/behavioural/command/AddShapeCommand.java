package com.rc.designpattern.pattern.behavioural.command;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rc.designpattern.pattern.behavioural.memento.CareTaker;
import com.rc.designpattern.pattern.behavioural.memento.GenericMemento;
import com.rc.designpattern.pattern.behavioural.memento.GenericOriginator;
import com.rc.designpattern.util.RandomManager;

public class AddShapeCommand implements Command {

    private ViewGroup parentView;
    private View shape;
    private String key;

    public AddShapeCommand(ViewGroup parentView, View shape) {
        this.parentView = parentView;
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(30);
        Log.d(AddShapeCommand.class.getSimpleName(), "key: " + key);
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
        return AddShapeCommand.class.getSimpleName() + "\n(" + key + ")";
    }

    @Override
    public void undoIt() {
        // Memento
        GenericMemento mMemento = CareTaker.getInstance().get(key);
        parentView.removeView((View) mMemento.getState());
    }
}