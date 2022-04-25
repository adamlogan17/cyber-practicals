package SymmetricEncryption;

public class AES {

	public static void main(String[] args) {
		int keySize = 128;
		
		int y = 0xd4;
		
		System.out.println(dotFunct(2, "d3"));
		
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
		
		String[][] key = {{"a0", "88", "23", "2a"},
				{"fa", "54", "a3", "6c"},
				{"2c", "fe", "39", "76"},
				{"17", "b1", "39", "05"}};
		
		//prntArry(circularLeftShift(x[3], 3));
		
		/*
		for(String[] xi : shiftRow(x)) {
			prntArry(xi);
		}*/
		
		//System.out.println(binToHex(dotFunct(2, "b7")));
		
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
		
		
		
		for(String[] xi : addRoundKey(x3, key)) {
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
				result[row][col] = binToHex(xoring(binKey, binInput));
			}
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
				String ans = dotFunct(block[row][0], input[0][col]);
				//System.out.println("block = " + block[row][0]);
				//System.out.println("input = " + input[0][col]);
				//System.out.println(". = " + dotFunct(block[row][0], input[0][col]));
				for(int i=1; i<block.length; i++) {
					//System.out.println("block = " + block[row][i]);
					//System.out.println("input = " + input[i][col]);
					//System.out.println(". = " + dotFunct(block[row][i], input[i][col]));
					
					
					ans = xoring(ans, dotFunct(block[row][i], input[i][col]));
					//System.out.println("ans = " + ans);
				}
				//System.out.println();

				result[row][col] = binToHex(ans);
			}
		}
		
		return result;
	}
	
	public static String[][] shiftRow(String[][] input) {
		String[][] result = new String[input.length][input[0].length];
		
		for(int i=0; i<input.length; i++) {
			result[i] = circularLeftShift(input[i], i);
			//prntArry(circularLeftShift(input[i], i));
		}
		
		return result;
	}
	
	public static String dotFunct(int x, String hexVal) {
		String binaryVal = addZrs(hexToBin(hexVal), 8);
		if(x == 2) {
			String ans = leftShift(binaryVal,1);
			//System.out.println(ans);
			//System.out.println(binaryVal);
			if(binaryVal.charAt(0) == '1') {
				ans = xoring(ans, "00011011");
			} 
			return ans;
		} else if (x == 3) {
			return xoring(dotFunct(1, hexVal), dotFunct(2, hexVal));
		} else if (x == 1) {
			return binaryVal;
		}
		return "";
	}
	
	public static String leftShift(String input, int shift) {
		int iInput = Integer.parseInt(input, 2) << shift;
		
		String result = Integer.toBinaryString(iInput);
		
		//System.out.println("result = " + result);
		
		if(result.length() > input.length()) {
			result = result.substring(result.length() - input.length(), result.length());
		}
		return addZrs(result, input.length());
	}
	
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
	
    public static void prntArry(String[] k) {
		for(String i: k) {
			System.out.print(i + ",");
		}
		System.out.println();
    }
}
