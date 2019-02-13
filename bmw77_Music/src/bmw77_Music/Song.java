package bmw77_Music;

	
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.UUID;

	/**
	 * Class Song defines the Song object and includes methods for deleting the song and
	 * adding to and removing from the song's artist list.
	 * @author Brad Wayman
	 */
public class Song {
	
		//Variables for Song
		private String songID;
		private String title;
		private int length;
		private String filePath;
		private String releaseDate;
		private String recordDate;
		private Map<String, Artist> songArtists;

		private DbUtilities db;
		
		/**
		 * The main constructor takes a title, length, release date and record date and builds the song,
		 * as well as an empty list of artists.
		 * @param title is the song's title.
		 * @param length is the length of the song in minutes.
		 * @param releaseDate is the date the song was released.
		 * @param recordDate is the date the song was recorded.
		 */
		public Song(String title, int length, String releaseDate, String recordDate) {
			
			this.songID = UUID.randomUUID().toString();
			this.title = title;
			this.length = length;
			this.releaseDate = releaseDate;
			this.recordDate = recordDate;
			this.songArtists = new HashMap<String, Artist>();
			
			db = new DbUtilities();
			String sql = "INSERT INTO song (song_id, title, length, release_date, record_date) VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '" + this.releaseDate + "', '" + this.recordDate +"');";
			db.executeQuery(sql);
			
		}
		
		/**
		 * This constructor uses a songID to build an object by referencing a song in the database by its UUID.
		 * @param songID is UUID of song.
		 */
		public Song(String songID) {
			
			this.songID = songID;
			this.songArtists = new HashMap<String, Artist>();
			
			db = new DbUtilities();
			String sql = "SELECT title, length, file_path, release_date, record_date FROM song WHERE song_id = '" + this.songID + "';";
			
			try {
				
				ResultSet rs = db.getResultSet(sql);
				if(rs.next()) {
					this.title = rs.getString("title");
					this.length = rs.getInt("length");
					this.filePath = rs.getString("file_path");
					this.releaseDate = rs.getString("release_date");
					this.recordDate = rs.getString("record_date");
					
				}
				
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
			//Populates list of artists by grabbing keys from song_artist table and adding artists to songArtists Map.
			String sql2 = "SELECT fk_artist_id FROM song_artist WHERE fk_song_id = '" + this.songID + "';";
			try {
				ResultSet rs2 = db.getResultSet(sql2);
				//Using while instead of if, since the list may have more than one entry.
				while(rs2.next()) {
					this.songArtists.put(rs2.getString("fk_artist_id"), new Artist(rs2.getString("fk_artist_id")));
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			
		}
		
		/**
		 * This method deletes a song from the database and sets all objects to null so they are gone.
		 * @param songID is UUID of song
		 */
		public void deleteSong(String songID) {
			
			this.songID = songID;
			
			db = new DbUtilities();
		
			String sql = "DELETE FROM album_song WHERE fk_song_id = '" + this.songID + "';";
			db.executeQuery(sql);
			
			this.title = null;
			this.length = 0;
			this.filePath = null;
			this.releaseDate = null;
			this.recordDate = null;
			this.songArtists = null;
			this.songID = null;
			
		}
		
		/**
		 * This method adds an artist to the list of artists associated with the song.
		 * @param artist is the object of the artist to be added.
		 */
		public void addArtist(Artist artist) {
			
			this.songArtists.put(artist.getArtistID(), artist);
			db = new DbUtilities();
			String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES ('" + this.songID + "', '" + artist.getArtistID() + "');";
			db.executeQuery(sql);
			
		}
		
		/**
		 * This method removes an artist from the list of artists associated with the song.
		 * The method does not remove the artist from the database.
		 * @param artistID is the identifying UUID of the artist to be removed.
		 */
		public void deleteArtist(String artistID) {
			
			this.songArtists.remove(artistID);
			db = new DbUtilities();
			//Only deleting from song_artist, not artist, since we're only removing the reference.
			String sql = "DELETE FROM song_artist WHERE fk_song_id = '" + this.songID + "' AND fk_artist_id = '" + artistID + "';";
			db.executeQuery(sql);
			
		}
		
		/**
		 * This method also removes an artist from the list of artists associated with the song.
		 * The method does not remove the artist from the database.
		 * @param artist is the object of the artist to be removed.
		 */
		public void deleteArtist(Artist artist) {
			
			this.songArtists.remove(artist.getArtistID());
			db = new DbUtilities();
			//Only deleting from song_artist, not artist, since we're only removing the reference.
			String sql = "DELETE FROM song_artist WHERE fk_song_id = '" + this.songID + "' AND fk_artist_id = '" + artist.getArtistID() + "';";
			db.executeQuery(sql);	
			
		}
		
	//Getters and Setters

		public String getTitle() {
			
			return title;
			
		}

		public void setTitle(String title) {
			
			this.title = title;
			db = new DbUtilities();
			String sql = "UPDATE song SET title = '" + this.title + "' WHERE song_id = '" + this.songID + "';";
			db.executeQuery(sql);
			
		}

		public int getLength() {
			
			return length;
			
		}

		public void setLength(int length) {
			
			this.length = length;
			db = new DbUtilities();
			String sql = "UPDATE song SET length = " + this.length + " WHERE song_id = '" + this.songID + "';";
			db.executeQuery(sql);
			
		}

		public String getReleaseDate() {
			
			return releaseDate;
			
		}

		public void setReleaseDate(String releaseDate) {
			
			this.releaseDate = releaseDate;
			String sql = "UPDATE song SET release_date = '" + this.releaseDate + "' WHERE song_id = '" + this.songID + "';";
			db.executeQuery(sql);

		}

		public String getRecordDate() {
			
			return recordDate;
			
		}

		public void setRecordDate(String recordDate) {
			
			this.recordDate = recordDate;
			String sql = "UPDATE song SET record_date = '" + this.recordDate + "' WHERE song_id = '" + this.songID + "';";
			db.executeQuery(sql);
			
		}
		
		//Skipping setter for songArtists, since they will be added and removed via the methods above.
		public Map<String, Artist> getSongArtists() {
			
			return songArtists;
			
		}

		public String getSongID() {
			
			return songID;
			
		}

		public String getFilePath() {
			
			return filePath;
			
		}
		
		public void setFilePath(String filePath) {
			
			this.filePath = filePath;
			String sql = "UPDATE song SET file_path = '" + this.filePath + "' WHERE song_id = '" + this.songID + "';";
			db.executeQuery(sql);
			
		}
		
	}