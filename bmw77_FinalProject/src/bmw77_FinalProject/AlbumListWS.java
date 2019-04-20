package bmw77_FinalProject;

	import java.io.IOException;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	/**
	 * Servlet implementation class AlbumListWS
	 */
	@WebServlet("/AlbumListWS")
	public class AlbumListWS extends HttpServlet {
		private static final long serialVersionUID = 1L;
	       
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public AlbumListWS() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// Set response content type to JSON
			response.setContentType("application/json");

			// Get album title and search type, if provided
			String albumTitle = "";
			String searchType = "";
			
			if (request.getParameter("albumTitle") != null) {
				albumTitle = request.getParameter("albumTitle");
				if (request.getParameter("searchType") != null) {
					searchType = request.getParameter("searchType");
				}
			}

			// Find albums for provided search parameters, or all if none given
			ManageAlbums bm = new ManageAlbums();
			response.getWriter().print(bm.getAlbumList(albumTitle, searchType));
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

	}

