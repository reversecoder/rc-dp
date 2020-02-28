package com.rc.shapemanager.pattern.behavioral.command;

import android.util.Log;

import com.rc.shapemanager.pattern.creational.abstractfactory.Shape;
import com.rc.shapemanager.pattern.structural.bridge.CircleViewGroupProperty;
import com.rc.shapemanager.pattern.structural.bridge.Property;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class CommandManager {

    private String TAG = "CommandManager";
    private Stack<Command> normalStack;
    private Stack<Command> reverseStack;

    public CommandManager() {
        this.normalStack = new Stack<>();
        this.reverseStack = new Stack<>();
    }

    public void executeCommand(Command command, Property property) {
        if (!reverseStack.isEmpty()) {
            reverseStack.clear();
        }
        command.execute(property);
        Log.d(TAG, "CommandManager>>executeCommand>>property: " + property.toString());
        normalStack.push(command);

        Iterator value = normalStack.iterator();
        while (value.hasNext()) {
            Log.d(TAG, "CommandManager>>executeCommand>>normalStack: " + value.next());
        }
    }

    public void undo(Shape shape) {
        Command undoCommand;
        if (!normalStack.isEmpty()) {
            undoCommand = normalStack.pop();
            Log.d(TAG, "CommandManager>>undo>>property: " + undoCommand.getProperty().toString());
            reverseStack.push(undoCommand);
            undoCommand.undo(shape, undoCommand.getProperty());

            Iterator value = normalStack.iterator();
            while (value.hasNext()) {
                Log.d(TAG, "CommandManager>>undo>>normalStack: " + value.next());
            }
        } else {
            Log.d(TAG, "CommandManager>>undo>>undoCommand: not found");
        }
    }

    public void redo(Shape shape) {
        Command redoCommand;
        if (!reverseStack.isEmpty()) {
            redoCommand = reverseStack.pop();
            Log.d(TAG, "CommandManager>>redo>>property: " + redoCommand.getProperty().toString());
            normalStack.push(redoCommand);
            redoCommand.redo(shape, redoCommand.getProperty());
        } else {
            Log.d(TAG, "CommandManager>>redo>>redoCommand: not found");
        }
    }
}
