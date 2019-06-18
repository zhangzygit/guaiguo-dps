package top.guaiguo.springdps.node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-06-18 12:03
 */
public class TestBinaryTree {

    static final class Node<E> {

        E e;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E e, Node<E> left, Node<E> right, Node<E> parent) {
            this.e = e;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(E e) {
            this.e = e;
        }

        private E getValue() {
            return this.e;
        }

        /**
         * 判断是否有左节点
         *
         * @return boolean
         */
        public boolean hasLeftNode() {
            if (this.left == null || this.left.getValue() == null) {
                return false;
            }
            return true;
        }

        /**
         * 判断是否有右节点
         *
         * @return boolean
         */
        public boolean hasRightNode() {
            if (this.right == null || this.right.getValue() == null) {
                return false;
            }
            return true;
        }

    }

    public static void main(String[] args) {
        Node<Integer> $21 = new Node<Integer>(21);
        Node<Integer> $22 = new Node<Integer>(22);
        Node<Integer> $31 = new Node<Integer>(31);
        Node<Integer> $32 = new Node<Integer>(32);
        Node<Integer> $33 = new Node<Integer>(33);
        Node<Integer> root = new Node<Integer>(1);

        root.left = $21;
        root.right = $22;
        $21.parent = root;
        $22.parent = root;

        $21.left = $31;
        $21.right = $32;

        $31.parent = $21;
        $32.parent = $21;
        $22.left = $33;
        $33.parent = $22;

        printBinaryTree(root);
    }

    private static void printBinaryTree(Node<Integer> root) {
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        Node nLast = null;
        Node last = root;
        int i = 1;
        while (nodes.size() > 0) {
            Node poll = nodes.poll();
            if (poll.hasLeftNode()) {
                nodes.add(poll.left);
                nLast = poll.left;
            }

            if (poll.hasRightNode()) {
                nodes.add(poll.right);
                nLast = poll.right;
            }
            if(i == 3){
                System.out.print("   " + poll.e);
            }

            if(last.equals(poll)){
                i++;
                System.out.println();
                last = nLast;
            }
        }
    }

}
