package org.cucumbertaf.utils.mail;

import java.util.Base64;


public class CryptoUtil {

    public static void main(String... args) throws Exception {

        String encrptData= "";
        byte[] encodedBytes = Base64.getEncoder().encode(encrptData.getBytes());
        String encrypted = new String(encodedBytes);
        System.out.println("encodedBytes --------------->" + encrypted);
        byte[] decodeBytes = Base64.getDecoder().decode(encrypted);
        String dencrypted = new String(decodeBytes);
        System.out.println("decodedBytes --------------->" + dencrypted);

    }

    public static String getDecryptedPassword(String key){
        byte[] decodeBytes = Base64.getDecoder().decode(key);
        return new String(decodeBytes);
    }

}
