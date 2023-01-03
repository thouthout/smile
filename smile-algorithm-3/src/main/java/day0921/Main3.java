package day0921;

import java.util.Objects;

/**
 * @className: Main3
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *  
 *
 * 示例 1：
 *
 *输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 **/
public class Main3 {


    public static void main(String[] args) {

    }



    public static LinedListNode addLined(LinedListNode node1, LinedListNode node2){

        LinedListNode head = null;
        LinedListNode curr = null;
        Integer carry = 0;

        while (node1 != null || node2 != null){
            Integer num1 = Objects.isNull(node1) ? 0 : node1.value;
            Integer num2 = Objects.isNull(node2) ? 0 : node2.value;

            Integer sum = num1 + num2 + carry;

            curr = new LinedListNode(sum % 10);
            if (head == null){
                head = curr;
            } else {
                curr = curr.next;
            }
            carry = sum / 10;
            if (node1 != null){
                node1 = node1.next;
            }
            if (node2 != null){
                node2 = node2.next;
            }
        }

        if (carry > 0){
            curr.next = new LinedListNode(carry);
        }

        return head;
    }





















    /*public LinkedNode getSum(LinkedNode l1,LinkedNode l2) {
        LinkedNode head = null;
        LinkedNode tail = null;
        int result = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.getData() : 0;
            int n2 = l2 != null ? l2.getData() : 0;
            int sum = n1 + n2 + result;
            if (head == null) {
                head = tail = new LinkedNode(sum % 10);
            }else {
                tail.setNext(new LinkedNode(sum % 10));
                tail = tail.getNext();
            }
            result = sum / 10;
            if (l1 != null) {
                l1 = l1.getNext();
            }
            if (l2 != null) {
                l2 = l2.getNext();
            }
        }
        if (result > 0) {
            tail.setNext(new LinkedNode(result));
        }
        return head;
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }*/


}


class LinedListNode{

    Integer value;

    LinedListNode next;

    public LinedListNode(Integer value){
        this.value = value;
    }
}
