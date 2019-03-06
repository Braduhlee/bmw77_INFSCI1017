package bmw77_Music;

	
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

	/**
	 * Class Song defines the Song object and includes methods for deleting the song and
	 * adding to and removing from the song's artist list.
	 * @author Brad Wayman
	 */
	@Entity
	@Table (name="song")
	public class Song {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		
		@Column(name = "song_id")
		private String songID;
		
		@Column(name = "title")
		private String title;
		
		@Column(name = "length")
		private int length;
		
		@Column(name = "file_path")
		private String filePath;
		
		@Column(name = "release_date")
		private String releaseDate;
		
		@Column(name = "record_date")
		private String recordDate;
		
		@Transient
		private Map<String, Artist> songArtists;	
		
		
		/**
		 * This method adds an artist to the list of artists associated with the song.
		 * @param artist is the object of the artist to be added.
		 */
		public void addArtist(Artist artist) {
			this.songArtists.put(artist.getArtistID(), artist);
		}
		
		/**
		 * This method removes an artist from the list of artists associated with the song.
		 * The method does not remove the artist from the database.
		 * @param artistID is the identifying UUID of the artist to be removed.
		 */
		public void deleteArtist(String artistID) {
			this.songArtists.remove(artistID);
		}
		
		/**
		 * This method also removes an artist from the list of artists associated with the song.
		 * The method does not remove the artist from the database.
		 * @param artist is the object of the artist to be removed.
		 */
		public void deleteArtist(Artist artist) {
			this.songArtists.remove(artist.getArtistID());
		}

	//Getters and Setters
		
		public String getSongID() {
			return songID;
		}

		public void setSongID(String songID) {
			this.songID = songID;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public String getReleaseDate() {
			return releaseDate;
		}

		public void setReleaseDate(String releaseDate) {
			this.releaseDate = releaseDate;
		}

		public String getRecordDate() {
			return recordDate;
		}

		public void setRecordDate(String recordDate) {
			this.recordDate = recordDate;
		}

		public String getFilePath() {
			return filePath;
		}
		
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
		//Skipping setter for songArtists, since they will be added and removed via the methods above.
		public Map<String, Artist> getSongArtists() {
			return songArtists;
		}
		
	}