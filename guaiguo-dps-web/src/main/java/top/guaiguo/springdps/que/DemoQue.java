package top.guaiguo.springdps.que;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-11-09 17:45
 */
public class DemoQue {
    private static Queue<Pet> catQue = new LinkedList<Pet>();
    private static Queue<Pet> dogQue = new LinkedList<Pet>();

    public DemoQue(){
    }

    public static void main(String[] args){
        Cat cat = new Cat();
        Dog dog = new Dog();
        Dog dog2 = new Dog();
        catQue.add(cat);
        dogQue.add(dog2);
        dogQue.add(dog);

        Pet poll = dogQue.poll();
        dogQue.isEmpty();

    }
}

class Pet{

}
class Cat extends Pet{

}
class Dog extends Pet{

}
