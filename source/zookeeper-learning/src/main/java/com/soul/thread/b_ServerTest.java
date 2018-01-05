package com.soul.thread;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/***
 * @author wangkun1
 * @version 2018/1/5 
 */
public class b_ServerTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static CountDownLatch countDownLatch2 = new CountDownLatch(300);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    Request request = new Request.Builder().url("https://www.baidu.com/")
                            .get()
                            .build();
                    try {
                        Response response = new OkHttpClient().newCall(request).execute();
                        if (!response.isSuccessful()) {
                            System.out.println(Thread.currentThread().getName() + "请求有错！" + response);
                        } else {
                            System.out.println(Thread.currentThread().getName() + "请求成功: " + response);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                countDownLatch2.countDown();
            }).start();
        }
        countDownLatch.countDown();
        countDownLatch2.await();
    }
}
