package bmw77_Music;


import javax.persistence.*;

/**
 * Class Artist defines the Artist object and includes a method for deleting the artist.
 * @author Brad Wayman
 */
@Entity
@Table (name="artist")
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Column(name = "artist_id")
	private String artistID;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "band_name")
	private String bandName;
	
	@Column(name = "bio")
	private String bio;
	
//Getters and Setters
	public void setArtistID(String artistID) {
		this.artistID = artistID;
	}
	
	public String getArtistID() {
		return artistID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

}