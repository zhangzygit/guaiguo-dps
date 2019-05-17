package top.guaiguo.springdps.jdk8;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-05-13 10:31
 */
public class MyList<T> {

    private Object[] array;
    private int size;
    private int count;


    public MyList() {
        this.array = new Object[10];
    }

    public void add(T t) {
        array[size++] = t;
        count = size;
    }

    public T get(int i) {
        return (T) array[i];
    }

    public int size(){
        return size;
    }

    public static void main(String[] args){
        MyList<Integer> myList= new MyList<Integer>();
        myList.add(1);
        myList.add(2);
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
    }
}
