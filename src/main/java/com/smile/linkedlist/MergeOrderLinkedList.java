package com.smile.linkedlist;

import com.smile.vo.ListNode;

import java.util.Objects;

/**
 * @author luweiming
 * @date 2020/11/21 7:48 下午
 * 合并两个有序数组
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeOrderLinkedList {
    
    public ListNode merge(ListNode listNode1, ListNode listNode2){
        if(Objects.isNull(listNode1)){
            return listNode2;
        }
        if(Objects.isNull(listNode2)){
            return listNode1;
        }

        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;

        while (listNode1 != null && listNode2 != null) {
            if(listNode1.getVal() <= listNode2.getVal()){
                prev.next = listNode1;
                listNode1 = listNode1.next;
            } else {
                prev.next = listNode2;
                listNode2 = listNode2.next;
            }
            prev = prev.next;
        }

        prev.next = Objects.isNull(listNode1) ? listNode2 : listNode1;

        return prehead.next;
    }

}
