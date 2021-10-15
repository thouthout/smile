package con.smile.domain.linknode;

/**
 * @className: LinkedList
 * @description: TODO
 * @author: luweiming
 * @date: 2021/10/14
 **/
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(){}

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(int val, ListNode next){
        this.val = val;
        this.next = next;
    }


}
