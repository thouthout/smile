package dp;

import curry.Node;

import javax.swing.tree.TreeNode;
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
                if (i - j + 1 <= maxLen){
                    break;
                }
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
        int maxLen = 1;
        int start = 0;

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

      /*public static void main(String[] args) {
          int  abcabcbb= lengthOfLongestSubstring("abcabcbb");
            System.out.println(abcabcbb);
      }*/

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

      public static Node main(Node head, int n) {
            Node dum = new Node(0, head);
            Node fast = head, slow = dum;
            for (int i = 0; i < n; i++){
                fast = fast.next;
            }
            while (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;

            return dum.next;
      }

      public static Node reverseNode(Node head){
           Node prev = null;
           while (head != null){
               Node tempNext = head.next;
               head.next = prev;
               prev = head;
               head = tempNext;
           }
           return prev;
      }

      /*public static void main(String[] args) {
          Node node1 = new Node(1);
          Node node2 = new Node(2, node1);
          Node node3 = new Node(3, node2);
          Node node4 = new Node(4, node3);
          Node node5 = new Node(5, node4);
          //Node main = reverseNode(node5);
        //System.out.println(main);

          System.out.println(maxProfit(new int[]{2,10, 1, 5, 3, 6, 4}));
      }*/

    public static int maxProfit(int[] prices) {

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;

    }


    /**
     *
     * int res=x^y;//x与y进行异或运算
     *         int count=0;//设置位数
     *         while(res!=0){
     *             if((res & 1) == 1){ //res与1进行逻辑与运算 每次最低位与1进行运算
     *                 count++;
     *             }
     *             res= res >> 1;
     *         }
     *         return count;
     * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
     *
     *
     *
     * 示例 1：
     *
     * 输入：x = 1, y = 4
     * 输出：2
     * 解释：
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     * 上面的箭头指出了对应二进制位不同的位置。
     * 示例 2：
     *
     * 输入：x = 3, y = 1
     * 输出：1
     */

    /*public static void main(String[] args) {
        hammingDistance(1, 4);
    }*/

    public static int hammingDistance(int x, int y) {
        int mid = x ^ y;

        int res = 0;

        while(mid > 0){
            if ((mid & 1) == 1){
                res ++;
            }
            mid = mid << 1;
        }

        return res;
    }


    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     *
     *
     *
     * 示例 :
     * 给定二叉树
     *
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     *
     *
     *
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     */








    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    static int ans = 1;

    public int dept(TreeNode root){

        if (root == null){
            return 0;
        }
        int deptLeft = dept(root.left);
        int deptRight = dept(root.right);

        ans = Math.max(ans, deptLeft + deptRight + 1);

        return Math.max(deptLeft , deptRight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dept(root);
        return  ans - 1;
    }





    public TreeNode mergeTrees(TreeNode root1, TreeNode root2){

        if (root1 == null || root2 == null){
            return root1 == null ? root2 : root1;
        }

        root1.val += root2.val;

        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;

    }















    /**
     * 给你两棵二叉树： root1 和 root2 。
     *
     * 想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
     *
     * 返回合并后的二叉树。
     *
     * 注意: 合并过程必须从两个树的根节点开始。
     *
     *
     *
     * 示例 1：
     *
     *
     * 输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
     * 输出：[3,4,5,5,4,null,7]
     * 示例 2：
     *
     * 输入：root1 = [1], root2 = [1,2]
     * 输出：[2,2]
     */















    public static void quickSort(int[] arr,int low,int high) {
        int p,i,j,temp;

        if(low >= high) {
            return;
        }
        //p就是基准数,这里就是每个数组的第一个
        p = arr[low];
        i = low;
        j = high;
        while(i < j) {
            //右边当发现小于p的值时停止循环
            while(arr[j] >= p && i < j) {
                j--;
            }

            //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

            //左边当发现大于p的值时停止循环
            while(arr[i] <= p && i < j) {
                i++;
            }

            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
        arr[i] = p;
        quickSort(arr,low,j-1);  //对左边快排
        quickSort(arr,j+1,high); //对右边快排

    }


    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     *
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     *
     * 请你实现这个将字符串进行指定行数变换的函数：
     *
     * string convert(string s, int numRows);
     *
     *
     * 示例 1：
     *
     * 输入：s = "PAYPALISHIRING", numRows = 3
     * 输出："PAHNAPLSIIGYIR"
     * 示例 2：
     * 输入：s = "PAYPALISHIRING", numRows = 4
     * 输出："PINALSIGYAHRPI"
     * 解释：
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     */




    public String convert(String s, int numRows) {
        if(numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);
            if(i == 0 || i == numRows -1) flag = - flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : rows) res.append(row);
        return res.toString();
    }
}






