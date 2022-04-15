package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Dom2
 * @description: TODO
 * @author: luweiming
 * @date: 2022/4/1
 **/
class Node {

    int key,val;
    Node next,prev;
    public Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}

class DoubleList {
    //虚拟出头节点和尾结点
    private Node head, tail;
    private int size;

    //初始化双链表
    public DoubleList() {
        //虚拟头结点
        head = new Node(0, 0);
        //虚拟头结点
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //要加到链表尾部，且越靠近链表尾部，越表示最近使用过
    public void addLast(Node x) {
        //比如当前链表为：head <-> 1 <-> tail，加入结点x = 2
        x.prev = tail.prev;
        // 完成结点2指向两端的箭头  head <-> 1 <- 2 -> tail; 此时tail.pre = 结点1还未断开
        x.next = tail;
        //head <-> 1 <-> 2 -> tail;
        tail.prev.next = x;
        //head <-> 1 <-> 2 <-> tail;
        tail.prev = x;
        //更新链表长度
        size++;
    }

    // 删除指定结点
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    // 删除并返回头结点
    public Node removeHead() {
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        // size在remove中更新了
        remove(first);
        // 用作在哈希表中移除最久未使用的数据值
        return first;
    }

    // 获取链表长度
    public int getSize() {
        return size;
    }
}

public class LRUCache {

    private Map<Integer, Node> map;
    private DoubleList doubleList;
    private int cap;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.doubleList = new DoubleList();
        this.cap = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            // 先将key标记为最近使用，再返回value
            makeRecently(key);
            return map.get(key).val;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deleteKey(key); // 从原map中移除该key
            addRecently(key, value); // 更新最近使用
            return;
        }

        int size = doubleList.getSize();
        if (size == cap) { // 说明需要移除最久未使用的元素了
            removeLeastRecently();
        }
        addRecently(key, value); //添加新的元素进来
    }


    public void makeRecently(int key) { // 将某个key标记为最近使用的元素（map中已存在的）
        Node x = map.get(key);
        doubleList.remove(x); // 先从双链表删除
        doubleList.addLast(x); // 再添加到链表末尾， 因为尾部是最近使用过的元素
    }

    public void addRecently(int key, int value) { // 添加最近使用过的元素
        Node x = new Node(key, value);
        doubleList.addLast(x);
        map.put(key, x);  //更新map
    }

    public void deleteKey(int key) {
        Node x = map.get(key);
        map.remove(key);
        doubleList.remove(x); // 在map中和cache中同时删除
    }

    // 删除最久未使用的元素
    public void removeLeastRecently() {
        // 最久未使用的一定在链表头部
        Node oldNode = doubleList.removeHead();
        int oldKey = oldNode.key;
        map.remove(oldKey);
    }
}
