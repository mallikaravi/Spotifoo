package com.novare.spotifoo.model;

import java.util.HashSet;
import java.util.Set;
import static com.novare.spotifoo.util.Constants.*;

public class Artist {
	private int id;
	private String name;
	private Set<Song> songs = new HashSet<>();

	/**
	 * @param id
	 * @param name
	 * @param songs
	 */
	public Artist(String name, Set<Song> songs) {
		super();
		setId(artistId++);
		this.name = name;
		this.songs = songs;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the songs
	 */
	public Set<Song> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void addSong(Song song) {
		this.songs.add(song);
	}

}
