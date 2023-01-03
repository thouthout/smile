package day0923;

import org.w3c.dom.ls.LSOutput;

import java.util.*;

//import java.util.*;


public class Main {

    /**
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,3]
     * 输出：[1,3,2]
     * 示例 2：
     *
     * 输入：nums = [3,2,1]
     * 输出：[1,2,3]
     * 示例 3：
     *
     * 输入：nums = [1,1,5]
     * 输出：[1,5,1]
     *
     */



    /*public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }*/





    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        int[] ints = Arrays.copyOf(arr, num);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < ints.length ; j++) {
                //遍历判断前面的值是否比后面的值大
                if(arr[i]<arr[j]){
                    list.add(j);
                    break;
                }//遍历完成后，如果找不到前面的值比后面的大，则赋值为0
                if(j==ints.length-1){
                    list.add(0);
                }
            }
        }
        list.add(0);
        for (int a:list) {
            System.out.print(a+" ");
        }

    }*/




    /*public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        in.close();

        Map<Character,String[]> keyBoards = new HashMap<>();

        keyBoards.put('1',new String[] {",","."});
        keyBoards.put('2',new String[] {"a","b","c"});
        keyBoards.put('3',new String[] {"d","e","f"});
        keyBoards.put('4',new String[] {"g","h","i"});
        keyBoards.put('5',new String[] {"j","k","l"});
        keyBoards.put('6',new String[] {"m","n","o"});
        keyBoards.put('7',new String[] {"p","q","r","s"});
        keyBoards.put('8',new String[] {"t","u","v"});
        keyBoards.put('9',new String[] {"w","x","y","z"});
        keyBoards.put('0',new String[] {" "});

        if(command.length() == 0) {
            System.out.print("");
        }

        int mod = 0; // 输入模式: 0-数字 1-字符
        char[] cs = command.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<cs.length;i++) {
            if(cs[i] == '#') {
                mod = mod == 0 ? 1 : 0;
            } else if(mod == 1 && Character.isDigit(cs[i])){
                // #2223/2
                int num = 1;
                for(int j=i;j<cs.length;j++) {
                    if(j+1<cs.length && cs[j] == cs[j+1]) {
                        num++;
                        i=j+1;
                    } else {
                        break;
                    }
                }
                String[] keyboard = keyBoards.get(cs[i]);
                String curStr = keyboard[(num-1)%keyboard.length];
                sb.append(curStr);
            } else if(mod == 0 && Character.isDigit(cs[i])) {
                sb.append(cs[i]);
            }
        }
        System.out.print(sb.toString());
    }
*/

    /*public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        int k = in.nextInt();
        in.close();

        char[] chars = line.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(aChar);
        }

        list.sort(Character::compareTo);
        char c = k >= list.size() ? list.get(list.size() - 1) : list.get(k - 1);

        System.out.print(String.valueOf(line.indexOf(c)));
    }*/



    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> set = new HashSet<>();
        ListNode tmp = headA;

        while (tmp != null) {
            set.add(tmp);
            tmp = tmp.next;
        }
        tmp = headB;
        while (tmp != null) {
            if (set.contains(tmp)){
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }




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
}