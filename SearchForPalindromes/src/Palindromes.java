
public class Palindromes {

	
	public static void returnPalyndroms(String str){
		StringBuilder br = new StringBuilder();
		int startIndex;
		int lastIndex;
		char ch;
		
		for (int i = 0; i < str.length(); i++) {
			startIndex = i;
			ch = str.charAt(i);
			lastIndex = str.lastIndexOf(ch, str.length()-1);
			
			while(startIndex < lastIndex){
				
				String currentString = str.substring(startIndex, lastIndex+1);
				//System.out.println(currentString);
				for (int j = currentString.length()-1; j >= 0; j--) {
					br.append(currentString.charAt(j));
				}
				//System.out.println(br);
				if(currentString.equals(br.toString())){
					System.out.println(currentString);
					
				}
				br.setLength(0);
				lastIndex = str.lastIndexOf(ch, lastIndex - 1);
				
			}
			
			
		}
		
	}
	public static void main(String[] args) {
		returnPalyndroms("Thisisalabalanicaneven"); 
		
	}
}
