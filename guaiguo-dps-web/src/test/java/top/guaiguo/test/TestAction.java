package top.guaiguo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-06-25 14:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestAction.class)
public class TestAction {

    @Test
    public void test1()
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        Class<? extends List> aClass = list.getClass();
        Method add = aClass.getDeclaredMethod("add", Object.class);
        add.invoke(list, "DEmo");

        System.out.println(list.toString());

    }
}
