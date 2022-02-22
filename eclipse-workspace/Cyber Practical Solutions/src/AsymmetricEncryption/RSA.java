package AsymmetricEncryption;

import java.math.BigInteger;

public class RSA {

	public static void main(String[] args) {
		int[][] keys = keyGeneration(19, 7, 31);
		
		int[][] keys2 = keyGeneration(41, 67, 83);
		
		//System.out.println(extEuclid(108, 31));

		int[] encyption = encrypt(keys[0], "U"); 
		prntArry(encyption);
		//System.out.println("Decrypt: ");
		int[] decryption = decrypt(keys[1], encyption);
		prntArry(decryption);
		
		System.out.println("------------");
		int[] encyption2 = encrypt(keys2[0], "ENIGMACODE"); 
		prntArry(encyption2);
		//System.out.println("Decrypt: ");
		int[] decryption2 = decrypt(keys[1], encyption2);
		prntArry(decryption2);
		
	}
	
	public static int[] encrypt(int[] publicKey, String plaintxt) {
		int[] cyphertxt = new int[plaintxt.length()];
		
		String e = Integer.toBinaryString(publicKey[0]);
		int n = publicKey[1];
		
		for(int i=0; i<plaintxt.length(); i++) {
			int crrtChr = plaintxt.charAt(i) - 64;
			//System.out.println("crrtChr = " + crrtChr);
			
			//System.out.println("e = " + publicKey[0]);
			
			//BigInteger C = new BigInteger("1");
			int C = 1;
			
			//BigInteger M = new BigInteger("5149713131545");
			int M = crrtChr; // I think this is where the problem is
			
			//BigInteger bigN = BigInteger.valueOf(n);
			
			//System.out.println("e = " + e);
			
			for(int j=0; j<e.length(); j++) {
				char ej = e.charAt(j);
				
				C = (int) (Math.pow(C, 2) % n);
				//C = C.pow(2).mod(bigN);
				if(ej == '1') {
					//C = C.multiply(M).mod(bigN);
					C = (C*M) % n;
				}
			}
			
			//System.out.println("C = " + C);
			//System.out.println();
			cyphertxt[i] = C;
		}
		
		return cyphertxt;
	}
	
	public static int[] decrypt(int[] privateKey, int[] cyphertxt) {
		int[] plaintxt = new int[cyphertxt.length];
		
		String d = Integer.toBinaryString(privateKey[0]);
		int n = privateKey[1];
		
		for(int i=0; i<cyphertxt.length; i++) {
			int crrtChr = cyphertxt[i];
			//System.out.println("crrtChr = " + crrtChr);
			
			//System.out.println("d = " + privateKey[0]);
			
			//BigInteger C = new BigInteger("1");
			int C = 1;
			
			//BigInteger M = new BigInteger("5149713131545");
			int M = crrtChr; // I think this is where the problem is
			
			//BigInteger bigN = BigInteger.valueOf(n);
			
			//System.out.println("d = " + d);
			
			for(int j=0; j<d.length(); j++) {
				char dj = d.charAt(j);
				
				C = (int) (Math.pow(C, 2) % n);
				//C = C.pow(2).mod(bigN);
				if(dj == '1') {
					//C = C.multiply(M).mod(bigN);
					C = (C*M) % n;
				}
			}
			
			//System.out.println("C = " + C);
			//System.out.println();
			plaintxt[i] = C;
		}
		
		return plaintxt;
	}
	
	public static int[][] keyGeneration(int p, int q, int forceD) {
		int[][] keys = new int[2][];
		
		if(isPrime(p) && isPrime(q)) {
			int n = p * q;
			
			int w = (p-1) * (q-1);
			//System.out.println("w = " + w);
			
			int d = w;
			while(gcd(d, w) != 1) {
				d = (int) (Math.random() * w);
			}
			
			d = forceD; // this is to get same answers as practical
			//System.out.println("d = " + d);
			
			int e = extEuclid(w, d);
			//System.out.println("eu = " + e);
			
			keys[0] = new int[]{e, n};
			keys[1] = new int[]{d, n};
		}
		
		return keys;
	}

	public static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; ++i) {
			if (num % i == 0) {
	          return false;
	        }
	    }
		return true;
	}
	
	/**
	 * Uses Euclid’s Algorithm to find greatest common divisor
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		while(b != 0) {  
			if(a > b) {  
				a = a - b;  
			} else {
				b = b - a;  
			}  
		}  
		return a;  
	}
	
	/**
	 * Calculates x within ax+by=1 where a and b is known
	 * @param a - the value a in ax+by=1
	 * @param b - the value b in ax+by=1
	 * @return - the value x in ax+by=1
	 * 
	 */
	public static int extEuclid(int a, int b) {
		int x = 0;
		
		// the '_' is meant to represent '-' therefore the below 
		// varaibles should read as "ri-2" and "ri-1" respectively
		int ri_2 = a;
		int ri_1 = b;
		
		int vi = (int) Math.floor(ri_2/ri_1);
		int ri = ri_2 - (vi * ri_1);
		
		while(ri > 1) {
			ri_2 = ri_1;
			ri_1 = ri;
			vi = (int) Math.floor(ri_2/ri_1);
			ri = ri_2 - (vi * ri_1);
		}
		
		int y = vi; /* there is no need for this declaration but 
					   it is here for better understanding and to 
					   link it back into the context of ax + by = 1 */
		x = ((y * a) + 1) / b;
		
		return x;
	}
	
    public static void prntArry(int[] k) {
		for(int i: k) {
			System.out.print(i + ", ");
		}
		System.out.println();
    }
}
