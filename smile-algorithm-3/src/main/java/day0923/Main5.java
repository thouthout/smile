package day0923;


import org.w3c.dom.css.CSSRuleList;

import java.util.Objects;
import java.util.Stack;

/**
 * @className: Main5
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 **/
public class Main5 {


    public String longestPublic(String[] strs){
        String firstStr = strs[0];

        for (int i = 0; i < firstStr.length(); i++) {
            char c = firstStr.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (!Objects.equals(c, strs[j].charAt(i)) || strs[j].length() <= i){
                    return firstStr.substring(0, i);
                }
            }
        }
        return firstStr;
    }


    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {

        Integer maxStart = 0;
        Integer maxEnd = 0;
        Integer maxLen = 0;
        Integer strLen = s.length();

        //存储是否是回文子串
        boolean[][] result = new boolean[strLen][strLen];


        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(r) == s.charAt(l) && (r - l <= 2 || (result[l + 1][r - 1]))){
                    result[l][r] = true;
                    if (r - l > maxLen){
                        maxLen = r - l;
                        maxStart = l;
                        maxEnd = r;
                    }
                }
            }

        }
        return s.substring(maxStart, maxEnd + 1);
    }

   /* public static void main(String[] args) {
        String abcb = longestPalindrome("abab");
        System.out.println(abcb);
    }
*/


static class ListNode{
        private Integer val;
        private ListNode next;

        public ListNode (Integer val){
            this.val = val;
        }

    public ListNode (Integer val, ListNode next){
        this.val = val;
        this.next = next;
    }
}


//删除倒数第target个节点
public ListNode deleteTarget(ListNode head, Integer n){

    ListNode dumary = new ListNode(0);
    dumary.next = head;
    ListNode fast = head;
    ListNode slow = dumary;

    for (int i = 0; i < n; ++i) {
        fast = fast.next;
    }

    ListNode prev = null;
    while(fast != null){
        prev = slow;
        slow = slow.next;
        fast = fast.next;
    }
    prev.next = slow.next;

    return dumary.next;



}


/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 */



    public Character get(Character chatAt){
        if(chatAt.equals(')')){
            return '(';
        }
        if(chatAt.equals('}')){
            return '{';
        }
        if(chatAt.equals(']')){
            return '[';
        }
        return null;
    }

    public boolean isEffective(String s){

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            Character character = get(charAt);
            if(!stack.empty() && stack.peek().equals(character)){
                stack.pop();
            } else {
                stack.push(charAt);
            }
        }
        return stack.empty();
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：["()"]

     */


    /**
     * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     *
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：[1]

     */


    public static ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode prev = newHead, curr = head;
        while(curr != null && curr.next != null) {
            // 记录当前节点的next节点
            ListNode next = curr.next;

            // 交换节点
            curr.next = curr.next.next;
            next.next = curr;
            prev.next = next;

            // prev，curr指针后移
            prev = curr;
            curr = curr.next;
        }
        return newHead.next;
    }


















    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null){
         return head;
        }

        ListNode newHead = new ListNode(-1, head);

        ListNode p = newHead;
        while (p.next != null && p.next.next != null){
            ListNode a = p.next;
            ListNode b = p.next.next;
            p.next = b;
            a.next = b.next;
            b.next = a;

            p = a;
        }
        return newHead.next;


    }







    public static void main(String[] args) {
        /*ListNode node3 = new ListNode(3);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        //ListNode listNode = swapPairs(node4);
        ListNode listNode = swapPairs2(node1);
        System.out.println(listNode);*/

        removeDuplicates(new int[]{1, 2, 3, 3, 4, 4, 5, 5});
    }


    /**
     * 输入：nums = [1,1,2]
     * 输出：2, nums = [1,2,_]
     * 解释：函数应该返回新的长度 2 ，并且原数组 nums  的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
     * 示例 2：
     *
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
     *

     */



    public static int removeDuplicates(int[] nums) {

        int left = 0;


        for (int i = 1; i < nums.length; i++) {

            if (nums[left] != nums[i]){
                left ++;
                nums[left] = nums[i];
            }
        }
        return left + 1;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     * 示例 2：
     *
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。

     */










    public static int removeElement(int[] nums, int val) {

        int left = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != val){
                nums[left] = nums[i];
                left ++;
            }
        }
        return left;
    }

    public int removeElement1(int[] nums, int val) {

        int i = 0;

        for (int j = 0; j < nums.length; j++) {
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }


    /**
     * 28. 找出字符串中第一个匹配项的下标
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
     *
     *
     *
     * 示例 1：
     *
     * 输入：haystack = "sadbutsad", needle = "sad"
     * 输出：0
     * 解释："sad" 在下标 0 和 6 处匹配。
     * 第一个匹配项的下标是 0 ，所以返回 0 。
     * 示例 2：
     *
     * 输入：haystack = "leetcode", needle = "leeto"
     * 输出：-1
     * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
     */













    public int strStr(String haystack, String needle) {



        return 0;
    }




}
