package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2019/9/26
 */
public class LetterCombinationsOfPhoneNumber_17 {

    private static Map<Character, List<Character>> keyboard = new HashMap<>();

    static {
        keyboard.put('2', Arrays.asList('a', 'b', 'c'));
        keyboard.put('3', Arrays.asList('d', 'e', 'f'));
        keyboard.put('4', Arrays.asList('g', 'h', 'i'));
        keyboard.put('5', Arrays.asList('j', 'k', 'l'));
        keyboard.put('6', Arrays.asList('m', 'n', 'o'));
        keyboard.put('7', Arrays.asList('p', 'q', 'r', 's'));
        keyboard.put('8', Arrays.asList('t', 'u', 'v'));
        keyboard.put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNumber_17().letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        if (Objects.isNull(digits) || "".equals(digits)) {
            return new ArrayList<>();
        }


        char[] chars = digits.toCharArray();
        List<String> result = new ArrayList<>();
        fillResult(0, chars, result, new char[chars.length]);
        return result;
    }

    private void fillResult(int offset, char[] chars, List<String> result, char[] target) {
        if (offset >= chars.length) {
            result.add(new String(target));
            return;
        }

        char aChar = chars[offset];
        List<Character> keys = keyboard.get(aChar);

        for (Character key : keys) {
            target[offset] = key;
            fillResult(offset + 1, chars, result, target);
        }

    }
}
