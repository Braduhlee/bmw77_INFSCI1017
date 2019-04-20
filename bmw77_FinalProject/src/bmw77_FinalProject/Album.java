package bmw77_FinalProject;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Album defines the Album object
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
	private double length;
	
	@Transient
	private Map<String, Song> albumSongs;
		
	

	public void addSong(Song song) {
		this.albumSongs.put(song.getSongID(), song);
	}
	
	
	public void deleteSong(String songID) {
		this.albumSongs.remove(songID);
	}
	
	
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

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	
	public Map<String, Song> getAlbumSongs() {
		return albumSongs;
	}
	
	public JSONObject toJSON(){
		JSONObject albumJson = new JSONObject();
		try {
			albumJson.put("album_id", this.albumID);
			albumJson.put("title", this.title);
			albumJson.put("release_date", this.releaseDate);
			albumJson.put("cover_image_path", this.coverImagePath);
			albumJson.put("recording_company_name", this.recordingCompany);
			albumJson.put("number_of_tracks", this.numberOfTracks);
			albumJson.put("PMRC_rating", this.pmrcRating);
			albumJson.put("length", this.length);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return albumJson;
		
	}

}