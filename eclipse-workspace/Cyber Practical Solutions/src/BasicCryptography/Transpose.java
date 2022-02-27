package BasicCryptography;

public class Transpose {

	public static void main(String[] args) {
		int[] key = {2,1,4,3};
		System.out.println(transpose("PUBLICKEYPAD", key));
		
		// to decrypt just use the 'transpose' function again
	}
	
	public static String transpose(String plaintxt, int[] key) {
		String cyphertxt = "";
		String[] plntxtArry = plaintxt.split("");
		
		for(int i=0; i < plntxtArry.length; i+=key.length) {
			String[] block = new String[key.length];
			for(int j=0; j<block.length; j++) {
				block[j] = plntxtArry[j+i];
			}
			
			String[] permutedBlock = permute(block, key);
			for(int j=0; j<permutedBlock.length; j++) {
				cyphertxt += permutedBlock[j];
			}
		}
		return cyphertxt;
	}

    public static void prntArry(String[] k) {
		for(String i: k) {
			System.out.print(i);
		}
		System.out.println();
    }
    
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
}
