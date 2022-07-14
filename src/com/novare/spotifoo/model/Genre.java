package com.novare.spotifoo.model;

import java.util.HashSet;
import java.util.Set;
import static com.novare.spotifoo.util.Constants.*;

public class Genre {
	private int id;
	private String name;
	private Set<String> songs = new HashSet<>();

	/**
	 * @param name
	 * @param songs
	 */
	public Genre(String name, Set<String> songs) {
		super();
		setId(generId++);
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
	public Set<String> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void setSongs(Set<String> songs) {
		this.songs = songs;
	}

}
