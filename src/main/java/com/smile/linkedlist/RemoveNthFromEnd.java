package com.smile.linkedlist;

import com.smile.vo.ListNode;

/**
 * @author luweiming
 * @date 2020/12/5 上午9:59
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 *     LeetCode 19. 删除链表的倒数第N个节点
 *     给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *     给定一个链表: 1->2->3->4->5, 和 n = 2.
 *     当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * }
 */
public class RemoveNthFromEnd {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        prev.next = head;
        int length = head.length();
        if(n > length){
            return head;
        }
        int currentNode = 1;
        int removeNode = length - n;
        if(removeNode == 1) {
            return head.next;
        }
        while (removeNode != currentNode){
            currentNode ++;
            head = head.next;
        }
        head.next = head.next.next;
        return prev.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode1.printNode();
        ListNode listNode = removeNthFromEnd(listNode1, 1);
        listNode.printNode();


    }


}
