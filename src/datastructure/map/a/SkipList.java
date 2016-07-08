package datastructure.map.a;

import java.util.Random;

public class SkipList<K extends Comparable<K>,V> {
    private QuadNode<K, V> head = new QuadNode<K, V>(null, null, 0);
    final static Random random = new Random();

    public void insert(K key, V value) {
        int level = 0;

        while (random.nextBoolean()) {
            level++;
        }

        while (head.getLevel() < level) {
            QuadNode<K, V> newHead = new QuadNode<K, V>(null, null, head.getLevel() + 1);
            head.setUp(newHead);
            newHead.setDown(head);
            head = newHead;
        }

        head.insert(key, value, level, null);
    }

    public V find(K key) {
        return head.find(key).getValue();
    }

    public void delete(K key) {
        for (QuadNode<K,V> node = head.find(key); node != null; node = node.getDown()) {
            node.getPrevious().setNext(node.getNext());
            if (node.getNext() != null) {
                node.getNext().setPrevious(node.getPrevious());
            }
        }

        while (head.getNext() == null) {
            head = head.getDown();
            head.setUp(null);
        }
    }

    @Override
    public String toString() {
        return head.toString();
    }
}