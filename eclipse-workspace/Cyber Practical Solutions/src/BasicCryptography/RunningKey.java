package BasicCryptography;

public class RunningKey {

	public static void main(String[] args) {
		char[] alphabet1 = {'A', 'B', 'C', 'D', 'E',
				'F', 'G', 'H', 'I', 'J', 'K', 'L', 
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		String key = "PACKET";
		
		System.out.println(encrypt("NETWORK", alphabet1, key));
		
		//System.out.println(decrypt(encrypt("NETWORK", alphabet1, key), alphabet1, key));
	}
	
	public static String encrypt(String plaintxt, char[] domain, String key) {
		String cyphertxt = "";
		
		for(int i=0; i<plaintxt.length(); i++) {
			int valOfPlnTxt = getVal(domain, plaintxt.charAt(i));
			int valOfKey = getVal(domain, key.charAt(i%key.length()));
			
			int newVal = (valOfPlnTxt + valOfKey) % domain.length;
			
			cyphertxt += getChr(domain, newVal);
		}
		
		return cyphertxt;
	}
	
	/*
	public static String decrypt(String cyphertxt, char[] domain, String key) {
		String plaintxt = "";
		
		for(int i=0; i<cyphertxt.length(); i++) {
			int valOfCphrTxt = getVal(domain, cyphertxt.charAt(i));
			int valOfKey = getVal(domain, key.charAt(i%key.length()));
			
			int newVal = Math.abs(valOfCphrTxt - valOfKey) % domain.length;
			
			plaintxt += getChr(domain, newVal);
		}
		
		return plaintxt;
	}*/
	
	public static int getVal(char[] domain, char c) {
	    for(int i =0 ; i<domain.length; i++) {
	    	if(domain[i] == c) {
	    		return i;
	    	}
	    }
	    
	    return -1;
	}
	
	public static char getChr(char[] domain, int c) {
	    for(int i =0 ; i<domain.length; i++) {
	    	if(i == c) {
	    		return domain[i];
	    	}
	    }
	    
	    return '1';
	}

}
