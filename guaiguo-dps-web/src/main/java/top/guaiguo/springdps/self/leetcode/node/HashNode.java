package top.guaiguo.springdps.self.leetcode.node;

import java.util.Objects;

import top.guaiguo.springdps.self.leetcode.node.HashNode.Node;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-11-22 14:51
 */
public class HashNode<K, V> {

    public HashNode(Node<K, V>[] table) {
        this.table = table;
    }

    Node<K, V>[] table;

    <K, V> void put(int hash, K key, V value) {
        Node<K, V>[] tab;
        Node<K, V> p;
        int n, i;
        if ((tab = (Node<K, V>[]) table) == null || (n = tab.length) == 0) {
            return;
        }

        if ((p = tab[i = (n - 1) & hash]) == null) {
            return;
        } else {
            Node<K, V> e;
            K k;
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = new Node<>(hash, key, value, p);
                    break;
                }
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k)))) {
                    break;
                }
                p = e;
            }
        }


    }


    static class Node<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

    }
}


class Test{
    public static void main(String[] args){
        //java数值传递
        Node<String, Integer> b = new Node<String, Integer>(2,"b",2,null);
        Node<String, Integer> a = new Node<String, Integer>(1,"a",1,b);
        Node[] nodes = new Node[2];
        nodes[0] = a;
        nodes[1] = b;
        HashNode<String, Integer> sss = new HashNode<>(nodes);
        sss.put(3,"c",3);

        System.out.println(sss.table);
    }
}
