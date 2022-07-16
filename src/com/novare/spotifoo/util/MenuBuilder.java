package com.novare.spotifoo.util;

import java.awt.Desktop;
import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.novare.spotifoo.model.Album;
import com.novare.spotifoo.model.Artist;
import com.novare.spotifoo.model.Genre;
import com.novare.spotifoo.model.Song;

public class MenuBuilder {

	private static final String WELCOME_NOTE="Welcome to the Spotifoo music player!\n\n";
	private enum Icon {
		WARNING("\u26A0"), PLAY("\u23e9"), ERROR("\u2716"), SEARCH("\u231b");

		private final String code;

		private Icon(String code) {
			this.code = code;
		}

	}

	public void mainMenu(boolean visible) {
		List<String> mainMenu = Arrays.asList("Songs", "Artists", "Albums", "Genres", "Search");
		displayMenu(mainMenu, "Main menu options:", visible);

		Scanner read = new Scanner(System.in);
		int choice = readInput(read.nextLine());
		switch (choice) {
		case 1: {
			songsMenu(Database.INST.getAllSongs(), true);
			break;
		}
		case 2: {
			artistMenu(Database.INST.getAllArtist(), true);
			break;
		}
		case 3: {
			albumMenu(Database.INST.getAllAlbums(), true);
			break;
		}
		case 4: {
			genreMenu(Database.INST.getAllGenre(), true);
			break;
		}
		case 5: {
			searchMenu();
			break;
		}
		default:
			log(Icon.WARNING, "Invalid option");
			mainMenu(false);
		}
		read.close();
	}

	private void songsMenu(List<Song> allSongs, boolean visible) {
		Collections.sort(allSongs);
		displayMenu(allSongs, "Songs menu:", visible);

		Scanner read = new Scanner(System.in);
		int choice = readInput(read.nextLine());
		switch (choice) {
		case 0: {
			mainMenu(true);
			break;
		}
		default:
			if (choice > 0 && choice <= allSongs.size()) {
				Song song = allSongs.get(choice - 1);
				play(song);
				log(Icon.PLAY, "Playing file!");
				System.exit(0);
			} else {
				log(Icon.WARNING, "Invalid option");
				songsMenu(allSongs, false);
			}
		}
		read.close();
	}

	private void albumMenu(List<Album> allAlbums, boolean visible) {
		Collections.sort(allAlbums);
		displayMenu(allAlbums, "album available:", visible);

		Scanner read = new Scanner(System.in);
		int choice = readInput(read.nextLine());
		switch (choice) {
		case 0: {
			mainMenu(true);
			break;
		}
		default:
			if (choice > 0 && choice <= allAlbums.size()) {
				Album album = allAlbums.get(choice - 1);
				songsMenu(album.getSongs(), true);

			} else {
				log(Icon.WARNING, "Invalid option");
				albumMenu(allAlbums, false);

			}
		}
		read.close();
	}

	private void artistMenu(List<Artist> allArtist, boolean visible) {
		Collections.sort(allArtist);
		displayMenu(allArtist, "artist available:", visible);

		Scanner read = new Scanner(System.in);
		int choice = readInput(read.nextLine());
		switch (choice) {
		case 0: {
			mainMenu(true);
			break;
		}
		default:
			if (choice > 0 && choice <= allArtist.size()) {
				Artist artist = allArtist.get(choice - 1);
				songsMenu(artist.getSongs(), true);
			} else {
				log(Icon.WARNING, "Invalid option");
				artistMenu(allArtist, false);
			}
		}
		read.close();
	}

	private void genreMenu(List<Genre> allGenres, boolean visible) {
		Collections.sort(allGenres);
		displayMenu(allGenres, "genre available:", visible);

		Scanner read = new Scanner(System.in);
		int choice = readInput(read.nextLine());
		switch (choice) {
		case 0: {
			mainMenu(true);
			break;
		}
		default:
			if (choice > 0 && choice <= allGenres.size()) {
				Genre genre = allGenres.get(choice - 1);
				songsMenu(genre.getSongs(), true);

			} else {
				log(Icon.WARNING, "Invalid option");
				genreMenu(allGenres, false);
			}
		}
		read.close();
	}

	private void searchMenu() {

		clearScreen();
		System.out.println(WELCOME_NOTE);
		System.out.println("Search for a song:");
		System.out.print("Write the name of the song and press enter:");

		Scanner read = new Scanner(System.in);
		String choice = read.nextLine();
		switch (choice) {
		case "0": {
			mainMenu(true);
			break;
		}
		default: {
			List<Song> songResult = Database.INST.searchSongs(choice);
			if (songResult.size() > 0) {
				songsMenu(songResult, true);
			} else {
				log(Icon.SEARCH, "No results found related to query");
				System.exit(0);
			}
		}
		}
		read.close();
	}

	private void displayMenu(List<?> menuItems, String menuTitle, boolean visibleMenu) {
		// Invalid case
		if (visibleMenu) {
			clearScreen();
			System.out.println(WELCOME_NOTE);
			System.out.println(menuTitle);
			for (int i = 0; i < menuItems.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + menuItems.get(i));
			}
			//System.out.println("[0] Back to main menu");
		}
		System.out.print("Choose an option and press enter:");
	}

	private void clearScreen() {

		try {
			String osName = System.getProperty("os.name").toLowerCase();
			if (osName.contains("win")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		} catch (Exception e) {
		}
	}

	private int readInput(String userInput) {
		try {
			return Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	private void play(Song song) {
		try {
			File mp3File = Paths.get(song.getFileName()).toFile();
			if (!mp3File.exists() || !mp3File.getName().endsWith("mp3")) {
				throw new IllegalArgumentException();
			}
			Desktop.getDesktop().open(mp3File);
			String imageAlt = Files.exists(Paths.get(song.getImage())) ? song.getImage() : Database.ASSETS_DEFAULT_IMG;
			Desktop.getDesktop().open(Paths.get(imageAlt).toFile());

		} catch (Exception e) {
			log(Icon.ERROR, "Could not play song");
			System.exit(0);
		}

	}

	private void log(Icon icon, String message) {
		try {
			PrintStream out = new PrintStream(System.out, true, "UTF-8");
			out.println(icon.code + " " + message);
		} catch (UnsupportedEncodingException e) {
		}
	}

}
