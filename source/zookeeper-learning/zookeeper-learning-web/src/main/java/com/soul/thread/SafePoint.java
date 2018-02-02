package com.soul.thread;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * @author wangkun1
 * @version 2018/2/1
 */
@ThreadSafe
public class SafePoint {
    @GuardedBy("this")
    private int x, y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint p) {
        this(p.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized int[] get() {
        return new int[]{x, y};
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void main(String[] args) {
        SafePoint safePoint = new SafePoint(0, 1);
        int[] ints = safePoint.get();
        System.out.println(ints);

        SafePoint safePoint1 = new SafePoint(safePoint);
        int[] ints1 = safePoint1.get();
        for (int i : ints1) {
            System.out.println(i);
        }
    }

}