package com.novare.spotifoo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	public static Integer songId = 1;
	public static Integer albumId = 1;
	public static Integer artistId = 1;
	public static Integer genreId = 1;

	public static final Database INST = new Database();

	private Set<Song> songsList;
	private Set<Artist> artistList;
	private Set<Genre> genresList;
	private Set<Album> albumsList;

	private Map<Integer, Set<Song>> albumBySongs ;
	private Map<Integer, Set<Song>> artistBySongs;
	private Map<Integer, Set<Song>> genreBySongs ;

	private Database() {
		songsList = new HashSet<Song>();
		artistList = new HashSet<Artist>();
		genresList = new HashSet<Genre>();
		albumsList = new HashSet<Album>();

		albumBySongs = new HashMap<Integer, Set<Song>>();
		artistBySongs = new HashMap<Integer, Set<Song>>();
		genreBySongs = new HashMap<Integer, Set<Song>>();

	}

	public void readSongsData(String fileName) {
		List<String> allLines;
		try {
			allLines = Files.readAllLines(Paths.get(fileName));
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
	}

	private Song createSong(String name, String icon, String fileName, String artist, String album, String genre) {
		Song song = new Song(name, icon, fileName);
		song.setAlbum(createAlbum(album, song));
		song.setArtist(createArtist(artist, song));
		song.setGenre(createGenre(genre, song));
		songsList.add(song);
		return song;
	}

	private Album createAlbum(String name, Song song) {
		Album album = new Album(name, song);
		albumsList.add(album);
		return album;
	}

	private Artist createArtist(String name, Song song) {
		Artist artist = new Artist(name, song);
		mapSongToArtist(song, artist);
		artistList.add(artist);
		return artist;
	}

	private Genre createGenre(String name, Song song) {
		Genre genre = new Genre(name, song);
		genresList.add(genre);
		return genre;
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
}
