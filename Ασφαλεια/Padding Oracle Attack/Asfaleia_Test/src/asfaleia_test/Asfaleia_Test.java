
package asfaleia_test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;



/**
 *
 * @author Pipis
 */
public class Asfaleia_Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, Exception {
        PaddingOracle po1 = new PaddingOracle();;
        String plaintext = po1.getPlaintext();
        Crypto c1 = new Crypto();
        byte[] b =c1.encrypt(plaintext);      
        byte[] result =c1.append_cypher(b, c1.MAC(b));   
        c1.check_auth(result);
      
        //System.out.println("Decrypted message : " + c1.decrypt(b));
     
                 
    }
    
}
