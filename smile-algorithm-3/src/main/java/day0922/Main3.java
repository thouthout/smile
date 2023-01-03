package day0922;

/**
 * @className: Main3
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 输入：x = 0
 * 输出：0
 **/
public class Main3 {


    public static void main(String[] args) {
        int reverse = reverse(123090);
        System.out.println(reverse);
    }











    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10  || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int index = x % 10; // 取出整数右边值
            x = x / 10;
            rev = rev * 10 + index;

        }
        return rev;
    }





}
