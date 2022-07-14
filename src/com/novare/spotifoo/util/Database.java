package com.novare.spotifoo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.novare.spotifoo.model.Album;
import com.novare.spotifoo.model.Artist;
import com.novare.spotifoo.model.Genre;
import com.novare.spotifoo.model.Song;

public class Database {
	public static final String ASSETS_SONGS = "assets/songs";
	public static final String ASSETS_ALBUMS = "assets/albums";
	public static final String ASSETS_DEFAULT_IMG = "assets/no-picture.png";

	private Integer songId = 1;
	private Integer albumId = 1;
	private Integer artistId = 1;
	private Integer genreId = 1;

	private final List<Song> songs;
	private final List<Artist> artists;
	private final List<Genre> genres;
	private final List<Album> albums;

	public static final Database INST = new Database();

	private Database() {
		songs = new ArrayList<Song>();
		artists = new ArrayList<Artist>();
		genres = new ArrayList<Genre>();
		albums = new ArrayList<Album>();
	}

	public void readSongsData(String fileName) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			for (String line : allLines) {
				String[] data = line.split(",");
				try {
					createSong(data[0], data[5], data[4], data[1], data[2], data[3]);
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Wrong data formate: " + line);
					continue;
				}
			}
		} catch (IOException e) {
			System.out.println("Error in reading file. ");
		}
	}

	private Song createSong(String name, String icon, String fileName, String artist, String album, String genre) {
		Song song = new Song(name, icon, fileName);
		song.setAlbum(createAlbum(album, song));
		song.setArtist(createArtist(artist, song));
		song.setGenre(createGenre(genre, song));
		songs.add(song);
		return song;
	}

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

	private Album findAlbumByName(String albumName) {
		for (Album album : albums) {
			if (album.getName().equals(albumName)) {
				return album;
			}
		}
		return null;
	}

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

	private Artist findArtistByName(String artistName) {
		for (Artist artist : artists) {
			if (artist.getName().equals(artistName)) {
				return artist;
			}
		}
		return null;
	}

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

	private Genre findGenreByName(String genreName) {
		for (Genre genre : genres) {
			if (genre.getName().equals(genreName)) {
				return genre;
			}
		}
		return null;
	}

	public List<Song> getAllSongs() {
		return songs;
	}

	public List<Album> getAllAlbums() {
		return albums;
	}

	public List<Artist> getAllArtist() {
		return artists;
	}

	public List<Genre> getAllGenre() {
		return genres;
	}

	public void displaySongs() {
		for (Song song : songs) {
			System.out.println(song);
		}
	}

	public void displayAlbums() {
		for (Album album : albums) {
			System.out.println(album);
		}
	}

	public void displayGenre() {
		for (Genre genre : genres) {
			System.out.println(genre);
		}
	}

	public void displayArtist() {
		for (Artist artist : artists) {
			System.out.println(artist);
		}
	}

	public Integer generateSongId() {
		return songId++;
	}

	public Integer generateAlbumId() {
		return albumId++;
	}

	public Integer generateArtistId() {
		return artistId++;
	}

	public Integer generateGenreId() {
		return genreId++;
	}

}
