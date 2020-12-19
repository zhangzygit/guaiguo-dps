package top.guaiguo.springdps.self.leetcode.linked;

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
        Node<Integer> first = createNode(4);
        printNext(first);
//        printNext(reverseNodeNext(first));
        printPrev(reverseNodePrev(first));
    }

    /**
     * 1 -> 2 -> 3 -> 4
     * return  4 <- 3 <- 2 <- 1
     *
     * @param head
     * @return
     */
    private static Node<Integer> reverseNodePrev(Node<Integer> head) {
        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
        Node<Integer> next = head.next;
        if (next == null) {
            return head;
        }

        Node reHead = reverseNodePrev(next);
        head.prev = reHead;
        head.next = null;
        return head;
    }

    /**
     * 1 -> 2 -> 3 -> 4
     * return  4 -> 3 -> 2 -> 1
     *
     * @param head
     * @return
     */
    private static Node reverseNodeNext(Node<Integer> head) {
        // head看作是前一结点，head.getNext()是当前结点，reHead是反转后新链表的头结点
        Node<Integer> next = head.next;
        if (next == null) {
            return head;
        }

        Node reHead = reverseNodeNext(next);
        next.next = head;
        head.next = null;
        return reHead;
    }

    private static Node<Integer> createNode(int i) {
        Node<Integer> first = null;
        Node<Integer> newNode = null;
        Node e = null;
        for (int j = 1; j <= i; j++) {
            newNode = new Node<Integer>(j, null, null);
            if (first == null) {
                first = newNode;
                e = first;
                continue;
            }

            for (; ; e = e.next) {
                if (e.next == null) {
                    e.next = newNode;
                    break;
                }
            }
        }

        return first;
    }

    private static void printPrev(Node<Integer> e) {
        System.out.println("-----printPrev------------");
        do {
            System.out.println(e.e);
        } while ((e = e.prev) != null);
    }

    private static void printNext(Node<Integer> e) {
        System.out.println("-----printNext------------");
        do {
            System.out.println(e.e);
        } while ((e = e.next) != null);
    }

}

