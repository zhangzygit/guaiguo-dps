package top.guaiguo.springdps.threadpool;

public class SuperClass {
    static {
        System.out.println("spuer init");
    }

    public SuperClass() {
        System.out.println("super construtor");
    }

    public static int m = 1;
}
