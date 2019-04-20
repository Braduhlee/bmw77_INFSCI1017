package bmw77_FinalProject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONArray;

public class GenreManager {
	public void createGenre(String genreID, String genreName, String descr){
		EntityManagerFactory emFactory = 
				Persistence.createEntityManagerFactory("bmw77_FinalProject");
		
		EntityManager em = emFactory.createEntityManager();
		
		em.getTransaction().begin();
		Genre g = new Genre();
		
		// Artist a = new Artist();
		// a.setArtistID(UUID.randomUUID().toString());
		
		g.setGenreID(genreID);
		g.setGenreName(genreName);
		g.setDescription(descr);
		
		// Add the Genre object to the ORM object grid
		em.persist(g);
		
		// Commit transaction
		em.getTransaction().commit();
		
		// Close connection to persistence manager
		em.close();
		emFactory.close();
	}
	
	public Genre getGenre(String genreID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		
		
		em.close();
		emFactory.close();
		return g;
	}
	
	public JSONArray getGenreList(){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		
		// Note that you are querying the object grid, not the database!
		List<String> genreIDs = em.createQuery("SELECT g.genreID FROM Genre g").getResultList();
		JSONArray genreListJSON = new JSONArray();
		for(String genreID : genreIDs){
			Genre g = em.find(Genre.class, genreID);
			genreListJSON.put(g.toJSON());
		}
		em.close();
		emFactory.close();
		
		return genreListJSON;
	}
	
	
	public void updateGenre(String genreID, String genreName, String descr){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		if(!genreName.equals("")){
			g.setGenreName(genreName);
		}
		
		if(!descr.equals("")){
			g.setDescription(descr);
		}
		
		em.persist(g);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
	
	public void deleteGenre(int genreID){
		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("bmw77_FinalProject");
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		
		Genre g = em.find(Genre.class, genreID);
		
		em.remove(g);
		em.getTransaction().commit();
		
		em.close();
		emFactory.close();
	}
}
