
import java.sql.*;

public class databaseConnection {
	 private static final String url = "jdbc:postgresql://localhost/PROlogue";
	 private static final String user = "postgres";
	 private static final String password = "postgres";  
	  
	 	public static Connection conn = null;
		public static Connection connect() {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.print("databaseConnection at line "+lineNum()+":"); e1.printStackTrace();
			}
		   
			try {
			   conn = DriverManager.getConnection(url, user, password);
			   System.out.println("databaseConnection at line "+lineNum()+":"+"Connected to the PostgreSQL server successfully.");
			} catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			}
	
			return conn;
	   }
	   
	   public static void disconnect() {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			}
		}
	   /*
	   public static void insertDataCandidate(int regNo, String cName,String cEmail, String linkedIn,String gitHub,String codeChef,String hackerRank,String skills){
		   connect();
		   PreparedStatement pst=null;
		   try {		
			   pst=conn.prepareStatement("INSERT INTO candidatedetails VALUES(?,?,?,?,?,?,?,?);");
			   pst.setInt(1,regNo);//puts the regno in the 1st posn
			   pst.setString(2,cName);
			   pst.setString(3,cEmail);
			   pst.setString(4, linkedIn);
			   pst.setString(5,gitHub);
			   pst.setString(6,codeChef);
			   pst.setString(7,hackerRank);
			   pst.setString(8,skills);
			   
			   pst.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": data inserted in candidatedetails table");
			   pst.close();
			   insertDataScraped(regNo);
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }
		   disconnect();
	   }
	   
	   */
	   private static int lineNum(){
			return Thread.currentThread().getStackTrace()[2].getLineNumber();
		}
}
	   