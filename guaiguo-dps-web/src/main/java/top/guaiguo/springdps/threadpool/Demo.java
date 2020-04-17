package top.guaiguo.springdps.threadpool;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        //add();
        //直接调用父类的字段，不会触发子类的初始化
//        System.out.println(ChildClass.m);
        SuperClass[] superClasses = new SuperClass[19];
        System.out.println(superClasses);
        ChildClass childClass = new ChildClass();
        System.out.println(ChildClass.ss);
    }

    public static long add() {
        long a = 1;
        int b = 9;
        long c = (a + b) * 10;
        return c;
    }
}
