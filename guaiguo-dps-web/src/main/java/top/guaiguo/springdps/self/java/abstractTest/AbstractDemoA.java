package top.guaiguo.springdps.self.java.abstractTest;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-09-18 16:32
 */
public abstract class AbstractDemoA implements ParentInterface{

    @Override
    public void invokeMethod() {
        System.out.println(getObject().toString());
    }

    public abstract Object getObject();
}
