package BasicCryptography;

public class Transpose {

	// this will crash if the length of the key is not fully divisible by the 
	// length of the cypher text (to fix this add padding)
	public static void main(String[] args) {
		int[] key = {3,2,4,1};
		System.out.println(transpose("ASYMMETRIC11", key));
		
		int[] key2 = {2,1,4,3};
		System.out.println(transpose("PUBLICKEYPAD", key2));
		
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
				System.out.print(permutedBlock[j]);
				cyphertxt += permutedBlock[j];
			}
			System.out.println();
		}
		return cyphertxt;
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
