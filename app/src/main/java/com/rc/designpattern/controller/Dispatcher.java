package com.rc.designpattern.controller;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.rc.designpattern.R;
import com.rc.designpattern.command.AddShapeCommand;
import com.rc.designpattern.command.Command;
import com.rc.designpattern.command.CommandExecutor;
import com.rc.designpattern.composite.Circle;
import com.rc.designpattern.composite.CompoundShape;
import com.rc.designpattern.composite.Shape;
import com.rc.designpattern.state.ActionType;
import com.rc.designpattern.tools.RandomManager;

public class Dispatcher {

    public void dispatch(Context context, ActionType actionType, ViewGroup parentView) {
        switch (actionType) {
            case COMPOSITE:
                Shape chileCircle = new Circle(context, RandomManager.getRandom(100, 500), RandomManager.getRandom(100, 800), RandomManager.getRandom(50, 100));
                chileCircle.setShapeColor(RandomManager.getRandomColor());
//                ((View)myShape).setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));
                CompoundShape compoundShape = new CompoundShape(context, chileCircle);
                compoundShape.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                compoundShape.setLayoutParams(params);

                AddShapeCommand addShapeCommand = new AddShapeCommand(parentView, compoundShape);
                CommandExecutor.getInstance().executeCommand(addShapeCommand);
                break;
            case CIRCLE:
                Circle circleShape = new Circle(context, RandomManager.getRandom(100, 500), RandomManager.getRandom(100, 800), RandomManager.getRandom(50, 100));
                circleShape.setShapeColor(RandomManager.getRandomColor());

                AddShapeCommand addCircleCommand = new AddShapeCommand(parentView, circleShape);
                CommandExecutor.getInstance().executeCommand(addCircleCommand);
                break;
            case RECTANGLE:
                break;
            case TRIANGLE:
                break;
            case UNDO:
                Command commandUndo = CommandExecutor.getInstance().undoLastCommand();
                if (commandUndo != null) {
                    Toast.makeText(context, commandUndo.whoAmI(), Toast.LENGTH_SHORT).show();
                }
                break;
            case REDO:
                Command commandRedo = CommandExecutor.getInstance().redoLastUndoedCommand();
                if (commandRedo != null) {
                    Toast.makeText(context, commandRedo.whoAmI(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}