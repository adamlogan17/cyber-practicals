package SymmetricEncryption;

public class AES {

	public static void main(String[] args) {
		int keySize = 128;
		
		int y = 0xd4;
		
		//System.out.println(dotFunct(2, "d3"));
		
		//System.out.println(dotFunct(3, "bf"));

		//System.out.println(binToHex(dotFunct(3, "bf")));
		
		String[][] x = {{"d4", "e0", "b8", "1e"},
				{"bf", "b4", "41", "27"},
				{"5d", "52", "11", "98"},
				{"30", "ae", "f1", "e5"}};
		
		String[][] x2 = {{"d4", "e0", "b8", "1e"},
				{"66", "cb", "f8", "06"},
				{"81", "19", "d3", "26"},
				{"e5", "9a", "7a", "4c"}};
		
		String[][] x3 = {{"04", "e0", "48", "28"},
				{"66", "cb", "f8", "06"},
				{"81", "19", "d3", "26"},
				{"e5", "9a", "7a", "4c"}};
		
		String[][] x4 = {{"d4", "e0", "b8", "1e"},
				{"27", "bf", "b4", "41"},
				{"11", "98", "5d", "52"},
				{"ae", "f1", "e5", "30"}};
		
		String[][] x5 = {{"19", "a0", "9a", "e9"},
				{"3d", "53", "c6", "f8"},
				{"e3", "e2", "8d", "48"},
				{"be", "2b", "2a", "08"}};
		
		String[][] key = {{"a0", "88", "23", "2a"},
				{"fa", "54", "a3", "6c"},
				{"2c", "fe", "39", "76"},
				{"17", "b1", "39", "05"}};
		
		String[][] key2 = {{"a0", "88", "23", "2a"},
				{"fa", "54", "a3", "6c"},
				{"2c", "2c", "39", "76"},
				{"17", "b1", "39", "05"}};
		
		String[][] key3 = {{"a0", "88", "23", "2a"},
				{"fa", "54", "a3", "6c"},
				{"fe", "2c", "39", "76"},
				{"17", "b1", "39", "05"}};
		
		/*
		for(String[] xi : addRoundKey(x3,key3)) {
			prntArry(xi);
		}*/
		
		//prntArry(circularLeftShift(x[3], 3));
		
		/*
		for(String[] xi : shiftRow(x)) {
			prntArry(xi);
		}*/
		
		//System.out.println(binToHex(dotFunct(2, "D3")));
		
		/*
		for(String[] xi : mixCol(x)) {
			prntArry(xi);
		}*/
		
		/*
		// mix col equations 
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.println(mixColEq(i, j));
			}
		}*/
		
		/*
		System.out.println();
		System.out.println(mixColEq(2,1));
		System.out.println();
		System.out.println(binToHex(dotFunct(2, "b7")));
		*/
		System.out.println();
	
		/*
		for(String[] xi : addRoundKey(x3, key)) {
			prntArry(xi);
		}*/
		
		encrypt(x5, key2);
	}
	
	/**
	 * Encrypts the input using the AES algorithm 
	 * @param input - the input to be encrypted
	 * @param key - the secret key to be used
	 */
	public static void encrypt(String[][] input, String[][] key) {
		input = subBytes(input);
		for(String[] xi : input) {
			prntArry(xi);
		}
		System.out.println();
		input = shiftRow(input);
		for(String[] xi : input) {
			prntArry(xi);
		}
		System.out.println();
		input = mixCol(input);
		for(String[] xi : input) {
			prntArry(xi);
		}
		System.out.println();
		input = addRoundKey(input, key);
		for(String[] xi : input) {
			prntArry(xi);
		}
		System.out.println();
		for(String[] xi : input) {
			prntArry(xi);
		}
	}
	
	public static String mixColEq(int x, int y) {
		String equation = "S'" + x + "," + y + " = ";
		int[][] block = {{2, 3, 1, 1},
				{1, 2, 3, 1},
				{1, 1, 2, 3},
				{3, 1, 1, 2}};
		
		String[][] S = {{"0,0", "0,1", "0,2", "0,3"},
				{"1,1", "1,2", "1,3", "1,0"},
				{"2,2", "2,3", "2,0", "2,1"},
				{"3,3", "3,0", "3,1", "3,2"}};
		
		for(int i=0; i<block.length; i++) {
			equation += "(" + block[x][i] + "." + "S" + S[i][y] + ")";
			if(i+1 !=  block.length) {
				equation += " xor ";
			}
		}
		
		return equation;
	}
	
	public static String[][] addRoundKey(String[][] input, String[][] key) {
		String[][] result = new String[input.length][input[0].length];
		
		for(int row=0; row<input.length; row++) {
			for(int col=0; col<input[row].length; col++) {
				String binKey = addZrs(hexToBin(key[row][col]), 8);
				String binInput = addZrs(hexToBin(input[row][col]), 8);
				
				//System.out.println("input = " + input[row][col]);
				//System.out.println("key = " + key[row][col]);
				//System.out.println("binInput = " + binInput);
				//System.out.println("binKey = " + binKey);
				//System.out.println();
				
				System.out.println(binKey + " xor " + binInput + " = " + xoring(binKey, binInput));
				result[row][col] = binToHex(xoring(binKey, binInput));
			}
			System.out.println();
		}
		
		return result;
	}
	
	public static String[][] mixCol(String[][] input) {
		String[][] result = new String[input.length][input[0].length];
		int[][] block = {{2, 3, 1, 1},
				{1, 2, 3, 1},
				{1, 1, 2, 3},
				{3, 1, 1, 2}};
		
		for(int row=0; row<result.length; row++) {
			for(int col=0; col<result[row].length; col++) {
				System.out.println(mixColEq(row, col));
				String ans = dotFunct(block[row][0], input[0][col]);
				System.out.println();
				
				//System.out.println("block = " + block[row][0]);
				//System.out.println("input = " + input[0][col]);
				//System.out.println("("+block[row][0] + "." + input[0][col] + ") = " + dotFunct(block[row][0], input[0][col]));
				for(int i=1; i<block.length; i++) {
					//System.out.println("block = " + block[row][i]);
					//System.out.println("input = " + input[i][col]);
					//System.out.println("("+block[row][i] + "." + input[i][col] + ") = " + dotFunct(block[row][i], input[i][col]));
					
					ans = xoring(ans, dotFunct(block[row][i], input[i][col]));
					System.out.println("ans = " + ans);
					System.out.println();
				}
				System.out.println();

				result[row][col] = binToHex(ans);
			}
		}
		
		return result;
	}
	
	/**
	 * Performs a circular left shift on each row, with the number of times the shift is to occur is dependent on the row number
	 * @param input - a 2D array of binary strings 
	 * @return - the shifted result
	 */
	public static String[][] shiftRow(String[][] input) {
		String[][] result = new String[input.length][input[0].length];
		
		for(int i=0; i<input.length; i++) {
			result[i] = circularLeftShift(input[i], i);
			//prntArry(circularLeftShift(input[i], i));
		}
		
		return result;
	}
	
	public static String dotFunct(int x, String hexVal) {
		System.out.println("(" + x + "." + hexVal + ")");
		String binaryVal = addZrs(hexToBin(hexVal), 8);
		if(x == 2) {
			String ans = leftShift(binaryVal,1);
			//System.out.println(ans);
			System.out.println("Shift " + binaryVal + " = " + ans);
			if(binaryVal.charAt(0) == '1') {
				System.out.print(ans + " xor 00011011 = ");
				ans = xoring(ans, "00011011");
				System.out.println(ans);
			} 
			return ans;
		} else if (x == 3) {
			String dot1 = dotFunct(1, hexVal);
			String dot2 = dotFunct(2, hexVal);
			System.out.println("Result:");
			System.out.println(dot1 + " xor " + dot2 + " = " + xoring(dot1, dot2));
			return xoring(dot1, dot2);
		} else if (x == 1) {
			System.out.println("result = " + binaryVal);
			return binaryVal;
		}
		return "";
	}
	
	/**
	 * Performs a left shift on a binary string 
	 * @param input - the binary string in which the shift will be performed on 
	 * @param shift - the number of times a shift is to be performed
	 * @return - the shifted binary string
	 */
	public static String leftShift(String input, int shift) {
		int iInput = Integer.parseInt(input, 2) << shift;
		
		String result = Integer.toBinaryString(iInput);
		
		//System.out.println("result = " + result);
		
		if(result.length() > input.length()) {
			result = result.substring(result.length() - input.length(), result.length());
		}
		return addZrs(result, input.length());
	}
	
	/**
	 * Performs a circular left shift on a binary string 
	 * @param input - the binary string in which the shift will be performed on 
	 * @param shift - the number of times a shift is to be performed
	 * @return - the shifted binary string
	 */
	public static String[] circularLeftShift(String[] input, int shift) {
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
	
	/**
	 * Performs a XOR operation on two binary strings, with the same length
	 * @param a - one of the values to XOR
	 * @param b - one of the values to XOR
	 * @return - the resulting binary string
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
	 * Pads the binary sting with 0s until the string is the correct length
	 * @param binaryStr - the binary string in which needs to be padded
	 * @param maxLngth - the required length of the binary string
	 * @return - the padded binary string
	 */
    public static String addZrs(String binaryStr, int maxLngth) {
    	int orgLngth = binaryStr.length();
		for(int j=0; j<(maxLngth-orgLngth); j++) {
			binaryStr = "0" + binaryStr;
		}
    	return binaryStr;
    }

    /**
     * Converts a binary string to hexadecimal
     * @param bin - the binary string to be converted
     * @return - the hexadecimal value as a string
     */
	public static String binToHex(String bin) {
		return Integer.toHexString(Integer.parseInt(bin, 2));
	}
	
	/**
     * Converts a hexadecimal value to binary
     * @param bin - the hexadecimal value to be converted
     * @return - the binary string
     */
	public static String hexToBin(String hex) {
		return Integer.toBinaryString(Integer.parseInt(hex, 16));
	}
	
    public static void prntArry(String[] k) {
		for(String i: k) {
			System.out.print(i + ",");
		}
		System.out.println();
    }
    
	public static String[][] subBytes(String[][] input) {
		String[][] sBox = {{"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
				{"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
				{"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
				{"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
				{"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
				{"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
				{"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
				{"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
				{"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
				{"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
				{"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
				{"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
				{"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
				{"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
				{"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
				{"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}};
		 
		for(int i=0; i<input.length; i++) {
			for(int j=0; j<input[i].length; j++) {
				String currVal = input[i][j];
				char col = currVal.charAt(0);
				char row = currVal.charAt(1);
				int colAsInt = Character.digit(col,16);
				int rowAsInt = Character.digit(row,16);
				input[i][j] = sBox[colAsInt][rowAsInt];
			}
		}
		return input;
	}
}
