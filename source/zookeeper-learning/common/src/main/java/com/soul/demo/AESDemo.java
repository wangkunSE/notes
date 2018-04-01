package com.soul.demo;

import com.soul.util.AESUtils;

/**
 * @author wangkun1
 * @version 2018/3/7
 */
public class AESDemo {

    public static void main(String[] args) throws Exception {
        String content = AESUtils.decodeContentWithAESBase64("", "");
        System.out.println(content);
    }
}
