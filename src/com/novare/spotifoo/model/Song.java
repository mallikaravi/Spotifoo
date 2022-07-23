package com.novare.spotifoo.model;

import java.io.File;

import com.novare.spotifoo.service.SpotifooController;

/**
 * This {@code Song} is mainly for holding the information about song name,
 * artist, album, genre etc. and It  implements comparable interface
 * because,it provides ordering of the data for objects of the user defined
 * class.
 * 
 * @author mallika
 *
 */
public class Song implements Comparable<Song> {
	private String name;
	private String image;
	private String fileName;
	private Artist artist;
	private Album album;
	private Genre genre;

	/**
	 * This construct for initializing the basic parameters like name, image and
	 * fileName.
	 * 
	 * @param name,     Name of the song
	 * @param image,    Image that shows while playing the song
	 * @param fileName, song location in the form of mp3.
	 */
	public Song(String name, String image, String fileName) {
		this(name, image, fileName, null, null, null);
	}

	/**
	 * This is a Song constructor which have parameters
	 * name,image,fileName,artist,album and genre.They are initialized.
	 * 
	 * @param name,  Name of the song
	 * @param image,    Image that shows while playing the song
	 * @param fileName, Location of the song
	 * @param artist,   Name of the artist
	 * @param album,    Name of the album
	 * @param genre,    Name of the genre
	 */
	public Song(String name, String image, String fileName, Artist artist, Album album, Genre genre) {
		super();
		this.name = name;
		this.image = SpotifooController.ASSETS_ALBUMS + File.separator + image;
		this.fileName = SpotifooController.ASSETS_SONGS + File.separator + fileName;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
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
	 * Getter method for image
	 * 
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter method for setting the image
	 * 
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Getter method for fileName
	 * 
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Setter method for setting the fileName
	 * 
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Getter method for artist
	 * 
	 * @return the artist
	 */
	public Artist getArtist() {
		return artist;
	}

	/**
	 * Setter method for setting the artist
	 * 
	 * @param artist the artist to set
	 */
	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	/**
	 * Getter method for album
	 * 
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * Setter method for setting the album
	 * 
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
	}

	/**
	 * Getter method for genre
	 * 
	 * @return the genre
	 */
	public Genre getGenre() {
		return genre;
	}

	/**
	 * Setter method for setting the genre
	 * 
	 * @param genre the genre to set
	 */
	public void setGenre(Genre genre) {
		this.genre = genre;
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
		Song song = (Song) obj;
		return this.getName().equals(song.getName());
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * This method compares the current object with given object (i.e. song).
	 *
	 */
	@Override
	public int compareTo(Song o) {
		return this.getName().compareTo(o.getName());
	}

}
