package com.soul.alg.leetcode2.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Objects;

/**
 * @author wangkunwk
 * @version 2020/10/11
 */
public class SimplifyPath {
    static class Solution {
        public String simplifyPath(String path) {

            if (Objects.isNull(path) || path.length() < 1) {
                return path;
            }

            Deque<String> stack = new ArrayDeque<>();
            String[] targets = path.split("/");

            for (String target : targets) {
                switch (target) {
                    case "..": {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                        break;
                    }
                    case ".":
                    case "": {
                        break;
                    }
                    default: {
                        stack.push(target);
                        break;
                    }
                }
            }


            StringBuilder sb = new StringBuilder();
            if (stack.isEmpty()) {
                sb.append("/");
            } else {

                while (!stack.isEmpty()) {
                    String last = stack.getLast();
                    sb.append("/").append(last);
                    stack.removeLast();
                }
            }


            return sb.toString();
        }
    }

// No recursive or repeatative pattern --> Two pointers
// [0,s): processed
//s: to be filled in
//f: inspector
//[s,f): unnecessary

// Example:
// /home   //foo   /

// /a  /.. /.. /b  /.. /c  //. //

// /a  /.  /b  /.. /.. /c  /
    //            f
// /a   /b
//         s
// f reads current section ending before /, cases:
//     /.: do nothing
//     /.. : s back to the right of prev / (or index == 0)
//     consecutive/: jump over

// Postprocessing: delete the / at the tail;unnecesary


    static class SolutionII {
        public String simplifyPath(String path) {
            if (path == null || path.length() == 0) return "";
            int s = 0, f = 0;
            char[] ch = path.toCharArray();

            while (f < path.length()) {
                int e = f + 1;
                while (e < path.length() && ch[e] != '/') e++;
                //now [f, e) is /XX or /
                String substr = path.substring(f, e);
                if (substr.equals("/.") || substr.equals("/")) {
                    //do nothing
                    f = e;
                } else if (substr.equals("/..")) {
                    //move s one layer back
                    if (s > 0) s--;
                    while (s > 0 && ch[s] != '/') s--;
                    f = e;
                } else { //normal /X case
                    while (f != e) {
                        ch[s++] = ch[f++];
                    }
                }

            }
            if (s == 0) s++;
            //no postprocessing
            return new String(Arrays.copyOf(ch, s));
        }
    }

//      /a  /b  /c  /d  /   /.  /.  /   /..


    public static void main(String[] args) {
        SolutionII solution = new SolutionII();
        String target = "/a//b////c/d//././/..";
        System.out.println(solution.simplifyPath(target));
    }
}
