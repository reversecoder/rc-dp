package com.rc.designpattern.pattern.behavioural.command;

import android.util.Log;
import android.view.View;

import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.memento.CareTaker;
import com.rc.designpattern.pattern.behavioural.memento.GenericMemento;
import com.rc.designpattern.pattern.behavioural.memento.GenericOriginator;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.behavioural.state.ShapeState;
import com.rc.designpattern.pattern.creational.abstractfactory.Shape;
import com.rc.designpattern.pattern.structural.composite.CompoundShape;
import com.rc.designpattern.util.RandomManager;

public class SelectShapeCommand implements Command {

    private String TAG = SelectShapeCommand.class.getSimpleName();
    private View shape;
    private String key;

    public SelectShapeCommand(View shape) {
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(30);
        Log.d(SelectShapeCommand.class.getSimpleName(), "key: " + key);
    }

    @Override
    public void doIt() {
        Shape mShape = (Shape) this.shape;
        mShape.getShapeProperty().setShapeState(mShape.getShapeProperty().getShapeState() == ShapeState.SELECTED ? ShapeState.UNSELECTED : ShapeState.SELECTED);
        mShape.refreshView();

        // Memento
        GenericOriginator<View> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<View> currentMemento = (GenericMemento<View>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);

        // Notify Observer for shape selection
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(TAG + ">>" + ((CompoundShape) shape).getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue((Shape)shape);
        }
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
        mShape.getShapeProperty().setShapeState(mShape.getShapeProperty().getShapeState() == ShapeState.SELECTED ? ShapeState.UNSELECTED : ShapeState.SELECTED);
        mShape.refreshView();

        // Notify Observer for shape unselection
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(TAG + ">>" + ((CompoundShape) shape).getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue((Shape)shape);

            topic.removeSubscriber((CompoundShape) shape);
            Log.d(TAG, TAG + ">>Removed subscribed shape");
        }
    }
}