package bmw77_FinalProject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArtistListWS
 */
@WebServlet("/ArtistListWS")
public class ArtistListWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArtistListWS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type to JSON
		response.setContentType("application/json");

		// Get artist name and search type, if provided
		String artistName = "";
		String searchType = "";
		
		if (request.getParameter("artistName") != null) {
			artistName = request.getParameter("artistName");
			if (request.getParameter("searchType") != null) {
				searchType = request.getParameter("searchType");
			}
		}

		// Find artists for provided search parameters, or all if none given
		ManageArtists am = new ManageArtists();
		response.getWriter().print(am.getArtistList(artistName, searchType));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}