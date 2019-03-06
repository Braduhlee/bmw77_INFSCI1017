package bmw77_Music;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

/**
 * Class Album defines the Album object and includes methods for deleting the album and
 * adding to and removing from the album's song list.
 * @author Brad Wayman
 */

@Entity
@Table (name="album")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "album_id")
	private String albumID;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "release_date")
	private String releaseDate;
	
	@Column(name = "cover_image_path")
	private String coverImagePath;
	
	@Column(name = "recording_company_name")
	private String recordingCompany;
	
	@Column(name = "number_of_tracks")
	private int numberOfTracks;
	
	@Column(name = "PMRC_rating")
	private String pmrcRating;
	
	@Column(name = "length")
	private int length;
	
	@Transient
	private Map<String, Song> albumSongs;
		
	
	/**
	 * This method adds a song to the list of songs associated with the album.
	 * @param song is the object of the song to be added.
	 */
	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
	}
	
	/**
	 * This method removes a song from the list of songs associated with the album.
	 * The method does not remove the song from the database.
	 * @param songID is the identifying UUID of the song to be removed.
	 */
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}
	
	/**
	 * This method also removes a song from the list of songs associated with the album.
	 * The method does not remove the song from the database.
	 * @param song is the object of the song to be removed.
	 */
	public void deleteSong(Song song) {
		this.albumSongs.remove(song.getSongID());
	}

//Getters and Setters
	
	public String getAlbumID() {
		return albumID;
	}

	public void setAlbumID(String albumID) {
		this.albumID = albumID;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	//Skipping setter for albumSongs, since they will be added and removed via the methods above.
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}

}