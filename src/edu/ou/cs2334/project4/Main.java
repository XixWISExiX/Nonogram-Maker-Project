package edu.ou.cs2334.project4;

import edu.ou.cs2334.project4.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The driver class that runs the application with given arguments.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class Main extends Application{
	private final static int IDX_NUM_ROWS = 0;
	private final static int IDX_NUM_COLS = 1;
	private final static int IDX_CELL_SIZE = 2;
	
	/**
	 * Calls the inherited launch method.
	 * 
	 * @param args  Reads the arguments.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Reads the given arguments and starts the application.
	 */
	public void start(Stage primaryStage) throws Exception {

		NonogramMakerPresenter presenter = new NonogramMakerPresenter(
                Integer.parseInt(getParameters().getUnnamed().get(IDX_NUM_ROWS)), 
                Integer.parseInt(getParameters().getUnnamed().get(IDX_NUM_COLS)), 
                Integer.parseInt(getParameters().getUnnamed().get(IDX_CELL_SIZE)));
		Scene scene = new Scene(presenter.getPane());

		scene.getStylesheets().add("style.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("Nonogram Maker");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}
