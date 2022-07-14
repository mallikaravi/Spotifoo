package com.novare.spotifoo.model;

import static com.novare.spotifoo.util.Database.INST;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	private Integer id;
	private String name;
	private List<Song> songs = new ArrayList<>();

	/**
	 * @param name
	 * @param songs
	 */
	public Genre(String name, Song song) {
		super();
		setId(INST.generateGenreId());
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
	public List<Song> getSongs() {
		return songs;
	}

	/**
	 * @param songs the songs to set
	 */
	public void addSong(Song song) {
		this.songs.add(song);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String name = (String) obj;
			return this.name.equals(name);
		}
		Genre genre = (Genre) obj;
		return this.id == genre.id;
	}

	@Override
	public String toString() {
		return "[" + id + "] " + name;
	}

}
