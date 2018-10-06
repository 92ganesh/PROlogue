

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class customization
 */
@WebServlet("/customization")
public class customization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String positionName = request.getParameter("positionName");
		System.out.println(positionName);
		String keyValue = request.getParameter("keyValue");
		System.out.println(keyValue);
		String GitHubActivity = request.getParameter("GitHubActivity");
		System.out.println(GitHubActivity);
		String CPActivity = request.getParameter("CPActivity");
		System.out.println(CPActivity);
		
		databaseConnection.insertDataRequirements(positionName,Integer.parseInt(GitHubActivity),Integer.parseInt(CPActivity),keyValue);
	}

}
