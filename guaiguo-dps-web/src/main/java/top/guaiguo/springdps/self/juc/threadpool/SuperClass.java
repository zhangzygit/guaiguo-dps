package top.guaiguo.springdps.self.juc.threadpool;

public class SuperClass {
    static {
        System.out.println("spuer init");
    }

    public SuperClass() {
        System.out.println("super construtor");
    }

    public static int m = 1;
}
