package com.novare.spotifoo.view;

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
import com.novare.spotifoo.service.SpotifooController;

/**
 * This {@code SpotifooView} is to create the User interface menus based on
 * given desired inputs by user.
 * 
 * @author mallika
 *
 */
public class SpotifooView {
	/**
	 * This enum is used as title for Menus
	 *
	 */
	private enum Title {
		WELCOME("Welcome to the Spotifoo music player!\n\n"), MAIN("Main menu options"), SONG("Song menu"),
		ARTIST("Artist menu"), ALBUM("Album menu"), GENRE("Genre menu"), SEARCH("Search song");

		private final String text;

		private Title(String text) {
			this.text = text;
		}

	}

	/**
	 * Enums to print the warning signs/symbols based on given unicode
	 *
	 */
	private enum Icon {
		WARNING("\u26A0"), PLAY("\u23e9"), ERROR("\u2716"), SEARCH("\u231b");

		private final String code;

		private Icon(String code) {
			this.code = code;
		}

	}

	/**
	 * Initialize the songs database cache and instantiate the menu creation.
	 * 
	 * @param songsDB, Songs metadata file location
	 */
	public void start(String songsDB) {
		SpotifooController.INST.readSongsData(songsDB);
		mainMenu(true);

	}

	/**
	 * Here this method prints the Menu in the terminal.Here I have used boolean
	 * visible to display menu if it satisfies the condition,otherwise it throws an
	 * exception.
	 * 
	 * @param visible
	 */
	private void mainMenu(boolean visible) {
		List<String> mainMenu = Arrays.asList("Songs", "Artists", "Albums", "Genres", "Search");
		displayMenu(mainMenu, Title.MAIN, visible);

		Scanner read = new Scanner(System.in);
		try {
			int choice = readInput(read.nextLine());
			switch (choice) {
			case 1: {
				songsMenu(SpotifooController.INST.getAllSongs(), true);
				break;
			}
			case 2: {
				artistMenu(SpotifooController.INST.getAllArtist(), true);
				break;
			}
			case 3: {
				albumMenu(SpotifooController.INST.getAllAlbums(), true);
				break;
			}
			case 4: {
				genreMenu(SpotifooController.INST.getAllGenre(), true);
				break;
			}
			case 5: {
				searchMenu();
				break;
			}
			default:
				throw new IllegalArgumentException("Not a valid  option");
			}
		} catch (IllegalArgumentException e) {
			log(Icon.WARNING, e.getMessage());
			mainMenu(false);
		} finally {
			read.close();

		}

	}

	/**
	 * This method is used to display all the songs available:here I have taken the
	 * songs in a list which have sequence order.The user can access elements by the
	 * index of the song.
	 * 
	 * @param allSongs
	 * @param visible
	 */
	private void songsMenu(List<Song> allSongs, boolean visible) {
		Collections.sort(allSongs);
		displayMenu(allSongs, Title.SONG, visible);

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
				log(Icon.WARNING, "Not a valid option");
				songsMenu(allSongs, false);
			}
		}
		read.close();
	}

	/**
	 * This method is used to display all the album available.Here I have used list
	 * for the ordered collection of Albums.The user can access or search for the
	 * albums according to their index.
	 * 
	 * @param allAlbum
	 * @param visible
	 */
	private void albumMenu(List<Album> allAlbums, boolean visible) {
		Collections.sort(allAlbums);
		displayMenu(allAlbums, Title.ALBUM, visible);

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
				log(Icon.WARNING, "Not a valid option");
				albumMenu(allAlbums, false);

			}
		}
		read.close();
	}

	/**
	 * This method is used to display all the artist available.Here I have used list
	 * for the ordered collection of Artist.The user can access or search for the
	 * artist according to their index.
	 * 
	 * @param allArtist
	 * @param visible
	 */
	private void artistMenu(List<Artist> allArtist, boolean visible) {
		Collections.sort(allArtist);
		displayMenu(allArtist, Title.ARTIST, visible);

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
				log(Icon.WARNING, "Not a valid option");
				artistMenu(allArtist, false);
			}
		}
		read.close();
	}

	/**
	 * Here this method is used to display all genre available.The genre are taken
	 * in a list which is an ordered collection.The User can select the genre
	 * according to their index position
	 * 
	 * @param allGenres
	 * @param visible
	 */
	private void genreMenu(List<Genre> allGenres, boolean visible) {
		Collections.sort(allGenres);
		displayMenu(allGenres, Title.GENRE, visible);

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
				log(Icon.WARNING, "Not a valid option");
				genreMenu(allGenres, false);
			}
		}
		read.close();
	}

	/**
	 * This method is used to search for a particular song in the application.The
	 * user can search for a song by name of the song
	 * 
	 */
	private void searchMenu() {

		clearScreen();
		System.out.println(Title.WELCOME.text);
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
			List<Song> songResult = SpotifooController.INST.searchSongs(choice);
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

	/**
	 * 
	 * This is the displayMenu method which is used to display menu items in the
	 * terminal.Here the menuItems are taken in a list.I have also passed boolean to
	 * check for the condition.If it satisfies the condition,the menu is visible
	 * otherwise it throws an exception.
	 * 
	 * @param menuItems
	 * @param menuTitle
	 * @param visibleMenu
	 */
	private void displayMenu(List<?> menuItems, Title menuTitle, boolean visibleMenu) {
		// Invalid case
		if (visibleMenu) {
			clearScreen();
			System.out.println(Title.WELCOME.text);
			System.out.println(menuTitle.text);
			for (int i = 0; i < menuItems.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + menuItems.get(i));
			}
			if (!menuTitle.equals(Title.MAIN)) {
				System.out.println("[0] Back to main menu");
			}
		}
		System.out.print("Choose an option and press enter:");
	}

	/**
	 * Method to clear screen in the terminal.I have written a condition to clear
	 * the screen in all the operating systems.
	 * 
	 */
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

	/**
	 * This method is for parse and convert the input string into Integer value,
	 * otherwise it will return -1 which is invalid option
	 * 
	 * @param userInput, user entered value in the terminal
	 * @return, integer value
	 */
	private int readInput(String userInput) {
		try {
			return Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * method to play song in the windows media player.It gives an exception if
	 * there is no song in the assets folder. Here I have used Desktop class to play
	 * a song.
	 * 
	 * @param song
	 */
	private void play(Song song) {
		try {
			File mp3File = Paths.get(song.getFileName()).toFile();
			if (!mp3File.exists() || !mp3File.getName().endsWith("mp3")) {
				throw new IllegalArgumentException();
			}
			Desktop.getDesktop().open(mp3File);
			String imageAlt = Files.exists(Paths.get(song.getImage())) ? song.getImage()
					: SpotifooController.ASSETS_DEFAULT_IMG;
			Desktop.getDesktop().open(Paths.get(imageAlt).toFile());

		} catch (Exception e) {
			log(Icon.ERROR, "Could not play song");
			System.exit(0);
		}

	}

	/**
	 * Here message is displayed with symbols in desired format.I have used enums
	 * for printing various messages including signs.
	 * 
	 * @param icon
	 * @param message
	 */
	private void log(Icon icon, String message) {
		try {
			PrintStream out = new PrintStream(System.out, true, "UTF-8");
			out.println(icon.code + " " + message);
		} catch (UnsupportedEncodingException e) {
		}
	}

}
