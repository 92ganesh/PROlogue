
public class scoreCalculator {

	public static float skillScoreCalculator(String keyValuePairs, String candidateSkills) {
		float result = 0;
		int count=0; //the number of things the candidate has that are matching the recruioterss needs
				
		//I am assuming the data comes in a format such that {'key:value','.....'}
		//It should use the , term as the separator
		
		String[] pair=keyValuePairs.split(",");
		
		for(String data : pair) {
			String[] keyValue=data.split(":");
			String candidateSkillslowerFormat=candidateSkills.toLowerCase();
			if(candidateSkillslowerFormat.contains(","+keyValue[0].toLowerCase())||candidateSkillslowerFormat.contains(keyValue[0].toLowerCase()+",")||candidateSkillslowerFormat.contains(","+keyValue[0].toLowerCase()+",")) {
				result= result + Integer.parseInt(keyValue[1]);
				count++;
			}
			System.out.println(count);
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		System.out.println(skillScoreCalculator("java:10,CPP:7","java,Cpp"));
	}
}
