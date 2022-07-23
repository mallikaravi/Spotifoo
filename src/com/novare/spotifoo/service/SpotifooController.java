package com.novare.spotifoo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.novare.spotifoo.model.Album;
import com.novare.spotifoo.model.Artist;
import com.novare.spotifoo.model.Genre;
import com.novare.spotifoo.model.Song;

/**
 * The {@code SpotifooController} class is mainly for caching the songs data in
 * memory. It is implemented using singleton design pattern, it shares the
 * cached song data to other classes
 * 
 * @author mallika
 *
 */
public class SpotifooController {
	/**
	 * Shared string constant used for the songs location
	 */
	public static final String ASSETS_SONGS = "assets/songs";
	/**
	 * Shared string constant used for the songs albums location
	 */
	public static final String ASSETS_ALBUMS = "assets/albums";
	/**
	 * Shared string constant used for the songs default image location, if there is
	 * no image for the song.
	 */
	public static final String ASSETS_DEFAULT_IMG = "assets/no-picture.png";

	/**
	 * It holds the songs models which is used in displaying the menu and caches
	 * in-memory.
	 */
	private final List<Song> songs;
	/**
	 * It holds the songs artist models which is used in displaying the menu and
	 * caches in-memory.
	 */
	private final List<Artist> artists;
	/**
	 * It holds the songs genre models which is used in displaying the menu and
	 * caches in-memory.
	 */
	private final List<Genre> genres;
	/**
	 * It holds the songs album models which is used in displaying the menu and
	 * caches in-memory.
	 */
	private final List<Album> albums;

	/**
	 * This is a {@code SpotifooController} object constant which is designed based
	 * on singleton design pattern and used it for accessing the class methods.
	 */
	public static final SpotifooController INST = new SpotifooController();

	/**
	 * Default restricted constructor which is not accessible to outside class and
	 * main purpose is to avoid the object creation.
	 */
	private SpotifooController() {
		songs = new ArrayList<Song>();
		artists = new ArrayList<Artist>();
		genres = new ArrayList<Genre>();
		albums = new ArrayList<Album>();
	}

	/**
	 * Reads the songs data from the given file line by line, parse the line and
	 * builds the {@code Song, Album, Artist and Genre} models.
	 * 
	 * @param fileName, Name of the file
	 */
	public void readSongsData(String fileName) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			for (String line : allLines) {
				String[] data = line.split(",");
				try {
					createSong(data[0], data[5], data[4], data[1], data[2], data[3]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Wrong data format: " + line);
					continue;
				}
			}
		} catch (IOException e) {
			System.out.println("Error in reading file. ");
		}
	}

	/**
	 * This method used to create song model by given input data.
	 * 
	 * @param name,     Name of the song
	 * @param image,    Image that shows while playing the song
	 * @param fileName, Location of the song
	 * @param artist,   Name of the artist
	 * @param album,    Name of the album
	 * @param genre,    Name of the genre
	 * @return
	 */
	private Song createSong(String name, String image, String fileName, String artist, String album, String genre) {
		Song song = new Song(name, image, fileName);
		song.setAlbum(createAlbum(album, song));
		song.setArtist(createArtist(artist, song));
		song.setGenre(createGenre(genre, song));
		songs.add(song);
		return song;
	}

	/**
	 * This method is to create an album according its name and song.
	 * 
	 * @param name  , Name of the Album
	 * @param song, Name of the song
	 * @return
	 */
	private Album createAlbum(String name, Song song) {
		Album album = findAlbumByName(name);
		if (album == null) {
			album = new Album(name, song);
			albums.add(album);
		} else {
			album.addSong(song);
		}
		return album;
	}

	/**
	 * Finds the albums from existing albums cache database by albumName, so that it
	 * will maintain the uniqueness
	 * 
	 * @param albumName, Name of the Album
	 * @return
	 */
	private Album findAlbumByName(String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName)) {
				return album;
			}
		}
		return null;
	}

	/**
	 * This method is to create an artist by its name and song.
	 * 
	 * @param name, Name of the Artist
	 * @param song, Name of the song
	 * @return
	 */
	private Artist createArtist(String name, Song song) {
		Artist artist = findArtistByName(name);
		if (artist == null) {
			artist = new Artist(name, song);
			artists.add(artist);
		} else {
			artist.addSong(song);
		}
		return artist;
	}

	/**
	 * Finds the artists from existing albums cache database by artistName, so that
	 * it will maintain the uniqueness
	 * 
	 * @param artistName, Name of the Artist
	 * @return
	 */
	private Artist findArtistByName(String artistName) {
		for (Artist artist : artists) {
			if (artist.getName().equals(artistName)) {
				return artist;
			}
		}
		return null;
	}

	/**
	 * This method is to create a genre by its name and song
	 *
	 * @param name, Name of the Genre
	 * @param song, Name of the song
	 * @return
	 */
	private Genre createGenre(String name, Song song) {
		Genre genre = findGenreByName(name);
		if (genre == null) {
			genre = new Genre(name, song);
			genres.add(genre);
		} else {
			genre.addSong(song);
		}
		return genre;
	}

	/**
	 * Finds the genres from existing genre cache database by genreName,so that it
	 * will maintain the uniqueness
	 * 
	 * @param genreName, Name of the genre
	 * @return
	 */
	private Genre findGenreByName(String genreName) {
		for (Genre genre : genres) {
			if (genre.getName().equals(genreName)) {
				return genre;
			}
		}
		return null;
	}

	/**
	 * Returns all the songs which are cached from the songs data.
	 * 
	 * @return, returns the songs cache.
	 */
	public List<Song> getAllSongs() {
		return songs;
	}

	/**
	 * Returns all the albums which are cached from the albums data. @return,
	 * returns the albums cache.
	 */
	public List<Album> getAllAlbums() {
		return albums;
	}

	/**
	 * Returns all the artists which are cached from the artist data. @return,
	 * returns the artist cache.
	 */
	public List<Artist> getAllArtist() {
		return artists;
	}

	/**
	 * Returns all the genres which are cached from the genre data. @return, returns
	 * the genre cache.
	 */
	public List<Genre> getAllGenre() {
		return genres;
	}

	/**
	 * This search method is used to search for a particular song by given search
	 * word.Here it searches the song by the name and if it finds the song in the
	 * cache list,then song will be added into result list which returns.
	 * 
	 * @param searchWord, user entered value @return, returns the song results that
	 *                    contains the name (e.g <strong>like</strong> operator in
	 *                    SQL).
	 */
	public List<Song> searchSongs(String searchWord) {
		List<Song> songsResult = new ArrayList<Song>();
		for (Song song : songs) {
			if (song.getName().toLowerCase().contains(searchWord.toLowerCase())) {
				songsResult.add(song);
			}
		}
		return songsResult;
	}

	
	
	public List<Artist> searchArtist(String searchWord) {
		List<Artist> artistResult = new ArrayList<Artist>();
		for (Artist artist : artists) {
			if (artist.getName().toLowerCase().contains(searchWord.toLowerCase())) {
				artistResult.add(artist);
			}
		}
		return artistResult;

	}

	public List<Album> searchAlbum(String searchWord) {
		List<Album> albumResult = new ArrayList<Album>();
		for (Album album : albums) {
			if (album.getName().toLowerCase().contains(searchWord.toLowerCase())) {
				albumResult.add(album);
			}
		}
		return albumResult;

	}

	public List<Genre> searchGenre(String searchWord) {
		List<Genre> genreResult = new ArrayList<Genre>();
		for (Genre genre : genres) {
			if (genre.getName().toLowerCase().contains(searchWord.toLowerCase())) {
				genreResult.add(genre);
			}
		}
		return genreResult;

	}

}
