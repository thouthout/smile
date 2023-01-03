package day0921;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: Main2
 * @description: TODO
 * @author: luweiming
 * @date: 2022/9/20
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]

 **/








public class Main2 {

    public static void main(String[] args) {
        solution(26, new Integer[]{2,7,11,15});
    }


    public static Integer[] solution(Integer target, Integer[] nums){
        //key
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] res = new Integer[2];

        for (int i = 0; i < nums.length; i++) {

            if (map.get(target - nums[i]) == null){
                map.put(nums[i], i);
            } else {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            }

        }
        return res;
    }

}
