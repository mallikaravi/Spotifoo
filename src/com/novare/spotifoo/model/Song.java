package com.novare.spotifoo.model;

import java.io.File;

import com.novare.spotifoo.util.Database;

public class Song implements Comparable<Song> {
	private String name;
	private String image;
	private String fileName;
	private Artist artist;
	private Album album;
	private Genre genre;

	/**
	 * @param name
	 * @param image
	 * @param fileName
	 */
	public Song(String name, String image, String fileName) {
		this(name, image, fileName, null, null, null);
	}

	/**
	 * @param name
	 * @param image
	 * @param fileName
	 * @param artist
	 * @param album
	 * @param gener
	 */
	public Song(String name, String image, String fileName, Artist artist, Album album, Genre genre) {
		super();
		this.name = name;
		this.image = Database.ASSETS_ALBUMS + File.separator + image;
		this.fileName = Database.ASSETS_SONGS + File.separator + fileName;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
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
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the icon to set
	 */
	public void setImage(String image) {
		this.image = image;
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String name = (String) obj;
			return this.name.equals(name);
		}
		Song song = (Song) obj;
		return this.getName().equals(song.getName());
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Song o) {
		return this.getName().compareTo(o.getName());
	}

}
