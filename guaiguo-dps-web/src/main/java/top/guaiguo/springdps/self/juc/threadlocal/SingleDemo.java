package top.guaiguo.springdps.self.juc.threadlocal;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description 单例模式下测试成员变量线程共享，如果不做处理，多线程下访问属性冲突，
 * 非单例情况下多线程访问不受影响，各自的成员变量互补改变
 * @Datetime 2018-09-21 9:32
 */
public class SingleDemo {

    private A a;
    private ThreadLocal<A> aThreadLocal = new ThreadLocal<A>();

    //单例情况
    private static final SingleDemo s = new SingleDemo();

    private SingleDemo() {
    }

    public static SingleDemo getInstance() {
        return s;
    }


    public /*synchronized*/ void testA() {
        a = new A(Thread.currentThread().getId());
        sleep();
        System.out.println(Thread.currentThread().getId() + "   " + a.getI() + "   " + a.toString());
    }

    public void testThreadLocal() {
        a = new A(Thread.currentThread().getId());
        sleep();
        System.out.println(
                Thread.currentThread().getId() + "   " + aThreadLocal.get().getI() + "   " + aThreadLocal.get()
                        .toString() + "----->" + a.toString());
    }

    void sleep() {
        try {
            aThreadLocal.set(a);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tests {

    private ThreadLocal<String> stringThreadLocal = new ThreadLocal<String>();
    private ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
//        testA();
        testThreadLocal();

        /*String str= "123wqeqe";
        Integer a= 213131;

        Tests tests = new Tests();
        tests.integerThreadLocal.set(a);
        tests.stringThreadLocal.set(str);
        System.out.println(tests.integerThreadLocal);
        System.out.println(tests.stringThreadLocal);
        Thread t = Thread.currentThread();
        System.out.println(t);*/
        //当前运行线程中存放一个ThreadLocalMap对象，键为integerThreadLocal，值为a，组成Entry对象，与其他Entry组成数组结构存储

    }

    private static void testA() {
        //多线程情况下访问a下面的属性不是自己设置的,除非synchronized修饰
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingleDemo.getInstance().testA();
            }).start();
        }
    }

    private static void testThreadLocal() {
        //多线程情况下变量a引用地址不同，但是属性是自己设置的
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                SingleDemo.getInstance().testThreadLocal();
            }).start();
        }
    }
}

class A {

    private long i;

    public A(long i) {
        this.i = i;
    }

    public long getI() {
        return i;
    }

    public void setI(long i) {
        this.i = i;
    }
}
