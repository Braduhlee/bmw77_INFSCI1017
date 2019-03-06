package bmw77_Music;

import java.util.UUID;
import javax.persistence.*;

/**
 * Class AlbumManager is responsible for creating, updating and deleting Album records. 
 * @author Brad Wayman
 *
 */
public class AlbumManager {
	
	

	/**
	 * Method createAlbum generates a new Album record in the ORM object grid.
	 * @param title is the title of the album.
	 * @param releaseDate is the date on which the album was released.
	 * @param coverImagePath is the location of the cover image for the album.
 	 * @param recordingCompany is the company that recorded and released the album.
	 * @param numberOfTracks is the number of tracks on the album.
	 * @param pmrcRating is the PMRC's rating for the album.
	 * @param length is the length of the album in minutes.
	 */
	public void createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = new Album();
		
		a.setAlbumID(UUID.randomUUID().toString());
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);

		em.persist(a);
		
		em.getTransaction().commit();

		em.close();
		emFactory.close();
		
	}
	
	/**
	 * Method updateAlbum updates an Album record in the ORM object grid.
	 * @param albumID is the identifying UUID of the target Album.
	 * @param title is the title of the album.
	 * @param releaseDate is the date on which the album was released.
	 * @param coverImagePath is the location of the cover image for the album.
 	 * @param recordingCompany is the company that recorded and released the album.
	 * @param numberOfTracks is the number of tracks on the album.
	 * @param pmrcRating is the PMRC's rating for the album.
	 * @param length is the length of the album in minutes.
	 */
	public void updateAlbum(String albumID, String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {

		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		if(!title.equals("")) {
			a.setTitle(title);
		}
		
		if(!releaseDate.equals("")) {
			a.setReleaseDate(releaseDate);
		}
		
		if(!coverImagePath.equals("")) {
			a.setCoverImagePath(coverImagePath);
		}
		
		if(!recordingCompany.equals("")) {
			a.setRecordingCompany(recordingCompany);
		}
		
		if(numberOfTracks > 0) {
			a.setNumberOfTracks(numberOfTracks);
		}
		
		if(!pmrcRating.equals("")) {
			a.setPmrcRating(pmrcRating);
		}
		
		if(length > 0) {
			a.setLength(length);
		}

		em.persist(a);
		
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method deleteAlbum removes an Album record from the ORM object grid.
	 * @param albumID is the identifying UUID of the target Album.
	 */
	public void deleteAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);
		
		em.remove(a);
		
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	/**
	 * Method findAlbum locates an Album record in the ORM object grid using its albumID.
	 * @param albumID is the identifying UUID of the target Album.
	 * @return is the Album object.
	 */
	public Album findAlbum(String albumID) {
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_Music");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Album a = em.find(Album.class, albumID);

		em.close();
		emFactory.close();
		
		return a;
		
	}
	
}
