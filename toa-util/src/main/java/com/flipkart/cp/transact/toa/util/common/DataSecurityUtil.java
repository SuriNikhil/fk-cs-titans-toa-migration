package com.flipkart.cp.transact.toa.util.common;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.FileReader;
import java.security.AlgorithmParameters;
import java.security.spec.KeySpec;
import java.util.Random;

/**
 * Created by padmanabh.kulkarni on 01/02/16.
 */
@Slf4j
public class DataSecurityUtil {
   // private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final SecretKey secret;

    private static final Random random = new Random();

    /* WARNING: NEVER EVER CHANGE THE VALUE OF THIS VARIABLE.
     CRITICAL VALUE THAT NEEDS TO REMAIN UNCHANGED OVER RELEASES.
     */
    private static String salt = "uebmdfglpowiutuybxQWQcmlddpoekjgqnd123mljldfguy6opolkjdf1nmdslPLkef";
    /* END WARNING */


    public DataSecurityUtil(String keyFilePath) {
       log.debug("Key file path = " + keyFilePath);

        try {
            String password = readStringFromFile(keyFilePath);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes("UTF-8"), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception e) {
            throw new RuntimeException("Error in init of DataSecurity" + e.getMessage(), e);
        }
    }

    public String doEncrypt(String input) {
        log.debug("doEncrypt : input = " + input);
        if (input == null) return null;
        try {
            long start = System.currentTimeMillis();
            Cipher encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encCipher.init(Cipher.ENCRYPT_MODE, secret);
            byte[] ciphertext = encCipher.doFinal(input.getBytes("UTF-8"));
            String encStr = Base64.encodeBase64String(ciphertext);
            AlgorithmParameters params = encCipher.getParameters();
            byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
            String ivStr = Base64.encodeBase64String(iv);
            String retval = getEncIv(encStr, ivStr);
            long end = System.currentTimeMillis();
            log.info("time taken for encryption: " + (end - start));
            log.debug("doEncrypt : output = " + retval);
            return retval;
        } catch (Exception e) {
            log.error("doEncrypt Error: " + e.getMessage(), e);
            throw new RuntimeException("Error encrypting string: " + e.getMessage(), e);
        }
    }

    public String doDecrypt(String input) {
        log.debug("doDecrypt : input = " + input);
        if (input == null) return null;
        try {
            long start = System.currentTimeMillis();
            Cipher decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = Base64.decodeBase64(getIv(input));
            decCipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
            byte[] deciphertext = decCipher.doFinal(Base64.decodeBase64(getEnc(input)));
            String plaintext = new String(deciphertext, "UTF-8");
            long end = System.currentTimeMillis();
            log.info("time taken for decryption: " + (end - start));
            log.debug("doDecrypt : output = " + plaintext);
            return plaintext;
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting string: " + e.getMessage(), e);
        }
    }

    private static char DELIMITER = '-';

    private String getEncIv(String enc, String iv) {
        StringBuffer buf = new StringBuffer();
        buf.append(enc.trim());
        buf.append(DELIMITER);
        buf.append(iv.trim());
        return buf.toString();
    }

    private String getIv(String input) {
        int i = input.lastIndexOf(DELIMITER);
        String retval = input.substring(i+1);
        return retval;
    }

    private String getEnc(String input) {
        int i = input.indexOf(DELIMITER);
        String retval = input.substring(0, i);
        return retval;
    }

    private static String readStringFromFile(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error reading security file: " + e.getMessage(), e);
        }
    }
}
