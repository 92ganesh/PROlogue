import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class scoreCalculator {

	public static float skillScoreCalculator(String keyValuePairs, String candidateSkills) {
		float result = 0;
		 //the number of things the candidate has that are matching the recruioterss needs
				
		//I am assuming the data comes in a format such that {'key:value','.....'}
		//It should use the , term as the separator
		
		String[] pair=keyValuePairs.split(",");
		
		for(String data : pair) {
			String[] keyValue=data.split(":");
			String candidateSkillslowerFormat=candidateSkills.toLowerCase();
			if(candidateSkillslowerFormat.contains(","+keyValue[0].toLowerCase())||candidateSkillslowerFormat.contains(keyValue[0].toLowerCase()+",")||candidateSkillslowerFormat.contains(","+keyValue[0].toLowerCase()+",")) {
				result= result + Integer.parseInt(keyValue[1]);
				
			}
		}
		System.out.println(result+" is skillScore matching");
		return result;
	}
	
	
	
	
	public static int scoreOfRequiredSkills(String keyValuePairs) {
		//calculate the number of matching skills, for our algorithm to calculate avg
		
		int score=0; //the number of things the candidate has that are matching the recruioterss needs
				
		//I am assuming the data comes in a format such that {'key:value','.....'}
		//It should use the , term as the separator
		
		String[] pair=keyValuePairs.split(",");
		
		for(String data : pair) {
			String[] keyValue=data.split(":");
		
			score=score + Integer.parseInt(keyValue[1]);
			
		
		}
		System.out.println(score+" is the max possible skillScore");
		
		return score;
		
	}
	
	
	
	
	
	public static float ultimateAlgorithm(int reg_no,String keyValuePairs, String candidateSkills,int cp, int dev) {
		int cc_rating = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "rating"));
		int cc_star = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "stars"));
		int cc_fullsolved = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "problems_fully_solved"));
		int cc_parsolved = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "problems_partially_solved"));
		int cc_glorank = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "global_rank"));
		int cc_counrank = Integer.parseInt(databaseConnection.selectCertainData("codechef", reg_no, "country_rank"));
		float cc_score = 0;
		int hr_stars = Integer.parseInt(databaseConnection.selectCertainData("hackerrank", reg_no, "stars"));
		int hr_gold = Integer.parseInt(databaseConnection.selectCertainData("hackerrank", reg_no, "gold"));
		int hr_silver = Integer.parseInt(databaseConnection.selectCertainData("hackerrank", reg_no, "silver"));
		int hr_bronze = Integer.parseInt(databaseConnection.selectCertainData("hackerrank", reg_no, "bronze"));
		float hr_score = 0;
		int cf_rating = Integer.parseInt(databaseConnection.selectCertainData("codeforces", reg_no, "contestRank"));
		float cf_score = 0;
		int git_repo = Integer.parseInt(databaseConnection.selectCertainData("github", reg_no, "repositories"));
		int git_stars = Integer.parseInt(databaseConnection.selectCertainData("github", reg_no, "stars"));
		float git_score = 0;
		git_score = (float)(git_repo/5) + (float)(git_stars/8) ;
		git_score /= 10;
		cf_score = (float)(cf_rating/38);
		cf_score /= 5;
		hr_score = (hr_stars*2)+(float)(hr_gold/3)+(float)(hr_silver/5)+(float)(hr_bronze/10);
		hr_score /= 1.5;
		cc_score = (float)(cc_rating/240)+(float)(cc_star*2/7)+(float)(0.001*(cc_glorank+cc_counrank)*0.5)+(float)(cc_fullsolved*0.5)+(float)(cc_parsolved*0.01);
		cc_score /= 3;
		float cp_score = (float)(cf_score+cc_score+hr_score)/3;
		cp_score *= cp;
		cp_score /= 2.5;
		float dev_score = (float)(git_score*dev);
		dev_score /= 2.5;
		System.out.println(cp_score);
		System.out.println(dev_score);
		
		float finalScore=0;
		int parameterCount=1;
		
		finalScore=skillScoreCalculator(keyValuePairs,candidateSkills)/scoreOfRequiredSkills(keyValuePairs);
		finalScore/=parameterCount*2;
		finalScore = cp_score + dev_score + finalScore*100 - 10;
		
		return finalScore;
		
		
	}
	
	public static void main(String args[]) {
		System.out.println(ultimateAlgorithm(1,"java:10,CPP:7,Python:4","java,Cpp",4,6));
		
	}
}
