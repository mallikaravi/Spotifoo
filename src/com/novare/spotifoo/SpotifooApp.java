package com.novare.spotifoo;

import com.novare.spotifoo.view.SpotifooView;

/**
 * This {@code SpotifooApp} is the main class of this application that
 * instantiate the SpotifooView class.
 * 
 * @author mallika
 *
 */
public class SpotifooApp {

	/**
	 * The main method of this application
	 * 
	 * @param args, array of String arguments
	 */
	public static void main(String[] args) {
		SpotifooView view = new SpotifooView();
		view.start("assets/data.txt");
	}

}
