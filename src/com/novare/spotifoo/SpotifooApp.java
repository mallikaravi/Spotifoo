package com.novare.spotifoo;

import com.novare.spotifoo.util.Database;
import com.novare.spotifoo.util.MenuBuilder;

public class SpotifooApp {

	public static void main(String[] args) {
		Database.INST.readSongsData("assets/data.txt");
		MenuBuilder menuBuilder=new MenuBuilder();
		menuBuilder.mainMenu(true);
	}

	}
