package com.smile.vo;

import lombok.Data;

@Data
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

    public int length(){
        ListNode head = this;
        int length = 0;
        while (head != null) {
            length ++;
            head = head.next;
        }
        return length;
    }

    public void printNode(){
        ListNode head = this;
        while (head != null){
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("=====");
    }
}
