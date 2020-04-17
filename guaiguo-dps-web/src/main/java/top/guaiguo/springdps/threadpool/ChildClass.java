package top.guaiguo.springdps.threadpool;

public class ChildClass implements ISuper {

    public static int s = 1;

    public final static int ss = 2;

    static {
        System.out.println("child clinit");
    }

    public ChildClass() {
        System.out.println("child init");
    }
}
