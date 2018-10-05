
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
	
	
	
	
	
	public static float ultimateAlgorithm(String keyValuePairs, String candidateSkills) {
		float finalScore=0;
		int parameterCount=1;
		
		finalScore=skillScoreCalculator(keyValuePairs,candidateSkills)/scoreOfRequiredSkills(keyValuePairs);
		finalScore/=parameterCount;
		return finalScore*100;
		
		
	}
	
	public static void main(String args[]) {
		System.out.println(ultimateAlgorithm("java:10,CPP:7,Python:4","java,Cpp"));
		
	}
}
