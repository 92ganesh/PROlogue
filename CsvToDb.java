import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CsvToDb {
	public static int regno = 1;
	public static ArrayList<String> arr = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		selectAllCSV("C:\\hackathon\\PROlogue-master\\Data Files");
	}
	
	
	
	public static void selectAllCSV(String folderPath) throws IOException {
		  List<Path> files =  Files.walk(Paths.get(folderPath)).filter(Files::isRegularFile).collect(Collectors.toList());   
		  for(int i=0;i<files.size();i++)
		     {	 String fullpath =files.get(i).toString();
		     	 if(fullpath.substring(fullpath.length()-4).equals(".csv")) {
		     		accessCSV(fullpath);
		     	 }
		     	 
		     }
	}
	
	public static void accessCSV(String filePath) throws IOException {
		Connection c = null;
		Statement stmt = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/PROlogue",
	            "postgres", "postgres");
	         c.setAutoCommit(false);
	         stmt = c.createStatement();
	         String sql = "INSERT INTO candidatedetails (reg_no,cname,cemail,linkedin,github,codechef,hackerrank,skills,contact_no,graduation_year"
	         		 +",fy_ypi,sy_ypi,ty_ypi,ly_ypi,codeforces) "
	            + "VALUES (";
	         FileReader r = new FileReader(filePath); //path
		     BufferedReader br = new BufferedReader(r);
		     String str= br.readLine();
		     String array[] ;
		     array = new String[2];
		     array = str.split(",");
		     sql +=regno+",";
		     if(array.length>1)
		    	 sql+="'"+array[1]+"',";
		     else
		    	 sql+="'-1'"+",";
		     do {	    	
		    	 str = br.readLine();
		    	 if(str!=null){
			    	 array = new String[2];
			    	 array = str.split(",");
			    	 if(array[0].equals("Skills")==true) {
			    		 array[1] = str.substring(7,str.length());
			    	 }
			     	if(array.length>1) {
				    	 sql+="'"+array[1]+"',";
			     	}
				    else
				    	 sql+="'-1'"+",";
		     	}
		     	  
		     }while(str!=null);
		     sql = sql.substring(0,sql.length()-1);
		     sql += ");";
		     br.close();
	         stmt.executeUpdate(sql);
	         System.out.println(filePath+"Success");
	         stmt.close();
	         c.commit();
	         c.close();
	         regno += 1;
	      } catch (Exception e) {
	         e.printStackTrace();
	         
	         System.exit(0);
	      }
	      
		 
	}
	
	/*public static void sendtodb(){

	}
	*/
}


