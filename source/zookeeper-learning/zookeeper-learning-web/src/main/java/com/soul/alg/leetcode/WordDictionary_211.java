package com.soul.alg.leetcode;

/**
 * @author wangkunwk
 * @version 2019/10/23
 */
public class WordDictionary_211 {
    public static void main(String[] args) {
        WordDictionary dictionary = new WordDictionary();
        dictionary.addWord("ran");
        dictionary.addWord("rune");
        dictionary.addWord("runner");
        dictionary.addWord("runs");
        dictionary.addWord("add");
        dictionary.addWord("adds");
        dictionary.addWord("adder");
        dictionary.addWord("addee");
        System.out.println(dictionary.search("r.n"));
        System.out.println(dictionary.search("ru.n.e"));
        System.out.println(dictionary.search("add"));
        System.out.println(dictionary.search("add."));
        System.out.println(dictionary.search("adde."));
        System.out.println(dictionary.search(".an."));
        System.out.println(dictionary.search("...s"));
        System.out.println(dictionary.search("....e."));
        System.out.println(dictionary.search("......."));
        System.out.println(dictionary.search("..n.r"));
    }

    static class WordDictionary {
        TrieNode root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieNode();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            if (null == word) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode p = root;
            for (char aChar : chars) {
                int index = aChar - 'a';
                if (p.next[index] == null) {
                    p.next[index] = new TrieNode();
                }
                p = p.next[index];
            }
            p.isWord = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            if (null == word) {
                return false;
            }
            return matches(word.toCharArray(), root, 0);
        }

        private boolean matches(char[] chars, TrieNode root, int offset) {
            if (chars.length == offset) {
                return root.isWord;
            }
            char curChar = chars[offset];
            if ('.' == curChar) {
                for (int i = 0; i < root.next.length; i++) {
                    TrieNode trieNode = root.next[i];
                    if (null != trieNode && matches(chars, root.next[i], offset + 1)) {
                        return true;
                    }
                }
            } else {

                return root.next[chars[offset] - 'a'] != null && matches(chars, root.next[chars[offset] - 'a'], offset + 1);
            }
            return false;
        }
    }

    static class TrieNode {
        boolean isWord;
        TrieNode[] next;

        public TrieNode() {
            next = new TrieNode[27];
            isWord = false;
        }
    }
}
