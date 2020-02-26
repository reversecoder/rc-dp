//package com.rc.designpattern.pattern.behavioural.command;
//
//public class SampleCommandClient {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		String initialState = "First";
//		String finalState = "Second";
//		DefaultCommandTarget migration = new DefaultCommandTarget(initialState, finalState);
//		Command cmd = new UndoableSampleCommand(migration);
//		CommandExecutor exec =CommandExecutor.getInstance();
//		System.out.print("Command execution: ");
//		exec.executeCommand(cmd);
//		System.out.print("Undo action: ");
//		exec.undoLastCommand();
//		System.out.print("Redo action: ");
//		exec.redoLastUndoedCommand();
//	}
//}