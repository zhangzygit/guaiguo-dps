package top.guaiguo.springdps.abstractTest;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-04-29 14:50
 */
public interface A {

    void testA();

    default void defaultA() {
        System.out.println("default A");
    }

    static void staticA() {
        System.out.println("static A");
    }
}
