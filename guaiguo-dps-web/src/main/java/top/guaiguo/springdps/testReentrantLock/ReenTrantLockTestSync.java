package top.guaiguo.springdps.testReentrantLock;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-05-30 18:19
 */
public class ReenTrantLockTestSync {

    final static Object obj = new Object();

    final static ReentrantLock lock = new ReentrantLock();

    final static ThreadPoolExecutor ex = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) throws InterruptedException {

        //不能中断read线程
        /*Thread read = new Thread(() -> {
            synchronized (obj) {
                System.out.println("read is runing!");
            }
        });

        Thread write = new Thread(() -> {
            synchronized (obj) {
                while (true) {
                    System.out.println("write is running!");
                }
            }
        });*/

        Thread read = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("read is runing!");
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                lock.unlock();
            }


        });

        Thread write = new Thread(() -> {
            try {
                lock.lock();
                while (true){
                    System.out.println("write is runing!");
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        });

        ex.execute(read);
        ex.execute(write);
        Thread.sleep(3000);
        //15秒后尝试中断read线程，不在读取
        read.interrupt();
        write.interrupt();

    }

}
