package edu.ou.cs2334.project4.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * This class creates a GridPane of ToggleButtons that is the primary component of the puzzle maker
 * interface. The user clicks the buttons to draw an image, and the buttons update the cell states in the model.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class CellGridView {
	private ArrayList<ToggleButton> gridButtons;
	private GridPane gridPane;
	private int numRows;
	private int numCols;
	
	/**
	 * Initializes instance variables and set the alignment of the grid to the Pos value CENTER.
	 * Also, initializes the toggle buttons using a helper method.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 * @param cellLength  The length of the cells in pixels.
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		// Initializing variables
		this.numRows = numRows;
		this.numCols = numCols;
		gridPane = new GridPane();
		gridButtons = new ArrayList<ToggleButton>();
		gridPane.setAlignment(Pos.CENTER);
		initButtons(numRows, numCols, cellLength);
	}
	
	/**
	 * Clears the gridButtons ArrayList and gridPane children list (to refreash the application)
	 * and also creates ToggleButtons for each element of the grid.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 * @param cellLength  The length of the cells in pixels.
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		// Clear the gridButtons ArrayList and gridPane
		gridButtons.clear();
		gridPane.getChildren().clear();
		this.numRows = numRows;
		this.numCols = numCols;
		// Creates ToggleButtons for each element of the grid
		for(int i = 0; i < numRows; ++i) {
			for(int j = 0; j < numCols; j++) {
				ToggleButton tb = new ToggleButton();
				tb.setMinSize(cellLength, cellLength);
				tb.setMaxSize(cellLength, cellLength);
				tb.setPrefSize(cellLength, cellLength);
				
				gridPane.add(tb, cellLength*j, cellLength*i);
				gridButtons.add(tb);
			}
		}

	}
	
	/**
	 * Returns the number of grid rows.
	 * 
	 * @return  Returns the number of rows in the grid.
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * Returns the number of grid columns.
	 * 
	 * @return  Returns the number of columns in the grid.
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * Returns the toggleButton at a given position using the gridButtons ArrayList.
	 * 
	 * @param row  The position of that given row.
	 * @param col  The position of that given column.
	 * @return  Returns the toggleButton of a given position.
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return gridButtons.get(numCols*(row)+col);
	}
	
	/**
	 * Returns the pane associated with this view.
	 * 
	 * @return  Returns the view's pane value.
	 */
	public Pane getPane() {
		return gridPane;
	}
}
