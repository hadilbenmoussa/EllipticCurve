/*package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppLauncher extends Application {

	public static void main(String[] args) {
		Application.launch(MainAppLauncher.class, args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/MainApp.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("JavaFX Graph Example");
			stage.show();
		} catch (Exception e) {
			System.out.print(e);
		}
	}


}*/
package application;

import java.math.BigInteger;

/**
 * This class represents Elliptic Curve in Galois Field G(p). The equation will
 * be in form  y^2 = x^3 + ax + b (mod p), for which a and b satisfy
 * 4a^3 + 27b^2 != 0 (mod p).
 * 
 * A point in the elliptic curve will be represented as a pair of BigInteger,
 * which is represented as ECPoint class.
 * 
 * This class implements some very basic operations of Points in the elliptic
 * curve, which are addition, multiplication (scalar), and subtraction.
 * 
 
 */
public class EllipticCurve {
    
    // The three parameters of the elliptic curve equation.
    private BigInteger a;
    private BigInteger b;
    private BigInteger p;
    
    // Optional attribute, the base point g.
    private ECPoint g = null;
    private BigInteger order;
    
    // some BigInteger constants that might help us in some calculations
    private static BigInteger THREE = new BigInteger("3");
    
    public EllipticCurve(BigInteger a, BigInteger b, BigInteger p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }
    
    public EllipticCurve(BigInteger a, BigInteger b, BigInteger p, ECPoint g,BigInteger order) {
        this.a = a;
        this.b = b;
        this.p = p;
        this.g = g;
        this.order=order;
    }
    
    public EllipticCurve(long a, long b, long p) {
        this.a = BigInteger.valueOf(a);
        this.b = BigInteger.valueOf(b);
        this.p = BigInteger.valueOf(p);
    }

    public EllipticCurve(long a, long b, long p, ECPoint g) {
        this.a = BigInteger.valueOf(a);
        this.b = BigInteger.valueOf(b);
        this.p = BigInteger.valueOf(p);
        this.g = g;
    }
    
    public ECPoint getBasePoint() {
        return g;
    }
    
    public void setBasePoint(ECPoint g) {
        this.g = g;
    }
    
    public BigInteger getA() {
        return a;
    }
    
    public BigInteger getB() {
        return b;
    }
    
    public BigInteger getP() {
        return p;
    }
    public BigInteger getOrder() {
        return order;
    }
    
    // We provide some standard curves
    // Source: http://csrc.nist.gov/groups/ST/toolkit/documents/dss/NISTReCur.pdf
    
    public static final EllipticCurve brainpoolP160r1 = new EllipticCurve(
            new BigInteger("340e7be2a280eb74e2be61bada745d97e8f7c300",16),
            new BigInteger("1e589a8595423412134faa2dbdec95c8d8675e58", 16),
            new BigInteger("e95e4a5f737059dc60dfc7ad95b3d8139515620f",16),
            new ECPoint(
                    new BigInteger("bed5af16ea3f6a4f62938c4631eb5af7bdbcdbc3", 16),
                    new BigInteger("1667cb477a1a8ec338f94741669c976316da6321", 16)),new BigInteger("e95e4a5f737059dc60df5991d45029409e60fc09",16)
            
    );

    /**
     * Warning: p = 1 (mod 4), cannot be used throughout the algorithm.
     */
    public static final EllipticCurve brainpoolP192r1 = new EllipticCurve(
            new BigInteger("6a91174076b1e0e19c39c031fe8685c1cae040e5c69a28ef",16),
            new BigInteger("469a28ef7c28cca3dc721d044f4496bcca7ef4146fbf25c9", 16),
            new BigInteger("c302f41d932a36cda7a3463093d18db78fce476de1a86297",16),
            new ECPoint(
                    new BigInteger("c0a0647eaab6a48753b033c56cb0f0900a2f5c4853375fd6", 16),
                    new BigInteger("14b690866abd5bb88b5f4828c1490002e6773fa2fa299b8f", 16)
            ),new BigInteger("c302f41d932a36cda7a3462f9e9e916b5be8f1029ac4acc1", 16)
    );
    
    public static final EllipticCurve brainpoolP224r1 = new EllipticCurve(
            new BigInteger("68a5e62ca9ce6c1c299803a6c1530b514e182ad8b0042a59cad29f43",16),
            new BigInteger("2580f63ccfe44138870713b1a92369e33e2135d266dbb372386c400b", 16),
            new BigInteger("d7c134aa264366862a18302575d1d787b09f075797da89f57ec8c0ff",16),
            new ECPoint(
                    new BigInteger("d9029ad2c7e5cf4340823b2a87dc68c9e4ce3174c1e6efdee12c07d", 16),
                    new BigInteger("58aa56f772c0726f24c6b89e4ecdac24354b9e99caa3f6d3761402cd", 16)
            ),new BigInteger("d7c134aa264366862a18302575d0fb98d116bc4b6ddebca3a5a7939f", 16)
    );
    public static final EllipticCurve brainpoolP256r1 = new EllipticCurve(
            new BigInteger("7d5a0975fc2c3057eef67530417affe7fb8055c126dc5c6ce94a4b44f330b5d9",16),
            new BigInteger("26dc5c6ce94a4b44f330b5d9bbd77cbf958416295cf7e1ce6bccdc18ff8c07b6", 16),
            new BigInteger("a9fb57dba1eea9bc3e660a909d838d726e3bf623d52620282013481d1f6e5377",16),
            new ECPoint(
                    new BigInteger("8bd2aeb9cb7e57cb2c4b482ffc81b7afb9de27e1e3bd23c23a4453bd9ace3262", 16),
                    new BigInteger("547ef835c3dac4fd97f8461a14611dc9c27745132ded8e545c1d54c72f046997", 16)
            ),new BigInteger("a9fb57dba1eea9bc3e660a909d838d718c397aa3b561a6f7901e0e82974856a7", 16)
    );
    public static final EllipticCurve brainpoolP320r1 = new EllipticCurve(
            new BigInteger("3ee30b568fbab0f883ccebd46d3f3bb8a2a73513f5eb79da66190eb085ffa9f492f375a97d860eb4",16),
            new BigInteger("520883949dfdbc42d3ad198640688a6fe13f41349554b49acc31dccd884539816f5eb4ac8fb1f1a6", 16),
            new BigInteger("d35e472036bc4fb7e13c785ed201e065f98fcfa6f6f40def4f92b9ec7893ec28fcd412b1f1b32e27",16),
            new ECPoint(
                    new BigInteger("43bd7e9afb53d8b85289bcc48ee5bfe6f20137d10a087eb6e7871e2a10a599c710af8d0d39e20611", 16),
                    new BigInteger("14fdd05545ec1cc8ab4093247f77275e0743ffed117182eaa9c77877aaac6ac7d35245d1692e8ee1", 16)
            ),new BigInteger("d35e472036bc4fb7e13c785ed201e065f98fcfa5b68f12a32d482ec7ee8658e98691555b44c59311", 16)
    );
    public static final EllipticCurve brainpoolP384r1 = new EllipticCurve(
            new BigInteger("7bc382c63d8c150c3c72080ace05afa0c2bea28e4fb22787139165efba91f90f8aa5814a503ad4eb04a8c7dd22ce2826",16),
            new BigInteger("4a8c7dd22ce28268b39b55416f0447c2fb77de107dcd2a62e880ea53eeb62d57cb4390295dbc9943ab78696fa504c11", 16),
            new BigInteger("8cb91e82a3386d280f5d6f7e50e641df152f7109ed5456b412b1da197fb71123acd3a729901d1a71874700133107ec53",16),
            new ECPoint(
                    new BigInteger("1d1c64f068cf45ffa2a63a81b7c13f6b8847a3e77ef14fe3db7fcafe0cbd10e8e826e03436d646aaef87b2e247d4af1e", 16),
                    new BigInteger("8abe1d7520f9c2a45cb1eb8e95cfd55262b70b29feec5864e19c054ff99129280e4646217791811142820341263c5315", 16)
            ),new BigInteger("8cb91e82a3386d280f5d6f7e50e641df152f7109ed5456b31f166e6cac0425a7cf3ab6af6b7fc3103b883202e9046565", 16)
    );
    public static final EllipticCurve  brainpoolP512r1= new EllipticCurve(
            new BigInteger("7830a3318b603b89e2327145ac234cc594cbdd8d3df91610a83441caea9863bc2ded5d5aa8253aa10a2ef1c98b9ac8b57f1117a72bf2c7b9e7c1ac4d77fc94ca",16),
            new BigInteger("3df91610a83441caea9863bc2ded5d5aa8253aa10a2ef1c98b9ac8b57f1117a72bf2c7b9e7c1ac4d77fc94cadc083e67984050b75ebae5dd2809bd638016f723", 16),
            new BigInteger("aadd9db8dbe9c48b3fd4e6ae33c9fc07cb308db3b3c9d20ed6639cca703308717d4d9b009bc66842aecda12ae6a380e62881ff2f2d82c68528aa6056583a48f3",16),
            new ECPoint(
                    new BigInteger("81aee4bdd82ed9645a21322e9c4c6a9385ed9f70b5d916c1b43b62eef4d0098eff3b1f78e2d0d48d50d1687b93b97d5f7c6d5047406a5e688b352209bcb9f822", 16),
                    new BigInteger("7dde385d566332ecc0eabfa9cf7822fdf209f70024a57b1aa000c55b881f8111b2dcde494a5f485e5bca4bd88a2763aed1ca2b2fa8f0540678cd1e0f3ad80892", 16)
            ),new BigInteger("aadd9db8dbe9c48b3fd4e6ae33c9fc07cb308db3b3c9d20ed6639cca70330870553e5c414ca92619418661197fac10471db1d381085ddaddb58796829ca90069", 16)
    );
    public static final EllipticCurve secp112r1 = new EllipticCurve(
            new BigInteger("db7c2abf62e35e668076bead2088",16),
            new BigInteger("659ef8ba043916eede8911702b22", 16),
            new BigInteger("db7c2abf62e35e668076bead208b",16),
            new ECPoint(
                    new BigInteger("9487239995a5ee76b55f9c2f098", 16),
                    new BigInteger("a89ce5af8724c0a23e0e0ff77500", 16)
            ),new BigInteger("db7c2abf62e35e7628dfac6561c5", 16)
    );
    public static final EllipticCurve  secp112r2= new EllipticCurve(
            new BigInteger("6127c24c05f38a0aaaf65c0ef02c",16),
            new BigInteger("51def1815db5ed74fcc34c85d709", 16),
            new BigInteger("db7c2abf62e35e668076bead208b",16),
            new ECPoint(
                    new BigInteger("4ba30ab5e892b4e1649dd0928643", 16),
                    new BigInteger("adcd46f5882e3747def36e956e97", 16)
            ),new BigInteger("36df0aafd8b8d7597ca10520d04b", 16)
    );
    
    public static final EllipticCurve  secp128r1= new EllipticCurve(
            new BigInteger("fffffffdfffffffffffffffffffffffc",16),
            new BigInteger("e87579c11079f43dd824993c2cee5ed3", 16),
            new BigInteger("fffffffdffffffffffffffffffffffff",16),
            new ECPoint(
                    new BigInteger("161ff7528b899b2d0c28607ca52c5b86", 16),
                    new BigInteger("cf5ac8395bafeb13c02da292dded7a83", 16)
            ),new BigInteger("fffffffe0000000075a30d1b9038a115", 16)
    );
    
    public static final EllipticCurve  secp128r2= new EllipticCurve(
            new BigInteger("d6031998d1b3bbfebf59cc9bbff9aee1",16),
            new BigInteger("5eeefca380d02919dc2c6558bb6d8a5d", 16),
            new BigInteger("fffffffdffffffffffffffffffffffff",16),
            new ECPoint(
                    new BigInteger("7b6aa5d85e572983e6fb32a7cdebc140", 16),
                    new BigInteger("27b6916a894d3aee7106fe805fc34b44", 16)
            ),new BigInteger("3fffffff7fffffffbe0024720613b5a3", 16)
    );
    public static final EllipticCurve  secp160k1= new EllipticCurve(
            new BigInteger("0",16),
            new BigInteger("7", 16),
            new BigInteger("fffffffffffffffffffffffffffffffeffffac73",16),
            new ECPoint(
                    new BigInteger("3b4c382ce37aa192a4019e763036f4f5dd4d7ebb", 16),
                    new BigInteger("938cf935318fdced6bc28286531733c3f03c4fee", 16)
            ),new BigInteger("100000000000000000001b8fa16dfab9aca16b6b3", 16)
    );
  
    public static final EllipticCurve secp160r1 = new EllipticCurve(
            new BigInteger("ffffffffffffffffffffffffffffffff7ffffffc",16),
            new BigInteger("1c97befc54bd7a8b65acf89f81d4d4adc565fa45", 16),
            new BigInteger("ffffffffffffffffffffffffffffffff7fffffff",16),
            new ECPoint(
                    new BigInteger("4a96b5688ef573284664698968c38bb913cbfc82", 16),
                    new BigInteger("23a628553168947d59dcc912042351377ac5fb32", 16)
            ),new BigInteger("100000000000000000001f4c8f927aed3ca752257", 16)
    );
    public static final EllipticCurve  secp160r2= new EllipticCurve(
            new BigInteger("fffffffffffffffffffffffffffffffeffffac70",16),
            new BigInteger("b4e134d3fb59eb8bab57274904664d5f50388ba", 16),
            new BigInteger("fffffffffffffffffffffffffffffffeffffac73",16),
            new ECPoint(
                    new BigInteger("52dcb034293a117e1f4ff11b30f7199d3144ce6d", 16),
                    new BigInteger("feaffef2e331f296e071fa0df9982cfea7d43f2e", 16)
            ),new BigInteger("100000000000000000000351ee786a818f3a1a16b", 16)
    );
    public static final EllipticCurve  secp192k1= new EllipticCurve(
            new BigInteger("0",16),
            new BigInteger("3", 16),
            new BigInteger("fffffffffffffffffffffffffffffffffffffffeffffee37",16),
            new ECPoint(
                    new BigInteger("db4ff10ec057e9ae26b07d0280b7f4341da5d1b1eae06c7d", 16),
                    new BigInteger("9b2f2f6d9c5628a7844163d015be86344082aa88d95e2f9d", 16)
            ),new BigInteger("fffffffffffffffffffffffe26f2fc170f69466a74defd8d", 16)
    );
    public static final EllipticCurve secp224k1 = new EllipticCurve(
            new BigInteger("0",16),
            new BigInteger("5", 16),
            new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffeffffe56d",16),
            new ECPoint(
                    new BigInteger("a1455b334df099df30fc28a169a467e9e47075a90f7e650eb6b7a45c", 16),
                    new BigInteger("7e089fed7fba344282cafbd6f7e319f7c0b0bd59e2ca4bdb556d61a5", 16)
            ),new BigInteger("10000000000000000000000000001dce8d2ec6184caf0a971769fb1f7", 16)
    );
    public static final EllipticCurve secp224r1 = new EllipticCurve(
            new BigInteger("fffffffffffffffffffffffffffffffefffffffffffffffffffffffe",16),
            new BigInteger("b4050a850c04b3abf54132565044b0b7d7bfd8ba270b39432355ffb4", 16),
            new BigInteger("ffffffffffffffffffffffffffffffff000000000000000000000001",16),
            new ECPoint(
                    new BigInteger("b70e0cbd6bb4bf7f321390b94a03c1d356c21122343280d6115c1d21", 16),
                    new BigInteger("bd376388b5f723fb4c22dfe6cd4375a05a07476444d5819985007e34", 16)
            ),new BigInteger("ffffffffffffffffffffffffffff16a2e0b8f03e13dd29455c5c2a3d", 16)
    );
    public static final EllipticCurve  secp256k1= new EllipticCurve(
            new BigInteger("0",16),
            new BigInteger("7", 16),
            new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffffffffffefffffc2f",16),
            new ECPoint(
                    new BigInteger("79be667ef9dcbbac55a06295ce870b07029bfcdb2dce28d959f2815b16f81798", 16),
                    new BigInteger("483ada7726a3c4655da4fbfc0e1108a8fd17b448a68554199c47d08ffb10d4b8", 16)
            ),new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16)
    );
    public static final EllipticCurve  secp384r1= new EllipticCurve(
            new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffeffffffff0000000000000000fffffffc",16),
            new BigInteger("b3312fa7e23ee7e4988e056be3f82d19181d9c6efe8141120314088f5013875ac656398d8a2ed19d2a85c8edd3ec2aef", 16),
            new BigInteger("fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffeffffffff0000000000000000ffffffff",16),
            new ECPoint(
                    new BigInteger("aa87ca22be8b05378eb1c71ef320ad746e1d3b628ba79b9859f741e082542a385502f25dbf55296c3a545e3872760ab7", 16),
                    new BigInteger("3617de4a96262c6f5d9e98bf9292dc29f8f41dbd289a147ce9da3113b5f0b8c00a60b1ce1d7e819d7a431d7c90ea0e5f", 16)
            ),new BigInteger("ffffffffffffffffffffffffffffffffffffffffffffffffc7634d81f4372ddf581a0db248b0a77aecec196accc52973", 16)
    );
    
    public static final EllipticCurve secp521r1 = new EllipticCurve(
            new BigInteger("1fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffc",16),
            new BigInteger("51953eb9618e1c9a1f929a21a0b68540eea2da725b99b315f3b8b489918ef109e156193951ec7e937b1652c0bd3bb1bf073573df883d2c34f1ef451fd46b503f00", 16),
            new BigInteger("1ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff",16),
            new ECPoint(
                    new BigInteger("c6858e06b70404e9cd9e3ecb662395b4429c648139053fb521f828af606b4d3dbaa14b5e77efe75928fe1dc127a2ffa8de3348b3c1856a429bf97e7e31c2e5bd66", 16),
                    new BigInteger("11839296a789a3bc0045c8a5fb42c7d1bd998f54449579b446817afbd17273e662c97ee72995ef42640c550b9013fad0761353c7086a272c24088be94769fd16650", 16)
            ),new BigInteger("1fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffa51868783bf2f966b7fcc0148f709a5d03bb5c9b8899c47aebb6fb71e91386409", 16)
    );
    
   
  
    

    
    /**
     * This method will check whether a point belong to this curve or not.
     */
    public boolean isPointInsideCurve(ECPoint point) {
        if (point.isPointOfInfinity()) return true;
        
        return point.x.multiply(point.x).mod(p).add(a).multiply(point.x).add(b)
                .mod(p).subtract(point.y.multiply(point.y)).mod(p)
                .compareTo(BigInteger.ZERO) == 0;
    }
    
    /**
     * Add two points. The result of this addition is the reflection of the
     * intersection of the line formed by the two points to the same curve with
     * respect to the x-axis. The line is the tangent when the two points equal.
     * 
     * The result will be point of infinity when the line is parallel to the
     * y-axis.
     * 
     * If one of them is point of infinity, then the other will be returned.
     * 
     * @param p1
     * @param p2
     * @return 
     */
    public ECPoint add(ECPoint p1, ECPoint p2) {
        if (p1 == null || p2 == null) return null;
        
        if (p1.isPointOfInfinity()) {
            return new ECPoint(p2);
        } else if (p2.isPointOfInfinity()) {
            return new ECPoint(p1);
        }
        
        // The lambda (the slope of the line formed by the two points) are
        // different when the two points are the same.
        BigInteger lambda;
        if (p1.x.subtract(p2.x).mod(p).compareTo(BigInteger.ZERO) == 0) {
            if (p1.y.subtract(p2.y).mod(p).compareTo(BigInteger.ZERO) == 0) {
                // lambda = (3x1^2 + a) / (2y1)
                BigInteger nom = p1.x.multiply(p1.x).multiply(THREE).add(a);
                BigInteger den = p1.y.add(p1.y);
                lambda = nom.multiply(den.modInverse(p));
            } else {
                // lambda = infinity
                return ECPoint.INFINTIY;
            }
        } else {
            // lambda = (y2 - y1) / (x2 - x1)
            BigInteger nom = p2.y.subtract(p1.y);
            BigInteger den = p2.x.subtract(p1.x);
            lambda = nom.multiply(den.modInverse(p));
        }
        
        // Now the easy part:
        // The result is (lambda^2 - x1 - y1, lambda(x2 - xr) - yp)
        BigInteger xr = lambda.multiply(lambda).subtract(p1.x).subtract(p2.x).mod(p);
        BigInteger yr = lambda.multiply(p1.x.subtract(xr)).subtract(p1.y).mod(p);
        return new ECPoint(xr, yr);
    }
    
    /**
     * Subtract two points, according to this equation: p1 - p2 = p1 + (-p2),
     * where -p2 is the reflection of p2 with respect to the x-axis.
     * 
     * @param p1
     * @param p2
     * @return 
     */
    public ECPoint subtract(ECPoint p1, ECPoint p2) {
        if (p1 == null || p2 == null) return null;
        
        return add(p1, p2.negate());
    }

    /**
     * Multiply p1 to a scalar n. That is, perform addition n times. The
     * following method implements divide and conquer approach.
     * 
     * @param p1
     * @param n
     * @return 
     */
    public ECPoint multiply(ECPoint p1, BigInteger n) {
        if (p1.isPointOfInfinity()) {
            return ECPoint.INFINTIY;
        }
        
        ECPoint result = ECPoint.INFINTIY;
        int bitLength = n.bitLength();
        for (int i = bitLength - 1; i >= 0; --i) {
            result = add(result, result);
            if (n.testBit(i)) {
                result = add(result, p1);
            }
        }
        
        return result;
    }
    
    public ECPoint multiply(ECPoint p1, long n) {
        return multiply(p1, BigInteger.valueOf(n));
    }

    /**
     * Calculate the right hand side of the equation.
     * 
     * @param x
     * @return 
     */
    public BigInteger calculateRhs(BigInteger x) {
        return x.multiply(x).mod(p).add(a).multiply(x).add(b).mod(p);
    }
   
    	
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Check whether the standard curves' base points lie on the curve
        System.out.println("NIST_P_192: " + EllipticCurve.brainpoolP160r1.isPointInsideCurve(EllipticCurve.brainpoolP160r1.getBasePoint()));
        System.out.println("NIST_P_224: " + EllipticCurve.brainpoolP192r1.isPointInsideCurve(EllipticCurve.brainpoolP192r1.getBasePoint()));
        System.out.println("NIST_P_256: " + EllipticCurve.brainpoolP224r1.isPointInsideCurve(EllipticCurve.brainpoolP224r1.getBasePoint()));
        System.out.println("NIST_P_384: " + EllipticCurve.brainpoolP256r1.isPointInsideCurve(EllipticCurve.brainpoolP256r1.getBasePoint()));
        System.out.println("NIST_P_521: " + EllipticCurve.brainpoolP320r1.isPointInsideCurve(EllipticCurve.brainpoolP320r1.getBasePoint()));
        
        for (int i = 0; i < 100; ++i) {
            System.out.println("NIST_P_521 x " + i + " = " + EllipticCurve.brainpoolP320r1.multiply(EllipticCurve.brainpoolP320r1.getBasePoint(), i).toString(16));
        }
    
        
        
        // This computes (2, 4) + (5, 9) in y^2 = x^3 + x + 6 mod 11
        
        ECPoint p = EllipticCurve.brainpoolP512r1.getBasePoint();
      
        ECPoint q =EllipticCurve.brainpoolP512r1.getBasePoint();
        
        System.out.println(p + " + " + q + " = " + EllipticCurve.brainpoolP512r1.add(p, q));
      /*  for (int i = 0; i < 20; ++i) {
            System.out.println(p + " x " + i + " = " + e.multiply(p, i));
        }*/
    }}


