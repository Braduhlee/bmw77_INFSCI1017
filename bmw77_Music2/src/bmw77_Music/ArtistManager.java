package bmw77_Music;

import java.util.UUID;
import javax.persistence.*;

/**
 * Class ArtistManager is responsible for creating, updating and deleting Artist records. 
 * @author Brad Wayman
 *
 */
public class ArtistManager {
	
	/**
	 * Method createArtist generates a new Artist record in the ORM object grid.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 * @param bio is biographical information for the artist and/or band.
	 */
	public void createArtist(String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = new Artist();
		
		a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		
		em.persist(a);
		
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
		
	}

	/**
	 * Method updateArtist updates an Artist record in the ORM object grid.
	 * @param artistID is the identifying UUID of the target Artist.
	 * @param firstName is the artist's first name.
	 * @param lastName is the artist's last name.
	 * @param bandName is the name of the artist's band.
	 * @param bio is biographical information for the artist and/or band.
	 */
	public void updateArtist(String artistID, String firstName, String lastName, String bandName, String bio) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		if(!firstName.equals("")) {
			a.setFirstName(firstName);
		}

		if(!lastName.equals("")) {
			a.setLastName(lastName);
		}
		
		if(!bandName.equals("")) {
			a.setBandName(bandName);
		}
		
		if(!bio.equals("")) {
			a.setBio(bio);
		}

		em.persist(a);

		em.getTransaction().commit();

		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteArtist removes an Artist record from the ORM object grid.
	 * @param artistID is the identifying UUID of the target Artist.
	 */
	public void deleteArtist(String artistID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);
		
		em.remove(a);
		
		em.getTransaction().commit();

		em.close();
		emFactory.close();
	}
	
	/**
	 * Method findSong locates an Artist record in the ORM object grid using its artistID.
	 * @param artistID is the identifying UUID of the target Artist.
	 * @return is the Artist object.
	 */
	public Artist findArtist(String artistID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Artist a = em.find(Artist.class, artistID);

		em.close();
		emFactory.close();
		
		return a;
		
	}

}