package com.novare.spotifoo;

import java.awt.Desktop;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.novare.spotifoo.model.Album;
import com.novare.spotifoo.model.Artist;
import com.novare.spotifoo.model.Genre;
import com.novare.spotifoo.model.Song;
import com.novare.spotifoo.util.Database;

public class SpotifooApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Spotifoo music player! \n\n");
		Database.INST.readSongsData("assets/data.txt");
		mainMenu();
	}

	private static void mainMenu() {
		System.out.println("Main menu options:");
		System.out.println("[1] Songs");
		System.out.println("[2] Artists");
		System.out.println("[3] Albums");
		System.out.println("[4] Genres");
		System.out.println("[5] Search");
		System.out.print("Choose an option and press enter:");
//		System.out.println(System.lineSeparator().repeat(20));
//		System.out.flush();

		Scanner read = new Scanner(System.in);
		int choice = 0;
		if (read.hasNextInt()) {
			choice = read.nextInt();
		}
		switch (choice) {
		case 1: {
			songsMenu(Database.INST.getAllSongs());
			break;
		}
		case 2: {
			artistMenu(Database.INST.getAllArtist());
			break;
		}
		case 3: {
			albumMenu(Database.INST.getAllAlbums());
			break;
		}
		case 4: {
			genreMenu(Database.INST.getAllGenre());
			break;
		}
		case 5: {
			searchMenu();
			break;
		}
		default:
			System.out.println("Invalid option");
			mainMenu();
		}
		read.close();
	}

	private static void songsMenu(List<Song> allSongs) {
		System.out.println("Songs menu:");

		display(allSongs);

		Scanner read = new Scanner(System.in);
		int choice = -1;
		if (read.hasNextInt()) {
			choice = read.nextInt();
		}
		if (choice == 0) {
			mainMenu();
		} else if (choice > 0 && choice <= allSongs.size()) {
			Song song = allSongs.get(choice - 1);
			play(song);
			System.out.println("Playing file!");
			System.exit(0);
		} else {
			System.out.println("Invalid option");
			songsMenu(allSongs);
		}
		read.close();
	}

	private static void albumMenu(List<Album> allAlbums) {
		System.out.println("album available:");
		display(allAlbums);

		Scanner read = new Scanner(System.in);
		int choice = -1;
		if (read.hasNextInt()) {
			choice = read.nextInt();
		}
		switch (choice) {
		case 0: {
			mainMenu();
			break;
		}
		default:
			if (choice > 0 && choice <= allAlbums.size()) {
				Album album = allAlbums.get(choice - 1);
				songsMenu(album.getSongs());
			} else {
				System.out.println("Invalid option");
				albumMenu(allAlbums);
			}
		}
		read.close();
	}

	private static void artistMenu(List<Artist> allArtist) {
		System.out.println("artist available:");
		display(allArtist);

		Scanner read = new Scanner(System.in);
		int choice = -1;
		if (read.hasNextInt()) {
			choice = read.nextInt();
		}
		switch (choice) {
		case 0: {
			mainMenu();
			break;
		}
		default:
			if (choice > 0 && choice <= allArtist.size()) {
				Artist artist = allArtist.get(choice - 1);
				songsMenu(artist.getSongs());
			} else {
				System.out.println("Invalid option");
				artistMenu(allArtist);
			}
		}
		read.close();
	}

	private static void genreMenu(List<Genre> allGenres) {
		System.out.println("genre available:");
		display(allGenres);

		Scanner read = new Scanner(System.in);
		int choice = -1;
		if (read.hasNextInt()) {
			choice = read.nextInt();
		}
		switch (choice) {
		case 0: {
			mainMenu();
			break;
		}
		default:
			if (choice > 0 && choice <= allGenres.size()) {
				Genre genre = allGenres.get(choice - 1);
				songsMenu(genre.getSongs());
			} else {
				System.out.println("Invalid option");
				genreMenu(allGenres);
			}
		}
		read.close();
	}

	private static void searchMenu() {

	}

	private static void display(List<?> objects) {
		for (int i = 0; i < objects.size(); i++) {
			System.out.println("[" + (i + 1) + "] " + objects.get(i));
		}
		System.out.println("[0] Back to main menu");
		System.out.print("Choose an option and press enter:");
	}

	private static void play(Song song) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().open(Paths.get(song.getFileName()).toFile());
				String imageAlt = Files.exists(Paths.get(song.getImage())) ? song.getImage()
						: Database.ASSETS_DEFAULT_IMG;
				Desktop.getDesktop().open(Paths.get(imageAlt).toFile());

			} catch (Exception e) {
				System.out.println("Could not play song");
				System.exit(0);
			}

		}

	}
}
