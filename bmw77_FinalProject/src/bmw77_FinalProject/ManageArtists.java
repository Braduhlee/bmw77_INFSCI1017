package bmw77_FinalProject;

import java.util.List;

import javax.persistence.*;

import org.json.JSONArray;

/**
 * Manages CRUD operations for Artist objects.
 * @author Brad Wayman
 * @version 1.1
 */
public class ManageArtists {
	
	final private String PERSISTENCE_UNIT = "bmw77_FinalProject"; // The name of the persistence unit
	
	// Search type keywords
	final private String TYPE_EQUALS = "equals";
	final private String TYPE_BEGINS = "begins";
	final private String TYPE_ENDS = "ends";
	
	/**
	 * Creates a new artist and adds them to the persistence context.
	 * @param firstName - the first name of the artist
	 * @param lastName - the last name of the artist
	 * @param bandName - the band name of the artist
	 */
	public void createArtist(String firstName, String lastName, String bandName) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
		// Create a new artist and set their properties
		Artist a = new Artist();
		
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		
		// Add the artist to the ORM object grid
		em.persist(a);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Updates an artist in the persistence context. Ignores blank strings.
	 * @param artistID - the UUID of the artist
	 * @param firstName - the new first name of the artist
	 * @param lastName - the new last name of the artist
	 * @param bandName - the new band name of the artist
	 * @param bio - the new biography of the artist
	 */
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();

		// Begin a new transaction
		em.getTransaction().begin();
		
		// Find the artist object in the ORM object grid
		Artist a = em.find(Artist.class, artistID);
		
		// Update properties that aren't blank
		if (!firstName.isEmpty()) {
			a.setFirstName(firstName);
		}
		
		if (!lastName.isEmpty()) {
			a.setLastName(lastName);
		}
		
		if (!bandName.isEmpty()) {
			a.setBandName(bandName);
		}
		
		if (!bio.isEmpty()) {
			a.setBio(bio);
		}
		
		// Update the artist in the ORM object grid
		em.persist(a);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Deletes an artist from the persistence context.
	 * @param artistID - the UUID of the artist
	 */
	public void deleteArtist(String artistID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Begin a new transaction
		em.getTransaction().begin();
		
		// Find the artist object in the ORM object grid
		Artist a = em.find(Artist.class, artistID);
		
		// Remove the artist from the ORM object grid
		em.remove(a);
		
		// Commit the transaction
		em.getTransaction().commit();
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
	}
	
	/**
	 * Gets an artist in the persistence context.
	 * @param artistID - the UUID of the artist
	 * @return the artist found in the persistence context
	 */
	public Artist getArtist(String artistID) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Find the artist object in the ORM object grid
		Artist a = em.find(Artist.class, artistID);
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
		
		// Return the found artist
		return a;
	}
	
	/**
	 * Gets a JSONArray of artists matching the specified search parameters, or all if not provided.
	 * @param artistName - the title of the artist to search for
	 * @param searchType - the type of search to conduct
	 * @return a list of found artists in JSON format
	 */
	public JSONArray getArtistList(String artistName, String searchType) {
		// Create a connection to the persistence manager
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		EntityManager em = emFactory.createEntityManager();
		
		// Start building the query to the persistence context
		String queryString = "SELECT a.artistID FROM Artist a";

		// Add the search condition if a name was provided
		if (!artistName.isEmpty()) {
			// Set the query to use the appropriate search type
			
			// " WHERE LOWER(a.lastName) " + (searchType.equalsIgnoreCase(TYPE_EQUALS) ? "=" : "LIKE") + " LOWER(?1)";
			String condition = " WHERE LOWER(a.firstName) %s LOWER(?1) OR LOWER(a.lastName) %s LOWER(?1) OR LOWER(a.bandName) %s LOWER(?1)";
						
			if (searchType.equalsIgnoreCase(TYPE_EQUALS)) {
				condition = String.format(condition, "=", "=", "=");
			} else {
				condition = String.format(condition, "LIKE", "LIKE", "LIKE");
			}
			
			queryString += condition;
		}
		
		// Create the query
		Query query = em.createQuery(queryString);
		
		// Insert the name into the query if it was provided
		if (!artistName.isEmpty()) {
			switch (searchType.toLowerCase()) {
				case TYPE_EQUALS:
					query.setParameter(1, artistName);
					break;
				case TYPE_BEGINS:
					query.setParameter(1, artistName + "%");
					break;
				case TYPE_ENDS:
					query.setParameter(1, "%" + artistName);
					break;
				default:
					query.setParameter(1, "%" + artistName + "%");
					break;
			}
		}

		// Search the persistence context for artists matching search parameters
		List<String> artistIDs = query.getResultList();
		
		// Create a JSON array of all found artists
		JSONArray artistList = new JSONArray();
		for(String artistID : artistIDs) {
			Artist a = em.find(Artist.class, artistID);
			artistList.put(a.toJSON());
		}
		
		// Close the connection to the persistence manager
		em.close();
		emFactory.close();
		
		// Return a list of artists
		return artistList;
	}
	
}
