package asfaleia_test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Pipis
 */
public class Crypto {

    private final int ivSize;
    private final byte[] keyBytes;
    private final byte[] keyBytes2;
    private final byte[] TestKeyBytes;
    private final KeyGenerator keyGen;
    private final SecretKey secretKey, secretKey2,testKey;
    private byte[] iv, encryptedIVAndText, macBytes;
    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private SecretKeySpec secretKeySpec2;
    private SecretKeySpec testSecretKeySpec;
    private byte[] testMacBytes;
    private MessageDigest digest;
    private MessageDigest digest2;
    private MessageDigest testDigest;

    //Αρχικοποιηση μεταβλητων
    public Crypto() throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom sr = new SecureRandom();
        keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128, sr);
        secretKey = keyGen.generateKey();
        secretKey2 = keyGen.generateKey();
        testKey = keyGen.generateKey();
        keyBytes = new byte[16];
        keyBytes2 = new byte[16];
        TestKeyBytes = new byte[16];
        System.out.println("Key1 : " + secretKey);
        System.out.println("Key2 : " + secretKey2);
        ivSize = 16;
        createIV();
    }

    private byte[] createIV() {
        // Δημιουργια IV.
        iv = new byte[ivSize];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        ivParameterSpec = new IvParameterSpec(iv);

        return iv;
    }

    public byte[] encrypt(String plaintext) {

        try {
            digest = MessageDigest.getInstance("SHA-256");
            digest.update(secretKey.getEncoded());
            System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
            secretKeySpec = new SecretKeySpec(keyBytes, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(plaintext.getBytes());
            System.out.println("Encrypted text's length : " + encrypted.length);
            // Συνχωνευση IV και κρυπτογραφημενου block.
            encryptedIVAndText = new byte[ivSize + encrypted.length];
            System.arraycopy(iv, 0, encryptedIVAndText, 0, ivSize);
            System.arraycopy(encrypted, 0, encryptedIVAndText, ivSize, encrypted.length);
            System.out.println("Final hash's length : " + encryptedIVAndText.length);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();

        }
        return (encryptedIVAndText);

    }

    public String decrypt(byte[] ciphertect) throws Exception {

        // Extract κρυπτογραφημενου block.
        int encryptedSize = ciphertect.length - ivSize;
        byte[] encryptedBytes = new byte[encryptedSize];
        System.arraycopy(ciphertect, ivSize, encryptedBytes, 0, encryptedSize);

        // Αποκρυπτογράφηση 
        Cipher cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(encryptedBytes);
        return new String(decrypted);

    }
    
    //Δημιουργία MAC 
    public byte[] MAC(byte[] encrypted) {
        Mac mac = null;
        macBytes = null;
        byte[] testMacArray = testMac(encrypted);
        try {
            mac = Mac.getInstance("HmacSHA256");
            digest2 = MessageDigest.getInstance("SHA-256");
            digest2.update(secretKey2.getEncoded());
           
            System.arraycopy(digest.digest(), 0, keyBytes2, 0, keyBytes2.length);
            secretKeySpec2 = new SecretKeySpec(keyBytes2, "AES");
            mac.init(secretKeySpec2);
            macBytes = mac.doFinal(encrypted);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("MAC's length : " + macBytes.length);
        return macBytes;
    }
    
    //Προέκταση cypher με MAC
    public byte[] append_cypher(byte[] cypher, byte[] macBytes) {
        byte[] final_block = new byte[cypher.length + macBytes.length];
        System.arraycopy(cypher, 0, final_block, 0, cypher.length);
        System.arraycopy(macBytes, 0, final_block, cypher.length, macBytes.length);
        System.out.println("Final's block size : " + final_block.length);
        return final_block;
    }

    public void check_auth(byte[] result) {
        
        //Instead of macBytes -> testMacBytes για δοκιμή 
        boolean checkauth = true;
        byte[] maccheck = new byte[macBytes.length];
        byte[] cypher = new byte[result.length - macBytes.length];
        System.arraycopy(result, result.length - macBytes.length, maccheck, 0, maccheck.length);
        System.arraycopy(result, 0, cypher, 0, cypher.length);
        
        
        if (maccheck.length != testMacBytes.length) {
            System.out.println("MACs has different lengths");
            checkauth = false;
        } else {

            for (int i = 0; i < maccheck.length; i++) {
                if (maccheck[i] != testMacBytes[i]) {
                    checkauth = false;
                    System.out.println("Sender MAC and calculated MAC are different. Message cannot be authenticated.");
                    break;
                }
            }
            if (checkauth) {
                try {
                    String decrypted = decrypt(cypher);
                    System.out.println("Decrypted message : " + decrypted);
                } catch (Exception ex) {
                    Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }
    
    //Δημιουργία καινουργιου MAC για επίδειξη απόρριψης αιτήματος αυθεντικοποίησης
    public byte[] testMac(byte[] encrypted)
    {
        Mac mac = null;
        testMacBytes = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            testDigest = MessageDigest.getInstance("SHA-256");
            testDigest.update(testKey.getEncoded());
            System.arraycopy(testDigest.digest(), 0, TestKeyBytes, 0, TestKeyBytes.length);
            testSecretKeySpec = new SecretKeySpec(TestKeyBytes, "AES");
            mac.init(testSecretKeySpec);
            testMacBytes = mac.doFinal(encrypted);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(Crypto.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(testMacBytes.length);
        return testMacBytes;

    }
}
