package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangkunwk
 * @version 2019/10/19
 */
public class WordLadder_II_126 {
    public static void main(String[] args) {

        System.out.println(new WordLadder_II_126().findLadders("hit"
                , "cog",
                Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    private int shortestPath = Integer.MAX_VALUE;
    private Map<String, List<String>> levelMap = new HashMap<>();

    int count = 0;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (null == beginWord || null == endWord || null == wordList) {
            return new ArrayList<>();
        }

        List<List<String>> result = new ArrayList<>();
        ArrayList<String> tempCombine = new ArrayList<>();

        findShortestPath(wordList, endWord, beginWord);
        if (shortestPath == Integer.MAX_VALUE) {
            return result;
        }

        Set<String> uniqTrace = new HashSet<>();
        uniqTrace.add(beginWord);
        tempCombine.add(beginWord);

        findLadderResults(result, beginWord, endWord, tempCombine, uniqTrace, 1);
        return result;
    }

    private void findShortestPath(List<String> wordList, String endWord, String beginWord) {

        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int level = 1;
        boolean find = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curLevelWord = queue.poll();
                if (levelMap.containsKey(curLevelWord)) {
                    continue;
                }
                List<String> ladders = new ArrayList<>();
                for (int j = 0; j < wordList.size(); j++) {
                    String curStr = wordList.get(j);
                    if (canBeLadder(curLevelWord, curStr)) {
                        if (endWord.equals(curLevelWord) && level < shortestPath) {
                            shortestPath = level;
                            find = true;
                        }
                        if (!levelMap.containsKey(curStr)) {
                            ladders.add(curStr);
                        }
                        queue.offer(curStr);
                    }
                }
                levelMap.put(curLevelWord, ladders);
            }
            if (find) {
                break;
            }
            level++;
        }


    }

    private void findLadderResults(List<List<String>> result, String beginWord, String endWord,
                                   List<String> tempCombine, Set<String> uniqTrace, int level) {


        if (level > shortestPath) {
            return;
        }

        if (level == shortestPath && endWord.equals(beginWord)) {
            result.add(new ArrayList<>(tempCombine));
            return;
        }

        List<String> curBeginLadders = levelMap.get(beginWord);
        for (String curBeginLadder : curBeginLadders) {
            if (uniqTrace.contains(curBeginLadder)) {
                continue;
            }
            uniqTrace.add(curBeginLadder);
            tempCombine.add(curBeginLadder);
            findLadderResults(result, curBeginLadder, endWord, tempCombine, uniqTrace, level + 1);
            tempCombine.remove(tempCombine.size() - 1);
            uniqTrace.remove(curBeginLadder);
        }

    }

    private boolean canBeLadder(String beginWord, String curStr) {
        if (beginWord == null || curStr == null) {
            return false;
        }
        int notSame = 0;
        for (int i = 0; i < beginWord.length(); i++) {
            if (beginWord.charAt(i) != curStr.charAt(i)) {
                notSame++;
            }
        }
        return notSame == 1;
    }
}
