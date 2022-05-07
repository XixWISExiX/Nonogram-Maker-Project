package edu.ou.cs2334.project4.presenters;

import java.io.File;
import java.io.IOException;

import edu.ou.cs2334.project4.handlers.OpenHandler;
import edu.ou.cs2334.project4.handlers.SaveHandler;
import edu.ou.cs2334.project4.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import edu.ou.cs2334.project4.models.NonogramMakerModel;
import edu.ou.cs2334.project4.views.NonogramMakerView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The graphical view and model data are connected by this class. Because the size of a NonogramMakerModel is immutable,
 * any size adjustments (rows or columns) require re-initializing the model and view information. The model reference
 * is completely updated. The view reference stays the same, though underlying data within the view undergoes change.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class NonogramMakerPresenter implements Saveable, Openable{
	private NonogramMakerView view;
	private NonogramMakerModel model;
	private int cellLength;
	
	/**
	 * Initializes the model and view instance variables and sets the cellLength instance variable.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 * @param cellLength  The length of the cells in pixels.
	 */
	public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
		model = new NonogramMakerModel(numRows, numCols);
		view = new NonogramMakerView(numRows, numCols, cellLength);
		this.cellLength = cellLength;
		init();
	}
	
	private Window getWindow() {
		try {
			return view.getPane().getScene().getWindow();
		}
		catch(NullPointerException e) {
			return null;
		}
	}
	
	private void init() {
		initToggleButtons();
		bindToggleButtons();
		configureMenuItems();
	}
	
	private void initToggleButtons() {
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		if(getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	private void bindToggleButtons() {
		for(int i = 0; i < model.getNumRows(); i++) {
			for(int j = 0; j < model.getNumCols(); j++) {
				view.getToggleButton(i, j).setSelected(model.getCell(i,  j));				
				view.getToggleButton(i, j).setOnAction(new ToggleButtonEventHandler(model, i, j) {
				});
			}
		}
	}
	
	private void configureMenuItems() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_OPEN)
		    .setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		fileChooser = new FileChooser();
		fileChooser.setTitle("Save");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_SAVE)
		    .setOnAction(new SaveHandler(getWindow(), fileChooser, this));
	}
	
	/**
	 * Returns the pane associated with this view.
	 * 
	 * @return  Returns the pane of the NonogramMakerView.
	 */
	public Pane getPane() {
		return view.getPane();
	}
	
	/**
	 * Re-initializes the model variable by calling the appropriate NonogramMakerModel constructor
	 * when opening a file.
	 */
	public void open(File file) throws IOException {
		model = new NonogramMakerModel(file);
		init();
	}
	
	/**
	 * Calls the model's saveToFile method using the given filename.
	 */
	public void save(String filename) throws IOException {
		model.saveToFile(filename);
	}
}
