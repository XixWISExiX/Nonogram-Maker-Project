package edu.ou.cs2334.project4.views;

import java.util.HashMap;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * This class creates a BorderPane with a graphical interface for the puzzle maker. The center position contains the pane
 * created by CellGridView, and the top position contains a MenuBar.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class NonogramMakerView {
	private BorderPane borderPane;
	private MenuBar menuBar;
	private CellGridView cellGridView;
	private HashMap<String, MenuItem> menuItemsMap;
	
	/**
	 * The open option in the item menu.
	 */
	public final static String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
	
	/**
	 * The save option in the item menu.
	 */
	public final static String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
	
	/**
	 * The exit option in the item menu.
	 */
	public final static String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";
	
	/**
	 * Constructs a NonogramMakerView.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 * @param cellLength  The length of the cells in pixels.
	 */
	public NonogramMakerView(int numRows, int numCols, int cellLength){
		// Initializing all variables
		cellGridView = new CellGridView(numRows, numCols, cellLength);
		borderPane = new BorderPane();
		menuBar = new MenuBar();
		menuItemsMap = new HashMap<String, MenuItem>();
		// Sets the values for the MenuBar up
		initMenuBar();
		// Seting the borderPane top and center values
		borderPane.setTop(menuBar);
		borderPane.setCenter(cellGridView.getPane());
	}
	
	/**
	 * Initializes menuBar and menuItemsMap.
	 */
	private void initMenuBar() {
		// Make Menu
		Menu m = new Menu("_File");
		// Make Menu Items
		MenuItem m1 = new MenuItem("_Open");
		MenuItem m2 = new MenuItem("_Save");
		MenuItem m3 = new MenuItem("_Exit");
		// Add Menu Items to Menu and hashmap
		m.getItems().addAll(m1, m2, m3);
        menuItemsMap.put(MENU_ITEM_OPEN, m1);
        menuItemsMap.put(MENU_ITEM_SAVE, m2);
        menuItemsMap.put(MENU_ITEM_EXIT, m3);        
        // Program will close when the Exit item is pressed
        m3.setOnAction(event -> Platform.exit());
        // Add Menu to Menu Bar
        menuBar.getMenus().add(m);
	}
	
	/**
	 * Returns the MenuItem associated with a given key String.
	 * 
	 * @param name  Name of the key to access the MenuItem value of the given hashmap.
	 * @return  Returns the menuItem based on the key.
	 */
	public MenuItem getMenuItem(String name) {
		return menuItemsMap.get(name);
	}
	
	/**
	 * Returns the pane associated with this view.
	 * 
	 * @return  Returns the pane of the NonogramMakerView.
	 */
	public Pane getPane() {
		return borderPane;
	}
	
	/**
	 * Calls the initButtons method on the cellGridView variable.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 * @param cellLength  The length of the cells in pixels.
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
	}
	
	/**
	 * Returns the toggleButton at a given position using the cellGridView variable.
	 * 
	 * @param row  The position of that given row.
	 * @param col  The position of that given column.
	 * @return  Returns the toggleButton of a given position.
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return cellGridView.getToggleButton(row, col);
	}
	
	/**
	 * Returns the number of grid rows.
	 * 
	 * @return  Returns the number of rows in the grid.
	 */
	public int getNumRows() {
		return cellGridView.getNumRows();
	}
	
	/**
	 * Returns the number of grid columns.
	 * 
	 * @return  Returns the number of columns in the grid.
	 */
	public int getNumCols() {
		return cellGridView.getNumCols();
	}
}
