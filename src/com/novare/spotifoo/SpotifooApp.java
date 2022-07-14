package com.novare.spotifoo;

import java.util.Scanner;

public class SpotifooApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Spotifoo music player! \n\n");
		while (true) {
			mainMenu();
		}
	}

	private static void mainMenu() {
		System.out.println("Main menu options:");
		System.out.println("[1] Songs");
		System.out.println("[2] Artists");
		System.out.println("[3] Albums");
		System.out.println("[4] Genres");
		System.out.println("[5] Search");
		System.out.print("Choose an option and press enter:");

		Scanner read = new Scanner(System.in);
		int choice = read.nextInt();
		switch (choice) {
		case 1: {
			System.exit(0);
		}
		default:
			System.out.println("Invalid option");
		}
		read.close();

	}
}
