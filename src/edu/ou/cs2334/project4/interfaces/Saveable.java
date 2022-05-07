package edu.ou.cs2334.project4.interfaces;

import java.io.IOException;

/**
 * Interface made to save files.
 * 
 * @author Joshua Wiseman
 * @version 0.1
 */
public interface Saveable {
	
	/**
	 * Saves the file with the given filename.
	 * 
	 * @param filename  The filename which the puzzle will be saved to.
	 * @throws IOException  Throws this exception if the file can't be made.
	 */
	void save(String filename) throws IOException;
}
