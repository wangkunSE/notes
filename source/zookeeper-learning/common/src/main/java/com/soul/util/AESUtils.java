package com.soul.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @author WK
 * @version 2018/3/3
 */
public class AESUtils {

    public static String encodeContentWithAESBase64(String content, String key) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] originKey = secretKey.getEncoded();
        SecretKey aes = new SecretKeySpec(originKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aes);
        byte[] contentBytes = content.getBytes("utf-8");
        byte[] secretContentBytes = cipher.doFinal(contentBytes);
        String encodeContent = new BASE64Encoder().encode(secretContentBytes);
        return encodeContent;
    }

    public static String decodeContentWithAESBase64(String encodedContent, String key) throws Exception {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(key.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] originKey = secretKey.getEncoded();
        SecretKey aes = new SecretKeySpec(originKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aes);
        byte[] decodeBuffer = new BASE64Decoder().decodeBuffer(encodedContent);
        byte[] decodeBytes = cipher.doFinal(decodeBuffer);
        String content = new String(decodeBytes, "utf-8");
        return content;
    }

    public static void main(String[] args) throws Exception {

        Integer temp = 1;
        System.out.println(numberOfLeadingZeros(temp));
        System.out.println(Integer.numberOfTrailingZeros(temp));
        System.out.println( Integer.highestOneBit(temp));
        System.out.println(Integer.lowestOneBit(temp));
        System.out.println(Integer.toHexString(temp));
        System.out.println(Integer.toBinaryString(temp));
        System.out.println(Integer.toOctalString(temp));
        System.out.println(Integer.rotateLeft(temp,2));
        System.out.println(Integer.rotateRight(temp,2));
    }

    public static int numberOfLeadingZeros(int i) {
        if (i == 0)
            return 32;
        int n = 1;
        // 下面的代码就是定位从左边开始第一个非零值的位置，在定位过程中顺便累加从左边开始0的个数
        // 将i无符号右移16位后，有二种情况；
        //   情况1.i=0,则第一个非零值位于低16位，i至少有16个0，同时将i左移16位（把低16位移到原高16位的位置，这样情况1和情况2就能统一后续的判断方式）
        //   情况2.i!=0,则第一个非零值位于高16位，后续在高16位中继续判断
        // 这个思路就是二分查找，首先把32位的数分为高低16位，如果非零值位于高16位，后续再将高16位继续二分为高低8位，一直二分到集合中只有1个元素
        if (i >>> 16 == 0) { n += 16; i <<= 16; }
        // 判断第一个非零值是否位于高8位
        if (i >>> 24 == 0) { n +=  8; i <<=  8; }
        // 判断第一个非零值是否位于高4位
        if (i >>> 28 == 0) { n +=  4; i <<=  4; }
        // 判断第一个非零值是否位于高2位
        if (i >>> 30 == 0) { n +=  2; i <<=  2; }
        // 判断第一个非零值是否位于左边第一位
        n -= i >>> 31;
        return n;
    }

    public static String byteToHexString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String strHex = Integer.toHexString(bytes[i]);
            if (strHex.length() > 3) {
                sb.append(strHex.substring(6));
            } else {
                if (strHex.length() < 2) {
                    sb.append("0" + strHex);
                } else {
                    sb.append(strHex);
                }
            }
        }
        return sb.toString();
    }
}
