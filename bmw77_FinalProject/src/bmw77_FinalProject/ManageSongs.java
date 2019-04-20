package bmw77_FinalProject;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

/**
 * Manages CRUD operations for Song objects.
 * @author Brad Wayman
 * @version 1.1
 */
public class ManageSongs {
	
	final private String PERSISTENCE_UNIT = "bmw77_FinalProject"; // The name of the persistence unit
	
	// Search type keywords
	final private String TYPE_EQUALS = "equals";
	final private String TYPE_BEGINS = "begins";
	final private String TYPE_ENDS = "ends";
	
	/**
	 * Creates a new song and adds it to the persistence context.
	 * @param title - the title of the song
	 * @param length - the length of the song
	 * @param releaseDate - the release date of the song
	 * @param recordDate - the recording date of the song
	 */
	public void createSong(String title, double length, String releaseDate, String recordDate) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
		// Create a new song and set its properties
		Song s = new Song();
		
		s.setTitle(title);
		s.setLength(length);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		
		// Add the song to the ORM object grid
		em.persist(s);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Updates a song in the persistence context. Ignores blank strings and numbers less than or equal to zero.
	 * @param songID - the UUID of the song
	 * @param title - the new title of the song
	 * @param length - the new length of the song
	 * @param filePath - the new file path of the song
	 * @param releaseDate - the new release date of the song
	 * @param recordDate - the new recording date of the song
	 */
	public void updateSong(String songID, String title, double length, String filePath, String releaseDate, String recordDate) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
        // Find the song object in the ORM object grid
		Song s = em.find(Song.class, songID);
		
		// Update properties that aren't blank
		if (!title.isEmpty()) {
			s.setTitle(title);
		}
		
		if (length > 0) {
			s.setLength(length);
		}
		
		if (!filePath.isEmpty()) {
			s.setFilePath(filePath);
		}
		
		if (!releaseDate.isEmpty()) {
			s.setReleaseDate(releaseDate);
		}
		
		if (!recordDate.isEmpty()) {
			s.setRecordDate(recordDate);
		}
		
		// Update the song in the ORM object grid
		em.persist(s);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Deletes a song from the persistence context.
	 * @param songID - the UUID of the song
	 */
	public void deleteSong(String songID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Begin a new transaction
		em.getTransaction().begin();
		
		// Find the song object in the ORM object grid
		Song s = em.find(Song.class, songID);
		
		// Remove the song from the ORM object grid
		em.remove(s);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Gets a song in the persistence context.
	 * @param songID - the UUID of the song
	 * @return the song found in the persistence context
	 */
	public Song getSong(String songID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
        // Find the song object in the ORM object grid
		Song s = em.find(Song.class, songID);
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
		
		// Return the found song
		return s;
	}
	
	/**
	 * Gets a JSONArray of songs matching the specified search parameters, or all if not provided.
	 * @param songTitle - the title of the song to search for
	 * @param searchType - the type of search to conduct
	 * @return a list of found songs in JSON format
	 */
	public JSONArray getSongList(String songTitle, String searchType) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Start building the query to the persistence context
		String queryString = "SELECT s.songID FROM Song s";

		// Add the search condition if a title was provided
		if (!songTitle.isEmpty()) {
			// Set the query to use the appropriate search type
			String condition = " WHERE LOWER(s.title) " + (searchType.equalsIgnoreCase(TYPE_EQUALS) ? "=" : "LIKE") + " LOWER(?1)";
			queryString += condition;
		}
		
		// Create the query
		Query query = em.createQuery(queryString);
		
		// Insert the title into the query if it was provided
		if (!songTitle.isEmpty()) {
			switch (searchType.toLowerCase()) {
				case TYPE_EQUALS:
					query.setParameter(1, songTitle);
					break;
				case TYPE_BEGINS:
					query.setParameter(1, songTitle + "%");
					break;
				case TYPE_ENDS:
					query.setParameter(1, "%" + songTitle);
					break;
				default:
					query.setParameter(1, "%" + songTitle + "%");
					break;
			}
		}

		// Search the persistence context for songs matching search parameters
		List<String> songIDs = query.getResultList();
		
		// Create a JSON array of all found songs
		JSONArray songList = new JSONArray();
		for(String songID : songIDs) {
			Song s = em.find(Song.class, songID);
			songList.put(s.toJSON());
		}
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
		
		// Return the list of songs
		return songList;
	}
	
}