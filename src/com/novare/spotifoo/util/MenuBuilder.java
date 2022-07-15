package com.novare.spotifoo.util;

import java.awt.Desktop;
import java.io.File;
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

	public void mainMenu(boolean visible) {
		List<String> mainMenu = Arrays.asList("Songs", "Artists", "Albums", "Genres", "Search");
		display(mainMenu, "Main menu options:", visible);

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
			System.out.println("Invalid option");
			mainMenu(false);
		}
		read.close();
	}

	private void songsMenu(List<Song> allSongs, boolean visible) {
		Collections.sort(allSongs);
		display(allSongs, "Songs menu:", visible);

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
				System.out.println("Playing file!");
				System.exit(0);
			} else {
				System.out.println("Invalid option");
				songsMenu(allSongs, false);
			}
		}
		read.close();
	}

	private void albumMenu(List<Album> allAlbums, boolean visible) {
		Collections.sort(allAlbums);
		display(allAlbums, "album available:", visible);

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
				System.out.println("Invalid option");
				albumMenu(allAlbums, false);
			}
		}
		read.close();
	}

	private void artistMenu(List<Artist> allArtist, boolean visible) {
		Collections.sort(allArtist);
		display(allArtist, "artist available:", visible);

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
				System.out.println("Invalid option");
				artistMenu(allArtist, false);
			}
		}
		read.close();
	}

	private void genreMenu(List<Genre> allGenres, boolean visible) {
		Collections.sort(allGenres);
		display(allGenres, "genre available:", visible);

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
				System.out.println("Invalid option");
				genreMenu(allGenres, false);
			}
		}
		read.close();
	}

	private void searchMenu() {

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
				System.out.println("No results found related to query");
				System.exit(0);
			}
		}
		}
		read.close();
	}

	private void display(List<?> objects, String title, boolean visible) {
		// Invalid case
		if (visible) {
			System.out.println(title);
			for (int i = 0; i < objects.size(); i++) {
				System.out.println("[" + (i + 1) + "] " + objects.get(i));
			}
			System.out.println("[0] Back to main menu");
		}
		System.out.print("Choose an option and press enter:");

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
			System.out.println("Could not play song");
			System.exit(0);
		}

	}

}
