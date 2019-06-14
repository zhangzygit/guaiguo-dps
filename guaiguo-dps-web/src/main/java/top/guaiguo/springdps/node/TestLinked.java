package top.guaiguo.springdps.node;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-06-14 17:16
 */
public class TestLinked {

    static class Node<E> {

        E e;
        Node<E> prev;
        Node<E> next;

        public Node(E e, Node<E> prev, Node<E> next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        Node<Integer> third = new Node<Integer>(3, null, null);
        Node<Integer> fourth = new Node<Integer>(4, null, null);
        Node<Integer> secod = new Node<Integer>(2, null, null);
        Node<Integer> first = new Node<Integer>(1, null, null);

        first.next = secod;
        secod.next = third;
        third.next = fourth;

        Node e = first;
        Node<Integer> reverse = new Node<Integer>((Integer) e.e, e.next, null);
        Node<Integer> reverse2 = reverse.prev;
        do {
            System.out.println(e.e);
            reverse2.prev = reverse2.next;
            reverse2.next = null;
            reverse2 = reverse2.prev;
        } while ((e = e.next) != null);

        System.out.println("------------------>");
        e = reverse;
        do {
            System.out.println(e.e);
        } while ((e = e.prev) != null);

    }

}

