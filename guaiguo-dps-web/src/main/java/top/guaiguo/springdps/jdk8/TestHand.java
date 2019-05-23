package top.guaiguo.springdps.jdk8;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

        Map<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("4",1);
        map.put("2",1);
        map.put("3",1);
        map.put("1",1);
        System.out.println(map);

        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        myLinkedList.addLast(1);
        myLinkedList.addLast(2);
        myLinkedList.addLast(3);

        TreeMap<String, Integer> stringIntegerTreeMap = new TreeMap<>();
        stringIntegerTreeMap.put("4",1);
        stringIntegerTreeMap.put("2",1);
        stringIntegerTreeMap.put("3",1);
        stringIntegerTreeMap.put("1",1);
        System.out.println(stringIntegerTreeMap.toString());


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
