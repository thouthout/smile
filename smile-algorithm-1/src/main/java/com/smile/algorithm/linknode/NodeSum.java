package com.smile.algorithm.linknode;

import con.smile.domain.linknode.ListNode;

import java.util.Objects;

/**
 * @className: NodeSum
 * @description: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *     <p>请你将两个数相加，并以相同形式返回一个表示和的链表。
 *     <p>你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *     <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/add-two-numbers
 *     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: luweiming
 * @date: 2021/10/14
 */
public class NodeSum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        //新生成返回指针
        ListNode head = null, tail = null;
        //进位
        int carry = 0;

        //1.遍历两个链表  直接相加
        while (l1 != null || l2 != null){
            //当前位的值
            int n1 = Objects.isNull(l1) ? 0 : l1.val;
            int n2 = Objects.isNull(l2) ? 0 : l2.val;
            //当前位相加的值
            int sum = n1 + n2 + carry;
            if (head == null){
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;

            if (l1 != null){
                l1 = l1.next;
            }
            if (l2 != null){
                l2 = l2.next;
            }
            if (carry > 0){
                tail.next = new ListNode(carry);
            }
        }
        return head;
    }

    /**
     * 判断是否有环
      * @param head
     * @return
     */
    public boolean ringLinkedList(ListNode head){

        if (Objects.isNull(head)){
            return false;
        }

        ListNode curr = head, pre = head;
        while(curr != null && curr.next != null){
            curr = curr = curr.next;
            head = pre.next = pre.next.next;
            if (curr == head){
                return true;
            }
        }
        return false;
    }
}
