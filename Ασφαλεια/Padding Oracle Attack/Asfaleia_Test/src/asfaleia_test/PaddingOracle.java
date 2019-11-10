package asfaleia_test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PaddingOracle {

    private static String[] paddings = {"01", "0202", "030303", "04040404", "0505050505", "060606060606", "07070707070707", "0808080808080808", "090909090909090909",
        "0a0a0a0a0a0a0a0a0a0a", "0b0b0b0b0b0b0b0b0b0b0b", "0c0c0c0c0c0c0c0c0c0c0c0c", "0d0d0d0d0d0d0d0d0d0d0d0d0d",
        "0e0e0e0e0e0e0e0e0e0e0e0e0e0e", "0f0f0f0f0f0f0f0f0f0f0f0f0f0f0f", "10101010101010101010101010101010"};
    private static String original = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";
    private static String solution;
    
    PaddingOracle(){
       createBlock();       
    }
    

    public static void createBlock() {
        int bs = 16, b = 0;
        byte[][] blocks;
        System.out.println(original.length());
        int cl = original.length() / 2;
        if ((cl % bs == 0) && (cl / bs > 1)) {
            blocks = new byte[cl / bs][bs];
            for (int i = 0; i < cl * 2; i = i + bs * 2) {
                blocks[b] = hexStringToByteArray(original.substring(i, i + bs * 2));
                b++;
            }
        } else {

            blocks = null;
            System.out.println(original);
            System.out.println("Cyphertext must be multiple of byte array size");
            System.out.println("Cyphertext length: " + cl + " Block size: " + bs);
            System.exit(1);
        }

        getSolution(blocks);
    }

    public static void getSolution(byte[][] blocks) {
        solution = "";
        int b = 0;

        //Οσο βρισκει block 
        while (b < blocks.length - 1) {
            solution += new String(crackBlock(blocks[b + 1], blocks[b]));
            b++;
        }

        // Αφαιρεση padding για εμφανιση τελικου αποτελεσματος
        for (String padding : paddings) {
            String hexPadding = hexStringToString(padding);
            if (solution.endsWith(hexPadding)) {
                solution = solution.substring(0, solution.length() - hexPadding.length());
            }
        }
        //Λυση
        System.out.println("Decrypted message : " + solution);

    }

    public static byte[] crackBlock(byte[] inputBlock, byte[] previousBlock) {
        //Δημιουργια array και αντιγραφη 16byte block 
        int bs = inputBlock.length;
        byte[] temp_Array = new byte[bs * 2];
        System.arraycopy(inputBlock, 0, temp_Array, bs, bs);

        byte[] crypto_solution = new byte[bs];
        int padding = 1;
        System.out.println("inputBlock    " + byteArrayToHexString(inputBlock) + "   previousBlock    " + byteArrayToHexString(previousBlock));
        //Για καθε byte
        for (int i = bs - 1; i >= 0; i--) {
            int counter = 0;
            int req_code = 0;

            // try all characters 
            while (req_code != 200) {
                temp_Array[i] += 1;
                if (counter > 256) {
                    System.out.println("Bit not found");
                    return crypto_solution;
                }
                counter++;

                req_code = getCode(byteArrayToHexString(temp_Array));      
            }
            
            System.out.println("Bit found: " + Integer.toHexString(temp_Array[i] & 0xff) + " in " + byteArrayToHexString(temp_Array));

            // Υπολογισμος padding
            if (padding == 0) {
                System.out.println("Padding not found!");
                System.exit(1);
            }
            //XOR 
            crypto_solution[i] = (byte) (padding ^ previousBlock[i] ^ temp_Array[i]);           
            System.out.println("Crypto Solution: " + byteArrayToHexString(crypto_solution) + " / " + new String(crypto_solution));

            // Υλοποιηση xor διαδικασιας 
            if (i > 0) {
                padding++;
                for (int j = i; j < bs; j++) {
                    temp_Array[j] = (byte) (crypto_solution[j] ^ (padding) ^ previousBlock[j]);
                }
                System.out.println("Set almost valid padding to " + (padding) + " in " + byteArrayToHexString(temp_Array));
            }
        }

        return crypto_solution;
    }

    //Αποστολη hashed string στον server και απαντηση του server
    public static int getCode(String secret) {

        Integer code = 1;
        String host = "http://crypto-class.appspot.com/po?er=";
        String full = host + secret;    
        try {

            URL url = new URL(full);
            URLConnection con = url.openConnection();
            con.connect();
            String head = con.getHeaderField(0);

            System.out.println(head);
            if (head.equals("HTTP/1.1 403 Forbidden")) {
                code = 500;
            } else if (head.equals("HTTP/1.1 404 Not Found")) {
                code = 200;
            }

        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }

        return code;
    }
    
    public String getPlaintext(){
        return solution;
    }

    //Μετατροπη byte πινακα σε string δεκαεξαδικου (format)
    public static String byteArrayToHexString(byte[] b) {
        int len = b.length;
        String data = new String();

        for (int i = 0; i < len; i++) {
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }

    //Μετατροπη δεκαεξδαικου σε String
    public static String hexStringToString(String hex) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
    
    //Μετατροπη δεκαεξαδικου σε bytes
    public static byte[] hexStringToByteArray(String s) {
        System.out.println(s);
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

}
