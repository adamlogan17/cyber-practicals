package SymmetricEncryption;

public class SDES {

	public static void main(String[] args) {
		String plain = "ENIGMACODE";
		
		String k = "1111011000";
		
		//System.out.println("key = " + keyGeneration(k));
		
		//System.out.println("end = " + encrypt(plain, k));
		
		//System.out.println(decrypt("y", k));
	    int S0[][] = {{ 1, 0, 3, 2},
	    		{ 3, 2, 1, 0},
	    		{ 0, 2, 1,3},
	    		{ 3, 1, 3, 2}};
	    
		System.out.println(sBoxing("10011101", S0));
	}
	
	public static String decrypt(String cyphertxt, String key) {
		String plaintxt = "";
		
		int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
		int IpMinus1[] = {4, 1, 3, 5, 7, 2, 8, 6};
		
		String[] keys = keyGeneration(key);
		
		String k1 = keys[0];
		String k2 = keys[1];
		System.out.println("k1 = " + k1);
		System.out.println("k2 = " + k2);
		
		for(int i=0; i<cyphertxt.length(); i++) {
			String currentChr = Integer.toBinaryString((int) cyphertxt.charAt(i));

			currentChr = addZrs(currentChr, 8);
			
			System.out.println("currentChr = " + currentChr);
			
			String postIP = permute(currentChr, IP);
			
			System.out.println("IP = " + postIP);
			
			String postFRK2 = f(postIP.substring(4,8), k2);
			
			System.out.println("postFRK2 = " + postFRK2);
			
			String postFk2 = xoring(postIP.substring(0,4), postFRK2) + postIP.substring(4,8);
			
			System.out.println("postFk2 = " + postFk2);
			
			String postSwap = postFk2.substring(4,8) + postFk2.substring(0,4);
			
			System.out.println("postSwap = " + postSwap);
			
			// still need to do frk2 and fk2
			String postFRK1 = f(postSwap.substring(4,8), k1);
			
			System.out.println("postFRK1 = " + postFRK2);
			
			String postFk1 = xoring(postSwap.substring(0,4), postFRK1) + postSwap.substring(4,8);
			
			System.out.println("postFk1 = " + postFk1);
			
			// still need to do ip-1
			String postIpMinus1 = permute(postFk1, IpMinus1);
			
			System.out.println("postIpMinus1/result = " + addZrs(postIpMinus1, 8));
			
			System.out.println();
			System.out.println();
			
			plaintxt += Character.toString(Integer.parseInt(postIpMinus1, 2));
			//plaintxt += Character.toString(Integer.parseInt(currentChr, 2));
		}
		
		
		return plaintxt;
	}
	
	public static String encrypt(String plaintxt, String key) {
		String cyphertxt =  "";
		int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
		int IpMinus1[] = {4, 1, 3, 5, 7, 2, 8, 6};
		
		String[] keys = keyGeneration(key);
		
		String k1 = keys[0];
		String k2 = keys[1];
		System.out.println("k1 = " + k1);
		System.out.println("k2 = " + k2);
		
		for(int i=0; i<plaintxt.length(); i++) {
			String currentChr = Integer.toBinaryString((int) plaintxt.charAt(i));

			currentChr = addZrs(currentChr, 8);
			
			System.out.println("currentChr = " + currentChr);
			
			String postIP = permute(currentChr, IP);
			
			System.out.println("IP = " + postIP);
			
			String postFRK1 = f(postIP.substring(4,8), k1);
			
			System.out.println("postFRK1 = " + postFRK1);
			
			String postFk1 = xoring(postIP.substring(0,4), postFRK1) + postIP.substring(4,8);
			
			System.out.println("postFk1 = " + postFk1);
			
			String postSwap = postFk1.substring(4,8) + postFk1.substring(0,4);
			
			System.out.println("postSwap = " + postSwap);
			
			// still need to do frk2 and fk2
			String postFRK2 = f(postSwap.substring(4,8), k2);
			
			System.out.println("postFRK2 = " + postFRK2);
			
			String postFk2 = xoring(postSwap.substring(0,4), postFRK2) + postSwap.substring(4,8);
			
			System.out.println("postFk2 = " + postFk2);
			
			// still need to do ip-1
			String postIpMinus1 = permute(postFk2, IpMinus1);
			
			System.out.println("postIpMinus1/result = " + addZrs(postIpMinus1, 8));
			
			System.out.println();
			System.out.println();
			
			cyphertxt += Character.toString(Integer.parseInt(postIpMinus1, 2));
			//cyphertxt += Character.toString(Integer.parseInt(currentChr, 2));
		}
		
		return cyphertxt;
	}
	
	public static String f(String R, String k) {
		String result = "";
		int EP[] = { 4, 1, 2, 3, 2, 3, 4, 1};
		int P4[] = { 2, 4, 3, 1};
	    int S0[][] = {{ 1, 0, 3, 2},
	    		{ 3, 2, 1, 0},
	    		{ 0, 2, 1,3},
	    		{ 3, 1, 3, 2}};
	    
	    int S1[][] = {{ 0, 1, 2, 3},
	    		{ 2, 0, 1, 3},
	    		{ 3, 0, 1, 0},
	    		{ 2, 1, 0, 3}};
	    
	    //System.out.println("R = " + R);
		
		String postEP = permute(R, EP);
		
		postEP = addZrs(postEP, 8);
		
		//System.out.println("(In f) EP = " + postEP);
		
		String postXor = xoring(postEP, k);
		
		//System.out.println("(In f) XOR = " + postXor);
		
		String postSBox = sBoxing(postXor.substring(0, 4), S0) + sBoxing(postXor.substring(4, 8), S1);
		
		//System.out.println("(In f) postSBox = " + postSBox);
		
		String postP4 = permute(postSBox, P4);
		
		postP4 = addZrs(postP4, 4);
		
		//System.out.println("(In f) postP4 = " + postP4);
		
		return postP4;
	}
	
	public static String sBoxing(String input, int[][] S) {
		String result = "";
		
		int row = Integer.parseInt(Character.toString(input.charAt(0)) + Character.toString(input.charAt(3)), 2);
		
		int col = Integer.parseInt(Character.toString(input.charAt(1)) + Character.toString(input.charAt(2)), 2);
		
		//System.out.println("(in sboxing) col = " + col);
		//System.out.println("(in sboxing) row = " + row);
		//System.out.println("(in sboxing) s = " + S[row][col]);
		
		result = Integer.toBinaryString(S[row][col]);
		
		result = addZrs(result, 2);
		
		return result;
	}
	
	/**
	 * @param a - one of the binary strings (must be same length as b)
	 * @param b - one of the binary strings (must be same length as a)
	 * @return - the binary string result
	 */
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
	
	/**
	 * @param orgK - the agreed key
	 * @return - an array with both k1 and k2
	 */
	public static String[] keyGeneration(String orgK) {
		String[] keys = new String[2];
		
		int[] p10 =  { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
		int[] p8 = { 6, 3, 7, 4, 8, 5, 10, 9};
		
		String postP10 = permute(orgK, p10);
	
		String firstHalf = leftShift(postP10.substring(0, 5), 1);
		String secondHalf = leftShift(postP10.substring(5, 10), 1);
		
		keys[0] = permute(firstHalf + secondHalf, p8);
		
		firstHalf = leftShift(firstHalf, 2);
		//System.out.println(firstHalf);
		secondHalf = leftShift(secondHalf, 2);
		//System.out.println(secondHalf);
		
		keys[1] = permute(firstHalf + secondHalf, p8);
		
		for(int i=0; i<keys.length; i++) {
			keys[i] = addZrs(keys[i], 8);
		}
		
		return keys;
	}
	
	/**
	 * @param input - the binary string to be shifted
	 * @param shift - how many shifts to be performed
	 * @return - the result as a binary string 
	 */
	public static String leftShift(String input, int shift) {
		int iInput = Integer.parseInt(input, 2) << shift;
		
		String result = Integer.toBinaryString(iInput);
		
		//System.out.println("result = " + result);
		
		if(result.length() > input.length()) {
			result = result.substring(result.length() - input.length(), result.length()-shift);
			for(int i=0; i<shift; i++) {
				result += input.charAt(i);
			}
		}
		return result;
	}
	
    /*
     * Generic Function to perform all permutations (think this cuts leading zeros)
     */
    public static String permute(String x, int p[]) {
    	String result = "";
    	int iX = Integer.parseInt(x, 2);
    	int y=0;
    	for(int i=0;i<p.length;i++) {
    		y=y<<1;
    		y=y|(iX>>(x.length()-p[i]))&1;
    	}
    	
    	result = Integer.toBinaryString(y);

    	return result;
    }
    
    public static String addZrs(String binaryStr, int maxLngth) {
    	int orgLngth = binaryStr.length();
		for(int j=0; j<(maxLngth-orgLngth); j++) {
			binaryStr = "0" + binaryStr;
		}
    	return binaryStr;
    }
}
