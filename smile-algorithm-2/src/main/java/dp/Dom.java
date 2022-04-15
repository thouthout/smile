package dp;

import curry.Node;

import java.util.*;

/**
 * @className: Dom
 * @description: 动态规划
 * @author: luweiming
 * @date: 2021/12/24
 **/
public class Dom {


//    public static void main(String[] args) {
//
//    System.out.println(solution3(10));
//    System.out.println(numWays(10));
//    }


    public static int numWays(int n) {
        if (n<= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = (a + b)% 1000000007;
            a = b;
            b = temp;
        }
        return temp;
    }

    public static String getLPS(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        // 第一维参数表示起始位置坐标，第二维参数表示终点坐标
        // lps[j][i] 表示以 j 为起始坐标，i 为终点坐标是否为回文子串
        boolean[][] lps = new boolean[length][length];
        // 记录最长回文子串最长长度
        int maxLen = 1;
        // 记录最长回文子串起始位置
        int start = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j < 2) {
                    // 子字符串长度小于 2 的时候单独处理
                    lps[j][i] = chars[i] == chars[j];
                } else {
                    // 如果 [i, j] 是回文子串，那么一定有 [j+1, i-1] 也是回子串
                    lps[j][i] = lps[j + 1][i - 1] && (chars[i] == chars[j]);
                }
                if (lps[j][i] && (i - j + 1) > maxLen) {
                    // 如果 [i, j] 是回文子串，并且长度大于 max，则刷新最长回文子串
                    maxLen = i - j + 1;
                    start = j;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }


    /**
     * 青蛙跳阶问题 青蛙每次可以跳一个  也可以跳两个  有多少种跳法
     */
    public class Solution {
        public int numWays(int n) {
            if (n<= 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            int a = 1;
            int b = 2;
            int temp = 0;
            for (int i = 3; i <= n; i++) {
                temp = (a + b)% 1000000007;
                a = b;
                b = temp;
            }
            return temp;
        }
    }

    public class Solution1 {
        //使用哈希map，充当备忘录的作用
        Map<Integer, Integer> tempMap = new HashMap();
        public int numWays(int n) {
            // n = 0 也算1种
            if (n == 0) {
                return 1;
            }
            if (n <= 2) {
                return n;
            }
            //先判断有没计算过，即看看备忘录有没有
            if (tempMap.containsKey(n)) {
                //备忘录有，即计算过，直接返回
                return tempMap.get(n);
            } else {
                // 备忘录没有，即没有计算过，执行递归计算,并且把结果保存到备忘录map中，对1000000007取余（这个是leetcode题目规定的）
                tempMap.put(n, (numWays(n - 1) + numWays(n - 2)) % 1000000007);
                return tempMap.get(n);
            }
        }
    }







    /*// 判断传入的值，是否为空，为空返回false
    if (!head)
        return false;
    // 定义两个值cur、pre
    let cur = head, pre = head;
    // 判断，当cur及cur的下一指针所指节点都不为空时，往前走，cur往前走一步，pre往前走两步，当cur与pre再次相同，则形成环形链表
    while (cur && cur.next) {
        cur = cur.next;
        pre = pre.next.next;
        if (cur === pre) {
            // 当cur与pre相同，则指针所指的节点为同一个节点，此链表为环形链表，返回true
            return true
        }
    }
    // 跳出循环，则表示已循环结束，最终cur都不等同pre，不存在环形链表，返回false
    return false;*/


    /**
     * 链表是否有环
     * @param head
     * @return
     */
    public boolean hasRing(Node head){
        if (Objects.isNull(head)){
            return false;
        }
        Node cur = head, prev = head;
        while (cur != null && cur.next != null){
            cur = cur.next;
            prev = prev.next.next;

            if (cur == prev){
                return true;
            }
        }
        return false;
    }


















    /**
     * 最长回文子串  动态规划
     * @param str
     * @return
     */
    public String longestStr(String str){
        if (Objects.isNull(str) || "".equals(str)){
            return "";
        }
        char[] arrayStr = str.toCharArray();
        //存储历史结果
        Boolean[][] history = new Boolean[][]{};
        Integer maxLen = 1;
        Integer start = 0;

        for(int i = 0; i < arrayStr.length; i++) {
            for(int j = 0; j < i; j++) {
                if (i - j < 2){
                    history[j][i] = arrayStr[i] == arrayStr[j];
                } else {
                    history[j][i] = history[j + 1][i - 1] && arrayStr[i] == arrayStr[j];
                }

                if (history[j][i] && i - j + 1 > maxLen){
                    maxLen = i - j + 1;
                    start = j;
                }
            }

        }
        return str.substring(start, start + maxLen);
    }

    public static Integer  solution3(Integer count){
        int[] history = new int[count + 1];

        history[1] = 1;
        history[2] = 2;

        if (count == 1){
            return 1;
        }

        if (count == 2){
            return 2;
        }

        for (int i = 3; i <= count; i++) {
            history[i] = history[i - 1] + history[i - 2];
        }

        return history[count];

    }


    /**
     * 重排链表  [1,2,3,4] -> [1,4,2,3]
     * @param head
     * @return
     */
    public static Node reorderList(Node head) {
        if (head == null) {
            return null;
        }
        List<Node> list = new ArrayList<Node>();
        Node node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;

        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
        return list.get(0);
    }


    /**
     * 最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        //初始化就是边界情况
        dp[0] = 1;
        int maxans = 1;
        //自底向上遍历
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            //从下标0到i遍历
            for (int j = 0; j < i; j++) {
                //找到前面比nums[i]小的数nums[j],即有dp[i]= dp[j]+1
                if (nums[j] < nums[i]) {
                    //因为会有多个小于nums[i]的数，也就是会存在多种组合了嘛，我们就取最大放到dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //求出dp[i]后，dp最大那个就是nums的最长递增子序列啦
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }



    /*public static void main(String[] args) {
        Node node1 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null)))));
        reorderList(node1);

        Node head = new Node(1);
        Node head1 = new Node(25);
        Node head2 = new Node(2);
        Node head3 = new Node(4);

        PriorityQueue<Node> queue = new PriorityQueue<>(4,(a, b)->(a.val-b.val));

        queue.add(head);
        queue.add(head1);
        queue.add(head2);
        queue.add(head3);

        Node node = queue.poll();
        System.out.println(node);
    }*/

    /**
     * 合并k个有序链表
     * @param lists
     * @return
     */
    public static Node mergeKLists(Node[] lists) {

        if(lists.length==0){
            return null;
        }
        //虚拟节点
        Node head = new Node(0);
        Node tail = head;
        //优先队列
        PriorityQueue<Node> queue = new PriorityQueue<>(lists.length,(a, b)->(a.val-b.val));

        //将K个链表头节点合并最小堆
        for (Node node: lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            //获取最小节点，放到结果链表中
            Node node = queue.poll();
            tail.next = node;

            if (node.next != null) {
                queue.add(node.next);
            }
            //指针链表一直往前
            tail = tail.next;
        }
        return head.next;
    }

    /**
     *无重复字符的最长子串 滑动窗口
     * @param s
     * @return
     */
    static int lengthOfLongestSubstring(String s){
        //获取原字符串的长度
        int len = s.length();
        //维护一个哈希集合的窗口
        Set<Character> windows = new HashSet<>();
        int left=0,right =0;
        int res =0;

        while(right<len){
            char c = s.charAt(right);
            //窗口右移
            right++;

            //判断是否左边窗口需要缩减，如果已经包含，那就需要缩减
            while(windows.contains(c)){
                windows.remove(s.charAt(left));
                left++;
            }
            windows.add(c);
            //比较更新答案
            res = Math.max(res,windows.size());
        }
        return res;
    }


    /**
     * 删除链表的倒数第 N 个结点   双指针
     * @param head
     * @param n
     * @return
     */
    public Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0, head);
        Node first = head;
        Node second = dummy;
        //first 比second先走n个节点
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        //直到走到链表尾部
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        //删除节点
        second.next = second.next.next;
        Node ans = dummy.next;
        return ans;
    }

      public static void main(String[] args) {
          lengthOfLongestSubstring("abcabcbb");

      }

  /**
   * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
   * 输入：nums = [1,2,3]   输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   * 输入：nums = [0,1]     输出：[[0,1],[1,0]]
   */
  class SolutionOrder {
          //全排列，即所有路径集合
          List<List<Integer>> allPath = new LinkedList<>();

          public List<List<Integer>> permute(int[] nums) {
              //当前路径，入口路径，path是空的
              List<Integer> path =  new LinkedList<>();
              //递归函数入口，可做选择是nums数组
              backTrace(nums,path);
              return allPath;
          }

          public void backTrace(int[] nums,List<Integer> path){
              //已走路径path的数组长度等于nums的长度,表示走到叶子节点，所以加到全排列集合
              if(nums.length==path.size()){
                  allPath.add(new LinkedList(path));
                  return;
              }

              for(int i=0;i<nums.length;i++){
                  //剪枝，排查已经走过的路径
                  if(path.contains(nums[i])){
                      continue;
                  }
                  //做选择，加到当前路径
                  path.add(nums[i]);
                  //递归，进入下一层的决策
                  backTrace(nums,path);
                  //取消选择
                  path.remove(path.size() - 1);
              }
          }
      }


    /**
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @param originStr
     * @param childStr
     */
        public String minWindow(String s, String t) {
            // 目标字符数组
            Map<Character, Integer> need = new HashMap<>();
            // 窗口数组
            int[] window = new int[128];
            // valid已找到的字符数
            int left = 0, right = 0, valid = 0;
            // 最小子串的长度
            int len = Integer.MAX_VALUE;
            // 最小子串的起始下标
            int start = 0;
            for (int i = 0; i < t.length(); i++) {
                need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
            }

            // 右指针往右走
            while (right < s.length()) {
                char c = s.charAt(right);
                right++;
                // 说明这个字符包含在目标字符串中
                if (need.containsKey(c)) {
                    window[c]++;
                    // 找到了一个满足条件的字符（数量够了）
                    if (window[c] == need.get(c)) {
                        valid++;
                    }
                }

                // 所有的目标字符都已经找到，缩小窗口寻找最小子串
                while (valid == need.size()) {
                    // 更新起始下标和最小子串的长度
                    if (right - left < len) {
                        len = right - left;
                        start = left;
                    }
                    // 移出窗口的元素
                    char d = s.charAt(left);
                    left++;
                    if (need.containsKey(d)) {
                        if (need.get(d) == window[d]) {
                            valid--;
                        }
                        window[d]--;
                    }
                }
            }

            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
        }
}
