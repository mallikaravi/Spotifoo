package com.novare.spotifoo.model;

import java.util.HashSet;
import java.util.Set;
import static com.novare.spotifoo.util.Database.*;

public class Album {
	private Integer id;
	private String name;
	private Set<Song> songs = new HashSet<>();

	/**
	 * @param name
	 * @param songs
	 */
	public Album(String name, Song song) {
		super();
		setId(albumId++);
		this.name = name;
		this.songs.add(song);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
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
