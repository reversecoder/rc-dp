package com.rc.designpattern.pattern.behavioural.command;

import android.util.Log;
import android.view.View;

import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.memento.CareTaker;
import com.rc.designpattern.pattern.behavioural.memento.GenericMemento;
import com.rc.designpattern.pattern.behavioural.memento.GenericOriginator;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.behavioural.state.ShapeCommandType;
import com.rc.designpattern.pattern.behavioural.state.ShapeState;
import com.rc.designpattern.pattern.creational.abstractfactory.Shape;

public class UpdateShapeCommand implements Command {

    private String TAG = UpdateShapeCommand.class.getSimpleName();
    private Shape shape;
    private String key;
    private ShapeCommandType shapeCommandType;
    private MutableVariable oldPropertyValue;
    private MutableVariable newPropertyValue;

    public UpdateShapeCommand(ShapeCommandType shapeCommandType, MutableVariable oldPropertyValue, MutableVariable newPropertyValue, Shape shape) {
        this.shape = shape;
        this.shapeCommandType = shapeCommandType;
        this.oldPropertyValue = oldPropertyValue;
        this.newPropertyValue = newPropertyValue;
        this.key = shapeCommandType.name() + shape.getShapeProperty().getShapeId();
        Log.d(TAG, TAG + ">>key: " + key);
    }

    @Override
    public void doIt() {
        switch (shapeCommandType) {
            case SHAPE_STATE:
                shape.getShapeProperty().setShapeState((ShapeState) newPropertyValue.getValue());
                break;
            case SHAPE_BACKGROUND_COLOR:
                shape.getShapeProperty().setShapeBackgroundColor((int) newPropertyValue.getValue());
                break;
            case SHAPE_COLOR:
                shape.getShapeProperty().setShapeColor((int) newPropertyValue.getValue());
                break;
        }
        shape.refreshView();

        // Memento
        GenericOriginator<Shape> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<Shape> currentMemento = (GenericMemento<Shape>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);

        // Notify Observer for shape do
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(AddShapeCommand.class.getSimpleName() + shape.getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue(shape);
        }
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
        switch (shapeCommandType) {
            case SHAPE_STATE:
                mShape.getShapeProperty().setShapeState((ShapeState) oldPropertyValue.getValue());
                break;
            case SHAPE_BACKGROUND_COLOR:
                mShape.getShapeProperty().setShapeBackgroundColor((int) oldPropertyValue.getValue());
                break;
            case SHAPE_COLOR:
                mShape.getShapeProperty().setShapeColor((int) oldPropertyValue.getValue());
                break;
        }
        mShape.refreshView();

        // Notify Observer for shape undo
        Topic<Shape> topic = TopicIteratorManager.getInstance().getTopic(AddShapeCommand.class.getSimpleName() + shape.getShapeProperty().getShapeId());
        if (topic != null) {
            topic.setValue(mShape);
        }
    }
}