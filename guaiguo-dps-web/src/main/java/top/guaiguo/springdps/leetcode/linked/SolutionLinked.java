package top.guaiguo.springdps.leetcode.linked;


import top.guaiguo.springdps.leetcode.ListNode;

/**
 * @author zhangzhaoyuan
 * @date 2019/12/7 22:40
 */
public class SolutionLinked {

    public static void main(String[] args) {
        ListNode listNode = createLinked();

//        ListNode removeElements = removeElements(listNode, 6);
//        ListNode reverse = reverse(listNode);
        ListNode reverse2 = reverse2(listNode);

        System.out.println(1);
    }

    private static ListNode createLinked() {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        return r1;
    }


    //2->4->6->6->5
    public static ListNode removeElements(ListNode root, int val) {
        if (root == null) {
            return root;
        }
        root.next = removeElements(root.next, val);
        return root.val == val ? root.next : root;
    }

    /**
     * 1 -> 2 -> 3 -> 4
     * 4 -> 3 -> 2 -> 1
     *
     * @param root
     * @return
     */
    public static ListNode reverse(ListNode root) {
        if (root.next == null) {
            return root;
        }
        ListNode reverse = reverse(root.next);
        root.next.next = root;
        root.next = null;
        return reverse;
    }

    /**
     * 1 -> 2 -> 3 -> 4
     * 4 <- 3 <- 2 <- 1
     *
     * @param root
     * @return
     */
    public static ListNode reverse2(ListNode root) {
        if (root.next == null) {
            return root;
        }
        root.prev = reverse2(root.next);
        root.next = null;
        return root;
    }

}
