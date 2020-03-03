package com.rc.designpattern.pattern.behavioural.command;

import android.util.Log;

import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.memento.CareTaker;
import com.rc.designpattern.pattern.behavioural.memento.GenericMemento;
import com.rc.designpattern.pattern.behavioural.memento.GenericOriginator;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.behavioural.state.CommandType;
import com.rc.designpattern.pattern.behavioural.state.StateType;
import com.rc.designpattern.pattern.creational.abstractfactory.Shape;

public class UpdateShapeCommand implements Command {

    private String TAG = UpdateShapeCommand.class.getSimpleName();
    private Shape shape;
    private String key;
    private CommandType commandType;
    private MutableVariable oldPropertyValue;
    private MutableVariable newPropertyValue;

    public UpdateShapeCommand(Shape shape, CommandType commandType, MutableVariable oldPropertyValue, MutableVariable newPropertyValue) {
        this.shape = shape;
        this.commandType = commandType;
        this.oldPropertyValue = oldPropertyValue;
        this.newPropertyValue = newPropertyValue;
        this.key = commandType.name() + shape.getShapeProperty().getShapeId();
        Log.d(TAG, TAG + ">>key: " + key);
    }

    @Override
    public void doIt() {
        switch (commandType) {
            case SHAPE_STATE:
                shape.getShapeProperty().setStateType((StateType) newPropertyValue.getValue());
                break;
            case SHAPE_BACKGROUND_COLOR:
                shape.getShapeProperty().setShapeBackgroundColor((int) newPropertyValue.getValue());
                break;
            case SHAPE_COLOR:
                shape.getShapeProperty().setShapeColor((int) newPropertyValue.getValue());
                break;
        }

        // Notify Observer for shape do
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(AddShapeCommand.class.getSimpleName() + shape.getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue(shape);
        }

        // Memento
        GenericOriginator<Shape> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<Shape> currentMemento = (GenericMemento<Shape>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);
    }

    @Override
    public String whoAmI() {
        return key;
    }

    @Override
    public void undoIt() {
        // Memento
        GenericMemento mMemento = CareTaker.getInstance().get(key);
        Shape mShape = (Shape) mMemento.getState();
        switch (commandType) {
            case SHAPE_STATE:
                mShape.getShapeProperty().setStateType((StateType) oldPropertyValue.getValue());
                break;
            case SHAPE_BACKGROUND_COLOR:
                mShape.getShapeProperty().setShapeBackgroundColor((int) oldPropertyValue.getValue());
                break;
            case SHAPE_COLOR:
                mShape.getShapeProperty().setShapeColor((int) oldPropertyValue.getValue());
                break;
        }

        // Notify Observer for shape undo
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(AddShapeCommand.class.getSimpleName() + shape.getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue(mShape);
        }
    }
}