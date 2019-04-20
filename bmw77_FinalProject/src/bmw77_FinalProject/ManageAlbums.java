package bmw77_FinalProject;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

/**
 * Manages CRUD operations for Album objects.
 * @author Brad Wayman
 * @version 1.1
 */
public class ManageAlbums {
	
	final private String PERSISTENCE_UNIT = "bmw77_FinalProject"; // The name of the persistence unit
	
	// Search type keywords
	final private String TYPE_EQUALS = "equals";
	final private String TYPE_BEGINS = "begins";
	final private String TYPE_ENDS = "ends";
	
	/**
	 * Creates a new album and adds it to the persistence context.
	 * @param title - the title of the album
	 * @param releaseDate - the release date of the album
	 * @param recordingCompany - the recording company of the album
	 * @param numberOfTracks - the number of tracks on the album
	 * @param pmrcRating - the PMRC rating of the album
	 * @param length - the length of the album
	 */
	public void createAlbum(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
        // Create a new album and set its properties
		Album b = new Album();
		
		b.setTitle(title);
		b.setReleaseDate(releaseDate);
		b.setRecordingCompany(recordingCompany);
		b.setNumberOfTracks(numberOfTracks);
		b.setPmrcRating(pmrcRating);
		b.setLength(length);
		
		// Add the album to the ORM object grid
		em.persist(b);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Updates an album in the persistence context. Ignores blank strings and numbers less than or equal to zero.
	 * @param albumID - the UUID of the album
	 * @param title - the new title of the album
	 * @param releaseDate - the new release date of the album
	 * @param coverImagePath - the new cover image path of the album
	 * @param recordingCompany - the new recording company of the album
	 * @param numberOfTracks - the new number of tracks on the album
	 * @param pmrcRating - the new PMRC rating of the album
	 * @param length - the new length of the album
	 */
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
		// Find the album object in the ORM object grid
		Album b = em.find(Album.class, albumID);
		
		// Update properties that aren't blank
		if (!title.isEmpty()) {
			b.setTitle(title);
		}
		
		if (!releaseDate.isEmpty()) {
			b.setReleaseDate(releaseDate);
		}
		
		if (!coverImagePath.isEmpty()) {
			b.setCoverImagePath(coverImagePath);
		}
		
		if (!recordingCompany.isEmpty()) {
			b.setRecordingCompany(recordingCompany);
		}
		
		if (numberOfTracks > 0) {
			b.setNumberOfTracks(numberOfTracks);
		}
		
		if (!pmrcRating.isEmpty()) {
			b.setPmrcRating(pmrcRating);
		}
		
		if (length > 0) {
			b.setLength(length);
		}
		
		// Update the album in the ORM object grid
		em.persist(b);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Deletes an album from the persistence context.
	 * @param albumID - the UUID of the album
	 */
	public void deleteAlbum(String albumID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Begin a new transaction
		em.getTransaction().begin();
		
		// Find the album object in the ORM object grid
		Album b = em.find(Album.class, albumID);
		
		// Remove the album from the ORM object grid
		em.remove(b);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Finds an album in the persistence context.
	 * @param albumID - the UUID of the album
	 * @return the album found in the persistence context
	 */
	public Album getAlbum(String albumID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Find the album object in the ORM object grid
		Album b = em.find(Album.class, albumID);
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
		
		// Return the found album
		return b;
	}
	
	/**
	 * Gets a JSONArray of albums matching the specified search parameters, or all if not provided.
	 * @param albumTitle - the title of the album to search for
	 * @param searchType - the type of search to conduct
	 * @return a list of found albums in JSON format
	 */
	public JSONArray getAlbumList(String albumTitle, String searchType) {
	    // Create a connection to the persistence manager
	    EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	    EntityManager em = emFactory.createEntityManager();
	    
	    // Start building the query to the persistence context
	    String queryString = "SELECT b.albumID FROM Album b";

	    // Add the search condition if a title was provided
	    if (!albumTitle.isEmpty()) {
	        // Set the query to use the appropriate search type
	        String condition = " WHERE LOWER(b.title) " + (searchType.equalsIgnoreCase(TYPE_EQUALS) ? "=" : "LIKE") + " LOWER(?1)";
	        queryString += condition;
	    }
	    
	    // Create the query
	    Query query = em.createQuery(queryString);
	    
	    // Insert the title into the query if it was provided
	    if (!albumTitle.isEmpty()) {
	        switch (searchType.toLowerCase()) {
	            case TYPE_EQUALS:
	                query.setParameter(1, albumTitle);
	                break;
	            case TYPE_BEGINS:
	                query.setParameter(1, albumTitle + "%");
	                break;
	            case TYPE_ENDS:
	                query.setParameter(1, "%" + albumTitle);
	                break;
	            default:
	                query.setParameter(1, "%" + albumTitle + "%");
	                break;
	        }
	    }

	    // Search the persistence context for albums matching the search parameters
	    List<String> albumIDs = query.getResultList();
	    
	    // Create a JSON array of all found albums
	    JSONArray albumList = new JSONArray();
	    for(String albumID : albumIDs) {
	        Album s = em.find(Album.class, albumID);
	        albumList.put(s.toJSON());
	    }
	    
	    // Close the connection to the persistence manager
	    em.close();
	    emFactory.close();
	    
	    // Return the list of albums
	    return albumList;
	}
	
}