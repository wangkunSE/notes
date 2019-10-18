package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangkunwk
 * @version 2019/10/18
 */
public class RestoreIpAddress_93 {
    public static void main(String[] args) {
//        String str = "25525511135";
//        System.out.println(str.substring(0,3));
//        System.out.println(str.substring(3));
        System.out.println(new RestoreIpAddress_93().restoreIpAddresses("010010"));
    }

    public List<String> restoreIpAddresses(String s) {
        if (null == s || s.length() < 4) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        List<String> ipSplit = new ArrayList<>();
        findAllIp(result, s, ipSplit);
        return result;
    }

    private void findAllIp(List<String> result, String s, List<String> ipSplit) {
        if ("".equals(s) && ipSplit.size() < 4 || ipSplit.size() > 4) {
            return;
        }
        if ("".equals(s)) {
            ipSplit.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ipSplit.size(); i++) {
                sb.append(ipSplit.get(i));
                if (i != 3) {
                    sb.append(".");
                }
            }
            result.add(sb.toString());
            return;
        }

        if (s.startsWith("0")) {
            ipSplit.add(s.substring(0, 1));
            findAllIp(result, s.substring(1), ipSplit);
            ipSplit.remove(ipSplit.size() - 1);
        } else {
            for (int i = 1; i < 4; i++) {
                if (i <= s.length()) {
                    String substring = s.substring(0, i);
                    int strInt = Integer.parseInt(substring);
                    if (strInt > 255 || strInt < 0) {
                        return;
                    }
                    ipSplit.add(substring);
                    findAllIp(result, s.substring(i), ipSplit);
                    ipSplit.remove(ipSplit.size() - 1);
                }
            }
        }


    }
}
