package top.guaiguo.springdps.self.juc.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-01-18 14:20
 *
 * 任务队列没有大小限制：
 *
 * 如果线程数量<=核心线程数量，那么直接启动一个核心线程来执行任务，不会放入队列中。
 * 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是LinkedBlockingDeque的时候，超过核心线程数量的任务会放在任务队列中排队。
 * 如果线程数量>核心线程数，但<=最大线程数，并且任务队列是SynchronousQueue的时候，线程池会创建新线程执行任务，
 *      这些任务也不会被放在任务队列中。这些线程属于非核心线程，在任务完成后，闲置时间达到了超时时间就会被清除。
 *
 * 如果线程数量>核心线程数，并且>最大线程数，当任务队列是LinkedBlockingDeque，
 *      会将超过核心线程的任务放在任务队列中排队。也就是当任务队列是LinkedBlockingDeque并且没有大小限制时，
 *      线程池的最大线程数设置是无效的，他的线程数最多不会超过核心线程数。
 *
 * 任务队列大小有限时
 * 当LinkedBlockingDeque塞满时，新增的任务会直接创建新线程来执行，当创建的线程数量超过最大线程数量时会抛异常。
 *
 * 注：SynchronousQueue没有数量限制。因为他根本不保持这些任务，而是直接交给线程池去执行。当任务数量超过最大线程数时会直接抛异常。
 *    如果线程数量>核心线程数，并且>最大线程数，当任务队列是SynchronousQueue的时候，会因为线程池拒绝添加任务而抛出异常。
 * ---------------------
 * 作者：喵了个呜s
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_25806863/article/details/71126867
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 *
 * https://blog.csdn.net/qq_25806863/article/details/71126867
 */
public class TestThreadPoolExecutor {

    static ThreadPoolExecutor executorLink = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2));
    static ThreadPoolExecutor executorSync = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS,
            new SynchronousQueue<>()/*, new CallerRunsPolicy()*/);

    public static void main(String[] args) throws InterruptedException {
        testLink();
//        testSync();
    }

    public static void testSync() throws InterruptedException {
        MyRunable rn = new MyRunable();
        executorSync.execute(rn);
        executorSync.execute(rn);
        executorSync.execute(rn);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executorSync.getCorePoolSize());
        System.out.println("线程池数" + executorSync.getPoolSize());
        System.out.println("队列任务数" + executorSync.getQueue().size());
        executorSync.execute(rn);
        executorSync.execute(rn);
        executorSync.execute(rn);
        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executorSync.getCorePoolSize());
        System.out.println("线程池数" + executorSync.getPoolSize());
        System.out.println("队列任务数" + executorSync.getQueue().size());
        Thread.sleep(7000);
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executorSync.getCorePoolSize());
        System.out.println("线程池数" + executorSync.getPoolSize());
        System.out.println("队列任务数" + executorSync.getQueue().size());

        executorSync.shutdown();
    }

    public static void testLink() throws InterruptedException {
        MyRunable rn = new MyRunable();
        executorLink.execute(rn);
        executorLink.execute(rn);
        executorLink.execute(rn);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + executorLink.getCorePoolSize());
        System.out.println("线程池数" + executorLink.getPoolSize());
        System.out.println("队列任务数" + executorLink.getQueue().size());
        executorLink.execute(rn);
        executorLink.execute(rn);
        executorLink.execute(rn);
        System.out.println("---再开三个---");
        System.out.println("核心线程数" + executorLink.getCorePoolSize());
        System.out.println("线程池数" + executorLink.getPoolSize());
        System.out.println("队列任务数" + executorLink.getQueue().size());
        Thread.sleep(8000);
        System.out.println("----8秒之后----");
        System.out.println("核心线程数" + executorLink.getCorePoolSize());
        System.out.println("线程池数" + executorLink.getPoolSize());
        System.out.println("队列任务数" + executorLink.getQueue().size());

        executorLink.shutdown();
    }

    static class MyRunable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


