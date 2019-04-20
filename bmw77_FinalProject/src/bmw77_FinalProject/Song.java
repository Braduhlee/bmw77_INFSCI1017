package bmw77_FinalProject;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Maps the song table of the music2019 database to a Java object.
 * @author Brad Wayamn
 */

@Entity
@Table(name = "song")
public class Song {
	
	// Database column names	
	@Transient
	final private String DB_SONG_ID = "song_id";

	@Transient
	final private String DB_TITLE = "title";

	@Transient
	final private String DB_LENGTH = "length";

	@Transient
	final private String DB_FILE_PATH = "file_path";

	@Transient
	final private String DB_RELEASE_DATE = "release_date";

	@Transient
	final private String DB_RECORD_DATE = "record_date";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	// Instance variables
	
	@Column(name = DB_SONG_ID)
	private String songID;
	
	@Column(name = DB_TITLE)
	private String title;

	@Column(name = DB_LENGTH)
	private double length;

	@Column(name = DB_FILE_PATH)
	private String filePath;

	@Column(name = DB_RELEASE_DATE)
	private String releaseDate;

	@Column(name = DB_RECORD_DATE)
	private String recordDate;

	@Transient
	private Map<String, Artist> songArtists;
	
	/**
	 * Constructs a new Song object with a random UUID.
	 */
	public Song() {
		this.songID = UUID.randomUUID().toString();
		this.songArtists = new Hashtable<String, Artist>(); // Initialize the song's list of artists
	}
		
	/**
	 * Adds an artist to the song's list of artists.
	 * @param artist - the artist to add to the song
	 */
	public void addArtist(Artist artist) {
		this.songArtists.put(artist.getArtistID(), artist);
	}
	
	/**
	 * Deletes an artist from the song's list of artists.
	 * @param artistID - the UUID of the artist to delete from the song
	 */
	public void deleteArtist(String artistID) {
		this.songArtists.remove(artistID);
	}
	
	/**
	 * Deletes an artist from the song's list of artists.
	 * @param artist - the artist to delete from the song
	 */
	public void deleteArtist(Artist artist) {
		this.songArtists.remove(artist.getArtistID());
	}
	
	/**
	 * Returns a JSON object representing the Song object.
	 * @return a JSON object representing the song
	 */
	public JSONObject toJSON() {
		JSONObject songJSON = new JSONObject();
		try {
			songJSON.put(DB_SONG_ID, this.songID);
			songJSON.put(DB_TITLE, this.title);
			songJSON.put(DB_LENGTH, this.length);
			songJSON.put(DB_FILE_PATH, this.filePath);
			songJSON.put(DB_RELEASE_DATE, this.releaseDate);
			songJSON.put(DB_RECORD_DATE, this.recordDate);
		// Catch any JSON errors and print their contents
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return songJSON;
	}
	
	
	// Getters

	/**
	 * Retrieves the value of the song's ID.
	 * @return the ID of the song
	 */
	public String getSongID() {
		return this.songID;
	}

	/**
	 * Retrieves the value of the song's title.
	 * @return the title of the song
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Retrieves the value of the song's length.
	 * @return the length of the song
	 */
	public double getLength() {
		return this.length;
	}

	/**
	 * Retrieves the value of the song's file path.
	 * @return the file path of the song
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Retrieves the value of the song's release date.
	 * @return the release date of the song
	 */
	public String getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * Retrieves the value of the song's recording date.
	 * @return the recording date of the song
	 */
	public String getRecordDate() {
		return this.recordDate;
	}

	/**
	 * Retrieves the song's list of artists.
	 * @return the list of artists on the song
	 */
	public Map<String, Artist> getSongArtists() {
		return this.songArtists;
	}
	
	
	// Setters

	/**
	 * Updates the value of the song's title.
	 * @param title - the new title of the song
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Updates the value of the song's length.
	 * @param length - the new length of the song
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Updates the value of the song's file path.
	 * @param filePath - the new file path of the song
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * Updates the value of the song's release date.
	 * @param releaseDate - the new release date of the song
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * Updates the value of the song's recording date.
	 * @param recordDate - the new recording date of the song
	 */
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	
	/**
	 * Updates the song's list of artists.
	 * @param songArtists - the new list of artists on the song
	 */
	public void setSongArtists(Map<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
	
}