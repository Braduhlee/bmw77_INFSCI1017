package bmw77_Music;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Class Artist defines the Artist object and includes a method for deleting the artist.
 * @author Brad Wayman
 */
public class Artist {
	//Variables for Artist
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	
	private DbUtilities db;
	
	/**
	 * The main constructor takes the artist's first and last name and the band's name and builds
	 * the Artist object.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 */
	public Artist(String firstName, String lastName, String bandName) {
		
		this.artistID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		
		db = new DbUtilities();
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, this.artistID);
			statement.setString(2, this.firstName);
			statement.setString(3, this.lastName);
			statement.setString(4, this.bandName);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This secondary constructor uses an artistID to build an object by referencing an artist in the database.
	 * @param artistID is the artist's identifying UUID.
	 */
	public Artist(String artistID) {
		
		this.artistID = artistID;
		
		db = new DbUtilities();
		String sql = "SELECT first_name, last_name, band_name, bio FROM artist WHERE artist_id = ?;";
		
		try {
			
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, this.artistID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method deletes an artist from the database and destroys the Java object.
	 * @param artistID is the artist's identifying UUID.
	 */
	public void deleteArtist(String artistID) {
		
		this.artistID = artistID;
		
		db = new DbUtilities();
	
		
		String sql = "DELETE FROM artist WHERE artist_id = ?;";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, this.artistID);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		
		//Setting fields to null to destroy object.
		this.firstName = null;
		this.lastName = null;
		this.bandName = null;
		this.bio = null;
		this.artistID = null;
		
	}
	
//Getters and Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
		String sql = "UPDATE artist SET first_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, firstName);
			statement.setString(2, this.artistID);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getLastName() {
		
		return lastName;
		
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
		String sql = "UPDATE artist SET last_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, lastName);
			statement.setString(2, this.artistID);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public String getBandName() {
		
		return bandName;
		
	}

	public void setBandName(String bandName) {
		
		this.bandName = bandName;
		String sql = "UPDATE artist SET band_name = ? WHERE artist_id = ?;";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, bandName);
			statement.setString(2, this.artistID);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getBio() {
		
		return bio;
		
	}

	public void setBio(String bio) {
		
		this.bio = bio;
		String sql = "UPDATE artist SET bio = ? WHERE artist_id = ?;";
		try {
			PreparedStatement statement = db.getConn().prepareStatement(sql);
			statement.setString(1, bio);
			statement.setString(2, this.artistID);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	public String getArtistID() {
		
		return artistID;
		
	}

}