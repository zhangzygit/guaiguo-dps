package top.guaiguo.springdps.self.juc.threadpool;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
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
public class TestCountDownLatch {

    private final ThreadLocal<List<Integer>> ss = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            executor.execute(() -> {
                try {
                    begin.await();
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("Thread --" + finalI + " is arrived!");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    end.countDown();
                }
            });
        }

        System.out.println("Game Start");
        Thread.sleep(3000);
        // begin减一，开始游戏
        begin.countDown();
        System.out.println("Runner begin run");
        // 等待end变为0，即所有选手到达终点
        //如果等待时间超出，返回 await = false但是程序继续往下走
        //boolean await = end.await(10, TimeUnit.MILLISECONDS);
        end.await();
        System.out.println("Game Over");
        executor.shutdown();

    }
}
