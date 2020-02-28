package com.rc.designpattern.pattern.controller;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.rc.designpattern.R;
import com.rc.designpattern.pattern.behavioural.command.AddShapeCommand;
import com.rc.designpattern.pattern.behavioural.command.Command;
import com.rc.designpattern.pattern.behavioural.command.CommandExecutor;
import com.rc.designpattern.pattern.creational.singleton.ShapeManager;
import com.rc.designpattern.pattern.structural.composite.Circle;
import com.rc.designpattern.pattern.structural.composite.CompoundShape;
import com.rc.designpattern.pattern.structural.composite.Shape;
import com.rc.designpattern.pattern.behavioural.state.ActionType;
import com.rc.designpattern.util.RandomManager;

public class Dispatcher {

    public void dispatch(Context context, ActionType actionType, ViewGroup parentView) {
        switch (actionType) {
            case CIRCLE:
                Shape chileCircle = ShapeManager.getInstance(context).getCircleShape();
                CompoundShape compoundShape = new CompoundShape(context, chileCircle);
                compoundShape.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
                params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                compoundShape.setLayoutParams(params);

                AddShapeCommand addShapeCommand = new AddShapeCommand(parentView, compoundShape.getShapeView());
                CommandExecutor.getInstance().executeCommand(addShapeCommand);
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