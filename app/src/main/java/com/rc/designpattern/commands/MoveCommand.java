//package com.rc.designpattern.commands;
//
//import com.rc.designpattern.shapes.Shape;
//import com.rc.designpattern.view.DragLayout;
//
//public class MoveCommand implements Command {
//    private DragLayout editor;
//    private int startX, startY;
//    private int endX, endY;
//
//    public MoveCommand(DragLayout editor) {
//        this.editor = editor;
//    }
//
//    @Override
//    public String getName() {
//        return "Move by X:" + (endX - startX) + " Y:" + (endY - startY);
//    }
//
//    public void start(int x, int y) {
//        startX = x;
//        startY = y;
//        for (Shape child : editor.getShapes().getSelected()) {
//            child.drag();
//        }
//    }
//
//    public void move(int x, int y) {
//        for (Shape child : editor.getShapes().getSelected()) {
//            child.moveTo(x - startX, y - startY);
//        }
//    }
//
//    public void stop(int x, int y) {
//        endX = x;
//        endY = y;
//        for (Shape child : editor.getShapes().getSelected()) {
//            child.drop();
//        }
//    }
//
//    @Override
//    public void execute() {
//        for (Shape child : editor.getShapes().getSelected()) {
//            child.moveBy(endX - startX, endY - startY);
//        }
//    }
//}
