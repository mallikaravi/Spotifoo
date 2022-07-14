package com.novare.spotifoo.model;

import static com.novare.spotifoo.util.Database.*;

public class Song {
	private Integer id;
	private String name;
	private String icon;
	private String fileName;
	private Artist artist;
	private Album album;
	private Genre genre;

	/**
	 * @param name
	 * @param icon
	 * @param fileName
	 */
	public Song(String name, String icon, String fileName) {
		this(name, icon, fileName, null, null, null);
	}

	/**
	 * @param name
	 * @param icon
	 * @param fileName
	 * @param artist
	 * @param album
	 * @param gener
	 */
	public Song(String name, String icon, String fileName, Artist artist, Album album, Genre genre) {
		super();
		setId(songId++);
		this.name = name;
		this.icon = icon;
		this.fileName = fileName;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * @param gener the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
