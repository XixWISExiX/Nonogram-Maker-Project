package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project4.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * Implements the EventHandler interface to the save feature and must implement the handle method this then
 * uses the FileChooser instance variable to show an save dialog to the user.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent>{
	private Saveable saver;
	
	/**
	 * Constructs a SaveHandler to then activate the save function when the save button is pressed.
	 * 
	 * @param window  Window of the given case.
	 * @param fileChooser  FileChooser of the given case.
	 * @param saver  Allows the class to save.
	 */
	public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
		super(window, fileChooser);
		this.saver = saver;
	}

	/**
	 * Uses the FileChooser instance variable to show an save dialog to the user
	 * and then implement that save.
	 */
	@Override
	public void handle(ActionEvent event) {
		File file = fileChooser.showSaveDialog(window);
		if (file != null) {
			try {
				saver.save(file.toString());
			} catch (IOException e) {
			}
        }
	}
}
