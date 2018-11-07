package top.guaiguo.springdps.node;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-11-07 10:50
 */
public class DictionaryTreeTest {

    public static void main(String[] args) {

        DictionaryTree dt = new DictionaryTree();

        dt.add("interest");
        dt.add("interesting");
        dt.add("interested");
        dt.add("inside");
        dt.add("insert");
        dt.add("apple");
        dt.add("inter");
        dt.add("interesting");

        dt.print();

        boolean isFind = dt.find("inside");
        System.out.println("find inside : " + isFind);

        int count = dt.count("inter");
        System.out.println("count prefix inter : " + count);

    }
}
