package top.guaiguo.springdps.self.juc.threadpool;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-08-02 22:21
 */
public class TestSynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        final BlockingQueue<String> synchronousQueue = new SynchronousQueue<String>();

        Runnable producer = () -> {
            while (true) {
                try {
                    String data = UUID.randomUUID().toString();
                    synchronousQueue.offer(data);
                    System.out.println("Put: " + data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    String data = synchronousQueue.take();
                    System.out.println(Thread.currentThread().getName()
                            + " take(): " + data);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        /*new Thread(producer).start();
        new Thread(consumer).start();*/

        System.out.println(synchronousQueue.offer("1"));
        System.out.println(synchronousQueue.offer("2"));
        System.out.println(synchronousQueue.offer("3"));
        System.out.println(synchronousQueue.size());



    }
}
