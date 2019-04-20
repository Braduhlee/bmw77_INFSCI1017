package bmw77_FinalProject;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.*;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class Tester {

	public static void main(String[] args) {
		GenreManager gm = new GenreManager();
		Genre g = gm.getGenre("0a65e548-0782-4b6f-8db3-72c8f6d9f765");
		System.out.println(g.getDescription());
		
		
		
		
		

		
		
		
		
		
		
	}

}