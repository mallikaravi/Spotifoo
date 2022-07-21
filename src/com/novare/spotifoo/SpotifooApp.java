package com.novare.spotifoo;

import com.novare.spotifoo.util.MenuBuilder;

/**
 * This {@code SpotifooApp} is the main class of this application that
 * instantiate the Menubuilder class.
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
		MenuBuilder menuBuilder = new MenuBuilder();
		menuBuilder.start("assets/data.txt");
	}

}
