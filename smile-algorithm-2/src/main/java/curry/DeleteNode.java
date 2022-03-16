package curry;

/**
 * @className: DeleteNode
 * @description: TODO
 * @author: luweiming
 * @date: 2021/12/24
 **/
public class DeleteNode {

    //删除当前链表节点
    public void deleteNode (Node currentNode){
        currentNode.next.setVal(currentNode.getVal());
        currentNode.next = currentNode.next.next;
    }

}
