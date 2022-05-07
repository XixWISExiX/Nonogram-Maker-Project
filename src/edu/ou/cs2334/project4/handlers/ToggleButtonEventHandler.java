package edu.ou.cs2334.project4.handlers;

import edu.ou.cs2334.project4.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

/**
 * This class implements the EventHandler interface for the ToggleButtons in CellGridView.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent>{
	private NonogramMakerModel model;
	private int rowIdx;
	private int colIdx;
	
	/**
	 * Constructs a ToggleButtonEventHandler to handle activation.
	 * 
	 * @param model  The model which the event (button press) is going to change elements in.
	 * @param rowIdx  The index of that given row.
	 * @param colIdx  The index of that given column.
	 */
	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
		this.model = model;
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
	}
	
	/**
	 * Gets the current state of the toggle button and sets the cell in the model with the same
	 * row and column indices to the same boolean value.
	 */
	@Override
	public void handle(ActionEvent event) {
		model.setCell(rowIdx, colIdx, ((ToggleButton) event.getSource()).isSelected());
	}
}
