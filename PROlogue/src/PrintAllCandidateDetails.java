

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrintAllCandidateDetails
 */
@WebServlet("/PrintAllCandidateDetails")
public class PrintAllCandidateDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String htmlTable  = "";
		String str = "1,3,4";
		//htmlTable+= databaseConnection.getRecommendedCandidates(str);
		String[] s = databaseConnection.getRecommendedCandidates(str);
		String[] regList=s[0].split(","), namesList=s[1].split(",");
		
		for(int i=0;i<regList.length;i++) {
			htmlTable+="<tr><td>"+regList[i]+"</td><td>"+namesList[i]+"</td>";
			htmlTable+= "<td><input type='checkbox' name='invite_list' value='"+regList[i]+"'></td></tr>";
		}
		System.out.println(htmlTable);
		response.addHeader("printCandidateDetails",htmlTable);
	}
}



















