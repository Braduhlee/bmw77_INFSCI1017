package bmw77_FinalProject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SongListWS
 */
@WebServlet("/SongListWS")
public class SongListWS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SongListWS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type to JSON
		response.setContentType("application/json");

		// Get song title and search type, if provided
		String songTitle = "";
		String searchType = "";
		
		if (request.getParameter("songTitle") != null) {
			songTitle = request.getParameter("songTitle");
			if (request.getParameter("searchType") != null) {
				searchType = request.getParameter("searchType");
			}
		}

		// Find songs for provided search parameters, or all if none given
		ManageSongs sm = new ManageSongs();
		response.getWriter().print(sm.getSongList(songTitle, searchType));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}