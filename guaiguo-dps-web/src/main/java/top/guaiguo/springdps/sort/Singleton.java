package top.guaiguo.springdps.sort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-06-28 19:06
 */
public class Singleton {

    private Singleton() {
        System.out.println("111");
    }

    private static Singleton sin = null;

    public static Singleton getInstance() {
        if (sin == null) {
            synchronized (Singleton.class) {
                if (sin == null) {
                    sin = new Singleton();
                }
            }
        }

        return sin;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        PrintBean printBean = new PrintBean(0, 2);
        ThreadNum threadNum = new ThreadNum(printBean);
        ThreadLetter threadLetter = new ThreadLetter(printBean);
        executorService.execute(threadNum);
        executorService.execute(threadLetter);

        executorService.shutdown();
    }

    static class ThreadNum implements Runnable {

        private PrintBean printBean;

        public ThreadNum(PrintBean printBean) {
            this.printBean = printBean;
        }

        @Override
        public void run() {
            for (int i = 1; i < 27; i++) {
                try {
                    System.out.println(i);
                    Thread.sleep((int) (100 + Math.random() * 1000));
                    printBean.printNum();
                    printBean.waitForLetter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class ThreadLetter implements Runnable {

        private PrintBean printBean;

        public ThreadLetter(PrintBean printBean) {
            this.printBean = printBean;
        }

        @Override
        public void run() {
            for (char i = 97; i < 123; i++) {
                try {
                    printBean.waitForNum();
                    System.out.println(i);
                    Thread.sleep((int) (50 + Math.random() * 1000));
                    printBean.printLetter();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PrintBean {

        private int executeNum;

        private int systemNum;

        public PrintBean(int executeNum, int systemNum) {
            this.executeNum = executeNum;
            this.systemNum = systemNum;
        }


        public synchronized void printNum() throws InterruptedException {
            executeNum++;
            if (executeNum == systemNum) {
                notifyAll();
            }
        }

        public synchronized void printLetter() throws InterruptedException {
            executeNum--;
            if (executeNum == 0) {
                notifyAll();
            }
        }

        public synchronized void waitForNum() throws InterruptedException {
            //不能只是用if判断一次，会出现不同步的问题
            while (executeNum == 0) {
                wait();
            }
        }

        public synchronized void waitForLetter() throws InterruptedException {
            while (executeNum == systemNum) {
                wait();
            }
        }

    }
}
