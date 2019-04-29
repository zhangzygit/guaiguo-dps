package top.guaiguo.springdps.abstractTest;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-04-29 14:51
 */
public interface B extends A {
    void testB();

    @Override
    default void defaultA() {
        System.out.println("deffault B");
    }


}
