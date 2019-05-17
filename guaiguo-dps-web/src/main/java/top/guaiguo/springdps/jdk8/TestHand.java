package top.guaiguo.springdps.jdk8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-05-10 17:36
 */
public class TestHand {

    static char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        Integer num = 5;
        String s = Integer.toHexString(num);
        System.out.println(s.toUpperCase());
        System.out.println(toHex(num));
        double pow = Math.pow(2, 4);
        System.out.println(pow);

        List array = new ArrayList<>();
        array.add(1);
        array.add(2);
    }

    private static String toHex(int num) {
        if (num == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(chars[num % 16]);
            num = num / 16;
        }
        return sb.reverse().toString();
    }



}
