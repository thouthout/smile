package com.smile.vo;

public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(){}

    public ListNode(int val){
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
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
        System.out.println();
        System.out.println("=====");
    }
}
