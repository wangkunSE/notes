package com.soul.alg.leetcode2.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author wangkunwk
 * @version 2020/9/5
 */
public class SortCharacterByFrequency {

    static class Solution {
        public String frequencySort(String s) {
            if (Objects.isNull(s)) {
                return null;
            }
            int[] index = new int[256];
            for (int i = 0; i < s.length(); i++) {
                index[s.charAt(i)]++;
            }

            TreeMap<Integer, List<Character>> treeMap = new TreeMap<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2 - o1;
                }
            });

            for (int i = 0; i < index.length; i++) {
                if (index[i] != 0) {
                    List<Character> characters = treeMap.get(index[i]);
                    if (Objects.isNull(characters)) {
                        characters = new ArrayList<>();
                        characters.add((char) i);
                        treeMap.put(index[i], characters);
                    } else {
                        characters.add((char) i);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            Set<Map.Entry<Integer, List<Character>>> entries = treeMap.entrySet();
            for (Map.Entry<Integer, List<Character>> entry : entries) {
                Integer key = entry.getKey();
                List<Character> value = entry.getValue();
                for (Character character : value) {
                    for (int j = 0; j < key; j++) {
                        sb.append(character);
                    }
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String tree = solution.frequencySort("tree");
        System.out.println(tree);
    }
}
