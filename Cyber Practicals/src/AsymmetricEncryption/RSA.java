package AsymmetricEncryption;

public class RSA {

	public static void main(String[] args) {
		
		int[][] keys = keyGeneration(19, 7, 31);
		
		int[][] keys2 = keyGeneration(41, 67, 83);
		
		//System.out.println(gcd(2640, 83));
		
		//System.out.println(extEuclid(108, 31));

		System.out.println("------------");
		int[] encyption = encrypt(keys[0], "U"); 
		System.out.print("Encrypt = ");
		prntArry(encyption);
		System.out.print("Decrypt = ");
		int[] decryption = decrypt(keys[1], encyption);
		prntArry(decryption);
		
		System.out.println("------------");
		System.out.print("Encrypt = ");
		int[] encyption2 = encrypt(keys2[0], "ENIGMACODE"); 
		prntArry(encyption2);
		System.out.print("Decrypt = ");
		int[] decryption2 = decrypt(keys2[1], encyption2);
		prntArry(decryption2);
		System.out.println("------------");
		
		System.out.println("------------");
		System.out.print("Encrypt = ");
		int[] encyption3 = encrypt(keys2[0], "enigmacode"); 
		prntArry(encyption3);
		System.out.print("Decrypt = ");
		int[] decryption3 = decrypt(keys2[1], encyption3);
		prntArry(decryption3);
		System.out.println("------------");
		
		
		// System.out.println("gcd = " + gcd(15, 54));
		
		// System.out.println("extended euclid = " + extEuclid(660, 41));
		
		
		/*
		System.out.println("gcd = " + gcd(13, 41));
		System.out.println();
		System.out.println("extended euclid = " + extEuclid(660, 41));
		System.out.println();
		int[] publicKey = {37, 143};
		int[] prvKey = {13, 143};
		int[] cyphrTxt = {57, 6, 1};
		prntArry(decrypt(publicKey, cyphrTxt));
		System.out.println();
		prntArry(encrypt(publicKey, "EFA"));
		*/
		
		/*
		System.out.println("gcd = " + gcd(12, 15));
		System.out.println();
		System.out.println("relatively prime = " + (gcd(7, 23)==1));
		System.out.println();
		System.out.println("extended euclid = " + extEuclid(120, 13));
		System.out.println();
		*/
		
		/*
		int[] key = {11, 2491};
		int[] cyphrTxt = {1874, 437, 605, 632};
		prntArry(decrypt(key, cyphrTxt));
		*/
	}
	
	/**
	 * Encrypts text using the RSA encryption
	 * @param publicKey - the public key generated using the 'keyGeneration' method
	 * @param plaintxt - The plain text to be encrypted  
	 * @return - an array of encrypted integer values
	 */
	public static int[] encrypt(int[] publicKey, String plaintxt) {
		int[] cyphertxt = new int[plaintxt.length()];
		String e = Integer.toBinaryString(publicKey[0]);
		int n = publicKey[1];
		// System.out.println("n = " + n);
		
		for(int i=0; i<plaintxt.length(); i++) {
			int crrtChr = plaintxt.charAt(i) - 64;
			// System.out.println("crrtChr = " + crrtChr);
			
			// System.out.println("e = " + publicKey[0]);
			
			int C = 1;
			
			int M = crrtChr;
			
			// System.out.println("e = " + e);
			
			for(int j=0; j<e.length(); j++) {
				char ej = e.charAt(j);
				
				C = (int) (Math.pow(C, 2) % n);
				if(ej == '1') {
					C = (C*M) % n;
				}
				// System.out.println(j + " = " + C);
			}
			
			// System.out.println("C = " + C);
			// System.out.println();
			cyphertxt[i] = C;
		}
		
		return cyphertxt;
	}
	
	/**
	 * Decrypts the integer array, which was the result of the 'encrypt' method
	 * @param privateKey - the private key generated using the 'keyGeneartion' method (must use the same values as what was used when the public key was generated)
	 * @param cyphertxt - the integer array that was the result of the 'encrypt' method
	 * @return - an integer array representing the letters of the alphabet (1 - 26 corresponds to upper case letters and 33 - 58 corresponds to lower case letters e.g. 5 = E and 37 = e)
	 */
	public static int[] decrypt(int[] privateKey, int[] cyphertxt) {
		int[] plaintxt = new int[cyphertxt.length];
		
		String d = Integer.toBinaryString(privateKey[0]);
		int n = privateKey[1];
		
		for(int i=0; i<cyphertxt.length; i++) {
			int crrtChr = cyphertxt[i];
			//System.out.println("crrtChr = " + crrtChr);
			
			// System.out.println("d = " + privateKey[0]);
			
			//BigInteger C = new BigInteger("1");
			int C = 1;
			
			//BigInteger M = new BigInteger("5149713131545");
			int M = crrtChr; 
			
			//BigInteger bigN = BigInteger.valueOf(n);
			
			// System.out.println("d = " + d);
			
			for(int j=0; j<d.length(); j++) {
				char dj = d.charAt(j);
				
				C = (int) (Math.pow(C, 2) % n);
				//C = C.pow(2).mod(bigN);
				if(dj == '1') {
					//C = C.multiply(M).mod(bigN);
					C = (C*M) % n;
				}
				// System.out.println(j + " = " + C);
			}
			
			// System.out.println("C = " + C);
			// System.out.println();
			plaintxt[i] = C;
		}
		
		return plaintxt;
	}
	
	/**
	 * Generates a public and a private key
	 * @param p - a large prime number
	 * @param q - a large prime number
	 * @return - a public and private key as integer arrays contained in an outer array with the format [public key, private key]
	 */
	public static int[][] keyGeneration(int p, int q) {
		int[][] keys = new int[2][];
		
		if(isPrime(p) && isPrime(q)) {
			int n = p * q;
			
			int w = (p-1) * (q-1);
			// System.out.println("p = " + p);
			// System.out.println("q = " + q);
			// System.out.println("w = " + w);
			
			int d = w;
			while(gcd(d, w) != 1) {
				d = (int) (Math.random() * w);
			}
			
			// System.out.println("d = " + d);
			// System.out.println("d = " + d);
			
			int e = extEuclid(w, d);
			// System.out.println("eu = " + e);
			
			keys[0] = new int[]{e, n};
			keys[1] = new int[]{d, n};
		}
		
		return keys;
	}
	
	/**
	 * Generates a public and a private key
	 * @param p - a large prime number
	 * @param q - a large prime number
	 * @param forceD - a value which it and (p-1) * (q-1) has a greatest common divisor equal to 1
	 * @return - a public and private key as integer arrays contained in an outer array with the format [public key, private key]
	 */
	public static int[][] keyGeneration(int p, int q, int forceD) {
		int[][] keys = new int[2][];
		
		if(isPrime(p) && isPrime(q)) {
			int n = p * q;
			
			int w = (p-1) * (q-1);
			// System.out.println("p = " + p);
			// System.out.println("q = " + q);
			// System.out.println("w = " + w);
			
			int e = extEuclid(w, forceD);
			// System.out.println("eu = " + e);
			
			keys[0] = new int[]{e, n};
			keys[1] = new int[]{forceD, n};
		}
		
		return keys;
	}

	/**
	 * Checks if a number is prime
	 * @param num - the number to be checked
	 * @return - true if the number is prime and false otherwise
	 */
	public static boolean isPrime(int num) {
		for (int i = 2; i <= num / 2; ++i) {
			if (num % i == 0) {
	          return false;
	        }
	    }
		return true;
	}
	
	/**
	 * Uses Euclid’s Algorithm to find the greatest common divisor
	 * @param a - one of the values to find the GCD
	 * @param b - one of the values to find the GCD
	 * @return - the greatest common divisor
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
		int xi = 0;
		int xi_1 = 0;
		int xi_2 = 1;
		
		int yi = 0;
		int yi_1 = 1;
		int yi_2 = 0;
		
		// the '_' is meant to represent '-' therefore the below 
		// varaibles should read as "ri-2" and "ri-1" respectively
		int ri_2 = a;
		int ri_1 = b;
		
		int step = 3;
		// System.out.println("step = " + step++);
		int vi = (int) Math.floor(ri_2/ri_1);
		// System.out.println("(vi = ri-2/ri-1) is " + vi);
		int ri = ri_2 - (vi * ri_1);
		/*
		System.out.println("(ri = ri-2 - (vi * ri-1)) is " + ri+"="+ri_2+"-("+vi+"x"+ri_1+")");
		System.out.println("(ri = axi-2 + byi-2) - (vi(axi-1 + byi-1))) is " + ((a*xi_2 + b*yi_2) - ((a*xi_1+yi_1*b)*vi)) + "=" + a+"x"+xi_2+"+"+b+"x"+yi_2+"-"+vi+"("+a+"x"+xi_1+"+"+b+"x"+yi_1+")");
		System.out.println("(ri = a(xi-2-vixi1)+b(yi-2 -viyi-1)) is " + (a*(xi_2- (vi*xi_1))+ b*(yi_2 - (vi * yi_1))) + "=" + a+"x"+(xi_2- (vi*xi_1))+"+"+b+"x"+(yi_2 - (vi*yi_1)));
		*/
		
		if(ri < 1) {
			return -1;
		}
		
		while(ri > 1) {
			// System.out.println();
			// System.out.println("step = " + step++);
			xi = xi_2 - (vi*xi_1);
			yi = yi_2 - (vi*yi_1);
			
			ri_2 = ri_1;
			ri_1 = ri;
			
			xi_2 = xi_1;
			xi_1 = xi;
			
			yi_2 = yi_1;
			yi_1 = yi;

			vi = (int) Math.floor(ri_2/ri_1);
			// System.out.println("(vi = ri_2/ri_1) is " + vi);
			ri = ri_2 - (vi * ri_1);
			/*
			System.out.println("(ri = ri_2 - (vi * ri_1)) is " + ri+"="+ri_2+"-("+vi+"x"+ri_1+")");
			System.out.println("(ri = axi-2 + byi-2) - (vi(axi-1 + byi-1))) is " + ((a*xi_2 + b*yi_2) - ((a*xi_1+yi_1*b)*vi)) + "=" + a+"x"+xi_2+"+"+b+"x"+yi_2+"-"+"("+a+"x"+xi_1+"+"+b+"x"+yi_1+")"+vi);
			System.out.println("(ri = a(xi-2-vixi1)+b(yi-2 -viyi-1)) is " + (a*(xi_2- (vi*xi_1))+ b*(yi_2 - (vi * yi_1))) + "=" + a+"x"+(xi_2- (vi*xi_1))+"+"+b+"x"+(yi_2 - (vi*yi_1)));
			*/
			
			/*
			System.out.println("xi-1 = " + xi_1);
			System.out.println("xi-2 = " + xi_2);
			System.out.println("yi-1 = " + yi_1);
			System.out.println("yi-2 = " + yi_2);
			 */
			
			//System.out.println("check x+y = " + ((a*xi_2 + b*yi_2) - ((a*xi_1+yi_1*b)*vi)));
		}
		yi = yi_2 - (vi*yi_1);
		// System.out.println("e = " + yi);
		return yi;
	}
	
	/**
	 * Prints the elements of an array
	 * @param k - an integer array
	 */
    public static void prntArry(int[] k) {
		for(int i: k) {
			System.out.print(i + ", ");
		}
		System.out.println();
    }
}
