package day0921;

import javax.print.Doc;

/**
 * @className: Main5
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 **/
public class Main5 {


    public static void main(String[] args) {
        Double aDouble = middleNumber(new Integer[]{1, 2}, new Integer[]{3, 4});
        System.out.println(aDouble);
    }







    public static Double middleNumber(Integer[] num1, Integer[] num2){

        Integer[] resultNum = new Integer[num1.length + num2.length];
        Integer index1 = 0;
        Integer index2 = 0;

        Integer resultIndex = 0;
        while(index1 < num1.length || index2 < num2.length){

            if (num1[index1] < num2[index2]){
                resultNum[resultIndex] = num1[index1];
                if (index1 < num1.length - 1){
                    index1 ++;
                }
            } else {
                resultNum[resultIndex] = num2[index2];
                if (index2 < num2.length - 1){
                    index2 ++;
                }
            }
            resultIndex ++;
        }
        Double x = 2D;
        if (resultNum.length % 2 == 0){
           return  (resultNum[resultNum.length / 2] + resultNum[resultNum.length / 2 + 1]) / x;
        } else {
            return resultNum[resultNum.length / 2 + 1] / x;
        }
    }















}
