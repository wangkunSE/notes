package com.soul.alg.sword;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author wangkun1
 * @version 2018/4/18
 */
public class StackSequence {

    public static void main(String[] args) throws URISyntaxException, IOException {
//        String inStack = "12345";
//        String outStack = "43512";
//        boolean result = new StackSequence().isStackSequence(inStack, outStack);
//        System.out.println(result);
        Desktop desktop = Desktop.getDesktop();
        URI uri = new URI("http://www.baidu.com");
//        for (int i = 0; i < 10; i++) {
//            desktop.browse(uri);
//        }
        File file = new File("D:/BugReport.txt");
        desktop.edit(file);

    }

    private boolean isStackSequence(String inStack, String outStack) {
        if (inStack == null || outStack == null) {
            return false;
        }
        char[] inChars = inStack.toCharArray();
        char[] outChars = outStack.toCharArray();
        int index = 0;
        Stack<Character> stack = new Stack<>();
        for (; index < inChars.length; index++) {
            if (outChars[0] != inChars[index]) {
                stack.push(inChars[index]);
            } else {
                break;
            }
        }
        index++;
        boolean flag = false;
        for (int i = 1; i < outChars.length; i++) {
            char outChar = outChars[i];
            //找队列后面是否存在该元素
            int temp = index;
            if (temp < inChars.length) {
                for (int j = temp; j < inChars.length; j++) {
                    if (inChars[j] != outChar) {
                        temp++;
                    } else {
                        for (int k = index; k < temp; k++) {
                            stack.push(inChars[k]);
                        }
                        index = temp + 1;
                        flag = true;
                    }
                }
            }
            if (!flag) {
                //找前面
                char pop = stack.pop();
                if (pop != outChar) {
                    return false;
                }
            }
            if (!flag && index >= inChars.length) {
                return false;
            }
        }
        return true;
    }
}
