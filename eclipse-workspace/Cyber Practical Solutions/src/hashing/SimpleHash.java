package hashing;

public class SimpleHash {

	public static void main(String[] args) {
		String plaintxt = "PKI";

		System.out.println(binToHex(encrypt(plaintxt)));
	}

	public static String encrypt(String plaintxt) {
		String cyphertxt =  addZrs(Integer.toBinaryString(plaintxt.charAt(0)), 8);
		
		for(int i=1; i<plaintxt.length(); i++) {
			char chr = plaintxt.charAt(i);
			
			String binChr = addZrs(Integer.toBinaryString(chr), 8);
			
			cyphertxt = xoring(binChr, cyphertxt);
			
		}
		
		return cyphertxt;
	}
	
	public static String xoring(String a, String b){
	    String ans = "";
	    for (int i = 0; i < a.length(); i++) {
	    	if (a.charAt(i) == b.charAt(i)) {
	    		ans += "0";
	    	} else {
	    		ans += "1";
	    	}
	    }
	    return ans;
	}
	
    public static String addZrs(String binaryStr, int maxLngth) {
    	int orgLngth = binaryStr.length();
		for(int j=0; j<(maxLngth-orgLngth); j++) {
			binaryStr = "0" + binaryStr;
		}
    	return binaryStr;
    }
    
	public static String binToHex(String bin) {
		return Integer.toHexString(Integer.parseInt(bin, 2));
	}
	
	public static String hexToBin(String hex) {
		return Integer.toBinaryString(Integer.parseInt(hex, 16));
	}
}
