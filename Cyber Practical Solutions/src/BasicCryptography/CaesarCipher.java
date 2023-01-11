package BasicCryptography;

public class CaesarCipher {

	public static void main(String[] args) {
		int key = 3;
		
		char[] alphabet1 = {'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		System.out.println(decrypt("E", alphabet1, 7));
		
		String plntxt = "HELLOZ";
		String cipher = encrypt(plntxt, alphabet1, key);
		System.out.println(cipher);
		System.out.println(decrypt(cipher, alphabet1, key));
	}
	
	public static String encrypt(String plaintxt, char[] domain, int key) {
		String cypherText = "";
		
		for (int i = 0; i < plaintxt.length(); i++){
		    char c = plaintxt.charAt(i);
		    char newC = 0;
		    for(int j =0 ; j<domain.length; j++) {
		    	if(domain[j] == c) {
		    		newC = domain[(j + key) % domain.length];
		    	}
		    }
		    cypherText += (char) newC;
		}
		
		return cypherText;
	}
	
	public static String decrypt(String cypherText, char[] domain, int key) {
		String plainTxt = "";
		
		for (int i = 0; i < cypherText.length(); i++){
		    char c = cypherText.charAt(i);
		    char newC = 0;
		    for(int j =0 ; j<domain.length; j++) {
		    	if(domain[j] == c) {
		    		int shift = j - key;
		    		if(shift < 0) {
		    			newC = domain[(domain.length - Math.abs(shift))];
		    		} else {
		    			newC = domain[shift];
		    		}
		    		
		    	}
		    }
		    plainTxt += (char) newC;
		}
		
		return plainTxt;
	}

}
