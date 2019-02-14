package bmw77_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Class Album defines the Album object and includes methods for deleting the album and
 * adding to and removing from the album's song list.
 * @author Brad Wayman
 */

public class Album {
	
	// Variables for Album
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	private Map<String, Song> albumSongs;
	
	private DbUtilities db;
	
	/**
	 * This constructor takes the title of the album and its release date, recording company,
	 * number of tracks, PMRC rating and length and builds the album object, along with an empty
	 * list of associated songs.
	 * @param title is the title of the album.
	 * @param releaseDate is the date on which the album was released.
	 * @param recordingCompany is the company that recorded and released the album.
	 * @param numberOfTracks is the number of tracks on the album.
	 * @param pmrcRating is the PMRC's rating for the album.
	 * @param length is the length of the album in minutes.
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		
		this.albumID = UUID.randomUUID().toString();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumSongs = new HashMap<String, Song>();
		
		db = new DbUtilities();
		String sql = "INSERT INTO album (album_id, title, release_date, recording_company_name, number_of_tracks, PMRC_rating, length) VALUES ('" + this.albumID + "', '" + this.title + "', '" + this.releaseDate + "', '" + this.recordingCompany + "', " + this.numberOfTracks + ", '" + this.pmrcRating + "', " + this.length + ");";
		db.executeQuery(sql);
		
	}
	
	/**
	 * This constructor uses an albumID to build an object by referencing an album in the database.
	 * @param albumID is the album's UUID.
	 */
	public Album(String albumID) {
		
		this.albumID = albumID;
		this.albumSongs = new HashMap<String, Song>();
		
		db = new DbUtilities();
		String sql = "SELECT title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album WHERE album_id = '" + this.albumID + "';";
		try {
			ResultSet rs = db.getResultSet(sql);
			if(rs.next()) {
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.coverImagePath = rs.getString("cover_image_path");
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getInt("length");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql2 = "SELECT fk_song_id FROM album_song WHERE fk_album_id = '" + this.albumID + "';";
		try {
			ResultSet rs2 = db.getResultSet(sql2);
			//Using while instead of if, since there may be more than one in the list
			while(rs2.next()) {
				this.albumSongs.put(rs2.getString("fk_song_id"), new Song(rs2.getString("fk_song_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method deletes an album from the database and destroys the Java object.
	 * @param albumID is the album's UUID.
	 */
	public void deleteAlbum(String albumID) {
		
		this.albumID = albumID;
		
		db = new DbUtilities();
		
		String sql = "DELETE FROM album WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
		//Setting fields to null or 0 to destroy object.
		this.title = null;
		this.releaseDate = null;
		this.coverImagePath = null;
		this.recordingCompany = null;
		this.numberOfTracks = 0;
		this.pmrcRating = null;
		this.length = 0;
		this.albumSongs = null;
		this.albumID = null;
		
	}
	
	/**
	 * This method adds a song to the list of songs associated with the album.
	 * @param song is the object of the song to be added.
	 */
	public void addSong(Song song) {
		
		this.albumSongs.put(song.getSongID(), song);
		db = new DbUtilities();
		String sql = "INSERT IGNORE INTO album_song (fk_album_id, fk_song_id) VALUES ('" + this.albumID + "', '" + song.getSongID() + "');";
		db.executeQuery(sql);
		
	}
	
	/**
	 * This method removes a song from the list of songs associated with the album.
	 * The method does not remove the song from the database.
	 * @param songID is the identifying UUID of the song to be removed.
	 */
	public void deleteSong(String songID) {
		
		this.albumSongs.remove(songID);
		db = new DbUtilities();
		//Only deleting from album_song, not song, since we're only removing the reference.
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "' AND fk_song_id = '" + songID + "';";
		db.executeQuery(sql);	
		
	}
	
	/**
	 * This method also removes a song from the list of songs associated with the album.
	 * The method does not remove the song from the database.
	 * @param song is the object of the song to be removed.
	 */
	public void deleteSong(Song song) {
		
		this.albumSongs.remove(song.getSongID());
		db = new DbUtilities();
		//Only deleting from album_song, not song, since we're only removing the reference.
		String sql = "DELETE FROM album_song WHERE fk_album_id = '" + this.albumID + "' AND fk_song_id = '" + song.getSongID() + "';";
		db.executeQuery(sql);	
		
	}

//Getters and Setters

	public String getTitle() {
		
		return title;
		
	}

	public void setTitle(String title) {
		
		this.title = title;
		db = new DbUtilities();
		String sql = "UPDATE album SET title = '" + this.title + "' WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}

	public String getReleaseDate() {
		
		return releaseDate;
		
	}

	public void setReleaseDate(String releaseDate) {
		
		this.releaseDate = releaseDate;
		String sql = "UPDATE album SET release_date = '" + this.releaseDate + "' WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}

	public String getCoverImagePath() {
		
		return coverImagePath;
		
	}

	public void setCoverImagePath(String coverImagePath) {
		
		this.coverImagePath = coverImagePath;
		String sql = "UPDATE album SET cover_image_path = '" + this.coverImagePath + "' WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}

	public String getRecordingCompany() {
		
		return recordingCompany;
		
	}

	public void setRecordingCompany(String recordingCompany) {
		
		this.recordingCompany = recordingCompany;
		String sql = "UPDATE album SET recording_company_name = '" + this.recordingCompany + "' WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}

	public int getNumberOfTracks() {
		
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		
		this.numberOfTracks = numberOfTracks;
		String sql = "UPDATE album SET number_of_tracks = " + this.numberOfTracks + " WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
	}

	public String getPmrcRating() {
		
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		
		this.pmrcRating = pmrcRating;
		String sql = "UPDATE album SET PMRC_rating = '" + this.pmrcRating + "' WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}

	public int getLength() {
		
		return length;
		
	}

	public void setLength(int length) {
		
		this.length = length;
		String sql = "UPDATE album SET length = " + this.length + " WHERE album_id = '" + this.albumID + "';";
		db.executeQuery(sql);
		
	}
	
	//Skipping setter for albumSongs, since they will be added and removed via the methods above.
	public Map<String, Song> getAlbumSongs() {
		
		return albumSongs;
		
	}

	public String getAlbumID() {
		
		return albumID;
		
	}

}
