package com.soul.alg.leetcode;

/**
 * @author wangkun1
 * @version 2018/3/26
 */
public class AddTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        String num1 = extractNumStringFromListNode(l1);
        if ("0".equals(num1)) {
            return l2;
        }
        String num2 = extractNumStringFromListNode(l2);
        if ("0".equals(num2)) {
            return l1;
        }
        String sum = addStringNums(num1, num2);
        ListNode result = buildNumToListNodeWithString(sum);
        return result;
    }

    private ListNode buildNumToListNodeWithString(String sum) {
        if ("0".equals(sum)) {
            return new ListNode(0);
        }
        char[] chars = sum.toCharArray();
        ListNode headNode = new ListNode(Integer.parseInt(chars[chars.length - 1] + ""));
        ListNode p = headNode;
        for (int i = chars.length - 2; i >= 0; i--) {
            p.next = new ListNode(Integer.parseInt(chars[i] + ""));
            p = p.next;
        }
        return headNode;
    }

    private String addStringNums(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        Integer temp = 0;
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for (i = chars1.length - 1, j = chars2.length - 1; i >= 0 && j >= 0; i--, j--) {
            int i1 = Integer.parseInt(chars1[i] + "");
            int j1 = Integer.parseInt(chars2[j] + "");
            int result = i1 + j1 + temp;
            if (result > 9) {
                temp = 1;
            } else {
                temp = 0;
            }
            sb.append(result % 10);
        }
        if (i >= 0) {
            for (int k = i; k >= 0; k--) {
                int rest = Integer.parseInt(chars1[k] + "");
                rest = rest + temp;
                if (rest > 9) {
                    temp = 1;
                } else {
                    temp = 0;
                }
                sb.append(rest % 10);
            }
        } else if (j >= 0) {
            for (int k = j; k >= 0; k--) {
                int rest = Integer.parseInt(chars2[k] + "");
                rest = rest + temp;
                if (rest > 9) {
                    temp = 1;
                } else {
                    temp = 0;
                }
                sb.append(rest % 10);
            }
        }
        if (temp == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    private String extractNumStringFromListNode(ListNode l1) {
        StringBuilder sb = new StringBuilder();
        ListNode p = l1;
        while (p != null) {
            sb.append(p.val);
            p = p.next;
        }
        return sb.reverse().toString();
    }

    private ListNode buildNumToListNode(Long sum) {
        if (sum == 0) {
            return new ListNode(0);
        }
        ListNode headNode = new ListNode(Math.toIntExact(sum % 10));
        ListNode p = headNode;
        while (true) {
            sum /= 10;
            if (sum == 0) {
                break;
            }
            p.next = new ListNode(Math.toIntExact(sum % 10));
            p = p.next;
        }
        return headNode;
    }

    private Long extractNumFromListNode(ListNode l1) {
        ListNode p = l1;
        Long result = Long.valueOf(p.val);
        Long i = Long.valueOf(1);
        while (p.next != null) {
            Long temp = 1L;
            p = p.next;
            for (int j = 0; j < i; j++) {
                temp *= 10;
            }
            result += p.val * temp;
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode listNode2 = generateListNodes(new int[]{2, 4, 3});
        ListNode listNode5 = generateListNodes(new int[]{5, 6, 4});
        ListNode listNode = new AddTwoNumbers().addTwoNumbers(listNode2, listNode5);
        System.out.println();

    }

    private static ListNode generateListNodes(int[] ints) {
        ListNode headNode = new ListNode(ints[0]);
        ListNode p = headNode;
        for (int i = 1; i < ints.length; i++) {
            ListNode tempNode = new ListNode(ints[i]);
            p.next = tempNode;
            p = p.next;
        }
        return headNode;
    }
}
