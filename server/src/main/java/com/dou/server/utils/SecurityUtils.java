package com.dou.server.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;

public class SecurityUtils {

    private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "!", "#", "@", "a", "b", "c", "d", "*", "f", "g", "F" };

    private final static String algorithm = "sha-256";

    public static String encode(String rawPass, String salt) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            // 加密后的字符串
            result = byteArrayToHexString(md.digest(mergePasswordAndSalt(rawPass, salt).getBytes(StandardCharsets.UTF_8)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static boolean isPasswordValid(String encPass, String rawPass, String salt) {
        String pass1 = "" + encPass;
        String pass2 = encode(rawPass, salt);
        return pass1.equals(pass2);
    }

    private static String mergePasswordAndSalt(String password, String salt) {
        if (password == null) {
            password = "";
        }
        if ((salt == null) || "".equals(salt)) {
            return password;
        } else {
            return password + "{" + salt + "}";
        }
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte value : b) {
            resultSb.append(byteToHexString(value));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / HEX_DIGITS.length;
        int d2 = n % HEX_DIGITS.length;
        return HEX_DIGITS[d1] + HEX_DIGITS[d2];
    }

    public static String randomSalt(int length) {
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}