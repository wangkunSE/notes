package com.soul.alg.leetcode2.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * <p>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * @author wangkunwk
 * @version 2020/9/13
 */
public class GroupAnagramString {

    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {

            List<List<String>> result = new ArrayList<>();
            if (Objects.isNull(strs)) {
                return result;
            }
            Map<String, List<String>> index = new HashMap<>();

            for (String str : strs) {
                char[] tmpStr = str.toCharArray();
                Arrays.sort(tmpStr);
                String key = new String(tmpStr);
                if (str.equals("")) {
                    key = "";
                }
                List<String> group = index.get(key);
                if (Objects.nonNull(group)) {
                    group.add(str);
                } else {
                    List<String> newGroup = new ArrayList<>();
                    newGroup.add(str);
                    index.put(key, newGroup);
                }
            }

            result.addAll(index.values());
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = solution.groupAnagrams(arr);
        System.out.println(lists);
    }
}
