package BasicCryptography;

public class RC4Keystream {

	public static void main(String[] args) {
		// ksa
		int[] key = {10, 12, 34, 16, 24};
		int[] S = new int[256];
		
		for(int i=0; i<S.length; i++) {
			S[i] = i;
		}
		ksa(key, S);
		prntArry(S);
		
		// ksa
		int[] key2 = {3, 123, 28, 12, 18};
		int[] S2 = new int[256];
		
		for(int i=0; i<S2.length; i++) {
			S2[i] = i;
		}
		ksa(key2, S2);
		prntArry(S2);
		
		// ksa
		int[] key3 = {6, 120, 24, 12, 10};
		int[] S4 = new int[256];
		
		for(int i=0; i<S4.length; i++) {
			S4[i] = i;
		}
		ksa(key3, S4, 1, 6);
		System.out.println(S4[1]);
		
		//prga
		int[] S3 = {3, 6, 1, 7, 2, 4, 0, 5};
		System.out.println("-------");
		prntArry(prga(S3, 1, 2, 7));
		
	}
	
	public static void ksa(int[] key, int[] S) {
		int j = 0;
		for(int i=0; i<S.length; i++) {
			j = (j + S[i] + key[i % key.length]) % S.length;
			int temp = S[i];
			S[i] = S[j];
			S[j] = temp;
			break;
		}
	}
	
	public static void ksa(int[] key, int[] S, int i, int j) {
		j = (j + S[i] + key[i % key.length]) % S.length;
		int temp = S[i];
		S[i] = S[j];
		S[j] = temp;
	}
	
	public static int[] prga(int[] S, int lenOfPlaintxt, int i, int j) {
		int[] K = new int[lenOfPlaintxt];
		
		for(int bit = 0; bit<lenOfPlaintxt; bit++) {
			i = (i+1) % S.length;
			j = (j+S[i]) % S.length;
			
			int temp = S[i];
			S[i] = S[j];
			S[j] = temp;
			//System.out.println(S[i]);
			//System.out.println(S[j]);
			K[bit] = S[(S[i] + S[j]) % S.length];
		}
		
		return K;
	}
	
	/**
	 * Prints the elements of an array
	 * @param k - an integer array
	 */
    public static void prntArry(int[] k) {
		for(int i: k) {
			System.out.print(i + ",");
		}
		System.out.println();
    }
}