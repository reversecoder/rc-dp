package com.rc.designpattern.pattern.behavioural.command;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.memento.CareTaker;
import com.rc.designpattern.pattern.behavioural.memento.GenericMemento;
import com.rc.designpattern.pattern.behavioural.memento.GenericOriginator;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.structural.bridge.Property;
import com.rc.designpattern.pattern.structural.composite.CompoundShape;
import com.rc.designpattern.util.RandomManager;

public class AddShapeCommand implements Command {

    private String TAG = AddShapeCommand.class.getSimpleName();
    private ViewGroup parentView;
    private View shape;
    private String key;

    public AddShapeCommand(ViewGroup parentView, View shape) {
        this.parentView = parentView;
        this.shape = shape;
        this.key = RandomManager.getRandomNumbersAndLetters(30);
        Log.d(TAG, TAG + ">>key: " + key);
    }

    @Override
    public void doIt() {
        parentView.addView(shape);

        // Memento
        GenericOriginator<View> mOriginator = new GenericOriginator<>(shape);
        GenericMemento<View> currentMemento = (GenericMemento<View>) mOriginator.saveToMemento();
        CareTaker.getInstance().add(key, currentMemento);

        // Observer pattern
        Topic<Property> topicCircleProperty = new Topic<Property>(TAG + ">>" + ((CompoundShape) shape).getShapeProperty().getShapeId(), ((CompoundShape) shape).getShapeProperty());
        topicCircleProperty.registerSubscriber((CompoundShape) shape);
        TopicIteratorManager.getInstance().addTopic(topicCircleProperty);
        Log.d(TAG, TAG + ">>Subscribed added shape");
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

        // Observer pattern
        Topic<Property> topic = TopicIteratorManager.getInstance().getTopic(TAG + ">>" + ((CompoundShape) shape).getShapeProperty().getShapeId());
        if (topic != null) {
            topic.removeSubscriber((CompoundShape) shape);
            TopicIteratorManager.getInstance().removeTopic(topic);
            Log.d(TAG, TAG + ">>Removed subscribed shape");
        }
    }
}