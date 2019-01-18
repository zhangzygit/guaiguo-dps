package top.guaiguo.springdps.threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-01-18 14:20
 * https://blog.csdn.net/qq_25806863/article/details/71126867
 */
public class TestThreadPoolExecutor {

    volatile static int threadNo = 1;

    static ThreadPoolExecutor executorLink = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(2));
    static ThreadPoolExecutor executorSync = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new CallerRunsPolicy());

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
    }
}

class MyRunable implements Runnable {

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
