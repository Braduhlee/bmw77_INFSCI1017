package bmw77_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class MusicTester {

public static void main(String[] args) {
		
		
		//testing Song
		Song s = new Song("1");
		System.out.println(s.getTitle());
		
		s.setLength(360);
		s.setFilePath("songs/2009.mp3");
		s.setRecordDate("2018-03-23 00:00:00");
		s.setReleaseDate("2018-09-07 00:00:00");
		s.setTitle("2009");
		System.out.println(s.getTitle());
		s.deleteSong("1");
		
		Song s2 = new Song("2009",360,"2018-03-23 00:00:00","2018-09-07 00:00:00");
		s2.deleteSong("1");

		
		//testing Artist
		Artist a = new Artist("123");
		System.out.println(a.getBandName());
		
		a.setFirstName("Malcolm");
		a.setLastName("McCormick");
		a.setBandName("Mac Miller");
		a.setBio("Pittsburgh Native Hip Hop artist");
		System.out.println(a.getBandName());
		a.deleteArtist("123");
		
		Artist a2 = new Artist("Dominique","Jones","Lil Baby");
		a2.deleteArtist("1");
		
		//testing Album
		Album al = new Album("2");
		System.out.println(al.getTitle());
		
		al.setTitle("Swimming");
		al.setReleaseDate("2018-09-07 00:00:00");
		al.setCoverImagePath("images/swimming.jpg");
		al.setRecordingCompany("Warner Bros");
		al.setNumberOfTracks(13);
		al.setPmrcRating("R");
		al.setLength(58);
		System.out.println(al.getTitle());
		al.deleteAlbum("2");
		
		Album al2 = new Album("Drip Harder", "2018-10-04 00:00:00",  "YSL", 13, "R", 38);
		al2.deleteAlbum("1");		
		
	}

}