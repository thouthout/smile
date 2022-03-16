package curry;

/**
 * @className: Node
 * @description: TODO
 * @author: luweiming
 * @date: 2021/12/12
 **/
public class Node {
    public int val;

    public Node next;

    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }



    /*合并两个有序链表，我们可以用迭代的方法来实现上述算法。当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。

    算法

    首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：
    如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。

    在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可。

    下图展示了 1->4->5 和 1->2->3->6 两个链表迭代合并的过程：*/


    public static Node mergeTwoLists(Node l1, Node l2) {

        Node dummy = new Node(0, null);

        Node pre = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        if (l1 == null) {
            pre.next = l2;
        } else {
            pre.next = l1;
        }

        return dummy.next;
    }

  public static void main(String[] args) {
      Node node1 = new Node (9, null);
      Node node2 = new Node (7, node1);
      Node node3 = new Node (5, node2);
      Node node4 = new Node (3, node3);
      Node node5 = new Node (1, node4);

      Node node21 = new Node (10, null);
      Node node22 = new Node (9, node21);
      Node node23 = new Node (8, node22);
      Node node24 = new Node (6, node23);
      Node node25 = new Node (2, node24);

      mergeTwoLists(node5, node25);
  }
}

