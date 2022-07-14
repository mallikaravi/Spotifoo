package com.novare.spotifoo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.novare.spotifoo.model.Album;
import com.novare.spotifoo.model.Artist;
import com.novare.spotifoo.model.Genre;
import com.novare.spotifoo.model.Song;

public class Database {
	private Integer songId = 1;
	private Integer albumId = 1;
	private Integer artistId = 1;
	private Integer genreId = 1;

	private List<Song> songs;
	private List<Artist> artists;
	private List<Genre> genres;
	private List<Album> albums;

	private Map<Integer, Set<Song>> albumBySongs;
	private Map<Integer, Set<Song>> artistBySongs;
	private Map<Integer, Set<Song>> genreBySongs;

	public static final Database INST = new Database();

	private Database() {
		songs = new ArrayList<Song>();
		artists = new ArrayList<Artist>();
		genres = new ArrayList<Genre>();
		albums = new ArrayList<Album>();

		albumBySongs = new HashMap<Integer, Set<Song>>();
		artistBySongs = new HashMap<Integer, Set<Song>>();
		genreBySongs = new HashMap<Integer, Set<Song>>();

	}

	public void readSongsData(String fileName) {
		try {
			List<String> allLines = Files.readAllLines(Paths.get(fileName));
			for (String line : allLines) {
				String[] data = line.split(",");
				try {
					System.out.println(createSong(data[0], data[5], data[4], data[1], data[2], data[3]));
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Wrong data formate: " + line);
					continue;
				}
			}
		} catch (IOException e) {
			System.out.println("Error in reading file. ");
		}
		System.out.println(artists);
		System.out.println(genres);
		System.out.println(genreBySongs);

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
		}
		mapSongToAlbum(song, album);
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
		}
		mapSongToArtist(song, artist);
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
		}
		mapSongToGenre(song, genre);
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

	private void mapSongToArtist(Song song, Artist artist) {
		if (artistBySongs.containsKey(artist.getId())) {
			Set<Song> songs = artistBySongs.get(artist.getId());
			songs.add(song);
		} else {
			Set<Song> songs = new HashSet<>();
			songs.add(song);
			artistBySongs.put(artist.getId(), songs);
		}

	}

	private void mapSongToAlbum(Song song, Album album) {
		if (albumBySongs.containsKey(album.getId())) {
			Set<Song> songs = albumBySongs.get(album.getId());
			songs.add(song);
		} else {
			Set<Song> songs = new HashSet<>();
			songs.add(song);
			albumBySongs.put(album.getId(), songs);

		}
	}

	private void mapSongToGenre(Song song, Genre genre) {
		if (genreBySongs.containsKey(genre.getId())) {
			Set<Song> songs = genreBySongs.get(genre.getId());
			songs.add(song);
		} else {
			Set<Song> songs = new HashSet<>();
			songs.add(song);
			genreBySongs.put(genre.getId(), songs);
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
