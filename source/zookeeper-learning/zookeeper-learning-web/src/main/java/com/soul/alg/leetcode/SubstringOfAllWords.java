package com.soul.alg.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: wangkun36
 * @date: 2018/6/13
 * @time: 下午8:11
 * Copyright (C) 2015 Meituan
 * All rights reserved
 */
public class SubstringOfAllWords {
    public static void main(String[] args) {
//        First first = new First();
        Second first = new Second();
        String s = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        String[] words = {"dhvf","sind","ffsl","yekr","zwzq","kpeo","cila","tfty","modg","ztjg","ybty","heqg","cpwo","gdcj","lnle","sefg","vimw","bxcb"};
        List<Integer> substring = first.findSubstring(s, words);
        System.out.println(substring);
    }

    private static class First {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> result = new ArrayList<>();
            if (s == null || s.length() <= 0 || words == null) {
                return result;
            }
            Set<String> strSet = new HashSet<>();
            allTypes(words, 0, words.length, strSet);
            Set<Integer> set = new HashSet<>();
            for (String str : strSet) {
                int index;
                int start = 0;
                while ((index = s.indexOf(str, start)) != -1 && start < s.length()) {
                    start = index + 1;
                    set.add(index);
                }
            }
            result.addAll(set);
            return result;
        }

        private void allTypes(String[] words, int start, int end, Set<String> strSet) {
            if (start == end - 1) {
                StringBuilder sb = new StringBuilder();
                for (String str : words) {
                    sb.append(str);
                }
                strSet.add(sb.toString());
            } else {
                for (int i = start; i < end; i++) {
                    swap(words, start, i);
                    allTypes(words, start + 1, end, strSet);
                    swap(words, start, i);
                }
            }
        }

        private void swap(String[] arr, int start, int end) {
            String temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
        }
    }

    private static class Second{
        public List<Integer> findSubstring(String s, String[] words) {
            int N = s.length();
            List<Integer> indexes = new ArrayList<Integer>(s.length());
            if (words.length == 0) {
                return indexes;
            }
            int M = words[0].length();
            if (N < M * words.length) {
                return indexes;
            }
            int last = N - M + 1;

            //map each string in words array to some index and compute target counters
            Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
            int [][] table = new int[2][words.length];
            int failures = 0, index = 0;
            for (int i = 0; i < words.length; ++i) {
                Integer mapped = mapping.get(words[i]);
                if (mapped == null) {
                    ++failures;
                    mapping.put(words[i], index);
                    mapped = index++;
                }
                ++table[0][mapped];
            }

            //find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
            int [] smapping = new int[last];
            for (int i = 0; i < last; ++i) {
                String section = s.substring(i, i + M);
                Integer mapped = mapping.get(section);
                if (mapped == null) {
                    smapping[i] = -1;
                } else {
                    smapping[i] = mapped;
                }
            }

            //fix the number of linear scans
            for (int i = 0; i < M; ++i) {
                //reset scan variables
                int currentFailures = failures; //number of current mismatches
                int left = i, right = i;
                Arrays.fill(table[1], 0);
                //here, simple solve the minimum-window-substring problem
                while (right < last) {
                    while (currentFailures > 0 && right < last) {
                        int target = smapping[right];
                        if (target != -1 && ++table[1][target] == table[0][target]) {
                            --currentFailures;
                        }
                        right += M;
                    }
                    while (currentFailures == 0 && left < right) {
                        int target = smapping[left];
                        if (target != -1 && --table[1][target] == table[0][target] - 1) {
                            int length = right - left;
                            //instead of checking every window, we know exactly the length we want
                            if ((length / M) ==  words.length) {
                                indexes.add(left);
                            }
                            ++currentFailures;
                        }
                        left += M;
                    }
                }

            }
            return indexes;
        }
    }
}
