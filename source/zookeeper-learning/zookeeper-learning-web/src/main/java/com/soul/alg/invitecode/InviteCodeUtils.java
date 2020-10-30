package com.soul.alg.invitecode;

/**
 * @author wangkun1
 * @version 2018/2/28
 */
public class InviteCodeUtils {

    public static void main(String[] args) {
        String s = "oep2jtzpP_DT1WwtKdG4A4zmu-Tw".replaceAll("-", "");
        char[] chars = s.toCharArray();
        char[] resultChars = new char[6];
        Integer result = 0;
        for (int i = 0, k = 0; i < chars.length && k < 6; i += 4, k++) {
            result = 0;
            for (int j = i; j < 4 || j < chars.length; j++) {
                result +=  chars[j];
            }
            resultChars[k] = Character.highSurrogate(result);
        }
        System.out.println(new String(resultChars));

    }
}
