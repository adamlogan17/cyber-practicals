package BasicCryptography;

public class RC4Keystream {

	public static void main(String[] args) {
		int[] key = {3, 123, 28, 12, 18};
		int[] S = new int[256];
		int[] S2 = {3, 6, 1, 7, 2, 4, 0, 5};
		for(int i=0; i<S.length; i++) {
			S[i] = i;
		}
		
		prntArry(S);
		
		ksa(key, S);
		
		prntArry(S);
		
		prntArry(prga(S2, 1, 4, 5));
		
	}
	
	public static void ksa(int[] key, int[] S) {
		int j = 0;
		for(int i=0; i<S.length; i++) {
			j = (j + S[i] + key[i % key.length]) % S.length;
			int temp = S[i];
			S[i] = S[j];
			S[j] = temp;
		}
	}
	
	public static int[] prga(int[] S, int lenOfPlaintxt, int i, int j) {
		int[] K = new int[lenOfPlaintxt];
		
		for(int bit = 0; bit<lenOfPlaintxt; bit++) {
			i = (i+1) % S.length;
			j = (j+S[i]) % S.length;
			
			int temp = S[i];
			S[i] = S[j];
			S[j] = temp;
			System.out.println(S[i]);
			System.out.println(S[j]);
			K[bit] = S[(S[i] + S[j]) % S.length];
		}
		
		return K;
	}
	
    public static void prntArry(int[] k) {
		for(int i: k) {
			System.out.print(i + ",");
		}
		System.out.println();
    }
}
