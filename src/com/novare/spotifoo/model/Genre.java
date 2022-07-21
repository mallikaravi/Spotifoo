package com.novare.spotifoo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@code genre} is mainly for holding information about genre.It implements comparable interface because,it
 * provides ordering of the data for objects of the user defined class.
 * 
 * @author mallika
 *
 */
public class Genre implements Comparable<Genre> {
	private String name;
	private List<Song> songs = new ArrayList<>();

	/**
	 * This is a genre constructor in which the name and the songs are initialized
	 * 
	 * @param name,   Name of the Song
	 * @param song,  Name of the genre
	 */
	public Genre(String name, Song song) {
		super();
		this.name = name;
		this.songs.add(song);
	}

	/**
	 * getter method for name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter method for setting the name
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getSongs  method for getting the songs
	 * @return the songs
	 */
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * addSongs method to add the song
	 * @param song, the songs to set
	 */
	public void addSong(Song song) {
		this.songs.add(song);
	}

	/**
	 * This is the method of Object class. This method is used to compare the given
	 * objects.Here I have used instance of operator to test whether the object is
	 * an instance of specified type.
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String name = (String) obj;
			return this.name.equals(name);
		}
		Genre genre = (Genre) obj;
		return this.getName().equals(genre.getName());
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * This method is used to compare the current object with the given object(i.e genre)
	 */
	@Override
	public int compareTo(Genre o) {
		return this.getName().compareTo(o.getName());
	}

}
