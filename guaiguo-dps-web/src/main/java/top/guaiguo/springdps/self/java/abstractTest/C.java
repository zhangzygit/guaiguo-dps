package top.guaiguo.springdps.self.java.abstractTest;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-09-18 16:32
 */
public class C extends AbstractDemoB implements B {

    public static void main(String[] args) {
        ParentInterface c = new C();
        c.invokeMethod();

        A.staticA();
        C d = new C();
        d.defaultA();
    }

    @Override
    public void testB() {

    }

    @Override
    public void testA() {

    }

    @Override
    public Object getObject() {
        return "CCCCCCCCCCCCC";
    }
}
