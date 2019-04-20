package bmw77_FinalProject;

public class MusicTester {

	public static void main(String[] args) {
		ManageArtists am = new ManageArtists();
		
		Artist a = am.getArtist("0208d02c-191e-4e58-9103-f474b9253581");
		
		System.out.println(a.getFirstName());
		
	}
	
}