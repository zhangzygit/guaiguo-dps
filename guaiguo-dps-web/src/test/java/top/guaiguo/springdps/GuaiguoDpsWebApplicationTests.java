package top.guaiguo.springdps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuaiguoDpsWebApplicationTests {

    @Test
    public void contextLoads() {
        List<String> list = new ArrayList<>();
        list.add("q23123");
        list.add("sqwe");
        list.add("hdhr");
        System.out.println(list.toString());
        list.stream().forEach(System.out::print);
        String[] strings = {"1"};
        Stream<String> stream = Arrays.stream(strings);
    }

}
