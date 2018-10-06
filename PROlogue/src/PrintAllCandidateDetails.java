

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
		int pos = Integer.parseInt(request.getParameter("pos"));
		
		
		
		String str = databaseConnection.eligibleCandidates(pos);
		htmlTable+= databaseConnection.getRecommendedCandidates(str);
		String[] s = databaseConnection.getRecommendedCandidates(str);
		String[] regList=s[0].split(","), namesList=s[1].split(",");
		
		for(int i=0;i<regList.length-1;i++) {
			String score = databaseConnection.selectCertainData("candidatedetails", Integer.parseInt(regList[i]), "cumulative_score");
			htmlTable+="<tr><td>"+regList[i]+"</td><td>"+namesList[i]+"</td><td>"+score+"</td>";
			htmlTable+= "<td><input type='checkbox' name='invite_list' value='"+regList[i]+"'></td></tr>";
		}
		response.addHeader("printCandidateDetails",htmlTable);
		
	}
}



















