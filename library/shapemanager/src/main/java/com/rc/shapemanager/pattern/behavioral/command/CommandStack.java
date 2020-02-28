//package com.rc.shapemanager.pattern.behavioral.command;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//public class CommandStack {
//
//    private LinkedList<Command> commandStack = new LinkedList<Command>();
//    private LinkedList<Command> redoStack = new LinkedList<Command>();
//
//    public CommandStack() {
//    }
//
//    public void execute(Command command) {
//        command.execute();
//        commandStack.addFirst(command);
//        redoStack.clear();
//    }
//
//    public void undo() {
//        if (commandStack.isEmpty())
//            return;
//        Command command = commandStack.removeFirst();
//        command.undo();
//        redoStack.addFirst(command);
//    }
//
//    public void redo() {
//        if (redoStack.isEmpty())
//            return;
//        Command command = redoStack.removeFirst();
//        command.redo();
//        commandStack.addFirst(command);
//    }
//
//}