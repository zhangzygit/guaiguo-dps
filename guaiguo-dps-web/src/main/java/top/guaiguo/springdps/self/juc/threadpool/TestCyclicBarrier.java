package top.guaiguo.springdps.self.juc.threadpool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
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
public class TestCyclicBarrier {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName() + " is cycleBarrier");
        });
        for (int i = 0; i < 5; i++) {
            int finalI = i + 1;
            threadPoolExecutor.execute(() -> {
                System.out.println("Thread--》" + finalI + "  begin runing");
                try {
                    Thread.sleep(2000);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread--》" + finalI + "  end runing");
            });
        }

    }
}
