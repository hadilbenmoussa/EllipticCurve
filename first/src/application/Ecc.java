package application;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.math.MathContext;
import java.util.Random;

import application.Point;


public class Ecc {
	
		public static void main(String[] args ) throws Exception {
		
		// initial elliptic curve configuration
		//BigInteger mod = generatePrimeModule();
		BigInteger order = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16); 
		BigInteger mod = new BigInteger("199");
		//BigInteger order = new BigInteger("211"); // order of group
		
		// equation y^2 = x^3 + ax + b
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("7");
		
		Point P = new Point();
		P.setPointX(BigInteger.valueOf(2));
		P.setPointY(BigInteger.valueOf(24));
		
		//System.out.print("2P: ");
		
		
		/**********************************************************
		// key exchange - elliptic curve diffie hellman
		 * 
		 */
		
		System.out.println("***************************key exchange - elliptic curve diffie hellman*************************************");
		
		
		BigInteger ka= new BigInteger("21");
		Point alicePublic = applyDoubleAndAddMethod(P, ka, a, b, mod);
		
		System.out.println("Alice public key:");
		displayPoint(alicePublic);
		
		BigInteger kb= new BigInteger("27");
		Point bobPublic = applyDoubleAndAddMethod(P, kb, a, b, mod);
		
		System.out.println("Bob public key:");
		displayPoint(bobPublic);
		
		System.out.println("shared key:");
		Point aliceShared = applyDoubleAndAddMethod(bobPublic, ka, a, b, mod);
		Point bobShared = applyDoubleAndAddMethod(alicePublic, kb, a, b, mod);
	
		displayPoint(aliceShared);
		System.out.println(" = ");
		displayPoint(bobShared);
		
		//mod = new BigInteger("7");
		
		//System.out.println(findMultiplicativeInverse(new BigInteger("3"), mod));
		
		System.out.println("**************************Elliptic Curve Digital Signature Algorithm**************************************");
		
		/**********************************************************************
		// ecdsa  Elliptic Curve Digital Signature Algorithm
		 * 
		 */
		
		String text = "ECC beats RSA";
		
		
		BigInteger mod_Digital_Signature = generatePrimeModule();
		
		Point basePoint = new Point();
		//basePoint.setPointX(BigInteger.valueOf(2));
		//basePoint.setPointY(BigInteger.valueOf(24));
		basePoint.setPointX(new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240"));
		basePoint.setPointY(new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424"));
		

		MessageDigest md = MessageDigest.getInstance("SHA1");
		md.update(text.getBytes());
		byte[] hashByte = md.digest();
		
		BigInteger hash = new BigInteger(hashByte).abs();
		
		System.out.println("message:"+text);
		System.out.println("hash:"+hash);
		
		//BigInteger privateKey = new BigInteger("151");
		BigInteger privateKey = new BigInteger("75263518707598184987916378021939673586055614731957507592904438851787542395619");
		Point publicKey = applyDoubleAndAddMethod(basePoint, privateKey, a, b, mod_Digital_Signature);
		
		System.out.println("public key:");
		displayPoint(publicKey);
		
		//BigInteger randomKey = new BigInteger("115");
		BigInteger randomKey = new BigInteger("28695618543805844332113829720373285210420739438570883203839696518176414791234");
		Point randomPoint = applyDoubleAndAddMethod(basePoint, randomKey, a, b, mod_Digital_Signature);
		
		System.out.println("random point:");
		displayPoint(randomPoint);
		
		BigInteger order_Digital_Signature = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16);;
		
		System.out.println("order_Digital_Signature:" +order_Digital_Signature);
				
		BigInteger r = randomPoint.getPointX().remainder(order_Digital_Signature);
		
		BigInteger s = (hash.add(r.multiply(privateKey)).multiply(findMultiplicativeInverse(randomKey, order_Digital_Signature))).remainder(order_Digital_Signature);
		
		System.out.println("Signature: (r, s) = ("+r+", "+s+")");
		
		// Verification
		BigInteger w = findMultiplicativeInverse(s, order_Digital_Signature);
		Point u1 = applyDoubleAndAddMethod(basePoint, (hash.multiply(w).remainder(order_Digital_Signature)), a, b, mod_Digital_Signature);
		
		Point u2 = applyDoubleAndAddMethod(publicKey, (r.multiply(w).remainder(order_Digital_Signature)), a, b, mod_Digital_Signature);
		
		Point checkpoint = pointAddition(u1, u2, a, b, mod_Digital_Signature);
		
		System.out.println("Verify.....");
		System.out.println("check point:");
		displayPoint(checkpoint);
		
		if (checkpoint.getPointX().compareTo(r) == 0) {
			System.out.println("Signature is valid....");
		} else
		{
			System.out.println("Signature is invalid....");
		}
		
		
		System.out.println("**************************Encryption & Decryption**************************************");
		/****************************************************************
		// Encryption & Decryption 
		 */
		
		
		BigInteger mod_Encryption_Decryption = generatePrimeModule();
		BigInteger order_Encryption_Decryption = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEBAAEDCE6AF48A03BBFD25E8CD0364141", 16); 
		
		Point basePoint_Encryption_Decryption = new Point();
		basePoint_Encryption_Decryption.setPointX(new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240"));
		basePoint_Encryption_Decryption.setPointY(new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424"));
		
		Point newPoint = pointAddition(basePoint_Encryption_Decryption, basePoint_Encryption_Decryption, a, b, mod_Encryption_Decryption);
		
		for (int i=3;i<=1000;i++) {
			//System.out.println(i+"P: ");
			try {
				newPoint = pointAddition(newPoint, basePoint_Encryption_Decryption, a, b, mod_Encryption_Decryption);
				//System.out.println(i+"P: ");
				//displayPoint(newPoint);
			} catch(Exception ex) {
				break;
			}
		
		}
		
		Point messagePoint = new Point();
		
		messagePoint.setPointX(newPoint.getPointX());
		messagePoint.setPointY(newPoint.getPointY());
		System.out.println("plaintext:");
		displayPoint(messagePoint);
		
		BigInteger privateKey_Encryption_Decryption = new BigInteger("75263518707598184987916378021939673586055614731957507592904438851787542395619");
		Point publicKey_Encryption_Decryption = applyDoubleAndAddMethod(basePoint_Encryption_Decryption, privateKey_Encryption_Decryption, a, b, mod_Encryption_Decryption);
		
		System.out.println("public key:");
		displayPoint(publicKey_Encryption_Decryption);
		
		// encryption part
		//BigInteger randomKey = new BigInteger("115");
		Random rand = new Random();
		
		BigInteger randomKey_Encryption_Decryption = new BigInteger(128, rand); 
		Point c1 = applyDoubleAndAddMethod(basePoint_Encryption_Decryption, randomKey_Encryption_Decryption, a, b, mod_Encryption_Decryption);
		Point c2 = applyDoubleAndAddMethod(publicKey_Encryption_Decryption, randomKey_Encryption_Decryption, a, b, mod_Encryption_Decryption);
		c2 = pointAddition(c2, messagePoint, a, b, mod_Encryption_Decryption);
		
		System.out.println("ciphertext:");
		System.out.println("c1:");
		displayPoint(c1);
		System.out.println("c2:");
		displayPoint(c2);
		
		// decryption part
		// message = c2 - private key * c1
		Point d = applyDoubleAndAddMethod(c1, privateKey_Encryption_Decryption, a, b, mod_Encryption_Decryption);
		
		Point dInv = new Point();
		dInv.setPointX(d.getPointX());
		dInv.setPointY(d.getPointY().multiply(new BigInteger("-1")));
		
		Point decrption = pointAddition(c2, dInv, a, b, mod_Encryption_Decryption);
		System.out.println("decription message:");
		displayPoint(decrption);
		Point g =new Point();
		BigInteger gy=new BigInteger("3525120595527770847583704454622871", 10);
		BigInteger gx=new BigInteger("1534098225527667214992304222930499", 10);
		g.setPointX(gx);
		g.setPointY(gy);
		BigInteger m = new BigInteger("e95e4a5f737059dc60dfc7ad95b3d8139515620f",16);
	  
	     System.out.print("hey");
	     System.out.print(m);
		System.out.print(m.add(BigInteger.ONE));
        BigInteger a1 = new BigInteger("1970543761890640310119143205433388",10);
        BigInteger b1 = new BigInteger("1660538572255285715897238774208265",10);
        BigInteger p1 = new BigInteger("4451685225093714772084598273548427",10); 
      
        
		find_order_group(g,a1,b1,p1);
				
		
		

	}

    public static BigInteger find_order_group(Point basePoint, BigInteger a, BigInteger b, BigInteger mod) throws Exception {

    	Point Q_group = applyDoubleAndAddMethod(basePoint,mod.add(BigInteger.ONE) , a, b, mod);
       System.out.print("no prblem");
		 
		BigInteger m_group = BigInteger_sqrt(BigInteger_sqrt(mod)).add(BigInteger.ONE);
		boolean  terminate = false;
		boolean  badd = true;
		BigInteger r2 = BigInteger.ONE;
		BigInteger final_j = BigInteger.ONE;
		
		for (BigInteger j = BigInteger.ONE; j.compareTo(m_group) < 1; j = j.add(BigInteger.ONE)) {
			Point jP = applyDoubleAndAddMethod(basePoint, j, a, b, mod);
			for (BigInteger k = m_group.multiply(new BigInteger("-1")); k.compareTo(m_group) < 1; k = k.add(BigInteger.ONE)) {
				// 2*m*k x P + Q
				Point cp = applyDoubleAndAddMethod(basePoint, BigInteger.valueOf(2).multiply(m_group.multiply(k)), a, b, mod);
				cp = pointAddition(cp, Q_group, a, b, mod); 
				
				if(cp.getPointX().compareTo(jP.getPointX()) == 0) {
					
					// (mod + 1 + 2*m*k)
					r2 = mod.add(BigInteger.ONE).add(BigInteger.valueOf(2).multiply(m_group.multiply(k)));
					
					System.out.println(r2+" plus or minus "+j);
					
					final_j = j;
		
					try {
						applyDoubleAndAddMethod(basePoint, r2.add(j), a, b, mod);
						try {
							applyDoubleAndAddMethod(basePoint, r2.subtract(j), a, b, mod);
						} catch(Exception ex) {
							System.out.println("order of group: "+r2.subtract(j));
							badd=false;
						}
						
					} catch(Exception ex) {
						System.out.println("order of group: "+r2.add(j));
					}
					
					terminate = true;
					
					break;
				}
			}
			
			if (terminate) {
				break;
			}
			 
		}

		if (badd)
			return r2.add(final_j);
		else
			return r2.subtract(final_j);
    }
		
	public static BigInteger BigInteger_sqrt(BigInteger n) {
		  BigInteger a = BigInteger.ONE;
		  BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
		  while(b.compareTo(a) >= 0) {
		    BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
		    if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
		    else a = mid.add(BigInteger.ONE);
		  }
		  return a.subtract(BigInteger.ONE);
	}
		
	public static Point applyDoubleAndAddMethod(Point P, BigInteger k, BigInteger a, BigInteger b, BigInteger mod) throws Exception {
		Point tempPoint = new Point();
		tempPoint.setPointX(P.getPointX());
		tempPoint.setPointY(P.getPointY());
		
		String kAsBinary = k.toString(2);
		
		//System.out.println("("+k+")10 = ("+kAsBinary+")2");
		
		for (int i=1;i<kAsBinary.length();i++) {
			int currentBit = Integer.parseInt(kAsBinary.substring(i, i+1));
			
			tempPoint = pointAddition(tempPoint, tempPoint, a, b, mod);
			
			if (currentBit == 1) {
				tempPoint = pointAddition(tempPoint, P, a, b, mod);
			}
				
		}
		
		/*System.out.println("("+tempPoint.getPointX()+", "+tempPoint.getPointY()+")");*/
	
		return tempPoint;
	}
	
	public static Point pointAddition(Point P, Point Q, BigInteger a, BigInteger b, BigInteger mod) throws Exception {
		
		MathContext mc = new MathContext(128);
		
		BigInteger x1 = P.getPointX();
		
		BigInteger y1 = (P).getPointY();
		
		BigInteger x2 = (Q).getPointX();
		BigInteger y2 = (Q).getPointY();
		
		BigInteger beta;
		int scale = 10;
		
		if ((x1.compareTo(x2)==0)&&(y1.compareTo(y2)==0)) {
			beta = (BigInteger.valueOf(3).multiply(x1.multiply(x1)).add(a)).multiply(findMultiplicativeInverse(BigInteger.valueOf(2).multiply(y1), mod));
		}else {
			beta = (y2.subtract(y1)).multiply(findMultiplicativeInverse(x2.subtract(x1), mod));
		}
		
		BigInteger x3 = (beta.multiply(beta)).subtract(x1).subtract(x2);
		BigInteger y3 = (beta.multiply(x1.subtract(x3))).subtract(y1);
		
		while (x3.compareTo(BigInteger.valueOf(0)) < 0) {
			BigInteger times = x3.abs().divide(mod).add(BigInteger.valueOf(1));
			x3 = x3.add(times.multiply(mod));
		}
		
		while (y3.compareTo(BigInteger.valueOf(0)) < 0) {
			BigInteger times = y3.abs().divide(mod).add(BigInteger.valueOf(1));
			y3 = y3.add(times.multiply(mod));
		}
		
		x3 = x3.remainder(mod);
		y3 = y3.remainder(mod);
		
		Point R = new Point();
		
		R.setPointX(x3);
		R.setPointY(y3);
		
		//System.out.println("("+x3+", "+y3+")");
		
		return R;
		
	}
	
	public static void displayPoint(Point P) {
		
		System.out.println("("+P.getPointX()+", "+P.getPointY()+")");
	}
	
	public static BigInteger findMultiplicativeInverse(BigInteger a, BigInteger mod) {
		// extended euclidean algorithm to find multiplication

		//return a.modInverse(mod);
		
		while(a.compareTo(new BigInteger("0")) == -1) {
			a = a.add(mod);
		}
		
		BigInteger x1 = new BigInteger("1");
		BigInteger x2 = new BigInteger("0");
		BigInteger x3 = mod;
		
		BigInteger y1 = new BigInteger("0");
		BigInteger y2 = new BigInteger("1");
		BigInteger y3 = a;
		
		BigInteger q = x3.divide(y3);
		
		BigInteger t1 = x1.subtract(q.multiply(y1));
		BigInteger t2 = x2.subtract(q.multiply(y2));
		BigInteger t3 = x3.subtract(q.multiply(y3));
		
		while(y3.compareTo(new BigInteger("1"))!= 0) {
			x1 = y1;
			x2 = y2;
			x3 = y3;
			
			y1 = t1;
			y2 = t2;
			y3 = t3;
			
	        q = x3.divide(y3);
			
			t1 = x1.subtract(q.multiply(y1));
			t2 = x2.subtract(q.multiply(y2));
			t3 = x3.subtract(q.multiply(y3));
		}
		
		while (y2.compareTo(new BigInteger("0")) == -1) {
			y2 = y2.add(mod);
		}
		
		return y2;
	}
	
	public static BigInteger generatePrimeModule( ) {
		BigInteger base = new BigInteger("2");
		
		BigInteger modulo = base.pow(256)
				.subtract(base.pow(32))
				.subtract(base.pow(9))
				.subtract(base.pow(8))
				.subtract(base.pow(7))
				.subtract(base.pow(6))
				.subtract(base.pow(4))
				.subtract(base.pow(0));
		
		return modulo;
	}
}