package BasicCryptography;

public class CaesarCipher {

	public static void main(String[] args) {
		int key = 3;
		
		char[] alphabet1 = {'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

		System.out.println(decrypt("E", alphabet1, 7));
		
		String plntxt = "HELLO";
		String cipher = encrypt(plntxt, alphabet1, key);
		System.out.println(cipher);
		System.out.println(decrypt(cipher, alphabet1, key));
	}
	
	/**
	 * Encrypts a message using a ceaser cipher
	 * @param plaintxt - the text to be encrypted (must only include values from the domain)
	 * @param domain - an array of characters, with the indices corresponding to their position
	 * @param key - the value to shift the plain text by
	 * @return - the encrypted text
	 */
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
	
	/**
	 * Decrypts a message using a ceaser cipher
	 * @param plaintxt - the encrypted text (must only include values from the domain)
	 * @param domain - the domain used to encrypt the text
	 * @param key - the shift value used when the text was encrypted
	 * @return - the decrypted text
	 */
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
