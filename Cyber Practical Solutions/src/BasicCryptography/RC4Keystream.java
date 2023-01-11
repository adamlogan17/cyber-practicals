package BasicCryptography;

public class RC4Keystream {

	public static void main(String[] args) {
		int[] key = {10, 12, 34, 16, 24};
		int[] S = new int[256];
		
		for(int i=0; i<S.length; i++) {
			S[i] = i;
		}
		
		//prntArry(S);
		
		
		ksa(key, S);
		
		//int[] key2 = {6, 120, 24, 12, 10};
		//ksa(key2, S, 1, 6);
		
		prntArry(S);
		
		int[] S2 = {3, 6, 1, 7, 2, 4, 0, 5};
		//System.out.println("-------");
		//prntArry(prga(S2, 1, 2, 7));
		
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
		
		prntArry(S);
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
	
    public static void prntArry(int[] k) {
		for(int i: k) {
			System.out.print(i + ",");
		}
		System.out.println();
    }
}