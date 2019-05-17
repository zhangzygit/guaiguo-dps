package top.guaiguo.springdps.jdk8;

import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description FIFO LIFO
 * @Datetime 2019-05-13 10:53
 */
public class MyLinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;


    public void addLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<E>(e, l, null);
        last = newNode;

        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public void addFirst(E e) {
        Node<E> l = first;
        Node<E> newNode = new Node<E>(e, null, l);
        first = newNode;

        if (l == null) {
            last = newNode;
        } else {
            l.prev = newNode;
        }
        size++;
    }

    public E getFirst() {
        final Node<E> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E getLast() {
        final Node<E> f = last;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
    }

    public E get(int index) {
        Node<E> x = first;
        if (index < (size << 2)) {
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x.item;
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x.item;
        }
    }

    class Node<E> {

        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E e, Node<E> prev, Node<E> next) {
            this.item = e;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "put--->" + finalI);
                myLinkedList.addLast(finalI);
                System.out.println(Thread.currentThread().getName() + "---->" + myLinkedList.getLast());
                countDownLatch.countDown();
            }).start();

        }

        countDownLatch.await();
        System.out.println(myLinkedList);
    }
}

