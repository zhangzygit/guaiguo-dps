package top.guaiguo.springdps.controller;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description  静态方法和成员变量 编辑和运行都看父类
 *              非静态方法编辑看父类，运行看子类（使用多态的时候）
 * @Datetime 2019-04-29 15:29
 */
public class B extends A {

    int num = 8;

    static void method4() {

        System.out.println("B method_4");

    }

    @Override
    void method3() {

        System.out.println("B method_3");

    }


    public static void main(String[] args) {
        A f = new B();

        System.out.println(f.num);//与父类一致

        f.method4();//与父类一致

        f.method3();//编译时与父类一致，运行时与子类一致

        B b = new B();

        System.out.println(b.num);

        b.method4();

        b.method3();
    }
}
