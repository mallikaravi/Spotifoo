package com.novare.spotifoo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This {@code Album} is mainly for holding the information about album .It
 * implements comparable interface,because it provides the ordering of the user
 * defined class
 * 
 * @author mallika
 *
 */
public class Album implements Comparable<Album> {
	private String name;
	private List<Song> songs = new ArrayList<>();

	/**
	 * This is an Album constructor which initializes the name and song.
	 * 
	 * @param name ,Name of the Album
	 * @param song, Name of the Song
	 */
	public Album(String name, Song song) {
		super();
		this.name = name;
		this.songs.add(song);
	}

	/**
	 * Getter method for name
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
	 * getSongs method for songs
	 * 
	 * @return the songs
	 */
	public List<Song> getSongs() {
		return songs;
	}

	
	/**
	 * This method adds songs to the album
	 * @param song, song that belongs to this album
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
		Album album = (Album) obj;
		return this.getName().equals(album.getName());
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * This method compares current object with given object(i.e album)
	 * 
	 */
	@Override
	public int compareTo(Album o) {
		return this.getName().compareTo(o.getName());
	}

}
