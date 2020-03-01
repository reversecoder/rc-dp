package com.rc.designpatterndemo.controller;

import android.app.Activity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.rc.designpattern.pattern.behavioural.command.AddShapeCommand;
import com.rc.designpattern.pattern.behavioural.command.Command;
import com.rc.designpattern.pattern.behavioural.command.CommandExecutor;
import com.rc.designpattern.pattern.behavioural.iterator.TopicIteratorManager;
import com.rc.designpattern.pattern.behavioural.observer.Topic;
import com.rc.designpattern.pattern.behavioural.state.ActionType;
import com.rc.designpattern.pattern.creational.abstractfactory.Shape;
import com.rc.designpattern.pattern.creational.singleton.ShapeManager;
import com.rc.designpattern.pattern.structural.composite.CompoundShape;
import com.rc.designpatterndemo.activity.MainActivity;

public class Dispatcher {

    public void dispatch(Activity activity, ActionType actionType, ViewGroup parentView) {
        switch (actionType) {
            case CIRCLE:
                Shape chileCircle = ShapeManager.getInstance(activity).getCircleShape();
                CompoundShape compoundShape = new CompoundShape(activity, chileCircle);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                compoundShape.setLayoutParams(params);

                AddShapeCommand addShapeCommand = new AddShapeCommand(parentView, compoundShape);
                CommandExecutor.getInstance().executeCommand(addShapeCommand);

                // Observer pattern
                Topic<Shape> topicSelectShape = new Topic<Shape>(addShapeCommand.whoAmI(), compoundShape);
                topicSelectShape.registerSubscriber((MainActivity) activity);
                topicSelectShape.registerSubscriber(compoundShape);
                TopicIteratorManager.getInstance().addTopic(topicSelectShape);
                Log.d("SelectShapeCommand", "SelectShapeCommand>>Subscribed added shape");

                break;
            case RECTANGLE:
                break;
            case TRIANGLE:
                break;
            case UNDO:
                Command commandUndo = CommandExecutor.getInstance().undoLastCommand();
                if (commandUndo != null) {
                    Toast.makeText(activity, "Undo>>" + commandUndo.whoAmI(), Toast.LENGTH_SHORT).show();
                }
                break;
            case REDO:
                Command commandRedo = CommandExecutor.getInstance().redoLastUndoedCommand();
                if (commandRedo != null) {
                    Toast.makeText(activity, "Redo>>" + commandRedo.whoAmI(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}