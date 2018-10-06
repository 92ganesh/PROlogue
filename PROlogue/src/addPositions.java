

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addPositions
 */
@WebServlet("/addPositions")
public class addPositions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String posList  = "";
		//"<a href='javascript:;' onClick='return printCandidateDetails();'>link</a>"
		String s[] = databaseConnection.posName();
		String[] posId=s[0].split(",");
		String[] posName=s[1].split(",");
		//posList+="<a href='javascript:;' onClick='return printCandidateDetails("+posId[0]+");'>"+posName[0]+"</a>";
		for(int i=0;i<posId.length;i++) {
			posList+="<a href='javascript:;' onClick='return printCandidateDetails("+posId[i]+");'>"+posName[i]+"</a>";
		}
		
		//System.out.println(posList);
		response.addHeader("addPositions",posList);
	}

}
