package edu.ou.cs2334.project4.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the functionality of the puzzle maker that is independent of the graphical interface. 
 * t encapsulates a one-dimensional array of boolean values that stores the state of each grid cell
 * (false for empty; true for filled). The array is indexed using (row-major ordering)Methods of the class
 * are used to calculate the nonogram numbers and save them to text files.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public class NonogramMakerModel {	
	private static char EMPTY_CELL_CHAR = '0';
	private static char FILLED_CELL_CHAR = '1';
	private int numRows;
	private int numCols;
	private boolean[] grid;
	
	/**
	 * Creates a 1D boolean array with the given number of rows and columns. With R rows and C columns.
	 * 
	 * @param numRows  Number of rows which the grid is going to contain.
	 * @param numCols  Number of columns which the grid is going to contain.
	 */
	public NonogramMakerModel(int numRows, int numCols){
		if(numRows <= 0 || numCols <= 0)
			throw new IllegalArgumentException();
		this.numRows = numRows;
		this.numCols = numCols;
		grid = new boolean[numRows*numCols];
	}
	
	/**
	 * Constructs a NonogramMakerModel by reading in data from a file. Reads data to populate the grid array.
	 * 
	 * @param file  The file which contains the data necessary to make a Nonogram.
	 * @throws IOException  Throws this exception if the file is not found.
	 */
	public NonogramMakerModel(File file) throws IOException{
		Scanner scnr = new Scanner(file);
		BufferedReader in = new BufferedReader(new FileReader(file));
		numRows = scnr.nextInt();
		numCols = scnr.nextInt();
		grid = new boolean[numRows*numCols];
		for(int i = 0; i <= numRows+numCols; ++i) {
			in.readLine();
		}		
		String str;
		char[] row;
		int count = 0;
		while ((str = in.readLine()) != null) {
			row = str.toCharArray();
			for(char c : row) {
				if(c == FILLED_CELL_CHAR)
					grid[count] = true;
				if(c == EMPTY_CELL_CHAR)
					grid[count] = false;
				count++;
			}
		}
		scnr.close();
		in.close();
	}
	
	/**
	 * Constructs a NonogramMakerModel by reading in data from a file given a filename. 
	 * 
	 * @param filename  The file location which contains the data necessary to make a Nonogram.
	 * @throws IOException  Throws this exception if the file is not found.
	 */
	public NonogramMakerModel(String filename) throws IOException{
		this(new File(filename));
	}
	
	/**
	 * Returns a copy of the grid array.
	 * 
	 * @return  Returns the grid.
	 */
	public boolean[] getGrid(){
		return grid;
	}
	
	/**
	 * Returns the cell state at a given position.
	 * 
	 * @param rowIdx  The index of that given row.
	 * @param colIdx  The index of that given column.
	 * @return  Returns the cell value of that position.
	 */
	public boolean getCell(int rowIdx, int colIdx) {
		return grid[numCols*(rowIdx)+colIdx];
	}
	
	/**
	 * Updates the cell state at a given position.
	 * 
	 * @param rowIdx  The index of that given row.
	 * @param colIdx  The index of that given column.
	 * @param state  The boolean value the cell is going to turn into (True or False).
	 */
	public void setCell(int rowIdx, int colIdx, boolean state) {
		grid[numCols*(rowIdx)+colIdx] = state;
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
	 * Returns the nonogram numbers of the given array of cell states.
	 * 
	 * @param cells  The different cell values which the projecter trys to project.
	 * @return  Returns the nonogram values of a given number of cell states.
	 */
	public static List<Integer> project(boolean[] cells){
		List<Integer> list = new LinkedList<Integer>();
		int count = 0;
		boolean called = false;
		for(int i = 0; i < cells.length; ++i) {
			if(cells[i]) {
				count++;
				called = true;
			}
			else {
				if(count > 0) {
					list.add(count);
					count = 0;
					called = false;
				}
			}
		}
		if(called || list.size() == 0)
			list.add(count);
		return list;
	}
	
	/**
	 * Returns the projection of the row with the given index.
	 * 
	 * @param rowIdx  The index of that given row.
	 * @return  Returns the projection of a given row.
	 */
	public List<Integer> projectRow(int rowIdx){
		boolean[] row = new boolean[numCols];
		for(int i = 0; i < row.length; ++i) {
			row[i] = grid[rowIdx*numCols+i];
		}
		return project(row);
	}
	
	/**
	 * Returns the projection of the column with the given index.
	 * 
	 * @param colIdx  The index of that given column.
	 * @return  Returns the projection of a given column.
	 */
	public List<Integer> projectCol(int colIdx){
		boolean[] col = new boolean[numRows];
		for(int i = 0; i < col.length; ++i) {
			col[i] = grid[colIdx+i*numCols];
		}
		return project(col);
	}
	
	/**
	 * Saves the output of toString to a text file with the given name.
	 * 
	 * @param filename  The file location to save the Nonogram to.
	 * @throws IOException  Throws this exception if the file is not found.
	 */
	public void saveToFile(String filename) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
		bw.flush();
		bw.write(toString());
		bw.close();
	}
	
	/**
	 * Returns a string representation of the puzzle/Nonogram.
	 */
	public String toString() {
		String s = "";
		
		// Number of rows and columns
		s+=""+numRows;
		s+=" "+numCols+System.lineSeparator();
		
		// Rows numbers true Side
		String tempHold = "";
		for(int i = 0; i < numRows; ++i) {
			for(int k : projectRow(i))
				tempHold+=k+" ";
			s+=tempHold.trim();
			tempHold="";
			s+=System.lineSeparator();
		}
		
		// Columns numbers true Side
		for(int i = 0; i < numCols; ++i) {
			for(int k : projectCol(i)) {
				tempHold+=k+" ";
			}
			s+=tempHold.trim();
			tempHold="";
			s+=System.lineSeparator();
		}
		
		// Printed out grid
		for(int i = 0; i < numRows; ++i) {
			for(int j = 0; j < numCols; ++j) {
				if(grid[i*numCols+j])
					s+=FILLED_CELL_CHAR;
				else
					s+=EMPTY_CELL_CHAR;
			}
			if(!(i == numRows-1))
				s+=System.lineSeparator();
		}
		return s;
	}
}
