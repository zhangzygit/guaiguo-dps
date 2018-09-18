package top.guaiguo.springdps.abstractTest;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-09-18 16:32
 */
public abstract class AbstractDemoB extends AbstractDemoA{

    @Override
    public Object getObject() {
        return new StringBuilder("123131");
    }
}
