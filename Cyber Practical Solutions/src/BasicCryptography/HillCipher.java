package BasicCryptography;

public class HillCipher {

	public static void main(String[] args) {
		float[][] key = {{15, 10, 14},
				{8, 17, 23},
				{19, 13, 5}};
		
		float[][] key2 = {{8, 3},
				{5, 2}};
		
		char[] alphabet1 = {'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		//String cypher2 = encrypt("PAY", alphabet1, key2);
		
		//String cypher = encrypt("ENIGMACODE", alphabet1, key);
		String cypher = encrypt("MYSECRET", alphabet1, key2);
		
		System.out.println(cypher);
		
		//String plaintext = decrypt(cypher, alphabet1, key);
		
		//System.out.println(plaintext);

	}
	
	public static String encrypt(String plaintxt, char[] domain, float[][] key) {
		String cypherText = "";		
		
		// padding and creating matching numbers to the domain
		int padLength = plaintxt.length() % key[0].length;
		if(padLength != 0) {
			for(int i=0; i<=padLength; i++) {
				plaintxt += "X";
			}
		}
		
		//System.out.println(plaintxt.length());
		
		for(int i=0; i<plaintxt.length(); i = i+key[0].length) {
			int[] plainVal = new int[key[0].length];

			for(int index=0; index<plainVal.length; index++) {
			    for(int j =0 ; j<domain.length; j++) {
			    	//System.out.println("i = " + i);
			    	//System.out.println("index = " + index);
			    	//System.out.println("char = " + plaintxt.charAt(index + i));
			    	if(domain[j] == plaintxt.charAt(index + i)) {
			    		plainVal[index] = j;
			    	}
			    }
			}

			for(int keyRow = 0; keyRow<plainVal.length; keyRow++) {
				int newVal = 0;
				System.out.println();
				System.out.println("New Row:");
				for(int j=0; j<plainVal.length; j++) {
					//System.out.println("plainVal[j] = " + plainVal[j]);
					//System.out.println("key[keyRow][j] = " + key[keyRow][j]);
					System.out.println(plainVal[j] + "x" + key[keyRow][j] + " = " + plainVal[j]*key[keyRow][j]);
					System.out.print(newVal + "+" + plainVal[j]*key[keyRow][j] + " = ");
					newVal += plainVal[j]*key[keyRow][j];
					System.out.println(newVal);
					
					System.out.println();
				}
				cypherText += domain[newVal % 26];
				System.out.println(newVal + "%26 = " + newVal % 26);
			}
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
