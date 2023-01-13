package SymmetricEncryption;

import java.util.Arrays;

public class SDesArray {
	public static void main(String[] args) {
		/*
		String plain = "ENIGMACODE";
		
		String kStr = "1111011000";
		
		String[] k = kStr.split("");
		//int[] y = {2,3,4,5};
		
		/*
		int[] x = concantenateArrys(k, y);
		for(int i: x) {
			System.out.print(i);
		}*/
		/*
		System.out.print("key = ");
		prntArry(k);
		
		String[][] keys = keyGeneration(k);
		System.out.print("k1 = ");
		for(String i: keys[0]) {
			System.out.print(i);
		}
		System.out.print("\n");
		System.out.print("k2 = ");
		for(String i: keys[1]) {
			System.out.print(i);
		}*/
		//System.out.println("key = " + keyGeneration(k));
		
		//System.out.println("end = " + encrypt(plain, k));
		
		 int []ip = {1,5,2,0,3,7,4,6};
		 String[] in = {"1", "0", "1", "0", "0", "0", "1", "1"};
		 String[] out = permute(in, ip);
		 prntArry(out);
 	}
	
/*
	public static String encrypt(String plaintxt, int[] key) {
		String cyphertxt =  "";
		int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7};
		int IpMinus1[] = {4, 1, 3, 5, 7, 2, 8, 6};
		
		String[][] keys = keyGeneration(key);
		
		int[] k1 = keys[0];
		int[] k2 = keys[1];
		System.out.println("k1 = " + k1);
		System.out.println("k2 = " + k2);
		
		for(int i=0; i<plaintxt.length(); i++) {
			String currentChr = Integer.toBinaryString((int) plaintxt.charAt(i));

			currentChr = addZrs(currentChr, 8);
			
			String[] crrntChr = currentChr.split("");
			
			System.out.print("currentChr = ");
			prntArry(crrntChr);
			
			String[] postIP = permute(crrntChr, IP);
			prntArry(postIP);
			
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
			
			//cyphertxt += Character.toString(Integer.parseInt(postIpMinus1, 2));
			//cyphertxt += Character.toString(Integer.parseInt(currentChr, 2));
		}
		
		return cyphertxt;
	}
	
	public static int[] f(int[] R, int[] k) {
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
		
		int[] postEP = permute(R, EP);
		
		//System.out.println("(In f) EP = " + postEP);
		
		int[] postXor = xoring(postEP, k);
		
		//System.out.println("(In f) XOR = " + postXor);
		
		int[] postSBox = concantenateArrys(sBoxing(Arrays.copyOfRange(postXor, 0, 4), S0) , sBoxing(Arrays.copyOfRange(postXor, 4, 8), S1));
		
		//System.out.println("(In f) postSBox = " + postSBox);
		
		int[] postP4 = permute(postSBox, P4);
		
		//System.out.println("(In f) postP4 = " + postP4);
		
		return postP4;
	}
	
	public static int[] sBoxing(int[] input, int[][] S) {
		int[] result = new int[2];
		
		int row = Integer.parseInt(Character.toString(input.charAt(0)) + Character.toString(input.charAt(3)), 2);
		
		int col = Integer.parseInt(Character.toString(input.charAt(1)) + Character.toString(input.charAt(2)), 2);
		
		//System.out.println("(in sboxing) col = " + col);
		//System.out.println("(in sboxing) row = " + row);
		//System.out.println("(in sboxing) s = " + S[row][col]);
		
		result = Integer.toBinaryString(S[row][col]);
		
		result = addZrs(result, 2);
		
		return result;
	}*/
	
	/**
	 * @param a - one of the binary strings (must be same length as b)
	 * @param b - one of the binary strings (must be same length as a)
	 * @return - the binary string result
	 */
	public static int[] xoring(int[] a, int[] b){
	    int[] ans = new int[a.length];
	    for (int i = 0; i < a.length; i++) {
	    	if (a[i] == b[i]) {
	    		ans[i] = 0;
	    	} else {
	    		ans[i] = 1;
	    	}
	    }
	    return ans;
	}
	
	/**
	 * @param orgK - the agreed key
	 * @return - an array with both k1 and k2
	 */
	public static String[][] keyGeneration(String[] orgK) {
		String[][] keys = new String[2][];
		
		int[] p10 =  { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
		int[] p8 = { 6, 3, 7, 4, 8, 5, 10, 9};
		
		String[] postP10 = permute(orgK, p10);
	
		String[] firstHalf =  leftShift(Arrays.copyOfRange(postP10, 0, 5),1);
		String[] secondHalf = leftShift(Arrays.copyOfRange(postP10, 5, 10),1);
		
		keys[0] = permute(concantenateArrys(firstHalf, secondHalf), p8);
		
		firstHalf = leftShift(firstHalf, 2);
		//System.out.println(firstHalf);
		secondHalf = leftShift(secondHalf, 2);
		//System.out.println(secondHalf);
		
		keys[1] =  permute(concantenateArrys(firstHalf, secondHalf), p8);
		
		return keys;
	}
	
	/**
	 * @param input - the binary string to be shifted
	 * @param shift - how many shifts to be performed
	 * @return - the result as a binary string 
	 */
	public static String[] leftShift(String[] input, int shift) {
		String[] result = new String[input.length];
		
		for(int i=0; i<input.length; i++) {
			if(i+shift < result.length) {
				result[i] = input[i+shift];
			} else {
				result[i] = input[(i+shift)-result.length];
			}
		}
		
		return result;
	}
	
    /*
     * Generic Function to perform all permutations
     */
    public static String[] permute(String[] input, int p[]) {
    	String[] result = new String[p.length];
    	for(int i=0; i<p.length; i++) {
    		if(p[i] <= input.length) {
    			result[i] = input[p[i]-1];
    		} else {
    			result[i] = "-1";
    		}
    	}
    	return result;
    }
    
    public static int[] cnvrstStrToArry(String input) {
    	int[] result = new int[input.length()];
    	for(int i=0; i<input.length(); i++) {
    		result[i] = Integer.parseInt((Character.toString(input.charAt(i))));
    	}
    	return result;
    }
    
    public static String[] concantenateArrys(String[] x, String[] y) {
    	String[] result = new String[x.length + y.length];
    	
    	for(int i=0; i<result.length; i++) {
    		if(i < x.length) {
    			result[i] = x[i];
    		} else {
    			result[i] = y[i - x.length];
    		}
    	}
    	
    	return result;
    }
    
    public static void prntArry(String[] k) {
		for(String i: k) {
			System.out.print(i);
		}
		System.out.println();
    }
    
    public static String addZrs(String binaryStr, int maxLngth) {
    	int orgLngth = binaryStr.length();
		for(int j=0; j<(maxLngth-orgLngth); j++) {
			binaryStr = "0" + binaryStr;
		}
    	return binaryStr;
    }
}
