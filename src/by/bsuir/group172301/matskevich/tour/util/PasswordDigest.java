package by.bsuir.group172301.matskevich.tour.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 */
public class PasswordDigest {
    public static String md5hash(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(password.getBytes("UTF-8"));
            BigInteger bigInteger = new BigInteger(1, bytes);
            String hash = bigInteger.toString(16);
            while(hash.length() < 32){
                hash = "0" + hash;
            }

            return hash;
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
}
