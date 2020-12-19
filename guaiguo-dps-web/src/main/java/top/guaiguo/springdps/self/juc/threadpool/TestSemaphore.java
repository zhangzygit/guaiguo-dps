package top.guaiguo.springdps.self.juc.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA Ultimate.
 * CountDownLatch
 * http://www.itzhai.com/the-introduction-and-use-of-a-countdownlatch.html
 * http://www.importnew.com/21889.html
 * https://blog.csdn.net/yanhandle/article/details/9016329
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-03-26 17:08
 */
public class TestSemaphore {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 8, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        //5个共享资源
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 8; i++) {
            int finalI = i + 1;
            threadPoolExecutor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("Thread " + finalI + " begin work!");
                    Thread.sleep(2000);
                    System.out.println("Thread " + finalI + " end work!");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
    }
}
