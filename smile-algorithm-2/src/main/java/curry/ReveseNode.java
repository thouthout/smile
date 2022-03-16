package curry;

/**
 * @className: ReveseNode
 * @description: TODO
 * @author: luweiming
 * @date: 2021/12/12
 **/
public class ReveseNode {

    public static Node reveseNode (Node head){
        //记录反转后链表头结点
        Node prev = null;
        //当前迭代节点
        Node curr = head;
        while (curr != null){
            //临时节点,记录一次迭代后的待反转列表头结点
            Node tempNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tempNode;
        }
        return prev;
    }

      public static void main(String[] args) {
          Node node1 = new Node (1, null);
          Node node2 = new Node (2, node1);
          Node node3 = new Node (3, node2);
          Node node4 = new Node (4, node3);
          Node node5 = new Node (5, node4);

          Node node = reveseNode(node5);
        System.out.println(node);
      }
}
