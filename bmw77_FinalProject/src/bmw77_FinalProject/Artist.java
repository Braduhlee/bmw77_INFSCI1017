package bmw77_FinalProject;

import java.util.UUID;
import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Maps the artist table of the music2019 database to a Java object.
 * @author Brad Wayman
 */

@Entity
@Table(name = "artist")
public class Artist {
	
	// Database column names
	@Transient
	final private String DB_ARTIST_ID = "artist_id";
	
	@Transient
	final private String DB_FIRST_NAME = "first_name";

	@Transient
	final private String DB_LAST_NAME = "last_name";

	@Transient
	final private String DB_BAND_NAME = "band_name";

	@Transient
	final private String DB_BIO = "bio";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	// Instance variables
	@Column(name = DB_ARTIST_ID)
	private String artistID;
	
	@Column(name = DB_FIRST_NAME)
	private String firstName;
	
	@Column(name = DB_LAST_NAME)
	private String lastName;
	
	@Column(name = DB_BAND_NAME)
	private String bandName;
	
	@Column(name = DB_BIO)
	private String bio;
	
	/**
	 * Constructs a new Artist object with a random UUID.
	 */
	public Artist() {
		this.artistID = UUID.randomUUID().toString();
	}
		
	/**
	 * Returns a JSON object representing the Artist object.
	 * @return a JSON object representing the artist
	 */
	public JSONObject toJSON() {
		JSONObject artistJSON = new JSONObject();
		try {
			artistJSON.put(DB_ARTIST_ID, this.artistID);
			artistJSON.put(DB_FIRST_NAME, this.firstName);
			artistJSON.put(DB_LAST_NAME, this.lastName);
			artistJSON.put(DB_BAND_NAME, this.bandName);
			artistJSON.put(DB_BIO, this.bio);
		// Catch any JSON errors and print their contents
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return artistJSON;
	}
	
	
	// Getters
	
    /**
	 * Retrieves the value of the artist's ID.
	 * @return the ID of the artist
	 */
	public String getArtistID() {
		return this.artistID;
	}

	/**
	 * Retrieves the value of the artist's first name.
	 * @return the first name of the artist
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Retrieves the value of the artist's last name.
	 * @return the last name of the artist
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Retrieves the value of the artist's band name.
	 * @return the band name of the artist
	 */
	public String getBandName() {
		return this.bandName;
	}

	/**
	 * Retrieves the value of the artist's biography.
	 * @return the biography of the artist
	 */
	public String getBio() {
		return this.bio;
	}

	
	// Setters

	/**
	 * Updates the value of the artist's first name.
	 * @param firstName - the new first name of the artist
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Updates the value of the artist's last name.
	 * @param lastName - the new last name of the artist
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Updates the value of the artist's band name.
	 * @param bandName - the new band name of the artist
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	/**
	 * Updates the value of the artist's biography.
	 * @param bio - the new biography of the artist
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}
	
}