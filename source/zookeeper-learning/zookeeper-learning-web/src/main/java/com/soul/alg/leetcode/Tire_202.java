package com.soul.alg.leetcode;

import java.util.Date;

/**
 * @author wangkunwk
 * @version 2019/10/23
 */
public class Tire_202 {

    public static void main(String[] args) {
        System.out.println(new Date(1570216413000L));
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
        System.out.println(trie.search("apple"));

        trie.insert("app");
        System.out.println(trie.search("app"));

    }

    static class TireNode {
        TireNode[] next = new TireNode[26];
        String word;
    }

    static class Trie {

        TireNode root = new TireNode();

        /**
         * Initialize your data structure here.
         */
        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (null == word) {
                return;
            }
            char[] chars = word.toCharArray();
            TireNode p = this.root;
            for (char aChar : chars) {
                if (p.next[aChar - 'a'] == null) {
                    p.next[aChar - 'a'] = new TireNode();
                }
                p = p.next[aChar - 'a'];
            }
            p.word = word;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (null == word) {
                return false;
            }
            char[] chars = word.toCharArray();
            TireNode p = root;
            for (char aChar : chars) {
                p = p.next[aChar - 'a'];
                if (p == null) {
                    return false;
                }
            }
            return word.equals(p.word);
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (null == prefix) {
                return false;
            }
            char[] chars = prefix.toCharArray();
            TireNode p = root;
            for (char aChar : chars) {
                p = p.next[aChar - 'a'];
                if (p == null) {
                    return false;
                }
            }
            return true;
        }
    }

}
