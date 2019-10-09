package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2019/9/26
 */
public class LetterCasePermutation_784 {

    public static void main(String[] args) {
        System.out.println(new LetterCasePermutation_784().letterCasePermutation("a1z1"));
    }

    public List<String> letterCasePermutation(String source) {
        if (Objects.isNull(source) || "".equals(source)) {
            return new ArrayList<>();
        }

        char[] chars = source.toCharArray();
        List<String> result = new ArrayList<>();

        fillResult(0, result, chars, new char[chars.length]);
        return result;
    }

    private void fillResult(int offset, List<String> result, char[] chars, char[] target) {
        if (offset >= chars.length) {
            result.add(new String(target));
            return;
        }

        char aChar = chars[offset];
        if (('a' <= aChar && 'z' >= aChar)|| ('A' <= aChar && 'Z' >= aChar)) {
            target[offset] = Character.toLowerCase(aChar);
            fillResult(offset + 1, result, chars, target);
            target[offset] = Character.toUpperCase(aChar);
            fillResult(offset + 1, result, chars, target);
        }else {
            target[offset] = aChar;
            fillResult(offset + 1, result, chars, target);
        }

    }
}
