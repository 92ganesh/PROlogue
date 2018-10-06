
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
	   
	   public static String eligibleCandidates(int positionId) {
		   scoreInserter(positionId);
		   String returnValue= scoreSorter();
		   
		   return returnValue;
	   }
	   
	   public static String selectCertainData(String tableName,int regNo_value,String what_data){
		   //the PK_identifier is the value of the reg_no in the table 'tableName'(identify it by the primary key)
		   connect();
		   try {
			   PreparedStatement pst=null;
			   
			   if(tableName=="recruiterrequirements") {
				   pst=conn.prepareStatement("SELECT "+what_data+" FROM "+tableName+" WHERE positionid = ? ;");
			   }
			   else {
				   pst=conn.prepareStatement("SELECT "+what_data+" FROM "+tableName+" WHERE reg_no = ? ;");
			   }
			   pst.setInt(1, regNo_value);
			   String return_value=new String(); 
			   ResultSet r=(ResultSet)pst.executeQuery();
			   while(r.next()){ 
				   return_value =  r.getString(what_data);
			   }
			   pst.close();
			   disconnect();
			   return return_value;
			
			}catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			   disconnect();
			   return "";
			}
		}
	   
	   public static void insertDataScraped(int regNo) {
			//inserting in codechef table:   
			int cc_rating=ScraperOfAllData.rating_CC(selectCertainData("candidatedetails",regNo,"codechef"));
			int cc_stars=ScraperOfAllData.star_CC(selectCertainData("candidatedetails",regNo,"codechef"));
			int cc_fullySolved=ScraperOfAllData.fullySolved_CC(selectCertainData("candidatedetails",regNo, "codechef"));
			int cc_partiallySolved=ScraperOfAllData.partialSolved_CC(selectCertainData("candidatedetails",regNo, "codechef"));
			int cc_globalRank=ScraperOfAllData.globalRank_CC(selectCertainData("candidatedetails",regNo, "codechef"));
			int cc_localRank=ScraperOfAllData.localRank_CC(selectCertainData("candidatedetails",regNo, "codechef"));
			System.out.println("databaseConnection at line "+lineNum()+": scrapped details from Codechef");
			
			//hackerrank
			int hr_star=ScraperOfAllData.star_HR(selectCertainData("candidatedetails",regNo,"hackerrank"));
			int hr_gold=ScraperOfAllData.gold_HR(selectCertainData("candidatedetails",regNo,"hackerrank"));
			int hr_silver=ScraperOfAllData.silver_HR(selectCertainData("candidatedetails",regNo,"hackerrank"));
			int hr_bronze=ScraperOfAllData.bronze_HR(selectCertainData("candidatedetails",regNo,"hackerrank"));
			System.out.println("databaseConnection at line "+lineNum()+": scrapped details from Hackerrank");
			
			//github
			int git_repo=ScraperOfAllData.repo_Git(selectCertainData("candidatedetails",regNo,"github"));
			int git_star=ScraperOfAllData.stars_Git(selectCertainData("candidatedetails",regNo,"github"));
			String git_followers=ScraperOfAllData.followers_Git(selectCertainData("candidatedetails",regNo,"github"));
			String git_following=ScraperOfAllData.following_Git(selectCertainData("candidatedetails",regNo,"github"));
			System.out.println("databaseConnection at line "+lineNum()+": scrapped details from Github");

			//codeforces
			int codeForces=ScraperOfAllData.codeForcesRanking(selectCertainData("candidateDetails",regNo,"codeforces"));

			// Codechef
			PreparedStatement pst=null;
			try {		
			   pst=conn.prepareStatement("INSERT INTO codechef VALUES(?,?,?,?,?,?,?);");
			   pst.setInt(1,regNo);//puts the regno in the 1st posn
			   pst.setInt(2,cc_rating);
			   pst.setInt(3,cc_stars);
			   pst.setInt(4, cc_fullySolved);
			   pst.setInt(5, cc_partiallySolved);
			   pst.setInt(6, cc_globalRank);
			   pst.setInt(7, cc_localRank);
			   
			   pst.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": codechef details inserted into database");
			   pst.close();
			} catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			}
		   
			// Hackerrank
		   PreparedStatement pst2=null;
		   try {		
			   pst2=conn.prepareStatement("INSERT INTO hackerrank VALUES(?,?,?,?,?);");
			   pst2.setInt(1,regNo);//puts the regno in the 1st posn
			   pst2.setInt(2,hr_star);
			   pst2.setInt(3,hr_gold);
			   pst2.setInt(4, hr_silver);
			   pst2.setInt(5, hr_bronze);
			   pst2.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": hackerrank details inserted into database");
			   pst2.close();
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }  
		   
		   
		   //github
		   PreparedStatement pst3=null;
		   try {		
			   pst3=conn.prepareStatement("INSERT INTO github VALUES(?,?,?,?,?);");
			   pst3.setInt(1,regNo);//puts the regno in the 1st posn
			   pst3.setInt(2,git_repo);
			   pst3.setInt(3,git_star);
			   pst3.setString(4, git_followers);
			   pst3.setString(5, git_following);
			   pst3.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": GitHub details inserted into database");
			   pst3.close();
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }
		   PreparedStatement pst4=null;
		   try {		
			   pst4=conn.prepareStatement("INSERT INTO codeforces VALUES(?,?);");
			   pst4.setInt(1,regNo);//puts the regno in the 1st posn
			   pst4.setInt(2,codeForces);
			   pst4.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": rating details inserted into codeForces database");
			   pst4.close();
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }
		   
		  
	   }
	   
	   
	   //create score inserter
	   public static void scoreInserter(int positionId) {
		   System.out.println("------99 "+positionId);
		   connect();
		   PreparedStatement pst2=null;
		   float score=0;
		   try {		
			   
			   PreparedStatement pst3=null;
			   pst3=conn.prepareStatement("SELECT * FROM candidatedetails");
			   ResultSet r=(ResultSet)pst3.executeQuery();
			   disconnect();
			   while(r.next()){ 
				    int reg = r.getInt("reg_no");
				    String key = selectCertainData("recruiterrequirements", positionId, "positionrequirements");
				    String skills =  r.getString("skills");
				    skills = skills.substring(1, skills.length()-1);
				    int cp = Integer.parseInt(selectCertainData("recruiterrequirements", positionId, "competitive"));
				    int git = Integer.parseInt(selectCertainData("recruiterrequirements", positionId, "gitActivity"));
				    
				    score=scoreCalculator.ultimateAlgorithm(reg,key ,skills,cp ,git );
				    connect();
				    
				    pst2=conn.prepareStatement("UPDATE candidatedetails SET cumulative_score="+score+" WHERE reg_no="+r.getInt("reg_no")+";");
			   		pst2.executeUpdate();
			   		
					System.out.println("databaseConnection at line "+lineNum()+": score details inserted into database");
					pst2.close();
			   }
			} catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }   
		   disconnect();
		   
	   }
	   
	   
	   //sort the scores
	   
	   public static String scoreSorter() {
		   connect();
		   PreparedStatement pst=null;
		   try {
			  pst=conn.prepareStatement("SELECT * FROM candidatedetails ORDER BY cumulative_score DESC;");
			  ResultSet r=(ResultSet)pst.executeQuery();
			  String reg_nos="";
			  while(r.next()) {
				reg_nos+=r.getString("reg_no")+",";
			  }

			  disconnect();
			  return reg_nos;
		   }
			  catch (SQLException e) {
		   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   disconnect();
		   return "";
	   }
		   
	   }
	   
	   
	   
	   public static String[] getSelectedCandidates(){
			connect();
			try {
			   PreparedStatement pst=null;
			   pst=conn.prepareStatement("SELECT cname,cemail FROM candidatedetails WHERE invite_for_next_round = 'YES' ;");
			 
			   ResultSet r=(ResultSet)pst.executeQuery();
			   String emailsList="", namesList="";
			   while(r.next()){ 
				   emailsList = emailsList + r.getString("cemail")+";";
				   namesList = namesList + r.getString("cname")+";";
			   }
			   
			   String namesAndEmails[] = {namesList,emailsList};
			   pst.close();
			   disconnect();
			   return namesAndEmails;
			
			}catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			   String[] dummyValue = {""};
			   disconnect();
			   return dummyValue;
			}
		}
	   
	   public static void updateInvitationStatus(String jointListOfRegNo){
			String listOfRegNo[] = jointListOfRegNo.split(";");
			PreparedStatement pst=null;
			connect();
			try {
					for(String each:listOfRegNo) {
						pst=conn.prepareStatement("UPDATE candidatedetails SET invite_for_next_round='YES' where reg_no=?");
						pst.setInt(1,Integer.parseInt(each));
						pst.executeUpdate();
					}
					
					System.out.println("databaseConnection at line "+lineNum()+": updated invitation status in database");
					pst.close();
				} catch (SQLException e) {
					System.out.println("databaseConnection at line "+lineNum()+": "+e.getMessage());
			}
			disconnect();
		}
	   
	   public static void insertDataRequirements(String positionname,int gitactivity, int competitive,String positionrequirements){
		   connect();
		   PreparedStatement pst=null;
		   try {		
			   pst=conn.prepareStatement("INSERT INTO recruiterrequirements VALUES(?,?,?,?,?);");
			   pst.setInt(1,totalPostings()+1);//puts the regno in the 1st posn
			   pst.setString(2,positionname);
			   pst.setInt(3,gitactivity);
			   pst.setInt(4, competitive);
			   pst.setString(5,positionrequirements);
			   
			   pst.executeUpdate();
			   System.out.println("databaseConnection at line "+lineNum()+": data inserted in recruiterrequirements table");
			   pst.close();
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
		   }
		   disconnect();
	   }
	   
	   public static int totalPostings() {
		   int total;
		   connect();
		   PreparedStatement pst=null;
		   try {
			   pst=conn.prepareStatement("SELECT COUNT(positionid) from recruiterrequirements;");
			   ResultSet r=(ResultSet)pst.executeQuery();
			   r.next();
			   total = r.getInt("count");
			   return total;
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			   total=-1;
		   }
		   disconnect();
		   return total;
	   }
	   
	   public static String[] posName() {
		   String posId="", posName="";
		   connect();
		   PreparedStatement pst=null;
		   try {
			   pst=conn.prepareStatement("SELECT * from recruiterrequirements;");
			   ResultSet r=(ResultSet)pst.executeQuery();
			   while(r.next()){ 
					posId += Integer.toString(r.getInt("positionid"))+"," ;
					posName += r.getString("positionname")+",";
			   }
			   String regAndnames[] = {posId,posName};
			   disconnect();
			   return regAndnames;
		   } catch (SQLException e) {
			   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
			   String[] dummyValue = {""};
			   disconnect();
			   return dummyValue;
		   }
	   }
	   
	   public static String[] getRecommendedCandidates(String regListCombined){
		    String[] regListSplitted = regListCombined.split(",");
		    String regList="", namesList="";
			   
		    for(int i=0; i<regListSplitted.length; i++) {
		    	connect();
				try {
				   int reg_no = Integer.parseInt(regListSplitted[i]);
				   PreparedStatement pst=null;
				   pst=conn.prepareStatement("SELECT cname FROM candidatedetails WHERE reg_no = "+reg_no+" ;");
				 
				   ResultSet r=(ResultSet)pst.executeQuery();
				   while(r.next()){ 
					   regList += reg_no +",";
					   namesList = namesList + r.getString("cname")+",";
				   }
				   pst.close();
				}catch (SQLException e) {
				   System.out.println("databaseConnection at line "+lineNum()+":"+e.getMessage());
				}
				disconnect();
		    }
		    String regAndNames[] = {regList,namesList};
		    return regAndNames;
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
	   