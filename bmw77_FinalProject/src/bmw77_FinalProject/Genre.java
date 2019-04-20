package bmw77_FinalProject;

import java.sql.SQLException;

import javax.persistence.*;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
@Table (name="genre")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column(name = "genre_id")
	private String genreID;

	@Column(name = "genre_name")
	private String genreName;

	@Column(name = "description")
	private String description;




	public void setGenreID(String genreID){
		this.genreID = genreID;
	}



	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;

	}

	public String getGenreID() {
		return genreID;
	}
	
	public JSONObject toJSON(){
		JSONObject genreJson = new JSONObject();
		try {
			genreJson.put("genre_id", this.genreID);
			genreJson.put("genre_name", this.genreName);
			genreJson.put("description", this.description);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return genreJson;
		
	}



}
