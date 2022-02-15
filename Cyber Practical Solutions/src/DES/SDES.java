package DES;

public class SDES {

	public static void main(String[] args) {
		String plain = "ENIGMACODE";
		
		String k = "1111011000";
		
		//System.out.println("key = " + keyGeneration(k));
		
		System.out.println("end =" + encrypt(plain, k));
	}
	
	public static String encrypt(String plaintxt, String key) {
		String cyphertxt =  "";
		int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
		
		String[] keys = keyGeneration(key);
		
		String k1 = keys[0];
		String k2 = keys[1];
		
		for(int i=0; i<plaintxt.length(); i++) {
			String currentChr = Integer.toBinaryString((int) plaintxt.charAt(i));

			
			for(int j=0; j<(8-currentChr.length()); j++) {
				currentChr = "0" + currentChr;
			}
			
			String postIP = permute(currentChr, IP);
			
			System.out.println("IP = " + postIP);
			
			String postFRK1 = f(postIP.substring(0,4), k1);
			
			System.out.println("postFRK1 = " + postFRK1);
			
			String postFk1 = xoring(postIP.substring(4,8), postFRK1) + postIP.substring(0,4);
			
			System.out.println("postFk1 = " + postFk1);
			
			String postSwap = postFk1.substring(4,8) + postFk1.substring(0,4);
			
			System.out.println("postSwap = " + postSwap);
			
			// still need to do frk2 and fk2
			
			// still need to do ip-1
			
			System.out.println();
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
	    
	    System.out.println("R = " + R);
		
		String postEP = permute(R, EP);
		
		for(int j=0; j<(8-postEP.length()); j++) {
			postEP = "0" + postEP;
		}
		
		System.out.println("EP = " + postEP);
		
		String postXor = xoring(postEP, k);
		
		System.out.println("XOR = " + postXor);
		
		String postSBox = sBoxing(postXor.substring(0, 4), S0) + sBoxing(postXor.substring(4, 8), S1);
		
		System.out.println("postSBox = " + postSBox);
		
		String postP4 = permute(postSBox, P4);
		
		for(int j=0; j<(4-postP4.length()); j++) {
			postP4 = "0" + postP4;
		}
		
		System.out.println("postP4 = " + postP4);
		
		return postP4;
	}
	
	public static String sBoxing(String input, int[][] S) {
		String result = "";
		
		int row = Integer.parseInt(Character.toString(input.charAt(0)) + Character.toString(input.charAt(3)), 2);
		
		int col = Integer.parseInt(Character.toString(input.charAt(1)) + Character.toString(input.charAt(2)), 2);
		
		//System.out.println("col = " + col);
		//System.out.println("row = " + row);
		//System.out.println("s = " + S[row][col]);
		
		result = Integer.toBinaryString(S[row][col]);
		
		for(int j=0; j<(2-result.length()); j++) {
			result = "0" + result;
		}
		
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
			for(int j=0; j<(8-keys[i].length()); j++) {
				keys[i] = "0" + keys[i];
			}
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
}
