package top.guaiguo.springdps.que;

import java.util.Arrays;
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

    public DemoQue() {
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        Dog dog = new Dog();
        Dog dog2 = new Dog();
        catQue.add(cat);
        dogQue.add(dog2);
        dogQue.add(dog);

        Pet poll = dogQue.poll();
        dogQue.isEmpty();

        int[] array = {1, 2, 56, 7, 9, 0, 12, 436, 9};
        int search = search(array, 9, 0, array.length - 1);
        System.out.println(search);

//        maopao(array);
        xuanze(array);
        System.out.println(Arrays.toString(array));

    }

    static int search(int[] array, int num, int start, int end) {
        int s = start;
        int e = end;
        int m = (s + e) / 2;

        if (array[m] == num) {
            return m;
        }

        if (array[m] < num) {
            s = m + 1;
            return search(array, num, s, e);
        } else {
            e = m;
            return search(array, num, s, e);
        }
    }

    static void maopao(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

    static void xuanze(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            System.out.println(Arrays.toString(array));
        }
    }


}

class Pet {

}

class Cat extends Pet {

}

class Dog extends Pet {

}
