package BasicCryptography;

public class HillCipher {

	public static void main(String[] args) {
		float[][] key = {{15, 10, 14},
				{8, 17, 23},
				{19, 13, 5}};
		
		char[] alphabet1 = {'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		String cypher = encrypt("ENIGMACODE", alphabet1, key);
		
		System.out.println(cypher);
		
		//String plaintext = decrypt(cypher, alphabet1, key);
		
		//System.out.println(plaintext);

	}
	
	public static String encrypt(String plaintxt, char[] domain, float[][] key) {
		String cypherText = "";		
		
		// padding and creating matching numbers to the domain
		int padLength = plaintxt.length() % 3;
		if(padLength != 0) {
			for(int i=0; i<=padLength; i++) {
				plaintxt += "X";
			}
		}
		
		System.out.println(plaintxt.length());
		
		int keyRow = 0;
		
		for(int i=0; i<plaintxt.length(); i = i+3) {
			int[] plainVal = new int[3];

			for(int index=0; index<plainVal.length; index++) {
			    for(int j =0 ; j<domain.length; j++) {
			    	System.out.println("i = " + i);
			    	System.out.println("index = " + index);
			    	System.out.println("char = " + plaintxt.charAt(index + i));
			    	if(domain[j] == plaintxt.charAt(index + i)) {
			    		plainVal[index] = j;
			    	}
			    }
			}

		    
			if(keyRow % 3 == 0) {
				keyRow=0;
			}
			//{plaintxt.charAt(i), plaintxt.charAt(i+1), plaintxt.charAt(i+2)};
			int newVal = 0;
			for(int j=0; j<plainVal.length; j++) {
				newVal += plainVal[j]*key[keyRow][j];
			}
			
			keyRow++;
			// some how get the below line to run for each char in the plaintxt
			cypherText += domain[newVal % 26];
		}
		
		return cypherText;
	}
	
	public static String decrypt(String cypherText, char[] domain, float[][] key) {
		String plainTxt = "";
		
		for (int i = 0; i < cypherText.length(); i++){
		    char c = cypherText.charAt(i);
		    char newC = 0;
		    for(int j =0 ; j<domain.length; j++) {
		    	if(domain[j] == c) {
		    		//newC = domain[Math.abs((j - key) % domain.length)];
		    	}
		    }
		    plainTxt += (char) newC;
		}
		
		return plainTxt;
	}

}
